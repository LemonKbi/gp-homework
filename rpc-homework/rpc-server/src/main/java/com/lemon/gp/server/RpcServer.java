package com.lemon.gp.server;

import com.lemon.gp.common.Constant;
import com.lemon.gp.common.ServiceDefintion;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by gujj on 2018/6/11.
 */
public class RpcServer {
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(10);
    ;
    private static final Map<String, Object> servicesMap = new ServiceDefintion().getServicesMap();


    public void start() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(Constant.DEFAULT_PORT));
            System.out.println("================服务启动==============");
            while (true) {
                threadPool.execute(new ServiceHandler(serverSocket.accept(), servicesMap));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
