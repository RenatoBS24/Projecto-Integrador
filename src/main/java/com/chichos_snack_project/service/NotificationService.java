package com.chichos_snack_project.service;

import com.chichos_snack_project.dao.NotificationDAOImpl;
import com.chichos_snack_project.model.Notification;
import com.chichos_snack_project.model.Product;
import com.chichos_snack_project.util.AppConfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class NotificationService {

    private static final NotificationDAOImpl notificationDAO = new NotificationDAOImpl(AppConfig.getDatasource());
    private static final java.util.logging.Logger log = Logger.getLogger(NotificationService.class.getName());

    public static List<Notification> getNotifications() {
        List<Notification> notificationList = new LinkedList<>();
        try(ResultSet rs = notificationDAO.findAll()){
            if(rs !=null){
                while (rs.next()){
                    notificationList.add(new Notification(rs.getInt(1),new Product(rs.getInt(2),"",0,0,null,null),rs.getString(3),rs.getString(4),rs.getDate(5)));
                }
            }else{
                log.severe("El resultset obtenido del metodo findAll de NotificationDAOImpl es nulo");
                notificationList.add(new Notification(0,null,"","",null));
            }
            return notificationList;
        }catch (SQLException e){
            notificationList.add(new Notification(0,null,"","",null));
            log.severe("Ocurrio un error en la lectura del metodo findAll de NotificationDAOImpl, state: "+e.getSQLState());
            return notificationList;
        }

    }
}
