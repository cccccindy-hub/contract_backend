package com.nnroad.quartz.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnroad.client.domain.SysClient;
import com.nnroad.client.service.IClientQuestionService;
import com.nnroad.system.constants.MailEnum;
import com.nnroad.common.utils.MailTemplate;
import com.nnroad.common.utils.StringUtils;
import com.nnroad.system.domain.SysEmailSend;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.nnroad.system.service.ISysMailService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Component("mailTask")
public class mailTask {
    @Value("${api.surveyApiUrl}")
    private String surveyApiUrl;

    @Value("${api.questionHtml}")
    private String questionHtml;

    @Autowired
    private ISysMailService sysMailServiceImpl;

    @Autowired
    private IClientQuestionService clientService;


    @Value("${sys.company}")
    private String companys;

    private static final Logger logger = LoggerFactory.getLogger(mailTask.class);
    public void sendEMail() {
        List<SysClient> clients= clientService.selectClient3();
        Set<String> allowedCompanies = new HashSet<>(Arrays.asList(companys.split(",")));
        for (SysClient client : clients) {
            try {
                if (client.getOrg() != null && allowedCompanies.contains(client.getOrg())
                        && client.getContactPersonEmail() != null
                        && !client.getContactPersonEmail().equals("null")
                        && !client.getContactPersonEmail().equals("")) {
                    Email(client); // 调用发送邮件的方法
                }
            } catch (Exception e) {
                // 捕获异常并记录错误信息
                e.printStackTrace(); // 打印详细堆栈信息（可选）
            }
        }

    }

    public void Email(SysClient client) {
        try {

            Calendar calendar = Calendar.getInstance();

            // 将日期设置为下个月的第一天
            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DAY_OF_MONTH, 1);

            // 然后再回到上一天，即当前月的最后一天
            calendar.add(Calendar.DAY_OF_MONTH, -1);

            // 设置时间为当天的最后一刻
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 999);

            // 获取时间戳
            long expiredAt = calendar.getTimeInMillis();

            String email = client.getContactPersonEmail();
            String surveyId = clientService.selectCRM();
            String reciverId = client.getCompanyCode();
            String reciverName = client.getClientName();
            String result = null;

            // 构建 JSON 字符串
            String jsonString = String.format("{"
                    + "\"surveyId\":\"%s\","
                    + "\"expiredAt\":%d,"
                    + "\"reciverId\":\"%s\","
                    + "\"reciverName\":\"%s\","
                    + "\"reciverEmail\":\"%s\""
                    + "}", surveyId, expiredAt, reciverId, reciverName, email);

            URL url = new URL(surveyApiUrl + "/server/mail_to");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);

            // 发送 JSON 数据
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            // 读取服务器的响应内容
            int responseCode = conn.getResponseCode();
            StringBuilder response = new StringBuilder();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                }
                System.out.println("Response Body: " + response.toString());
            } else {
                System.out.println("Request failed with response code: " + responseCode);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
