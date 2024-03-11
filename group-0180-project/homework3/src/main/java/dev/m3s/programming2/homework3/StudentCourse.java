package dev.m3s.programming2.homework3;
/*In this class we handle the StudentCourse*/

/*Attributes for the studentcourse:
 * the completed course
 * Number for the graded course
 * Year when the course was completed*/
public class StudentCourse {
    private Course course;
    private int gradeNum;
    private int yearCompleted;

    /*Constructors for the studentcourse.*/
    public StudentCourse(){

    }
    public StudentCourse( Course course, final int gradeNum, final int yearCompleted ){
        setCourse(course);
        setGrade(gradeNum);
        setYear(yearCompleted);
    }
    /* Methods*/
    /* Getter and setter for the course*/
    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }

    /*Setter for the grade. This method first checks if the given value is valid using the checkGradeVadility method.
     * It also sets the year if it hasd not been set.*/
    protected void setGrade(int gradeNum){
        if(checkGradeValidity(gradeNum)){
            this.gradeNum = gradeNum;
        }
        if(getYear()==0){
            this.yearCompleted = ConstantValues.CURRENT_YEAR;
        }
    }
    /*This returns the gradenum*/
    public int getGradeNum() {
        return gradeNum;
    }
    /*This method willheck whether the
     * given value is valid (0-5 or ‘F’ or ‘A’). Returns true if the value is valid. */
    private boolean checkGradeValidity(final int gradeNum){
        if(course.isNumericGrade()){
            return gradeNum >= ConstantValues.MIN_GRADE && gradeNum <= ConstantValues.MAX_GRADE;
        }else{
            return gradeNum == ConstantValues.GRADE_ACCEPTED || gradeNum == ConstantValues.GRADE_FAILED;
        }
    }
    /*Method returns true if the gradenum is not 0 or 'F'*/
    public boolean isPassed() {
        return gradeNum > ConstantValues.MIN_GRADE && gradeNum != ConstantValues.GRADE_FAILED;
    }

    /*Getter and setter for the Year. Year can be set only if it is valid (after the year 2000 and not later than the current year).*/
    public int getYear() {
        return yearCompleted;
    }
    public void setYear(int year) {
        if(year > 2000 && year <= ConstantValues.CURRENT_YEAR){
            this.yearCompleted = year;
        }
    }

    /*My own method. It checks the gradenumber for the output and returns the matching grade for the number or letter.  */
    private String getGrade(){
        int num = getGradeNum();
        return switch (num) {
            case ConstantValues.MIN_GRADE  -> "\"Not graded\"";
            case ConstantValues.GRADE_FAILED, 'f' -> "F";
            case ConstantValues.GRADE_ACCEPTED, 'a' -> "A";
            case 1,2,3,4,5 -> Integer.toString(gradeNum);
            default -> "Unexpected value: " + gradeNum;
        };
    }


    /*This method will output the studentCourse utilizing the Course-class. For example: [811104P ( 5.00 cr), "Programming 1". Mandatory, period: 1.] Year: 2013, Grade: 1.]
     */
    public String toString(){
        return course.toString() + " Year: " + yearCompleted + ", Grade: " + getGrade() + ".]";
    }
}

