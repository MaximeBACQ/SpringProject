package com.jeeSpring.Business.Servlet;

import com.jeeSpring.Controller.BankController;
import com.jeeSpring.Controller.CartController;
import com.jeeSpring.Controller.ProductController;
import com.jeeSpring.Model.BankAccountEntity;
import com.jeeSpring.Model.CartEntity;
import com.jeeSpring.Model.ProductEntity;
import com.jeeSpring.Model.User;
import jakarta.servlet.http.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.*;
import jakarta.servlet.ServletException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CheckoutServlet", value = "/CheckoutServlet")
public class CheckoutServlet extends HttpServlet{

    private BankController bankController;
    private CartController cartController;
    private ProductController productController;

    @Override
    public void init() throws ServletException {
        super.init();

        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        productController = context.getBean(ProductController.class);
        bankController = context.getBean(BankController.class);
        cartController = context.getBean(CartController.class);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String finalMsg = "";
        HttpSession session = request.getSession();
        if(session.getAttribute("connectedUser")!=null) {
            if (request.getParameter("Payment") != null) {
                User connectedPerson = (User) session.getAttribute("connectedUser");
                List<CartEntity> carts = cartController.getCartsByUser(connectedPerson);
                int amountDue = 0;
                for(CartEntity cart : carts){
                    int price = cart.getProduct().getPrice();
                    int quantity = cart.getQuantity();
                    amountDue += price * quantity;
                }
                String expiryDate = request.getParameter("month") + "/" + request.getParameter("year");
                if(bankController.isAccountValid(Long.parseLong(request.getParameter("CardNumber")),
                        expiryDate,Integer.parseInt(request.getParameter("cvv")))!=null){
                    BankAccountEntity userBankAccount = bankController.isAccountValid
                            (Long.parseLong(request.getParameter("CardNumber")), expiryDate
                                    ,Integer.parseInt(request.getParameter("cvv")));
                    if(userBankAccount.getBankBalance()<amountDue){ // doesn't have enough money
                        finalMsg="You do not have sufficient funds in your bank account";
                        session.setAttribute("finalMsgPayment",finalMsg);
                        response.sendRedirect("cart");
                    }else{ // has enough money, entered right bank account -> update balance, update stocks
                        userBankAccount.setBankBalance(userBankAccount.getBankBalance() - amountDue);
                        bankController.updateBank(userBankAccount);
                        connectedPerson.setLoyaltyPoints(amountDue*5); // 1 euro = 5 points
                        for(CartEntity cart : carts){
                            ProductEntity productToUpdate = productController.getProductById(cart.getProduct().getProductId());
                            productToUpdate.setStock(productToUpdate.getStock() - cart.getQuantity());
                            productController.updateProduct(productToUpdate); // reduce stock in db
                            cartController.deleteCart(cart.getCartId());
                        }
                        response.sendRedirect("cart");
                    }
                }
                else {
                    finalMsg = "Invalid bank account";
                    request.setAttribute("finalMsgPayment", finalMsg);
                    response.sendRedirect("cart");
                }

            } else {
                finalMsg = "You entered wrong payment credentials";
                request.setAttribute("finalMsgPayment", finalMsg);
                response.sendRedirect("cart");
            }
        }else{
            response.sendRedirect("loginPage");
        }
    }
}
