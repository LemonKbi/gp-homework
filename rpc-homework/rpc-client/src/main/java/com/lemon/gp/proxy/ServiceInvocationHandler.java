package com.lemon.gp.proxy;

import com.lemon.gp.client.RpcClient;
import com.lemon.gp.common.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by gujj on 2018/6/12.
 */
public class ServiceInvocationHandler implements InvocationHandler{
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest request=new RpcRequest();
        request.setClassNmae(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParameters(args);
        return RpcClient.send(request);
    }
}
