package com.chichos_snack_project.service;

import com.chichos_snack_project.dao.CustomerDAOImpl;
import com.chichos_snack_project.model.Customer;
import com.chichos_snack_project.model.Employee;
import com.chichos_snack_project.util.AppConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class Create_customer {
    private static final CustomerDAOImpl customerDAO = new CustomerDAOImpl(AppConfig.getDatasource());
    private static final Logger log = LogManager.getLogger(Create_customer.class);
    public static boolean create(String name,String phone,String credit){
        double credit_cast = Double.parseDouble(credit);
        try{
            Customer customer = new Customer(0,name,phone,null,0.0,0.0,credit_cast,0);
            customerDAO.create(customer);
            log.info("Se ha registrado un nuevo cliente \n Nombre: "+name);
            return true;
        }catch (SQLException e){
            log.error("Ocurrion un error en el metodo create de EmployeeDAOImpl\n status: "+e.getSQLState());
            return false;
        }
    }
}
