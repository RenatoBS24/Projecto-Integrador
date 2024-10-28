package com.chichos_snack_project.service;

import com.chichos_snack_project.dao.UserDAOImpl;
import com.chichos_snack_project.model.User;
import com.chichos_snack_project.util.AppConfig;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The class validate_user is used to validate parameters entered by user
 * @autor Renato
 */

public class Validate_User {

    private static final Logger log = LogManager.getLogger(Validate_User.class);

    /**
     *
     * @param username username entered by user
     * @param password password entered by user
     * @return true if the parameters is correct, false if password is incorrect or the read user
     * method returns null
     */

    public static boolean validate(String username,String password){
        password = sha256(password);
        try{
            checkNotNull(username,"El parametro name no puede ser nulo");
            checkNotNull(password,"El parametro password no puede ser nulo");
            UserDAOImpl userDAO = new UserDAOImpl(AppConfig.getDatasource());
            User user = userDAO.read(new User(username,password,0));
            if(user != null){
                if(user.getPassword().equals(password)){
                    log.info("El usuario "+user.getUsername()+" ha iniciado sesion");
                    return true;
                }else{
                    log.info("El usuario "+user.getUsername()+"ha intentado iniciar sesion, pero la clave es incorrecta");
                    return false;
                }
            }else {
                log.warn("El usuario recibido por el metodo read de userDAO es nulo");
                return false;
            }
        }catch (SQLException e){
            return false;
        }catch (NullPointerException e) {
            log.error(e.getMessage());
            return false;
        }
    }
    private static String sha256(String password){
        HashFunction hashFunction = Hashing.sha256();
        HashCode hashCode = hashFunction.newHasher().putString(password, StandardCharsets.UTF_8).hash();
        return hashCode.toString();
    }




}
