package com.example;

/* import java.sql.*;
import java.util.ArrayList;
public class Database {
    static ArrayList<Customer> customerDatabase=new ArrayList<>();
    public static DatabaseMetaData meta;

    Database() {
    }

    private static Connection connectDatabase() {
        Connection tryConnect = null;
        String dbPath = "src/com/example/db/customerdatabase.db";
        String url = "jdbc:sqlite:" + dbPath;
        try {
            tryConnect = DriverManager.getConnection(url);
            if (tryConnect != null) {
                meta = tryConnect.getMetaData();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error Connecting To Database.");
        }
        return tryConnect;
    }

    public static void createNewTable() {
        Connection tryConnect = connectDatabase();
        String QUERY = "CREATE TABLE IF NOT EXISTS userDetailsATM (\n"
                + " accountNo text NOT NULL, \n"
                + " PIN text NOT NULL, \n"
                + " userName text NOT NULL,\n"
                + " accountBalance real NOT NULL\n"
                + " bank text NOT NULL,\n"
                + " phoneNumber text NOT NULL,\n"
                + " transactionFees real NOT NULL\n"

                + " "
                + ");";

        try {
            Statement statementSQL = null;
            statementSQL = tryConnect.createStatement();
            statementSQL.execute(QUERY);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public ArrayList<Customer> getCustomerDatabase() {
        return customerDatabase;
    }
}



}*/

