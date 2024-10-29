package com.chichos_snack_project.controller;


import com.chichos_snack_project.service.CustomerService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "CreateCustomerServlet", value = "/CreateCustomer")
public class CreateCustomerServlet extends HttpServlet {
    public void init() {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String credit = request.getParameter("credit");
        if(CustomerService.create(name,phone,credit)){
            request.setAttribute("confirm" ,"Se registro al cliente");
            request.getRequestDispatcher("customer.jsp").forward(request,response);
        }else{
            request.setAttribute("Error" ,"No se pudo registrar al cliente");
            request.getRequestDispatcher("customer.jsp").forward(request,response);
        }
    }
    public void destroy() {
    }
}
