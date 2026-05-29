package com.automation.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class TestDataReader {

    private final Properties properties = new Properties();
    public TestDataReader(){
        try(FileInputStream input =new FileInputStream("src/test/resources/testdata.properties")){
            properties.load(input);
        }
        catch(Exception e){
            System.out.println("Unable to read file");
        }
    }

    public String getData(String key){
        return properties.getProperty(key);
    }
}
