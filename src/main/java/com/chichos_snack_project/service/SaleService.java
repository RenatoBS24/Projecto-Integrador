package com.chichos_snack_project.service;
import com.chichos_snack_project.dao.SaleDAOImpl;
import com.chichos_snack_project.model.Customer;
import com.chichos_snack_project.model.Employee;
import com.chichos_snack_project.model.Product;
import com.chichos_snack_project.model.Sale;
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
public class SaleService {

    private static final java.util.logging.Logger log = Logger.getLogger(SaleService.class.getName());
    private static final SaleDAOImpl saleDAO = new SaleDAOImpl(AppConfig.getDatasource());

    public static List<Sale> getSales(){
        List<Sale> saleList = new LinkedList<>();
        try(ResultSet rs = saleDAO.findAll()){
            return getListSaleFilter(rs);
        }catch (SQLException e){
            saleList.add(new Sale(0,null,null,null,0,0,null));
            log.severe("Hubo un error al procesar la el resulset obtenido por el metodo getSales de SaleDAOImpl state: "+e.getSQLState() +" "+e.getMessage());
            return saleList;
        }
    }

    public static void createReport(String id_user, String id_customer, String id_employee, String date_start, String date_end, OutputStream outputStream){
        try{
            log.info(id_user+" "+id_customer+" "+id_employee+" "+date_start+" "+date_end);
            checkNotNull(id_user,"El id del usuario no puede ser nulo");
            checkNotNull(id_customer,"El id del cliente no puede ser nulo");
            checkNotNull(id_employee,"El id del empleado no puede ser nulo");
            checkArgument(!id_user.isEmpty(),"El id del usuario no puede estar vacio");
            checkArgument(!id_customer.isEmpty(),"El id del cliente no puede estar vacio");
            checkArgument(!id_employee.isEmpty(),"El id del empleado no puede estar vacio");
            id_user = id_user.trim();
            id_customer = id_customer.trim();
            id_employee = id_employee.trim();
            // valida que los ids sean numeros enteros
            checkArgument(id_user.matches("[0-9]+"),"El id del usuario debe ser un numero entero");
            checkArgument(id_customer.matches("[0-9]+"),"El id del cliente debe ser un numero entero");
            checkArgument(id_employee.matches("[0-9]+"),"El id del empleado debe ser un numero entero");
            int id_user_cast = Integer.parseInt(id_user);
            int id_customer_cast = Integer.parseInt(id_customer);
            int id_employee_cast = Integer.parseInt(id_employee);
            // valida que sean mayores a 0
            checkArgument(id_user_cast > 0,"El id del usuario debe ser mayor a 0");
            checkArgument(id_customer_cast >= 0,"El id del cliente debe ser mayor a 0");
            checkArgument(id_employee_cast >= 0,"El id del empleado debe ser mayor a 0");
            java.sql.Date date_start_cast = null;
            java.sql.Date date_end_cast = null;
            if(!date_start.equalsIgnoreCase("")) {
                date_start = date_start.trim();
                checkArgument(date_start.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}"), "La fecha de inicio debe tener el formato yyyy-mm-dd");
                date_start_cast = java.sql.Date.valueOf(date_start);
            }
            if(!date_end.equalsIgnoreCase("")){
                date_end = date_end.trim();
                checkArgument(date_end.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}"),"La fecha de fin debe tener el formato yyyy-mm-dd");
                date_end_cast = java.sql.Date.valueOf(date_end);
            }
            try(ResultSet rs = saleDAO.createReport(id_employee_cast,id_customer_cast,date_start_cast,date_end_cast); Workbook wb = new XSSFWorkbook()){
                List<Sale> saleList = getListSaleFilter(rs);
                if(!saleList.isEmpty()){
                    String safeName = WorkbookUtil.createSafeSheetName("Reporte de ventas");
                    Sheet sheet = wb.createSheet(safeName);
                    //Header
                    Row row = sheet.createRow(0);
                    createCell(wb, row, 0,"Id de venta");
                    createCell(wb, row, 1,"Empleado");
                    createCell(wb, row, 2, "Cliente");
                    createCell(wb, row, 3,"Fecha de venta");
                    createCell(wb,row,4,"Monto total");
                    int rowId = 1;
                    for(Sale sale: saleList){
                        Row rowItem = sheet.createRow(rowId++);
                        createCell(wb, rowItem, 0,sale.getId_sale());
                        createCell(wb, rowItem, 1,sale.getEmployee().getName());
                        createCell(wb, rowItem, 2,sale.getCustomer().getName());
                        createCell(wb, rowItem, 3,sale.getSale_date());
                        createCell(wb, rowItem, 4,sale.getAmount());
                    }
                    wb.write(outputStream);
                    log.info("El usuario: "+id_user_cast+" ha creado un reporte de ventas");
                }else{
                    log.warning("No se encontraron ventas en el rango de fechas proporcionado");
                }

            }catch (SQLException e){
                log.severe("Hubo un error al procesar el resulset obtenido por el metodo createReport de SaleDAOImpl state: "+e.getSQLState() +" "+e.getMessage());
            }catch (IOException e){
                log.severe("Hubo un error al crear el reporte state: "+e.getMessage());
            }
        }catch (NullPointerException e){
            log.severe("Hubo un error al crear el reporte porque un argumento es nulo state: "+e.getMessage());
        }catch (IllegalArgumentException e){
            log.severe("Hubo un error al crear el reporte porque un argumento es invalido state: "+e.getMessage());
        }
    }

