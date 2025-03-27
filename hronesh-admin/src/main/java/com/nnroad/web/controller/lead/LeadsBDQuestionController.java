package com.nnroad.web.controller.lead;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.utils.SecurityUtils;
import com.nnroad.lead.domain.ClientDBQuestion;
import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.page.TableDataInfo;
import com.nnroad.lead.service.ILeadsBDQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/client/clientLeads/question")
public class LeadsBDQuestionController extends BaseController {
    @Autowired
    private ILeadsBDQuestionService leadsBDQuestionService;

    /**
     * 表格数据
     * @param clientDBQuestion
     * @return
     */
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody ClientDBQuestion clientDBQuestion) {
        startPage();
        List<ClientDBQuestion> list = leadsBDQuestionService.selectClientLeadsQuestion(clientDBQuestion);
        for (ClientDBQuestion question : list) {
            if (question.getFresult() != null) {
                question.setFresult(getResult(question.getFresult()));
            }
        }
        return getDataTable(list);
    }

    /**
     * 解析 Fresult最后一个字段内容
     * @param fresult
     * @return
     */
    private static String getResult(String fresult) {
        try {
            // 创建 ObjectMapper 实例
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 JSON 字符串转换为 List<Map<String, Object>> 类型
            List<Map<String, Object>> list = objectMapper.readValue(fresult, List.class);

            // 获取列表中的最后一个元素
            Map<String, Object> lastItem = list.get(list.size() - 1);

            // 获取最后一个元素的 "value" 字段
            Object value = lastItem.get("value");

            // 返回 value 字段的值，如果是 String 类型则直接返回
            return value.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;  // 如果解析失败，则返回 null
        }
    }

    /**
     * 查询统计
     * @param params
     * @return
     * @throws Exception
     */
    @PostMapping("/queryStatistics")
    public TableDataInfo queryStatistics(@RequestBody Map<String, String> params) throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String sendStartDate = String.valueOf(dateFormat.parse(params.get("sendStartDate")).getTime());
        String sendEndDate = String.valueOf(dateFormat.parse(params.get("sendEndDate")).getTime());

        // 获取问题标题
        String question = leadsBDQuestionService.selectBDQuestion();
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> items = mapper.readValue(question, List.class);

        List<String> titles = new ArrayList<>();
        List<String> types = new ArrayList<>();

        for (Map<String, Object> item : items) {
            String title = (String) item.get("title");
            String type = (String) item.get("type");
            if (!"input".equals(type)) {
                titles.add(title);
            }
            types.add(type);
        }

        // 获取问题回答列表
        List<String> list = leadsBDQuestionService.selectBDQuery(sendStartDate, sendEndDate);
        ObjectMapper mapper2 = new ObjectMapper();
        Map<Integer, Map<Integer, Integer>> questionAnswerCounts = new HashMap<>();

        // 遍历每个响应
        for (String response : list) {
            List<Map<String, Object>> answers = mapper2.readValue(response, new TypeReference<List<Map<String, Object>>>(){});

            // 遍历每个回答
            for (Map<String, Object> answer : answers) {
                int questionSeq = (int) answer.get("seq");

                // 检查 questionSeq 是否在 titles 列表的范围内，并且确保它是 "rate" 类型的问题
                if (questionSeq > 0 && questionSeq <= titles.size() &&
                        ! ("input".equals(types.get(questionSeq - 1)))) {
                    Object answerValueObj = answer.get("value");

                    // 确保 answerValueObj 被转换为 Integer 类型
                    int answerValue = 0;
                    if (answerValueObj instanceof String) {
                        // 如果是 String 类型，尝试转换为 Integer
                        answerValue = Integer.parseInt((String) answerValueObj);
                    } else if (answerValueObj instanceof Integer) {
                        // 如果已经是 Integer 类型，直接使用
                        answerValue = (Integer) answerValueObj;
                    }

                    // 初始化每个问题的回答计数 Map
                    questionAnswerCounts.putIfAbsent(questionSeq, new HashMap<>());
                    Map<Integer, Integer> answerCounts = questionAnswerCounts.get(questionSeq);
                    // 更新回答计数
                    answerCounts.put(answerValue, answerCounts.getOrDefault(answerValue, 0) + 1);
                }
            }
        }

        // 计算比例
        Map<Integer, Map<Integer, Double>> questionAnswerRatios = new HashMap<>();

        // 遍历每个问题的回答计数
        for (Map.Entry<Integer, Map<Integer, Integer>> entry : questionAnswerCounts.entrySet()) {
            int questionSeq = entry.getKey();
            Map<Integer, Integer> answerCounts = entry.getValue();

            // 计算该问题的总回答数
            int totalAnswers = answerCounts.values().stream().mapToInt(Integer::intValue).sum();

            // 存储比例数据
            Map<Integer, Double> answerRatios = new HashMap<>();
            for (Map.Entry<Integer, Integer> answerEntry : answerCounts.entrySet()) {
                int answerValue = answerEntry.getKey();
                int count = answerEntry.getValue();
                double percentage = (count * 100.0) / totalAnswers;
                answerRatios.put(answerValue, percentage);  // 存储答案比例
            }
            questionAnswerRatios.put(questionSeq, answerRatios);  // 存储每个问题的答案比例
        }

        // 将 titles 和 questionAnswerRatios 合并到一个 List 中
        List<Object> combinedDataList = new ArrayList<>();
        combinedDataList.add(titles);               // 添加 titles 列表
        combinedDataList.add(questionAnswerRatios); // 添加 questionAnswerRatios 映射

        int send=leadsBDQuestionService.selectBDstatus(sendStartDate, sendEndDate,1);
        int unSend=leadsBDQuestionService.selectBDstatus(sendStartDate, sendEndDate,0);
        int count=leadsBDQuestionService.countBDQuery(sendStartDate, sendEndDate);
        int totalScore = leadsBDQuestionService.countBDScores(sendStartDate, sendEndDate,1);
        int totalSubmit = leadsBDQuestionService.countBDSubmit(sendStartDate, sendEndDate,1);

        combinedDataList.add(send);                 // 添加已发送数量/已发送人数
        combinedDataList.add(unSend);               // 添加未发送数量
        combinedDataList.add(count);                // 添加问卷总数
        combinedDataList.add(totalScore);           // 添加总分数
        combinedDataList.add(totalSubmit);          // 添加已提交人数

        return getDataTable(combinedDataList);
    }

    /**
     * 跟进
     * @param clientDBQuestion
     * @return
     */
    @PostMapping("/follow")
    public AjaxResult follow(@RequestBody ClientDBQuestion clientDBQuestion) {
        Long userId = SecurityUtils.getUserId();
        clientDBQuestion.setFfollowedBy(Long.toString(userId));
        return success(leadsBDQuestionService.updateDBQuestionFollowStatus(clientDBQuestion));
    }

}
