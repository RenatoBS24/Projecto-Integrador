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

@WebServlet(name = "AddToCartServlet", value = "/addToCart")
public class AddToCartServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id_product");
        String quantity = request.getParameter("quantity");
        String price = request.getParameter("price");
        String name = request.getParameter("name");
        Product product = ProductService.productForSale(id, quantity, price,name);
        HttpSession session = request.getSession();
        if(session.getAttribute("cart") == null) {
            HashMap<Integer,Product> cart = new HashMap<>();
            cart.put(product.getId_product(),product);
            session.setAttribute("cart",cart);
        }else{
            @SuppressWarnings("unchecked")
            HashMap<Integer,Product> cart = (HashMap<Integer, Product>) session.getAttribute("cart");
            cart.put(product.getId_product(),product);
            session.setAttribute("cart",cart);
        }
    }
}
