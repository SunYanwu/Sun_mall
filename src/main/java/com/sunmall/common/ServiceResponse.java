package com.sunmall.common;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.JsonSerializable;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.schema.JsonSerializableSchema;

import java.io.Serializable;

/**
 * @className ServiceResponse
 * @Author SunYanwu
 * @Description：高复用的服务端响应对象
 * @Date 2018/7/24 0:50
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)  //配置使Json不返回NULL，只需要返回status时，不会返回msg和data的NULL对象
public class ServiceResponse<T> implements Serializable {
    private int status;    //返回的状态代码，
    private String msg;    //返回的信息
    private T data;        //服务端返回的对象（泛型）

    //将返回对象的几种情况进行私有的构造
    private ServiceResponse(int status){
        this.status=status;
    }

    private ServiceResponse(int status,T data){
        this.status=status;
        this.data=data;
    }

    private ServiceResponse(int status,String msg){
        this.status=status;
        this.msg=msg;
    }

    private ServiceResponse(int status,String msg,T data){
        this.msg=msg;
        this.status=status;
        this.data=data;
    }
    //判断是否成功
    //将isSuccess字段进行JsonIgnore注解，使其不会被返回到前端
    @JsonIgnore
    public boolean isSuccess(){
        return this.status==ResponseCode.SUCCESS.getCode();
    }
    //各个参数的get方法
    public String getMsg(){
        return msg;
    }
    public int getStatus(){
        return status;
    }
    public T getData(){
        return data;
    }
    //对外提供返回服务端响应对象的方法
    //返回值为成功时
    public static <T> ServiceResponse<T> createBySucess(String msg){
        return new ServiceResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }
    public static <T> ServiceResponse<T> createBySucess(T data){
        return new ServiceResponse<>(ResponseCode.SUCCESS.getCode(),data);
    }
    public static <T> ServiceResponse<T> createBySucess(String msg,T data){
        return new ServiceResponse<>(ResponseCode.SUCCESS.getCode(),msg,data);
    }
    public static <T> ServiceResponse<T> createBySucess(){
        return new ServiceResponse<>(ResponseCode.SUCCESS.getCode());
    }

    //当返回状态代码为error时
    public static <T> ServiceResponse<T> createByError(){
        return new ServiceResponse<T>(ResponseCode.ERRO.getCode(),ResponseCode.ERRO.getDesc());
    }

    public static <T> ServiceResponse<T> createByErrorMessage(String erroMsg){
        return new ServiceResponse<T>(ResponseCode.ERRO.getCode(),erroMsg);
    }
    //扩展新的状态码和错误信息
    public static <T> ServiceResponse<T> createByErroCodeMessage(int errorCode,String erroMsg){
        return new ServiceResponse<>(errorCode,erroMsg);
    }
}
