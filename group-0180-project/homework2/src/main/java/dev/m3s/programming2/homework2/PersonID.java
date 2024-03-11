package dev.m3s.programming2.homework2;

/* In this class we set and check everything related to the Students person id.*/
public class PersonID {
    private String birthDate = ConstantValues.NO_BIRTHDATE;


    public String getBirthDate() {
        return birthDate;
    }
/* In this method we set the personid by first checking if the String is not null and then checking if the personId id valid with the method
* checkPersonIdNumber. After this we format the birthday into dd.MM.yyyy based on the checkmark and then check the birthday and the last character */
    public String setPersonId(final String personId) {
        if(personId != null){
            char c = personId.charAt(6);
            if (checkPersonIDNumber(personId)) {
            if (c == 'A') {
                this.birthDate = personId.substring(0, 2) + "." + personId.substring(2, 4) + ".20" + personId.substring(4, 6);
            } else if (c == '-') {
                this.birthDate = personId.substring(0, 2) + "." + personId.substring(2, 4) + ".19" + personId.substring(4, 6);
            } else if (c == '+') {
                this.birthDate = personId.substring(0, 2) + "." + personId.substring(2, 4) + ".18" + personId.substring(4, 6);
            } else {
                return ConstantValues.INCORRECT_CHECKMARK;
            }

            if (!checkBirthdate(this.birthDate)) {
                return ConstantValues.INVALID_BIRTHDAY;
            } else if (!checkValidCharacter(personId)) {
                return ConstantValues.INCORRECT_CHECKMARK;
            } else {
                return "Ok";
            }
        }
        }
        return ConstantValues.INVALID_BIRTHDAY;
    }
/* This method checks that the personId has a valid length and also that it has a valid checkmark (+, - or A)*/
    private boolean checkPersonIDNumber(final String personId){
        boolean result = false;
        if(personId.length() == 11)
            if((personId.charAt(6) == '+') || (personId.charAt(6) == '-') || (personId.charAt(6) == 'A')) {
                result = true;
            }
        return result;

    }
    /*This method will check if the given year is a leap year.*/
    private boolean checkLeapYear(int year){
        return (year % 100 != 0&& year%4==0) || year % 400 ==0;
    }
    /*This method will check that the given personid has a valid last character. Got the formula for the calculation from here: https://dvv.fi/en/personal-identity-code */
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
    /*This method will check that the formatted birthday is valid. First we check that the date itself is 10 characters long. Then we parse it into smaller
    * substrings and check first if the given year is a leap year, the year is valid, given month is valid and finally that the day is valid. If all checks out
    * the method returns true.*/
    private boolean checkBirthdate(final String date) {
        boolean result = false;
        int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int years, months, days;
        if (date.length() == 10) {
            days = Integer.parseInt(date.substring(0, 2));
            months = Integer.parseInt(date.substring(3, 5));
            years = Integer.parseInt(date.substring(6, 10));

            if (checkLeapYear(years)) {
                monthDays[1] = 29;
            }
            if (years >= 0) {
                if (months >= 1 && months <= 12) {
                    if (monthDays[months - 1] >= days && days >= 1) {
                        result = true;
                    }
                }
            }
        }
        return result;
    }
}
