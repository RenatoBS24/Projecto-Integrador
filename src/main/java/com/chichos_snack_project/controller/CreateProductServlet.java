package com.chichos_snack_project.controller;
import com.chichos_snack_project.service.InventoryService;
import com.chichos_snack_project.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateProductServlet", value = "/CreateProduct")
public class CreateProductServlet extends HttpServlet {
    public void init() {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String lot = request.getParameter("lot");
        String expiration = request.getParameter("expired_date");
        String purchase = request.getParameter("purchase_date");
        String id_unit = request.getParameter("unit");
        String id_category = request.getParameter("category");
        String stock = request.getParameter("stock");
        String buy_price = request.getParameter("purchase_price");
        if(ProductService.createProduct(name,price,id_unit,id_category) && InventoryService.createInventory(lot,expiration,purchase,stock,buy_price)){
            response.sendRedirect("Products");
        }else{
            request.setAttribute("Error" ,"No se pudo registrar el producto");
            request.getRequestDispatcher("products.jsp").forward(request,response);
        }
    }
    public void destroy() {
    }

}
