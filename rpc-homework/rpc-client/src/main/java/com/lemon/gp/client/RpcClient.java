package com.lemon.gp.client;

import com.lemon.gp.common.Constant;
import com.lemon.gp.common.RpcRequest;
import com.lemon.gp.common.RpcResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by gujj on 2018/6/12.
 */
public class RpcClient {


    //创建一个socket连接
    private static Socket newSocket() {
        System.out.println("创建一个新的连接");
        Socket socket;
        try {
            socket = new Socket(Constant.DEFAULT_HOST, Constant.DEFAULT_PORT);
            return socket;
        } catch (Exception e) {
            throw new RuntimeException("连接建立失败");
        }
    }

    public static Object send(RpcRequest request) {
        Object result = new Object();
        Socket socket = null;
        socket = newSocket();
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            //向服务端发送请求
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(request);
            oos.flush();

            //读取服务端返回信息
            ois = new ObjectInputStream(socket.getInputStream());
            result = ois.readObject();
            oos.close();
            ois.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }


}
