<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Vérification de l'adresse mail</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
<div class="full-wraper">
    <div class ="tout"><div class="main-text">
        <h1>Vérification</h1>
        <form action="MailVerification" method="post">
            <p class="champs">
                <label> Code reçu : </label>
                <input class=main-connexion" type="text" name="codeInput" placeholder="Code reçu" required /><br/>
            </p>


            <input class="main-connexion-submit" type="submit" value="Register !"/>
        </form>

    </div></div>
</div>
<% String registrationMessage = (String) request.getAttribute("registrationMessage");
    if (registrationMessage != null) {
%>
<p><%= registrationMessage %></p>
<%
    }
%>

</body>
</html>
