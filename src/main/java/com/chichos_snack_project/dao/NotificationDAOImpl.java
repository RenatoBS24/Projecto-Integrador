package com.chichos_snack_project.dao;

import com.chichos_snack_project.interfaces.NotificationDAO;
import com.chichos_snack_project.model.Notification;
import com.chichos_snack_project.util.MysqlConnector;

import java.sql.*;

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

    @Override
    public void close() throws SQLException {
        con.close();
    }

    public ResultSet findAll() throws SQLException{
        String sql = "{CALL sp_getNotifications()}";
        CallableStatement cs = con.prepareCall(sql);
        return cs.executeQuery();
    }
}
