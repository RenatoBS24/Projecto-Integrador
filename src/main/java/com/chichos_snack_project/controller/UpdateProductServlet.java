package com.chichos_snack_project.controller;


import com.chichos_snack_project.service.InventoryService;
import com.chichos_snack_project.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateProductServlet", value = "/UpdateProduct")
public class UpdateProductServlet extends HttpServlet {
    public void init() {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id_product = request.getParameter("id_product");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String unit = request.getParameter("unit");
        String category = request.getParameter("category");
        String id_lot = request.getParameter("lot");
        String expired = request.getParameter("expired");
        String purchase = request.getParameter("purchase");
        String buy_purchase = request.getParameter("buy_purchase");
        String stock = request.getParameter("stock");
        if(ProductService.UpdateProduct(id_product,name,price,unit,category) && InventoryService.UpdateInventory(id_lot,expired,purchase,stock,buy_purchase)){
            response.sendRedirect("Products");
        }else{
            request.setAttribute("error","Error al actualizar el producto");
            request.getRequestDispatcher("products.jsp").forward(request,response);
        }

    }
    public void destroy() {
    }
}
