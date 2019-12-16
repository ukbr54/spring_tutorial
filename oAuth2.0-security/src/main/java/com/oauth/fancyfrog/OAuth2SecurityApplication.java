package com.oauth.fancyfrog;

import com.oauth.fancyfrog.config.props.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Created by Ujjwal Gupta on Dec,2019
 */

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class OAuth2SecurityApplication {

    public static void main(String[] args){
        SpringApplication.run(OAuth2SecurityApplication.class,args);
    }
}
