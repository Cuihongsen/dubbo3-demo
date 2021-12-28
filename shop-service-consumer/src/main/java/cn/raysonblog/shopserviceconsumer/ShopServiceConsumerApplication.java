package cn.raysonblog.shopserviceconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
