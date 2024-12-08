package com.chichos_snack_project.controller;
import com.chichos_snack_project.model.Product;
import com.chichos_snack_project.service.SaleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "CreateSaleServlet", value = "/CreateSale")
public class CreateSaleServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id_customer = request.getParameter("id_customer");
        String id_employee = request.getParameter("id_employee");
        String amount = request.getParameter("amount");
        String confirm = request.getParameter("confirm");
        if(request.getSession().getAttribute("cart") !=null){
            @SuppressWarnings("unchecked")
            HashMap<Integer, Product> cart = (HashMap<Integer, Product>) request.getSession().getAttribute("cart");
            if(SaleService.createSale(cart,amount,id_employee,id_customer,confirm)) {
                request.getSession().removeAttribute("cart");
                response.setContentType("application/json");
                response.getWriter().write("{\"redirect\":\"sales.jsp\"}");
            }
        }

    }

}
