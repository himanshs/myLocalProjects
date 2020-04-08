package com.samples;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.thoughtworks.xstream.*;

public class Writer {

    public static void main(String[] args) {
        Employee e = new Employee();

        //Set the properties using the setter methods
        //Note: This can also be done with a constructor.
        //Since we want to show that XStream can serialize
        //even without a constructor, this approach is used.
        e.setName("Jack");
        e.setDesignation("Manager");
        e.setDepartment("Finance");
        
        String [] arr = {"B43", "Pune"};        
        e.setAddress(arr);
        
        //Serialize the object
        XStream xs = new XStream();
        xs.alias("Emp", Employee.class);
        xs.addImplicitCollection(Employee.class, "address");

        //Write to a file in the file system
        try {
            FileOutputStream fs = new FileOutputStream("c:/temp/employeedata.txt");
            xs.toXML(e, fs);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }
}
