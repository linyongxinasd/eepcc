package com.lin.exception;

import com.lin.domain.Response;
import com.lin.domain.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = AccessDeniedException.class)
    public Response handler(AccessDeniedException e) {
        log.info("security权限不足：----------------{}", e.getMessage());
        Response response = new Response();
        response.setStatusCode(ResponseCode.BAD_REQUEST);
        response.setMsg("权限不足");
        return response;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = BadCredentialsException.class)
    public Response handler(BadCredentialsException e) {
        log.info("密码错误：----------------{}", e.getMessage());
        Response response = new Response();
        response.setStatusCode(ResponseCode.USER_PASSWORD_ERROR);
        response.setMsg("密码错误");
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Response handler(MethodArgumentNotValidException e) {
        log.info("实体校验异常：----------------{}", e.getMessage());
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        Response response = new Response();
        response.setStatusCode(ResponseCode.BAD_REQUEST);
        response.setMsg(objectError.getDefaultMessage());
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Response handler(IllegalArgumentException e) {
        log.error("Assert异常：----------------{}", e.getMessage());
        Response response = new Response();
        response.setStatusCode(ResponseCode.BAD_REQUEST);
        response.setMsg(e.getMessage());
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public Response handler(RuntimeException e) {
        log.error("运行时异常:----------------{}", e);
        Response response = new Response();
        response.setStatusCode(ResponseCode.BAD_REQUEST);
        response.setMsg(e.getMessage());
        return response;
    }
}
