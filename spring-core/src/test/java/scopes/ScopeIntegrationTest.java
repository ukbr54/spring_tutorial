package scopes;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import scope.Person;
import scope.ScopeConfig;

/**
 * Created by Ujjwal Gupta on Jan,2020
 */
public class ScopeIntegrationTest {

    private static final String NAME = "Spring App";
    private static final String OTHER_NAME = "Spring Boot";

    @Test
    public void givenSingletonScope_whenSetName_thenEqualsName(){
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ScopeConfig.class);

        final Person personSingletonA = (Person) applicationContext.getBean("personSingleton");
        final Person personSingletonB = (Person) applicationContext.getBean("personSingleton");

        personSingletonA.setName(NAME);

        Assert.assertEquals(NAME,personSingletonB.getName());

        ((AbstractApplicationContext)applicationContext).close();
    }

    @Test
    public void givenPrototypeScope_whenSetName_thenDifferntName(){
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ScopeConfig.class);

        final Person personPrototypeA = (Person) applicationContext.getBean("personPrototype");
        final Person personPrototypeB = (Person) applicationContext.getBean("personPrototype");

        personPrototypeA.setName(NAME);
        personPrototypeB.setName(OTHER_NAME);

        Assert.assertEquals(NAME,personPrototypeA.getName());
        Assert.assertEquals(OTHER_NAME,personPrototypeB.getName());

        ((AbstractApplicationContext)applicationContext).close();
    }
}
