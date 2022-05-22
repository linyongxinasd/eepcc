package com.lin.filter;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lin.constant.Constant;
import com.lin.exception.KaptchaNotMatchException;
import com.lin.utils.RedisUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    public static final String FORM_KAPTCHA_KEY = "kaptcha";

    private String kaptchaParameter = FORM_KAPTCHA_KEY;

    public String getKaptchaParameter() {
        return kaptchaParameter;
    }

    @Autowired
    RedisUtil redisUtil;

    public void setKaptchaParameter(String kaptchaParameter) {
        this.kaptchaParameter = kaptchaParameter;
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String url = request.getRequestURI();
        //1. 判断是否 post 方式请求
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        //2. 判断是否 json 格式请求类型
        if ("/login".equals(url)) {
            //3.从 json 数据中获取用户输入用户名和密码进行认证 {"uname":"xxx","password":"xxx"}
            try {
                //获取请求数据
                //Map<String, String> userInfo = new ObjectMapper().readValue(request.getInputStream(), Map.class);
                String username = obtainUsername(request);
                username = (username != null) ? username : "";
                username = username.trim();
                String password = obtainPassword(request);
                password = (password != null) ? password : "";

                String kaptcha = request.getParameter(getKaptchaParameter());
                String token = request.getParameter("token");

                /*String username =
                String password = userInfo.get(getPasswordParameter());
                String kaptcha = userInfo.get(getKaptchaParameter());
                String token = userInfo.get("token");*/
                System.out.println("用户名: " + username + " 密码: " + password + " 验证码: " + kaptcha + " token: " + token);
                //2.获取 session 中验证码
                //String sessionVerifyCode = (String) request.getSession().getAttribute("kaptcha");
                if (!(StringUtils.isBlank(kaptcha) || StringUtils.isBlank(token)) && kaptcha.equals(redisUtil.hget(Constant.KAPTCHA_KEY, token))) {
                    //3.获取用户名 和密码认证
                    UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
                    setDetails(request, authRequest);
                    return this.getAuthenticationManager().authenticate(authRequest);
                }


            } catch (KaptchaNotMatchException e) {
                e.printStackTrace();
            }
        }
        throw new KaptchaNotMatchException("验证码不匹配");

        //return super.attemptAuthentication(request, response);
    }
}
