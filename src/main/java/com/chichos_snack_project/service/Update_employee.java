package com.chichos_snack_project.service;

import com.chichos_snack_project.dao.EmployeeDAPImpl;
import com.chichos_snack_project.model.Employee;
import com.chichos_snack_project.util.AppConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class Update_employee {
    private static final EmployeeDAPImpl employees = new EmployeeDAPImpl(AppConfig.getDatasource());
    private static final Logger log = LogManager.getLogger(Update_employee.class);

    public static boolean update(String name,String lastname,String salary,String dni,String phone,String id){
        double salary_cast = Double.parseDouble(salary);
        log.info(salary_cast);
        try {
            Employee employee = new Employee();
            employee.setId_employee(Integer.parseInt(id));
            Employee employee_data = employees.read(employee);
            if(employee_data !=null){
                employee_data.setName(name);
                employee_data.setLastname(lastname);
                employee_data.setDni(dni);
                employee_data.setPhone(phone);
                employee_data.setSalary(salary_cast);
                employees.update(employee_data.getId_employee(),employee_data);
                log.info("Se actualizaron los datos del empleado: "+employee_data.getName());
                return true;
            }else{
                log.error("El Objeto empleado recibido del metodo read de la clase EmployeeDAOImpl es nulo");
                return false;
            }
        } catch (SQLException e) {
            log.error("Hubo un error de SQLException con el metodo read o el metodo update de la clase EmployeeDAOImpl");
            throw new RuntimeException(e);
        }
    }

}
