package com.chichos_snack_project.service;

import com.chichos_snack_project.dao.EmployeeDAPImpl;
import com.chichos_snack_project.util.AppConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

import static com.google.common.base.Preconditions.checkNotNull;

public class Delete_employee {
    private static final EmployeeDAPImpl employeeDAO = new EmployeeDAPImpl(AppConfig.getDatasource());
    private static final Logger log = LogManager.getLogger(Delete_employee.class);

    public static boolean delete(int id,String code,String code_entered){
        try {
            checkNotNull(code,"El parametro code no puede ser nulo");
            checkNotNull(code_entered,"El parametro code_entered no puede ser nulo");
            if(code.equals(code_entered)){
                try{
                    log.info("Deleting employee with id: " + id);
                    employeeDAO.delete(id);
                    return true;
                }catch (SQLException e){
                    log.error("No se pudo eliminar al empleado por una SQLException en el metodo delete de EmployeeDAOImpl "+e.getSQLState());
                    return false;
                }
            }else{
                log.info("El codigo ingresado por el usuario no coincide");
                return false;
            }
        } catch (NullPointerException e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
