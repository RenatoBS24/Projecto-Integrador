package com.chichos_snack_project.controller;


import com.chichos_snack_project.util.Sending_Email;
import com.chichos_snack_project.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;




@WebServlet(name ="register", value = "/register")
public class SingUpServlet extends HttpServlet {
    private String code;
    Logger log = LogManager.getLogger(SingUpServlet.class);
    public void init() {
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        code = Sending_Email.email();
        HttpSession session = request.getSession();
        session.setAttribute("code",code);
        request.getRequestDispatcher("register_user.jsp").forward(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String code = (String)request.getSession().getAttribute("code");
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordRepeat = request.getParameter("passwordRepeat");
        String code_entered = request.getParameter("code_entered");
        String rol = request.getParameter("rol");
        log.info(code+" "+code_entered);
        if (code != null && code.equals(code_entered)) {
            request.getSession().removeAttribute("code");
            if(UserService.register(name,password,passwordRepeat,Integer.parseInt(rol))){
                HttpSession session = request.getSession();
                session.setAttribute("is_valid_user", true);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }else{
                request.setAttribute("error","No se pudo registrar al usuario intente de nuevo");
                request.getRequestDispatcher("register_user.jsp").forward(request,response);
            }
        } else {
            request.setAttribute("error","Codigo incorrecto");
            request.getRequestDispatcher("register_user.jsp").forward(request,response);
        }
    }

    public void destroy() {
    }



}

