package cn.raysonblog.shopserviceconsumer;

import cn.raysonblog.shopservice.api.service.RpcShopService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MyController {
    @DubboReference(protocol = "tri")
    RpcShopService shopService;

    @GetMapping("/sayHello")
    public String sayHello(){
        return shopService.sayHello("Hello Dubbo Nacos!更多原创分享，技术交流，关注：Java技术干货（ID:raysonfang）");
    }
}
