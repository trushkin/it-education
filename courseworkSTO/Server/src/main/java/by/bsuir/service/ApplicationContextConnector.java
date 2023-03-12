package by.bsuir.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextConnector {
    private static ClassPathXmlApplicationContext context;
    public static ClassPathXmlApplicationContext getContext() {
        if (context == null) {
            return new ClassPathXmlApplicationContext("applicationContext.xml");
        }
        return context;
    }

}
