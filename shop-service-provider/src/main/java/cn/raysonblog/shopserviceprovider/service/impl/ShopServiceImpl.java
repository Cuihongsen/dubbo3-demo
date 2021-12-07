package cn.raysonblog.shopserviceprovider.service.impl;

import cn.raysonblog.shopservice.api.service.RpcShopService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 接口实现类
 * <p>
 * ## @Service 这个注解是使用dubbo提供的，
 * 这个注解中有很多属性，需要单独了解去进行配置
 *
 * @author raysonfang
 */
//@DubboService()
public class ShopServiceImpl implements RpcShopService {

    @Override
//    @POST
//    @Path("/register")
//    @Consumes({MediaType.APPLICATION_JSON,MediaType.TEXT_HTML})
//    @Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_HTML})
    public String sayHello(String name) {
        return name + " provider" + System.currentTimeMillis();
    }
}
