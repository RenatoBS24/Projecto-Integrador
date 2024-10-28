package com.chichos_snack_project.service;

import com.chichos_snack_project.dao.CustomerDAOImpl;
import com.chichos_snack_project.model.Customer;
import com.chichos_snack_project.util.AppConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class Update_customer {
    private static final CustomerDAOImpl customerDAO = new CustomerDAOImpl(AppConfig.getDatasource());
    private static final Logger log = LogManager.getLogger(Update_customer.class);

    public static boolean update(String name,String phone,String id) {
        try{
            Customer customer = new Customer();
            log.info(id);
            customer.setId_customer(Integer.parseInt(id));
            Customer customer_data = customerDAO.read(customer);
            customer_data.setName(name);
            customer_data.setPhone(phone);
            log.info(customer_data.getId_customer()+" "+customer_data);
            customerDAO.update(customer_data.getId_customer(),customer_data);
            log.info("Se actualizaron los datos del cliente: "+customer_data.getName());
            return true;
        }catch (SQLException e){
            log.error("Ocurrio un error en los metodos de CustomerDAOImpl\n state: "+e.getSQLState());
            return false;
        }

    }
}
