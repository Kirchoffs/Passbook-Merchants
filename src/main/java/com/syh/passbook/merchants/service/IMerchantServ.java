package com.syh.passbook.merchants.service;

import com.syh.passbook.merchants.vo.CreateMerchantRequest;
import com.syh.passbook.merchants.vo.PassTemplate;
import com.syh.passbook.merchants.vo.Response;

/**
 * Merchant service
 */
public interface IMerchantServ {
    /**
     *
     * @param request {@link CreateMerchantRequest}
     * @return {@link Response}
     */
    Response createMerchant(CreateMerchantRequest request);

    Response buildMerchantInfoById(Integer id);

    Response distributePassTemplate(PassTemplate passTemplate);
}
