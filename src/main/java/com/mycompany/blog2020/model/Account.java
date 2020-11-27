/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blog2020.model;

import java.util.List;

/**
 *
 * @author User
 */
public class Account {
    private String SortCode;
    private String AccountType;
    private String AccountNumber;
    private double CurrentBalance;
    
    private Transaction [] trans;
    private List<Transaction> listTrans;

    public Account() {
       
    }

    

    public Account(String SortCode, String AccountType, String AccountNumber, double CurrentBalance) {
        this.SortCode = SortCode;
        this.AccountType = AccountType;
        this.AccountNumber = AccountNumber;
        this.CurrentBalance = CurrentBalance;
    }

    public Transaction[] getTrans() {
        return trans;
    }

    public void setTrans(Transaction[] trans) {
        this.trans = trans;
    }

    public List<Transaction> getListTrans() {
        return listTrans;
    }

    public void setListTrans(List<Transaction> listTrans) {
        this.listTrans = listTrans;
    }

    
   

    public String getAccountType() {
        return AccountType;
    }

    public void setAccountType(String AccountType) {
        this.AccountType = AccountType;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String AccountNumber) {
        this.AccountNumber = AccountNumber;
    }

    public String getSortCode() {
        return SortCode;
    }

    public void setSortCode(String SortCode) {
        this.SortCode = SortCode;
    }

    public double getCurrentBalance() {
        return CurrentBalance;
    }

    public void setCurrentBalance(double CurrentBalance) {
        this.CurrentBalance = CurrentBalance;
    }
    
    
    
}
