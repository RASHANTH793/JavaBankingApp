package com.bank.App;

import java.util.Scanner;

import com.bank.DAO.CustomerDAOImpl;
import com.bank.dto.Customer;
import com.bank.DAO.CustomerDAO;

public class Login {
    public static void login() {

        Scanner sc = new Scanner(System.in);

        CustomerDAO cdao = new CustomerDAOImpl();
        System.out.println("Enter the Account Number");
        long accno = sc.nextLong();

        System.out.println("Enter your pin");
        int pin = sc.nextInt();
        Customer c = cdao.getCustomer(accno, pin);
        if(c != null) {
            System.out.println("login Sucessful!");
            App.options(c);
        }else {
            System.out.println("Failed to Login!");
        }
    }

}
