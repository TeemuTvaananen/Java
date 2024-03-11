package dev.m3s.programming2.homework3;
public class DesignatedCourse {
    //the needed variables
    private Course course;
    private boolean responsible;
    private int year;

//functionless constructor
    public DesignatedCourse(){ }

    //constructor which sets the course, responsible, and the year
    public DesignatedCourse(Course course, boolean resp, int year){
        setCourse(course);
        setResponsible(resp);
        setYear(year);
    }
    // getter and setter for the course, only set if the given course is not null
    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        if(course != null){
            this.course = course;
        }
    }
    // this method sets the responsible
    public void setResponsible(boolean responsible) {
        this.responsible = responsible;
    }
    // this method returns the responsible
    public boolean isResponsible() {
        return responsible;
    }
    public boolean getResponsible(){
        return responsible;
    }
    // getter and setter for the year, year is only set if the given year is not null
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        if(year >= 2000 && year <= ConstantValues.CURRENT_YEAR+1){
        this.year = year;
        }
    }
    // this method provides output for the course
    public String toString(){
        return "[course=" + course.toString() + ", year=" + getYear() + "]";
    }
}
