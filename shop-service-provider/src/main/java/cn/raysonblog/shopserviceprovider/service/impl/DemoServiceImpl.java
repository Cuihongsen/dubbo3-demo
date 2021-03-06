package cn.raysonblog.shopserviceprovider.service.impl;

import org.apache.dubbo.common.stream.StreamObserver;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.demo.HelloReply;
import org.apache.dubbo.demo.HelloRequest;

import java.util.ArrayList;
import java.util.List;

@DubboService(protocol = {"tri"})
public class DemoServiceImpl implements org.apache.dubbo.demo.DemoService {
    @Override
    public HelloReply sayHello(HelloRequest request) {
        final String name = request.getName();
        return HelloReply.newBuilder().setMessage("hello " + name).build();
    }

    @Override
    public StreamObserver<HelloRequest> sayHelloStream(StreamObserver<HelloReply> responseObserver) {
        return new StreamObserver<HelloRequest>() {
            private final List<HelloReply> replyList = new ArrayList<>();

            @Override
            public void onNext(HelloRequest helloRequest) {
                System.out.println("onNext receive request name:" + helloRequest.getName());
                final HelloReply build = HelloReply.newBuilder()
                        .setMessage("receive name:" + helloRequest.getName())
                        .build();
                replyList.add(build);
                responseObserver.onNext(build);
            }

            @Override
            public void onError(Throwable cause) {
                System.out.println("onError");
                responseObserver.onError(cause);
            }

            @Override
            public void onCompleted() {
                System.out.println("onComplete receive request size:" + replyList.size());
                for (HelloReply reply : replyList) {
//                    replyObserver.onNext(reply);
                }
                responseObserver.onCompleted();
            }
        };
    }
}
