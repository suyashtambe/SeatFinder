package com.seatfinder.arrangement;

import com.seatfinder.dto.Room;
import com.seatfinder.dto.Seat;

import java.util.List;

public abstract class AbstractSeatingArrangement {
     public abstract List<Seat> generateSeating(int numStudents, List<Room> rooms);


}
