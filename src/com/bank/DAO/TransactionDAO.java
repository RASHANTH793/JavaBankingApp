package com.bank.DAO;
import java.util.*;

import com.bank.dto.Transaction;

public interface TransactionDAO {
    public boolean insertTransaction(Transaction t);
    public List getTransaction(long user);
}

