package dev.m3s.programming2.homework3;

public abstract class Employee extends Person implements Payment {
    // the needed variables
    private String empId;
    private int startYear;
    private Payment payment;
    //constructor for the employee, sets the first and last name by using the person class' constructor
    //also sets the empID for the employee and the staryear to the current year.
    public Employee(String lname, String fname) {
        super(lname, fname);
        this.empId = getEmployeeIdString() + getRandomId(ConstantValues.MIN_EMP_ID, ConstantValues.MAX_EMP_ID);
        setStartYear(ConstantValues.CURRENT_YEAR);
    }
    // this method returns the whole empId in an string
    public String getIdString() {
        return empId;
    }
    // getter and setter for the startYear, year is set only if it is in the right range
    public int getStartYear() {
        return startYear;
    }
    public void setStartYear(int startYear) {
        if (startYear > 2000 && startYear <= ConstantValues.CURRENT_YEAR) {
            this.startYear = startYear;
        }
    }
// getter and setter for the payment, sets the payment only if it is not null
    public Payment getPayment() {
        return payment;
    }
    public void setPayment(Payment payment) {
        if (payment != null) {
            this.payment = payment;
        }
    }
    //this method calculates the payment for the employee, only calculated if the given payment is not null
    @Override
    public double calculatePayment() {
        double paymentAmount = 0.0;
        if (payment != null) {
            paymentAmount = payment.calculatePayment();
        }
        return paymentAmount;
    }
    // an abstract method, returns the employeeIdString
    abstract protected String getEmployeeIdString();
}
