package com.chichos_snack_project.controller;


import com.chichos_snack_project.service.Validate_User;


import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;


@WebServlet(name = "SingIn",value = "/SingIn")
public class SingInServlet extends HttpServlet{

    public void init() {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        if(Validate_User.validate(name,password)){
            HttpSession se = request.getSession();
            se.setAttribute("is_valid_user",true);
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }else{
            request.setAttribute("errorMessage","Credenciales invalidas");
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }
    }
    public void destroy() {
    }
}
