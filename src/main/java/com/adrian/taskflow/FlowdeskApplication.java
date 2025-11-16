package com.adrian.taskflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FlowdeskApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowdeskApplication.class, args);
    }

}
