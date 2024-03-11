package dev.m3s.programming2.homework3;
import java.util.Random;
// abstract class person
public abstract class Person {
    private String firstName = ConstantValues.NO_NAME;
    private String lastName = ConstantValues.NO_NAME;
    private String birthdate = ConstantValues.NO_BIRTHDATE;

    // constructor for the person, sets the firstname and last name
    public Person(String lname, String fname){
        setFirstName(fname);
        setLastName(lname);
    }

    // getter and setter for the first name, only set if the given name is not null
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        if(firstName != null){
            this.firstName = firstName;
        }
    }
    // getter and setter for the lastname, only set if the given name is not null
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        if(lastName != null){
            this.lastName = lastName;
        }
    }
    //getter and setter for the birthdate, only set if the person object is not null and the setPersonId method returns "ok"
    public String getBirthDate() {
        return birthdate;
    }
    public String setBirthDate(String birthdate) {
        PersonID testPerson = new PersonID();
        if(testPerson != null) {
            if (testPerson.setPersonId(birthdate).equals("Ok")) {
                this.birthdate = testPerson.getBirthDate();
                return this.birthdate;
            }
        }
        return "No change";
    }
    // this method returns the random id, generated from the given parametres range
    protected int getRandomId(final int min, final int max) {
        final Random id = new Random();
        return id.nextInt((max-min) + 1)+ min;
    }
    // abstract method
abstract String getIdString();

}
