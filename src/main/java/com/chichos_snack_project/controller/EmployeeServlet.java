package com.chichos_snack_project.controller;

import com.chichos_snack_project.model.Employee;
import com.chichos_snack_project.service.View_data_employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet(name = "EmployeeServlet", value = "/Employee")
public class EmployeeServlet  extends HttpServlet {
    public void init() {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        List<Employee> employeeList = View_data_employee.getEmployees();
        request.setAttribute("employeeList",employeeList);
        request.getRequestDispatcher("employee.jsp").forward(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
    public void destroy() {
    }
}
