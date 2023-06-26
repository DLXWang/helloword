package com.test.config;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
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

import java.util.stream.Collectors;


@Configuration
@EnableReactiveFeignClients(basePackages = "com.test.api")
public class FeignConfig {

    @Value("${ryzen.biz.feignclient.timeout:4500}")
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
        HttpClient httpClient = HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

}
