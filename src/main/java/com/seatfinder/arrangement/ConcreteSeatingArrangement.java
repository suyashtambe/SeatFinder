package com.seatfinder.arrangement;


import com.seatfinder.dto.Room;
import com.seatfinder.dto.Seat;
import java.util.ArrayList;
import java.util.List;

public class ConcreteSeatingArrangement extends AbstractSeatingArrangement {
    public List<Seat> generateSeating(int numStudents, List<Room> rooms) {
        List<Seat> seatingArrangement = new ArrayList<>();
        int studentsSeated = 0;

        for (Room room : rooms) {
            int numRows = room.getNumRows();
            int numColumns = room.getNumColumns();

            for (int row = 1; row <= numRows; row++) {
                for (int column = 1; column <= numColumns; column++) {
                    if (studentsSeated < numStudents) {
                        seatingArrangement.add(new Seat(room.getRoomNumber(), row, column));
                        studentsSeated++;
                    } else {
                        break;
                    }
                    // Add an empty seat between students if needed
                    if (studentsSeated < numStudents) {
                        column++; 
                    }
                }
                if (studentsSeated >= numStudents) {
                    break;
                }
            }
            if (studentsSeated >= numStudents) {
                break;
            }
        }

        return seatingArrangement; 
    }
}
