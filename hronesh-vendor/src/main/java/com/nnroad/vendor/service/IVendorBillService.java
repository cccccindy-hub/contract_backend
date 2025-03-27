package com.nnroad.vendor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nnroad.vendor.domain.VendorBill;
import com.nnroad.vendor.model.form.VendorQueryForm;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

public interface IVendorBillService extends IService<VendorBill> {

    List<VendorBill> queryPage(VendorQueryForm form);

    void importData(MultipartFile file);

    void add(VendorBill vendorBill);

    void modify(@Valid VendorBill vendorBill);
}
