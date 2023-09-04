package com.project.UserService.Configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConfigs {
    @KafkaListener(topics = "User-Journey",groupId = "group-01")
    public void receiveUserInteraction(String value){
        System.out.println(value);
    }
}
