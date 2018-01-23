package com.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * shiro框架配置类 - 指定了shiro配置文件的位置
 * @author wxdl
 *
 */
@Configuration
@ImportResource({"classpath:spring-shiro-web.xml"})
public class ShiroConfiguration {
 
}
