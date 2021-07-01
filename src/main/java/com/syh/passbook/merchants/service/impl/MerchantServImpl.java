package com.syh.passbook.merchants.service.impl;

import com.google.gson.GsonBuilder;
import com.syh.passbook.merchants.constant.Constants;
import com.syh.passbook.merchants.constant.ErrorCode;
import com.syh.passbook.merchants.dao.MerchantDao;
import com.syh.passbook.merchants.entity.Merchant;
import com.syh.passbook.merchants.service.IMerchantServ;
import com.syh.passbook.merchants.vo.CreateMerchantRequest;
import com.syh.passbook.merchants.vo.CreateMerchantResponse;
import com.syh.passbook.merchants.vo.PassTemplate;
import com.syh.passbook.merchants.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
public class MerchantServImpl implements IMerchantServ {
    private final MerchantDao merchantDao;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public MerchantServImpl(MerchantDao merchantDao, KafkaTemplate<String, String> kafkaTemplate) {
        this.merchantDao = merchantDao;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @Transactional
    public Response createMerchant(CreateMerchantRequest request) {
        Response response = new Response();
        CreateMerchantResponse merchantResponse = new CreateMerchantResponse();
        ErrorCode errorCode = request.validate(merchantDao);
        if (errorCode != ErrorCode.SUCCESS) {
            merchantResponse.setId(-1);
            response.setErrorCode(errorCode.getCode());
            response.setErrorMsg(errorCode.getDesc());
        } else {
            merchantResponse.setId(merchantDao.save(request.toMerchant()).getId());
        }
        response.setData(merchantResponse);
        return response;
    }

    @Override
    public Response buildMerchantInfoById(Integer id) {
        Response response = new Response();
        Optional<Merchant> merchant = merchantDao.findById(id);
        if (!merchant.isPresent()) {
            response.setErrorCode(ErrorCode.MERCHANT_NOT_EXIST.getCode());
            response.setErrorMsg(ErrorCode.MERCHANT_NOT_EXIST.getDesc());
        }
        response.setData(merchant.orElse(null));
        return response;
    }

    @Override
    public Response distributePassTemplate(PassTemplate passTemplate) {
        Response response = new Response();
        ErrorCode errorCode = passTemplate.validate(merchantDao);

        if (errorCode != ErrorCode.SUCCESS) {
            response.setErrorCode(errorCode.getCode());
            response.setErrorMsg(errorCode.getDesc());
        } else {
            String passTemplateJsonString = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX").create().toJson(passTemplate);
            // send(topic, key, value)
            kafkaTemplate.send(
                    Constants.TEMPLATE_TOPIC,
                    Constants.TEMPLATE_TOPIC,
                    passTemplateJsonString
            );
            log.debug("DistributePassTemplates: {}", passTemplateJsonString);
        }

        return response;
    }
}
