package com.lemon.gp;

import com.lemon.gp.server.RpcServer;

/**
 * Created by gujj on 2018/6/12.
 */
public class ServiceDemo {
    public static void main(String[] args) {
        RpcServer server= new RpcServer();
        server.start();
    }
}
