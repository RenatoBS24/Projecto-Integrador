package com.chichos_snack_project.controller;

import com.chichos_snack_project.service.Delete_customer;
import com.chichos_snack_project.service.Delete_employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "DeleteCustomerServlet", value = "/DeleteCustomer")
public class DeleteCustomerServlet extends HttpServlet {
    public void init() {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        String code = (String)request.getSession().getAttribute("code");
        String code_entered = request.getParameter("code_entered");
        if(Delete_customer.delete(Integer.parseInt(id),code,code_entered)){
            response.sendRedirect("Customer");
        }else{
            request.setAttribute("Error","No se pudo eliminar al empleado, intente de nuevo");
            request.getRequestDispatcher("Customer.jsp").forward(request,response);
        }
    }
    public void destroy() {
    }
}
