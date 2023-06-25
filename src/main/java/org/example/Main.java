package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import static java.lang.Class.forName;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
     
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn =null;
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business", "root" ,"root");

        ArrayList <CustomerDetailsDemo> a = new ArrayList<CustomerDetailsDemo>();

        //object of statement class will help us to execute queries
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from CustomerInfo where purchasedDate=CURDATE() and Location ='Asia';");
        while(rs.next())
        {
            CustomerDetailsDemo c = new CustomerDetailsDemo();
            //3 different json files, 3 different java objects
            c.setCourseName(rs.getString(1));
            c.setPurchaseDate(rs.getString(2));
            c.setAmount(rs.getInt(3));
            c.setLocation(rs.getString(4));
            a.add(c);

        }
        //Create json file
        for(int i=0; i<a.size(); i++) {
            ObjectMapper o = new ObjectMapper();
            o.writeValue(new File("//Users//yk//Downloads//JsonJava//src//main//java//customerInfoSecond"+i+".json"),a.get(i));
        }
        conn.close();
    }
}