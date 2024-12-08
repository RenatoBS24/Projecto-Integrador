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

import static com.google.common.base.Preconditions.checkArgument;
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
            userDAO.open(AppConfig.getDatasource());
            checkNotNull(username,"El parametro name no puede ser nulo");
            checkNotNull(password,"El parametro password no puede ser nulo");
            checkArgument(!username.trim().isEmpty(),"El parametro name no puede estar vacio");
            checkArgument(!password.trim().isEmpty(),"El parametro password no puede estar vacio");
            checkArgument(username.matches("^[a-zA-Z0-9_\\-\\s]+$"),"El parametro name solo puede contener letras, numeros, guiones y guiones bajos");
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
        }catch (IllegalArgumentException e){
            log.warning("Se ha intentado iniciar sesion con un nombre de usuario invalido : "+username);
            return false;
        }finally {
           try{
                userDAO.close();
           }catch (SQLException e){
               log.warning(e.getMessage());
           }
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
            checkArgument(!name.trim().isEmpty(),"El parametro name no puede estar vacio");
            checkArgument(!password.trim().isEmpty(),"El parametro password no puede estar vacio");
            checkArgument(!passwordRepeat.trim().isEmpty(),"El parametro passwordRepeat no puede estar vacio");
            checkArgument(name.matches("^[a-zA-Z0-9_\\-\\s]+$"),"El parametro name solo puede contener letras, numeros, guiones y guiones bajos");
            checkArgument(id_role > 0,"El parametro id_role debe ser un numero mayor a 0");

            if (password.equals(passwordRepeat)) {
                User user = new User(name,sha256(password),id_role);
                try{
                    userDAO.open(AppConfig.getDatasource());
                    userDAO.create(user);
                    log.info("Se ha registrado correctamente al usuario "+name);
                    return true;
                }catch (SQLException e){
                       return false;
                }
            }else{
                return false;
            }
        } catch (NullPointerException |IllegalArgumentException e) {
            log.severe(e.getMessage());
            return false;
        }finally {
            try{
                userDAO.close();
            }catch (SQLException e){
                log.warning(e.getMessage());
            }
        }
    }
    public static boolean update(String username, String password, String passwordRepeat){
        try {
            checkNotNull(username,"El parametro name no puede ser nulo");
            checkNotNull(password,"El parametro password no puede ser nulo");
            checkNotNull(passwordRepeat,"El parametro passwordRepeat no puede ser nulo");
            checkArgument(!username.trim().isEmpty(),"El parametro name no puede estar vacio");
            checkArgument(!password.trim().isEmpty(),"El parametro password no puede estar vacio");
            checkArgument(!passwordRepeat.trim().isEmpty(),"El parametro passwordRepeat no puede estar vacio");
            checkArgument(username.matches("^[a-zA-Z0-9_\\-\\s]+$"),"El parametro name solo puede contener letras, numeros, guiones y guiones bajos");
            if (password.equals(passwordRepeat)) {
                try{
                    userDAO.open(AppConfig.getDatasource());
                    int id_user = userDAO.read(username.trim());
                    if(id_user != -1){
                        User user = new User(username,sha256(password));
                        userDAO.update(id_user, user);
                        log.info("Se ha actualizado correctamente la clave del usuario "+username);
                        return true;
                    }else{
                        return false;
                    }
                }catch (SQLException e){
                    return false;
                }
            }else{
                return false;
            }
        }catch (NullPointerException e) {
            log.severe(e.getMessage());
            return false;
        }finally {
            try{
                userDAO.close();
            }catch (SQLException e){
                log.warning(e.getMessage());
            }
        }
    }

    /**
     * This method use the library google guava for use Hashing class
     * @param password password entered by user
     * @return hash of the password
     */
    private static String sha256(String password){
        HashFunction hashFunction = Hashing.sha256();
        @SuppressWarnings("UnstableApiUsage") HashCode hashCode = hashFunction.newHasher().putString(password, StandardCharsets.UTF_8).hash();
        return hashCode.toString();
    }
}
