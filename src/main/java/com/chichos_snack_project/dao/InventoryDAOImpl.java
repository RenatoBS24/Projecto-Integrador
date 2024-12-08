package com.chichos_snack_project.dao;

import com.chichos_snack_project.interfaces.InventoryDAO;
import com.chichos_snack_project.model.Inventory;
import com.chichos_snack_project.util.MysqlConnector;

import java.sql.*;

public class InventoryDAOImpl implements InventoryDAO {

    private Connection con;
    public InventoryDAOImpl(String name_datasource) {
        this.con = MysqlConnector.getConnection(name_datasource);
    }
    @Override
    public void create(Inventory inventory) throws SQLException{
        String sql = "{CALL sp_create_inventory(?,?,?,?,?,?)}";
        CallableStatement cs = con.prepareCall(sql);
        cs.setInt(1,inventory.getStock());
        cs.setString(2,inventory.getLot());
        cs.setDate(3,inventory.getExpiration_date());
        cs.setDate(4,inventory.getPurchase_date());
        cs.setDouble(5,inventory.getPurchase_price());
        cs.setInt(6,inventory.getProduct().getId_product());
        cs.execute();

    }

    @Override
    public Inventory read(Inventory inventory) throws SQLException {
        return null;
    }

    @Override
    public void update(Integer integer, Inventory inventory) throws SQLException {
        String sql = "{CALL sp_update_inventario(?,?,?,?,?)}";
        CallableStatement cs = con.prepareCall(sql);
        cs.setInt(1,integer);
        cs.setInt(2,inventory.getStock());
        cs.setDate(3,inventory.getExpiration_date());
        cs.setDate(4,inventory.getPurchase_date());
        cs.setDouble(5,inventory.getPurchase_price());
        cs.execute();
    }

    @Override
    public void delete(Integer integer) throws SQLException {
        String sql = "{CALL sp_delete_inventory(?)}";
        CallableStatement cs = con.prepareCall(sql);
        cs.setInt(1,integer);
        cs.execute();
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
    public void createOfProduct(Inventory inventory) throws SQLException {
        String sql = "{CALL sp_insert_inventario(?,?,?,?,?)}";
        CallableStatement cs = con.prepareCall(sql);
        cs.setInt(1,inventory.getStock());
        cs.setString(2,inventory.getLot());
        cs.setDate(3,inventory.getExpiration_date());
        cs.setDate(4,inventory.getPurchase_date());
        cs.setDouble(5,inventory.getPurchase_price());
        cs.execute();
    }

    public ResultSet findAll() throws SQLException {
        String sql = "select * from uv_inventario";
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();
    }
}
