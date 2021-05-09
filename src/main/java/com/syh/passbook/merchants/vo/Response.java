package com.syh.passbook.merchants.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// vo: value object, transferred between service

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private Integer errorCode = 0;

    /** error message, if correct, return empty string */
    private String errorMsg = "";

    private Object data;

    /**
     *
     * @param data
     */
    public Response(Object data) {
        this.data = data;
    }
}
