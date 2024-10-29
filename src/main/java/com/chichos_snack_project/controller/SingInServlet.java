package com.chichos_snack_project.controller;


import com.chichos_snack_project.service.Validate_User;


import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.util.logging.Logger;


@WebServlet(name = "SingIn",value = "/SingIn")
public class SingInServlet extends HttpServlet{
    private static final Logger log = Logger.getLogger("com.chichos_snack_project.formview");
    public void init() {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        log.info(name +" "+password);
        if(Validate_User.validate(name,password)){
            HttpSession se = request.getSession();
            se.setAttribute("is_valid_user",true);
            se.setAttribute("username",name);
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }else{
            request.setAttribute("errorMessage","Credenciales invalidas");
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }
    }
    public void destroy() {
    }
}
