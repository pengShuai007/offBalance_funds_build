package com.bootdo.common.utils;

import java.io.Serializable;

/**
 * Created by zhaos on 2019/8/17.
 */
public class ReturnUtils implements Serializable {
    private static final long serialVersionUID = 1L;

    int code = 0;
    String msg = "";

    Object data = null;

  /*  public ReturnUtils(List<?> list, int total,String resultCode,boolean seccess) {
        data = new PageUtils(list,total);
        this.resultCode = resultCode;
        this.success = seccess;
    }*/

    public void seccessReturn(Object data,String msg) {
        this.code = 0;
        this.data = data;
        this.msg = msg;
    }

    public void failReturn(String msg) {
        this.code = 1;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
