package com.nnroad.vendor.model.form;

import lombok.Data;

@Data
public class VendorQueryForm {

    private String vendorName;

    private String clientCode;

    private String beginPeriod;

    private String endPeriod;
}
