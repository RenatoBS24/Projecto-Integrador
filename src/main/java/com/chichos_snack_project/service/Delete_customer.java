package com.chichos_snack_project.service;

import com.chichos_snack_project.dao.CustomerDAOImpl;
import com.chichos_snack_project.util.AppConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class Delete_customer {
    private static final CustomerDAOImpl customerDAO = new CustomerDAOImpl(AppConfig.getDatasource());
    private static final Logger log = LogManager.getLogger(Delete_customer.class);

    public static boolean delete(int id,String code,String code_entered){
        if(code.equals(code_entered)){
            try{
                log.info("Deleting customer with id: " + id);
                customerDAO.delete(id);
                return true;
            }catch (SQLException e){
                log.error("No se pudo eliminar al cliente por una SQLException en el metodo delete de EmployeeDAOImpl "+e.getSQLState());
                return false;
            }
        }else{
            log.info("El codigo ingresado por el usuario no coincide");
            return false;
        }
    }
}
