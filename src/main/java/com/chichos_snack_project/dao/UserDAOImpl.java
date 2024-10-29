package com.chichos_snack_project.dao;

import com.chichos_snack_project.interfaces.UserDAO;
import com.chichos_snack_project.model.User;
import com.chichos_snack_project.util.MysqlConnector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.*;



public class UserDAOImpl implements UserDAO {
    Connection con;
    private static Logger log = LogManager.getLogger(UserDAOImpl.class);

    public UserDAOImpl(String name_datasource) throws SQLException {
        this.con = MysqlConnector.getConnection(name_datasource);
    }

    @Override
    public void create(User user) throws SQLException {
        String sql = "{CALL sp_register(?,?,?)}";
        if(user != null){
            CallableStatement cs = con.prepareCall(sql);
            cs.setString(1,user.getUsername());
            cs.setString(2,user.getPassword());
            cs.setInt(3,user.getId_rol());
            cs.executeUpdate();
        }

    }

    @Override
    public User read(User user) throws SQLException {
        String sql = "{CALL sp_validate(?,?,?,?,?)}";
        if(user !=null){
            CallableStatement cs = con.prepareCall(sql);
            cs.setString(1,user.getUsername());
            cs.setString(2,user.getPassword());
            cs.registerOutParameter(3, Types.VARCHAR);
            cs.registerOutParameter(4,Types.VARCHAR);
            cs.registerOutParameter(5,Types.INTEGER);
            cs.executeQuery();
            //---------------------------------

            String username = cs.getString(3);
            String password = cs.getString(4);
            int id_rol = cs.getInt(5);

            if(id_rol != 0 && username!= null && password != null){
                return new User(username,password,id_rol);
            }

        }
        return null;
    }

    @Override
    public void update(Integer integer,User user) throws SQLException {
        String sql = "{CALL sp_update_password(?,?)}";
        try {
            if(integer != null){
                CallableStatement cs = con.prepareCall(sql);
                cs.setString(1,user.getPassword());
                cs.setInt(2,integer);
                cs.executeUpdate();
                log.info("Se actualizaron las credenciales del usuario : "+user.getUsername());
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }

    }

    @Override
    public void delete(Integer integer) throws SQLException {

    }

    public int read(String username){
        String sql = "{CALL sp_read(?)}";
        try{
            CallableStatement cs = con.prepareCall(sql);
            cs.setString(1,username);
            ResultSet resultSet = cs.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt(1);
            }else{
                return -1;
            }
        }catch (SQLException e){
            return -1;
        }
    }
}
