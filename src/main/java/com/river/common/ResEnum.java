package com.river.common;

public enum ResEnum {
    SUCCESS("000000","成功"),
    SYS_ERROR("000001","系统异常");
    ResEnum(String resCode, String resMsg) {
        this.resCode = resCode;
        this.resMsg = resMsg;
    }

    private String resCode;
    private String resMsg;

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }
}
