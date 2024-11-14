package com.chichos_snack_project.service;

import com.chichos_snack_project.dao.InventoryDAOImpl;
import com.chichos_snack_project.model.Category;
import com.chichos_snack_project.model.Inventory;
import com.chichos_snack_project.model.Product;
import com.chichos_snack_project.util.AppConfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class InventoryService {

    private static final java.util.logging.Logger log = Logger.getLogger(InventoryService.class.getName());
    private static final InventoryDAOImpl inventoryDAO = new InventoryDAOImpl(AppConfig.getDatasource());

    public static List<Inventory> getAllInventory() {
        List<Inventory> inventoryList = new LinkedList<>();
        try{
            ResultSet rs = inventoryDAO.findAll();
            if (rs !=null){
                while (rs.next()){
                    log.info(rs.getString(10));
                    log.info(rs.getString(11));
                    inventoryList.add(new Inventory(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDate(4),rs.getDate(5),rs.getDouble(6),new Product(rs.getInt(7),rs.getString(8),0,0,new Category(rs.getInt(10), rs.getString(11),""))));
                }
            }else{
                log.severe("El resultset obtenido del metodo findAll de InventoryDAOImpl es nulo");
                inventoryList.add(new Inventory(0,0,"error",null,null,0,null));
            }
            return inventoryList;
        }catch(SQLException e){
            inventoryList.add(new Inventory(0,0,"error",null,null,0,null));
            log.severe("Ocurrio un error en la lectura del metodo findAll de InventoryDAOImpl, state: "+e.getSQLState());
            return inventoryList;
        }
    }

}
