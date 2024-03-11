package dev.m3s.programming2.homework3;
import java.util.ArrayList;
import java.util.List;
// Responsible teacher class
public class ResponsibleTeacher extends Employee implements Teacher, Payment {
  private List<DesignatedCourse> courses = new ArrayList<>();

  //Constructor for the responsible teacher. Uses the constructor for Person by using the keyword "super".
    public ResponsibleTeacher(String lname, String fname) {
        super(lname, fname);
    }

    // this method returns the corresponding id string for the responsible teacher
    public String getEmployeeIdString() {
        return "OY_TEACHER_";
    }
// this method sets the courses for the responsible teacher
    public void setCourses(List<DesignatedCourse> courses) {
        if(courses!= null){
            this.courses = courses;
        }
    }
// this method will output all the assigned courses for the responsible teacher. Used the method isResponsible and formatted the string according to it
    public String getCourses() {
        StringBuilder print = new StringBuilder();
        for(DesignatedCourse course: courses){
            if(course != null){
                if(course.isResponsible()){
                    print.append("Responsible teacher: ").append(course).append("\n");
                } else{
                    print.append("Teacher: ").append(course).append("\n");
                }
            }
        }
        return print.toString();
    }

    // this method will print out the responsible teacher.
    /* For example:
    Teacher id: OY_TEACHER_2412
	    First name: Mickey, Last name: Mouse
	    Birthdate: 23.04.1998
	    Salary: 765.85
	    Teacher for courses:
    Responsible teacher: [course=[223344A (50.50 cr), "More basic studies". Mandatory, period: 1.], year=2023]
    Teacher: [course=[556677A (50.00 cr), "Even more basic studies". Optional, period: 3.], year=2023]
    Teacher: [course=[666666S (8.00 cr), "Extra master studies". Optional, period: 5.], year=2022]
    Teacher: [course=[888888S (18.00 cr), "Final master studies". Mandatory, period: 5.], year=2023]
     */
    public String toString(){
        return "Teacher id: " + getIdString()  +
                "\n\tFirst name: " + getFirstName() + "," + " Last name: " + getLastName() +
                "\n\tBirthdate: " + getBirthDate() +
                "\n\tSalary: " + String.format("%.2f", getPayment().calculatePayment()) +
                "\n\tTeacher for courses:\n" + getCourses();
    }
}
