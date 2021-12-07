package cn.raysonblog.shopserviceprovider.service.impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.demo.HelloReply;
import org.apache.dubbo.demo.HelloRequest;

@DubboService(protocol = {"tri"})
public class DemoServiceImpl implements org.apache.dubbo.demo.DemoService {
    @Override
    public HelloReply sayHello(HelloRequest request) {
        final String name = request.getName();
        return HelloReply.newBuilder().setMessage("cui " + name).build();
    }
}
