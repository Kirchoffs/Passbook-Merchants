package com.syh.passbook.merchants.constant;

public enum TemplateColor {
    RED(1, "red"),
    GREEN(2, "green"),
    BLUE(3, "blue");

    private Integer code;
    private String color;

    TemplateColor(Integer code, String color) {
        this.code = code;
        this.color = color;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getColor() {
        return this.color;
    }
}