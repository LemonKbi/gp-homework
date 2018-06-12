package com.lemon.gp.common;

import java.io.Serializable;

/**
 * Created by gujj on 2018/6/12.
 */
public class RpcResponse implements Serializable {
    private static final long serialVersionUID = 7548048716456951090L;

    private String errorcode = "1111";//默认连接失败，code

    private String msg;

    private String successflag = "0";//默认连接失败

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSuccessflag() {
        return successflag;
    }

    public void setSuccessflag(String successflag) {
        this.successflag = successflag;
    }
}
