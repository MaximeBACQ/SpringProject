package com.jeeSpring.Business.Servlet;

import com.jeeSpring.Controller.UserController;
import com.jeeSpring.Exceptions.UserExistenceException;
import com.jeeSpring.Mail.MailSender;
import com.jeeSpring.Model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;

@WebServlet(name = "MailVerification", value = "/MailVerification")
public class MailVerification extends HttpServlet {

    private UserController userController;

    @Override
    public void init() throws ServletException {
        super.init();

        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        userController = context.getBean(UserController.class);

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String codeInput = request.getParameter("codeInput");
        HttpSession session = request.getSession();
        Object objectCode = session.getAttribute("codeExpected");
        String codeExpected = (String) objectCode;
        Object objectNewUser = session.getAttribute("newUser");
        User newUser = (User) objectNewUser;
        try{
            System.out.println(codeExpected);
            System.out.println(codeInput);
            if (codeInput.equals(codeExpected)){
                userController.createUser(newUser);
                MailSender.sendEmail(newUser.getEmail(), "Votre compte a été créé avec succès !",
                        "Votre compte a été créé avec succès. Retrouvez dès à présent l'ensemble de nos produits sur ZGLABIM");
                response.sendRedirect("/");
            }else{
                response.sendRedirect("mailVerification");
            }
        }catch (UserExistenceException e) {
            String errorMessage = "Erreur : cette adresse e-mail existe déjà.";
            request.setAttribute("registrationMessage", errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("registerPage");
            dispatcher.forward(request, response); // Utilisation de forward pour envoyer le message à la page
        }
    }
    public void destroy () {
    }
}