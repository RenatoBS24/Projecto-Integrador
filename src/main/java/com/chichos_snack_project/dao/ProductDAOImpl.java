package com.chichos_snack_project.dao;

import com.chichos_snack_project.interfaces.ProductDAO;
import com.chichos_snack_project.model.Product;
import com.chichos_snack_project.util.MysqlConnector;

import java.sql.Connection;
import java.sql.SQLException;

public class ProductDAOImpl implements ProductDAO {
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
}
