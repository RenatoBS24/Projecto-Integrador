package com.chichos_snack_project.controller;
import com.chichos_snack_project.service.InventoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateInventoryServlet", value = "/UpdateInventory")
public class UpdateInventoryServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id_inventory = request.getParameter("id_inventory");
        String stock = request.getParameter("stock");
        String purchase = request.getParameter("purchase");
        String expired = request.getParameter("expired");
        String buy_purchase = request.getParameter("buy_purchase");
        if(InventoryService.UpdateInventory(id_inventory,expired,purchase,stock,buy_purchase)) {
            response.sendRedirect("inventory.jsp");
        }else{
            request.getSession().setAttribute("error","No se ha podido actualizar el inventario");
            request.getRequestDispatcher("inventory.jsp").forward(request, response);
        }

    }

}
