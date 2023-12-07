package com.jeeSpring.AdminActions;
/*
import com.jeeSpring.Model.CompanyEntity;
import com.jeeSpring.Model.ProductEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "ModeratorServlet", value = "/ModeratorServlet")
public class ModeratorServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ProductDAO productDAO = new ProductDAO();
        HttpSession session = request.getSession();
        SiteUser connectedPerson = (SiteUser) session.getAttribute("connectedUser");
        String finalMsg = "";
        boolean isAdmOrModConnected = connectedPerson.getIsModerator() || connectedPerson.getIsAdmin();
        if (isAdmOrModConnected) {
            if (request.getParameter("addProduct") != null) {
                    String label = request.getParameter("label");
                    int price = Integer.parseInt(request.getParameter("price"));
                    int stock = Integer.parseInt(request.getParameter("stock"));
                    String description = request.getParameter("description");
                    String imageLink = request.getParameter("imageLink");
                    final CompanyEntity connectedPersonCompany = connectedPerson.getCompany();

                    ProductEntity newProduct = new ProductEntity();
                    newProduct.setLabel(label);
                    newProduct.setPrice(price);
                    newProduct.setStock(stock);
                    newProduct.setDescription(description);
                    newProduct.setProductImage(imageLink);
                    newProduct.setCompanyId(connectedPersonCompany);
                    session.setAttribute("finalMsg", finalMsg);

                    finalMsg = "Your product has been added.";

                    productDAO.createProduct(newProduct);

                    response.sendRedirect("index.jsp");
            }
        }
    }
    public void destroy(){

    }
} */