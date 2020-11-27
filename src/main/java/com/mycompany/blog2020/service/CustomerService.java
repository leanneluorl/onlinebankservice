/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blog2020.service;

import java.util.List;
import com.mycompany.blog2020.model.Customer;
import java.util.ArrayList;
import com.mycompany.blog2020.model.Account;
import com.mycompany.blog2020.model.Transaction;
import java.util.Date;
import javax.ws.rs.core.Response;
import java.util.Random; 


/**
 *
 * @author User
 */
public class CustomerService {
    public static List<Customer> list = new ArrayList<>();
    public static List<Account> listA = new ArrayList<>();
    public static List<Transaction> listT = new ArrayList<>();

    public List<Customer> createAllCustomers() {
        int l=list.size();
        l++;
        Customer b1 = new Customer(l, "leanneluorl", "Ruei-Ling Luo", "Hsinchu, Taiwan", "x19143320@student.ncirl.ie","1234");
        l++;
        Customer b2 = new Customer(l, "Bartholomew", "Cho Fai Bartholomew Cheung", "D12, Ireland", "x18192807@student.ncirl.ie","1234");
        l++;
        Customer b3 = new Customer(l, "leanneluorl1", "Ruei-Ling Luo", "Hsinchu, Taiwan", "x19143320@student.ncirl.ie","1234");
        l++;
        Customer b4 = new Customer(l, "leanneluorl2", "Ruei-Ling Luo", "Hsinchu, Taiwan", "x19143320@student.ncirl.ie","1234");
        
        list.add(b1);
        list.add(b2);
        list.add(b3);
        list.add(b4);
        
            List<Account> listAccountB1 = new ArrayList<>();
            List<Account> listAccountB2 = new ArrayList<>();

            Account a1 = new Account("D01", "Current","D01CR99999999", 99999.9);
            Account a2 = new Account("D01", "Saving","D01SV99999999", 6666666.9);
            Account a3 = new Account("D01", "Current","D01CR12345678", 6666.6);
            Account a4 = new Account("D01", "Saving","D01SV12345678", 666666.6);
            listAccountB1.add(a1);
            listAccountB1.add(a2);

            listAccountB2.add(a3);
            listAccountB2.add(a4);

            listA.add(a1);
            listA.add(a2);
            listA.add(a3);
            listA.add(a4);

            b1.setListAccount(listAccountB1);
            b2.setListAccount(listAccountB2);
                    
                    List<Transaction> D01CR99999999 = new ArrayList<>();
                    List<Transaction> D01SV99999999 = new ArrayList<>();
                    List<Transaction> D01CR12345678 = new ArrayList<>();
                    List<Transaction> D01SV12345678 = new ArrayList<>();
                    
                    listA.get(0).setListTrans(D01CR99999999);
                    listA.get(1).setListTrans(D01SV99999999);
                    listA.get(2).setListTrans(D01CR12345678);
                    listA.get(3).setListTrans(D01SV12345678);
            
                    Transaction t = new Transaction();
                    listT.add(t); 
            
            
        return list;        
    }
    
    public List<Customer> getAllCustomers(){
        return list;
    }
    
    public Customer getCustomer(String id){
       int index=-1;
        for(int i=0;i<list.size();i++)
        {if(list.get(i).getUserId().equalsIgnoreCase(id)){index=i;}}
        return list.get(index);
    }
    
     public List<Account> getAllAccounts(){
        return listA;
    }
    
      public Account getAccount(String id, String ACCno){
       
       int index =-1;
       Customer b = getCustomer(id);
       List<Account> listAcc =b.getListAccount();
       
        for(int i=0;i<listAcc.size();i++)
        {
                if(listAcc.get(i).getAccountNumber().equalsIgnoreCase(ACCno))
                {index=i;}
        }
        return listAcc.get(index);
    }
    
    
     public double getCBalance(String id, String ACCno){
       double CBval=0;
       Customer b = getCustomer(id);
       List<Account> listAcc =b.getListAccount();
       
        for(int i=0;i<listAcc.size();i++)
        {
                if(listAcc.get(i).getAccountNumber().equalsIgnoreCase(ACCno))
                {CBval=listAcc.get(i).getCurrentBalance();
                Account listAccSelected =listAcc.get(i);}
        }
        return CBval;
    }
    
     public double Lodge(String Uid, String ACCno, double LodgeAmount){
         double OldCB = 0;
         double NewCB = 0;
         double Lodge=LodgeAmount;
         
         OldCB = getCBalance(Uid, ACCno);
         NewCB = OldCB+Lodge;
         
         
         Account acc = getAccount(Uid,ACCno);
         acc.setCurrentBalance(NewCB);
         
             
        return NewCB;  
     }
     
