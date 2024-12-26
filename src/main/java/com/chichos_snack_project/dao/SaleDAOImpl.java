package com.chichos_snack_project.dao;

import com.chichos_snack_project.interfaces.SaleDAO;
import com.chichos_snack_project.model.Sale;
import com.chichos_snack_project.util.MysqlConnector;


import java.sql.*;

public class SaleDAOImpl implements SaleDAO {

    private  Connection con;

    public SaleDAOImpl(String name_datasource){
        con = MysqlConnector.getConnection(name_datasource);
    }

    @Override
    public void create(Sale sale) throws SQLException {
        String sql = "{CALL sp_create_sale(?,?,?,?)}";
        CallableStatement cs = con.prepareCall(sql);
        cs.setDouble(1,sale.getAmount());
        cs.setInt(2,sale.getEmployee().getId_employee());
        cs.setInt(3,sale.getCustomer().getId_customer());
        boolean confirm = sale.getCount() == 1;
        cs.setBoolean(4,confirm);
        cs.execute();
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
        String sql = "{CALL sp_delete_venta(?)}";
        CallableStatement cs = con.prepareCall(sql);
        cs.setInt(1,integer);
        cs.execute();
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
        String sql = "{CALL sp_ventas()}";
        PreparedStatement ps = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        return ps.executeQuery();
    }

    public ResultSet sumAmountSales() throws SQLException{
        String sql = "select uf_sumVentas()";
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();
    }
    public void registerSaleProduct(Integer id,Double price,Integer quantity) throws SQLException{
        String sql = "{CALL sp_insert_venta_producto(?,?,?)}";
        CallableStatement cs = con.prepareCall(sql);
        cs.setInt(1,id);
        cs.setDouble(2,price);
        cs.setInt(3,quantity);
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
}
