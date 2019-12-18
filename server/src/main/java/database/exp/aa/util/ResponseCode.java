package database.exp.aa.util;

public enum  ResponseCode {
    SUCCESS(0,"SUCCESS"),
    ERROR(1,"请求错误");

    //成员变量（常量）
    private final int code;
    private final String desc;

    //构造方法
    ResponseCode(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }

    public String getDesc(){
        return desc;
    }
}
