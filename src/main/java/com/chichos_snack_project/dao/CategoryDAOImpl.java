package com.chichos_snack_project.dao;

import com.chichos_snack_project.interfaces.CategoryDAO;
import com.chichos_snack_project.model.Category;
import com.chichos_snack_project.util.MysqlConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class CategoryDAOImpl implements CategoryDAO {

    private static final java.util.logging.Logger log = Logger.getLogger(CategoryDAOImpl.class.getName());
    Connection con;
    public CategoryDAOImpl(String name_datasource){
        this.con = MysqlConnector.getConnection(name_datasource);
    }
    @Override
    public void create(Category category) throws SQLException {

    }

    @Override
    public Category read(Category category) throws SQLException {
        return null;
    }

    @Override
    public void update(Integer integer, Category category) throws SQLException {

    }

    @Override
    public void delete(Integer integer) throws SQLException {

    }

    @Override
    public void close() throws SQLException {
        con.close();
    }
    public void open(String name_datasource) throws SQLException {
        if(con.isClosed()){
            con = MysqlConnector.getConnection(name_datasource);
        }
    }

    public ResultSet findAll() throws SQLException{
        String sql = "select * from uv_categoria";
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();

    }
}
