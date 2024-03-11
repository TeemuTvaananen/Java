package dev.m3s.programming2.homework3;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person{
    private int id;
    private int startYear = ConstantValues.CURRENT_YEAR;
    private int graduationYear;
   private final List<Degree> degrees = new ArrayList<>(3);

    public Student(String lname, String fname) {
        super(lname, fname);
        id = getRandomId(ConstantValues.MIN_STUDENT_ID, ConstantValues.MAX_STUDENT_ID);
        for(int i = 0; i < 3; i++){
          degrees.add(new Degree());
      }
    }

    public int getId() {return id;
    }
    public void setId(int id) {
        if(id >= 1 && id <=100){
            this.id = id;
        }
    }
    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        if(startYear>2000 && startYear <= ConstantValues.CURRENT_YEAR ){
            this.startYear = startYear;
        }
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
        if ((i >= 0 && i < 3) && (dName != null)) {
            degrees.get(i).setDegreeTitle(dName);
        }
    }

    /*This method will add a course to a degree if the course is not null and the given degrees index is within the range of degreecount.*/
    public boolean addCourse(final int i, StudentCourse course){
        if (degrees.size() > i && i >= 0 && i <= 3 && (course != null)){
            return degrees.get(i).addStudentCourse(course);
        }
        return false;
    }

    /* In this method we add the courses for the degree if the index is valid and the array and the course is not null. Method returns the number of added courses.*/
    public int addCourses(final int i, List<StudentCourse> courses) {
        int count = 0;
        if(courses != null){
            for (StudentCourse course : courses) {
                if (course != null && addCourse(i, course)) {
                    count++;  }
                if (count >= 50) {
                    return count;
                }
            }
        }
        return count;
    }

    /*This method returns all the courses from all degrees(not null values)*/
    public void printCourses() {
        for (Degree degree : degrees) {
            if (degree != null) {
                for (StudentCourse course : degree.getCourses()) {
                    if (course != null) {
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
        if ((i >= 0 && i < 3) && (title != null)) {
            degrees.get(i).setTitleOfThesis(title);
        }
    }
    /*This method checks if the student has graduated by seeing if the getGraduationYear returns something else than 0.*/
    public boolean hasGraduated() {
        return getGraduationYear() != 0;
    }

    /*This method checks if the student has all the required credits and titles for the both degree's theisis. If so it will return true.*/
    private boolean canGraduate(){
        if(!degrees.isEmpty()){
            return ( degrees.get(ConstantValues.BACHELOR_TYPE).getCredits() >= ConstantValues.BACHELOR_MANDATORY && degrees.get(ConstantValues.BACHELOR_TYPE).getCredits() >= ConstantValues.BACHELOR_CREDITS ) &&
                    ( degrees.get(ConstantValues.MASTER_TYPE).getCredits()>=ConstantValues.MASTER_MANDATORY && degrees.get(ConstantValues.MASTER_TYPE).getCredits()>= ConstantValues.MASTER_CREDITS )
                    && (!degrees.get(ConstantValues.BACHELOR_TYPE).getTitleOfThesis().equals(ConstantValues.NO_TITLE) && !degrees.get(ConstantValues.MASTER_TYPE).getTitleOfThesis().equals(ConstantValues.NO_TITLE));
        }
        return false;
    }
    /*This method will return the studied year whether the student has graduated. If the student has graduated years studied is simply the difference between graduation year
     * and start year. Otherwise, it is the difference between the current year and start year.*/
    public int getStudyYears(){
        int years_Studied = ConstantValues.CURRENT_YEAR - getStartYear();
        if (hasGraduated()){
            years_Studied = getGraduationYear() - getStartYear();
        }
        return years_Studied;
    }

   public String getIdString() {
        return "Student id:" + getId();
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
        return degrees.get(i).getCredits();
    }

    /*This method will return the given degree's mandatory courses credits. Based on the index(0=bachelor, 1= master).*/
    private double getMandatoryCredits(int i){
       return degrees.get(i).getCreditsByType(1);
    }

    /*This method will return the given degree's title. Based on the index(0=bachelor, 1= master).*/
    private String getDegreeThesisTitle(int i){
        return degrees.get(i).getTitleOfThesis();
    }

// This method will return the given degrees GPA based on the index (0=bachelor, 1= master).
    private double getDegreeGPA(int i){
        double result = degrees.get(i).getGPA(2).get(2);
        return Math.round(result * 100.0) / 100.0;
    }

    //this method will calculate the total GPA for all of the student's degrees.
    private double getTotalGPA(){
        double totalSum = 0.0;
        double totalCount = 0.0;
        double totalAverage;
        for(Degree degree: degrees){
            totalSum += degree.getGPA(2).get(0);
            totalCount += degree.getGPA(2).get(1);
        }
        totalAverage = totalSum/totalCount;
        return Math.round(totalAverage * 100.0) / 100.0;
    }

    // This method checks the Bachelor studies of a student. The method is used in the toString method only when an student cannot graduate.
    private String checkBScStudies(){
        if(getDegreeCredits(0) < ConstantValues.BACHELOR_CREDITS){
            return "\n\t\t Missing bachelor credits " + (ConstantValues.BACHELOR_CREDITS - getDegreeCredits(ConstantValues.BACHELOR_TYPE)) + " (" +getDegreeCredits(ConstantValues.BACHELOR_TYPE)+"/"+ConstantValues.BACHELOR_CREDITS+") "
                    +"\n\t\t All mandatory bachelor credits completed ("+getMandatoryCredits(ConstantValues.BACHELOR_TYPE)+"/"+ConstantValues.BACHELOR_MANDATORY+")";

        } else if(getMandatoryCredits(0) < ConstantValues.BACHELOR_MANDATORY){
            return "\n\t\t Total bachelor credits completed ("+getDegreeCredits(ConstantValues.BACHELOR_TYPE)+"/"+ConstantValues.BACHELOR_CREDITS+")"+
                    "\n\t\t Missing mandatory bachelor credits "+ (ConstantValues.BACHELOR_MANDATORY - getMandatoryCredits(ConstantValues.BACHELOR_TYPE)) +
                    " (" +getMandatoryCredits(ConstantValues.BACHELOR_TYPE)+"/"+ConstantValues.BACHELOR_MANDATORY+") ";
        }
        return "\n\t\t Total bachelor credits completed ("+getDegreeCredits(ConstantValues.BACHELOR_TYPE)+"/"+ConstantValues.BACHELOR_CREDITS+")"+
                "\n\t\t All mandatory bachelor credits completed ("+getMandatoryCredits(ConstantValues.BACHELOR_TYPE)+"/"+ConstantValues.BACHELOR_MANDATORY+")";
    }

    //This method checks the Master studies of an student. Used in the toString method only when a student cannot graduate.
    private String checkMscStudies(){
    if(getDegreeCredits(ConstantValues.MASTER_TYPE) < ConstantValues.MASTER_CREDITS){
        return "\n Missing master's credits " + (ConstantValues.MASTER_CREDITS - getDegreeCredits(ConstantValues.MASTER_TYPE)) + " (" +getDegreeCredits(ConstantValues.MASTER_TYPE)+"/"+ConstantValues.MASTER_CREDITS+") "
                +"\n\t\t All mandatory master credits completed ("+getMandatoryCredits(ConstantValues.MASTER_TYPE)+"/"+ConstantValues.MASTER_MANDATORY+")";

    } else if(getMandatoryCredits(ConstantValues.BACHELOR_TYPE) < ConstantValues.MASTER_MANDATORY){
        return "\n\t\tTotal master credits completed ("+getDegreeCredits(ConstantValues.MASTER_TYPE)+"/"+ConstantValues.MASTER_CREDITS+")"+
                "\n\t\t Missing mandatory master's credits "+
                (ConstantValues.MASTER_MANDATORY - getMandatoryCredits(ConstantValues.MASTER_TYPE)) + " (" +getMandatoryCredits(ConstantValues.MASTER_TYPE)+"/"+ConstantValues.MASTER_MANDATORY+") ";
    }
    return "\n\t\tTotal master credits completed ("+getDegreeCredits(ConstantValues.MASTER_TYPE)+"/"+ConstantValues.MASTER_CREDITS+")"+
            "\n\t\t All mandatory master credits completed ("+getMandatoryCredits(ConstantValues.MASTER_TYPE)+"/"+ConstantValues.MASTER_MANDATORY+")";
}

//this method provides the output for an student
    public String toString(){
        String myString = "";
        myString += getIdString();
        myString+= "\n\tFirst name: "+getFirstName()+", Last name: "+getLastName();
        myString+="\n\tDate of birth: " +getBirthDate();

        //here we check if the student has graduated and format the string according to it
        if (hasGraduated()) {
            myString += "\n\t Status: The student has graduated in " + getGraduationYear() +
                    "\n\t Start Year : " + getStartYear() + " (studies have lasted for " + (getGraduationYear() - getStartYear()) + "years)" +
                    "\n\t Total credits: " + getTotalCredits() + " (GPA = " + getTotalGPA() + ")" +
                    "\n\t Bachelor credits: " + getDegreeCredits(ConstantValues.BACHELOR_TYPE) +
                     checkBScStudies() +
                    "\n\t\t GPA of Bachelor Studies: " + getDegreeGPA(ConstantValues.BACHELOR_TYPE) +
                    "\n\t\t Title of BSc Thesis: " + "\"" + getDegreeThesisTitle(0) + "\"" +

                    "\n Master credits: " + getDegreeCredits(ConstantValues.MASTER_TYPE) +
                     checkMscStudies() +
                    "\n\t\t GPA of Master Studies: " + getDegreeGPA(ConstantValues.MASTER_TYPE) +
                    "\n\t\t Title of MSc Thesis: " + "\"" + getDegreeThesisTitle(1) + "\"";
        } else {
            myString += "\n\t Status: The student has not graduated, yet" +
                    "\n\t Start Year : " + getStartYear() + " (studies have lasted for " + (ConstantValues.CURRENT_YEAR - getStartYear()) + " years)" +
                    "\n\t Total credits: " + getTotalCredits()
                    +" (GPA = " + getTotalGPA() + ")"
                    +"\n Bachelor credits: " + getDegreeCredits(0)
                    +checkBScStudies()
                    + "\n\t\t GPA of Bachelor Studies: " + getDegreeGPA(0)
                    + "\n\t\t Title of BSc Thesis: " + "\"" + getDegreeThesisTitle(0) + "\""
                    + "\n Master credits: " + getDegreeCredits(ConstantValues.MASTER_TYPE)
                    +checkMscStudies()
                    + "\n\t\t GPA of Master Studies: " + getDegreeGPA(ConstantValues.MASTER_TYPE)
                    + "\n\t\t Title of MSc Thesis: " + "\"" + getDegreeThesisTitle(ConstantValues.MASTER_TYPE) + "\"";
        }
        return myString;
    }
}
