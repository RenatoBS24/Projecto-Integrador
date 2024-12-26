package com.chichos_snack_project.service;
import com.chichos_snack_project.dao.ProductDAOImpl;
import com.chichos_snack_project.model.Category;
import com.chichos_snack_project.model.Product;
import com.chichos_snack_project.model.UnitOfMeasurement;
import com.chichos_snack_project.util.AppConfig;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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
        try {
            productDAO.open(AppConfig.getDatasource());
            try(ResultSet rs = productDAO.findAll()){
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
        } catch (SQLException e) {
            log.severe("Hubo un error al abrir la conexion en el metodo getProducts de ProductService state: "+e.getSQLState());
            productsList.add(new Product(0,"Error",0,0,new Category(0,"error","error"),new UnitOfMeasurement(0,"error","error")));
            return productsList;

        }
    }
    public static List<Product> ProductsMostSale(){
        List<Product> productsList = new LinkedList<>();
        try {
            productDAO.open(AppConfig.getDatasource());
            try(ResultSet rs = productDAO.mostSale()){
                if(rs !=null){
                    while(rs.next()){
                        productsList.add(new Product(0,rs.getString(1),0,0,new Category(0,"",""),new UnitOfMeasurement(0,"","")));
                    }
                }else{
                    productsList.add(new Product(0,"Error",0,0,new Category(0,"error","error"),new UnitOfMeasurement(0,"error","error")));
                }
            }catch (SQLException e){
                log.severe("Hubo un error al cargar la data del ResulSet recibido por el metodo mostSale de ProductDAOImpl state: "+e.getSQLState());
                productsList.add(new Product(0,"Error",0,0,new Category(0,"error","error"),new UnitOfMeasurement(0,"error","error")));
            }
        } catch (SQLException e) {
           log.severe("Hubo un error al abrir la conexion en el metodo ProductsMostSale de ProductService state: "+e.getSQLState());
            productsList.add(new Product(0,"Error",0,0,new Category(0,"error","error"),new UnitOfMeasurement(0,"error","error")));
        }
        return productsList;
    }

    public static boolean createProduct (String name,String price,String id_unit,String id_category){
        try{
            productDAO.open(AppConfig.getDatasource());
            checkNotNull(name,"El parametro name no puede ser nulo");
            checkNotNull(price,"El parametro price no puede ser nulo");
            checkNotNull(id_unit,"El parametro id_unit no puede ser nulo");
            checkNotNull(id_category,"El parametro id_category no puede ser nulo");
            // Valida que los argumentos no esten vacios
            checkArgument(!name.isEmpty(),"El nombre del producto no puede estar vacio");
            checkArgument(!price.isEmpty(),"El precio no puede estar vacio");
            checkArgument(!id_unit.isEmpty(),"El id de la unidad de medida no puede estar vacio");
            checkArgument(!id_category.isEmpty(),"El id de la categoria no puede estar vacio");
            id_unit = id_unit.trim();
            id_category = id_category.trim();
            // Valida que el nombre del prodcuto solo tenga letras o espacios
            checkArgument(name.matches("^([A-Za-záéíóúÁÉÍÓÚñÑ]+)(\\s[A-Za-záéíóúÁÉÍÓÚñÑ]+)*$"), "El nombre del producto debe contener al menos una letra y solo puede contener letras y espacios");
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
        }finally {
            try {
                productDAO.close();
            } catch (SQLException e) {
                log.severe("Hubo un error al cerrar la conexion en el metodo createProduct de ProductService state: "+e.getSQLState());
            }
        }

    }

    public static boolean UpdateProduct(String id_product, String name, String price, String unit, String category){
        try{
            productDAO.open(AppConfig.getDatasource());
            checkNotNull(id_product,"El parametro id_product no puede ser nulo");
            checkNotNull(name,"El parametro name no puede ser nulo");
            checkNotNull(price,"El parametro price no puede ser nulo");
            checkNotNull(unit,"El parametro unit no puede ser nulo");
            checkNotNull(category,"El parametro category no puede ser nulo");
            // valida que los parametros no esten vacios
            checkArgument(!id_product.isEmpty(),"El id del producto no puede estar vacio");
            checkArgument(!name.isEmpty(),"El nombre del producto no puede estar vacio");
            checkArgument(!price.isEmpty(),"El precio no puede estar vacio");
            checkArgument(!unit.isEmpty(),"El id de la unidad de medida no puede estar vacio");
            checkArgument(!category.isEmpty(),"El id de la categoria no puede estar vacio");
            // valida que el name product solo tenga letras o espacios
            checkArgument(name.matches("^([A-Za-záéíóúÁÉÍÓÚñÑ]+)(\\s[A-Za-záéíóúÁÉÍÓÚñÑ]+)*$"), "El nombre del producto debe contener al menos una letra y solo puede contener letras y espacios");
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
        }finally {
            try {
                productDAO.close();
            } catch (SQLException e) {
                log.severe("Hubo un error al cerrar la conexion en el metodo UpdateProduct de ProductService state: "+e.getSQLState());
            }
        }
    }

    public static boolean deleteProduct(String id_product, String code, String code_entered){
        try{
            checkNotNull(code,"El parametro code no puede ser nulo");
            checkNotNull(code_entered,"El parametro code_entered no puede ser nulo");
            // valida que los argumentos no esten vacios
            checkArgument(!code.isEmpty(),"El codigo no puede estar vacio");
            checkArgument(!code_entered.isEmpty(),"El codigo ingresado no puede estar vacio");
            checkArgument(code.matches("\\d+"),"El codigo solo debe contener numeros");
            checkArgument(code_entered.matches("\\d+"),"El codigo ingresado solo debe contener numeros");
            checkNotNull(id_product,"El parametro id_product no puede ser nulo");
            id_product = id_product.trim();
            checkArgument(id_product.matches("\\d+"),"El id del producto debe ser un numero");
            checkArgument(!id_product.equals("0"),"El id del producto no puede ser 0");
            if (code.equals(code_entered)) {
                try {
                    productDAO.open(AppConfig.getDatasource());
                    int id_product_cast = Integer.parseInt(id_product);
                    productDAO.delete(id_product_cast);
                    return true;
                } catch (SQLException e) {
                    log.severe("No se pudo eliminar el producto por una SQLException en el metodo delete de ProductDAOImpl " + e.getSQLState());
                    return false;
                }finally {
                    try {
                        productDAO.close();
                    } catch (SQLException e) {
                        log.severe("Hubo un error al cerrar la conexion en el metodo deleteProduct de ProductService state: "+e.getSQLState());
                    }
                }
            }else{
                log.info("Los codigos ingresados no son iguales");
                return false;
            }
        }catch (NullPointerException e){
            log.severe("Hubo un error al eliminar el producto porque un argumento es nulo state: "+e.getMessage());
            return false;
        }catch (IllegalArgumentException e){
            log.severe("Hubo un error al eliminar el producto  porque un argumento no cumple las condiciones de checkArgument state: "+e.getMessage());
            return false;
        }
    }

    public static void createReport(String id_user,String id_category,OutputStream outputStream){
        try{
            productDAO.open(AppConfig.getDatasource());
            checkNotNull(id_user,"El parametro id_user no puede ser nulo");
            checkNotNull(id_category,"El parametro id_category no puede ser nulo");
            checkArgument(id_user.matches("\\d+"),"El id del usuario debe ser un numero");
            checkArgument(id_category.matches("\\d+"),"El id de la categoria debe ser un numero");
            int id_user_cast = Integer.parseInt(id_user);
            int id_category_cast = Integer.parseInt(id_category);
            checkArgument(id_user_cast>0,"El id del usuario debe ser mayor a 0");
            try(ResultSet rs = productDAO.getProductsByCategory(id_category_cast);Workbook workbook = new XSSFWorkbook()){
                if(rs !=null){
                    String safeName = WorkbookUtil.createSafeSheetName("Reporte de productos");
                    Sheet sheet = workbook.createSheet(safeName);
                    //Header
                    Row row = sheet.createRow(0);
                    createCell(workbook, row, 0,"Producto");
                    createCell(workbook, row, 1,"Categoria");
                    createCell(workbook, row, 2, "Precio");
                    createCell(workbook, row, 3,"Stock");
                    int rowId = 1;
                    while(rs.next()){
                        Row rowItem = sheet.createRow(rowId++);
                        createCell(workbook, rowItem, 0,rs.getString(2));
                        createCell(workbook, rowItem, 1,rs.getString(6));
                        createCell(workbook, rowItem, 2, rs.getDouble(3));
                        createCell(workbook, rowItem, 3,rs.getInt(4));
                    }
                    workbook.write(outputStream);
                    log.info("El usuario:"+id_user_cast+" ha creado un reporte de productos");
                }

            }catch (IOException e){
                log.severe("Hubo un error al crear el reporte state: "+e.getMessage());
            }
            catch (SQLException e){
                log.severe("Hubo un error al crear el reporte state: "+e.getSQLState());
            }
        } catch (NullPointerException e) {
            log.severe("Hubo un error al crear el reporte porque un argumento es nulo state: " + e.getMessage());
        }catch (IllegalArgumentException e){
            log.severe("Hubo un error al crear el reporte porque un argumento no cumple las condiciones de checkArgument state: "+e.getMessage());
        }catch (SQLException e) {
            log.severe("Hubo un error al abrir la conexion en el metodo createReport de ProductService state: " + e.getSQLState());

        }
    }

    private static void createCell(Workbook wb,Row row,int column,Object val){
        CreationHelper creationHelper = wb.getCreationHelper();
        Cell cell = row.createCell(column);
        CellStyle cellStyle = wb.createCellStyle();
        if(val instanceof String){
            cell.setCellValue((String) val);
        }else if(val instanceof Integer){
            cell.setCellValue((Integer) val);
        } else if (val instanceof Double){
            cell.setCellValue((Double) val);
        }
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cell.setCellStyle(cellStyle);
    }

    public static Product productForSale(String id,String quantity,String price,String name){
        try{
            checkNotNull(id,"El parametro id no puede ser nulo");
            checkNotNull(quantity,"El parametro quantity no puede ser nulo");
            checkNotNull(price,"El parametro price no puede ser nulo");
            checkNotNull(name,"El parametro name no puede ser nulo");
            checkArgument(!id.isEmpty(),"El id del producto no puede estar vacio");
            checkArgument(!quantity.isEmpty(),"La cantidad no puede estar vacia");
            checkArgument(!price.isEmpty(),"El precio no puede estar vacio");
            checkArgument(!name.isEmpty(),"El nombre del producto no puede estar vacio");
            checkArgument(name.matches("^([A-Za-záéíóúÁÉÍÓÚñÑ]+)(\\s[A-Za-záéíóúÁÉÍÓÚñÑ]+)*$"), "El nombre del producto debe contener al menos una letra y solo puede contener letras y espacios");
            checkArgument(id.matches("\\d+"),"El id del producto debe ser un numero");
            checkArgument(quantity.matches("\\d+"),"La cantidad debe ser un numero");
            checkArgument(price.matches("\\d+(\\.\\d+)?"),"El precio debe ser un numero");
            int id_cast = Integer.parseInt(id);
            int quantity_cast = Integer.parseInt(quantity);
            double price_cast = Double.parseDouble(price);
            // valida que id_cast sea mayor a 0
            checkArgument(id_cast>0,"El id del producto debe ser mayor a 0");
            // valida que quantity_cast sea mayor a 0
            checkArgument(quantity_cast>0,"La cantidad debe ser mayor a 0");
            // valida que price_cast sea mayor a 0
            checkArgument(price_cast>0,"El precio debe ser mayor a 0");
            return new Product(id_cast,name,price_cast,quantity_cast,new Category(0,"",""),new UnitOfMeasurement(0,"",""));
        }catch (NullPointerException | IllegalArgumentException e) {
            log.severe("Hubo un error al crear el producto para la venta porque un argumento es nulo state: " + e.getMessage());
            return new Product(0, "", 0, 0, new Category(0, "", ""), new UnitOfMeasurement(0, "", ""));
        }
    }
    public static HashMap<Integer,Product> updateQuantity(String quantity,String id,HashMap<Integer,Product> cart) {
        try{
            checkNotNull(quantity,"El parametro quantity no puede ser nulo");
            checkArgument(!quantity.isEmpty(),"La cantidad no puede estar vacia");
            checkArgument(quantity.matches("\\d+"),"La cantidad debe ser un numero");
            checkNotNull(id,"El parametro id no puede ser nulo");
            checkArgument(!id.isEmpty(),"El id del producto no puede estar vacio");
            checkArgument(id.matches("\\d+"),"El id del producto debe ser un numero");
            int id_cast = Integer.parseInt(id);
            checkArgument(id_cast>0,"El id del producto debe ser mayor a 0");
            int quantity_cast = Integer.parseInt(quantity);

            if (quantity_cast>0) {
                Product product = cart.get(id_cast);
                product.setStock(quantity_cast);
                cart.put(id_cast,product);
            }else{
                cart.remove(id_cast);
            }
            return cart;
        }catch (NullPointerException | IllegalArgumentException e){
            log.severe("Hubo un error al actualizar la cantidad del producto en el carrito porque un argumento es nulo state: "+e.getMessage());
            return cart;
        }
    }

}
