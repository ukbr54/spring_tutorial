package injectingPrototypeToSingleton;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Ujjwal Gupta on Jan,2020
 */
public class SingletonBean {

    private @Autowired
    PrototypeBean prototypeBean;

    public void message(){
        System.out.println("Hi, the time is "+prototypeBean.getDate());
    }

    public PrototypeBean getPrototypeBean(){
        return this.prototypeBean;
    }
}
