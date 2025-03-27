package com.nnroad.web.controller.payroll;

import com.nnroad.common.annotation.Log;
import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.core.page.TableDataInfo;
import com.nnroad.common.enums.BusinessType;
import com.nnroad.common.utils.DateUtils;
import com.nnroad.common.utils.poi.ExcelUtil;
import com.nnroad.common.utils.poi.HroneExcelUtil;
import com.nnroad.payroll.domain.InsuranceAndFees;
import com.nnroad.payroll.service.IInsuranceAndFeesService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 工会费/商业保险/雇主责任险

 */
@Controller
@RequestMapping("/payroll/Insurance")
public class InsuranceAndFeesReportController extends BaseController
{
//    private String prefix = "payroll/workInjuryInsurance";

    @Autowired
    private IInsuranceAndFeesService insuranceAndFeesService;


    /**
     * 查询WorkInjuryInsuranceReport列表
     */
    @PreAuthorize("@ss.hasPermi('paystub:InsuranceAndFees:list')")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestBody InsuranceAndFees insuranceAndFees)
    {
        startPage();
        List<InsuranceAndFees> list = insuranceAndFeesService.selectInsuranceAndFeesList(insuranceAndFees);
        return getDataTable(list);
    }

//    @RequiresPermissions("paystub:workInjuryInsurance:list")
//    @PostMapping("/employeeList")
//    @ResponseBody
//    public AjaxResult employeeList(String clientCode) {
//        ClientEmployeeDto employeeDto = new ClientEmployeeDto();
//        employeeDto.setClientCode(clientCode);
//        List<ClientEmployeeDto> clientEmployeeList = clientEmployeeService.selectClientEmployeeDtoList(employeeDto);
//        return AjaxResult.success(clientEmployeeList);
//    }
//    /**
//     * 导出WorkInjuryInsuranceReport列表
//     */
    @PreAuthorize("@ss.hasPermi('paystub:InsuranceAndFees:export')")
    @Log(title = "WorkInjuryInsuranceReport", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestBody InsuranceAndFees insuranceAndFees)
    {
        List<InsuranceAndFees> list = insuranceAndFeesService.selectInsuranceAndFeesList(insuranceAndFees);
        HroneExcelUtil<InsuranceAndFees> util = new  HroneExcelUtil<>(InsuranceAndFees.class);
        return util.exportExcel(list, "WorkInjuryInsurance");
    }


}
