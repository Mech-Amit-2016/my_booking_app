package com.example.booking.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
// ****** email verifier class in this we pass email as parameter and returns is valid or not **********
public class EmailVerifier {
    public  boolean isValidEmail(String email){
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches())
            return true;
        return false;
    }
//    public static void main(String args[]){
//        System.out.println(isValidEmail("eramit3993@gmail.com"));
//        String s="hello";
//        System.out.println(s=="hello");
//    }
}
