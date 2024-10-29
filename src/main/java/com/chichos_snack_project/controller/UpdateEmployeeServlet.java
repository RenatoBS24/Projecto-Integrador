package com.chichos_snack_project.controller;

import com.chichos_snack_project.service.EmployeeService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateEmployee",value = "/UpdateEmployee")
public class UpdateEmployeeServlet  extends HttpServlet {
    public void init() {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String salary = request.getParameter("salary");
        String dni = request.getParameter("dni");
        String phone = request.getParameter("phone");
        String id = request.getParameter("id");
        if(EmployeeService.update(name,lastname,salary,dni,phone,id)){
            response.sendRedirect("Employee");
        }else{
            request.setAttribute("Error","No se pudo actualizar los datos, intente de nuevo");
            request.getRequestDispatcher("Employee").forward(request,response);
        }
    }
    public void destroy() {
    }
}
