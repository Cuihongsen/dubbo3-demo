package cn.raysonblog.shopserviceconsumer;

import cn.raysonblog.shopservice.api.service.RpcShopService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 把主类和controller写在一起，方便简单测试演示。
 *
 * @author raysonfang
 */
@SpringBootApplication

public class ShopServiceConsumerApplication {


    public static void main(String[] args) {
        SpringApplication.run(ShopServiceConsumerApplication.class, args);
    }

}
