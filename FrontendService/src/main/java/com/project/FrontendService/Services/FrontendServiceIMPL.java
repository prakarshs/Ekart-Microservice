package com.project.FrontendService.Services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@Data
@AllArgsConstructor
public class FrontendServiceIMPL implements FrontendService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendUserInteraction(String userInteraction) {
        log.info("this is being sent {}",userInteraction);
        this.kafkaTemplate.send("User-Journey", userInteraction);
    }
}
