package com.seatfinder.arrangement;

import com.seatfinder.dto.Seat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.Collections;

public class WriteDataToExcel {

    // Method to save seating data to an Excel file
    public static void save(List<Seat> seat, int room, int seatno, String csv) {
        Workbook workbook = new XSSFWorkbook(); // Create a new Excel workbook

        // Create a new sheet
        Sheet sheet = workbook.createSheet("Students_Data");

        // Read CSV data from the provided file
        List<String[]> data = CSVReader.readCSV("src/main/resources/static/seating_csv/rollno.csv");

                Collections.shuffle(data);

        // Initialize variables
        int rowNum = 0; // Initialize row number (0-based index)
        int colNum = 0; // Initialize column number (0-based index)
        int studentId = 0; // Initialize student ID based on the total number of seats and seat number
        int rcount = 12; // Number of rows
        int ccount = 5; // Number of columns

        // Iterate through each row
        for (int i = 0; i < rcount; i++) {
            Row row = sheet.createRow(i); // Create a new row
            // Alternate the starting column index based on the row number to achieve the chessboard pattern
            int startCol = i % 2 == 0 ? 0 : 1;
            // Iterate through each column
            for (int j = startCol; j < ccount; j += 2) {
                // Get student data based on student ID
                String student = (studentId < data.size()) ? data.get(studentId)[0] : null;
                Cell cell = row.createCell(j); // Create a new cell in the row
                cell.setCellValue(student); // Set cell value
                studentId++; // Increment student ID for the next cell
            }
        }
        try {
            // Save the workbook to an Excel file
            FileOutputStream fileOut = new FileOutputStream(String.format("src/main/resources/static/seating_csv/studentData_%d.xlsx", room));
            workbook.write(fileOut);
            fileOut.close();
            workbook.close(); // Close the workbook
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace(); // Print the stack trace if an IOException occurs
        }
    }
}
