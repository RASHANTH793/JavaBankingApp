package com.bank.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.Connectors.ConnectionFactory;
import com.bank.dto.Transaction;

public class TransactionDAOImpl implements TransactionDAO {
    private Connection con;

    public TransactionDAOImpl() {
        this.con= ConnectionFactory.requestConnection();
    }

    @Override
    public boolean insertTransaction(Transaction t) {
        PreparedStatement ps=null;
        String query = "insert into passbook(tran_id, user_acc, rec_acc, tran_date, tran_type, amount, balance)"+"values(?, ?, ?, sysdate(), ?, ?, ?)";
        int res = 0;
        con= ConnectionFactory.requestConnection();
        try {
            ps= con.prepareStatement(query);
            ps.setLong(1, t.getTransactionID());
            ps.setLong(2, t.getUser());
            ps.setLong(3, t.getRec_acc());
            ps.setString(4, t.getTransaction());
            ps.setDouble(5, t.getAmount());
            ps.setDouble(6, t.getBalance());
            res=ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(res >0) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List getTransaction(long user) {
        PreparedStatement ps=null;
        ResultSet rs=null;
        String query = "Select * from passbook where user_acc=? order by Tran_date desc";
        Transaction t = null;
        ArrayList<Transaction> passbook=new ArrayList<Transaction>();

        try {
            ps = con.prepareStatement(query);
            ps.setLong(1, user);
            rs = ps.executeQuery();
            while(rs.next()) {
                t= new Transaction();
                t.setTransactionID(rs.getLong(1));
                t.setUser(rs.getLong(2));
                t.setRec_acc(rs.getLong(3));
                t.setDate(rs.getDate(4));
                t.setTransaction(rs.getString(5));
                t.setAmount(rs.getDouble(6));
                t.setBalance(rs.getDouble(7));
                passbook.add(t);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return passbook;
    }


}
