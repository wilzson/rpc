package com.example.rpc.springboot.starter.annotation;

import com.example.rpc.springboot.starter.bootstrap.RpcConsumerBootstrap;
import com.example.rpc.springboot.starter.bootstrap.RpcInitBootstrap;
import com.example.rpc.springboot.starter.bootstrap.RpcProviderBootstrap;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启用 Rpc 注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({RpcInitBootstrap.class, RpcProviderBootstrap.class, RpcConsumerBootstrap.class})
public @interface EnableRpc {
    /**
     * @Target注解决定enablerpc注解可以加载哪些成分上，如加载类身上，或者属性身上，或者方法身上
     * @Retention注解表明该注解的生命周期，
     */
    /**
     * 需要启动 server
     *
     * @return
     */
    boolean needServer() default true;
}
