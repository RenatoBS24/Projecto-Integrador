package com.chichos_snack_project.controller;



import com.chichos_snack_project.service.EmployeeService;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;
import java.io.IOException;

@WebServlet(name = "CrCreateEmployeeServlet" ,value = "/CreateEmployee")
@MultipartConfig
public class CreateEmployeeServlet extends HttpServlet {
    public void init() {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       String name = request.getParameter("name");
       String lastname = request.getParameter("lastname");
       String dni = request.getParameter("dni");
       String phone = request.getParameter("phone");
       String salary = request.getParameter("salary");
       Part file = request.getPart("file");
        if (file.getSize() == 0) {
            if(EmployeeService.create(name,lastname,dni,phone,salary)){
                request.setAttribute("confirm" ,"Se registro al trabajador");
                request.getRequestDispatcher("employee.jsp").forward(request,response);
            }else{
                request.setAttribute("Error" ,"No se pudo registrar al trabajador");
                request.getRequestDispatcher("employee.jsp").forward(request,response);
            }
        }else{
            if(EmployeeService.create(EmployeeService.readFile(file))){
                request.setAttribute("confirm" ,"Se registro al trabajador");
                request.getRequestDispatcher("employee.jsp").forward(request,response);
            }else{
                request.setAttribute("Error" ,"No se pudo registrar al trabajador");
                request.getRequestDispatcher("employee.jsp").forward(request,response);
            }

        }
    }
    public void destroy() {
    }
}
