package com.chichos_snack_project.dao;

import com.chichos_snack_project.interfaces.UnitOfMeasurementDAO;
import com.chichos_snack_project.model.UnitOfMeasurement;
import com.chichos_snack_project.util.MysqlConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UnitOfMeasurementDAOImpl implements UnitOfMeasurementDAO {

    private Connection con;

    public UnitOfMeasurementDAOImpl(String name_datasource) {
        con = MysqlConnector.getConnection(name_datasource);
    }

    @Override
    public void create(UnitOfMeasurement unitOfMeasurement) throws SQLException {


    }

    @Override
    public UnitOfMeasurement read(UnitOfMeasurement unitOfMeasurement) throws SQLException {
        return null;
    }

    @Override
    public void update(Integer integer, UnitOfMeasurement unitOfMeasurement) throws SQLException {

    }

    @Override
    public void delete(Integer integer) throws SQLException {

    }

    public ResultSet findAll() throws SQLException {
        String sql = "select * from uv_unidadades";
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();
    }
}
