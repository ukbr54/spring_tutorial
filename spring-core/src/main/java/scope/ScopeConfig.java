package scope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.SessionScope;

/**
 * Created by Ujjwal Gupta on Jan,2020
 */

@Configuration
public class ScopeConfig {

    @Bean(name = "personSingleton")
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Person personSingleton(){
        return new Person();
    }

    @Bean(name = "personPrototype")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Person personPrototype(){
        return new Person();
    }

    @Bean
    @SessionScope
    public HelloMessageGenerator requestScopedBean(){
        return new HelloMessageGenerator();
    }

    @Bean
    @ApplicationScope
    public HelloMessageGenerator applicationScopedBean(){
        return new HelloMessageGenerator();
    }

    @Bean
    @Scope(scopeName = "websocket", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public HelloMessageGenerator websocketScopedBean() {
        return new HelloMessageGenerator();
    }
}
