package com.syh.passbook.merchants.conf;

import com.syh.passbook.merchants.security.AuthCheckInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Slf4j
@Component
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("Add interceptors");
        registry.addInterceptor(new AuthCheckInterceptor())
                .addPathPatterns("/merchant/**")
                .order(0);
    }
}
