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
import java.util.List;

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
                    finalMsg = "No user was found matching this email";
                    session.setAttribute("finalMsgDelete", finalMsg);
                    response.sendRedirect("adminPage");
                }
            }
            if(request.getParameter("idForSelection")!=null) {
                System.out.println("jusqu'ici c'est ok");
                try {
                    if(userController.getUserById(Long.parseLong(request.getParameter("idForSelection")))!=null){
                        User selectedUser = userController.getUserById(Long.parseLong(request.getParameter("idForSelection")));
                        System.out.println("pseudo du boug" + selectedUser.getUsername());
                        finalMsg="The user you are searching for is :" + selectedUser.getUsername();
                        session.setAttribute("finalMsgSelectUser",finalMsg);
                        response.sendRedirect("adminPage");
                    }else{
                        finalMsg="The id you've entered doesn't exist in our database";
                        session.setAttribute("finalMsgSelectUser",finalMsg);
                    }
                } catch (UserExistenceException e) {
                    throw new RuntimeException(e);
                }
            }
            if(request.getParameter("idToPromote")!=null){
                try{
                    if(userController.getUserById(Long.parseLong(request.getParameter("idToPromote")))!=null){
                        User userToPromote = userController.getUserById(Long.parseLong(request.getParameter("idToPromote")));
                        if (!userToPromote.getIsModerator()) {
                            finalMsg = "User is now a moderator";
                            session.setAttribute("finalMsgModerator", finalMsg);
                            userToPromote.setIsModerator(true);
                            userController.updateUser(userToPromote);
                            response.sendRedirect("adminPage");
                        } else {
                            finalMsg = "User was already a moderator, nothing happened";
                            session.setAttribute("finalMsgModerator", finalMsg);
                            response.sendRedirect("adminPage");
                        }
                    }
                } catch (UserExistenceException e) {
                    finalMsg = "No user found for this id";
                    session.setAttribute("finalMsgModerator",finalMsg);
                    response.sendRedirect("adminPage");
                }
            }
            if (request.getParameter("submitCompany") != null) {
                System.out.println("il se passe qqchose");
                try {
                    long userId = Long.parseLong(request.getParameter("userForCompany"));
                    if(userController.getUserById(userId)!=null) {
                        User selectedUser = userController.getUserById(userId);
                        System.out.println("ton user :" + selectedUser.getUsername());
                        session.setAttribute("selectedUser", selectedUser);

                        if (selectedUser.getCompany() != null) {
                            finalMsg = "User already works for a company";
                            session.setAttribute("finalMsgCompany", finalMsg);
                            response.sendRedirect("adminPage");
                        } else if (!selectedUser.getIsModerator()) {
                            finalMsg = "User is not a moderator";
                            session.setAttribute("finalMsgCompany", finalMsg);
                            response.sendRedirect("adminPage");
                        } else {
                            long companyId = Long.parseLong(request.getParameter("CompanyId"));
                            CompanyEntity userCompany = companyController.getCompanyById(companyId);
                            System.out.println("la company:" + userCompany.getName());

                            if (userCompany != null) {
                                //try {
                                selectedUser.setCompany(userCompany);
                                userController.updateUser(selectedUser);
                                response.sendRedirect("adminPage");
                                       /* if (rowsUpdated > 0) {
                                            finalMsg = "Added " + selectedUser.getUsername() + " to " + userCompany.getName();
                                            session.setAttribute("finalMsg", finalMsg);
                                        } else {
                                            finalMsg = "Failed to update user's company";
                                            session.setAttribute("finalMsg", finalMsg);
                                        }
                                    } catch (Exception e) {
                                        if (transaction != null && transaction.isActive()) {
                                            transaction.rollback();
                                        }
                                        e.printStackTrace();
                                    }
                                } else {
                                    finalMsg = "Company not found for id: " + companyId;
                                    session.setAttribute("finalMsg", finalMsg); */
                            }
                        }
                    }
                } catch (UserExistenceException e) {
                    finalMsg = "No user found for this id";
                    session.setAttribute("finalMsgCompany", finalMsg);
                }
            }

            if (request.getParameter("userToMakeSeller") != null) {
                try{
                    if(userController.getUserById(Long.parseLong(request.getParameter("userToMakeSeller")))!=null) {
                        System.out.println("test userToMakeSeller" + request.getParameter("userToMakeSeller"));
                        User userToMakeSeller = userController.getUserById(Long.parseLong(request.getParameter("userToMakeSeller")));
                        if(userToMakeSeller.getIsModerator() || userToMakeSeller.getIsAdmin()) { //if not mod/admin
                            if (!userToMakeSeller.getIsSeller()) {
                                finalMsg = "User is now a seller";
                                session.setAttribute("finalMsgSeller", finalMsg);
                                userToMakeSeller.setIsSeller(true);
                                userController.updateUser(userToMakeSeller);
                                response.sendRedirect("adminPage");
                            } else {
                                finalMsg = "User was already a seller, nothing happened";
                                session.setAttribute("finalMsgSeller", finalMsg);
                                response.sendRedirect("adminPage");
                            }
                        }else{
                            finalMsg = "User is not a moderator nor an administrator, nothing happened.";
                            session.setAttribute("finalMsgRemoveSeller", finalMsg);
                            response.sendRedirect("adminPage");
                        }
                    }
                }catch (UserExistenceException e) {
                    finalMsg = "No user found for this id";
                    session.setAttribute("finalMsgSeller", finalMsg);
                }
            }

                if (request.getParameter("userToRemoveSeller") != null) {
                    try {
                        if (userController.getUserById(Long.parseLong(request.getParameter("userToRemoveSeller"))) != null) {
                            User userToMakeSeller = userController.getUserById(Long.parseLong(request.getParameter("userToRemoveSeller")));
                            if(userToMakeSeller.getIsModerator() || userToMakeSeller.getIsAdmin()) { //if not mod/admin
                                if (userToMakeSeller.getIsSeller()) {
                                    finalMsg = "User is not a seller anymore.";
                                    session.setAttribute("finalMsgRemoveSeller", finalMsg);
                                    userToMakeSeller.setIsSeller(false);
                                    userController.updateUser(userToMakeSeller);
                                    response.sendRedirect("adminPage");
                                } else {
                                    finalMsg = "User was already not a seller, nothing happened.";
                                    session.setAttribute("finalMsgRemoveSeller", finalMsg);
                                    response.sendRedirect("adminPage");
                                }
                            }else{
                                finalMsg = "User is not a moderator nor an administrator, nothing happened.";
                                session.setAttribute("finalMsgRemoveSeller", finalMsg);
                                response.sendRedirect("adminPage");
                            }
                        }
                    } catch (UserExistenceException e) {
                        finalMsg = "No user found for this id";
                        session.setAttribute("finalMsgSeller", finalMsg);
                    }
                }
