package com.nepcc.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: cuzz
 * @Date: 2018/9/21 14:29
 * @Description:
 */
@Configuration
// WebMvcConfigurerAdapter过时,使用WebMvcConfigurer接口
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/index.html").setViewName("index.html");
        registry.addViewController("/registry.html").setViewName("registry.html");
        registry.addViewController("/login.html").setViewName("login.html");
        //templates中放的是视图views，无法通过url直接访问，所以需要通过视图控制器转发
    }

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //super.addInterceptors(registry);

        //拦截页面
        /*registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/index.html","/login.html","/registry.html","registrationSuccess.html",
                        "/userLogin", "/mp/userLogin", "/newUserRegistration", "/getThisUser", "/pages/**",
                        "/img/**", "/js/**", "/css/**", "/fonts/**", "/images/**");*/

    }






/*    // 所有的WebMvcConfigurerAdapter组件都会一起起作用
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }
        };
        return webMvcConfigurer;
    }
*/
}
