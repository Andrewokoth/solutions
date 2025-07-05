/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.nash.aqimonitor;
import java.util.Arrays;
import java.util.Random;
/**
 *
 * @author nashvybzes
 */
public class AQIMonitor {

    public static void main(String[] args) {
        Random random = new Random();
        int[] aqiReadings = new int[30];
        
        for (int i = 0; i < aqiReadings.length; i++) {
            aqiReadings[i] = random.nextInt(300) + 1; // Generating AQI between 1 and 300
        }

        Arrays.sort(aqiReadings);
        double median = calculateMedian(aqiReadings);
        int hazardousDays = countHazardousDays(aqiReadings);

        System.out.println("Median AQI: " + median);
        System.out.println("Number of hazardous days: " + hazardousDays);
    }

    private static double calculateMedian(int[] aqiReadings) {
        int middle = aqiReadings.length / 2;
        if (aqiReadings.length % 2 == 0) {
            return (aqiReadings[middle - 1] + aqiReadings[middle]) / 2.0;
        } else {
            return aqiReadings[middle];
        }
    }

    private static int countHazardousDays(int[] aqiReadings) {
        int count = 0;
        for (int aqi : aqiReadings) {
            if (aqi > 200) {
                count++;
            }
        }
        return count;
    }
}