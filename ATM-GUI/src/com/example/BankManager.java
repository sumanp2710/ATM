package com.example;

import java.util.Scanner;
import java.util.ArrayList;


import static com.example.Solution.startMenu;
import static com.example.Solution.customerArrayList;

public class BankManager implements  ShowMenu{

        public static ATM atm=new ATM();
        Scanner sc = new Scanner(System.in);



        BankManager(ATM atm) {
        this.atm=atm;
        }
        public void displayCustomerDetails() {
            if (customerArrayList.size() <= 0) {
                System.out.println("No Customer in DataBase!");
                return;
            } else {

                System.out.println("Details: ");
                for (int i = 0; i < customerArrayList.size(); i++) {
                    if (customerArrayList.get(i).getAccountNo().equals(""))
                        continue;
                    int j=i+1;
                    System.out.printf("%d. \n", j);
                    System.out.printf("%-15s%15s\n", "Account No", customerArrayList.get(i).getAccountNo());
                    System.out.printf("%-15s%15s\n", "Username", customerArrayList.get(i).getUserName());
                    System.out.printf("%-15s%15f\n", "Account Balance: ", customerArrayList.get(i).getAccountBalance());
                    System.out.printf("%-15s%15s\n", "Phone Number: ", customerArrayList.get(i).getPhoneNumber());
                    customerArrayList.get(i).displayAllDeposits();
                    customerArrayList.get(i).displayAllWithdrawals();
                    customerArrayList.get(i).displayTransfer();
                }

            }
        }



        @Override
        public void CallMenu() {


            Scanner sc = new Scanner(System.in);
            int choice;
            System.out.println("Welcome to the BANK");
            System.out.println("Enter your choice");
            String[] options = new String[]{"Fill the Cash in the ATM","View Customer Details","View Balance Cash In ATM","back", "exit" };
            for (int i = 0; i < options.length; i++) {
                System.out.println(i + 1 + ":" + options[i]);
            }

            choice = sc.nextInt();
            FunctionInvoker(choice);

        }

        @Override
        public void FunctionInvoker(int choice) {
            switch (choice) {
                case 1: {
                    System.out.println("Enter Amount of Cash to be filled in the ATM");
                    float x = sc.nextFloat();
                    atm.setAtmAccountBalance(x);
                    System.out.println("Total Cash available for disposal in the ATM:" + atm.getAtmAccountBalance());
                    CallMenu();

                    break;
                }



               /* case 2:{
                    atm.setTransactionFees();
                    CallMenu();
                    break;
                }*/


                case 2: {
                    displayCustomerDetails();
                    CallMenu();
                    break;
                }
                case 3:{
                    System.out.println("ATM CASH BALANCE: "+atm.getAtmAccountBalance());
                    CallMenu();;
                    break;
                }


                case 4: {
                    startMenu();
                    break;
                }

                case 5: {
                    System.out.println("You EXITED from the ATM");
                    System.exit(0);
                    break;
                }


            }

        }



}
