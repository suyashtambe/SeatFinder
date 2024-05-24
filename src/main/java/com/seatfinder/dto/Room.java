package com.seatfinder.dto;

import java.util.ArrayList;
import java.util.List;


public class Room {
    private int roomNumber;
    private int numRows;
    private int numColumns;
    private List<Boolean> seatAvailability;

    public Room(int roomNumber, int numRows, int numColumns) {
        this.roomNumber = roomNumber;
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.seatAvailability = new ArrayList<>();
        initializeSeats();
    }

    private void initializeSeats() {
        int totalSeats = numRows * numColumns;
        for (int i = 0; i < totalSeats; i++) {
            seatAvailability.add(true); // Assuming all seats are initially available
        }
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public boolean isSeatAvailable(int row, int column) {
        int index = calculateIndex(row, column);
        return seatAvailability.get(index);
    }

    public void occupySeat(int row, int column) {
        int index = calculateIndex(row, column);
        seatAvailability.set(index, false);
    }

    public void releaseSeat(int row, int column) {
        int index = calculateIndex(row, column);
        seatAvailability.set(index, true);
    }

    private int calculateIndex(int row, int column) {
        return (row - 1) * numColumns + (column - 1);
    }

    // Additional methods can be added as needed

    @Override
    public String toString() {
        return "Room " + roomNumber + " with " + numRows + " rows and " + numColumns + " columns";
    }
}
