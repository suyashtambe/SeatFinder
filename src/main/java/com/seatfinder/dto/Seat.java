package com.seatfinder.dto;

public class Seat {
    private int roomNumber;
    private int row;
    private int column;

    public Seat(int roomNumber, int row, int column) {
        this.roomNumber = roomNumber;
        this.row = row;
        this.column = column;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "roomNumber=" + roomNumber +
                ", row=" + row +
                ", column=" + column +
                '}';
    }
}
