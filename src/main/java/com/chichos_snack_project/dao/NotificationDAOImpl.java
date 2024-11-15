package com.chichos_snack_project.dao;

import com.chichos_snack_project.interfaces.NotificationDAO;
import com.chichos_snack_project.model.Notification;
import com.chichos_snack_project.util.MysqlConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificationDAOImpl implements NotificationDAO {

    private final Connection con;
    public NotificationDAOImpl(String name_datasource){
        this.con = MysqlConnector.getConnection(name_datasource);
    }
    @Override
    public void create(Notification notification) throws SQLException {

    }

    @Override
    public Notification read(Notification notification) throws SQLException {
        return null;
    }

    @Override
    public void update(Integer integer, Notification notification) throws SQLException {

    }

    @Override
    public void delete(Integer integer) throws SQLException {

    }

    public ResultSet findAll() throws SQLException{
        String sql = "select * from uv_notifiacion";
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();
    }
}
