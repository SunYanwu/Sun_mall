package com.sunmall.common;

/**
 * @className ResponseCode
 * @Author SunYanwu
 * @Description：响应编码的枚举类
 * @Date 2018/7/24 1:47
 */
public enum  ResponseCode {
    SUCCESS(0,"Sucess"),
    ERRO(1,"erro"),
    NEED_LOGIN(10,"need login"),
    ILLEGAL_ARGUMENT(12,"illegal argument");

    private int code;
    private String desc;

    ResponseCode(int code,String desc){
        this.code=code;
        this.desc=desc;
    }
    public int getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }
}
