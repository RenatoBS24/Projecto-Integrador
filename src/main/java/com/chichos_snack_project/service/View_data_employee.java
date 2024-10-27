package com.chichos_snack_project.service;

import com.chichos_snack_project.dao.EmployeeDAPImpl;
import com.chichos_snack_project.model.Employee;
import com.chichos_snack_project.util.AppConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class View_data_employee {
    private static final EmployeeDAPImpl employees = new EmployeeDAPImpl(AppConfig.getDatasource());
    private static final Logger log = LogManager.getLogger(View_data_employee.class);
    public static List<Employee> getEmployees(){
        List<Employee> employeeList = new LinkedList<>();
        try{
            ResultSet rs = employees.findAll();
            if(rs !=null){
                while (rs.next()){
                    employeeList.add(new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getString(5),rs.getDate(6),rs.getString(7)));
                }
            }else{
                employeeList.add(new Employee(0,"Error","Error",0,"Error",null,"Error"));
            }
            return employeeList;
        }catch (SQLException e){
            log.error("Hubo un error al cargar la data del ResulSet recibido por el metodo findAll de EmployeeDAOImpl");
            employeeList.add(new Employee(0,"Error","Error",0,"Error",null,"Error"));
            return employeeList;
        }
    }
}
