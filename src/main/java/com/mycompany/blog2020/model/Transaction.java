/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blog2020.model;

import java.util.Date;

/**
 *
 * @author Leanne
 */
public class Transaction {
    private Date TransactionDate;
    private String Type;
    private String Description;
    private double Amount;
    private double PostBalance;
    

    public Transaction() {
    }

    public Transaction( String Type, String Description,double Amount, double PostBalance) {
        this.TransactionDate = new Date();
        this.Type = Type;
        this.Description = Description;
        this.Amount = Amount;
        this.PostBalance = PostBalance;
    }

   

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double Amount) {
        this.Amount = Amount;
    }
    
    
    public Date getTransactionDate() {
        return TransactionDate;
    }

    public void setTransactionDate(Date TransactionDate) {
        this.TransactionDate = TransactionDate;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public double getPostBalance() {
        return PostBalance;
    }

    public void setPostBalance(double PostBalance) {
        this.PostBalance = PostBalance;
    }
    
      
    
    
}
