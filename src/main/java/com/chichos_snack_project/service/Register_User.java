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

/**
 * The class Register_User is used to validate the parameters and create user object
 * @autor Renato
 */

public class Register_User {

    private static final Logger log = LogManager.getLogger(Register_User.class);

    public static boolean register(String name,String password,String passwordRepeat,int id_rol){
        if (password.equals(passwordRepeat)) {
            if (!name.trim().isEmpty() && !password.trim().isEmpty()) {
                User user = new User(name,sha256(password),id_rol);
                try{
                    UserDAOImpl userDAO = new UserDAOImpl(AppConfig.getDatasource());
                    userDAO.create(user);
                    log.info("Se ha registrado correctamente al usuario "+name);
                    return true;
                }catch (SQLException e){
                    return false;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    /**
     * This method use the library google guava for use Hashing class
     * @param password password entered by user
     * @return hash of the password
     */

    private static String sha256(String password){
        HashFunction hashFunction = Hashing.sha256();
        HashCode hashCode = hashFunction.newHasher().putString(password, StandardCharsets.UTF_8).hash();
        return hashCode.toString();
    }


}
