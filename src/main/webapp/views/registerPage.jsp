<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 01/11/2023
  Time: 18:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
<div class ="tout"><div class="main-text">
    <h1>Inscription</h1>
    <form action="RegisterServlet" method="post">
        <p class="champs">
            <label> Nom : </label>
            <input class=main-connexion" type="text" name="name" placeholder="Your Name" required /><br/>
        </p>

        <p class="champs">
            <label> Prenom : </label>
        <input class=main-connexion" type="text" name="surname" placeholder="Your Surname" required /><br/>
        </p>

        <p class="champs">
            <label> Pseudo : </label>
        <input class=main-connexion" type="text" name="username" placeholder="Your Username, that will be displayed to others" required /><br/>
        </p>
        <p class="champs">
            <label> Adresse mail : </label>
        <input class=main-connexion" type="email" name="email" placeholder="Your Email" required /><br/>
        </p>

        <p class="champs">
            <label> Date de naissance : </label>
        <input class=main-connexion" type="date" name="birthDate" required /><br/>
        </p>

        <p class="champs">
            <label> Genre : </label>
            <input class="main-connexion" type="radio" name="gender" id="male" value="male"/>
            <label for="male">M</label>
            <input type="radio" name="gender" id="female" value="female"/>
            <label for="female">F</label>
        </p>
    <%--<p class="champs">
            <label> Genre : </label>
        <input class=main-connexion" type="checkbox" name="gender" id="male"/>
        <label for="male">M</label><br>
        <input type="checkbox" name="gender" id="female"/>
        <label for="female">F</label><br>
        </p>--%>

        <p class="champs">
            <label> Mot de passe : </label>
        <input type="password" name="password" placeholder="Your Password" required /><br/>
        </p>

        <input class="main-connexion-submit" type="submit" value="Register !"/>
    </form>

</div></div>
    <% String registrationMessage = (String) request.getAttribute("registrationMessage");
        if (registrationMessage != null) {
    %>
    <p><%= registrationMessage %></p>
    <%
        }
    %>

</body>
</html>
