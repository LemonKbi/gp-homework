package com.lemon.gp.server;

import com.lemon.gp.common.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

/**
 * Created by gujj on 2018/6/11.
 */
public class ServiceHandler implements Runnable {
    private Socket accept;
    private Map<String, Object> servicesMap;

    public ServiceHandler(Socket accept, Map<String, Object> servicesMap) {
        this.accept = accept;
        this.servicesMap = servicesMap;
    }

    @Override
    public void run() {
        ObjectInputStream ois = null;
        try {
            //获取客户端输入流
            ois = new ObjectInputStream(accept.getInputStream());
            RpcRequest request = (RpcRequest) ois.readObject();
            Object result = invoke(request);//通过反射调用注册过的方法
            //输出返回给客户端
            ObjectOutputStream oos = new ObjectOutputStream(accept.getOutputStream());
            oos.writeObject(result);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    private Object invoke(RpcRequest request) throws Exception {
        Object service = servicesMap.get(request.getClassNmae());
        if (service == null) {
            return "该服务未注册，无法调用！";
        }
        Object[] args = request.getParameters();
        Class<?>[] types = new Class[0];
        if (args != null) {
            types = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                types[i] = args[i].getClass();
            }
        }
        Method method = service.getClass().getMethod(request.getMethodName(), types);
        System.out.println("客户端调用："+request.getClassNmae()+"服务，"+method.getName()+"方法，入参为"+args);
        return method.invoke(service, args);
    }
}
