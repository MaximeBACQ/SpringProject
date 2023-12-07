package com.jeeSpring.Business.Servlet;

import com.jeeSpring.Controller.UserController;
import com.jeeSpring.Mail.MailSender;
import com.jeeSpring.Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    private UserController userController;

    @Override
    public void init() throws ServletException {
        super.init();

        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        userController = context.getBean(UserController.class);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServletException {
        response.setContentType("text/html");

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        LocalDate birthDate = LocalDate.parse(request.getParameter("birthDate"));
        String gender = request.getParameter("gender");
        String password = request.getParameter("password");
        String passwordHashed = BCrypt.hashpw(password, BCrypt.gensalt());


        //User newUser = new User(name, surname, username, email, birthDate, gender, password, false, false, new Boolean(false));
        User newUser = new User();
        newUser.setName(name);
        newUser.setSurname(surname);
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setBirthDate(birthDate);
        newUser.setGender(gender);
        newUser.setPassword(passwordHashed);
        newUser.setIsAdmin(false);
        newUser.setIsModerator(false);
        newUser.setIsSeller(false);

        newUser.setCompany(null);

        Random random = new Random();
        String codeExpected = String.valueOf(100000+random.nextInt(899999));

        MailSender.sendEmail(newUser.getEmail(), "Code de vérification",
                "Voici votre code pour créer votre compte" +
                        "\n" +
                        "\n" +
                        "Voici votre code de vérification pour créer votre compte ZGLABIM :\n"+codeExpected);
        HttpSession session = request.getSession();
        session.setAttribute("codeExpected", codeExpected);
        System.out.println(codeExpected);
        session.setAttribute("newUser", newUser);
        response.sendRedirect("mailVerification");
    }

        public void destroy () {
        }
    }