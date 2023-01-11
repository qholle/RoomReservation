
import java.util.ArrayList;

/**
 * A class used to create and modify Room objects
 * 
 * @author Quentin Holle
 */
public class Room {

  private static ArrayList<String> names = new ArrayList<String>(); // ArrayList of names

  /**
   * This method creates a string array of room names.
   * 
   * @return a string array of all room names
   */
  public static String[] getNames() {
    String[] roomNames = new String[names.size()];
    for (int i = 0; i < roomNames.length; i++) {
      roomNames[i] = names.get(i);
    }
    return roomNames;
  }

  private String name; // name of the room
  private Person[] occupants; // array of people
  private int currentOccupancy; // current occupancy of the room

  /**
   * This method creates a room with values given by the arguments
   * 
   * @param name     is the name of the room that is created
   * @param capacity is the capacity of the room that is created
   * @return a Room object with the given parameters
   */
  public Room(String name, int capacity) {
    this.name = name;
    if (capacity > 0 && !names.contains(name)) {
      occupants = new Person[capacity];
    } else {
      throw new IllegalArgumentException("Capacity is less than zero or name is already in list!");
    }
    names.add(name);
  }

  /**
   * This method gets the name of the room.
   * 
   * @return the name of the room
   */
  public String getName() {
    return name;
  }

  /**
   * This method gets the occupancy of the room.
   * 
   * @return the occupancy of the room
   */
  public int getOccupancy() {
    return currentOccupancy;
  }

  /**
   * This method gets the COVID capacity of the room.
   * 
   * @return the COVID capacity of the room
   */
  public int getCOVIDCapacity() {
    return (occupants.length / 2 + occupants.length % 2);
  }

  /**
   * This method gets the regular capacity of a given room.
   * 
   * @return the regular capacity of the room
   */
  public int getCapacity() {
    return (occupants.length);
  }

  /**
   * This method searches the occupants array to determine if it contains the given person
   * 
   * @param p is the person to be searched for in the occupants array
   * @return true if the given person is in the array, and false if it isn't
   */
  public boolean contains(Person p) {
    for (int i = 0; i < getCapacity(); i++) {
      if (occupants[i] != null) {
        if (occupants[i] == p) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * This method checks in a given person to the room
   * 
   * @param in is the person to be checked into the room
   * @return true if the given person was checked into the room, false if not.
   * @throws an IllegalArgumentException if the person passed was null or already checked in
   */
  public boolean checkIn(Person in) {
    if (in == null || !in.isWaiting()) {
      throw new IllegalArgumentException("Person who was input was null or was already checked in");
    }
    if (currentOccupancy < getCOVIDCapacity()) {
      for (int i = 0; i < getCapacity(); i += 2) {
        if (occupants[i] == null) {
          occupants[i] = in;
          in.toggleWaiting();
          currentOccupancy = currentOccupancy + 1;
          return true;
        }
      }
    }
    return false;
  }

  /**
   * This method checks out a given person
   * 
   * @param out the person to be checked out of the room
   * @return true if the given person was checked out of the room, false if not.
   * @throws an IllegalArgumentException if the person passed was null
   */
  public boolean checkOut(Person out) {
    if (out == null) {
      throw new IllegalArgumentException("Person passed as input was null");
    }
    for (int i = 0; i < getCapacity(); i++) {
      if (occupants[i] != null) {
        if (occupants[i] == out) {
          currentOccupancy = currentOccupancy - 1;
          out.toggleWaiting();
          occupants[i] = null;
          return true;
        }
      }
    }
    return false;
  }

  /**
   * This method creates a formatted string representation of the room and its occupants
   * 
   * @return a string representation of the room and its occupants
   */
  public String toString() {
    String room = "";
    room = room + name + "\n===";
    for (int i = 0; i < getCapacity(); i++) {
      if (occupants[i] == null) {
        room = room + "\n-";
      } else {
        room = room + "\n" + occupants[i].getName();
      }
    }
    room = room + "\n";
    return room;
  }
}
