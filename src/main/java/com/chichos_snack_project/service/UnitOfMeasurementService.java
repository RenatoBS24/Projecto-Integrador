package com.chichos_snack_project.service;

import com.chichos_snack_project.dao.UnitOfMeasurementDAOImpl;
import com.chichos_snack_project.model.UnitOfMeasurement;
import com.chichos_snack_project.util.AppConfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class UnitOfMeasurementService {

    private static final java.util.logging.Logger log = Logger.getLogger(UnitOfMeasurementService.class.getName());
    private static final UnitOfMeasurementDAOImpl unitDAO = new UnitOfMeasurementDAOImpl(AppConfig.getDatasource());

    public static List<UnitOfMeasurement> getAllUnitOfMeasurements() {
        List<UnitOfMeasurement> unitOfMeasurementList = new LinkedList<>();
        try{
            ResultSet rs = unitDAO.findAll();
            if(rs != null){
                while (rs.next()){
                    unitOfMeasurementList.add(new UnitOfMeasurement(rs.getInt(1),rs.getString(2), rs.getString(3)));
                }
            }else{
                unitOfMeasurementList.add(new UnitOfMeasurement(0,"Error","Error"));
                log.severe("El resultset obtenido del metodo findAll de UnitOfMeasurementDAOImpl es nulo");
            }
            return unitOfMeasurementList;
        }catch(SQLException e){
            unitOfMeasurementList.add(new UnitOfMeasurement(0,"Error","Error"));
            log.severe("Ocurrio un error en la lectura del metodo findAll de UnitOfMeasurementDAOImpl, state: "+e.getSQLState());
            return unitOfMeasurementList;
        }
    }
}
