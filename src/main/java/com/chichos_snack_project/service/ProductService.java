package com.chichos_snack_project.service;

import com.chichos_snack_project.dao.ProductDAOImpl;
import com.chichos_snack_project.model.Category;
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

public class ProductService  {
    private static final java.util.logging.Logger log = Logger.getLogger(ProductService.class.getName());
    private static final ProductDAOImpl productDAO = new ProductDAOImpl(AppConfig.getDatasource());
    public static List<Product> getProducts(){
        List<Product> productsList = new LinkedList<>();
        try{
            ResultSet rs = productDAO.findAll();
            if(rs !=null){
                while (rs.next()){
                    productsList.add(new Product(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getInt(4),new Category(rs.getInt(5),rs.getString(6),""),new UnitOfMeasurement(rs.getInt(7),rs.getString(8),"")));
                }
            }else{
                productsList.add(new Product(0,"Error",0,0,new Category(0,"error","error"),new UnitOfMeasurement(0,"error","error")));
            }
            return productsList;
        }catch (SQLException e){
            log.severe("Hubo un error al cargar la data del ResulSet recibido por el metodo findAll de ProductDAOImpl state: "+e.getSQLState());
            productsList.add(new Product(0,"Error",0,0,new Category(0,"error","error"),new UnitOfMeasurement(0,"error","error")));
            return productsList;
        }
    }

    public static boolean createProduct (String name,String price,String id_unit,String id_category){
        try{
            checkNotNull(name,"El parametro name no puede ser nulo");
            checkNotNull(price,"El parametro price no puede ser nulo");
            checkNotNull(id_unit,"El parametro id_unit no puede ser nulo");
            checkNotNull(id_category,"El parametro id_category no puede ser nulo");
            id_unit = id_unit.trim();
            id_category = id_category.trim();
            checkArgument(price.matches("\\d+(\\.\\d+)?"),"El precio debe ser un numero");
            checkArgument(id_unit.matches("\\d+"),"El id de la unidad de medida debe ser un numero");
            checkArgument(id_category.matches("\\d+"),"El id de la categoria debe ser un numero");
            double price_cast = Double.parseDouble(price);
            int id_unit_cast = Integer.parseInt(id_unit);
            int id_category_cast = Integer.parseInt(id_category);
            productDAO.create(new Product(0,name,price_cast,0,new Category(id_category_cast,"",""),new UnitOfMeasurement(id_unit_cast,"","")));
            return true;
        }catch (NullPointerException e){
            log.severe("Hubo un error al crear el producto porque un argumento es nulo state: "+e.getMessage());
            return false;
        }catch (IllegalArgumentException e){
            log.severe("Hubo un error al crear el producto  porque un argumento no cumple las condiciones de checkArgument state: "+e.getMessage());
            return false;
        }
        catch (SQLException e){
            log.severe("Hubo un error al crear el producto state: "+e.getSQLState());
            return false;
        }

    }

    public static boolean UpdateProduct(String id_product,String name,String price,String unit,String category){
        try{
            checkNotNull(id_product,"El parametro id_product no puede ser nulo");
            checkNotNull(name,"El parametro name no puede ser nulo");
            checkNotNull(price,"El parametro price no puede ser nulo");
            checkNotNull(unit,"El parametro unit no puede ser nulo");
            checkNotNull(category,"El parametro category no puede ser nulo");
            id_product = id_product.trim();
            name = name.trim();
            price = price.trim();
            unit = unit.trim();
            category = category.trim();
            checkArgument(id_product.matches("\\d+"),"El id del producto debe ser un numero");
            checkArgument(price.matches("\\d+(\\.\\d+)?"),"El precio debe ser un numero");
            checkArgument(unit.matches("\\d+"),"El id de la unidad de medida debe ser un numero");
            checkArgument(category.matches("\\d+"),"El id de la categoria debe ser un numero");
            int id_product_cast = Integer.parseInt(id_product);
            double price_cast = Double.parseDouble(price);
            checkArgument(price_cast>0,"El precio debe ser mayor a 0");
            int id_unit_cast = Integer.parseInt(unit);
            int id_category_cast = Integer.parseInt(category);
            productDAO.update(id_product_cast,new Product(id_product_cast,name,price_cast,0,new Category(id_category_cast,"",""),new UnitOfMeasurement(id_unit_cast,"","")));
            return true;
        }catch (NullPointerException e){
            log.severe("Hubo un error al actualizar el producto porque un argumento es nulo state: "+e.getMessage());
            return false;
        }catch (IllegalArgumentException e){
            log.severe("Hubo un error al actualizar el producto  porque un argumento no cumple las condiciones de checkArgument state: "+e.getMessage());
            return false;
        }
        catch (SQLException e){
            log.severe("Hubo un error al actualizar el producto state: "+e.getSQLState());
            return false;
        }
    }
}
