package com.chichos_snack_project.service;

import com.chichos_snack_project.dao.CustomerDAOImpl;
import com.chichos_snack_project.model.Customer;
import com.chichos_snack_project.util.AppConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class View_data_customer {
    private static final CustomerDAOImpl customerDAO = new CustomerDAOImpl(AppConfig.getDatasource());
    private static final Logger log = LogManager.getLogger(View_data_products.class);
    public static List<Customer> getCustomer(){
        List<Customer> customerList = new LinkedList<>();
        try{
            ResultSet rs = customerDAO.findAll();
            if(rs !=null){
                while (rs.next()){
                    customerList.add(new Customer(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getDouble(5),rs.getDouble(6),rs.getDouble(7),rs.getInt(8)));
                }
            }else{
                customerList.add(new Customer(0,"Error","Error",null,0,0,0,0));
            }
            return customerList;
        }catch (SQLException e){
            log.error("Hubo un error al cargar la data del ResulSet recibido por el metodo findAll de CustomerDAOImpl");
            customerList.add(new Customer(0,"Error","Error",null,0,0,0,0));
            return customerList;
        }
    }
}
