package com.example;

import java.util.ArrayList;

import java.util.Scanner;

import static com.example.Solution.*;

public class Customer implements ShowMenu {
    Scanner sc = new Scanner(System.in);
    private String accountNo;
    private String PIN;
    private String userName;
    private float accountBalance;
    private float transactionFees;


    static ArrayList<Deposit> deposits = new ArrayList<>(0);
    static ArrayList<Withdrawal> withdrawals = new ArrayList<>(0);
    private float transfer;
    private float receiver;
    private String bank;
    private float[] transferStatus = new float[30];
    private int transferNumber = 0;
    private String phoneNumber;
    Password pin = new Password();
    private String encoder;
    public static ATM atm = new ATM();
    private String[] allBanks = new String[]{"SBI", "HDFC", "ICICI", "KOTAK MAHINDRA", "INDUSIND", "BANDHAN", "UJJIVAN"};


    Customer(ATM atm) {
        setUserName();
        setAccountNo();

        pin.setPIN();
        PIN = pin.getPIN();
      //  encoder=pin.getEncoder(PIN);

        setBank();
        setPhone();
        Deposit depositMoney = new Deposit(accountBalance, 0f);

        deposits.add(depositMoney);
        depositMoney.deposit();
        accountBalance = depositMoney.getAccountBalance();
        this.atm = atm;
        transactionFees = atm.getTransactionFees();


    }

    public void setUserName() {
        System.out.println("Enter your Username");
        userName = sc.nextLine();
    }

    public String getUserName() {
        return userName;
    }

    public String getPIN() {
        return PIN;
    }

    public void setAccountNo() {
        int n = 5;
        String AlphaNumericString = "0123456789";
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        accountNo = sb.toString();

        System.out.println("Account Number:" + accountNo);
    }

    public String getAccountNo() {
        return accountNo;
    }


    public void setAccountBalance(float balance) {
        //  System.out.println("Set Initial Account Balance");


        if (balance >= 0)
            accountBalance = balance;
            //   System.out.println("Initial Account Balance:" + accountBalance);
        else {
            //  System.out.println("invalid Entry");
            accountBalance = 0f;
        }
    }

    public float getAccountBalance() {
        return accountBalance;
    }

    public void setBank() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Bank name");
        for (int i = 0; i < allBanks.length; i++) {
            int j = i + 1;
            System.out.println(j + ":" + allBanks[i]);
        }

        int choice = sc.nextInt();

