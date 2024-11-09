package com.chichos_snack_project.service;

import com.chichos_snack_project.dao.SaleDAOImpl;
import com.chichos_snack_project.util.AppConfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class SaleService {

    private static final java.util.logging.Logger log = Logger.getLogger(SaleService.class.getName());
    private static final SaleDAOImpl saleDAO = new SaleDAOImpl(AppConfig.getDatasource());

    public static double sumSales(){
        double total = 0;
        try {
            ResultSet rs = saleDAO.sumAmountSales();
            if(rs.next()){
                total = rs.getDouble(1);
            }
        } catch (SQLException e) {
            log.severe("Hubo un error al procesar la el resulset obtenido por el metodo sumAmountSales de SaleDAOImpl state: "+e.getSQLState());
        }
        return total;
    }
}
