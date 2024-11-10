package com.chichos_snack_project.controller;

import com.chichos_snack_project.model.Employee;
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

@WebServlet(name = "IndexServlet", value = "/Index")
public class IndexServlet  extends HttpServlet {
    public void init() {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       double total = SaleService.sumSales();
       int count = CustomerService.getCountCustomer();
       List<Employee> employeeList = EmployeeService.getEmployees();
       request.setAttribute("total",total);
       request.setAttribute("count",count);
       request.setAttribute("employeeList",employeeList);
       request.getRequestDispatcher("index.jsp").forward(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
    public void destroy() {
    }


}
