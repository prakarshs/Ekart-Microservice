package com.project.UserService.Configs;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
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
        System.out.println(value);
        if (!sessionActive) {
            // Check if the received value indicates the start of a session
            if (value.equals("Start Session")) {
                sessionStarted = true;
            } else {
                sessionStarted = false;
            }
        } else {
            // Check if the received value indicates the end of a session
            if (value.equals("End Session")) {
                endSession = true;
            } else if (value.equals("Yes Button") && endSession) {
                // End the session when "Yes Button" follows "End Session"
                endSession();
            } else {
                // Add the received value to the buffer
                messageWindow.add(value);
                System.out.println("Received message: " + value);
            }
        }

        // Check if a session has started and we received a "Yes Button"
        if (sessionStarted && value.equals("Yes Button")) {
            startSession();
        }

        for (String message : messageWindow) {
            log.info("The Printing area");
            System.out.print(message+", ");
        }
    }

    private void startSession() {
        // Start a new session
        messageWindow.clear();
        sessionActive = true;
        sessionStarted = false;
        endSession = false;
        System.out.println("Session started.");
    }

    private void endSession() {
        // End the session and stop adding to the list
        sessionActive = false;
        System.out.println("Session ended.");
    }

}
