package com.chichos_snack_project.service;

import com.chichos_snack_project.dao.EmployeeDAPImpl;
import com.chichos_snack_project.util.AppConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class Delete_employee {
    private static final EmployeeDAPImpl employeeDAO = new EmployeeDAPImpl(AppConfig.getDatasource());
    private static final Logger log = LogManager.getLogger(Delete_employee.class);

    public static boolean delete(int id){
        try{
           employeeDAO.delete(id);
           return true;
        }catch (SQLException e){
            log.error("No se pudo eliminar al empleado por una SQLException en el metodo delete de EmployeeDAOImpl");
            return false;
        }
    }
}
