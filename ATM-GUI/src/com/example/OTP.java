package com.example;
import java.util.*;
public class OTP {
    String otp;
    public String Otp()
    {

        System.out.println("OTP is sent to your Phone");
        System.out.print("You OTP is : ");




        int n = 4;
        String AlphaNumericString = "0123456789";
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        otp = sb.toString();
        System.out.println(otp);
        return otp;
    }
    public boolean checkOTP(String OTP){
        if(OTP.equals(otp)){
            return true;
        }
        else
            return false;
    }
}
