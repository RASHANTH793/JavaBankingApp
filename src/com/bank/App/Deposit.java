package com.bank.App;

import java.sql.PreparedStatement;
import java.util.Scanner;


import com.bank.DAO.CustomerDAO;
import com.bank.DAO.CustomerDAOImpl;
import com.bank.DAO.TransactionDAO;
import com.bank.DAO.TransactionDAOImpl;
import com.bank.dto.Customer;
import com.bank.dto.Transaction;
import com.bank.dto.TransactionID;

public class Deposit {
    public static void deposit(Customer c) {
        CustomerDAO cdao = new CustomerDAOImpl();
        TransactionDAO tdao = new TransactionDAOImpl();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the amount to be depoisted");
        double amount = sc.nextDouble();
        c.setbal(c.getbal()+amount);
        boolean res = cdao.updateCustomer(c);
        if(res) {
            Transaction t = new Transaction();
            t.setTransactionID(TransactionID.generatetransactionID());
            t.setUser(c.getAccno());
            t.setRec_acc(c.getAccno());
            t.setTransaction("CREDITED");
            t.setBalance(amount);
            t.setBalance(c.getbal());
            boolean res1 = tdao.insertTransaction(t);
            if(res1) {
                System.out.println("Amount of Rs."+amount+"has been added Successfully!");
                System.out.println("Your updated balance is Rs."+c.getbal());

            }else {
                System.out.println("Failed to deposit! Try again later");
            }
        }
    }
}







