package dev.m3s.programming2.homework3;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ResponsibleTeacher teacher1 = new ResponsibleTeacher("Mouse", "Mickey");
        teacher1.setBirthDate("230498-045T");
        MonthlyPayment salary1 = new MonthlyPayment();
        salary1.setSalary(765.85);
        teacher1.setPayment(salary1);

        AssistantTeacher teacher2 = new AssistantTeacher("The Dog", "Goofy");
        teacher2.setBirthDate("141200A2315");
        HourBasedPayment salary2 = new HourBasedPayment();
        salary2.setHours(11.0);
        salary2.setEurosPerHour(3.5);
        teacher2.setPayment(salary2);

        Student student1 = new Student("Duck", "Donald");
        Course course1 = new Course("Programming 1", 811104, 'P', 1, 1, 5.0, true);
        Course course2 = new Course("All kinds of basic studies", 112233, 'P', 1, 2, 45.0, true);
        Course course3 = new Course("More basic studies", 223344, 'a', 1, 1, 50.5, true);
        Course course4 = new Course("Even more basic studies", 556677, 'a', 0, 3, 50.0, true);
        Course course5 = new Course("Final basic studies", 123123, 'A', 1, 4, 50.5, true);
        Course course6 = new Course("Programming 2", 616161, 'A', 1, 3, 25.0, true);
        Course course7 = new Course("All kinds of master studies", 717171, 'P', 0, 2, 45.0, true);
        Course course8 = new Course("More master studies", 818181, 'A', 1, 1, 20.0, true);
        Course course9 = new Course("Even more master studies", 919191, 'S', 1, 3, 5.0, true);
        Course course10 = new Course("Extra master studies", 666666, 'S', 0, 5, 8.0, false);
        Course course11 = new Course("Final master studies", 888888, 'S', 1, 5, 18.0, false);

        DesignatedCourse dcourse1 =new DesignatedCourse(course3, true, 2023);
        DesignatedCourse dcourse2 =new DesignatedCourse(course4, false, 2023);
        DesignatedCourse dcourse3 =new DesignatedCourse(course10, false, 2022);
        DesignatedCourse dcourse4 =new DesignatedCourse(course11, false, 2023);

        ArrayList<DesignatedCourse>list1 =new ArrayList<>();
        list1.add(dcourse1);
        list1.add(dcourse2);
        list1.add(dcourse3);
        list1.add(dcourse4);

        teacher1.setCourses(list1);
        teacher2.setCourses(list1);
        StudentCourse studentCourse1 = new StudentCourse(course1, 2, 2013);
        StudentCourse studentCourse2 = new StudentCourse(course2, 2, 2014);
        StudentCourse studentCourse3 = new StudentCourse(course3, 2, 2015);
        StudentCourse studentCourse4 = new StudentCourse(course4, 4, 2016);
        StudentCourse studentCourse5 = new StudentCourse(course5, 5, 2017);
        StudentCourse studentCourse6 = new StudentCourse(course6, 2, 2018);
        StudentCourse studentCourse7 = new StudentCourse(course7, 3, 2019);
        StudentCourse studentCourse8 = new StudentCourse(course8, 2, 2021);
        StudentCourse studentCourse9 = new StudentCourse(course9, 3, 2021);
        StudentCourse studentCourse10 = new StudentCourse(course10, 'A', 2021);
        StudentCourse studentCourse11 = new StudentCourse(course11, 'f', 2022);
        ArrayList<StudentCourse> bachelor = new ArrayList<>();
        bachelor.add(studentCourse1);
        bachelor.add(studentCourse2);
        bachelor.add(studentCourse3);
        bachelor.add(studentCourse4);
        bachelor.add(studentCourse5);

        ArrayList<StudentCourse> master = new ArrayList<>();
        master.add(studentCourse6);
        master.add(studentCourse7);
        master.add(studentCourse8);
        master.add(studentCourse9);
        master.add(studentCourse10);
        master.add(studentCourse11);

        Degree degree1 = new Degree();
        Degree degree2 = new Degree();
        degree1.addStudentCourses(bachelor);
        degree2.addStudentCourses(master);

        System.out.println(teacher1);
        System.out.println(teacher2);  degree1.setDegreeTitle("Bachelor of Science");
        degree2.setDegreeTitle("Master of Science");
        degree1.setTitleOfThesis("Bachelor thesis title");
        degree2.setTitleOfThesis("Masters thesis title");

        student1.addCourses(0, bachelor);
        student1.addCourses(1, master);
        student1.setStartYear(2001);
        student1.setGraduationYear(2020);
        student1.setBirthDate("061002A563L");
        student1.setTitleOfThesis(0, "Christmas - The most wonderful time of the year");
        student1.setTitleOfThesis(1, "Dreaming of a White Christmas");
        System.out.println(student1);
        student1.setGraduationYear(2020);
    }
}
