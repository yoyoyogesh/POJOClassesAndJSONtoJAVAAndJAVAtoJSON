package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.commons.text.StringEscapeUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import static java.lang.Class.forName;

public class oneSingleJson {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn =null;
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business", "root" ,"root");

        ArrayList <CustomerDetailsDemo> a = new ArrayList<CustomerDetailsDemo>();
        JSONArray js = new JSONArray();

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
            //create json string from java object
            Gson g = new Gson();
            String jsonString = g.toJson(a.get(i));
            js.add(jsonString);
        }

        //Json simple
        JSONObject jo = new JSONObject();
        jo.put("data",js);

        String unescapedString = StringEscapeUtils.unescapeJava(jo.toJSONString());
        String string1 = unescapedString.replace("\"{","{");
        String finalString =string1.replace("}\"","}");
        System.out.println("Json created: "+ finalString);
        try(FileWriter file = new FileWriter("//Users//yk//Downloads//JsonJava//src//main//java//singleJson.json"))
        {
            file.write(finalString);
            System.out.println("Successfully written");
        }

        conn.close();
    }
}