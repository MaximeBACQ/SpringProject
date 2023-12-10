    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <% String logFail = (String) session.getAttribute("refused");
        session.removeAttribute("refused");
    %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
<div class="full-wraper">
    <div class="tout">
        <div class="main-text">
            <%
                if(logFail != null){
            %>
            <div style="color:red;font-size:20px;">You have entered credentials that don't match any user in our database</div>
            <%
                }%>
            <h2>Login</h2><br>
            <p class="register">Doesn't have an account yet ? <a href="registerPage">Sign in</a></p>
            <form action="LoginServlet" method="post">
                <strong>Email address</strong><br>
                <input class="main-connexion-username" type="email" name="email" placeholder="Your Email" required /><br><br>

                <strong>Password</strong><br>
                <input class="main-connexion-password" type="password" name="password" placeholder="Your Password" required /><br>
                <input class="main-connexion-submit" type="submit" value="Login !">
            </form>

        </div>
    </div>

    <div class="login-illustration"><img src="${pageContext.request.contextPath}/images/login.png"></div>

</div>
</body>
</html>
