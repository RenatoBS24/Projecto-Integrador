package com.chichos_snack_project.controller;
import com.chichos_snack_project.service.SaleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReportSalesServlet", value = "/ReportSales")
public class ReportSalesServlet extends HttpServlet {
    public void init() {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id_user = "1";
        String id_employee = request.getParameter("id_employee");
        String id_customer = request.getParameter("id_customer");
        String date_start = request.getParameter("date_start");
        String date_end = request.getParameter("date_end");
        SaleService.createReport(id_user,id_customer, id_employee , date_start, date_end, response.getOutputStream());
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=ReporteVentas.xlsx");

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
    public void destroy() {
    }
}
