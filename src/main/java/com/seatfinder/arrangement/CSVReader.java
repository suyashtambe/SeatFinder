package com.seatfinder.arrangement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static List<String[]> readCSV(String fileName) {
        List<String[]> data = new ArrayList<>();//This method is used to read data from a CSV file and store it in a list of string arrays.

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // Initialize a BufferedReader to read from the file
            String line; // Declare a variable to store each line read from the file
            while ((line = br.readLine()) != null) { // Read lines until the end of the file
                // Split each line into an array of strings using comma as delimiter
                String[] values = line.split(",");

                // Add the array of values to the data list
                data.add(values);
            }
        } catch (IOException e) { // Catch any IOException that might occur during file reading
            // Print the stack trace of the caught exception for debugging
            e.printStackTrace();
        }


        return data;
    }

}