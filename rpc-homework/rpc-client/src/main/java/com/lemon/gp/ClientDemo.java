package com.lemon.gp;

import com.lemon.gp.proxy.RpcClientProxy;
import com.lemon.gp.service.IHelloService;

/**
 * Created by gujj on 2018/6/12.
 */
public class ClientDemo {
        public static void main(String[] args) {
            IHelloService hello=  RpcClientProxy.serviceProxy(IHelloService.class);
            System.out.println(hello.sayBye("lemon"));

    }
}
