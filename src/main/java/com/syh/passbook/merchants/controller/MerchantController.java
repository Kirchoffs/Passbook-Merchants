package com.syh.passbook.merchants.controller;

import com.google.gson.Gson;
import com.syh.passbook.merchants.service.IMerchantServ;
import com.syh.passbook.merchants.vo.CreateMerchantRequest;
import com.syh.passbook.merchants.vo.PassTemplate;
import com.syh.passbook.merchants.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/merchant")
public class MerchantController {
    private final IMerchantServ merchantServ;

    @Autowired
    public MerchantController(IMerchantServ merchantServ) {
        this.merchantServ = merchantServ;
    }

    @PostMapping("/create")
    public Response createMerchant(@RequestBody CreateMerchantRequest request) {
        log.info("CreateMerchants: {}", new Gson().toJson(request));
        return merchantServ.createMerchant(request);
    }

    @GetMapping("/{id}")
    public Response buildMerchantInfo(@PathVariable Integer id) {
        log.info("BuildMerchantInfo: {}", id);
        return merchantServ.buildMerchantInfoById(id);
    }

    @ResponseBody
    @PostMapping("/distribute")
    public Response distributePassTemplate(@RequestBody PassTemplate passTemplate) {
        log.info("DistributePassTemplate: {}", passTemplate);
        return merchantServ.distributePassTemplate(passTemplate);
    }
}
