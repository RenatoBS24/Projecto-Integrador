package com.chichos_snack_project.controller;
import com.chichos_snack_project.model.Category;
import com.chichos_snack_project.model.Customer;
import com.chichos_snack_project.model.Employee;
import com.chichos_snack_project.model.Product;

import com.chichos_snack_project.service.CategoryService;
import com.chichos_snack_project.service.CustomerService;
import com.chichos_snack_project.service.EmployeeService;
import com.chichos_snack_project.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SaleRegisterServlet", value = "/SaleRegister")
public class SaleRegisterServlet extends HttpServlet {
    public void init() {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Product> productList = ProductService.getProducts();
        List<Category> categoryList = CategoryService.getCategory();
        List<Employee> employeeList = EmployeeService.getEmployees();
        List<Customer> customerList = CustomerService.getCustomer();
        request.setAttribute("employeeList", employeeList);
        request.setAttribute("customerList", customerList);
        request.setAttribute("productList", productList);
        request.setAttribute("categoryList", categoryList);
        request.getRequestDispatcher("sale_register.jsp").forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
    public void destroy() {
    }
}
