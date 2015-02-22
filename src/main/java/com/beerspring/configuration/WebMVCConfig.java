/**

package com.beerspring.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by markryan on 2/16/15.
 */

/**
@Configuration
@EnableWebMvc
public class WebMVCConfig extends WebMvcConfigurerAdapter {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/css/**")) {
            registry.addResourceHandler("/css/**").addResourceLocations(
                    "classpath:/META-INF/resources/static/css/");
        }
        if (!registry.hasMappingForPattern("/js/**")) {
            registry.addResourceHandler("/js/**").addResourceLocations(
                    "classpath:/META-INF/resources/static/js/");
        }

        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/resources/");
    }



}
**/
