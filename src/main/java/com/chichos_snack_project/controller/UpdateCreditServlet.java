package com.chichos_snack_project.controller;

import com.chichos_snack_project.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateCreditServlet", value = "/UpdateCredit")
public class UpdateCreditServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id_credit = request.getParameter("id_credit");
        String total = request.getParameter("amount_total");
        String used = request.getParameter("amount_used");
        if(CustomerService.updateCredit(id_credit,total,used)) {
            response.sendRedirect("customer.jsp");
        }else{
            request.setAttribute("error","Error al actualizar el credito");
            request.getRequestDispatcher("customer.jsp").forward(request,response);
        }
    }

}
