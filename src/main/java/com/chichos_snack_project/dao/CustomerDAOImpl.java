package com.chichos_snack_project.dao;

import com.chichos_snack_project.interfaces.CustomerDAO;
import com.chichos_snack_project.model.Customer;
import com.chichos_snack_project.util.MysqlConnector;


import java.sql.*;
import java.util.logging.Logger;

public class CustomerDAOImpl implements CustomerDAO {
    private static final java.util.logging.Logger log = Logger.getLogger(CustomerDAOImpl.class.getName());
    Connection con;
    public CustomerDAOImpl(String name_datasource){
        this.con = MysqlConnector.getConnection(name_datasource);
    }

    @Override
    public void create(Customer customer) throws SQLException {
        String sql = "{CALL sp_insert_cliente(?,?,?)}";
        CallableStatement cs = con.prepareCall(sql);
        cs.setString(1,customer.getName());
        cs.setString(2,customer.getPhone());
        cs.setDouble(3,customer.getAmount_total());
        cs.executeUpdate();
        log.info("Se registro un nuevo cliente identificado con el nombre: "+customer.getName());
    }

    @Override
    public Customer read(Customer customer) throws SQLException {
        String sql = "{CALL sp_getCliente(?)}";
        CallableStatement cs = con.prepareCall(sql);
        cs.setInt(1,customer.getId_customer());
        ResultSet rs = cs.executeQuery();
        if(rs.next()){
            return new Customer(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getDouble(5),rs.getDouble(6),rs.getDouble(7),rs.getInt(8));
        }else{
            log.severe("El procedimiento sp_getCliente ha retornado un null \n id recibido: "+customer.getId_customer());
            return null;
        }

    }

    @Override
    public void update(Integer integer, Customer customer) throws SQLException {
        String sql = "{CALL sp_update_cliente(?,?,?)}";
        CallableStatement cs = con.prepareCall(sql);
        cs.setInt(1,customer.getId_customer());
        cs.setString(2, customer.getName());
        cs.setString(3, customer.getPhone());
        cs.executeUpdate();
    }

    @Override
    public void delete(Integer integer) throws SQLException {
        String sql = "{CALL sp_delete_cliente(?)}";
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
        if(con.isClosed()){
            con = MysqlConnector.getConnection(name_datasource);
        }
    }

    public ResultSet findAll() throws SQLException {
        String sql = "select * from uv_clientes";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            return ps.executeQuery();
        }catch (SQLException e){
            log.severe("Ocurrio un error al obtener los datos de la vista uv_clientes");
            return null;

        }
    }
    public ResultSet countCustomer() throws SQLException{
        String sql = "select uf_totalClientes()";
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();
    }

    public void updateCredit(Integer id, Double total, Double used) throws SQLException {
        String sql = "{CALL sp_update_credito(?,?,?)}";
        CallableStatement cs = con.prepareCall(sql);
        cs.setInt(1, id);
        cs.setDouble(2, total);
        cs.setDouble(3, used);
        cs.executeUpdate();
    }
}
