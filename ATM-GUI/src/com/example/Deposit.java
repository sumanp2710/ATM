package com.example;

import java.util.Scanner;

import static com.example.Solution.atm;

public class Deposit extends OTP{
    private float accountBalance;
    private float deposit=0f;
    private float transactionFees;

    Deposit(float accountBalance,float transactionFees){
        this.accountBalance=accountBalance;
        this.transactionFees=transactionFees;
    }


    public float deposit() {
        Scanner sc = new Scanner(System.in);
        Otp();
        System.out.println("Please Enter the OTP");
        String OneTImePassword = sc.next();
        boolean check=checkOTP(OneTImePassword);
        if(check) {

            System.out.println("Enter the amount to be DEPOSITED");
            deposit = sc.nextFloat();

            if (deposit >= 0) {
                accountBalance = accountBalance + deposit;
                float fees=deposit*(transactionFees)/100;
                accountBalance-=fees;
                System.out.println("Account Balance:" + accountBalance);
                System.out.println("Deposited amount:" + deposit);
                System.out.println("TransactionFees:"+fees);
                float y = atm.getAtmAccountBalance() + deposit+fees;
                atm.setAtmAccountBalance(y);

            } else
                System.out.println("Invalid entry");
        }
        else{
            System.out.println("Wrong OTP");
        }
        return accountBalance;
    }

    public float getDeposit(){
        return deposit;
    }
    public float getAccountBalance(){
        return accountBalance;
    }
}
