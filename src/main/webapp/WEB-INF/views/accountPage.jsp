<%@ page import="com.jeeSpring.Model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Account Page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cart.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/account.css">

    <link href="https://fonts.cdnfonts.com/css/trade-gothic-lt-std" rel="stylesheet">
</head>
<body>
<jsp:include page="header.jsp"/>
<br><br><br>
<div class="account-container">
    <%  User user = (User) session.getAttribute("connectedUser");
        if(user != null) {
    %>
    <div class="user-info-card">
        <h2>Informations sur le Compte</h2>
        <div class="user-info">
            <p><span class="info-label">Nom :</span> <%= user.getName() %></p>
            <p><span class="info-label">Prénom :</span> <%= user.getSurname() %></p>
            <p><span class="info-label">Nom d'utilisateur :</span> <%= user.getUsername() %></p>
            <p><span class="info-label">Email :</span> <%= user.getEmail() %></p>
            <p><span class="info-label">Date de Naissance :</span> <%= user.getBirthDate() %></p>
            <p><span class="info-label">Genre :</span> <%= user.getGender() %></p>
        </div>
    </div>
    <%  }
    %>
</div>
<br><br><br>

</body>
</html>
