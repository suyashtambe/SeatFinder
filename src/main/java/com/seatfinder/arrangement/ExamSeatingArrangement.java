package com.seatfinder.arrangement;

import com.seatfinder.exception.ClassCapacityExceededException;
import com.seatfinder.dto.Room;
import com.seatfinder.dto.Seat;
import com.seatfinder.model.User;
import com.seatfinder.repository.UserRepository;

import java.util.*;

public class ExamSeatingArrangement {

    private final UserRepository userRepository;


    public ExamSeatingArrangement(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void seating(String csvFilePath, int numberOfRooms) throws ClassCapacityExceededException {
        List<Room> rooms = new ArrayList<>();
        System.out.println(csvFilePath);
        System.out.print("Enter the number of available rooms: ");
        int numRows = 12;
        int numColumns = 5;
        for (int i = 0; i < numberOfRooms; i++) {
            rooms.add(new Room(i + 1, numRows, numColumns));
        }
        User user = new User();
        user.setFileName(csvFilePath);
        user.setRooms(String.valueOf(numberOfRooms));
        userRepository.save(user);
        int numStudents = CSVReader.readCSV(csvFilePath).size();
        try {
            if (isCapacityExceeded(numStudents, rooms)) {
                throw new ClassCapacityExceededException("Number of students exceeds class capacity.");
            }
            ConcreteSeatingArrangement seatingArrangement = new ConcreteSeatingArrangement();

            List<Seat> seats = seatingArrangement.generateSeating(numStudents, rooms);
            System.out.println("Seating Arrangement for " + numStudents + " students:");
            saveSeatingArrangement(seats, rooms, csvFilePath);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private boolean isCapacityExceeded(int numStudents, List<Room> rooms) {
        int totalSeats = 0;
        for (Room room : rooms) {
            totalSeats += room.getNumRows() * room.getNumColumns();
        }
        return numStudents > totalSeats;
    }

    private void saveSeatingArrangement(List<Seat> seatingArrangement, List<Room> rooms, String csvFilePath) throws Exception {
        // Group seats by room
        Map<Integer, List<Seat>> seatsByRoom = new HashMap<>();
        for (Seat seat : seatingArrangement) {
            seatsByRoom.computeIfAbsent(seat.getRoomNumber(), k -> new ArrayList<>()).add(seat);
        }

        // Print seating arrangement for each room
        int seatNumber = 0;
        for (Room room : rooms) {
            List<Seat> seatsInRoom = seatsByRoom.getOrDefault(room.getRoomNumber(), Collections.emptyList());
            WriteDataToExcel.save(seatsInRoom, room.getRoomNumber(), seatNumber, csvFilePath);
            seatNumber = seatsInRoom.size();
        }
    }
}
