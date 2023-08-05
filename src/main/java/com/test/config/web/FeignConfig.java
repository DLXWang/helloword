package com.test.config.web;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.reactive.function.client.WebClient;
import reactivefeign.ReactiveOptions;
import reactivefeign.retry.BasicReactiveRetryPolicy;
import reactivefeign.spring.config.EnableReactiveFeignClients;
import reactivefeign.spring.config.ReactiveRetryPolicies;
import reactivefeign.webclient.WebReactiveOptions;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Configuration
@EnableReactiveFeignClients(basePackages = "com.test.api")

//@EnableFeignClients(basePackages = {"com.xt.jwt.bridge", "com.test.api"},basePackageClasses = {com.xt.jwt.bridge.UserCenterApi.class})
public class FeignConfig {

    @Value("${timeout:4500}")
    private long timeout;

    @Bean
    @ConditionalOnMissingBean
    public HttpMessageConverters messageConverters(ObjectProvider<HttpMessageConverter<?>> converters) {
        return new HttpMessageConverters(converters.orderedStream().collect(Collectors.toList()));
    }

    @Bean
    public ReactiveOptions reactiveOptions() {
        return new WebReactiveOptions.Builder()
                .setWriteTimeoutMillis(timeout)
                .setReadTimeoutMillis(timeout)
                .setConnectTimeoutMillis(timeout)
                .build();
    }

    @Bean
    public ReactiveRetryPolicies retryOnNext() {
        //不进行重试，retryOnSame是控制对同一个实例的重试策略，retryOnNext是控制对不同实例的重试策略。
        return new ReactiveRetryPolicies.Builder()
                .retryOnSame(BasicReactiveRetryPolicy.retryWithBackoff(0, 10))
                .retryOnNext(BasicReactiveRetryPolicy.retryWithBackoff(0, 10))
                .build();
    }

    @Bean
    @Primary
    public WebClient webClient() {
        //HttpClient httpClient = HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
        ConnectionProvider provider = ConnectionProvider.builder("custom")
                .maxConnections(1)
                .pendingAcquireMaxCount(1)
                .maxIdleTime(Duration.ofSeconds(60))
                .maxLifeTime(Duration.ofSeconds(60))
                .lifo()
                .pendingAcquireTimeout(Duration.ofSeconds(60))
                .evictInBackground(Duration.ofSeconds(120)).build();

        HttpClient httpClient = HttpClient.create(provider)
                //.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 60000)
                //.option(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
                //.option(ChannelOption.SO_TIMEOUT, 60000)
                //.option(ChannelOption.TCP_NODELAY,Boolean.TRUE)
                .resolver(DefaultAddressResolverGroup.INSTANCE)
                .responseTimeout(Duration.ofSeconds(60))
                .doOnConnected(conn -> conn
                        .addHandlerLast(new ReadTimeoutHandler(60, TimeUnit.SECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(60, TimeUnit.SECONDS)));

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

}
