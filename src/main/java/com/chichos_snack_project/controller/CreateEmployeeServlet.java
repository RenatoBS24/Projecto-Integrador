package com.chichos_snack_project.controller;

import com.chichos_snack_project.service.Create_employee;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CrCreateEmployeeServlet" ,value = "/CreateEmployee")
public class CreateEmployeeServlet extends HttpServlet {
    public void init() {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       String name = request.getParameter("name");
       String lastname = request.getParameter("lastname");
       String dni = request.getParameter("dni");
       String phone = request.getParameter("phone");
       String salary = request.getParameter("salary");
       if(Create_employee.create(name,lastname,dni,phone,salary)){
           request.setAttribute("confirm" ,"Se registro al trabajador");
           request.getRequestDispatcher("employee.jsp").forward(request,response);
       }else{
           request.setAttribute("Error" ,"No se pudo registrar al trabajador");
           request.getRequestDispatcher("employee.jsp").forward(request,response);
       }
    }
    public void destroy() {
    }
}
