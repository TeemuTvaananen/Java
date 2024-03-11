package dev.m3s.programming2.homework2;
public class Main {
    public static void main(String[] args) {
        Student student = new Student();
        //Courses
        Course course1 = new Course("Programming 1", 811104, 'P', 1, 1, 5.0, true);
        Course course2 = new Course("All kinds of basic studies", 112233, 'P', 1, 1, 5.0, true);
        Course course3 = new Course("More basic studies", 223344, 'a', 1, 2, 45.0, true);
        Course course4 = new Course("Even more basic studies",556677,'a',1, 3, 50.0, true );
        Course course5 = new Course("Final basic studies", 123123, 'A', 1, 4, 50.5, true);
        Course course6 = new Course("Programming 2", 616161, 'A', 1, 3, 25.0, true);
        Course course7 = new Course("All kinds of master studies", 717171, 'P', 0, 2, 45.0, true);
        Course course8 = new Course("More master studies", 818181, 'A', 1, 1, 25.0, true);
        Course course9 = new Course("Even more master studies", 919191, 'S', 1, 3, 20.0, true);
        Course course10 = new Course("Extra master studies", 666666, 'S', 0, 5, 8.0, false);
        Course course11 = new Course("Final master studies", 888888, 'S', 1, 5, 18.0, false);
        //StudentCourses
        StudentCourse studentCourse1 = new StudentCourse(course1, 1, 2013);
        StudentCourse studentCourse2 = new StudentCourse(course2, 1, 2014);
        StudentCourse studentCourse3 = new StudentCourse(course3, 1, 2015);
        StudentCourse studentCourse4 = new StudentCourse(course4, 4, 2016);
        StudentCourse studentCourse5 = new StudentCourse(course5, 5, 2017);
        StudentCourse studentCourse6 = new StudentCourse(course6, 1, 2018);
        StudentCourse studentCourse7 = new StudentCourse(course7, 1, 2019);
        StudentCourse studentCourse8 = new StudentCourse(course8, 2, 2021);
        StudentCourse studentCourse9 = new StudentCourse(course9, 0, 2021);
        StudentCourse studentCourse10 = new StudentCourse(course10, 'A', 2021);
        StudentCourse studentCourse11 = new StudentCourse(course11, 'f', 2022);


        //StudentCourse Arrays
        StudentCourse[] bachelor = { studentCourse1, studentCourse2, studentCourse3, studentCourse4, studentCourse5 };
        StudentCourse[] master = {studentCourse6, studentCourse7, studentCourse8, studentCourse9, studentCourse10, studentCourse11};
        Degree degree1 = new Degree();
        Degree degree2 = new Degree();

        degree1.addStudentCourses(bachelor);
        degree2.addStudentCourses(master);


        degree1.setDegreeTitle("Bachelor of Science");
        degree2.setDegreeTitle("Master of Science");

        degree1.setTitleOfThesis("Bachelor thesis title");
        degree2.setTitleOfThesis("Master thesis title");



        student.addCourses(0, bachelor);
        student.addCourses(1, master);
        student.setStartYear(2001);
        student.setGraduationYear(2020);
        student.setFirstName("Donald");
        student.setLastName("Duck");
        student.setBirthDate("230498-045T");
        student.setTitleOfThesis(0, "Christmas - The most wonderful time of the year");
        student.setTitleOfThesis(1, "Dreaming of a White Christmas");

        System.out.println(student);

    }
}
