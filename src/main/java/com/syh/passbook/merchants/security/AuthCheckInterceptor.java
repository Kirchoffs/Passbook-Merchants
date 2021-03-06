package com.syh.passbook.merchants.security;

import com.syh.passbook.merchants.constant.Constants;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String token = request.getHeader(Constants.TOKEN_STRING);
        if (StringUtils.isEmpty(token)) {
            throw new Exception("Lack of " + Constants.TOKEN_STRING + " in header");
        }
        if (!token.equals(Constants.TOKEN)) {
            throw new Exception("Error in " + Constants.TOKEN_STRING + " in header");
        }

        AccessContext.setToken(token);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    // Even if throw an exception, it will execute.
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        AccessContext.clearAccessKey();
    }
}
