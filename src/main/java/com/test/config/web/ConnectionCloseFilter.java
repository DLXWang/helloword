package com.test.config.web;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/*@Component
public class ConnectionCloseFilter implements WebFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        // Delegate the request handling to the next filter/handler
        return chain.filter(exchange).doFinally(signalType -> {
            // Close the connection after the response has been written
            HandlerMethod handlerMethod = exchange.getAttribute(HandlerMapping.BEST_MATCHING_HANDLER_ATTRIBUTE);
            assert handlerMethod != null;
            MyAnnotation myAnnotation = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), MyAnnotation.class);
            System.out.println("===");
            exchange.getResponse().setComplete().subscribe();
        });
    }

    @Override
    public int getOrder() {
        // Set the order to be the highest, ensuring it runs last
        return Ordered.HIGHEST_PRECEDENCE;
    }
}*/

