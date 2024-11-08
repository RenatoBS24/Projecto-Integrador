package com.chichos_snack_project.service;

import com.chichos_snack_project.dao.ProductDAOImpl;
import com.chichos_snack_project.model.Category;
import com.chichos_snack_project.model.Product;
import com.chichos_snack_project.util.AppConfig;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class ProductService  {
    private static final java.util.logging.Logger log = Logger.getLogger(ProductService.class.getName());
    private static final ProductDAOImpl productDAO = new ProductDAOImpl(AppConfig.getDatasource());
    public static List<Product> getProducts(){
        List<Product> productsList = new LinkedList<>();
        try{
            ResultSet rs = productDAO.findAll();
            if(rs !=null){
                while (rs.next()){
                    productsList.add(new Product(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getInt(4),new Category(rs.getInt(5),rs.getString(6),"")));
                }
            }else{
                productsList.add(new Product(0,"Error",0,0,new Category(0,"error","error")));
            }
            return productsList;
        }catch (SQLException e){
            log.severe("Hubo un error al cargar la data del ResulSet recibido por el metodo findAll de ProductDAOImpl state: "+e.getSQLState());
            productsList.add(new Product(0,"Error",0,0,new Category(0,"error","error")));
            return productsList;
        }
    }
}
