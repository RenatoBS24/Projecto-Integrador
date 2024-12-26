package com.chichos_snack_project.dao;

import com.chichos_snack_project.interfaces.EmployeeDAO;
import com.chichos_snack_project.model.Employee;
import com.chichos_snack_project.util.MysqlConnector;


import java.sql.*;
import java.util.logging.Logger;


public class EmployeeDAOImpl implements EmployeeDAO {

    private Connection con;
    private static final java.util.logging.Logger log = Logger.getLogger(EmployeeDAOImpl.class.getName());

    public EmployeeDAOImpl(String name_datasource) {
        this.con = MysqlConnector.getConnection(name_datasource);
    }

    @Override
    public void create(Employee employee) throws SQLException {
        String sql = "{CALL sp_insert_trabajador(?,?,?,?,?)}";
        CallableStatement cs = con.prepareCall(sql);
        cs.setString(1,employee.getName());
        cs.setString(2,employee.getLastname());
        cs.setString(3,employee.getDni());
        cs.setDouble(4,employee.getSalary());
        cs.setString(5,employee.getPhone());
        cs.executeUpdate();
        log.info("Se registro un nuevo empleado identificado con el nombre: "+employee.getName());
    }

    @Override
    public Employee read(Employee employee) throws SQLException {
        String sql = "{CALL sp_getTrabajador(?)}";
        CallableStatement cs = con.prepareCall(sql);
        cs.setInt(1,employee.getId_employee());
        ResultSet rs = cs.executeQuery();
        if(rs.next()){
            return new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4), rs.getString(5),rs.getDate(6),rs.getString(7));
        }else{
            log.severe("No se pudo obtener al empleado por lo cual se retorna null");
            return null;
        }

    }

    @Override
    public void update(Integer integer, Employee employee) throws SQLException {
        String sql = "{CALL sp_update_trabajador(?,?,?,?,?,?)}";
        CallableStatement cs = con.prepareCall(sql);
        cs.setInt(1, integer);
        cs.setString(2,employee.getName());
        cs.setString(3,employee.getLastname());
        cs.setDouble(4,employee.getSalary());
        cs.setString(5,employee.getDni());
        cs.setString(6,employee.getPhone());
        cs.executeUpdate();
    }

    @Override
    public void delete(Integer integer) throws SQLException {
        String sql = "{CALL sp_delete_trabajador(?)}";
        log.info("El id recibido es:"+integer);
        CallableStatement cs = con.prepareCall(sql);
        cs.setInt(1,integer);
        cs.executeUpdate();
    }

    @Override
    public void close() throws SQLException {
        con.close();
    }
    public void open(String name_datasource) throws SQLException{
        if (con.isClosed()) {
            this.con = MysqlConnector.getConnection(name_datasource);
        }
    }

    public ResultSet findAll() throws SQLException {
        String sql = "select * from uv_trabajadores";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            return ps.executeQuery();
        }catch (SQLException e){
            log.severe("Ocurrio un error al obtener los datos de la vista uv_trabajadores");
            return null;

        }
    }


}
