package com.chichos_snack_project.controller;

import com.chichos_snack_project.service.InventoryService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReportInventoryServlet", value = "/reportInventory")
public class ReportInventoryServlet extends HttpServlet {
    public void init() {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id_user = "1";
        String id_product = request.getParameter("id_product");
        String date_start = request.getParameter("date_start");
        String date_end = request.getParameter("date_end");
        InventoryService.createReport(id_product,date_start,date_end,id_user,response.getOutputStream());
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=ReporteInventario.xlsx");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    }
    public void destroy() {
    }
}
