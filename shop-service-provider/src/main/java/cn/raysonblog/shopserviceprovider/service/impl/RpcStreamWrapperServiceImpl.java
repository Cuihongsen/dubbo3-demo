package cn.raysonblog.shopserviceprovider.service.impl;

import cn.raysonblog.shopservice.api.service.RpcStreamWrapperService;
import org.apache.dubbo.common.stream.StreamObserver;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.ArrayList;
import java.util.List;

@DubboService(protocol = {"tri"})
public class RpcStreamWrapperServiceImpl implements RpcStreamWrapperService {
    @Override
    public StreamObserver<HelloRequest> sayHello(StreamObserver<HelloReply> replyObserver) {
        return new StreamObserver<HelloRequest>() {
            private final List<HelloReply> replyList = new ArrayList<>();

            @Override
            public void onNext(HelloRequest helloRequest) {
                System.out.println("onNext receive request name:" + helloRequest.getName());
                final HelloReply build = HelloReply.builder()
                        .message("receive name:" + helloRequest.getName())
                        .build();
                replyList.add(build);
                replyObserver.onNext(build);
            }

            @Override
            public void onError(Throwable cause) {
                System.out.println("onError");
                replyObserver.onError(cause);
            }

            @Override
            public void onCompleted() {
                System.out.println("onComplete receive request size:" + replyList.size());
                for (HelloReply reply : replyList) {
//                    replyObserver.onNext(reply);
                }
                replyObserver.onCompleted();
            }
        };
    }

    @Override
    public HelloReply sayHelloWithoutStream(HelloRequest helloRequest) {
        System.out.println("receive request name:" + helloRequest.getName());
        final HelloReply helloReply = HelloReply.builder()
                .message("receive name:" + helloRequest.getName())
                .build();
        return helloReply;
    }
}
