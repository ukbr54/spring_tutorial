package injectingPrototypeToSingleton;

import java.time.LocalDateTime;

/**
 * Created by Ujjwal Gupta on Jan,2020
 */
public class PrototypeBean {

    private String date = LocalDateTime.now().toString();

    public String getDate() {
        return date;
    }
}
