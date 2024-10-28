package com.chichos_snack_project.service;

import com.chichos_snack_project.dao.CustomerDAOImpl;
import com.chichos_snack_project.model.Customer;
import com.chichos_snack_project.util.AppConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class Update_customer {
    private static final CustomerDAOImpl customerDAO = new CustomerDAOImpl(AppConfig.getDatasource());
    private static final Logger log = LogManager.getLogger(Update_customer.class);

    public static boolean update(String name,String phone,String id) {
        try{
            checkNotNull(name,"El parametro name no puede ser nulo");
            checkNotNull(phone,"El parametro phone no puede ser nulo");
            checkNotNull(id,"El parametro id no puede ser nulo");
            checkArgument(phone.matches("\\d+"),"El telefono solo debe contener numeros");
            Customer customer = new Customer();
            customer.setId_customer(Integer.parseInt(id));
            Customer customer_data = customerDAO.read(customer);
            customer_data.setName(name);
            customer_data.setPhone(phone);
            customerDAO.update(customer_data.getId_customer(),customer_data);
            log.info("Se actualizaron los datos del cliente: "+customer_data.getName());
            return true;
        }catch (SQLException e){
            log.error("Ocurrio un error en los metodos de CustomerDAOImpl\n state: "+e.getSQLState());
            return false;
        }catch (NullPointerException | IllegalArgumentException e){
            log.error(e.getMessage());
            return false;
        }

    }
}
