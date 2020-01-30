package com.beyond.note.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NoteIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoteIntegrationApplication.class, args);
    }

}
