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

    public ResultSet findAll(){
        String sql = "select * from uv_categoria";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            return ps.executeQuery();
        } catch (SQLException e) {
            log.severe("No se pudo obtener los datos de la vista uv_categoria");
            return null;
        }
    }
}
