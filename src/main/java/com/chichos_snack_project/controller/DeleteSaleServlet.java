package com.chichos_snack_project.controller;
import com.chichos_snack_project.service.SaleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteSaleServlet", value = "/DeleteSale")
public class DeleteSaleServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        String code = request.getSession().getAttribute("code").toString();
        String code_entered = request.getParameter("code_entered");
        if(SaleService.deleteSale(id, code, code_entered)) {
            response.sendRedirect("sales.jsp");
        }else{
            request.getSession().setAttribute("error","No se pudo eliminar la venta, Intente de nuevo");
            request.getRequestDispatcher("sales.jsp").forward(request,response);
        }

    }
}
