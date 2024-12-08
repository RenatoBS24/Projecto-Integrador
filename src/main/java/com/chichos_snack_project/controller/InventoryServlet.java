package com.chichos_snack_project.controller;

import com.chichos_snack_project.model.Category;
import com.chichos_snack_project.model.Inventory;
import com.chichos_snack_project.model.Product;
import com.chichos_snack_project.service.CategoryService;

import com.chichos_snack_project.service.InventoryService;
import com.chichos_snack_project.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet(name = "InventoryServlet", value = "/Inventory")
public class InventoryServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Inventory> inventoryList = InventoryService.getAllInventory();
        List<Category> categoryList = CategoryService.getCategory();
        List<Product> productList = ProductService.getProducts();
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("inventoryList", inventoryList);
        request.setAttribute("productList", productList);
        request.getRequestDispatcher("inventory.jsp").forward(request, response);
    }
}
