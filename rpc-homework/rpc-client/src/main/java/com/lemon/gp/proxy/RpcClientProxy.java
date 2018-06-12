package com.lemon.gp.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by gujj on 2018/6/12.
 */
public class RpcClientProxy {

    /**
     * 对调用的service进行代理
     * */
    public static <T> T serviceProxy(Class<T> service) {

        return (T) Proxy.newProxyInstance(service.getClassLoader(),
                new Class[]{service}, new ServiceInvocationHandler());

    }

}
