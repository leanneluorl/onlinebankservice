/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blog2020.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.mycompany.blog2020.model.Customer;
import com.mycompany.blog2020.model.Account;
import com.mycompany.blog2020.model.Transaction;
import com.mycompany.blog2020.service.CustomerService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
/**
 *
 * @name User
 */
 
   

@Path("/onlinebankservice")
public class CustomerResource {
    CustomerService bs = new CustomerService();
    
         //@Override
         protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                                                                  throws ServletException, IOException {
             fixHeaders(resp);
         }

         //@Override
         protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
                                                                  throws ServletException, IOException {
             fixHeaders(resp);
         }

         private void fixHeaders(HttpServletResponse response) {

             response.setContentType("text/html");
             response.setHeader("Cache-control", "no-cache, no-store");
             response.setHeader("Pragma", "no-cache");
             response.setHeader("Expires", "-1");

             response.addHeader("Access-Control-Allow-Origin", "*"); // 授權的網址，星號代表接受所有
             response.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, OPTIONS, DELETE"); // 接受的方式
             response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Content-Length");
             response.addHeader("Access-Control-Max-Age", "86400");
         }
         
         

        //curl -vi -X GET -G "http://localhost:49000/api/onlinebankservice/create"
        @GET
        @Path("/create")   
        @Produces(MediaType.APPLICATION_JSON)
        public Response createCustomers() {
            Gson gson = new Gson();

            List<Customer> books = bs.createAllCustomers();

            return Response.status(Response.Status.CREATED).entity(gson.toJson(books)).build();
        }  

        //curl -vi -X GET -G "http://localhost:49000/api/onlinebankservice/fetchAll"
        @GET
        @Path("/fetchAll")   
        @Produces(MediaType.APPLICATION_JSON)
        public Response getAllCustomers() {
            Gson gson = new Gson();

            List<Customer> books = bs.getAllCustomers();

            return Response.status(Response.Status.CREATED).entity(gson.toJson(books)).build();
        } 
        
         //curl -vi -X GET -G "http://localhost:49000/api/onlinebankservice/account/fetchAll"
        @GET
        @Path("/account/fetchAll")   
        @Produces(MediaType.APPLICATION_JSON)
        public Response getAllaccs() {
            Gson gson = new Gson();

            List<Account> books = bs.getAllAccounts();

            return Response.status(Response.Status.CREATED).entity(gson.toJson(books)).build();
        } 

        //curl -vi -X GET -G "http://127.0.0.1:49000/api/onlinebankservice/customer/leanneluorl"
        @GET
        @Path("/customer/{param}")   
        @Produces(MediaType.APPLICATION_JSON)
        public Response getCustomer(@PathParam("param") String id) {
            Gson gson = new Gson();

            //List<Customer> books = bs.getAllCustomers();
            Customer b = bs.getCustomer(id);
            return Response.status(Response.Status.CREATED).entity(gson.toJson(b)).build();

        } 
    //curl -vi -X GET -G "http://127.0.0.1:49000/api/onlinebankservice/customer/leanneluorl/acc
    @GET
    @Path("/customer/acc/{Uid}")   
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIdAllAccount(@PathParam("Uid") String id) {
        Gson gson = new Gson();
        
        //List<Customer> books = bs.getAllCustomers();
        Customer b = bs.getCustomer(id);
        List<Account> listA =b.getListAccount();
        //String Acc = gson.toJson(list);
        return Response.status(Response.Status.CREATED).entity(gson.toJson(listA)).build();
    } 
    
    // curl -v -X GET http://127.0.0.1:49000/api/onlinebankservice/customer/accountBalance?Uid=leanneluorl&ACCno=D01SV99999999
    
    @Path("/customer/accountBalance")  
    @GET
    //@Path("/customer/{Uid}/{ACCno}")   
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIdAccount(@QueryParam("Uid") String id, @QueryParam("ACCno") String ACCno) {
        Gson gson = new Gson();
        
        //Customer b = bs.getCustomer(id);
        //List<Account> listA =b.getListAccount();
        String CBval = "Balance is: "+ bs.getCBalance(id,ACCno);
        Account A =bs.getAccount(id,ACCno);
        //String CBval = gson.toJson(list[ACCno]);
        return Response.status(Response.Status.CREATED).entity(gson.toJson(A)).build();
    } 
    /*
    @GET
    @Path("/customer/{Uid}/{ACCno}")   
    //@Produces(MediaType.APPLICATION_JSON)
    public Response getAccountCBalance(@PathParam("Uid") String id, @PathParam("ACCno") String ACCno) {
        Gson gson = new Gson();
        
        //Customer b = bs.getCustomer(id);
        //List<Account> listA =b.getListAccount();
        String CBval = "Balance is: "+ bs.getCBalance(id,ACCno);
        //Account [] list =c.getCBalance();
        //String CBval = gson.toJson(list[ACCno]);
        return Response.status(200).entity(CBval).build();
    } 
    */
    
