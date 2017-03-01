package com.alibaba.dubbo.demo.provider;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.dubbo.demo.DemoService2;
import com.alibaba.dubbo.rpc.RpcContext;


public class DemoServiceImpl2 implements DemoService2 {

    public String sayHello2(String name){
        /*  try {
                Thread.currentThread().sleep(1000*30);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }*/
            System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] Hello " + name + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
            return "Hello " + name + ", response form provider: " + RpcContext.getContext().getLocalAddress();
    }

    
}
