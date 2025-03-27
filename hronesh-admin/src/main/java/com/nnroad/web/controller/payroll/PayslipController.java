package com.nnroad.web.controller.payroll;

import com.nnroad.common.annotation.Log;
import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.core.page.TableDataInfo;
import com.nnroad.common.core.text.Convert;
import com.nnroad.common.enums.BusinessType;
import com.nnroad.common.utils.DateUtils;
import com.nnroad.common.utils.SecurityUtils;
import com.nnroad.common.utils.poi.HroneExcelUtil;
import com.nnroad.payroll.constants.PayrollConstants;
import com.nnroad.payroll.domain.MyPsPayslip;
import com.nnroad.payroll.domain.MyPsPayslipDto;
import com.nnroad.payroll.domain.common.PsPayslip;
import com.nnroad.payroll.service.IPsBasicInfoService;
import com.nnroad.payroll.service.IPsPayslipService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 工资单信息管理Controller
 */
@Controller
@RequestMapping("/payroll/payslip")
public class PayslipController extends BaseController {

    @Value("${sys.orgs}")
    private String orgs;

    @Autowired
    private IPsPayslipService payslipService;

    @Autowired
    private IPsBasicInfoService psBasicInfoService;

    /**
     * 查询工资单信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('payroll:payslip:list')")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestBody PsPayslip payslip) {
        startPage();
        List<PsPayslip> list = payslipService.selectPsPayslipListByEmployeeOrg(payslip);
        return getDataTable(list);
    }

    /**
     * 导出工资单信息管理列表
     */
    @RequiresPermissions("payroll:payslip:export")
    @Log(title = "工资单信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PsPayslip payslip) {
        List<PsPayslip> list = payslipService.selectPsPayslipList1(payslip);
        HroneExcelUtil<PsPayslip> hroneExcelUtil = new HroneExcelUtil<PsPayslip>(PsPayslip.class);
        return hroneExcelUtil.exportExcel(list, "payslip");
    }
//
//    /**
//     * 新增工资单信息管理
//     */
//    @GetMapping("/add")
//    public String add() {
//        return prefix + "/add";
//    }
//
//    /**
//     * 新增保存工资单信息管理
//     */
//    @RequiresPermissions("payroll:payslip:add")
//    @Log(title = "工资单信息管理", businessType = BusinessType.INSERT)
//    @PostMapping("/add")
//    @ResponseBody
//    public AjaxResult addSave(PsPayslip payslip) {
//        return toAjax(payslipService.insertPsPayslip(payslip));
//    }

//    /**
//     * 修改工资单信息管理
//     */
//    @GetMapping("/edit/{id}")
//    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
//        PsPayslip payslip = payslipService.selectPsPayslipById(id);
//        mmap.put("payslip", payslip);
//        return prefix + "/edit";
//    }

//    /**
//     * 修改保存工资单信息管理
//     */
//    @RequiresPermissions("payroll:payslip:edit")
//    @Log(title = "工资单信息管理", businessType = BusinessType.UPDATE)
//    @PostMapping("/edit")
//    @ResponseBody
//    public AjaxResult editSave(PsPayslip payslip) {
//        return toAjax(payslipService.updatePsPayslip(payslip));
//    }

//    /**
//     * 删除工资单信息管理
//     */
//    @RequiresPermissions("payroll:payslip:remove")
//    @Log(title = "工资单信息管理", businessType = BusinessType.DELETE)
//    @PostMapping("/remove")
//    @ResponseBody
//    public AjaxResult remove(String ids) {
//        return toAjax(payslipService.deletePsPayslipByIds(ids));
//    }

    /**
     * 工资单查看
     */
    @PreAuthorize("@ss.hasPermi('payroll:payslip:view')")
    @GetMapping("/view/{id}")
    @ResponseBody
    public AjaxResult view(@PathVariable Long id) {
        PsPayslip psPayslip = payslipService.selectPsPayslipById(id);
        MyPsPayslipDto myPsPayslipDto = new MyPsPayslipDto();
        myPsPayslipDto.setId(id);
        myPsPayslipDto.setEeNo(psPayslip.getIdNo());
        myPsPayslipDto.setDuration(psPayslip.getDuration());
        MyPsPayslip myPsPayslip = payslipService.getMyPayslipData(myPsPayslipDto);
        //期间格式化
        myPsPayslip.setDuration(DateUtils.dateFormat(myPsPayslip.getDuration()));
        //不需要另存为
        myPsPayslip.setType(PayrollConstants.SAVE_AS_NOT_NEEDED);
       return success(myPsPayslip);
    }

    /**
     * 工资单开放查询
     */
    @PreAuthorize("@ss.hasPermi('payroll:payslip:openQuery')")
    @Log(title = "工资单信息管理", businessType = BusinessType.UPDATE)
    @PutMapping("/openQuery")
    @ResponseBody
    public AjaxResult openQuery(@RequestParam String ids) {
        String message = payslipService.openQueryPsPayslipByIds(ids);
        return  AjaxResult.success(message);
    }

    @GetMapping("/sysConfigOrg")
    @ResponseBody
    public AjaxResult getsysConfigOrg(){
        List<String> orgList =new ArrayList<>();
        if (orgs != null && !orgs.trim().isEmpty()) {
            String[] orgArray = orgs.split(",\\s*");
           orgList = Arrays.asList(orgArray);
        }
        return  success(orgList);
    }
}
