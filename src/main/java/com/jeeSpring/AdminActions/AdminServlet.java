package com.jeeSpring.AdminActions;


import com.jeeSpring.Controller.CompanyController;
import com.jeeSpring.Controller.ProductController;
import com.jeeSpring.Exceptions.UserExistenceException;
import com.jeeSpring.Model.CompanyEntity;
import com.jeeSpring.Model.ProductEntity;
import com.jeeSpring.Controller.UserController;
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
import java.util.ArrayList;

@WebServlet(name = "AdminServlet", value = "/AdminServlet")
public class AdminServlet extends HttpServlet {

    private ProductController productController;
    private UserController userController;
    private CompanyController companyController;
    @Override
    public void init() throws ServletException {
        super.init();

        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        productController = context.getBean(ProductController.class);
        companyController = context.getBean(CompanyController.class);
        userController = context.getBean(UserController.class);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String finalMsg = "";

        if (request.getParameter("email") != null) {
            String email = request.getParameter("email");
            if(userController.getUserByEmail(email)!=null) {
                User userToDelete = userController.getUserByEmail(email);
                finalMsg = "User" + userToDelete.getUsername() + "was deleted";
                session.setAttribute("finalMsgDelete", finalMsg);
                userController.deleteUser(userToDelete.getUserId());
                response.sendRedirect("adminPage");
            }else{
                finalMsg = "No user was found matching this email.";
                session.setAttribute("finalMsgDelete", finalMsg);
                response.sendRedirect("adminPage");
            }
        }
        if (request.getParameter("idForSelection") != null) {
            String idForSelection = request.getParameter("idForSelection");
            try {
                User selectedUser = userController.getUserById(Long.parseLong(idForSelection));
                if (selectedUser != null) {
                    finalMsg = "The user " +selectedUser.getUsername()+ " is selected.";
                    session.setAttribute("finalMsgSelectUser", finalMsg);
                } else {
                    finalMsg = "The id you've entered doesn't exist in our database";
                    session.setAttribute("finalMsgSelectUser", finalMsg);
                }
            } catch (NumberFormatException | UserExistenceException e) {
                finalMsg = "Invalid ID format or no user found for this ID";
                session.setAttribute("finalMsgSelectUser", finalMsg);
            }
            response.sendRedirect("adminPage");
        }
        if (request.getParameter("idToPromote") != null) {
            String idToPromote = request.getParameter("idToPromote");
            try {
                User userToPromote = userController.getUserById(Long.parseLong(idToPromote));
                if (userToPromote != null) {
                    if (!userToPromote.getIsModerator()) {
                        userToPromote.setIsModerator(true);
                        userController.updateUser(userToPromote);
                        finalMsg = "User " + userToPromote.getUsername() + " is now a moderator.";
                    } else {
                        finalMsg = "User " + userToPromote.getUsername() + " is already a moderator.";
                    }
                } else {
                    finalMsg = "No user found for this ID";
                }
            } catch (NumberFormatException | UserExistenceException e) {
                finalMsg = "Invalid ID format or no user found for this ID";
            }
            session.setAttribute("finalMsgModerator", finalMsg);
            response.sendRedirect("adminPage");
        }
        if (request.getParameter("submitCompany") != null) {
            try {
                long userId = Long.parseLong(request.getParameter("userForCompany"));
                User selectedUser = userController.getUserById(userId);

                if (selectedUser != null) {
                    long companyId = Long.parseLong(request.getParameter("CompanyId"));
                    CompanyEntity userCompany = companyController.getCompanyById(companyId);

                    if (userCompany != null) {
                        selectedUser.setCompany(userCompany);
                        userController.updateUser(selectedUser);
                        finalMsg = "Added " + userCompany.getName() + " to " + selectedUser.getUsername();
                    } else {
                        finalMsg = "Company not found for id: " + companyId;
                    }
                } else {
                    finalMsg = "No user found for this id";
                }
            } catch (NumberFormatException | UserExistenceException e) {
                finalMsg = "Invalid input or no user/company found for given IDs";
            }
            session.setAttribute("finalMsgCompany", finalMsg);
            response.sendRedirect("adminPage");
        }

        if (request.getParameter("userToMakeSeller") != null) {
            String idToMakeSeller = request.getParameter("userToMakeSeller");
            try {
                User userToMakeSeller = userController.getUserById(Long.parseLong(idToMakeSeller));
                if (userToMakeSeller != null) {
                    if (!userToMakeSeller.getIsSeller()) {
                        userToMakeSeller.setIsSeller(true);
                        userController.updateUser(userToMakeSeller);
                        finalMsg = "User " + userToMakeSeller.getUsername() + " is now a seller.";
                    } else {
                        finalMsg = "User " + userToMakeSeller.getUsername() + " is already a seller.";
                    }
                } else {
                    finalMsg = "No user found for this ID.";
                }
            } catch (NumberFormatException | UserExistenceException e) {
                finalMsg = "Invalid ID format or no user found for this ID.";
            }
            session.setAttribute("finalMsgSeller", finalMsg);
            response.sendRedirect("adminPage");
        }

        if (request.getParameter("userToRemoveSeller") != null) {
            String idToRemoveSeller = request.getParameter("userToRemoveSeller");
            try {
                User userToRemoveSeller = userController.getUserById(Long.parseLong(idToRemoveSeller));
                if (userToRemoveSeller != null) {
                    if (userToRemoveSeller.getIsSeller()) {
                        userToRemoveSeller.setIsSeller(false);
                        userController.updateUser(userToRemoveSeller);
                        finalMsg = "User " + userToRemoveSeller.getUsername() + " is no longer a seller.";
                    } else {
                        finalMsg = "User " + userToRemoveSeller.getUsername() + " is not a seller.";
                    }
                } else {
                    finalMsg = "No user found for this ID.";
                }
            } catch (NumberFormatException | UserExistenceException e) {
                finalMsg = "Invalid ID format or no user found for this ID.";
            }
            session.setAttribute("finalMsgRemoveSeller", finalMsg);
            response.sendRedirect("adminPage");
        }

        if (request.getParameter("pToSearch") != null) {
            String labelToSearch = request.getParameter("pToSearch").trim();
            try {
                ArrayList<ProductEntity> products = new ArrayList<>(productController.searchProductsByLabelAndDescription(labelToSearch));

                if (!products.isEmpty()) {
                    finalMsg = "Products found with the label: " + labelToSearch;
                    session.setAttribute("ProductList", products);
                } else {
                    finalMsg = "No products found with the label: " + labelToSearch;
                }
            } catch (Exception e) {
                finalMsg = "Error searching for products: " + e.getMessage();
            }
            session.setAttribute("finalMsgSearchProducts", finalMsg);
            response.sendRedirect("adminPage");
        }
        if (request.getParameter("pToDelete") != null) {
            try {
                ProductEntity selectedProduct = productController.getProductById(Long.parseLong(request.getParameter("pToDelete")));
                if (selectedProduct != null) {
                    productController.deleteProduct(selectedProduct.getProductId());
                    finalMsg = "Product has been deleted";
                } else {
                    finalMsg = "No product found for this id";
                }
            } catch (NumberFormatException e) {
                finalMsg = "Invalid product ID format";
            }
            session.setAttribute("finalMsgDeleteP", finalMsg);
            response.sendRedirect("adminPage");
        }
    }




    public void destroy(){

    }
}