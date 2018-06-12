package com.lemon.gp;

import com.lemon.gp.common.RpcRequest;
import com.lemon.gp.server.RpcServer;

/**
 * Created by gujj on 2018/6/11.
 */
public class ServiceDemo {
    public static void main(String[] args) {
        RpcServer rpcServer=new RpcServer();
        rpcServer.start();
    }
}
