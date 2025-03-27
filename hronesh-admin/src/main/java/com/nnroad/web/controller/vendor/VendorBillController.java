package com.nnroad.web.controller.vendor;

import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.core.page.TableDataInfo;
import com.nnroad.common.utils.poi.ExcelUtil;
import com.nnroad.finance.constants.ConstantKey;
import com.nnroad.vendor.domain.VendorBill;
import com.nnroad.vendor.model.form.VendorQueryForm;
import com.nnroad.vendor.service.IVendorBillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/vendor/invoice")
@RequiredArgsConstructor
public class VendorBillController extends BaseController {

    private final IVendorBillService vendorBillService;

    @PostMapping("/page")
    public TableDataInfo page(VendorQueryForm form) {
        startPage();
        List<VendorBill> vendorBills = vendorBillService.queryPage(form);

        return getDataTable(vendorBills);
    }

    @GetMapping("/detail/{id}")
    public AjaxResult detail(@PathVariable Long id) {

        return AjaxResult.success(vendorBillService.getById(id));
    }

    @PostMapping("/add")
    public AjaxResult add(@Valid @RequestBody VendorBill vendorBill) {
        vendorBillService.add(vendorBill);

        return success();
    }

    @PutMapping("/modify")
    public AjaxResult modify(@Valid @RequestBody VendorBill vendorBill) {
        vendorBillService.modify(vendorBill);
        return success();
    }

    @DeleteMapping("/del/{id}")
    public AjaxResult delById(@PathVariable("id") Long id) {
        vendorBillService.removeById(id);

        return success();
    }

    @GetMapping("/download_temp")
    public void downloadTemp(HttpServletResponse response) {
        String fileName = String.format(ConstantKey.INVOICE_EXPORT_NAME, DateUtil.format(new Date(), "yyyyMMddHHmmss"));

        try (ExcelWriter excelWriter = EasyExcel.write(ExcelUtil.getOutputStream(fileName, response), VendorBill.class)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).build()) {
            // 这里注意 如果同一个sheet只要创建一次
            List<String> ignoreFiles = ConstantKey.ENTRY_IGNORE_FILES;
            ignoreFiles.add("id");
            ignoreFiles.add("clientCode");
            ignoreFiles.add("eeCode");
            WriteSheet writeSheet = EasyExcel.writerSheet("异地供应商账单").excludeColumnFieldNames(ignoreFiles).build();
            //写入本地文件
            excelWriter.write(new ArrayList<>(), writeSheet);
            excelWriter.finish();
        }
    }

    @PostMapping("/import")
    public AjaxResult importData(@RequestParam("file") MultipartFile file) {
        vendorBillService.importData(file);

        return success();
    }
}
