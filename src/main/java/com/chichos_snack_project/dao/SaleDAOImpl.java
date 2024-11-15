package com.chichos_snack_project.dao;

import com.chichos_snack_project.interfaces.SaleDAO;
import com.chichos_snack_project.model.Sale;
import com.chichos_snack_project.util.MysqlConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SaleDAOImpl implements SaleDAO {

    private Connection con;

    public SaleDAOImpl(String name_datasource){
        con = MysqlConnector.getConnection(name_datasource);
    }

    @Override
    public void create(Sale sale) throws SQLException {

    }

    @Override
    public Sale read(Sale sale) throws SQLException {
        return null;
    }

    @Override
    public void update(Integer integer, Sale sale) throws SQLException {

    }

    @Override
    public void delete(Integer integer) throws SQLException {

    }
    public ResultSet findAll() throws SQLException{
        String sql = "select * from uv_ventas";
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();
    }

    public ResultSet sumAmountSales() throws SQLException{
        String sql = "select uf_sumVentas()";
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();
    }
}
