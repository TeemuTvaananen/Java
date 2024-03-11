package dev.m3s.programming2.homework2;
import java.util.Random;
/*This Class is the student class which utlizes every other class.*/


/*All the needed attributes for a student.*/
public class Student {
    private String firstName = ConstantValues.NO_NAME;
    private String lastName = ConstantValues.NO_NAME;
    private int startYear = ConstantValues.CURRENT_YEAR;
    private int graduationYear;
    private int id;
    private final int degreeCount = 3;
    private Degree[] degrees = new Degree[degreeCount];
    private String birthDate = "\"" + ConstantValues.NO_BIRTHDATE + "\"";

    /*Constructors for the student. */

    /*"Default" constructor for the student. Start year is set to current year by default and the elements for the array are created in this constructor.*/
    public Student(){
        id = getRandomId();
        setStartYear(ConstantValues.CURRENT_YEAR);
        int i = 0;
        while (i<degrees.length){
            degrees[i] = new Degree();
            i++;
        }
    id = getRandomId();
    }
/*The constructor first calls the default constructor of the Student class using the "this()" keyword. This is done to initialize the array of degrees and setting the startYear
* to current year which are done in the "default" constructor */
    public Student(String lname, String fname) {
        this();
    setFirstName(fname);
    setLastName(lname);
    id = getRandomId();
    }
    /*Getters and setters for the first- and the last name. Names can be set only if the given name is not null*/
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        if(firstName != null){
            this.firstName = firstName;
        }
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        if(lastName != null){
            this.lastName = lastName;
        }
    }

    /* Getter and setter for the id. Id can be set only if it is within the valid range.*/
    public int getId() {
        return id;
    }

    public void setId(final int id) {
        if(id >= ConstantValues.MIN_ID && id <= ConstantValues.MAX_ID){
            this.id = id;
        }
    }

    /*Getter and setter for the start year. Start year can be set only if it is within the valid range.*/
    public void setStartYear(final int startYear) {
        if(startYear>2000 && startYear <= ConstantValues.CURRENT_YEAR ){
            this.startYear = startYear;
        }
    }
    public int getStartYear() {
        return startYear;
    }

    /*Getter and setter for the graduation year. With the graduation year we first check if the student can graduate by utilizing the method canGraduate.
    * If that returns false, the method returns "Check amount of required credits". If it is true, we continue by checking if the year is valid. If it is not,
    * the method will return "Check graduation year". If all of the checks are passed, we set the graduationyear and return "Ok".*/
    public int getGraduationYear() {
        return graduationYear;
    }
    public String setGraduationYear(final int graduationYear) {
        if(!canGraduate()){
            return "Check amount of required credits";
        } else if(graduationYear>ConstantValues.CURRENT_YEAR || graduationYear < startYear){
            return "Check graduation year";
        } else{
            this.graduationYear = graduationYear;
            return "Ok";
        }
    }

    /*This method sets the degrees name if the name is not null and the given degree's index is within the range of degreecount.*/
    public void setDegreeTitle(final int i, String dName){
        if ((i >= 0 && i < degreeCount) && (dName != null)) {
            degrees[i].setDegreeTitle(dName);
        }
    }

    /*This method will add a course to a degree if the course is not null and the given degrees index is within the range of degreecount.*/
    public boolean addCourse(final int i, StudentCourse course){
        if ((i >= 0 && i <= degreeCount) && (course != null)){
            return degrees[i].addStudentCourse(course);
        }
        return false;
    }
/* In this method we add the courses for the degree if the index is valid and the array and the course is not null. Method returns the number of added courses.*/
    public int addCourses(final int i, StudentCourse[] courses){
        int index, count = 0;
        if (courses != null){
            for(index = 0; index < courses.length; index++){
                if(count < 50 && courses[index] != null && addCourse(i, courses[index])){
                    count++;
                }
            }
        }

        return count;
    }

    /*This method returns all the courses from all degrees(not null values)*/
    public void printCourses() {
        for (Degree degree : degrees) {
            if (degree != null) {
                for(StudentCourse course: degree.getCourses()){
                    if(course != null){
                        System.out.println(course);
                    }
                }
            }
        }
    }
/*This method will print all the degrees(not null values).*/
    public void printDegrees(){
            for (Degree degree : degrees) {
                if (null != degree) {
                    System.out.println(degree);
                }
            }
    }
/*In this method we set the given title name to the given degree. We have to check that the index is valid and the name itself is not null.*/
    public void setTitleOfThesis(final int i, String title){
        if ((i >= 0 && i < degreeCount) && (title != null)) {
            degrees[i].setTitleOfThesis(title);
        }
    }

    /*Getter and setter for the students birthday. By default the getter returns the value. "Not available". We only allow the setter to
    * set the birthday if the given person is not null and the method setPersonId from PersonId class returns "OK". The setter also returns the birthday or "No change" in
    * case the birthday can not be set.  */
    public String getBirthDate() {
        return birthDate;
    }

    public String setBirthDate(String personId) {
        PersonID testPerson = new PersonID();
        if(testPerson != null) {
            if (testPerson.setPersonId(personId).equals("Ok")) {
                this.birthDate = testPerson.getBirthDate();
                return this.birthDate;
            }
        }
        return "No change";
    }

    /*This method checks if the student has graduated by seeing if the getGraduationYear returns something else than 0.*/
    public boolean hasGraduated() {
        return getGraduationYear() != 0;
    }
