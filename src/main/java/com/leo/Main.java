package com.leo;

import lombok.extern.java.Log;
import com.leo.http.framework.Server;
import com.leo.http.framework.resolver.argument.RequestHandlerMethodArgumentResolver;
import com.leo.http.framework.resolver.argument.RequestHeaderHandlerMethodArgumentResolver;
import com.leo.http.framework.resolver.argument.ResponseHandlerMethodArgumentResolver;

@Log
public class Main {
    public static void main(String[] args) {
        final var server = new Server();
        server.autoRegisterHandlers("com.leo.http");
        server.addArgumentResolver(
                new RequestHandlerMethodArgumentResolver(),
                new ResponseHandlerMethodArgumentResolver(),
                new RequestHeaderHandlerMethodArgumentResolver()
        );
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                server.stop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        server.listen(9999);
    }
}
