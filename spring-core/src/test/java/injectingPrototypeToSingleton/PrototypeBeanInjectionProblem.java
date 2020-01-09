package injectingPrototypeToSingleton;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.util.Assert;

/**
 * Created by Ujjwal Gupta on Jan,2020
 */

public class PrototypeBeanInjectionProblem {

    /**
     * From the test we get to know the SingletonBeanLookupMethod and PrototypeBean is created only once.
     * So The PrototypeBean is not giving new instance for every call from the container.
     */

    @Test
    public void narrowerScopeBeanInjectionTest(){

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        SingletonBean firstSingletonBean = context.getBean(SingletonBean.class);
        PrototypeBean firstPrototypeBean = firstSingletonBean.getPrototypeBean();

        firstSingletonBean.message();

        SingletonBean secondSingletonBean = context.getBean(SingletonBean.class);
        PrototypeBean secondPrototypeBean = secondSingletonBean.getPrototypeBean();

        secondSingletonBean.message();

        Assert.isTrue(firstPrototypeBean.equals(secondPrototypeBean), "The same instance should be returned");

        ((AbstractApplicationContext)context).close();
    }

    /**
     * Disadvantage:
     * Injecting ApplicationContext, couples the application code to Spring API. Secondly, it does not follow the Inversion of Control principle as we
     * are not letting Spring to inject the dependencies but we are asking the container to give us the dependencies.
     */

    @Test
    public void applicationContextAwareTest(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        SingletonApplicationContextAware firstSingleton = ctx.getBean(SingletonApplicationContextAware.class);
        PrototypeBean firstPrototype = firstSingleton.getPrototypeBean();

        firstSingleton.message();

        SingletonApplicationContextAware secondSingleton = ctx.getBean(SingletonApplicationContextAware.class);
        PrototypeBean secondPrototype = secondSingleton.getPrototypeBean();

        secondSingleton.message();

        org.junit.Assert.assertTrue("New Instance expected", firstPrototype != secondPrototype);
        ((AbstractApplicationContext)ctx).close();
    }

    /**
     * ObjectFactory is a functional interface from Spring framework designed to provide a reference to beans managed by the application context.
     * The interface defines only one method which returns an instance of the selected bean.
     */
    @Test
    public void objectFactoryBeanTest(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        SingletonObjectFatoryBean firstSingleton = ctx.getBean(SingletonObjectFatoryBean.class);
        PrototypeBean firstPrototype = firstSingleton.getPrototypeInstance();

        firstSingleton.message();

        SingletonObjectFatoryBean secondSingleton = ctx.getBean(SingletonObjectFatoryBean.class);
        PrototypeBean secondPrototype = secondSingleton.getPrototypeInstance();

        secondSingleton.message();

        org.junit.Assert.assertTrue("New Instance expected", firstPrototype != secondPrototype);
        ((AbstractApplicationContext)ctx).close();
    }
}
