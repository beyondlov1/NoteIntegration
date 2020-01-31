package com.beyond.note.integration;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@SpringBootApplication
@EnableScheduling
public class NoteIntegrationApplication {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) throws IOException {
        new SpringApplicationBuilder(NoteIntegrationApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

}
