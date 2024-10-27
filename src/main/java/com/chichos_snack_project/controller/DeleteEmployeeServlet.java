package com.chichos_snack_project.controller;

import com.chichos_snack_project.service.Delete_employee;
import com.chichos_snack_project.service.Update_employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "DeleteEmployeeServlet",value = "/DeleteEmployee")
public class DeleteEmployeeServlet extends HttpServlet {
    public void init() {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        if(Delete_employee.delete(Integer.parseInt(id))){
            response.sendRedirect("Employee");
        }else{
            request.setAttribute("Error","No se pudo eliminar al empleado, intente de nuevo");
            request.getRequestDispatcher("Employee").forward(request,response);
        }
    }
    public void destroy() {
    }
}
