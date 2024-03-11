package dev.m3s.programming2.homework1;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Student {
    private String firstName = ConstantValues.NO_NAME;
    private String lastName = ConstantValues.NO_NAME;
    private String titleOfMastersThesis = ConstantValues.NO_TITLE;
    private String titleOfBachelorThesis = ConstantValues.NO_TITLE;
    private double bachelorCredits;
    private double masterCredits;
    private int startYear = ConstantValues.currentYear;
    private int graduationYear;
    private final Random studentId = new Random();
    private int id;
    private String birthDate = "\"" + ConstantValues.NO_BIRTHDATE + "\"";

    //constructors
    public Student() {
        id = getRandomId();
    }

    public Student(String lname, String fname) {
        firstName = null != fname ? fname : ConstantValues.NO_NAME;
        lastName = null != lname ? lname : ConstantValues.NO_NAME;
        id = getRandomId();
    }

    //  ***methods that work***
    private int getRandomId() {
        return studentId.nextInt(ConstantValues.MAX_ID) + ConstantValues.MIN_ID;
    }
    public int getId() {
        return id;
    }

    public void setId(final int id) {
        if(id >= ConstantValues.MIN_ID && id <= ConstantValues.MAX_ID){
        this.id = id;
        }
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(firstName != null){
        this.firstName = firstName;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(lastName != null){
        this.lastName = lastName;
        }
    }
    public double getBachelorCredits() {
        return bachelorCredits;
    }

    public void setBachelorCredits(final double bachelorCredits) {
        if (bachelorCredits >= ConstantValues.MIN_CREDIT && bachelorCredits <= ConstantValues.MAX_CREDITS) {
            this.bachelorCredits = bachelorCredits;
        } else {
            System.out.println("Error, invalid amount of credits");
        }
    }
    public double getMasterCredits() {
        return masterCredits;
    }

    public void setMasterCredits(double masterCredits) {
        if (masterCredits >= ConstantValues.MIN_CREDIT && masterCredits <= ConstantValues.MAX_CREDITS) {
            this.masterCredits = masterCredits;
        } else {
            System.out.println("Error, invalid amount of Master credits!");
        }
    }
    public void setTitleOfMastersThesis(String title) {
        if(title != null){
        this.titleOfMastersThesis = title; }
    }
    public String getTitleOfMastersThesis() {
        return titleOfMastersThesis;
    }

    public void setTitleOfBachelorThesis(String title) {
        if(title != null) {
            this.titleOfBachelorThesis = title;
        }
    }
    public String getTitleOfBachelorThesis() {
        return titleOfBachelorThesis;
    }
    public void setStartYear(final int startYear) {
        if(startYear>2000 && startYear <= ConstantValues.currentYear ){
        this.startYear = startYear;
        }
    }
    public int getStartYear() {
        return startYear;
    }

    public int getGraduationYear() {
        return graduationYear;

    }
    private boolean canGraduate() {
        boolean ans = false;
        if(getBachelorCredits() >= ConstantValues.BACHELOR_CREDITS && getMasterCredits() >= ConstantValues.MASTER_CREDITS){
            if(getTitleOfBachelorThesis()!= ConstantValues.NO_TITLE && getTitleOfMastersThesis()!= ConstantValues.NO_TITLE)
                ans = true;
        }
        return ans;
    }

    public String setGraduationYear(final int graduationYear) {
       if(canGraduate() == false){
           return "Check the required studies";
    } else if(graduationYear>ConstantValues.currentYear || graduationYear < startYear){
        return "Check graduation year";
    } else{
        this.graduationYear = graduationYear;
        return "Ok";
    }
    }

    public boolean hasGraduated() {
        return getGraduationYear() != 0;
    }

    public int getStudyYears(){
        int years_Studied = ConstantValues.currentYear - getStartYear();
        if (hasGraduated()){
            years_Studied = getGraduationYear() - getStartYear();
        }
        return years_Studied;
    }

    
public String setPersonId(final String personId) {
        String result;
        char c = personId.charAt(6);
        if (checkPersonIDNumber(personId) && checkBirthdate(personId)) {

            if (checkValidCharacter(personId)) {

                switch (c) {
                    case 'A' ->
                            this.birthDate = personId.substring(0, 2) + "." + personId.substring(2, 4) + ".20" + personId.substring(4, 6);
                    case '-' ->
                            this.birthDate = personId.substring(0, 2) + "." + personId.substring(2, 4) + ".19" + personId.substring(4, 6);
                    case '+' ->
                            this.birthDate = personId.substring(0, 2) + "." + personId.substring(2, 4) + ".18" + personId.substring(4, 6);
                    default -> {
                    }
                }
                    result = "Ok";

            } else {
                result = ConstantValues.INCORRECT_CHECKMARK;
            }
        } else {
            result = ConstantValues.INVALID_BIRTHDAY;
        }
        return result;
    }

    private boolean checkPersonIDNumber(final String personId){
        boolean result = false;
        if(personId.length() == 11)
            if((personId.charAt(6) == '+') || (personId.charAt(6) == '-') || (personId.charAt(6) == 'A')) {
                result = true;
            }
      return result;
    }


    private boolean checkLeapYear(int year){
        return (year % 100 != 0 && year % 4 == 0) || year % 400 == 0;
    }
    private boolean checkBirthdate(final String date){
        boolean result = false;
        int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
       if (date.length() == 11) {
            int day = Integer.parseInt(date.substring(0, 2));
            int month = Integer.parseInt(date.substring(2, 4));
            int year = Integer.parseInt(date.substring(4, 6));
          
            if (checkLeapYear(year) == true) {
                monthDays[1] = 29;
            }

            if (year >= 0) {
                if (month >= 1 && month <= 12) {
                    if (monthDays[month-1] >= day && day >= 1) {
                        result = true;
                    }
                }
            } 
        } else if (date.length() == 10) {
            int day = Integer.parseInt(date.substring(0, 2));
            int month = Integer.parseInt(date.substring(3, 5));
            int year = Integer.parseInt(date.substring(6, 10));

            if (checkLeapYear(year) == true) {
                monthDays[1] = 29;
            }

            if (year >= 0) {
                if (month >= 1 && month <= 12) {
                    if (monthDays[month-1] >= day && day >= 1) {
                        result = true;
                    }
                }
            }
        }

        return result;
    }
    private boolean checkValidCharacter(final String personID){
        char[] validCharacters = {'0','1','2','3','4','5','6','7','8','9',
        'A','B','C','D','E','F','H','J','K','L','M','N', 'P','R','S','T','U','V','W','X','Y'};
        boolean ans = false;
        String num_part = personID.substring(0,6) + personID.substring(7,10);
        double numbers = (Double.parseDouble(num_part) / 31);
        //get the fractional part of a decimal number

        double fractional = numbers % 1;
        fractional *= 31;
        long x = Math.round(fractional);
       if( personID.charAt(10) == validCharacters[(int) x]){
           ans = true;
       }
        return ans;
    }


    public String toString(){
        String myString ="\nStudent id: " + getId() + "\n" + " FirstName: " + getFirstName() + ", LastName: " + getLastName() + "\n" + " Date of birth: " + birthDate + "\n";

        if(hasGraduated()){
            myString += " Status: The Student has graduated in " + getGraduationYear() + "\n";
            myString += " StartYear: " + getStartYear() + "(studies have lasted for " + (getGraduationYear() - getStartYear()) + " years)" + "\n";
            myString += " BachelorCredits: " + getBachelorCredits() + " ==> All required bachelors credits completed " +
                    "(" + getBachelorCredits() + "/" + ConstantValues.BACHELOR_CREDITS + ")" + "\n";
            myString += " TitleOfBachelorsThesis: " + "\"" +getTitleOfBachelorThesis() + "\"";
            myString += " MasterCredits: " + getMasterCredits() + " ==> All required masters credits completed " +
                    "(" + getMasterCredits() + "/" + ConstantValues.MASTER_CREDITS + ")" + "\n";
        }
        else{
            myString += " Status: The Student has not graduated, yet " + "\n";
            myString += " StartYear: " + getStartYear() + " (studies have lasted for " + (ConstantValues.currentYear - getStartYear()) + " years) " + "\n";

            //This checks if Student has completed all of the required bachelors credits
            if(getBachelorCredits() >= ConstantValues.BACHELOR_CREDITS) {
                myString += " BachelorCredits: " + getBachelorCredits() + " ==> All required bachelors credits completed " +
                        "(" + getBachelorCredits() + "/" + ConstantValues.BACHELOR_CREDITS + ")" + "\n";
                myString += " TitleOfBachelorsThesis: " + "\"" + getTitleOfBachelorThesis()+ "\"" +"\n";

            } else {
                myString += " BachelorCredits: " + getBachelorCredits() + " ==> Missing bachelors credits " + (ConstantValues.BACHELOR_CREDITS - getBachelorCredits()) +
                        "(" + getBachelorCredits() + "/" + ConstantValues.BACHELOR_CREDITS + ")" + "\n";
                myString += " TitleOfBachelorsThesis: " + "\"" + getTitleOfBachelorThesis() + "\"";
            }
            //this checks if student has completed all of the required masters
            if(getMasterCredits() >= ConstantValues.MASTER_CREDITS ){
                myString += " MasterCredits: " + getMasterCredits() + " ==> All required masters credits completed" +
                        " (" + getMasterCredits() + "/" + ConstantValues.MASTER_CREDITS + ")" + "\n";
            } else {
                myString += " MasterCredits: " + getMasterCredits() + " ==> Missing masters credits " + (ConstantValues.MASTER_CREDITS - getMasterCredits()) +
                        " (" + getMasterCredits() + "/" + ConstantValues.MASTER_CREDITS + ")" + "\n";
            }
        }
        myString += " TitleOfMastersThesis: " +"\"" + getTitleOfMastersThesis() + "\"" + "\n";
        return myString;
    }

}
