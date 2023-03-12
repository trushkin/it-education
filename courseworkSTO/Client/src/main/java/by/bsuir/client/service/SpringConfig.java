package by.bsuir.client.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class SpringConfig {
    @Bean ClientConnectionModule clientConnectionModule(){
        return new ClientConnectionModule();
    }
}
