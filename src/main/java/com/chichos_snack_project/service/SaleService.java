package com.chichos_snack_project.service;

import com.chichos_snack_project.dao.SaleDAOImpl;
import com.chichos_snack_project.model.Customer;
import com.chichos_snack_project.model.Employee;
import com.chichos_snack_project.model.Product;
import com.chichos_snack_project.model.Sale;
import com.chichos_snack_project.util.AppConfig;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class SaleService {

    private static final java.util.logging.Logger log = Logger.getLogger(SaleService.class.getName());
    private static final SaleDAOImpl saleDAO = new SaleDAOImpl(AppConfig.getDatasource());

    public static List<Sale> getSales(){
        List<Sale> saleList = new LinkedList<>();
        try(ResultSet rs = saleDAO.findAll()){
            int count = 1;
            if(rs !=null){
                while (rs.absolute(count)) {
                    count++;
                    int id = rs.getInt(1);
                    Employee employee = new Employee(rs.getInt(4),rs.getString(5),"",0,"",null,"");
                    Customer customer = new Customer(rs.getInt(2),rs.getString(3),"",null,0,0,0,0);
                    java.sql.Date date = rs.getDate(11);
                    double amount = rs.getDouble(10);
                    List<Product> productList = new LinkedList<>();
                    Product product = new Product(rs.getInt(6),rs.getString(7),rs.getDouble(8),rs.getInt(9),null,null);
                    productList.add(product);
                    rs.beforeFirst();
                    while (rs.next()){
                        int id_compare = rs.getInt(1);
                        if(id == id_compare ){
                            Product products = new Product(rs.getInt(6),rs.getString(7),rs.getDouble(8),rs.getInt(9),null,null);
                            if(!productList.contains(products)){
                                productList.add(products);
                            }
                        }
                    }
                    Sale sale = new Sale(id,employee,customer,date,amount,productList.size(),productList);
                    if(!saleList.contains(sale)){
                        saleList.add(sale);
                    }
                }
            }else{
                saleList.add(new Sale(0,null,null,null,0,0,null));
                log.severe("No se encontraron ventas en la base de datos");
            }
            return saleList;
        }catch (SQLException e){
            saleList.add(new Sale(0,null,null,null,0,0,null));
            log.severe("Hubo un error al procesar la el resulset obtenido por el metodo getSales de SaleDAOImpl state: "+e.getSQLState() +" "+e.getMessage());
            return saleList;
        }
    }
    public static double sumSales(){
        double total = 0;
        try(ResultSet rs = saleDAO.sumAmountSales()) {
            if(rs.next()){
                total = rs.getDouble(1);
            }
        } catch (SQLException e) {
            log.severe("Hubo un error al procesar la el resulset obtenido por el metodo sumAmountSales de SaleDAOImpl state: "+e.getSQLState());
        }
        return total;
    }


}
