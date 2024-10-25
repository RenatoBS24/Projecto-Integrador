package com.chichos_snack_project.util;

import java.util.ResourceBundle;

public class AppConfig {

    private static final ResourceBundle rb = ResourceBundle.getBundle("app");
    public static String getDatasource(){
        return rb.getString("datasource");
    }


}
