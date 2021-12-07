package cn.raysonblog.shopserviceconsumer;

import cn.raysonblog.shopservice.api.service.RpcShopService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.demo.DemoService;
import org.apache.dubbo.demo.HelloReply;
import org.apache.dubbo.demo.HelloRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MyController {
    @DubboReference(protocol = "tri")
    RpcShopService shopService;

    @DubboReference(protocol = "tri")
    DemoService demoService;

    @GetMapping("/sayHello")
    public String sayHello() {
        return shopService.sayHello("Hello Dubbo Nacos!更多原创分享，技术交流，关注：Java技术干货（ID:raysonfang）");
    }

    @GetMapping("/proto")
    public String proto() {
        final HelloRequest name = HelloRequest.newBuilder().setName("name").build();
        final HelloReply helloReply = demoService.sayHello(name);
        final String message = helloReply.getMessage();
        return message;
    }
}
