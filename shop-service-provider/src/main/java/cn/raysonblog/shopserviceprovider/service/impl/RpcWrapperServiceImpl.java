package cn.raysonblog.shopserviceprovider.service.impl;

import cn.raysonblog.shopservice.api.service.RpcWrapperService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 接口实现类
 * <p>
 * ## @Service 这个注解是使用dubbo提供的，
 * 这个注解中有很多属性，需要单独了解去进行配置
 *
 * @author raysonfang
 */
@DubboService(protocol = "tri")
public class RpcWrapperServiceImpl implements RpcWrapperService {

    @Override
    public String sayHello(String name) {
        return name + " provider" + System.currentTimeMillis();
    }
}
