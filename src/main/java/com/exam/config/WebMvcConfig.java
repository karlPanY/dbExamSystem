package com.exam.config;

import com.exam.web.RequestUrls;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by NeilHY on 2016/11/14.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController(RequestUrls.LoginUrl).setViewName("login");
        registry.addViewController(RequestUrls.exam).setViewName("exam");

    }
}
