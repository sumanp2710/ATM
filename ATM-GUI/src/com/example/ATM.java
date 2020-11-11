package com.example;


import java.util.Scanner;

public class ATM {
    private float atmAccountBalance=0f;

    private String bank="SBI";
    private float transactionFees=0.1f;

    public String getBank(){
        return bank;
    }

    public float getTransactionFees(){
        return transactionFees;
    }

    public void setAtmAccountBalance(float x) {
        if(x>=0)
            atmAccountBalance=x;
        else
        {
            System.out.println("Invalid Entry");

        }

    }

    public float getAtmAccountBalance() {

        return atmAccountBalance;
    }


}
