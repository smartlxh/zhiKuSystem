package com.example.smartlxh.model;

/**
 * Created by lixianhai on 23/03/2018.
 */
public enum ReturnCodeEnum {

    ERROR("001","操作出错"),
    SUCCESS("002","操作成功"),
    UNKNOW("003","未知的出错类型");

    private String code;
    private String msg;

    private ReturnCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    //根据枚举的code获取msg的方法
    public static String getMsgByCode(String code){
        for(ReturnCodeEnum responseEnum : ReturnCodeEnum.values()) {
            if(responseEnum.getCode().equals(code)){
                return responseEnum.msg;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return code;
    }
}