        bank = allBanks[choice - 1];


    }

    public String getBankName() {
        return bank;
    }



    public void displayAllDeposits() {

        for (int i = 0; i < deposits.size(); i++) {
            int j = i + 1;
            System.out.println("DEPOSIT" + j + ": " + deposits.get(i).getDeposit() + "  Account balance:" + deposits.get(i).getAccountBalance());
        }
    }

    public void displayAllWithdrawals() {

        for (int i = 0; i < withdrawals.size(); i++) {
            int j = i + 1;
            System.out.println("WITHDRAW" + j + ": " + withdrawals.get(i).getWithdrawal() + "  Account balance:" + withdrawals.get(i).getAccountBalance());
        }
    }


    public float moneyTransfer() {
        Scanner sc = new Scanner(System.in);
        OTP otp = new OTP();
        otp.Otp();
        System.out.println("Please Enter the OTP");
        String OneTImePassword = sc.next();
        boolean check = otp.checkOTP(OneTImePassword);
        if (check) {
            System.out.println("Enter the amount to be TRANSFERRED");
            transfer = sc.nextFloat();

            if (transfer >= 0) {
                if (bank.equals(atm.getBank())) {
                    if (accountBalance >= transfer) {
                        accountBalance = accountBalance - transfer;
                        System.out.println("Account Balance:" + accountBalance);
                        System.out.println("Transfer amount:" + transfer);
                        System.out.println("Transaction Fees:" + 0f);
                        transferStatus[transferNumber] = -1 * (transfer);
                        transferNumber++;
                    } else {
                        transfer = 0f;
                        System.out.println("Insufficient balance");
                    }
                } else {
                    if (accountBalance >= transfer) {
                        accountBalance = accountBalance - transfer;
                        float fees = transfer * transactionFees / 100;
                        accountBalance -= fees;
                        System.out.println("Account Balance:" + accountBalance);
                        System.out.println("Transfer amount:" + transfer);
                        System.out.println("Transaction Fees:" + fees);
                        transferStatus[transferNumber] = -1 * (transfer);
                        transferNumber++;
                        atm.setAtmAccountBalance(atm.getAtmAccountBalance() + fees);
                    } else {
                        transfer=0f;
                        System.out.println("Insufficient balance");
                    }

                }
                return transfer;

            } else {
                transfer=0f;
                System.out.println("Invalid entry");
            }
        } else {
            System.out.println("Wrong OTP");
        }
        return 0;


    }

    public void moneyReceiver(float money) {
        receiver = money;
        accountBalance = accountBalance + receiver;
        transferStatus[transferNumber++] = receiver;


    }

    public void displayTransfer() {
        for (int i = 0; i < transferNumber; i++) {
            if (transferStatus[i] > 0)
                System.out.println("Amount Received:" + transferStatus[i]);
            else if (transferStatus[i] < 0)
                System.out.println("Amount Transfered:" + -1 * (transferStatus[i]));
            else
                System.out.println("0");
        }

    }

    public boolean checkPassword(String PIN){
        if(PIN.equals(pin.getDecoder())){
            return true;
        }
        else
            return false;
    }

    public void setPhone(){
        System.out.println("Enter phone number:");
        String phone=sc.next();
        if(phone.length()==10){
            for(int i=0;i<phone.length();i++){
                if(phone.charAt(i)>47 & phone.charAt(i)<58)
                    continue;
                else
                {
                    System.out.println("Invalid Number");
                    setPhone();
                }
            }
            phoneNumber=phone;

        }
        else{
            System.out.println("Invalid Number");
            setPhone();
        }

    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void miniStatement(){
        System.out.printf("%-15s%15s\n", "Account No", getAccountNo());
        System.out.printf("%-15s%15s\n", "Username", getUserName());
        System.out.printf("%-15s%15f\n", "Account Balance: ", getAccountBalance());
        System.out.printf("%-15s%15s\n", "Phone Number: ", getPhoneNumber());
        displayAllDeposits();
        displayAllWithdrawals();
        displayTransfer();

    }




    public void CallMenu() {
        Scanner sc = new Scanner(System.in);
        int choice;
        String[] options = new String[]{"Deposit", "Withdrawal", "View Account Balance", "View Transaction History", "Transfer money to another Account", "Display All money Transfer","Access Family Account","back", "exit"};
        for (int i = 0; i < options.length; i++) {
            System.out.println(i + 1 + ":" + options[i]);
        }
        choice = sc.nextInt();
        FunctionInvoker(choice);
    }


    public void FunctionInvoker(int choice) {
        switch (choice) {
            case 1: {

                if (atm.getBank().equals(bank)) {
                    Deposit depositMoney = new Deposit(accountBalance, 0f);
                    deposits.add(depositMoney);
                    depositMoney.deposit();
                    accountBalance = depositMoney.getAccountBalance();
                } else {
                    Deposit depositMoney = new Deposit(accountBalance, transactionFees);
                    deposits.add(depositMoney);
                    depositMoney.deposit();
                    accountBalance = depositMoney.getAccountBalance();
                }
                miniStatement();
                CallMenu();
                break;
            }
            case 2: {
                if (atm.getBank().equals(bank)) {
                    Withdrawal withdrawMoney = new Withdrawal(accountBalance, 0f);
                    withdrawMoney.withdrawal();
                    withdrawals.add(withdrawMoney);
                    accountBalance = withdrawMoney.getAccountBalance();
                } else {
                    Withdrawal withdrawMoney = new Withdrawal(accountBalance, transactionFees);
                    withdrawMoney.withdrawal();
                    withdrawals.add(withdrawMoney);
                    accountBalance = withdrawMoney.getAccountBalance();

                }
                miniStatement();
                CallMenu();
                break;
            }
            case 4: {

                displayAllDeposits();
                displayAllWithdrawals();
                CallMenu();
            }

            case 3: {
                System.out.println("Account Balance:" + getAccountBalance());
                CallMenu();
            }
            case 5: {
                String accountNumber;
                String username;
                System.out.println("Enter Account Number");
                accountNumber = sc.next();
                if (customerArrayList.size() == 1 | accountNumber == getAccountNo()) {
                    System.out.println("No such Customer exists in the Database");
                    startMenu();
                } else {
                    for (int i = 0; i < customerArrayList.size(); i++) {
                        if (accountNumber.equals(customerArrayList.get(i).getAccountNo())) {
                            System.out.println("Welcome to our ATM");
                            System.out.println("Enter the username of the receiver");
                            username = sc.next();
                            if (username.equals(customerArrayList.get(i).getUserName())) {
                                float x = moneyTransfer();


                                customerArrayList.get(i).moneyReceiver(x);
                                break;
                            } else {
                                System.out.println("Incorrect Username");
                                startMenu();
                            }


                        } else {
                            System.out.println("Incorrect Account number");
                        }


                    }
                    miniStatement();
                    CallMenu();
                }


                CallMenu();
                break;
            }
            case 6: {
                displayTransfer();

                CallMenu();
            }
           case 7: {
                String accountnumber;
                String userName;
                String pin;
                System.out.println("Enter Account Number");
                accountnumber = sc.next();
                if (customerArrayList.size() == 1 | accountnumber.equals(getAccountNo())) {
                    System.out.println("No such Customer exists in the Database");
                    startMenu();
                } else {
                    for (int i = 0; i < customerArrayList.size(); i++) {
                        if (accountnumber.equals(customerArrayList.get(i).getAccountNo())) {
                            System.out.println("Welcome to our ATM");
                            System.out.println("Enter the username of the family number");
                            userName = sc.next();
                            System.out.println("Enter the PIN of the Family Member");
                            pin = sc.next();
                            if (userName.equals(customerArrayList.get(i).getUserName()) & customerArrayList.get(i).checkPassword(pin)) {

                                customerArrayList.get(i).CallMenu();

                            } else {
                                System.out.println("Incorrect Username or PIN");
                                startMenu();
                            }


                        } else {
                            System.out.println("Incorrect Account number");
                        }


                    }
                    CallMenu();
                }


                CallMenu();
                break;

            }
            case 8: {
                startMenu();
                break;

            }
            case 9: {
                System.out.println("You EXITED from the ATM");
                System.exit(0);
                break;
            }

        }
    }


}


