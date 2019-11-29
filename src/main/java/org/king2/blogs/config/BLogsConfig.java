package org.king2.blogs.config;


import org.king2.blogs.interceptor.BLogsInterceptor;
import org.king2.webkcache.cache.annotation.EnableWebKingCache;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
// 开启WebKingCache对我们项目的支持
@EnableWebKingCache
// 开启Mapper扫描
@MapperScan("org.king2.blogs.mapper")
// 导入配置文件
@PropertySource("classpath:server.properties")
public class BLogsConfig implements WebMvcConfigurer {

    @Bean
    public BLogsInterceptor bLogsInterceptor() {
        return new BLogsInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(bLogsInterceptor()).addPathPatterns("/logs/**", "/");
    }
}
