package com.chichos_snack_project.service;

import com.chichos_snack_project.dao.ProductDAOImpl;
import com.chichos_snack_project.model.Employee;
import com.chichos_snack_project.model.Product;
import com.chichos_snack_project.util.AppConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class View_data_products {

    private static final Logger log = LogManager.getLogger(View_data_products.class);
    private static final ProductDAOImpl productDAO = new ProductDAOImpl(AppConfig.getDatasource());

    public static List<Product> getProducts(){
        List<Product> productsList = new LinkedList<>();
        try{
            ResultSet rs = productDAO.findAll();
            if(rs !=null){
                while (rs.next()){
                    productsList.add(new Product(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getInt(4)));
                }
            }else{
                productsList.add(new Product(0,"Error",0,0));
            }
            return productsList;
        }catch (SQLException e){
            log.error("Hubo un error al cargar la data del ResulSet recibido por el metodo findAll de ProductDAOImpl");
            productsList.add(new Product(0,"Error",0,0));
            return productsList;
        }
    }
}
