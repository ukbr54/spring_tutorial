package injectingPrototypeToSingleton;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Ujjwal Gupta on Jan,2020
 */
public class SingletonObjectFatoryBean {

    private @Autowired ObjectFactory<PrototypeBean> prototypeBeanObjectFactory;

    public PrototypeBean getPrototypeInstance() {
        return prototypeBeanObjectFactory.getObject();
    }

    public void message(){
        System.out.println("Hi, the time is "+getPrototypeInstance().getDate());
    }
}