    // curl -v -X POST http://127.0.0.1:49000/api/onlinebankservice/customer/leanneluorl/D01SV99999999/save/666699
    // curl -v -X GET http://127.0.0.1:49000/api/onlinebankservice/customer/lodge?Uid=leanneluorl&ACCno=D01SV99999999&LodgeAmount=666699
    
    @Path("/customer/lodge")
    @GET
    //@XMLRootElement
    //@Path("/customer/{Uid}/{ACCno}/save/{LodgeAmount}")   
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Lodge(@QueryParam("Uid") String id ,@QueryParam("ACCno") String ACCno,@QueryParam("LodgeAmount") double LodgeAmount ) {
        Gson gson = new Gson();
        double NCBval=0;
        if(LodgeAmount<=0){
        String type="Input error";
        double CB = bs.getCBalance(id,ACCno);
        bs.updateTransaction(id,ACCno,type,LodgeAmount,CB);
        }
        else{
        List<Account> Acc = bs.getAllAccounts();
       
        NCBval= bs.Lodge(id,ACCno,LodgeAmount);
        bs.updateTransaction( id, ACCno,"Lodgement",LodgeAmount,NCBval);
        }
        
        Account AccUpdate = bs.getAccount(id,ACCno);
        Transaction Trans = AccUpdate.getListTrans().get(0);
        
        return Response.status(Response.Status.CREATED).entity(gson.toJson(Trans)).build();    
    } 
    
    
    // curl -v -X POST http://127.0.0.1:49000/api/onlinebankservice/customer/leanneluorl/D01SV99999999/withdraw/666
    // curl -v -X GET http://127.0.0.1:49000/api/onlinebankservice/customer/withdraw?Uid=leanneluorl&ACCno=D01SV99999999&WAmount=6666
    
    @Path("/customer/withdraw")
    @GET
    //@Path("/customer/{Uid}/{ACCno}/withdraw/{WAmount}")   
    @Produces(MediaType.APPLICATION_JSON)
    @SuppressWarnings("empty-statement")
    public Response Withdraw(@QueryParam("Uid") String id, @QueryParam("ACCno") String ACCno,@QueryParam("WAmount") double WAmount) {
        Gson gson = new Gson();
        double CB = bs.getCBalance(id,ACCno);
        double NCBval= 0;
        if(WAmount<=0||WAmount>=CB){
            if(WAmount<=0){
            String type="Input error";

            bs.updateTransaction(id,ACCno,type,WAmount,CB);
            }
            else if(WAmount>=CB){
            String type="Your balance is not enough!";

            bs.updateTransaction(id,ACCno,type,WAmount,CB);
            }
        }else{
        
            NCBval= bs.Withdraw(id,ACCno,WAmount); 
            bs.updateTransaction(id,ACCno,"Withdrawal",WAmount,NCBval);
         }  
        Account AccUpdate = bs.getAccount(id,ACCno);
        
       
        
        String NCBvalue = "New Balance is: "+ NCBval;
        System.out.print("Withdraw: "+WAmount+"\b"+ NCBvalue);
        Transaction Trans = AccUpdate.getListTrans().get(0);
        
        return Response.status(Response.Status.CREATED).entity(gson.toJson(Trans)).build();  
              
          
    } 
    
    /*tttest trans
    // curl -v -X POST http://127.0.0.1:49000/api/onlinebankservice/customer/trans/D01SV99999999
    @GET
    @Path("/customer/trans/{TACCno}")   
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAccountNo(@PathParam("TACCno") String TACCno) {
        Gson gson = new Gson();
        
      
       // Account TA = bs.findAccount(TACCno);
       // return Response.status(Response.Status.CREATED).entity(gson.toJson(TA)).build();
        String TCBval = "Balance is: "+ bs.Transfer(TACCno);
        
        return Response.status(200).entity(TCBval).build();
    
    } */
    
    
    // curl -v -X POST "http://127.0.0.1:49000/api/onlinebankservice/customer/leanneluorl/D01CR99999999/trans/D01SV99999999/9999"
    // curl -v -X GET http://127.0.0.1:49000/api/onlinebankservice/customer/transfer?Uid=leanneluorl&ACCno=D01SV99999999&TACCno=D01cr12345678&TAmount=6666
    
