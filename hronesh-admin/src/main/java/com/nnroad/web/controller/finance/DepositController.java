package com.nnroad.web.controller.finance;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.github.pagehelper.util.StringUtil;
import com.nnroad.common.annotation.Log;
import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.core.domain.entity.SysDictData;
import com.nnroad.common.core.page.TableDataInfo;
import com.nnroad.common.core.text.Convert;
import com.nnroad.common.enums.BusinessType;
import com.nnroad.common.exception.ServiceException;
import com.nnroad.common.utils.SecurityUtils;
import com.nnroad.common.utils.poi.ExcelUtil;
import com.nnroad.employee.constants.enums.EEStatus;
import com.nnroad.employee.domain.SysEmployee;
import com.nnroad.employee.model.dto.EEBaseDTO;
import com.nnroad.employee.service.ISysEmployeeService;
import com.nnroad.finance.constants.ConstantKey;
import com.nnroad.finance.domain.DepositClient;
import com.nnroad.finance.model.dto.DepositClientDetailDto;
import com.nnroad.finance.model.form.DepositClientCondition;
import com.nnroad.finance.model.form.DepositClientDetailCondition;
import com.nnroad.finance.model.form.DepositEmployeeCondition;
import com.nnroad.finance.model.form.DepositForm;
import com.nnroad.finance.model.vo.DepositClientDetailVo;
import com.nnroad.finance.model.vo.DepositEmployeeVo;
import com.nnroad.finance.service.IDepositClientDetailService;
import com.nnroad.finance.service.IDepositClientService;
import com.nnroad.finance.service.IDepositEmployeeService;
import com.nnroad.system.service.ISysDictDataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 客户押金 Controller
 *
 * @author Lucio
 * @date 2021-05-13
 */
@RestController
@RequestMapping("/finance/deposit")
@Slf4j
public class DepositController extends BaseController {
    @Autowired
    private IDepositClientService depositClientService;

    @Autowired
    private IDepositEmployeeService depositEmployeeService;

    @Autowired
    private IDepositClientDetailService depositClientDetailService;

    @Autowired
    private ISysDictDataService dictDataService;

    @Autowired
    private ISysEmployeeService employeeService;

    @RequiresPermissions("finance:deposit:list")
    @RequestMapping("/total")
    public AjaxResult clientTotalAmount() {
        return success(depositClientService.getDepositTotal());
    }

    @RequiresPermissions("finance:deposit:list")
    @RequestMapping("/list_client")
    public TableDataInfo listClient(DepositForm form) {
        startPage();
        List<DepositClient> depositList = depositClientService.getDepositClientList(setQueryCondition(form));
        return getDataTable(depositList);
    }

    @RequiresPermissions("finance:deposit:list")
    @RequestMapping("/list_employee")
    public TableDataInfo listEmployee(DepositForm form) {
        startPage();
        DepositEmployeeCondition condition = new DepositEmployeeCondition();
        condition.setClientCode(form.getClientCode());
        condition.setMonthSettle(form.getMonthSettle());
        // 时间范围处理
        if (StringUtil.isNotEmpty(form.getDateRange())) {
            Map<String, Object> params = condition.getParams();
            String[] dateArr = Convert.toStrArray("~", form.getDateRange().replaceAll(" ", ""));
            if (dateArr.length == 2) {
                String beginTime = dateArr[0];
                String endTime = dateArr[1];
                params.put("beginTime", beginTime.trim());
                params.put("endTime", endTime.trim());
            }
        }
        List<DepositEmployeeVo> depositList = depositEmployeeService.getDepositEmployeeList(condition);

        return getDataTable(depositList);
    }

    @RequiresPermissions("finance:deposit:list")
    @RequestMapping("/list_detail")
    public TableDataInfo listDetail(DepositForm form) {
        startPage();
        List<DepositClientDetailVo> depositList =
                depositClientDetailService.getDepositClientDetailList(DepositClientDetailCondition.fromDepositForm(form));

        return getDataTable(depositList);
    }


    @GetMapping("/detail/dict/{clientCode}")
    @RequiresPermissions("finance:deposit:list")
    public AjaxResult detailDict(@PathVariable String clientCode) {
        Map<String, List<SysDictData>> dictMap = dictDataService.selectByDictTypes(Arrays.asList("depositSource", "currency"))
                .stream().collect(Collectors.groupingBy(SysDictData::getDictType));
        Map<String, Object> data = new HashMap<>(dictMap);
        List<EEBaseDTO> sysEmployees =
                employeeService.queryBaseByClientCode(clientCode, Arrays.asList(EEStatus.ONGOING.getStatus(), EEStatus.OFF_BOARD.getStatus()));
        data.put("eeList", sysEmployees);

        return success(data);
    }

