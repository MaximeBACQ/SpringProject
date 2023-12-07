package com.jeeSpring.AdminActions;
/*
import com.example.projetjee.DAO.*;
import com.example.projetjee.Model.CompanyEntity;
import com.example.projetjee.Model.ProductEntity;
import com.example.projetjee.Model.SiteUser;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminServlet", value = "/AdminServlet")
public class AdminServlet extends HttpServlet {
        public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            UserDAO userDAO = new UserDAO();
            ProductDAO productDAO = new ProductDAO();
            HttpSession session = request.getSession();
            String finalMsg = "";

            if (request.getParameter("email") != null) {
                String email = request.getParameter("email");
                if(userDAO.findUserByEmail(email)!=null) {
                    SiteUser userToDelete = userDAO.findUserByEmail(email);
                    finalMsg = "User" + userToDelete.getUsername() + "was deleted";
                    session.setAttribute("finalMsgDelete", finalMsg);
                    userDAO.deleteUser(userToDelete);
                    response.sendRedirect("adminPage.jsp");
                }else{
                    finalMsg = "No user was found matching this email";
                    session.setAttribute("finalMsgDelete", finalMsg);
                    response.sendRedirect("adminPage.jsp");
                }
            }
            if(request.getParameter("idForSelection")!=null) {
                try {
                    System.out.println("jusqu'ici c'est ok");
                    if(userDAO.findUserById(Integer.parseInt(request.getParameter("idForSelection")))!=null){
                        SiteUser selectedUser = userDAO.findUserById(Integer.parseInt(request.getParameter("idForSelection")));
                        System.out.println("pseudo du boug" + selectedUser.getUsername());
                        finalMsg="The user you are searching for is :" + selectedUser.getUsername();
                        session.setAttribute("finalMsgSelectUser",finalMsg);
                        response.sendRedirect("adminPage.jsp");
                    }else{
                        finalMsg="The id you've entered doesn't exist in our database";
                        session.setAttribute("finalMsgSelectUser",finalMsg);
                    }
                } catch (UserExistenceException e) {
                    finalMsg="The id you've entered doesn't exist in our database";
                    session.setAttribute("finalMsgSelectUser",finalMsg);
                    response.sendRedirect("adminPage.jsp");
                }
            }
            if(request.getParameter("idToPromote")!=null){
                try{
                    if(userDAO.findUserById(Integer.parseInt(request.getParameter("idToPromote")))!=null){
                        SiteUser userToPromote = userDAO.findUserById(Integer.parseInt(request.getParameter("idToPromote")));
                        if (!userToPromote.getIsModerator()) {
                            finalMsg = "User is now a moderator";
                            session.setAttribute("finalMsgModerator", finalMsg);
                            userToPromote.setIsModerator(true);
                            userDAO.updateUser(userToPromote);
                            response.sendRedirect("adminPage.jsp");
                        } else {
                            finalMsg = "User was already a moderator, nothing happened";
                            session.setAttribute("finalMsgModerator", finalMsg);
                            response.sendRedirect("adminPage.jsp");
                        }
                    }
                } catch (UserExistenceException e) {
                    finalMsg = "No user found for this id";
                    session.setAttribute("finalMsgModerator",finalMsg);
                    response.sendRedirect("adminPage.jsp");
                }
            }
            if (request.getParameter("submitCompany") != null) {
                System.out.println("il se passe qqchose");
                try {
                    int userId = Integer.parseInt(request.getParameter("userForCompany"));
                    if(userDAO.findUserById(userId)!=null) {
                        SiteUser selectedUser = userDAO.findUserById(userId);
                        System.out.println("ton user :" + selectedUser.getUsername());
                        session.setAttribute("selectedUser", selectedUser);

                        if (selectedUser.getCompany() != null) {
                            finalMsg = "User already works for a company";
                            session.setAttribute("finalMsgCompany", finalMsg);
                            response.sendRedirect("adminPage.jsp");
                        } else if (!selectedUser.getIsModerator()) {
                            finalMsg = "User is not a moderator";
                            session.setAttribute("finalMsgCompany", finalMsg);
                            response.sendRedirect("adminPage.jsp");
                        } else {
                            CompanyDAO cpDAO = new CompanyDAO();
                            int companyId = Integer.parseInt(request.getParameter("CompanyId"));
                            CompanyEntity userCompany = cpDAO.findById(companyId);
                            System.out.println("la company:" + userCompany.getName());

                            if (userCompany != null) {
                                //try {
                                selectedUser.setCompany(userCompany);
                                userDAO.updateUser(selectedUser);
                                response.sendRedirect("adminPage.jsp");
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
                                    session.setAttribute("finalMsg", finalMsg); */ /*
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
                    if(userDAO.findUserById(Integer.parseInt(request.getParameter("userToMakeSeller")))!=null) {
                        System.out.println("test userToMakeSeller" + request.getParameter("userToMakeSeller"));
                        SiteUser userToMakeSeller = userDAO.findUserById(Integer.parseInt(request.getParameter("userToMakeSeller")));
                        if(userToMakeSeller.getIsModerator() || userToMakeSeller.getIsAdmin()) { //if not mod/admin
                            if (!userToMakeSeller.getIsSeller()) {
                                finalMsg = "User is now a seller";
                                session.setAttribute("finalMsgSeller", finalMsg);
                                userToMakeSeller.setIsSeller(true);
                                userDAO.updateUser(userToMakeSeller);
                                response.sendRedirect("adminPage.jsp");
                            } else {
                                finalMsg = "User was already a seller, nothing happened";
                                session.setAttribute("finalMsgSeller", finalMsg);
                                response.sendRedirect("adminPage.jsp");
                            }
                        }else{
                            finalMsg = "User is not a moderator nor an administrator, nothing happened.";
                            session.setAttribute("finalMsgRemoveSeller", finalMsg);
                            response.sendRedirect("adminPage.jsp");
                        }
                    }
                }catch (UserExistenceException e) {
                    finalMsg = "No user found for this id";
                    session.setAttribute("finalMsgSeller", finalMsg);
                }
            }

                if (request.getParameter("userToRemoveSeller") != null) {
                    try {
                        if (userDAO.findUserById(Integer.parseInt(request.getParameter("userToRemoveSeller"))) != null) {
                            SiteUser userToMakeSeller = userDAO.findUserById(Integer.parseInt(request.getParameter("userToRemoveSeller")));
                            if(userToMakeSeller.getIsModerator() || userToMakeSeller.getIsAdmin()) { //if not mod/admin
                                if (userToMakeSeller.getIsSeller()) {
                                    finalMsg = "User is not a seller anymore.";
                                    session.setAttribute("finalMsgRemoveSeller", finalMsg);
                                    userToMakeSeller.setIsSeller(false);
                                    userDAO.updateUser(userToMakeSeller);
                                    response.sendRedirect("adminPage.jsp");
                                } else {
                                    finalMsg = "User was already not a seller, nothing happened.";
                                    session.setAttribute("finalMsgRemoveSeller", finalMsg);
                                    response.sendRedirect("adminPage.jsp");
                                }
                            }else{
                                finalMsg = "User is not a moderator nor an administrator, nothing happened.";
                                session.setAttribute("finalMsgRemoveSeller", finalMsg);
                                response.sendRedirect("adminPage.jsp");
                            }
                        }
                    } catch (UserExistenceException e) {
                        finalMsg = "No user found for this id";
                        session.setAttribute("finalMsgSeller", finalMsg);
                    }
                }
//                try {
//                    int userId = Integer.parseInt(request.getParameter("userForCompany"));
//                    SiteUser selectedUser = userDAO.findUserById(userId);
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
//                        userDAO.updateUser(selectedUser);
//                    }
//                } catch (UserExistenceException e) {
//                    finalMsg = "No user found for this id";
//                    session.setAttribute("finalMsgSeller",finalMsg);
//                }
                if (request.getParameter("pToSearch") != null) {

                try {
                    TypedQuery<ProductEntity> query = JPAUtil.getEntityManager()
                            .createQuery("SELECT p FROM ProductEntity p WHERE label=:label",ProductEntity.class);
                    List<ProductEntity> selectedProducts =  query.getResultList();
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
                    ProductEntity selectedProduct = productDAO.findProductById(Integer.parseInt(
                            request.getParameter("pToDelete"))
                    );
                    if(selectedProduct != null) {
                        finalMsg = "Product has been deleted";
                        session.setAttribute("finalMsgDeleteP",finalMsg);
                        productDAO.deleteProduct(selectedProduct);
                    }
                }catch (Exception e){
                    finalMsg = "No product found for this id";
                    session.setAttribute("finalMsgDeleteP",finalMsg);
                }
            }
        }




    public void destroy(){

    }
} */