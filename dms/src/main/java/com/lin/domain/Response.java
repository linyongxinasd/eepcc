package com.lin.domain;


import com.lin.domain.ResponseCode;
import org.apache.commons.lang3.StringUtils;

public class Response{
    /**
     * 200是正确的 其他都是错误的
     */
    public int statusCode = 200;

    /**
     * 状态描述
     */
    public String msg = "成功";

    /**
     * 用来返回各种数据
     */
    public Object data;

    public void setStatusCode(ResponseCode status) {
        this.statusCode = status.getCode();
        this.msg = status.getMsg();
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setFail() {
        setStatusCode(ResponseCode.FAILED);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data=data;
    }

    public void setResponseByErrorMsg(String errorMsg){
        if(StringUtils.isNotBlank(errorMsg)){
            setStatusCode(ResponseCode.FAILED);
            setMsg(errorMsg);
        }
    }

    public void setResponseBySuccessMsg(String successMsg){
        if(StringUtils.isNotBlank(successMsg)){
            setStatusCode(ResponseCode.SUCCESS);
            setMsg(successMsg);
        }
    }

    @Override
    public String toString() {
        return "Response{" +
                "statusCode=" + statusCode +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
