package com.chichos_snack_project.controller;

import com.chichos_snack_project.model.Category;
import com.chichos_snack_project.model.Inventory;
import com.chichos_snack_project.model.Product;

import com.chichos_snack_project.model.UnitOfMeasurement;
import com.chichos_snack_project.service.CategoryService;
import com.chichos_snack_project.service.InventoryService;
import com.chichos_snack_project.service.ProductService;
import com.chichos_snack_project.service.UnitOfMeasurementService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet",value = "/Products")
public class ProductServlet extends HttpServlet {
    public void init() {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Product> productList = ProductService.getProducts();
        List<Category> categoryList = CategoryService.getCategory();
        List<UnitOfMeasurement>unitOfMeasurementList = UnitOfMeasurementService.getAllUnitOfMeasurements();
        List<Inventory>inventoryList = InventoryService.getAllInventory();
        request.setAttribute("productList",productList);
        request.setAttribute("categoryList",categoryList);
        request.setAttribute("unitOfMeasurementList",unitOfMeasurementList);
        request.setAttribute("inventoryList",inventoryList);
        request.getRequestDispatcher("products.jsp").forward(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
    public void destroy() {
    }
}
