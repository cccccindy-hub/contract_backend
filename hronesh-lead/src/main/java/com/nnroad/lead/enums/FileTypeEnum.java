package com.nnroad.lead.enums;

public enum FileTypeEnum {
    //1 Agreemnet 2 Appendix
    membershipAgreemnetFiles(1, "membershipAgreemnetFiles"),
    businessLicenseFile(2, "businessLicenseFile");

    private final Integer type;
    private final String name;

    FileTypeEnum(Integer type, String name)
    {
        this.type = type;
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
