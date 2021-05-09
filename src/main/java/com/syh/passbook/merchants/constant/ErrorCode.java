package com.syh.passbook.merchants.constant;

public enum ErrorCode {
    SUCCESS(0, ""),
    DUPLICATE_NAME(1, "Repeated merchant name"),
    EMPTY_LOGO(2, "Merchant's logo is empty"),
    EMPTY_BUSINESS_LICENCE(3, "Merchant's business licence is empty"),
    ERROR_PHONE(4, "Phone error"),
    EMPTY_ADDRESS(5, "Merchant's address is empty"),
    MERCHANT_NOT_EXIST(6, "Merchant is not existed"),
    EMPTY_NAME(7, "Merchant name is empty");

    private Integer code;
    private String desc;
    ErrorCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
