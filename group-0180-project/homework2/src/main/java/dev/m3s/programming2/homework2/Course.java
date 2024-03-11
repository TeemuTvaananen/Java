package dev.m3s.programming2.homework2;
/* This class handles all the information regarding a single course. For example:
[811104P ( 5.00 cr), "Programming 1". Mandatory, period: 1.]
 */
/*All the attributes needed*/
public class Course {
    private String name = ConstantValues.NO_NAME;
    private String courseCode = ConstantValues.NO_NAME;
    private Character courseBase;
    private int courseType;
    private int period;
    private double credits = ConstantValues.MIN_CREDITS;
    private boolean numericGrade;

    /*Constructors*/
    public Course() {

    }

    public Course(String name, final int code, Character courseBase, final int type, final int period, final double credits, boolean numericGrade) {
        setName(name);
        setCourseCode(code, courseBase);
        setCourseType(type);
        setPeriod(period);
        setCredits(credits);
        setNumericGrade(numericGrade);
    }

    public Course(Course course) {
        this.name = course.name;
        this.courseCode = course.courseCode;
        this.courseBase = course.courseBase;
        this.courseType = course.courseType;
        this.period = course.period;
        this.credits = course.credits;
        this.numericGrade = course.numericGrade;
    }

    /*All the methods.*/
    public String getName() {
        return name;
    }

    /*This method will set the course's name if the given course is not null and the name is not empty.
     * Utilized the isEmpty method from: https://www.javatpoint.com/java-string-isempty */
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }

    /*This method returns the given courses type in a String format. Either "Optional" or "Mandatory" based on the courses type.*/
    public String getCourseTypeString() {
        String courseType = "";
        if (getCourseType() == 0) {
            courseType = "Optional";
        } else if (getCourseType() == 1) {
            courseType = "Mandatory";
        }
        return courseType;
    }

    /*Getter and setter for the course's type. Type is only set if the given value is valid(0 or 1)*/
    public int getCourseType() {
        return courseType;
    }

    public void setCourseType(final int type) {
        if (type == 0 || type == 1) {
            this.courseType = type;
        }
    }

    /*Getter and setter for the course code. Course code is only set id the numeric part of it is higher than 0 and less than 1000000*/
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(final int courseCode, Character courseBase) {
        if (courseCode > 0 && courseCode < 1000000) {
            if ((courseBase == 'A' || courseBase == 'P' || courseBase == 'S')
                    || (courseBase == 'a' || courseBase == 'p' || courseBase == 's')) {
                this.courseBase = Character.toUpperCase(courseBase);
                this.courseCode = String.valueOf(courseCode) + this.courseBase;
            }
        }
    }

    /*This method returns the courseBase*/
    public Character getCourseBase() {
        return courseBase;
    }

    /*Getter and setter for the period. Period is only set if the given value is valid.*/
    public int getPeriod() {
        return period;
    }

    public void setPeriod(final int period) {
        if (period >= ConstantValues.MIN_PERIOD && period <= ConstantValues.MAX_PERIOD) {
            this.period = period;
        }
    }

    /*Getter and setter for the credits. Credits are only set if the value is valid.*/
    public double getCredits() {
        return credits;
    }

    private void setCredits(final double credits) {
        if (credits >= ConstantValues.MIN_CREDITS && credits <= ConstantValues.MAX_COURSE_CREDITS) {
            this.credits = credits;
        }
    }

    /* This method returns the boolean value: numericGreade.*/
    public boolean isNumericGrade() {
        return numericGrade;
    }

    /* This method sets the numericGrade.*/
    public void setNumericGrade(boolean numericGrade) {
        this.numericGrade = numericGrade;
    }

    /* This method will output the object Course. For example:
    [811104P ( 5.00 cr), "Programming 1". Mandatory, period: 1.]
     */
    public String toString() {
        return new StringBuilder().append("[").append(courseCode).append(" ( ").append(credits).append("0 cr), ").append("\"").append(name).append("\". ").append(getCourseTypeString()).append(", ").append("period: ").append(period).append(".]").toString();
    }
}

