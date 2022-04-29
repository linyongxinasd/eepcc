package com.lin.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lin.domain.ResponseCode;
import com.lin.domain.Response;
import com.lin.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*自定义认证成功之后处理*/
@Slf4j
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {


        Response result = new Response();
        String jwt = jwtUtils.generateToken(authentication.getName());
        response.setHeader(jwtUtils.getHeader(), jwt);
        result.setMsg("登陆成功");
        log.info("进入filter登录成功");
        result.setStatusCode(ResponseCode.ADMIN_LOGIN_SUCCESS);
        result.setData(authentication.getPrincipal());
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());
        String s = new ObjectMapper().writeValueAsString(result);
        response.getWriter().println(s);

    }
}
