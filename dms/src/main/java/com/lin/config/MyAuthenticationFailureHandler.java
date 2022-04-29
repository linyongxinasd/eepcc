package com.lin.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lin.domain.Response;
import com.lin.domain.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Response result = new Response();
        log.info("进入filter登录失败");
        result.setStatusCode(ResponseCode.API_LOGIN_FAILURE);
        //result.setMsg("登录失败: " + ex.getMessage());
        result.setMsg("登录失败: 用户名或密码错误");
        response.setContentType("application/json;charset=UTF-8");
        String s = new ObjectMapper().writeValueAsString(result);
        response.getWriter().println(s);
    }
}
