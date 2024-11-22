package com.chichos_snack_project.dao;

import com.chichos_snack_project.interfaces.SaleDAO;
import com.chichos_snack_project.model.Sale;
import com.chichos_snack_project.util.MysqlConnector;

import java.sql.*;

public class SaleDAOImpl implements SaleDAO {

    private final Connection con;

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

    public ResultSet createReport(Integer id_employee, Integer id_customer, java.sql.Date date_start, java.sql.Date date_end) throws SQLException{
        String sql = "{CALL sp_create_reporte_ventas(?,?,?,?)}";
        CallableStatement cs = con.prepareCall(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        cs.setInt(1,id_customer);
        cs.setInt(2,id_employee);
        cs.setDate(3,date_start);
        cs.setDate(4,date_end);
        return cs.executeQuery();
    }
    @SuppressWarnings("SqlResolve")
    public ResultSet findAll() throws SQLException{
        String sql = "select * from uv_ventas";
        PreparedStatement ps = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        return ps.executeQuery();
    }

    public ResultSet sumAmountSales() throws SQLException{
        String sql = "select uf_sumVentas()";
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();
    }
}
