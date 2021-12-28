package cn.raysonblog.shopservice.api.service;

/**
 * Wrapper形式传输
 *
 * @author cui
 */
public interface RpcWrapperService {
    /**
     * 接口
     *
     * @param name name
     * @return str
     */
    String sayHello(String name);
}
