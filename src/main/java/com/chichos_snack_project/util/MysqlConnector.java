package com.chichos_snack_project.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;

import java.sql.SQLException;
import java.util.logging.Logger;


public class MysqlConnector {

    private static final java.util.logging.Logger log = Logger.getLogger(MysqlConnector.class.getName());

    public static Connection getConnection(String name_datasource ){
        Connection cn = null;
        try{
            InitialContext context = new InitialContext();
            DataSource ds = (DataSource) context.lookupLink(name_datasource);
            if(ds == null){
                log.severe("El datasource obtenido es nulo");
            }else{
                cn = ds.getConnection();
            }
        }catch (NamingException | SQLException e){
            log.severe(e.getMessage());
            throw new RuntimeException();
        }
        return cn;
    }

}
