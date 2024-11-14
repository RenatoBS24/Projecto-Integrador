package com.chichos_snack_project.dao;

import com.chichos_snack_project.interfaces.InventoryDAO;
import com.chichos_snack_project.model.Inventory;
import com.chichos_snack_project.util.MysqlConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryDAOImpl implements InventoryDAO {

    private Connection con;
    public InventoryDAOImpl(String name_datasource) {
        this.con = MysqlConnector.getConnection(name_datasource);
    }

    @Override
    public void create(Inventory inventory) throws SQLException {

    }

    @Override
    public Inventory read(Inventory inventory) throws SQLException {
        return null;
    }

    @Override
    public void update(Integer integer, Inventory inventory) throws SQLException {

    }

    @Override
    public void delete(Integer integer) throws SQLException {

    }

    public ResultSet findAll() throws SQLException {
        String sql = "select * from uv_inventario";
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();
    }
}
