package com.chichos_snack_project.dao;

import com.chichos_snack_project.interfaces.CustomerDAO;
import com.chichos_snack_project.model.Customer;
import com.chichos_snack_project.util.MysqlConnector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAOImpl implements CustomerDAO {
    private final Logger log = LogManager.getLogger(ProductDAOImpl.class);
    Connection con;
    public CustomerDAOImpl(String name_datasource){
        this.con = MysqlConnector.getConnection(name_datasource);
    }

    @Override
    public void create(Customer customer) throws SQLException {

    }

    @Override
    public Customer read(Customer customer) throws SQLException {
        return null;
    }

    @Override
    public void update(Integer integer, Customer customer) throws SQLException {

    }

    @Override
    public void delete(Integer integer) throws SQLException {

    }
    public ResultSet findAll() throws SQLException {
        String sql = "select * from uv_clientes";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            return ps.executeQuery();
        }catch (SQLException e){
            log.error("Ocurrio un error al obtener los datos de la vista uv_clientes");
            return null;

        }
    }
}
