package com.chichos_snack_project.service;

import com.chichos_snack_project.dao.InventoryDAOImpl;
import com.chichos_snack_project.model.Category;
import com.chichos_snack_project.model.Inventory;
import com.chichos_snack_project.model.Product;
import com.chichos_snack_project.model.UnitOfMeasurement;
import com.chichos_snack_project.util.AppConfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class InventoryService {

    private static final java.util.logging.Logger log = Logger.getLogger(InventoryService.class.getName());
    private static final InventoryDAOImpl inventoryDAO = new InventoryDAOImpl(AppConfig.getDatasource());

    public static List<Inventory> getAllInventory() {
        List<Inventory> inventoryList = new LinkedList<>();
        try {
            inventoryDAO.open(AppConfig.getDatasource());
            try(ResultSet rs = inventoryDAO.findAll()){
                if (rs !=null){
                    while (rs.next()){
                        inventoryList.add(new Inventory(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDate(4),rs.getDate(5),rs.getDouble(6),new Product(rs.getInt(7),rs.getString(8),0,0,new Category(rs.getInt(10), rs.getString(11),""),new UnitOfMeasurement(rs.getInt(12),rs.getString(13),""))));
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
        } catch (SQLException e) {
            log.severe("Ocurrio un error en la apertura de la conexion en el metodo getAllInventory de InventoryService state: "+e.getSQLState());
            inventoryList.add(new Inventory(0,0,"error",null,null,0,new Product(0,"",0,0,new Category(0,"",""),new UnitOfMeasurement(0,"",""))));
            return inventoryList;
        }
    }
    public static boolean createInventory(String lot,String expiration,String purchase,String stock,String buy_price,String id_product){
        try {
            inventoryDAO.open(AppConfig.getDatasource());
            checkNotNull(lot,"El parametro lot no puede ser nulo");
            checkNotNull(expiration,"El parametro expiration no puede ser nulo");
            checkNotNull(purchase,"El parametro purchase no puede ser nulo");
            checkNotNull(stock,"El parametro stock no puede ser nulo");
            checkNotNull(buy_price,"El parametro buy_price no puede ser nulo");
            checkNotNull(id_product,"El parametro id_product no puede ser nulo");
            checkArgument(!lot.isEmpty(),"El lote no puede estar vacio");
            checkArgument(!expiration.isEmpty(),"La fecha de expiracion no puede estar vacia");
            checkArgument(!purchase.isEmpty(),"La fecha de compra no puede estar vacia");
            checkArgument(!stock.isEmpty(),"El stock no puede estar vacio");
            checkArgument(!buy_price.isEmpty(),"El precio de compra no puede estar vacio");
            checkArgument(!id_product.isEmpty(),"El id del producto no puede estar vacio");
            checkArgument(lot.matches("[a-zA-Z0-9]+"),"El lote solo puede tener letras o numeros");
            checkArgument(stock.matches("\\d+"),"El stock debe ser un numero");
            checkArgument(buy_price.matches("\\d+(\\.\\d+)?"),"El precio de compra debe ser un numero");
            checkArgument(id_product.matches("\\d+"),"El id del producto debe ser un numero");
            checkArgument(expiration.matches("\\d{4}-\\d{2}-\\d{2}"),"La fecha de expiracion debe tener el formato yyyy-mm-dd");
            checkArgument(purchase.matches("\\d{4}-\\d{2}-\\d{2}"),"La fecha de compra debe tener el formato yyyy-mm-dd");
            java.sql.Date expiration_cast = java.sql.Date.valueOf(expiration);
            java.sql.Date purchase_cast = java.sql.Date.valueOf(purchase);
            int stock_cast = Integer.parseInt(stock);
            double buy_price_cast = Double.parseDouble(buy_price);
            checkArgument(stock_cast > 0,"El stock debe ser mayor a 0");
            checkArgument(buy_price_cast > 0,"El precio de compra debe ser mayor a 0");
            int id_product_cast = Integer.parseInt(id_product);
            checkArgument(id_product_cast > 0,"El id del producto debe ser mayor a 0");
            inventoryDAO.create(new Inventory(0,stock_cast,lot,expiration_cast,purchase_cast,buy_price_cast,new Product(id_product_cast,"",0,0,new Category(0,"",""),new UnitOfMeasurement(0,"",""))));
            return true;
        } catch (NullPointerException e){
            log.severe("Hubo un error en la creacion del inventario porque un argumento es nulo state: "+e.getMessage());
            return false;
        }catch (IllegalArgumentException e){
            log.severe("Hubo un error en la creacion del inventario porque un argumento no cumple las condiciones de checkArgument state: "+e.getMessage());
            return false;
        }catch (SQLException e){
            log.severe("Hubo un error en la creacion del inventario state: "+e.getSQLState());
            return false;
        }finally {
            try{
                inventoryDAO.close();
            }catch (SQLException e){
                log.info("No se pudo cerrar la conexion en el metodo createInventory de InventoryService state: "+e.getSQLState());
            }
        }
    }
    public static boolean createInventoryOfProduct(String lot,String expiration,String purchase,String stock,String buy_price){
        try {
            inventoryDAO.open(AppConfig.getDatasource());
            checkNotNull(lot,"El parametro lot no puede ser nulo");
            checkNotNull(expiration,"El parametro expiration no puede ser nulo");
            checkNotNull(purchase,"El parametro purchase no puede ser nulo");
            checkNotNull(stock,"El parametro stock no puede ser nulo");
            checkNotNull(buy_price,"El parametro buy_price no puede ser nulo");
            checkArgument(!lot.isEmpty(),"El lote no puede estar vacio");
            checkArgument(!expiration.isEmpty(),"La fecha de expiracion no puede estar vacia");
            checkArgument(!purchase.isEmpty(),"La fecha de compra no puede estar vacia");
            checkArgument(!stock.isEmpty(),"El stock no puede estar vacio");
            checkArgument(!buy_price.isEmpty(),"El precio de compra no puede estar vacio");
            stock = stock.trim();
            buy_price = buy_price.trim();
            checkArgument(stock.matches("\\d+"),"El stock debe ser un numero");
            checkArgument(buy_price.matches("\\d+(\\.\\d+)?"),"El precio de compra debe ser un numero");
            checkArgument(expiration.matches("\\d{4}-\\d{2}-\\d{2}"),"La fecha de expiracion debe tener el formato yyyy-mm-dd");
            checkArgument(purchase.matches("\\d{4}-\\d{2}-\\d{2}"),"La fecha de compra debe tener el formato yyyy-mm-dd");

            java.sql.Date expiration_cast = java.sql.Date.valueOf(expiration);
            java.sql.Date purchase_cast = java.sql.Date.valueOf(purchase);
            int stock_cast = Integer.parseInt(stock);
            double buy_price_cast = Double.parseDouble(buy_price);
            checkArgument(stock_cast > 0,"El stock debe ser mayor a 0");
            checkArgument(buy_price_cast > 0,"El precio de compra debe ser mayor a 0");
            inventoryDAO.createOfProduct(new Inventory(0,stock_cast,lot,expiration_cast,purchase_cast,buy_price_cast,new Product(0,"",0,0,new Category(0,"",""),new UnitOfMeasurement(0,"",""))));
            return true;
        } catch (NullPointerException e) {
            log.severe("Hubo un error en la creacion del inventario porque un argumento es nulo state: "+e.getMessage());
            return false;
        }catch (IllegalArgumentException e){
            log.severe("Hubo un error en la creacion del inventario porque un argumento no cumple las condiciones de checkArgument state: "+e.getMessage());
            return false;
        }catch (SQLException e){
            log.severe("Hubo un error en la creacion del inventario state: "+e.getMessage());
            return false;
        }finally {
            try{
                inventoryDAO.close();
            }catch (SQLException e){
                log.info("No se pudo cerrar la conexion en el metodo createInventory de InventoryService state: "+e.getSQLState());
            }
        }
    }

    public static boolean UpdateInventory(String id_inventory,String expired,String purchase,String stock,String buy_purchase) {
        try {
            inventoryDAO.open(AppConfig.getDatasource());
            checkNotNull(id_inventory, "El parametro id_inventory no puede ser nulo");
            checkNotNull(expired, "El parametro expired no puede ser nulo");
            checkNotNull(purchase, "El parametro purchase no puede ser nulo");
            checkNotNull(stock, "El parametro stock no puede ser nulo");
            checkNotNull(buy_purchase, "El parametro buy_purchase no puede ser nulo");
            checkArgument(!id_inventory.isEmpty(), "El id del inventario no puede estar vacio");
            checkArgument(!expired.isEmpty(), "La fecha de expiracion no puede estar vacia");
            checkArgument(!purchase.isEmpty(), "La fecha de compra no puede estar vacia");
            checkArgument(!stock.isEmpty(), "El stock no puede estar vacio");
            checkArgument(!buy_purchase.isEmpty(), "El precio de compra no puede estar vacio");
            stock = stock.trim();
            buy_purchase = buy_purchase.trim();
            checkArgument(id_inventory.matches("\\d+"), "El id del inventario debe ser un numero");
            checkArgument(stock.matches("\\d+"), "El stock debe ser un numero");
            checkArgument(buy_purchase.matches("\\d+(\\.\\d+)?"), "El precio de compra debe ser un numero");
            checkArgument(expired.matches("\\d{4}-\\d{2}-\\d{2}"), "La fecha de expiracion debe tener el formato yyyy-mm-dd");
            checkArgument(purchase.matches("\\d{4}-\\d{2}-\\d{2}"), "La fecha de compra debe tener el formato yyyy-mm-dd");
            int id_inventory_cast = Integer.parseInt(id_inventory);
            java.sql.Date expired_cast = java.sql.Date.valueOf(expired);
            java.sql.Date purchase_cast = java.sql.Date.valueOf(purchase);
            int stock_cast = Integer.parseInt(stock);
            double buy_purchase_cast = Double.parseDouble(buy_purchase);
            checkArgument(stock_cast > 0, "El stock debe ser mayor a 0");
            checkArgument(buy_purchase_cast > 0, "El precio de compra debe ser mayor a 0");
            checkArgument(id_inventory_cast > 0, "El id del inventario debe ser mayor a 0");
            inventoryDAO.update(id_inventory_cast, new Inventory(id_inventory_cast, stock_cast, "", expired_cast, purchase_cast, buy_purchase_cast, new Product(0, "", 0, 0, new Category(0, "", ""), new UnitOfMeasurement(0, "", ""))));
            log.info("Se actualizo el inventario correctamente id-inventario:"+id_inventory_cast);
            return true;
        } catch (NullPointerException e) {
            log.severe("Hubo un error al actualizar el inventario porque un argumento es nulo state: " + e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            log.severe("Hubo un error al actualizar el inventario  porque un argumento no cumple las condiciones de checkArgument state: " + e.getMessage());
            return false;
        } catch (SQLException e) {
            log.severe("Hubo un error al actualizar el inventario state: " + e.getSQLState());
            return false;
        }finally {
            try{
                inventoryDAO.close();
            }catch (SQLException e){
                log.info("No se pudo cerrar la conexion en el metodo createInventory de InventoryService state: "+e.getSQLState());
            }
        }
    }

    public static boolean deleteInventory(String id_inventory,String code,String code_entered){
        try{
            checkNotNull(id_inventory,"El id del inventario no puede ser nulo");
            checkNotNull(code,"El codigo no puede ser nulo");
            checkNotNull(code_entered,"El codigo ingresado no puede ser nulo");
            checkArgument(!id_inventory.isEmpty(),"El id del inventario no puede estar vacio");
            checkArgument(!code.isEmpty(),"El codigo no puede estar vacio");
            checkArgument(!code_entered.isEmpty(),"El codigo ingresado no puede estar vacio");
            checkArgument(id_inventory.matches("\\d+"),"El id del inventario debe ser un numero");
            checkArgument(code.matches("\\d+"),"El codigo debe ser un numero");
            checkArgument(code_entered.matches("\\d+"),"El codigo ingresado debe ser un numero");
            int id_inventory_cast = Integer.parseInt(id_inventory);
            checkArgument(id_inventory_cast > 0,"El id del inventario debe ser mayor a 0");
            if(code.equals(code_entered)){
                try {
                    inventoryDAO.open(AppConfig.getDatasource());
                    inventoryDAO.delete(id_inventory_cast);
                    log.info("Sele elimino el inventario con id:"+id_inventory_cast);
                    return true;
                } catch (SQLException e) {
                    log.severe("Hubo un error al eliminar el inventario state: "+e.getSQLState());
                    return false;
                }finally {
                    try{
                        inventoryDAO.close();
                    }catch (SQLException e) {
                        log.info("No se pudo cerrar la conexion en el metodo createInventory de InventoryService state: " + e.getSQLState());
                    }
                }
            }else{
                log.info("No se pudo eliminar el inventario por que los codigos no son iguales");
                return false;
            }
        }catch (NullPointerException e){
            log.severe("Hubo un error al eliminar el inventario porque un argumento es nulo state: "+e.getMessage());
            return false;
        }catch (IllegalArgumentException e){
            log.severe("Hubo un error al eliminar el inventario porque un argumento no cumple las condiciones de checkArgument state: "+e.getMessage());
            return false;
        }
    }
}
