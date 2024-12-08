package com.chichos_snack_project.controller;
import com.chichos_snack_project.service.InventoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="CreateInventoryServlet", value="/createInventory")
public class CreateInventoryServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id_product = request.getParameter("product");
        String lot = request.getParameter("lot");
        String stock = request.getParameter("stock");
        String date_purchase = request.getParameter("date_buy");
        String date_expiration = request.getParameter("date_end");
        String price_buy = request.getParameter("price_buy");
        if(InventoryService.createInventory(lot, date_expiration,date_purchase,stock,price_buy,id_product)) {
            response.sendRedirect("inventory.jsp");
        }else{
            request.getSession().setAttribute("error","No se ha podido crear el inventario, intente de nuevo");
            request.getRequestDispatcher("inventory.jsp").forward(request,response);
        }
    }

}
