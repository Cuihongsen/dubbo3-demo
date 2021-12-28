package cn.raysonblog.shopserviceconsumer;

import cn.raysonblog.shopservice.api.service.RpcStreamWrapperService;
import cn.raysonblog.shopservice.api.service.RpcWrapperService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.stream.StreamObserver;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.demo.DemoService;
import org.apache.dubbo.demo.HelloReply;
import org.apache.dubbo.demo.HelloRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Slf4j
public class MyController {
    @DubboReference(protocol = "tri")
    RpcWrapperService rpcWrapperService;

    @DubboReference(protocol = "tri")
    DemoService demoService;

    @DubboReference(protocol = "tri")
    RpcStreamWrapperService rpcStreamWrapperService;

    @GetMapping("/wrapper")
    public String wrapper() {
        return rpcWrapperService.sayHello("Hello Dubbo ");
    }

    @GetMapping("/proto")
    public String proto() {
        final HelloRequest name = HelloRequest.newBuilder().setName("name").build();
        final HelloReply helloReply = demoService.sayHello(name);
        final String message = helloReply.getMessage();
        return message;
    }


    @GetMapping("/stream-wrapper")
    public String streamWrapper() {
        final StreamObserver<RpcStreamWrapperService.HelloRequest> streamObserver = rpcStreamWrapperService.sayHello(new StreamObserver<RpcStreamWrapperService.HelloReply>() {
            @Override
            public void onNext(RpcStreamWrapperService.HelloReply reply) {
                log.info("onNext {} ", reply.getMessage());
            }

            @Override
            public void onError(Throwable throwable) {
                log.info("onError:" + throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                log.info("onCompleted");
            }
        });

        for (int i = 0; i < 100; i++) {
            final RpcStreamWrapperService.HelloRequest request = RpcStreamWrapperService.HelloRequest.builder()
                    .name("tony-" + i)
                    .build();
            log.info("send {}", request);
            streamObserver.onNext(request);
        }
        streamObserver.onCompleted();
        return "stream ok";
    }

    @GetMapping("/stream-proto")
    public String streamProto() {
        final StreamObserver<HelloRequest> streamObserver = demoService.sayHelloStream(new StreamObserver<HelloReply>() {

            @Override
            public void onNext(HelloReply data) {
                log.info("onNext {} ", data.getMessage());
            }

            @Override
            public void onError(Throwable throwable) {
                log.info("onError:" + throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                log.info("onCompleted");
            }
        });

        for (int i = 0; i < 100; i++) {
            final HelloRequest request = HelloRequest.newBuilder()
                    .setName("tony-" + i)
                    .build();
            log.info("send {}", request);
            streamObserver.onNext(request);
        }
        streamObserver.onCompleted();
        return "stream ok";
    }

    @GetMapping("/nostream")
    public String nostream() {
        for (int i = 0; i < 100; i++) {
            final RpcStreamWrapperService.HelloRequest request = RpcStreamWrapperService.HelloRequest.builder()
                    .name("tony-" + i)
                    .build();
            log.info("send {}", request);
            final RpcStreamWrapperService.HelloReply helloReply = rpcStreamWrapperService.sayHelloWithoutStream(request);
        }
        return "nostream ok";
    }
}