//                try {
//                    int userId = Integer.parseInt(request.getParameter("userForCompany"));
//                    User selectedUser = userController.getUserById(userId);
//                    session.setAttribute("selectedUser", selectedUser);
//
//                    if (selectedUser != null && (!selectedUser.getIsModerator()||!selectedUser.getIsAdmin())){
//                        finalMsg = "User is not a moderator";
//                        session.setAttribute("finalMsgSeller", finalMsg);
//                    }else if(selectedUser.getIsSeller()){
//                        finalMsg="User is already a seller";
//                        session.setAttribute("finalMsgSeller",finalMsg);
//                    }else{
//                        selectedUser.setIsSeller(true);
//                        userController.updateUser(selectedUser);
//                    }
//                } catch (UserExistenceException e) {
//                    finalMsg = "No user found for this id";
//                    session.setAttribute("finalMsgSeller",finalMsg);
//                }
                if (request.getParameter("pToSearch") != null) {

                try {
                    String pToSearch = request.getParameter("pToSearch");
                    List<ProductEntity> selectedProducts = productController.searchProductsByLabel(pToSearch);
                    if(!selectedProducts.isEmpty()) {
                        finalMsg="Here are the products containing the label :"+(String)request.getParameter("pToSearch");
                        session.setAttribute("finalMsgSearchProducts",finalMsg);
                        session.setAttribute("ProductList", selectedProducts);
                    }
                }catch (Exception e){
                    finalMsg="No products found with this name";
                    session.setAttribute("finalMsgSearchProducts",finalMsg);
                }
            }
            if (request.getParameter("pToDelete") != null) {
                try {
                    ProductEntity selectedProduct = productController.getProductById(Long.parseLong(
                            request.getParameter("pToDelete"))
                    );
                    if(selectedProduct != null) {
                        finalMsg = "Product has been deleted";
                        session.setAttribute("finalMsgDeleteP",finalMsg);
                        productController.deleteProduct(selectedProduct.getProductId());
                    }
                }catch (Exception e){
                    finalMsg = "No product found for this id";
                    session.setAttribute("finalMsgDeleteP",finalMsg);
                }
            }
        }




    public void destroy(){

    }
} 