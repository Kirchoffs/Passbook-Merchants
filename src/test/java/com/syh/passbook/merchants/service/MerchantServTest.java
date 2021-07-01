package com.syh.passbook.merchants.service;

import com.google.gson.Gson;
import com.syh.passbook.merchants.vo.CreateMerchantRequest;
import com.syh.passbook.merchants.vo.PassTemplate;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MerchantServTest {
    @Autowired
    private IMerchantServ merchantServ;

    @Transactional
    @Test
    public void testCreateMerchantServ() {
        CreateMerchantRequest request = new CreateMerchantRequest();
        request.setName("ebay");
        request.setLogoUrl("ebay.com");
        request.setBusinessLicenceUrl("ebay.com");
        request.setPhone("4294967296");
        request.setAddress("San Jose");
        System.out.println(new Gson().toJson(merchantServ.createMerchant(request)));
    }

    @Test
    public void testBuildMerchantInfoById() {
        System.out.println(new Gson().toJson(merchantServ.buildMerchantInfoById(18)));
    }

    @Test
    public void testBuildMerchantInfoByIdNotExist() {
        System.out.println(new Gson().toJson(merchantServ.buildMerchantInfoById(-1)));
    }

    @Test
    public void testDistributePassTemplate() {
        PassTemplate passTemplate = new PassTemplate();
        passTemplate.setMerchantId(20);
        passTemplate.setTitle("att family plan");
        passTemplate.setSummary("phone plan for a family");
        passTemplate.setDesc("Cheap but strong");
        passTemplate.setLimit(10000L);
        passTemplate.setHasToken(true);
        passTemplate.setBackground(2);
        passTemplate.setStart(DateUtils.addDays(new Date(), -10));
        passTemplate.setEnd(DateUtils.addDays(new Date(), 10));
        System.out.println("log:" + new Gson().toJson(merchantServ.distributePassTemplate(passTemplate)));
    }
}
