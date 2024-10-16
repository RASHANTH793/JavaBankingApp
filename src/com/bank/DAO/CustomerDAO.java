package com.bank.DAO;

import com.bank.dto.Customer;
import java.util.*;

public interface CustomerDAO {
    public boolean insertCustomer(Customer c);
    public Customer getCustomer(long accno,int pin);
    public Customer getCustomer(long phone, String mail);
    public Customer getCustomer(long accno);

    public List getCustomer();

    public boolean updateCustomer(Customer c);
    public boolean deleteCustomer(Customer c);

}
