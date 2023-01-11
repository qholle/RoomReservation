/**
 * A class used to create and modify a Person object
 * 
 * @author Quentin Holle
 */
public class Person {

  private String name; // the name of the person
  private boolean isWaiting; // the waiting status of a person

  /**
   * This method creates a new person object
   * 
   * @param name is the name of the person created
   * @return a Person object with the given parameters
   */
  public Person(String name) {
    this.name = name;
    isWaiting = true;
  }

  /**
   * This method gets the name of the person
   * 
   * @return a String of the person's name
   */
  public String getName() {
    return name;
  }

  /**
   * This method gets the waiting status of the person
   * 
   * @return true if the person is waiting, false if otherwise
   */
  public boolean isWaiting() {
    return isWaiting;
  }

  /**
   * This method toggles the waiting status of the person
   */
  public void toggleWaiting() {
    if (isWaiting) {
      isWaiting = false;
    } else {
      isWaiting = true;
    }
  }

  /**
   * This method checks if the object is the instance of person.
   * 
   * @param o the object to be checked
   * @return true if they are the same, false if not
   */
  public boolean equals(Object o) {
    if (o instanceof Person) {
      return this.name.equals(((Person) o).name);
    }
    return false;
  }

}
