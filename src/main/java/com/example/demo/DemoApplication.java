package com.example.demo;

import com.example.demo.service.CoordinateFunctions;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate) {
        CoordinateFunctions coordinateFunctions = new CoordinateFunctions();
        return args -> {
            for (int i = 0; true; i++) {
                String str = coordinateFunctions.generateCoordinates();
                kafkaTemplate.send("coordinatesTopic", str);
                Thread.sleep(1000);
            }
        };
    }

}
