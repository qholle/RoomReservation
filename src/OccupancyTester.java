import java.util.ArrayList;

/**
 * A class used to test the Room and Person classes of my program.
 * 
 * @author Quentin Holle
 */
public class OccupancyTester {

  /**
   * Main method tests each of the methods in the Room and Person classes
   */
  public static void main(String[] args) {
    testPerson();
    testRoomConstructor();
    testRoomAccessors();
    testRoomCheckIn();
    testRoomCheckOut();
    testRoomToString();
  }

  /**
   * This method tests the constructor, accessors, mutator, and equals method for the Person class
   * 
   * @return true if the tests pass, false if they fail
   */
  public static boolean testPerson() {
    ArrayList<Person> people = new ArrayList<Person>();
    people.add(new Person("Quentin"));
    people.add(new Person("Dave"));

    if (!people.get(0).getName().equals("Quentin")) {
      System.out.println("Name " + people.get(0).getName() + " is not \"Quentin\"");
      return false;
    }
    if (!people.get(1).getName().equals("Dave")) {
      System.out.println("Name " + people.get(0).getName() + " is not \"Dave\"");
      return false;
    }
    if (people.get(0).isWaiting() == false) {
      System.out.println("Quentin was not waiting for a room when he should have been.");
      return false;
    }
    people.get(0).toggleWaiting();
    if (people.get(0).isWaiting() == true) {
      System.out.println("toggleWaiting failed to toggle Quentin's waiting status.");
      return false;
    }
    if (people.get(0).equals(people.get(1))) {
      System.out.println("The two people objects are the same, when that shouldn't be the case");
      return false;
    }
    System.out.println("All tests in testPerson method passed!");
    return true;
  }

  /**
   * This method tests the constructor of the Room class
   * 
   * @return true if the tests pass, false if they fail
   */
  public static boolean testRoomConstructor() {
    ArrayList<Room> testRoomConstructor = new ArrayList<Room>();
    try {
      testRoomConstructor.add(new Room("AG 100", 7));
      testRoomConstructor.add(new Room("AG 100", 10));
    } catch (IllegalArgumentException e) {
      System.out.println("All tests in testRoomConstructor method passed!");
      return true;
    }

    System.out.println("Two rooms created with the same name! That shouldn't happen.");
    return false;
  }

  /**
   * This method tests the accessor methods of the Room class
   * 
   * @return true if the tests pass, false if they fail
   */
  public static boolean testRoomAccessors() {
    Room testRoomAccessors;

    testRoomAccessors = new Room("CS 200", 14);

    if (testRoomAccessors.getCapacity() != 14) {
      System.out.println("Actual Room Capacity: " + testRoomAccessors.getCapacity()
          + " Expected Room Capacity: 14");
      return false;
    }
    if (!testRoomAccessors.getName().equals("CS 200")) {
      System.out.println("Actual Name: " + "\"" + testRoomAccessors.getName() + "\""
          + " Expected Name: \"CS 200\"");
      return false;
    }
    if (testRoomAccessors.getCOVIDCapacity() != 7) {
      System.out.println("Actual Covid Capacity: " + testRoomAccessors.getCOVIDCapacity()
          + "Expected Covid Capacity: 7");
      return false;
    }
    if (testRoomAccessors.getCapacity() != 14) {
      System.out
          .println("Actual Capacity: " + testRoomAccessors.getCapacity() + "Expected Capacity: 14");
      return false;
    }
    System.out.println("All tests in testRoomAccessors method passed!");
    return true;
  }

