package com.chichos_snack_project.dao;

import com.chichos_snack_project.interfaces.ProductDAO;
import com.chichos_snack_project.model.Product;
import com.chichos_snack_project.util.MysqlConnector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAOImpl implements ProductDAO {
    private final Logger log = LogManager.getLogger(ProductDAOImpl.class);
    Connection con;
    public ProductDAOImpl(String name_datasource){
        this.con = MysqlConnector.getConnection(name_datasource);
    }
    @Override
    public void create(Product product) throws SQLException {

    }

    @Override
    public Product read(Product product) throws SQLException {
        return null;
    }

    @Override
    public void update(Integer integer, Product product) throws SQLException {

    }

    @Override
    public void delete(Integer integer) throws SQLException {

    }
    public ResultSet findAll() throws SQLException {
        String sql = "select * from uv_products";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            return ps.executeQuery();
        }catch (SQLException e){
            log.error("Ocurrio un error al obtener los datos de la vista uv_trabajadores");
            return null;

        }
    }

}
