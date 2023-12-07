package com.jeeSpring.Business.Servlet;

import com.jeeSpring.Controller.CartController;
import com.jeeSpring.Controller.ProductController;
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

@WebServlet(name = "DeleteProductServlet", value = "/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {

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
        String deleteMsg = "";
        HttpSession session = request.getSession();

        if(request.getParameter("productId")!=null){
            if(session.getAttribute("connectedUser")!=null){

                User connectedUser = (User) session.getAttribute("connectedUser");
                ProductEntity productToDeleteFromCart = productController.getProductById(Long.parseLong(
                        request.getParameter("productId")
                ));

                cartController.deleteCart(cartController.getCartByUserAndProduct(connectedUser,
                        productToDeleteFromCart).getCartId() );

                response.sendRedirect("cart");
            }
            else{
                deleteMsg = "You tried to delete a product without being connected, please connect to see your cart";
                session.setAttribute("deleteMsg",deleteMsg);
            }
        }else{
            deleteMsg = "You tried to delete a product without using the associated trash bin button in your cart," +
                    " please use your cart to delete a product.";
            session.setAttribute("deleteMsg",deleteMsg);
        }
    }
}
