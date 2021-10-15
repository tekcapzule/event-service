package com.tekcapsule.event.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.tekcapsule.event","com.tekcapsule.core"})
public class EventApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventApplication.class, args);
    }
}
