package com.chichos_snack_project.controller;

import com.chichos_snack_project.model.Category;
import com.chichos_snack_project.model.Customer;
import com.chichos_snack_project.model.Inventory;
import com.chichos_snack_project.service.CategoryService;
import com.chichos_snack_project.service.CustomerService;
import com.chichos_snack_project.service.InventoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet(name = "InventoryServlet", value = "/inventory")
public class InventoryServlet extends HttpServlet {
    public void init() {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Inventory> inventoryList = InventoryService.getAllInventory();
        List<Category> categoryList = CategoryService.getCategory();
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("inventoryList", inventoryList);
        request.getRequestDispatcher("inventory.jsp").forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
    public void destroy() {
    }

}
