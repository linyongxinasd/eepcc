package com.lin.domain;

public enum ResponseCode {
    KAPTCHA_SUCCESS(200,"请求图片验证码成功"),

    BAD_REQUEST(400,"错误请求"),
    FAILED(199, "失败"),
    PARAM_NULL(8000, "参数有误"),
    SUCCESS(200, "成功"),
    PAY_SUCCESS(1, "PAY_SUCCESS"),
    PARAM_ERROR(1000, "PARAM_ERROR"),
    ADMISSION_INFO_LOST(1001, "ADMISSION_INFO_LOST"),
    MSG_SEND_ERROR(1002, "MSG SEND ERROR"),
    CODE_ERROR(1003, "CODE_ERROR"),
    TELEPHONE_EXIST(1004, "TELEPHONE_EXIST"),
    PASSWORD_ERROR(1005, "PASSWORD_ERROR"),
    CULTURE_COUNT_EXHAUST(1006, "CULTURE_COUNT_EXHAUST"),
    ADMISSION_COUNT_EXHAUST(1007, "ADMISSION_COUNT_EXHAUST"),
    NOT_PAYED(1008, "NOT_PAYED"),
    INTERNAL_ERROR(2000, "INTERNAL ERROR"),
    NO_AUTH(3000, "NO_AUTH"),
    NO_AUDIT(808, "NO_AUDIT"),
    //crud通用
    ADD_SUCCESS(200, "添加成功"),
    ADD_FAILED(201, "添加失败"),
    MODIFY_SUCCESS(200, "修改成功"),
    MODIFY_FAILED(202, "修改失败"),
    DELETE_SUCCESS(200, "删除成功"),
    DELETE_FAILED(203, "删除失败"),
    //小程序用户模块使用
    API_LOGIN_SUCCESS(200, "登录成功"),
    API_LOGIN_FAILURE(210, "登录失败"),
    //后台管理用户模块使用
    ADMIN_LOGOUT_SUCCESS(200,"注销成功"),
    ADMIN_LOGIN_SUCCESS(200, "登录成功"),
    ADMIN_PARAM_NO_NULL(2001, "账号或密码不能为空"),
    ADMIN_PWD_NO_NORM(2002, "账号必需长度1~128位"),
    ADMIN_ACCOUNT_NO_NORM(2003, "密码必须6~64位"),
    ADMIN_ACCOUNT_NO_REGISTER(2004, "账户未注册"),
    PHONE_ONT_NORM(2006, "手机号不规范"),
    PHONE_ONE(201, "手机号已存在"),
    EMAIL_ONE(202, "邮箱已存在"),
    EMAIL_ONT_NORM(203,"邮箱格式错误"),
    EMAIL_SEND_FAILURE(201,"邮箱发送失败"),
    PASSWORD_SIX(204, "密码不得小于6位"),
    ADMIN_PWD_ERROR(2005, "账号或密码错误"),
    UNKNOWN_ERROR(500,"服务器繁忙"),
    //注册使用
    REGISTER_SUCCESS(200,"注册成功"),
    REGISTER_FAILURE(161,"注册失败"),
    USER_EXIST(160,"用户已存在"),
    USER_NO_EXIST(201,"用户不存在"),

    USER_CODE_ERROR(302,"验证码错误"),
    USER_PASSWORD_ERROR(303,"密码错误");

    //





    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
