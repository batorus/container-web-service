package com.batorus.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ContainerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContainerApplication.class, args);
    }

}
