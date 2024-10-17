package com.bank.App;
import java.util.Scanner;
import com.bank.DAO.CustomerDAOImpl;
import com.bank.DAO.TransactionDAOImpl;
import com.bank.DAO.TransactionDAO;
import com.bank.dto.Customer;
import com.bank.DAO.CustomerDAO;
import com.bank.dto.Transaction;
import com.bank.dto.TransactionID;

public class TransferAmount {
    public static void transferAmount(Customer c) {
        CustomerDAO cdao=new CustomerDAOImpl();
        Transaction t1=null;
        Transaction t2=null;
        TransactionDAOImpl tdao=new TransactionDAOImpl();
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the amount to be transferred");
        double amount=sc.nextDouble();
        System.out.println("Enter the benificiary account number");
        long receiver_accno=sc.nextLong();
        Customer receiver=cdao.getCustomer(receiver_accno);
        if(c.getAccno()!= receiver.getAccno() && c.getbal()> 0 && c.getbal() >=amount && amount > 0) {
            System.out.println("Enter the PIN");
            int pin=sc.nextInt();
            if(pin==c.getPin()) {
                c.setbal(c.getbal()-amount);
                boolean c_res=cdao.updateCustomer(c);
                if(c_res) {
                    t1= new Transaction();
                    t1.setTransactionID(TransactionID.generatetransactionID());
                    t1.setUser(c.getAccno());
                    t1.setRec_acc(receiver.getAccno());
                    t1.setTransaction("DEBITED");
                    t1.setAmount(amount);
                    t1.setBalance(c.getbal());
                    boolean res1 = tdao.insertTransaction(t1);
                    receiver.setbal(receiver.getbal()+amount);
                    boolean receiver_res=cdao.updateCustomer(receiver);
                    if(receiver_res) {
                        {
                            t2= new Transaction();
                            t2.setTransactionID(t1.getTransactionID());
                            t2.setUser(receiver.getAccno());
                            t2.setRec_acc(c.getAccno());
                            t2.setTransaction("CREDITED");
                            t2.setAmount(amount);
                            t2.setBalance(receiver.getbal());
                            boolean res11=tdao.insertTransaction(t2);
                        }
                    }
                    if(c_res && receiver_res) {
                        System.out.println("Transaction successfull");
                    }else {
                        System.out.println("Transaction failed !!!!");
                    }
                }
            } else {
                System.out.println("Transaction failed !!!");
            }
        }
    }

}
