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
        try(ResultSet rs = customerDAO.findAll()){
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
    public static Customer getCustomer(int id){
        Customer customer = new Customer();
        try{
            customer.setId_customer(id);
            return customerDAO.read(customer);
        }catch (SQLException e){
            log.severe("Hubo un error al obtener el empleado recibido por el metodo read de CustomerDAOImpl");
            return new Customer(0,"Error","Error",null,0,0,0,0);
        }
    }
    public static boolean create(String name,String phone,String credit){
        try{
            checkNotNull(name,"El parametro name no puede ser nulo");
            checkNotNull(phone,"El parametro phone no puede ser nulo");
            checkNotNull(credit,"El parametro credit no puede ser nulo");
            checkArgument(!name.isEmpty(),"El nombre no puede estar vacio");
            checkArgument(!phone.isEmpty(),"El telefono no puede estar vacio");
            checkArgument(!credit.isEmpty(),"El credito no puede estar vacio");
            checkArgument(credit.matches("\\d+(\\.\\d+)?"),"El total debe ser un numero positivo");
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
            // valida que name,phone y id no esten vacios
            checkArgument(!name.isEmpty(),"El nombre no puede estar vacio");
            checkArgument(!phone.isEmpty(),"El telefono no puede estar vacio");
            checkArgument(!id.isEmpty(),"El id no puede estar vacio");
            // valida que name solo contenga letras
            checkArgument(name.matches("[a-zA-Z]+"),"El nombre solo debe contener letras");
            // valida que phone solo tenga 9 numeros
            checkArgument(phone.length() == 9,"El telefono debe tener 9 digitos");
            checkArgument(phone.matches("\\d+"),"El telefono solo debe contener numeros");
            // valida que id sea mayor que 0
            checkArgument(Integer.parseInt(id)>0,"El id debe ser mayor que 0");
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
            checkArgument(!code.isEmpty(),"El codigo no puede estar vacio");
            checkArgument(!code_entered.isEmpty(),"El codigo ingresado no puede estar vacio");
            checkArgument(id>0,"El id debe ser mayor que 0");
            checkArgument(code.matches("\\d{8}"),"El codigo debe tener 8 digitos");
            checkArgument(code_entered.matches("\\d{8}"),"El codigo ingresado debe tener 8 digitos");
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
    public static int getCountCustomer(){
        int count = 0;
        try(ResultSet rs = customerDAO.countCustomer()){
            if(rs != null && rs.next()){
                count = rs.getInt(1);
            }else{
                log.severe("El ResultSet recibido por el metodo countCustoemr de CustomerDAOImpl es nulo ");
            }

        }catch (SQLException e){
            log.severe("Hubo un error al procesar la el resulset obtenido por el metodo sumAmountSales de SaleDAOImpl state: " +e.getSQLState());

        }
        return count;
    }

    public static boolean updateCredit(String id_credit,String total,String used){
        try{
            checkNotNull(id_credit,"El parametro id_credit no puede ser nulo");
            checkNotNull(total,"El parametro total no puede ser nulo");
            checkNotNull(used,"El parametro used no puede ser nulo");
            checkArgument(!id_credit.isEmpty(),"El id_credit no puede estar vacio");
            checkArgument(!total.isEmpty(),"El total no puede estar vacio");
            checkArgument(!used.isEmpty(),"El used no puede estar vacio");
            checkArgument(total.matches("\\d+(\\.\\d+)?"),"El total debe ser un numero positivo");
            checkArgument(used.matches("\\d+(\\.\\d+)?"),"El used debe ser un numero positivo");
            checkArgument(id_credit.matches("\\d+"),"El id_credit debe ser un numero positivo");
            int id_credit_cast = Integer.parseInt(id_credit);
            double total_cast = Double.parseDouble(total);
            double used_cast = Double.parseDouble(used);
            try{
                customerDAO.updateCredit(id_credit_cast,total_cast,used_cast);
                log.info("Se actualizo el credito del cliente con id: "+id_credit_cast);
                return true;
            }catch(SQLException e){
                log.severe("No se pudo actualizar el credito del cliente por una SQLException en el metodo updateCredit de CustomerDAOImpl "+e.getSQLState());
                return false;
            }
        }catch (NullPointerException | IllegalArgumentException e){
            log.severe(e.getMessage());
            return false;
        }
    }
}
