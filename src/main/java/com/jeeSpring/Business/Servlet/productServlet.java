package com.jeeSpring.Business.Servlet;

import com.jeeSpring.Controller.ProductController;
import com.jeeSpring.Controller.UserController;
import com.jeeSpring.Model.ProductEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;

@WebServlet(name = "ProductServlet", urlPatterns = "/productPage")
public class productServlet extends HttpServlet {

    private ProductController productController;

    @Override
    public void init() throws ServletException {
        super.init();

        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        productController = context.getBean(ProductController.class);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("products");

        if (productId != null && !productId.isEmpty()) {

            long productIdInt = Long.parseLong(productId);

            ProductEntity product = productController.getProductById(productIdInt);

            if (product != null) {
                request.setAttribute("product", product);
                request.getRequestDispatcher("/productPage").forward(request, response);
            } else {
                response.getWriter().println("Le produit n'est pas disponible.");
            }
        } else {
            response.getWriter().println("Aucun produit spécifié.");
        }
    }
}