  /**
   * This method tests the check-in functionality and effects on the Room and Person checked in
   * 
   * @return true if the tests pass, false if they fail
   */
  public static boolean testRoomCheckIn() {
    Room testRoomCheckIn;
    ArrayList<Person> people = new ArrayList<Person>();

    testRoomCheckIn = new Room("CS 300", 4);

    people.add(new Person("Quentin"));
    people.add(new Person("Bob"));
    people.add(new Person("Stevie"));

    if (!testRoomCheckIn.checkIn(people.get(0))) {
      System.out.println("roomCheckIn method should have checked in Quentin and it didn't.");
      return false;
    }
    try {
      if (testRoomCheckIn.checkIn(people.get(0))) {
        System.out.println("roomCheckIn method shouldn't have checked in Quentin because he was "
            + "already in the room.");
        return false;
      }
    } catch (IllegalArgumentException c) {
    }
    if (!testRoomCheckIn.checkIn(people.get(1))) {
      System.out.println("roomCheckIn method should have checked in Bob and it didn't.");
      return false;
    }
    if (!testRoomCheckIn.contains(people.get(0))) {
      System.out.println("Occupant " + people.get(0).getName() + " not found in list.");
      return false;
    }
    if (testRoomCheckIn.getOccupancy() != 2) {
      System.out
          .println("Actual Occupancy " + testRoomCheckIn.getOccupancy() + " Expected Occupancy: 2");
      return false;
    }

    if (testRoomCheckIn.checkIn(people.get(2))) {
      System.out.println("roomCheckIn method returned true when it should have returned false.");
      return false;
    }

    if (testRoomCheckIn.getOccupancy() != 2) {
      System.out
          .println("Actual Occupancy " + testRoomCheckIn.getOccupancy() + " Expected Occupancy: 2");
      return false;
    }
    if (testRoomCheckIn.contains(people.get(2))) {
      System.out.println("Occupant \"Stevie\" shouldn't have been added to list.");
      return false;
    }
    try {
      testRoomCheckIn.checkIn(null);
    } catch (IllegalArgumentException e) {
      System.out.println("All tests in testRoomCheckIn method passed!");
      return true;
    }

    System.out.println("IllegalArgumentException wasn't thrown when a null person was checked in.");
    return false;

  }

  /**
   * This method tests the check-out functionality and effects on the Room and Person checked in
   * 
   * @return true if the tests pass, false if they fail
   */
  public static boolean testRoomCheckOut() {

    Room testRoomCheckOut;
    ArrayList<Person> people = new ArrayList<Person>();

    testRoomCheckOut = new Room("COM ARTS 250", 5);

    people.add(new Person("Quentin"));
    people.add(new Person("Bob"));
    people.add(new Person("Stevie"));
    people.add(new Person("Jeremy"));

    testRoomCheckOut.checkIn(people.get(0));
    testRoomCheckOut.checkIn(people.get(1));
    testRoomCheckOut.checkIn(people.get(2));

    if (!testRoomCheckOut.checkOut(people.get(0))) {
      System.out.println(
          "roomCheckOut method didnt check out Quentin even though he was in the classroom.");
      return false;
    }
    if (testRoomCheckOut.contains(people.get(0))) {
      System.out.println("Occupant " + people.get(0).getName() + "was found in list.");
      return false;
    }
    if (testRoomCheckOut.getOccupancy() != 2) {
      System.out.println(
          "Actual Occupancy " + testRoomCheckOut.getOccupancy() + " Expected Occupancy: 2");
      return false;
    }
    if (testRoomCheckOut.checkOut(people.get(3))) {
      System.out
          .println("roomCheckOut method returned true even though Jeremy wasn't in the classroom.");
      return false;
    }
    if (testRoomCheckOut.getOccupancy() != 2) {
      System.out.println(
          "Actual Occupancy " + testRoomCheckOut.getOccupancy() + " Expected Occupancy: 2");
      return false;
    }
    try {
      testRoomCheckOut.checkIn(null);
    } catch (IllegalArgumentException e) {
      System.out.println("All tests in testRoomCheckOut method passed!");
      return true;
    }

    System.out.println("IllegalArgumentException wasn't thrown when a null person was checked in.");
    return false;
  }

  /**
   * This method tests the results of the toString method
   * 
   * @return true if the tests pass, false if they fail
   */
  public static boolean testRoomToString() {

    Room testRoomToString;
    ArrayList<Person> people = new ArrayList<Person>();

    testRoomToString = new Room("MATH 171", 7);
    people.add(new Person("Quentin"));
    people.add(new Person("Bob"));
    people.add(new Person("Stevie"));
    people.add(new Person("Jeremy"));

    for (int i = 0; i < people.size(); i++) {
      testRoomToString.checkIn(people.get(i));
    }

    if (!testRoomToString.toString()
        .equals("MATH 171\n===\nQuentin\n-\nBob\n-\nStevie\n-\nJeremy\n")) {
      System.out.println("Actual: " + testRoomToString.toString()
          + " different from expected: MATH 171\n===\nQuentin\n-\nBob\n-\nStevie\n-\nJeremy\n");
      return false;
    }
    System.out.println("All tests in testRoomToString method passed!");
    return true;
  }

}
