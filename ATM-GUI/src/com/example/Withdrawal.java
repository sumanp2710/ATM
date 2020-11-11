package com.example;

import java.util.Scanner;
import static com.example.Solution.atm;

public class Withdrawal extends OTP{


    private float accountBalance;
    private float withdrawal=0f;
    private float transactionFees;


    Withdrawal(float accountBalance,float transactionFees){
        this.accountBalance=accountBalance;
        this.transactionFees=transactionFees;

    }
    public float withdrawal() {

        Scanner sc = new Scanner(System.in);
        Otp();
        System.out.println("Please Enter the OTP");
        String OneTImePassword = sc.next();
        boolean check=checkOTP(OneTImePassword);
        if(check) {
            System.out.println("Enter the amount to be WITHDRAWN");
            withdrawal = sc.nextFloat();
            if (atm.getAtmAccountBalance() > withdrawal) {
                if (withdrawal >= 0) {
                    if (accountBalance >= withdrawal) {
                        accountBalance = accountBalance - withdrawal;
                        float fees=withdrawal*(transactionFees)/100;
                        accountBalance=accountBalance-fees;

                        System.out.println("Account Balance:" + accountBalance);
                        System.out.println("Withdrawn amount:" + withdrawal);
                        System.out.println("Transaction Fees:"+ transactionFees);
                        float y = atm.getAtmAccountBalance() - withdrawal+fees;
                        atm.setAtmAccountBalance(y);

                    } else{
                        System.out.println("Insufficient balance");
                        withdrawal=0f;
                    }

                } else{
                    System.out.println("Invalid entry");
                    withdrawal=0f;
                }

            } else {
                System.out.println("ATM doesn't have enough cash");
                withdrawal=0f;
            }
        }
        else{
            System.out.println("Wrong OTP");
        }

        return accountBalance;
    }

    public float getWithdrawal(){
        return withdrawal;
    }
    public float getAccountBalance(){
        return accountBalance;
    }
}
