package com.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    public static ATM atm=new ATM();
    static public BankManager bankManager=new BankManager(atm);

    static ArrayList<Customer> customerArrayList = new ArrayList<>();

    static void startMenu() {

        Scanner sc = new Scanner(System.in);
        int choice;
        String[] options = new String[]{"Bank Manager","Existing Customer","Create Account","exit"};
        System.out.println("Enter your choice");
        for (int i = 0; i < options.length; i++)
            System.out.println(i + 1 + ":" + options[i]);

        choice = sc.nextInt();

        switch (choice) {
            case 1: {
                System.out.println("ENTER ADMIN PASSWORD");
                if (sc.next().equals("1234")) {
                    bankManager.CallMenu();
                } else {
                    System.out.println("Not Authorized");
                    startMenu();
                }
                break;

            }
            case 2: {
                String accountNumber;
                String pin;
                System.out.println("Enter Account Number");
                accountNumber = sc.next();
                if(customerArrayList.size()==0)
                {
                    System.out.println("No Customer in Database");
                    startMenu();
                }
                else {
                    for (int i = 0; i < customerArrayList.size(); i++) {
                        if (accountNumber.equals(customerArrayList.get(i).getAccountNo())) {
                            System.out.println("Welcome to our ATM");
                            System.out.println("ENTER your PIN");
                            pin = sc.next();
                            if (customerArrayList.get(i).checkPassword(pin)){
                                customerArrayList.get(i).CallMenu();
                            } else {
                                System.out.println("Incorrect PIN");
                                startMenu();
                            }


                        } else {
                            System.out.println("Incorrect Account number");
                            startMenu();
                        }
                    }
                }

                break;
            }

            case 3: {
                Customer customer = new Customer(atm);
                customerArrayList.add(customer);
                customer.CallMenu();
                break;
            }
            case 4: {
                System.out.println("You EXITED from the ATM");
                System.exit(0);
                break;
            }
            default:
                System.out.println("INVALID choice");
                startMenu();
        }


    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to ATM");

        startMenu();
    }





}

