package com.chichos_snack_project.service;

import com.chichos_snack_project.dao.CategoryDAOImpl;
import com.chichos_snack_project.model.Category;
import com.chichos_snack_project.util.AppConfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class CategoryService {
    private static final java.util.logging.Logger log = Logger.getLogger(CategoryService.class.getName());
    private static final CategoryDAOImpl categoryDAO = new CategoryDAOImpl(AppConfig.getDatasource());

    public static List<Category> getCategory(){
        List<Category> categoryList = new LinkedList<Category>();
        try{
            ResultSet rs = categoryDAO.findAll();
            if(rs != null){
                while (rs.next()) {
                    categoryList.add(new Category(rs.getInt(1),rs.getString(2), rs.getString(3)));
                }
            }else{
                categoryList.add(new Category(0,"Error","Error"));
            }
            return categoryList;

        }catch (SQLException e){
            log.severe("Hubo un error al cargar la data del ResulSet recibido por el metodo findAll de CategoryDAOImpl");
            categoryList.add(new Category(0,"Error","Error"));
            return categoryList;
        }
    }
}
