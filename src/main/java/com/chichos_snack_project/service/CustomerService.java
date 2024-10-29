package com.chichos_snack_project.service;

import com.chichos_snack_project.dao.CustomerDAOImpl;
import com.chichos_snack_project.model.Customer;
import com.chichos_snack_project.util.AppConfig;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class CustomerService {
    private static final CustomerDAOImpl customerDAO = new CustomerDAOImpl(AppConfig.getDatasource());
    private static final java.util.logging.Logger log = Logger.getLogger(CustomerService.class.getName());
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
            log.severe("Hubo un error al cargar la data del ResulSet recibido por el metodo findAll de CustomerDAOImpl");
            customerList.add(new Customer(0,"Error","Error",null,0,0,0,0));
            return customerList;
        }
    }
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
            log.severe("Ocurrion un error en el metodo create de CustomerDAOImpl\n status: "+e.getSQLState());
            return false;
        }catch (NullPointerException | IllegalArgumentException e){
            log.severe(e.getMessage());
            return false;
        }
    }
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
            log.severe("Ocurrio un error en los metodos de CustomerDAOImpl\n state: "+e.getSQLState());
            return false;
        }catch (NullPointerException | IllegalArgumentException e){
            log.severe(e.getMessage());
            return false;
        }

    }
    public static boolean delete(int id,String code,String code_entered){
        try {
            checkNotNull(code,"El parametro code no puede ser nulo");
            checkNotNull(code_entered,"El parametro code_entered no puede ser nulo");
            if(code.equals(code_entered)){
                try{
                    log.info("Deleting customer with id: " + id);
                    customerDAO.delete(id);
                    return true;
                }catch (SQLException e){
                    log.severe("No se pudo eliminar al cliente por una SQLException en el metodo delete de EmployeeDAOImpl "+e.getSQLState());
                    return false;
                }
            }else{
                log.info("El codigo ingresado por el usuario no coincide");
                return false;
            }
        } catch (NullPointerException e) {
            log.severe(e.getMessage());
            return false;
        }
    }
}