    private static List<Sale> getListSaleFilter(ResultSet rs) throws SQLException{
        List<Sale> saleList = new LinkedList<>();
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
    }

    private static void createCell(Workbook wb, Row row, int column, Object val){
        CreationHelper creationHelper = wb.getCreationHelper();
        Cell cell = row.createCell(column);
        CellStyle cellStyle = wb.createCellStyle();
        if(val instanceof String){
            cell.setCellValue((String) val);
        }else if(val instanceof Integer){
            cell.setCellValue((Integer) val);
        } else if (val instanceof Double){
            cell.setCellValue((Double) val);
        } else if (val instanceof java.sql.Date) {
            cell.setCellValue((java.sql.Date) val);
            cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd/MM/yyyy"));
        }
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cell.setCellStyle(cellStyle);
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

    public static boolean createSale(HashMap<Integer,Product> productList, String amount, String id_employee, String id_Customer,String confirm){
        try{
            checkNotNull(productList,"La lista de productos no puede ser nula");
            checkNotNull(id_employee,"El id del empleado no puede ser nulo");
            checkNotNull(id_Customer,"El id del cliente no puede ser nulo");
            checkNotNull(amount,"El monto total de la venta no puede ser nulo");
            checkNotNull(confirm,"El confirm no puede ser nulo");
            checkArgument(!id_employee.isEmpty(),"El id del empleado no puede estar vacio");
            checkArgument(!id_Customer.isEmpty(),"El id del cliente no puede estar vacio");
            checkArgument(!amount.isEmpty(),"El monto total de la venta no puede estar vacio");
            checkArgument(!productList.isEmpty(),"La lista de productos no puede estar vacia");
            checkArgument(!confirm.isEmpty(),"El confirm no puede estar vacio");
            checkArgument(id_employee.matches("[0-9]+"),"El id del empleado debe ser un numero entero");
            checkArgument(id_Customer.matches("[0-9]+"),"El id del cliente debe ser un numero entero");
            int id_employee_cast = Integer.parseInt(id_employee);
            int id_customer_cast = Integer.parseInt(id_Customer);
            checkArgument(id_employee_cast > 0,"El id del empleado debe ser mayor a 0");
            checkArgument(id_customer_cast > 0,"El id del cliente debe ser mayor a 0");
            // valida que el confirm pueda ser convertido a boolean
            checkArgument(confirm.equalsIgnoreCase("true") || confirm.equalsIgnoreCase("false"),"El confirm debe ser un booleano");
            boolean confirm_cast = Boolean.parseBoolean(confirm);
            int conf = confirm_cast ? 1 : 0;
            LinkedList<Product> products = new LinkedList<>(productList.values());
            double total = 0;
            for(Product product: products){
                total+= product.getPrice()*product.getStock();
            }
            try{
                Employee employee = EmployeeService.getEmployee(id_employee_cast);
                Customer customer = CustomerService.getCustomer(id_customer_cast);
                if (employee.getId_employee() !=0 && customer.getId_customer() !=0) {
                    Sale sale = new Sale(0,employee,customer,null,total,conf,null);
                    saleDAO.create(sale);
                    createSaleProduct(products);
                    log.info("Se ha creado una venta con un monto de: "+amount);
                    return true;
                }else{
                    log.warning("No se encontro el empleado o el cliente");
                    return false;
                }
            }catch (SQLException e){
                log.severe("Hubo un error al procesar la el resulset obtenido por el metodo createSale de SaleDAOImpl state: "+e.getSQLState());
                return false;
            }
        }catch (NullPointerException e){
            log.severe("Hubo un error al crear la venta porque un argumento es nulo state: "+e.getMessage());
            return false;
        }catch (IllegalArgumentException e){
            log.severe("Hubo un error al crear la venta porque un argumento es invalido state: "+e.getMessage());
            return false;
        }

    }
    private static void createSaleProduct(List<Product> productList){
        try{
            for(Product product: productList){
                saleDAO.registerSaleProduct(product.getId_product(),product.getPrice(),product.getStock());
            }
        }catch (SQLException e){
            log.severe("Hubo un error al procesar la el resulset obtenido por el metodo createSaleProduct de SaleDAOImpl state: "+e.getSQLState());
        }

    }

    public static boolean deleteSale(String id_sale,String code,String code_entered){
        try{
            checkNotNull(id_sale,"El id de la venta no puede ser nulo");
            checkNotNull(code,"El codigo de la venta no puede ser nulo");
            checkNotNull(code_entered,"El codigo ingresado no puede ser nulo");
            checkArgument(!id_sale.isEmpty(),"El id de la venta no puede estar vacio");
            checkArgument(!code.isEmpty(),"El codigo de la venta no puede estar vacio");
            checkArgument(!code_entered.isEmpty(),"El codigo ingresado no puede estar vacio");
            checkArgument(id_sale.matches("[0-9]+"),"El id de la venta debe ser un numero entero");
            checkArgument(code.matches("[0-9]+"),"El codigo de la venta debe ser un numero entero");
            checkArgument(code_entered.matches("[0-9]+"),"El codigo ingresado debe ser un numero entero");
            int id_sale_cast = Integer.parseInt(id_sale);
            checkArgument(id_sale_cast > 0,"El id de la venta debe ser mayor a 0");
            if(code.equals(code_entered)){
                try {
                    saleDAO.delete(id_sale_cast);
                    log.info("Se ha eliminado la venta con el id: "+id_sale_cast);
                    return true;
                } catch (SQLException e) {
                    log.severe("Hubo un error al eliminar la venta mediante  deleteSale de SaleDAOImpl state: "+e.getSQLState());
                    return false;
                }
            }else{
                log.warning("El codigo ingresado no coincide con el codigo de la venta");
                return false;
            }
        }catch (NullPointerException e) {
            log.severe("Hubo un error al eliminar la venta porque un argumento es nulo state: " + e.getMessage());
            return false;
        }catch (IllegalArgumentException e){
            log.severe("Hubo un error al eliminar la venta porque un argumento es invalido state: "+e.getMessage());
            return false;
        }
    }


}
