package com.river.model.dto;

import com.river.common.ResEnum;

import java.util.HashMap;

/**
 * create by river  2017/9/30
 * desc: 前端返回model
 */
public class WebDTO<T> extends HashMap<String,Object> {

    /** 返回码 */
    private String resCode;
    /** 描述 */
    private String resMsg;
    /** 总记录数 */
    private Long total;
    /** 总页数 */
    private Integer totalPage;
    /** 具体数据 */
    private T data;

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
        put("resCode",resCode);
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
        put("resMsg",resMsg);
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
        put("total",total);
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
        put("totalPage",totalPage);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
        put("data",data);
    }

    public void setResEnum(ResEnum resEnum) {
        setResCode(resEnum.getResCode());
        setResMsg(resEnum.getResMsg());
    }
}
