package com.lemon.gp.service.impl;

import com.lemon.gp.anntation.Service;
import com.lemon.gp.service.IHelloService;

import java.io.Serializable;

/**
 * Created by gujj on 2018/6/11.
 */
@Service
public class HelloServiceImpl implements IHelloService ,Serializable{
    @Override
    public String sayHello(String name) {
        return "你好：" + name;
    }

    @Override
    public String sayBye(String name) {
        return "再见：" + name;
    }
}
