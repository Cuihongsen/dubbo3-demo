package cn.raysonblog.shopservice.api.service;


import lombok.Builder;
import lombok.Data;
import org.apache.dubbo.common.stream.StreamObserver;

import java.io.Serializable;


/**
 * Wrapper形式流式传输
 *
 * @author cui
 */
public interface RpcStreamWrapperService {

    /**
     * 流式接口
     * @param replyObserver replyObserver
     * @return StreamObserver
     */
    StreamObserver<HelloRequest> sayHello(StreamObserver<HelloReply> replyObserver);

    /**
     * 非流式接口（作对比）
     * @param helloReply helloReply
     * @return HelloReply
     */
    HelloReply sayHelloWithoutStream(HelloRequest helloReply);

    @Data
    @Builder
    public static class HelloRequest implements Serializable {
        private String name;
    }

    @Data
    @Builder
    public static class HelloReply implements Serializable {
        private String message;
    }
}