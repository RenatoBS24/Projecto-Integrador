package com.chichos_snack_project.controller;

import com.chichos_snack_project.model.Product;
import com.chichos_snack_project.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
@WebServlet(name = "UpdateQuantityServlet", value = "/UpdateQuantity")
public class UpdateQuantityServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String quantity = request.getParameter("quantity");
        String id = request.getParameter("id");
        @SuppressWarnings("unchecked")
        HashMap<Integer, Product> cart = (HashMap<Integer, Product>) request.getSession().getAttribute("cart");
        HttpSession session = request.getSession();
        session.setAttribute("cart", ProductService.updateQuantity(quantity, id, cart));
    }

}
