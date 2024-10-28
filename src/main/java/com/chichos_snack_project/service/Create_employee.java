package com.chichos_snack_project.service;

import com.chichos_snack_project.dao.EmployeeDAPImpl;
import com.chichos_snack_project.model.Employee;
import com.chichos_snack_project.util.AppConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class Create_employee {
    private static final EmployeeDAPImpl employeeDAO = new EmployeeDAPImpl(AppConfig.getDatasource());
    private static final Logger log = LogManager.getLogger(Create_employee.class);
    public static boolean create(String name,String lastname,String dni,String phone,String salary){
        try{
            checkNotNull(name,"El parametro name no puede ser nulo");
            checkNotNull(lastname,"El parametro lastname no puede ser nulo");
            checkNotNull(dni,"El parametro dni no puede ser nulo");
            checkNotNull(phone,"El parametro phone no puede ser nulo");
            checkNotNull(salary,"El parametro salary no puede ser nulo");
            double salary_cast = Double.parseDouble(salary);
            checkArgument(phone.matches("\\d+"),"El telefono solo debe contener numeros");
            checkArgument(salary_cast>0,"El salario ingresado no puede ser negativo");
            Employee employee = new Employee(0,name,lastname,salary_cast,dni,null,phone);
            employeeDAO.create(employee);
            log.info("Se ha registrado un nuevo trabajador \n Nombre: "+name+" "+lastname);
            return true;
        }catch (SQLException e){
            log.error("Ocurrion un error en el metodo create de EmployeeDAOImpl\n status: "+e.getSQLState());
            return false;
        }catch (NullPointerException | IllegalArgumentException e){
            log.error(e.getMessage());
            return false;
        }
    }

}
