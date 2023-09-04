package com.project.UserService.Configs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Data
@Log4j2
public class KafkaConfigs {

    private final List<String> messageWindow = new ArrayList<>();
    private boolean sessionActive = false;
    private boolean sessionStarted = false;
    private boolean endSession = false;

    @KafkaListener(topics = "User-Journey", groupId = "group-02")
    public void receiveUserInteraction(String value) {

        if (!sessionActive) {
            // Check if the received value indicates the start of a session
            if (value.equals("Start Session")) {
                // Start a new session
                sessionActive = true;
                sessionStarted = false; // Reset sessionStarted
                endSession = false;
                messageWindow.clear();
                log.info("Session started.");
            }
        } else {
            // Check if the received value indicates the end of a session
            if (value.equals("End Session")) {
                endSession = true;
            } else if (value.equals("Yes Button") && endSession) {
                // End the session when "Yes Button" follows "End Session"
                endSession();
            } else if (value.equals("Yes Button") && !sessionStarted) {
                // Start the session when "Yes Button" follows "Start Session"
                sessionStarted = true;
                log.info("Session started.");
            } else {
                // Add the received value to the buffer
                messageWindow.add(value);
                log.info("Added message to messageWindow: {}", value);
            }
        }


    }

    private void endSession() {
        // End the session and stop adding to the list
        sessionActive = false;
        sessionStarted = false;
        endSession = false;
        log.info("Session ended.");

    }
}
