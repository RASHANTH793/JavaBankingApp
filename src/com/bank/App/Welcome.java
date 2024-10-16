package com.bank.App;

import java.util.List;
import java.util.Scanner;
import com.bank.DAO.CustomerDAO;
import com.bank.DAO.CustomerDAOImpl;
import com.bank.dto.Customer;



public class Welcome {

    public static void main(String[] args) {
        int Choice = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("<==================Welcome to New bank==================>");
        do {
            System.out.println("1.  Customer Login");
            System.out.println("2.  Create a account");
            System.out.println("3.  Exit");
            Choice = sc.nextInt();
            switch (Choice) {
                case 1:{
                    Login.login();
                    break;
                }
                case 2:{
                    Signup.signup();
                }
                case 3:{
                    System.out.println("Exiting the Application. Thank You");
                    break;
                }
                case 4:{
                    CustomerDAO cdao = new CustomerDAOImpl();
                    List<Customer> Lc = cdao.getCustomer();
                    for(Customer c: Lc) {
                        System.out.println(c);
                    }
                }
                default:
                    System.out.println("Invalid input. Please try again!");
            }
        }

        while(Choice !=3);
        sc.close();

    }
}


