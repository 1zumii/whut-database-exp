package database.exp.aa.util;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

//保证序列化json的时候,如果是null的对象,key也会消失
@JsonSerialize(include =  JsonSerialize.Inclusion.NON_NULL)
public class AaResponse<T> {
    private int code;       //状态
    private String msg;     //信息
    private T data;         //数据

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    private AaResponse(int code) {
        this(code,null,null);
    }

    private AaResponse(int code,T data){
        this(code,null,data);
    }

    private AaResponse(int code,String msg){
        this(code,msg,null);
    }

    private AaResponse(int code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    //根据静态工厂创建对象
    public static <T> AaResponse<T> createBySuccess(){
        return new AaResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> AaResponse<T> createBySuccessMessage(String msg){
        return new AaResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

    public static <T> AaResponse<T> createBySuccess(T data){
        return new AaResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }

    public static <T> AaResponse<T> createBySuccess(String msg,T data){
        return new AaResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }

    public static <T> AaResponse<T> createByError(){
        return new AaResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }

    public static <T> AaResponse<T> createByErrorMessage(String errorMessage){
        return new AaResponse<T>(ResponseCode.ERROR.getCode(),errorMessage);
    }
}
