package com.chichos_snack_project.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;

import java.sql.SQLException;


public class MysqlConnector {

    private static final Logger log = LogManager.getLogger(MysqlConnector.class);


    public static Connection getConnection(String name_datasource ){
        Connection cn = null;
        try{
            InitialContext context = new InitialContext();
            DataSource ds = (DataSource) context.lookupLink(name_datasource);
            if(ds == null){
                log.error("El datasource obtenido es nulo");
            }else{
                cn = ds.getConnection();
            }
        }catch (NamingException | SQLException e){
            log.error(e.getMessage());
            throw new RuntimeException();
        }
        return cn;
    }

}
