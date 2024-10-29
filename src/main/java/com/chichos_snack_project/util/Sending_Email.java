package com.chichos_snack_project.util;

import org.apache.commons.mail.*;

public class Sending_Email {


    public static String email(){
        String email = "pro51.ballena@gmail.com";
        String password = "msgh lesd hwxu xivt";
        String code = generate_code();
        try{
            MultiPartEmail multiPartEmail = new MultiPartEmail();
            multiPartEmail.setHostName("smtp.gmail.com");
            multiPartEmail.setSmtpPort(587);
            multiPartEmail.setAuthentication(email,password);
            multiPartEmail.setSSLOnConnect(true);
            multiPartEmail.setFrom(email);
            multiPartEmail.setSubject("Codigo de verifacion");
            multiPartEmail.setMsg("Su codigo de verificacion es: "+code);
            multiPartEmail.addTo("renatoballena2405@gmail.com");
            multiPartEmail.send();
            return code;
        }catch (EmailException e){
            throw new RuntimeException(e.getMessage());
        }

    }

    private static String generate_code(){
        StringBuilder code = new StringBuilder();
        for(int i = 0;i<8;i++){
            code.append((int)(Math.random()*9));
        }
        return code.toString();
    }
}
