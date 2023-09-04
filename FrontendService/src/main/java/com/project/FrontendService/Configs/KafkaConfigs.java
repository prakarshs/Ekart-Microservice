package com.project.FrontendService.Configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfigs {
    @Bean
    public NewTopic newTopic(){
        return TopicBuilder.name("User-Journey").build();
    }
}
