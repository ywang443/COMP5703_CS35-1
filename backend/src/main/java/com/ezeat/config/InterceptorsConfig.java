package com.ezeat.config;

import com.ezeat.Interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

/**
 * 配置拦截器
 */
@Configuration
public class InterceptorsConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 验证token
        registry.addInterceptor(new TokenInterceptor())
                // 包含的路由
                .addPathPatterns("/**")
                // 排除的路由
                .excludePathPatterns("/user/login", "/user/register/user", "/register/**");
    }
}
