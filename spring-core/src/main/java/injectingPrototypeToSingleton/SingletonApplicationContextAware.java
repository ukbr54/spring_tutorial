package injectingPrototypeToSingleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * Created by Ujjwal Gupta on Jan,2020
 */
public class SingletonApplicationContextAware {

    private @Autowired ApplicationContext ctx;

    public PrototypeBean getPrototypeBean(){
        return ctx.getBean(PrototypeBean.class);
    }

    public void message(){
        System.out.println("Time: "+ getPrototypeBean().getDate());
    }
}
