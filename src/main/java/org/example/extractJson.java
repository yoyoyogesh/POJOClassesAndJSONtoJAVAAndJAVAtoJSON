package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class extractJson {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

        ObjectMapper o = new ObjectMapper();
        CustomerDetailsDemoAppium c= o.readValue(new File("//Users//yk//Downloads//JsonJava//src//main//java//customerInfoSecond0.json"), CustomerDetailsDemoAppium.class);

        System.out.println(c.getStudentName());
    }

    }
