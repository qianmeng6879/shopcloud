package top.mxzero.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.mxzero.common.interceptor.JWTAuthenticationInterceptor;
import top.mxzero.orderservice.interceptor.MDCInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(mdcInterceptor()).addPathPatterns("/**").order(-100);
        registry.addInterceptor(jwtAuthenticationInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public MDCInterceptor mdcInterceptor(){
        return new MDCInterceptor();
    }

    @Bean
    public JWTAuthenticationInterceptor jwtAuthenticationInterceptor(){
        return new JWTAuthenticationInterceptor();
    }
}


