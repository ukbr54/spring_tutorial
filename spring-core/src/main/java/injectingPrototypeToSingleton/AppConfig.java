package injectingPrototypeToSingleton;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by Ujjwal Gupta on Jan,2020
 */

@Configuration
public class AppConfig {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public PrototypeBean prototypeBean(){
        return new PrototypeBean();
    }

    @Bean
    public SingletonBean singletonBean(){
        return new SingletonBean();
    }

    @Bean
    public SingletonApplicationContextAware singletonApplicationContextAware(){
        return new SingletonApplicationContextAware();
    }

    @Bean
    public SingletonObjectFatoryBean singletonObjectFatoryBean(){
        return new SingletonObjectFatoryBean();
    }
}
