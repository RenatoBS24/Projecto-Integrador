package com.chichos_snack_project.controller;


import com.chichos_snack_project.service.Register_User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;




@WebServlet(name ="register", value = "/register")
public class SingUpServlet extends HttpServlet {
    
    public void init() {
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordRepeat = request.getParameter("passwordRepeat");
        if(Register_User.register(name,password,passwordRepeat,2)){
            HttpSession session = request.getSession();
            session.setAttribute("is_valid_user", true);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }else{
            request.setAttribute("error","No se pudo registrar al usuario intente de nuevo");
            request.getRequestDispatcher("register_user.jsp").forward(request,response);
        }
    }

    public void destroy() {
    }



}

