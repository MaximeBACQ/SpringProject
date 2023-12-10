<%@ page import="com.jeeSpring.Model.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <link href="https://fonts.cdnfonts.com/css/trade-gothic-lt-std" rel="stylesheet">

</head>
<body>
<div class="header">
<div class="header-banniere">
    <a href="/"><img id="header-banniere-logo" src="${pageContext.request.contextPath}/images/fire.png" alt="Logo Tenor"></a>
    <h2 id="header-banniere-titre"> ZGLABIM </h2>
    <li>
    <%
        HttpSession userSession = request.getSession();
        User connectedUser = (User) userSession.getAttribute("connectedUser");

        if (connectedUser != null) {
            if(connectedUser.getIsAdmin()){
                %>
        <a class="connected" href="adminPage">Admin</a>
        <a href="">|</a>
        <a class="connected" href="accountPage">Profile</a>
        <a href="">|</a>
        <a class="connected" href="LogoutServlet">Déconnexion</a>
                <%
            }
            else if(connectedUser.getIsModerator()){
            %>
        <a class="connected" href="moderatorPage">Moderator</a>
        <a href="">|</a>
        <a class="connected" href="accountPage">Profile</a>
        <a href="">|</a>
        <a class="connected" href="LogoutServlet">Déconnexion</a>
            <%
            }
            else {
            %>
        <a class="connected" href="accountPage">Profile</a>
        <a href="">|</a>
        <a class="connected" href="LogoutServlet">Déconnexion</a>
            <%
            }
        }
        else {
            %>
            <div class="neon_inscription">
                <a href="loginPage">Connexion</a>
            </div>
            <%
        }
        %>
    </li>
</div>

<div class='header-menu'>
    <ul>
        <li class="no-search"><a href='/'> <i class='fa fa-house'></i>Accueil</a></li>
        <li class="searchbar">
            <form id="searchForm" action="productPage" method="get">
                <input type="text" id="productId" name="products" placeholder="Nom du produit">
                <button type="submit"></button>
            </form>

        </li>

        <li class="no-search"><a href='CartServlet'> <i class='fa fa-bag-shopping'></i>Panier</a></li>
        <div class='header-sous-menu-bag'>
            <ul>
                <li> 0 article(s) <br/> Total (TTC) : 0 €</li>
            </ul>
        </div>

    </ul>
</div>
</div>
<script>
    function searchProducts() {

    }
</script>


</body>
</html>