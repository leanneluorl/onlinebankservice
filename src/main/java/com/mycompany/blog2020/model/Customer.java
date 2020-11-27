/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blog2020.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @name User
 */

public class Customer {
    private long id;
    private String UserId;
    private String name;
    private Date created;
    private String CorrespondenceAddress;
    private String Email;
    private String pwd;
    //private Account [] accs;
    private List<Account> listAccount;

     public Customer() {
    }
    public Customer(long id, String UserId, String name, String CorrespondenceAddress, String Email,String pwd) {
        this.id = id;
        this.UserId = UserId;
        this.name = name;
        this.created = new Date();
        this.CorrespondenceAddress = CorrespondenceAddress;
        this.Email = Email;
        this.pwd=pwd;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    
   

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public Date getcreated() {
        return created;
    }

    public void setcreated(Date created) {
        this.created = created;
    }

    public String getCorrespondenceAddress() {
        return CorrespondenceAddress;
    }

    public void setCorrespondenceAddress(String CorrespondenceAddress) {
        this.CorrespondenceAddress = CorrespondenceAddress;
    }

    public String getPublisher() {
        return Email;
    }

    public void setPublisher(String Email) {
        this.Email = Email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
/*
    public Account[] getAccs() {
        return accs;
    }

    public void setAccs(Account[] accs) {
        this.accs = accs;
    }
*/
    public List<Account> getListAccount() {
        return listAccount;
    }

    public void setListAccount(List<Account> listAccount) {
        this.listAccount = listAccount;
    }

   
    
    
    
}
