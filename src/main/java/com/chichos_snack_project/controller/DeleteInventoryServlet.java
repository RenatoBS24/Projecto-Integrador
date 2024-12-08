package com.chichos_snack_project.controller;

import com.chichos_snack_project.service.InventoryService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteInventoryServlet", value = "/DeleteInventory")
public class DeleteInventoryServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id_inventory");
        String code = (String)request.getSession().getAttribute("code");
        String code_entered = request.getParameter("code_entered");
        if(InventoryService.deleteInventory(id,code,code_entered)) {
            response.sendRedirect("inventory.jsp");
        }else{
            request.getSession().setAttribute("error","No se pudo eliminar el inventario");
            request.getRequestDispatcher("inventory.jsp").forward(request,response);
        }
    }
}
