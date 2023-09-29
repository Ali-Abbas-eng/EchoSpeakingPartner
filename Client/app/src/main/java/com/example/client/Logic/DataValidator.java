package com.example.client.Logic;

import java.util.regex.Pattern;

public class DataValidator {
    boolean registration;
    String passwordCriteria;
    String emailCriteria;

    public DataValidator(boolean registration){
        this.registration = registration;
        this.passwordCriteria = "Password must contain (Lower and Upper case letters, numbers and special symbols)";
        this.emailCriteria = "Email should follow the convention 'name@domain.extention'";
    }

    public String validate(String username, String password){
        if (isValidEmail(username) && isValidPassword(password)){
            return "";
        }
        return this.emailCriteria + "\t" + this.passwordCriteria;

    }
    public Boolean isValidEmail(String email){
        /*
        * Email validation using regex
        *   must be of the form name@domain.extension
        * */

        if (this.registration){
            String regex = "^(.+)@(\\S+)$";
            return Pattern.matches(regex, email);
        } else {
            return !email.isEmpty();
        }


    }

    public Boolean isValidPassword(String password){
        /*
        * password validation using regex
        *       lengths at least 8 characters.
        *       contains upper case and lower case letters.
        *       contains at least one symbol of !@#$%^&*()_+")
        * */
        if (this.registration){
            String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+]).{8,}$";
            return Pattern.matches(regex, password);
        } else {
            return !password.isEmpty();
        }


    }
}
