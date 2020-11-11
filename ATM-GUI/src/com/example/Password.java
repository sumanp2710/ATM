package com.example;
import java.util.Base64;
import java.util.Scanner;

public class Password {
//  ENCRYPTION DECRYPTION

    private  String PIN;
    private String bankPIN="1234";
    private String encoder;
    private String decoder;

    public void setPIN() {
        int n = 5;
        String AlphaNumericString = "0123456789";
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        PIN = sb.toString();
        System.out.println("PIN:" + PIN);
        encoder=Encoder(PIN);
    }


    public String getBankPIN() {
        return bankPIN;
    }

    public String getPIN() {
        return PIN;
    }
    public String getEncoder(){return encoder;}
    public String getDecoder(){
        decoder=Decoder(getEncoder());
        return decoder;}

    private static String Encoder(String pin){
        return Base64.getEncoder().encodeToString(pin.getBytes());

    }

    private static String Decoder(String encoder){
        return new String(Base64.getMimeDecoder().decode(encoder));
    }





}