    @RequiresPermissions("finance:deposit:add")
    @Log(title = "Deposit", businessType = BusinessType.INSERT)
    @PostMapping("/create")
    public AjaxResult create(@RequestBody DepositClientDetailDto dto) {
        depositClientService.addDepositClientDetail(dto);
        return AjaxResult.success();
    }

    @GetMapping("/query_by_id/{depositId}")
    @RequiresPermissions("finance:deposit:add")
    public AjaxResult detail(@PathVariable Long depositId) {

        return success(depositClientDetailService.getDepositClientDetailById(depositId));
    }

    @RequiresPermissions("finance:deposit:edit")
    @Log(title = "Deposit", businessType = BusinessType.UPDATE)
    @PutMapping("/save")
    public AjaxResult save(@RequestBody DepositClientDetailDto dto) {
        depositClientService.editDepositClientDetail(dto);
        return AjaxResult.success();
    }

    /*@PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<DepositClientDetailDto> util = new ExcelUtil<>(DepositClientDetailDto.class);
        List<DepositClientDetailDto> list = util.importExcel(file.getInputStream());
        String operName = SecurityUtils.getLoginUser().getUser().getUserName();
        String message = depositClientService.importData(list, updateSupport, operName);
        return AjaxResult.success(message);
    }

    *//**
     * 导入数据模板
     *//*
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<DepositClientDetailDto> util = new ExcelUtil<DepositClientDetailDto>(DepositClientDetailDto.class);
        return util.exportExcel(null, "DepositClientDetail");
    }*/

    @RequiresPermissions("finance:deposit:delete")
    @Log(title = "Deposit", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{id}")
    public AjaxResult delete(@PathVariable Long id) {
        depositClientService.removeDepositClientDetail(id);
        return AjaxResult.success();
    }

    /*@RequiresPermissions("finance:deposit:settle")
    @Log(title = "Deposit", businessType = BusinessType.OTHER)
    @PostMapping("/settle")
    @Transactional
    public AjaxResult settle(String clientCode) {
        depositClientService.settleDepositClient(clientCode);
        return AjaxResult.success();
    }*/

    /**
     * 导出客户押金列表
     */
    @Log(title = "Deposit", businessType = BusinessType.EXPORT)
    @RequiresPermissions("finance:deposit:export:client")
    @PostMapping("/export_client")
    public void exportClient(@RequestBody DepositForm form,
                             HttpServletResponse response) {

        depositClientService.export(setQueryCondition(form), response);
    }

    private DepositClientCondition setQueryCondition(DepositForm form) {
        DepositClientCondition condition = new DepositClientCondition();
        if (StrUtil.isNotBlank(form.getClientCode())) {
            condition.setClientCodeList(Convert.toStrArray(form.getClientCode()));
        }
        // 时间范围处理
        if (StrUtil.isNotEmpty(form.getDateRange())) {
            String[] dateArr = Convert.toStrArray("~", form.getDateRange().replaceAll(" ", ""));
            if (dateArr.length == 2) {
                condition.setMonthSettleStart(dateArr[0]);
                condition.setMonthSettleEnd(dateArr[1]);
            }
        }
        return condition;
    }

    /**
     * 导出客户员工押金列表
     */
    @Log(title = "Deposit", businessType = BusinessType.EXPORT)
    @RequiresPermissions("finance:deposit:export:employee")
    @PostMapping("/export_employee")
    public void exportEmployee(@RequestBody DepositForm form, HttpServletResponse response) {
        List<DepositClientDetailVo> depositList =  depositClientDetailService.getDepositClientDetailList(DepositClientDetailCondition.fromDepositForm(form));
        String fileName = String.format(ConstantKey.DEPOSIT_CLIENT_EE_EXPORT_NAME, DateUtil.format(new Date(), "yyyyMMddHHmmss"));
        try (ExcelWriter excelWriter = EasyExcel.write(ExcelUtil.getOutputStream(fileName, response), DepositClientDetailVo.class).build()) {
            // 这里注意 如果同一个sheet只要创建一次
            WriteSheet writeSheet = EasyExcel.writerSheet("sheet").excludeColumnFieldNames(ConstantKey.ENTRY_IGNORE_FILES).build();
            //写入本地文件
            excelWriter.write(depositList, writeSheet);
            excelWriter.finish();
        } catch (Exception e) {
            log.error("上海账套导出失败：", e);
            throw new ServiceException(e.getMessage());
        }
    }
}