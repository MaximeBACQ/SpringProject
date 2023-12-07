package com.jeeSpring.Business.Servlet;

import com.jeeSpring.Controller.CartController;
import com.jeeSpring.Controller.ProductController;
import com.jeeSpring.Model.CartEntity;
import com.jeeSpring.Model.ProductEntity;
import com.jeeSpring.Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;

@WebServlet(name = "CartServlet", value = "/CartServlet")
public class CartServlet extends HttpServlet {

    private ProductController productController;
    private CartController cartController;

    @Override
    public void init() throws ServletException {
        super.init();

        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        productController = context.getBean(ProductController.class);
        cartController = context.getBean(CartController.class);

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();

            if (session.getAttribute("connectedUser") == null) {
                response.sendRedirect("loginPage");
            }else{
                response.sendRedirect("cart");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            HttpSession session = request.getSession();
            Object obj = session.getAttribute("connectedUser");

            if (obj == null) {
                response.sendRedirect("loginPage");
            }

            User user = (User) obj;

            ProductEntity product =  productController.getProductById(Long.parseLong(request.getParameter("productId")));

            CartEntity cart = new CartEntity(1, user, product);

            cartController.createCart(cart);

            response.sendRedirect("cart");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}