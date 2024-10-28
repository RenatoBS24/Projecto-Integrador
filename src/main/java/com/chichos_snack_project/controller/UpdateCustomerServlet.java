package com.chichos_snack_project.controller;

import com.chichos_snack_project.service.Update_customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "UpdateCustomerServlet", value = "/UpdateCustomer")
public class UpdateCustomerServlet extends HttpServlet {
    public void init() {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String id = request.getParameter("id_customer");
        if(Update_customer.update(name,phone,id)){
            response.sendRedirect("Customer");
        }else{
            request.setAttribute("Error","No se pudo actualizar los datos, intente de nuevo");
            request.getRequestDispatcher("Customer").forward(request,response);
        }
    }
    public void destroy() {
    }
}
