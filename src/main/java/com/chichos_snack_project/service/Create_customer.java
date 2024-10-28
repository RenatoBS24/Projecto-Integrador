package com.chichos_snack_project.service;

import com.chichos_snack_project.dao.CustomerDAOImpl;
import com.chichos_snack_project.model.Customer;
import com.chichos_snack_project.util.AppConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static com.google.common.base.Preconditions.*;
import java.sql.SQLException;

public class Create_customer {
    private static final CustomerDAOImpl customerDAO = new CustomerDAOImpl(AppConfig.getDatasource());
    private static final Logger log = LogManager.getLogger(Create_customer.class);
    public static boolean create(String name,String phone,String credit){
        try{
            checkNotNull(name,"El parametro name no puede ser nulo");
            checkNotNull(phone,"El parametro phone no puede ser nulo");
            checkNotNull(credit,"El parametro credit no puede ser nulo");
            double credit_cast = Double.parseDouble(credit);
            checkArgument(phone.matches("\\d+"),"El telefono solo debe contener numeros");
            checkArgument(credit_cast>0,"El credito ingresado no puede ser negativo");
            Customer customer = new Customer(0,name,phone,null,0.0,0.0,credit_cast,0);
            customerDAO.create(customer);
            log.info("Se ha registrado un nuevo cliente \n Nombre: "+name);
            return true;
        }catch (SQLException e){
            log.error("Ocurrion un error en el metodo create de CustomerDAOImpl\n status: "+e.getSQLState());
            return false;
        }catch (NullPointerException | IllegalArgumentException e){
            log.error(e.getMessage());
            return false;
        }
    }
}
