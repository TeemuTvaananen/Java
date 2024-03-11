package dev.m3s.programming2.homework3;
// class for the hourbased payment, implements the interface Payment
public class HourBasedPayment implements Payment {
    // the needed variables
 private double eurosPerHour;
 private double hours;

    // this method will return the set euros per hour
    public double getEurosPerHour() {
        return eurosPerHour;
    }
    // this method sets the euros per hour
    public void setEurosPerHour(double eurosPerHour) {
        if(eurosPerHour > 0.0) this.eurosPerHour = eurosPerHour;
    }
    //this method will return the hours
    public double getHours() {
        return hours;
    }

    //this method sets the hours
    public void setHours(double hours) {
        if(hours > 0.0) this.hours = hours;
    }
    // in this method we calculate the hourbased pay by multiplying the hours by euros per hour
    public double calculatePayment() {
        return getHours()* getEurosPerHour();
    }
}