     public List<Transaction> updateTransaction(String Uid,String ACCno, String type,double Amount, double NewCB ){
         Account acc = getAccount(Uid,ACCno);
         List<Transaction> T = acc.getListTrans();
         if(type=="Input error"){
         String Description="Transaction Failed";
        
            Transaction t1 = new Transaction(type,Description,Amount, NewCB);
            T.add(0,t1);
            listT.add(0,t1);
            }
         else if(type=="Your balance is not enough!"){
         String Description="Transaction Failed";
        
            Transaction t1 = new Transaction(type,Description,Amount, NewCB);
            T.add(0,t1);
            listT.add(0,t1);
        }
         else if(type=="Recepient's account not existed."){
         String Description="Transaction Failed";
        
            Transaction t1 = new Transaction(type,Description,Amount, NewCB);
            T.add(0,t1);
            listT.add(0,t1);
        }
        else{
        String Description="Transaction Succeed";
        
        
        Transaction t1 = new Transaction(type,Description,Amount, NewCB);
        T.add(0,t1);
        listT.add(0,t1);
        }
        return T;
     }
     
     
     public double Withdraw(String Uid, String ACCno, double WAmount){
         double OldCB = 0;
         double NewCB = 0;
         double withdrawal=WAmount;
                  
         OldCB = getCBalance(Uid, ACCno);
         
         if(OldCB>withdrawal){
         OldCB = getCBalance(Uid, ACCno);
         NewCB = OldCB-withdrawal;
         }
         
         Account acc = getAccount(Uid,ACCno);
         acc.setCurrentBalance(NewCB);
         
         
             
        return NewCB ;  
     }
    /*
    public Transaction RemoveT(String Uid,String ACCno){
        Account acc = getAccount(Uid,ACCno);
        List<Transaction> LT = acc.getListTrans();
        int index=0;
         for(int i=0;i<LT.size();i++){
            for(int j=i+1;j<LT.size();j++){
                if(LT.get(i).getTransactionDate().equals(LT.get(j).getTransactionDate())){
                    LT.remove(j);
                    j--; index=i;
                }
            }
        }
        return LT.get(index);
    }*/
     
     public Response WithdrawError(){
         String Err1 ="Access denied - Your balance is not enough";
         return Response.status(200).entity(Err1).build();
     }
     
     
          
    public double Transfer(String ACCno, String TACCno,double Tam){
       double TCBval = 0;
       
       double NCBval;
       
      
       
       for(int j=0;j<listA.size();j++)
            {
                
                if(listA.get(j).getAccountNumber().equalsIgnoreCase(TACCno))
                {   TCBval=listA.get(j).getCurrentBalance();
                    NCBval=TCBval+Tam;
                    listA.get(j).setCurrentBalance(NCBval);
                    
                     String Description="Transaction Succeed";
                     String type="Transfer from: "+ ACCno;
                     List<Transaction> T = listA.get(j).getListTrans();
                     T.add(0,new Transaction(type,Description,Tam, NCBval));
                     listT.add(0,new Transaction(type,Description,Tam, NCBval));
                     //emoveT(Uid, ACCno);

                }
            }
       return TCBval;
    } 
    
    public boolean faT(String TAno){
        for(int j=0;j<listA.size();j++)
            {
                if(listA.get(j).getAccountNumber().equalsIgnoreCase(TAno))
                { return true;}
                
            }
        return false;
     }
    
    public Account findAccount(String TACCno){
       double TCBval=-1;
       int index=0;
       int ii = -1;
       List<Account> listAcc =new ArrayList<>();
       
       for(int j=0;j<listA.size();j++)
            {
                if(listA.get(j).getAccountNumber().equalsIgnoreCase(TACCno))
                {   TCBval=listA.get(j).getCurrentBalance();
                    Account listAccSelectedT = listAcc.get(j);
                    index=j;
                    
                   

                }
            }
       
       
      return listA.get(index);
    } 
    
    public void addCustomer(Customer e){
        list.add(e);
        int s= list.size();
        e.setcreated(new Date());
        int id=s;
        e.setId(id);
        List<Account> LA = new ArrayList<>();
        e.setListAccount(LA);
    }
    
    public void addAccount(String Uid,Account a){
        List<Account> LA = getCustomer(Uid).getListAccount();
        List<Transaction> ACCno = new ArrayList<>();
        LA.add(a);
        a.setListTrans(ACCno);
        
        
        listA.add(a);
        
        
    }
    
    public String ACCno(String Acctype){
        Random rand = new Random(); 
        int randno = rand.nextInt(100000000); 
        String formatted = String.format("%08d", randno);
        
        String ACCno="";
        if(Acctype=="Current"){
        ACCno="D01CR"+formatted;
        }else if(Acctype=="Saving"){
        ACCno="D01SV"+formatted;
        }
        for(int i=0;i<listA.size();i++){
            if(ACCno==listA.get(i).getAccountNumber()){ACCno(Acctype);}
        }
        return ACCno;
    }

    
    
}