    @Path("/customer/transfer")
    @GET
    //@POST 
    //@Path("/customer/{Uid}/{ACCno}/trans/{TACCno}/{TAmount}")   
    @Produces(MediaType.APPLICATION_JSON)
    public Response Transfer(@QueryParam("Uid") String id, @QueryParam("ACCno") String ACCno,@QueryParam("TACCno") String TACCno, @QueryParam("TAmount")double TransAmount) {
        Gson gson = new Gson();
        double CB = bs.getCBalance(id,ACCno);
        if(bs.faT(TACCno)){
        if(TACCno==""||TransAmount<=0||CB<TransAmount){
        String type="Input error";
        
        bs.updateTransaction(id,ACCno,type,TransAmount,CB);
        }
        else{
        bs.Withdraw(id,ACCno,TransAmount); 
        double TCB = bs.Transfer(ACCno,TACCno,TransAmount);
        double ACB = bs.getCBalance(id,ACCno);
        
        String TCBval =ACCno+ " Balance is: "+ ACB+"; "+TACCno+ " Balance is: "+ TCB;
        String type="Transfer to "+ TACCno;
        bs.updateTransaction(id,ACCno,type,TransAmount,ACB);
        }}
        else{
        String type="Recepient's account not existed.";
        
        bs.updateTransaction(id,ACCno,type,TransAmount,CB);
        }
        Account AccUpdate = bs.getAccount(id,ACCno);
        Transaction Trans = AccUpdate.getListTrans().get(0);
        
        return Response.status(Response.Status.CREATED).entity(gson.toJson(Trans)).build(); 
        //return Response.status(200).entity(TCBval).build();
    
    } 
    
    
    
    
    
    
    
    // curl -v -X POST "http://localhost:49000/api/onlinebankservice/" -d {"id": 4,"UserId":"Enda", "name": "Mr. Enda Stafford","CorrespondenceAddress": Dublin,"Email": "enda.stafford@ncirl.ie"}
    /* In Postman...Body -> raw
       {
     
       }    
    */
    
    @Path("/createCustomer")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCustomer(String body){
        Gson gson = new Gson();
        Customer a = gson.fromJson(body, Customer.class);
        bs.addCustomer(a);
        
        return Response.status(Response.Status.CREATED).entity(gson.toJson(a)).build();    
    }
    
    
     // curl -v -X POST "http://localhost:49000/api/onlinebankservice/{Uid}/Account" -d {"id": 4,"UserId":"Enda", "name": "Mr. Enda Stafford","CorrespondenceAddress": Dublin,"Email": "enda.stafford@ncirl.ie"}
    /* In Postman...Body -> raw
        {
        "SortCode":"D01",
        "AccountType"="Saving",
        "AccountNumber"="D01sv99999998",
        "CurrentBalance"=10000
}     
    */
    
    @Path("/createCustomerACC") 
    @GET
    // curl -v -X GET http://127.0.0.1:49000/api/onlinebankservice/createCustomer?Uid=enda&Acctype=Saving
    //http://127.0.0.1:49000/api/onlinebankservice/createCustomer?Uid=enda&Acctype=Saving&Acctype2=Current
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAccount(@QueryParam("Uid") String id, @QueryParam("Acctype") String Acctype, @QueryParam("Acctype2") String Acctype2 ){
        Gson gson = new Gson();
        
        int i=0;
         while(Acctype.contains("Saving")&&i==0){
            Acctype="Saving";
            String ACCno = bs.ACCno(Acctype);
            Account a = new Account("D01",Acctype,ACCno,0);
            bs.addAccount(id,a);i++;}
        int j=0; 
          while(Acctype2.contains("Current")&&j==0){
            Acctype="Current";
            String ACCno = bs.ACCno(Acctype);
            Account a = new Account("D01",Acctype,ACCno,0);
            bs.addAccount(id,a);j++;}
        List<Account> a = bs.getCustomer(id).getListAccount();
        return Response.status(Response.Status.CREATED).entity(gson.toJson(a)).build();    
    }
    
    
    
    
    

   
    
     //curl -vi -X GET -G "http://127.0.0.1:49000/api/onlinebankservice/customer?Uid=leanneluorl"
    @GET
    @Path("/customer")   
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchCustomer(@QueryParam("Uid") String id) {
        Gson gson = new Gson();
        
        //List<Customer> books = bs.getAllCustomers();
        Customer b = bs.getCustomer(id);
        return Response.status(Response.Status.CREATED).entity(gson.toJson(b)).build();
        
    } 
    
    /*
     //curl -vi -X GET -G "http://127.0.0.1:49000/api/onlinebankservice/login?Uid=leanneluorl&pwd=1234"
    @GET
    @Path("/login")   
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@QueryParam("Uid") String Uid,@QueryParam("pwd") String pwd) {
        Gson gson = new Gson();
        Customer True = new Customer();
        Customer b = bs.getCustomer(Uid);
        if(b.getPwd().equalsIgnoreCase(pwd)){
        True=b;}
        
        return Response.status(Response.Status.CREATED).entity(gson.toJson(True)).build();
        
    }*/
    
    
}
