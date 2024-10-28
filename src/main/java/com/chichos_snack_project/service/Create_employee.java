package com.chichos_snack_project.service;

import com.chichos_snack_project.dao.EmployeeDAPImpl;
import com.chichos_snack_project.model.Employee;
import com.chichos_snack_project.util.AppConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class Create_employee {
    private static final EmployeeDAPImpl employeeDAO = new EmployeeDAPImpl(AppConfig.getDatasource());
    private static final Logger log = LogManager.getLogger(Create_employee.class);
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
            log.error("Ocurrion un error en el metodo create de EmployeeDAOImpl\n status: "+e.getSQLState());
            return false;
        }catch (NullPointerException | IllegalArgumentException e){
            log.error(e.getMessage());
            return false;
        }
    }

    public static boolean create(List<Employee> employeeList){
        try {
            for (Employee employee : employeeList){
                log.info(employee);
                employeeDAO.create(employee);
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<Employee>readFile(Part file){
        List<Employee> employeeList = new LinkedList<>();
        try {
            InputStream is = file.getInputStream();
            Workbook wb = WorkbookFactory.create(is);
            DataFormatter formatter = new DataFormatter();
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
