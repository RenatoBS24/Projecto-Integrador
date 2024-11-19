package com.chichos_snack_project.dao;

import com.chichos_snack_project.interfaces.ProductDAO;
import com.chichos_snack_project.model.Product;
import com.chichos_snack_project.util.MysqlConnector;

import java.sql.*;
import java.util.logging.Logger;

public class ProductDAOImpl implements ProductDAO {
    private static final java.util.logging.Logger log = Logger.getLogger(ProductDAOImpl.class.getName());
    Connection con;
    public ProductDAOImpl(String name_datasource){
        this.con = MysqlConnector.getConnection(name_datasource);
    }
    @Override
    public void create(Product product) throws SQLException {
        String sql = "{CALL sp_insert_producto(?,?,?,?)}";
        CallableStatement cs = con.prepareCall(sql);
        cs.setString(1,product.getName());
        cs.setDouble(2,product.getPrice());
        cs.setInt(3,product.getUnitOfMeasurement().getId_unit_of_measurement());
        cs.setInt(4,product.getCategory().getId_category());
        cs.execute();
    }

    @Override
    public Product read(Product product) throws SQLException {
        return null;
    }

    @Override
    public void update(Integer integer, Product product) throws SQLException {
        String sql = "{CALL sp_update_producto(?,?,?,?,?)}";
        CallableStatement cs = con.prepareCall(sql);
        cs.setInt(1,integer);
        cs.setString(2,product.getName());
        cs.setDouble(3,product.getPrice());
        cs.setInt(4,product.getUnitOfMeasurement().getId_unit_of_measurement());
        cs.setInt(5,product.getCategory().getId_category());
        cs.execute();
    }

    @Override
    public void delete(Integer integer) throws SQLException {
        String sql = "{CALL sp_delete_producto(?)}";
        CallableStatement cs = con.prepareCall(sql);
        cs.setInt(1,integer);
        cs.execute();
    }

    public ResultSet getProductsByCategory(Integer id_category) throws SQLException {
        String sql = "{CALL sp_getProductosByCategoria(?)}";
        CallableStatement cs = con.prepareCall(sql);
        cs.setInt(1,id_category);
        return cs.executeQuery();
    }
    public ResultSet findAll() throws SQLException {
        String sql = "select * from uv_products";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            return ps.executeQuery();
        }catch (SQLException e){
            log.severe("Ocurrio un error al obtener los datos de la vista uv_products");
            return null;

        }
    }

}