/*This method will return the studied year whether or not the student has graduated. If the student has graduated years studied is simply the difference between graduation year
* and start year. Otherwise it is the difference between the current year and start year.*/
    public int getStudyYears(){
        int years_Studied = ConstantValues.CURRENT_YEAR - getStartYear();
        if (hasGraduated()){
            years_Studied = getGraduationYear() - getStartYear();
        }
        return years_Studied;

    }
    /*This method checks if the student has all the required credits and titles for the both degree's theisis. If so it will return true.*/
    private boolean canGraduate(){

        return (degrees[0].getCredits() >= ConstantValues.BACHELOR_CREDITS && degrees[1].getCredits() >= ConstantValues.MASTER_CREDITS)
                && (!degrees[0].getTitleOfThesis().equals(ConstantValues.NO_TITLE) && !degrees[1].getTitleOfThesis().equals(ConstantValues.NO_TITLE));
    }

    /*This method calculates a random integer between 1 and 100.*/
    private int getRandomId() {
        final Random studentId = new Random();
        return studentId.nextInt(ConstantValues.MAX_ID) + ConstantValues.MIN_ID;
    }

    /*Own method. Calculates the total credits for the students by looping through all the degrees and adding up the credits from them.*/
    private double getTotalCredits(){
        double total = 0;
        for(Degree degree: degrees){
            total += degree.getCredits();
        }
        return total;
    }

    /*This method will return the given degrees credits based on the index(0= bachelor, 1=master)*/
    private double getDegreeCredits(int i){
        double credits;
        credits = degrees[i].getCredits();
        return credits;
    }
/*This method will return the given degree's title. Based on the index(0=bachelor, 1= master).*/
    private String getDegreeThesisTitle(int i){
        return degrees[i].getTitleOfThesis();
    }

    public String toString(){
        StringBuilder myString = new StringBuilder();
        myString.append("\nStudent id: ").append(getId()).append("\n").append(" FirstName: ").append(getFirstName()).append(", LastName: ").append(getLastName()).append("\n").append(" Date of birth: ").append(birthDate).append("\n");
        //here we check if the student has graduated and format the string according to it
        if(hasGraduated()){
            myString.append(" Status: The Student has graduated in ").append(getGraduationYear()).append("\n");
            myString.append(" StartYear: ").append(getStartYear()).append("(studies have lasted for ").append(getGraduationYear() - getStartYear()).append(" years)").append("\n");
            myString.append(" Total credits: ").append(getTotalCredits()).append("\n");
            myString.append(" Bachelor credits: ").append(getDegreeCredits(0)).append("\n\t Total bachelors credits completed ").append("(").append(getDegreeCredits(0)).append("/").append(ConstantValues.BACHELOR_CREDITS).append(")").append("\n");
            myString.append(" TitleOfBachelorsThesis: " + "\"").append(getDegreeThesisTitle(0)).append("\"");
            myString.append(" MasterCredits: ").append(getDegreeCredits(1)).append("\n\tTotal masters credits completed ").append("(").append(getDegreeCredits(1)).append("/").append(ConstantValues.MASTER_CREDITS).append(")").append("\n");
        }else{
            myString.append(" Status: The Student has not graduated, yet " + "\n");
            myString.append(" StartYear: ").append(getStartYear()).append(" (studies have lasted for ").append(ConstantValues.CURRENT_YEAR - getStartYear()).append(" years) ").append("\n");
            myString.append(" Total credits: ").append(getTotalCredits()).append("\n");

            //here we check if the student has completed all required bachelor studies but not master studies and format the string according to it
            if(getDegreeCredits(0) >= ConstantValues.BACHELOR_CREDITS) {
                myString.append(" Bachelor credits: ").append(getDegreeCredits(0)).append("\n\t Total bachelors credits completed ").append("(").append(getDegreeCredits(0)).append("/").append(ConstantValues.BACHELOR_CREDITS).append(")").append("\n");
                myString.append(" Title of BSc: " + "\"").append(getDegreeThesisTitle(0)).append("\"").append("\n");
            }else {
                myString.append(" Bachelor credits: ").append(getDegreeCredits(0)).append(" Missing bachelors credits ").append(ConstantValues.BACHELOR_CREDITS - getDegreeCredits(0)).append("(").append(getDegreeCredits(0)).append("/").append(ConstantValues.BACHELOR_CREDITS).append(")").append("\n");
                myString.append(" Title of BSc: " + "\"").append(getDegreeThesisTitle(0)).append("\"");
            }

            //here we check if student has completed all of the required masters studies but not bachelors and format the string according to it
            if(getDegreeCredits(1) >= ConstantValues.MASTER_CREDITS ){
                myString.append(" Master credits: ").append(getDegreeCredits(1)).append("\n\t Total masters credits completed").append(" (").append(getDegreeCredits(1)).append("/").append(ConstantValues.MASTER_CREDITS).append(")").append("\n");
            }  else {
                myString.append(" Master credits: ").append(getDegreeCredits(1)).append(" Missing masters credits ").append(ConstantValues.MASTER_CREDITS - getDegreeCredits(1)).append(" (").append(getDegreeCredits(1)).append("/").append(ConstantValues.MASTER_CREDITS).append(")").append("\n");
            }
        }
        myString.append(" TitleOfMastersThesis: " + "\"").append(getDegreeThesisTitle(1)).append("\"").append("\n");
        return String.valueOf(myString);
    }
}
