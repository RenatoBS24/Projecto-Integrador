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

public class Update_User {

    private static final Logger log = LogManager.getLogger(Update_User.class);

    public static boolean update(String username, String password, String passwordRepeat){
        try {
            checkNotNull(username,"El parametro name no puede ser nulo");
            checkNotNull(password,"El parametro password no puede ser nulo");
            checkNotNull(passwordRepeat,"El parametro passwordRepeat no puede ser nulo");
            if (password.equals(passwordRepeat)) {
                try{
                    UserDAOImpl userDAO = new UserDAOImpl(AppConfig.getDatasource());
                    int id_user = userDAO.read(username.trim());
                    if(id_user != -1){
                        User user = new User(username,sha256(password));
                        userDAO.update(id_user, user);
                        return true;
                    }
                }catch (SQLException e){
                        return false;
                }
            }
        }catch (NullPointerException e) {
            log.error(e.getMessage());
            return false;
        }
        return false;
    }
    private static String sha256(String password){
        HashFunction hashFunction = Hashing.sha256();
        HashCode hashCode = hashFunction.newHasher().putString(password, StandardCharsets.UTF_8).hash();
        return hashCode.toString();
    }
}
