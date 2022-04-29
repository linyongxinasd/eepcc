package com.lin.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.map.MapUtil;
import com.google.code.kaptcha.Producer;
import com.lin.constant.Constant;
import com.lin.domain.Response;
import com.lin.domain.ResponseCode;
import com.lin.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@Slf4j
public class KaptchaController {


    private final Producer producer;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    public KaptchaController(Producer producer) {
        this.producer = producer;
    }

    @GetMapping("/kaptcha1")
    public String getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.生成验证码
        String code = producer.createText();
        log.info("code1"+code);
        //session.setAttribute("kaptcha", code);//可以更换成 redis 实现
        BufferedImage bi = producer.createImage(code);
        //2.写入内存
        FastByteArrayOutputStream fos = new FastByteArrayOutputStream();
        ImageIO.write(bi, "jpg", fos);
        //3.生成 base64
        return Base64.encodeBase64String(fos.toByteArray());
    }

    @GetMapping("/kaptcha")
    public Response getVerifyCode() throws IOException {
        //1.生成验证码
        String code = producer.createText();
        String key = UUID.randomUUID().toString();
        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        String base64 = Base64.encodeBase64String(outputStream.toByteArray());
        //BASE64Encoder encoder = new BASE64Encoder();
        String str = "data:image/jpeg;base64,";
        String base64Img = str + base64;

        // 存储到redis中
        redisUtil.hset(Constant.KAPTCHA_KEY, key, code, 120);
        log.info("token --> "+ key);
        log.info("code -- > "+code);
        //log.info("验证码 -- {} - {}", key, code);
        Response result = new Response();
        result.setStatusCode(ResponseCode.KAPTCHA_SUCCESS);

        result.setData(MapUtil.builder()
                .put("token", key)
                .put("base64Img", base64Img)
                .build());

        return result;
    }
}