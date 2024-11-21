package com.chichos_snack_project.controller;
import com.chichos_snack_project.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReportProductServlet", value = "/reportProduct")
public class ReportProductServlet  extends HttpServlet {
    public void init() {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id_user = "1";
        String id_category = request.getParameter("id_category");
        ProductService.createReport(id_user, id_category,response.getOutputStream());
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=Prueba.xlsx");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    }
    public void destroy() {
    }
}
