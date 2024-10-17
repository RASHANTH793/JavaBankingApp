package com.bank.App;
import java.util.Scanner;
import com.bank.DAO.CustomerDAO;
import com.bank.DAO.CustomerDAOImpl;
import com.bank.dto.Customer;
public class ResetPin {
    public static void resetPin(Customer c) {
        Scanner sc=new Scanner(System.in);
        CustomerDAO cdao=new CustomerDAOImpl();
        System.out.println("Enter your Phone number");
        long phone=sc.nextLong();
        System.out.println("Enter your Mail ID");
        String mail=sc.next();
        if(phone==c.getPhone()&&mail.equals(c.getMail())) {
            System.out.println("Set a new Pin");
            int pin=sc.nextInt();
            System.out.println("Confirm the Pin");
            int confirm=sc.nextInt();
            if(pin==confirm) {
                c.setPin(pin);
                boolean res=cdao.updateCustomer(c);
                if(res) {
                    System.out.println("Pin updates SuccessFully");
                }else {
                    System.out.println("Failed to update the Pin");
                }
            }else {
                System.out.println("Pin mismatch or incorrect pin");
            }
        }else {
            System.out.println("Incorrect phone number or mail ID");
        }
    }

}
