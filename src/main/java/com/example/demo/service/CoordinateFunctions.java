package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CoordinateFunctions {
    static final double ourLatitude = 41.099288987638914;
    static final double ourLongitude = 24.789900033102697;

    public String generateCoordinates() {
        Random rand = new Random();

        double latitude = rand.nextDouble(-90,90);
        double longitude = rand.nextDouble(-180,180);

        return latitude + "," + longitude;
    }
    public Double distanceReport (String s)
    {
        double lat1 = Double.parseDouble(s.split(",")[0]);
        double lon1 = Double.parseDouble(s.split(",")[1]);

        double lon1rad = Math.toRadians(lon1);
        double lon2rad = Math.toRadians(ourLongitude);
        double lat1rad = Math.toRadians(lat1);
        double lat2rad = Math.toRadians(ourLatitude);

        double a = Math.pow(Math.sin((lat2rad - lat1rad)/2),2);
        double b = Math.cos(lat1rad) * Math.cos(lat2rad) * Math.pow(Math.sin((lon2rad - lon1rad)/2),2);
        double c = Math.asin(Math.sqrt(a + b));

        return 2 * 6371 * c;
    }
}
