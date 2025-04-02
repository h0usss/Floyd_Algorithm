package com.h0uss.floyd_algorithm.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Property {
    public int getProperty(String key){
        String value = "";
        Properties prop = new Properties();
        String path = "/com/h0uss/floyd_algorithm/util/application.properties";

        try(InputStream inputStream = getClass().getResourceAsStream(path)){
            prop.load(inputStream);
            value = prop.getProperty(key);
        }
        catch (IOException e){
            System.out.print(e);
        }
        return Integer.parseInt(value);
    }
}
