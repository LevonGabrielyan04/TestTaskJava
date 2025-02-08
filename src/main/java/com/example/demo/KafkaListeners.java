package com.example.demo;

import com.example.demo.service.CoordinateFunctions;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Component
public class KafkaListeners {
    CoordinateFunctions coordinateFunctions = new CoordinateFunctions();
    int iterator = 1;
    @KafkaListener(topics = "coordinatesTopic", groupId = "groupId")
    void coordinatesListener(String data){
        System.out.println("The coordinates are: "+data);

        String filePath = "src/main/resources/mydata.csv";
        try {
            Files.write(Paths.get(filePath), (data + "\n").getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }

        if(iterator % 5 == 0){
            System.out.println("The report is: " + coordinateFunctions.distanceReport(data) + " km");
        }
        iterator++;
    }
}
