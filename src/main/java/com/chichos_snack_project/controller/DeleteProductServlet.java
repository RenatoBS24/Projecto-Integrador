package com.chichos_snack_project.controller;
import com.chichos_snack_project.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteProductServlet", value = "/DeleteProduct")
public class DeleteProductServlet extends HttpServlet {
    public void init() {
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id_product");
        String code = (String)request.getSession().getAttribute("code");
        String code_entered = request.getParameter("code_entered");
        if(ProductService.deleteProduct(id,code,code_entered)) {
            response.sendRedirect("Products");
        }else{
            request.setAttribute("Error","No se pudo eliminar el producto");
            request.getRequestDispatcher("products.jsp").forward(request,response);
        }

    }
    public void destroy() {
    }
}
