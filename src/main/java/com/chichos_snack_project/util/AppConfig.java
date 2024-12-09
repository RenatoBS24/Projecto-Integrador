package com.chichos_snack_project.util;

import javax.sql.DataSource;
import java.util.ResourceBundle;

public class AppConfig {

    private static final ResourceBundle rb = ResourceBundle.getBundle("app");
    public static String getDatasource(){
        return rb.getString("datasource");
    }
    private static DataSource dataSource;

    public static void setDatasource(DataSource ds) {
        dataSource = ds;
    }

    public static DataSource getDatasources() {
        return dataSource;
    }


}
