package com.chichos_snack_project.controller;
import com.chichos_snack_project.model.Customer;
import com.chichos_snack_project.model.Employee;
import com.chichos_snack_project.model.Sale;
import com.chichos_snack_project.service.CustomerService;
import com.chichos_snack_project.service.EmployeeService;
import com.chichos_snack_project.service.SaleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet(name = "SaleServlet", value = "/Sales")
public class SaleServlet extends HttpServlet {
    public void init() {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Customer> customerList = CustomerService.getCustomer();
        List<Employee> employeeList = EmployeeService.getEmployees();
        List<Sale> saleList = SaleService.getSales();
        request.setAttribute("customerList",customerList);
        request.setAttribute("employeeList",employeeList);
        request.setAttribute("saleList",saleList);
        request.getRequestDispatcher("sales.jsp").forward(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
    public void destroy() {
    }
}
