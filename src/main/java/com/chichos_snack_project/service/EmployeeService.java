package com.chichos_snack_project.service;

import com.chichos_snack_project.dao.EmployeeDAPImpl;
import com.chichos_snack_project.model.Employee;
import com.chichos_snack_project.util.AppConfig;
import org.apache.poi.ss.usermodel.*;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class EmployeeService {
    private static final EmployeeDAPImpl employeeDAO = new EmployeeDAPImpl(AppConfig.getDatasource());
    private static final java.util.logging.Logger log = Logger.getLogger(EmployeeService.class.getName());
    public static List<Employee> getEmployees(){
        List<Employee> employeeList = new LinkedList<>();
        try(ResultSet rs = employeeDAO.findAll()){
            if(rs !=null){
                while (rs.next()){
                    employeeList.add(new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getString(5),rs.getDate(6),rs.getString(7)));
                }
            }else{
                employeeList.add(new Employee(0,"Error","Error",0,"Error",null,"Error"));
            }
            return employeeList;
        }catch (SQLException e){
            log.severe("Hubo un error al cargar la data del ResulSet recibido por el metodo findAll de EmployeeDAOImpl");
            employeeList.add(new Employee(0,"Error","Error",0,"Error",null,"Error"));
            return employeeList;
        }
    }
    public static boolean create(String name,String lastname,String dni,String phone,String salary){
        try{
            checkNotNull(name,"El parametro name no puede ser nulo");
            checkNotNull(lastname,"El parametro lastname no puede ser nulo");
            checkNotNull(dni,"El parametro dni no puede ser nulo");
            checkNotNull(phone,"El parametro phone no puede ser nulo");
            checkNotNull(salary,"El parametro salary no puede ser nulo");
            double salary_cast = Double.parseDouble(salary);
            checkArgument(phone.matches("\\d+"),"El telefono solo debe contener numeros");
            checkArgument(salary_cast>0,"El salario ingresado no puede ser negativo");
            Employee employee = new Employee(0,name,lastname,salary_cast,dni,null,phone);
            employeeDAO.create(employee);
            log.info("Se ha registrado un nuevo trabajador \n Nombre: "+name+" "+lastname);
            return true;
        }catch (SQLException e){
            log.severe("Ocurrion un error en el metodo create de EmployeeDAOImpl\n status: "+e.getSQLState());
            return false;
        }catch (NullPointerException | IllegalArgumentException e){
            log.severe(e.getMessage());
            return false;
        }
    }

    public static boolean create(List<Employee> employeeList){
        try {
            for (Employee employee : employeeList){
                employeeDAO.create(employee);
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean update(String name,String lastname,String salary,String dni,String phone,String id){
        try{
            checkNotNull(name,"El parametro name no puede ser nulo");
            checkNotNull(lastname,"El parametro lastname no puede ser nulo");
            checkNotNull(dni,"El parametro dni no puede ser nulo");
            checkNotNull(phone,"El parametro phone no puede ser nulo");
            checkNotNull(salary,"El parametro salary no puede ser nulo");
            checkNotNull(id,"El parametro id no puede ser nulo");
            checkArgument(phone.matches("\\d+"),"El telefono solo debe contener numeros");
            double salary_cast = Double.parseDouble(salary);
            checkArgument(salary_cast>0,"El salario ingresado no puede ser negativo");
            Employee employee = new Employee();
            employee.setId_employee(Integer.parseInt(id));
            Employee employee_data = employeeDAO.read(employee);
            if(employee_data !=null){
                employee_data.setName(name);
                employee_data.setLastname(lastname);
                employee_data.setDni(dni);
                employee_data.setPhone(phone);
                employee_data.setSalary(salary_cast);
                employeeDAO.update(employee_data.getId_employee(),employee_data);
                log.info("Se actualizaron los datos del empleado: "+employee_data.getName());
                return true;
            }else{
                log.severe("El Objeto empleado recibido del metodo read de la clase EmployeeDAOImpl es nulo");
                return false;
            }
        } catch (SQLException e) {
            log.severe("Hubo un error de SQLException con el metodo read o el metodo update de la clase EmployeeDAOImpl");
            throw new RuntimeException(e);
        }catch (NullPointerException | IllegalArgumentException e){
            log.severe(e.getMessage());
            return false;
        }
    }
    public static boolean delete(int id,String code,String code_entered){
        try {
            checkNotNull(code,"El parametro code no puede ser nulo");
            checkNotNull(code_entered,"El parametro code_entered no puede ser nulo");
            if(code.equals(code_entered)){
                try{
                    log.info("Deleting employee with id: " + id);
                    employeeDAO.delete(id);
                    return true;
                }catch (SQLException e){
                    log.severe("No se pudo eliminar al empleado por una SQLException en el metodo delete de EmployeeDAOImpl "+e.getSQLState());
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
    public static List<Employee>readFile(Part file){
        List<Employee> employeeList = new LinkedList<>();
        try {
            InputStream is = file.getInputStream();
            Workbook wb = WorkbookFactory.create(is);
            Sheet sheet = wb.getSheetAt(0);
            for(Row row : sheet){
                List<String> listData = getStrings(row);
                Employee employee = new Employee(0,listData.get(0),listData.get(1),Double.parseDouble(listData.get(2)),listData.get(3),null, listData.get(5));
                employeeList.add(employee);
            }
            return employeeList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> getStrings(Row row) {
        List<String> listData = new LinkedList<>();
        for(Cell cell: row){
            switch (cell.getCellType()){
                case STRING:
                    listData.add(cell.getStringCellValue());
                    break;
                case NUMERIC:
                    listData.add(String.valueOf((long)cell.getNumericCellValue()));
                    break;
                default:
                    listData.add("UNKNOWN");
                    break;
            }
        }
        return listData;
    }

}
