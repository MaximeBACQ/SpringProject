package com.jeeSpring.Business.Servlet;

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

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    private UserController userController;

    @Override
    public void init() throws ServletException {
        super.init();

        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        userController = context.getBean(UserController.class);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User authenticatedUser = userController.authenticateUser(email, password);

        HttpSession session = request.getSession();

        //System.out.println(authenticatedUser.toString());

        if (authenticatedUser != null) {
            session.setAttribute("connectedUser", authenticatedUser);
            response.sendRedirect("/");
        } else {
            session.setAttribute("refused", "true");
            response.sendRedirect("loginPage");
        }

    }

    public void destroy() {
    }
}