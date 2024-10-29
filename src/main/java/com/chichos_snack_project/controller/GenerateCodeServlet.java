package com.chichos_snack_project.controller;



import com.chichos_snack_project.util.Sending_Email;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "GenerateCodeServlet" , value = "/GenerateCode")
public class GenerateCodeServlet extends HttpServlet {
    public void init() {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String code = Sending_Email.email();
        if(session.getAttribute("code") !=null){
            session.removeAttribute("code");
            session.setAttribute("code",code);
        }else{
            session.setAttribute("code",code);
        }

    }
    public void destroy() {
    }
}
