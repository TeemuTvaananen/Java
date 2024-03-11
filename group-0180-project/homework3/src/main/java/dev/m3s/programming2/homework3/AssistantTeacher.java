
package dev.m3s.programming2.homework3;
import java.util.ArrayList;
import java.util.List;
// class for the assistant teacher, inherits the class employee and implements the interfaces Teacher and payment
public class AssistantTeacher extends Employee implements Teacher, Payment {
private List<DesignatedCourse> courses = new ArrayList<>();

//  constructor: The constructor takes two parameters of type String: "lname" and "fname".
// The keyword "super" in the constructor is used to call the constructor of the person class
    public AssistantTeacher(String lname, String fname) {
        super(lname, fname);
    }

    // this String returns the corresponding string for the Assistant teacher
    public String getEmployeeIdString() {
        return "OY_ASSISTANT_";
    }
// this method outputs all the courses assigned to the assistant teacher
    public String getCourses() {
        StringBuilder print = new StringBuilder();
        for(DesignatedCourse course: courses){
            if(course != null){
               print.append(" ").append(course).append("\n");
            }
        }
        return print.toString();
    }
// this method sets all the designated courses for the assistant teacher
    public void setCourses(List<DesignatedCourse> courses) {
        if(courses != null){
            this.courses = courses;
        }
    }


// this string will provide the output for the teacher
    /* For example:
    Teacher id: OY_ASSISTANT_2431
	First name: Goofy, Last name: The Dog
	Birthdate: 14.12.2000
	Salary: 38.50
	Assistant for courses:
 [course=[223344A (50.50 cr), "More basic studies". Mandatory, period: 1.], year=2023]
 [course=[556677A (50.00 cr), "Even more basic studies". Optional, period: 3.], year=2023]
 [course=[666666S (8.00 cr), "Extra master studies". Optional, period: 5.], year=2022]
 [course=[888888S (18.00 cr), "Final master studies". Mandatory, period: 5.], year=2023]*/
  public String toString(){
      return "Teacher id: " + getIdString()  +
              "\n\tFirst name: " + getFirstName() + "," + " Last name: " + getLastName() +
              "\n\tBirthdate: " + getBirthDate() +
              "\n\tSalary: " + String.format("%.2f", getPayment().calculatePayment()) +
              "\n\tAssistant for courses: \n" +
              getCourses();
  }

}
