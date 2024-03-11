package dev.m3s.programming2.homework3;
// class for the monthly payment
public class MonthlyPayment implements Payment{
    // the needed variable
    private double salary;

    //this method returns the salary
    public double getSalary() {
            return salary;
    }
    //this method sets the salary
    public void setSalary(double salary) {
        if(salary > 0.0) this.salary = salary;
    }
    // this method returns the salary
    public double calculatePayment() {
        return salary;
    }
}
