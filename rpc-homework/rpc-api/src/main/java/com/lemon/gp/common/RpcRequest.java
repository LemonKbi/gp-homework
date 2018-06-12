package com.lemon.gp.common;

import java.io.Serializable;

/**
 * Created by gujj on 2018/6/11.
 */
public class RpcRequest implements Serializable {

    private static final long serialVersionUID = 7456508032292523744L;

    private Object[] parameters;
    private String classNmae;
    private String methodName;

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public String getClassNmae() {
        return classNmae;
    }

    public void setClassNmae(String classNmae) {
        this.classNmae = classNmae;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
