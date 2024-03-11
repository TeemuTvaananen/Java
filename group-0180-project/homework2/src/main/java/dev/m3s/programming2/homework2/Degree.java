package dev.m3s.programming2.homework2;

/* In this class we format a Degree for a student with the help of classes studentcourse and course*/


/* All the attributes that a degree will need*/
public class Degree {
    private static final int MAX_COURSES = 50;
    private int count;
    private String degreeTitle = ConstantValues.NO_TITLE;
    private String titleOfThesis = ConstantValues.NO_TITLE;
    private StudentCourse[] myCourses = new StudentCourse[MAX_COURSES];


    /*All the methods for the degree-class*/

    /*This method will return the courses for the degree.*/
    public StudentCourse[] getCourses(){
        return myCourses;
    }
    /*This method sets the courses if they can be added utilizing the method addStudentCourse.*/
    public void addStudentCourses(StudentCourse[] courses){
        if(courses != null){
            for(StudentCourse course : courses){
                if(course != null){
                    addStudentCourse(course);
                }
            }
        }
    }
/*This method will set an individual course if it is not null and the count is less than the maximum size of the StudentCourse array.*/
    public boolean addStudentCourse(StudentCourse course){
        boolean result = false;
        if(course != null && count < MAX_COURSES){
            //here we set the course to the array and also increment the count with every course which can be set
            myCourses[count] = course;
            count++;
            result= true;
        }
        return result;
    }

    // Getter and setter for the Degree's title. Title can be set if the given title name is not null.
    public String getDegreeTitle() {
        return degreeTitle;
    }
    public void setDegreeTitle(String degreeTitle) {
        if(degreeTitle != null) {
            this.degreeTitle = degreeTitle;
        }
    }

    // Getter and Setter for the Title of the degree's thesis. Title can be set only if the given name is not null.
    public String getTitleOfThesis() {
        return titleOfThesis;
    }
    public void setTitleOfThesis(String titleOfThesis) {
        if(titleOfThesis != null){
        this.titleOfThesis = titleOfThesis;
        }
    }

    /* This method returns the total amount of credits for the given base (A, P or S)*/
    public double getCreditsByBase(Character base){
        double result = 0;
        for(StudentCourse course : myCourses){
            if(course != null){
                if(course.getCourse().getCourseBase()==base && isCourseCompleted(course)){
                    result += course.getCourse().getCredits();
                }
            }

        }
        return result;
    }

   /*This method returns the total amount of credits for the given course type(1= mandatory or 0= optional)*/
    public double getCreditsByType(final int courseType){
        double result = 0;
        for (StudentCourse course : myCourses){
            if(course != null){
                if(course.getCourse().getCourseType()== courseType && isCourseCompleted(course)){
                    result += course.getCourse().getCredits();
            }
            }
        }
        return result;
    }
    /*This method returns all the credits for the completed courses.*/
    public double getCredits(){
        double result = 0;
        for(StudentCourse course : myCourses){
            if(course != null){
                if(isCourseCompleted(course))
                        result += course.getCourse().getCredits();
            }
        }
        return result;
    }

    /*This method will check that the given course is completed utilizing the isPassed method from the StudentCourse-class.*/
    private boolean isCourseCompleted(StudentCourse c){
        return c != null && c.isPassed();
    }

    /*This method will print all the courses for the degree.*/
    public void printCourses() {
        for (StudentCourse course : myCourses) {
            if (course != null) {
                System.out.println(course);
            }
        }
    }

/* This method will output the degree. First we construct a new Stringbuilder for all the courses and utilize it in the latter Stringbuilder which is for the degree itself. We will
* only place the course in the output if it is not null.
* The output will be as follows for example:
*
* Degree [Title: "Bachelor of Science" (courses: 5)
Thesis title: "Christmas - The most wonderful time of the year"
1. [811104P ( 5.00 cr), "Programming 1". Mandatory, period: 1.] Year: 2013, Grade: 1.]
2. [112233P (45.00 cr), "All kinds of basic studies". Mandatory, period: 2.] Year: 2014, Grade: 1.]
3. [223344A (50.50 cr), "More basic studies". Mandatory, period: 1.] Year: 2015, Grade: 1.]
4. [556677A (50.00 cr), "Even more basic studies". Optional, period: 3.] Year: 2016, Grade: 4.]
5. [123123A (50.50 cr), "Final basic studies". Mandatory, period: 4.] Year: 2017, Grade: 5.]]*/
public String toString(){
        int i = 1;
        StringBuilder printCourses = new StringBuilder();
        for(StudentCourse course: myCourses){
            if(course != null){
                printCourses.append("\n\t ").append(i).append(". ").append(course);
                i++;
            }
        }

    return new StringBuilder().append("Degree [Title: ").append("\"").append(getDegreeTitle()).append("\"").append(" (courses: ").append(count).append(")").append("\n\tThesis title: ").append("\"").append(getTitleOfThesis()).append("\"").append(printCourses).append("]").toString();
    }
}
