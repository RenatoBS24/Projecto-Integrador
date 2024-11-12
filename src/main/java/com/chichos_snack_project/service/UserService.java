package com.chichos_snack_project.service;

import com.chichos_snack_project.dao.UserDAOImpl;
import com.chichos_snack_project.model.User;
import com.chichos_snack_project.util.AppConfig;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;


import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkNotNull;

public class UserService  {

    private static final java.util.logging.Logger log = Logger.getLogger(UserService.class.getName());
    private static final UserDAOImpl userDAO = new UserDAOImpl(AppConfig.getDatasource());
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
                log.warning("El usuario recibido por el metodo read de userDAO es nulo");
                return false;
            }
        }catch (SQLException | NullPointerException e){
            log.warning(e.getMessage());
            return false;
        }
    }

    /**
     * @param name name of the user
     * @param password new password
     * @param passwordRepeat new password repeat
     * @param id_role id that reference the name of the user role
     * @return true if register user is successful or false if occurred a SQLException
     */
    public static boolean register(String name,String password,String passwordRepeat,int id_role){
        try {
            checkNotNull(name,"El parametro name no puede ser nulo");
            checkNotNull(password,"El parametro password no puede ser nulo");
            checkNotNull(passwordRepeat,"El parametro passwordRepeat no puede ser nulo");
            if (password.equals(passwordRepeat)) {
                if (!name.trim().isEmpty() && !password.trim().isEmpty()) {
                    User user = new User(name,sha256(password),id_role);
                    try{
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
        } catch (NullPointerException e) {
            log.severe(e.getMessage());
            return false;
        }
    }
    public static boolean update(String username, String password, String passwordRepeat){
        try {
            checkNotNull(username,"El parametro name no puede ser nulo");
            checkNotNull(password,"El parametro password no puede ser nulo");
            checkNotNull(passwordRepeat,"El parametro passwordRepeat no puede ser nulo");
            if (password.equals(passwordRepeat)) {
                try{
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
            log.severe(e.getMessage());
            return false;
        }
        return false;
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
