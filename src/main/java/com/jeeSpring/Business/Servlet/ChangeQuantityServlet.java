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

@WebServlet(name = "ChangeQuantityServlet", value = "/ChangeQuantityServlet")
public class ChangeQuantityServlet extends HttpServlet {

    private ProductController productController;
    private CartController cartController;

    @Override
    public void init() throws ServletException {
        super.init();

        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        productController = context.getBean(ProductController.class);
        cartController = context.getBean(CartController.class);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les paramètres de la requête POST
        long productId = Long.parseLong(request.getParameter("productId"));
        int newQuantity = Integer.parseInt(request.getParameter("newQuantity"));
        HttpSession session = request.getSession();
        User connectedPerson = (User) session.getAttribute("connectedUser");

        System.out.println("Produit id :" + productId + "newQuantity" + newQuantity);

        ProductEntity product = productController.getProductById(productId);

        if(newQuantity > product.getStock()) {
            //TODO : envoyer affichage dans panier : plus assez de stock, il reste actuellement n produits
            response.sendRedirect("cart");
        }else {
            CartEntity cartWithProductForUser = cartController.getCartByUserAndProduct(
                    connectedPerson, product
            );
            System.out.println(" cart qu'on a sélectionné : ");
            System.out.println(cartWithProductForUser.toString());
            cartWithProductForUser.setQuantity(newQuantity);
            cartController.updateCart(cartWithProductForUser);

            // Vous pouvez envoyer une réponse JSON ou un simple message de réussite ici si nécessaire
            response.sendRedirect("cart");
        }
    }
}
