package com.chichos_snack_project.controller;



import com.chichos_snack_project.util.Sending_Email;
import com.chichos_snack_project.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ForgotPassword",value = "/ForgotPassword")
public class ForgotPassword extends HttpServlet {
    private String code;
    public void init() {

    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        code = Sending_Email.email();
        HttpSession session = request.getSession();
        session.setAttribute("code",code);
        request.getRequestDispatcher("forgot_password.jsp").forward(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String code = (String)request.getSession().getAttribute("code");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordRepeat = request.getParameter("passwordRepeat");
        String code_entered = request.getParameter("code");
        if(code != null && code.equals(code_entered)){
            if(UserService.update(username,password,passwordRepeat)){
                response.sendRedirect("login.jsp");
            }else{
                request.setAttribute("error","Ocurrio un error intente de nuevo");
                request.getRequestDispatcher("forgot_password.jsp").forward(request,response);
            }
        }else{
            request.setAttribute("error","Codigo incorrecto");
            request.getRequestDispatcher("forgot_password.jsp").forward(request,response);
        }
    }

    public void destroy() {
    }
}
