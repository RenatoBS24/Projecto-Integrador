package com.chichos_snack_project.dao;

import com.chichos_snack_project.interfaces.EmployeeDAO;
import com.chichos_snack_project.model.Employee;
import com.chichos_snack_project.util.MysqlConnector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;


public class EmployeeDAPImpl implements EmployeeDAO {

    private Connection conn;
    private static Logger log = LogManager.getLogger(EmployeeDAPImpl.class);

    public EmployeeDAPImpl(String name_datasource) {
        this.conn = MysqlConnector.getConnection(name_datasource);
    }

    @Override
    public void create(Employee employee) throws SQLException {

    }

    @Override
    public Employee read(Employee employee) throws SQLException {
        String sql = "{CALL sp_getTrabajador(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1,employee.getId_employee());
        ResultSet rs = cs.executeQuery();
        if(rs.next()){
            return new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4), rs.getString(5),rs.getDate(6),rs.getString(7));
        }else{
            log.error("No se pudo obtener al empleado por lo cual se retorna null");
            return null;
        }

    }

    @Override
    public void update(Integer integer, Employee employee) throws SQLException {
        String sql = "{CALL sp_update_trabajador(?,?,?,?,?,?)}";
        CallableStatement cs = conn.prepareCall(sql);
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
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1,integer);
        cs.executeUpdate();
    }

    public ResultSet findAll() throws SQLException {
        String sql = "select * from uv_trabajadores";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            return ps.executeQuery();
        }catch (SQLException e){
            log.error("Ocurrio un error al obtener los datos de la vista uv_trabajadores");
            return null;

        }
    }


}
