<%@ page import="com.jeeSpring.Model.ProductEntity" %>
<%@ page import="com.jeeSpring.Controller.CartController" %>
<%@ page import="com.jeeSpring.Model.CartEntity" %>
<%@ page import="java.util.ArrayList" %>

<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="com.jeeSpring.Model.User" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Panier</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cart.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link href="https://fonts.cdnfonts.com/css/trade-gothic-lt-std" rel="stylesheet">
    <script>
        function updateQuantity(productId) {
            var newQuantity = document.getElementById("quantity_" + productId).valueAsNumber;

            console.log("Nouvelle quantité : " + newQuantity);

            // Vérifier si la nouvelle quantité est un nombre valide
            if (!isNaN(newQuantity)) {
                console.log("Envoi de la requête au serveur...");
                console.log("NewQuantity après la condition sur le fait que c'est un nombre"+newQuantity)
                // Utilise  z une requête AJAX pour envoyer la nouvelle quantité au serveur
                var xhr = new XMLHttpRequest();
                console.log("NewQuantity après l'ouverture de la variable xhr sur le fait que c'est un nombre"+newQuantity)
                xhr.open("POST", "ChangeQuantityServlet?productId=" + productId + "&newQuantity=" + newQuantity, true);
                xhr.send();
                console.log("NewQuantity après l'envoi de la variable xhr sur le fait que c'est un nombre"+newQuantity)


                // Vous pouvez gérer la réponse du serveur ici si nécessaire
                xhr.onreadystatechange = function() {
                    if (xhr.readyState === XMLHttpRequest.DONE) {
                        if (xhr.status === 200) {
                            console.log("Mise à jour réussie !");
                            location.href = "cart";
                        } else {
                            console.error("Erreur lors de la mise à jour de la quantité :" + "L'url pr appeler la servlet" + "ChangeQuantityServlet?productId=" + productId + "&newQuantity=", xhr.statusText);
                        }
                    }
                };
            } else {
                console.error("La nouvelle quantité n'est pas un nombre valide.");
            }
        }
    </script>

</head>
<body>
<jsp:include page="header.jsp" />
<br><br><br><br>

<% Object obj = session.getAttribute("connectedUser");

    if (obj != null) {

        User user = (User) obj;

        int grandTotal = 0;

        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(application);
        CartController cartController = ctx.getBean(CartController.class);

        ArrayList<CartEntity> cartArrayList  =
                new ArrayList<>(cartController.getCartsByUser(user));

        if (cartArrayList.isEmpty()) {
%>
<div class="empty-cart">Your bag is <strong>empty</strong><br><br><a href="/" home></a><br><br><br></div>
<%
} else {
%>

<div class="main-cart">
    <div class="left-cart">
        <div class="left-cart-title"><strong>Bag</strong></div>

        <%
            for (CartEntity cart : cartArrayList) {
                ProductEntity product = cart.getProduct();
                int quantity = cart.getQuantity();
                double total = product.getPrice() * quantity;
                grandTotal += total;
        %>

        <div class="product-cart">
            <div class="product-cart-img"><img src="<%=product.getProductImage()%>"/></div>
            <div class="product-cart-txt">
                <strong><%= product.getLabel() %></strong> <br><br>
                <%=product.getDescription()%> <br><br>
                Quantity : <input type="number" id="quantity_<%=product.getProductId()%>" value="<%= quantity %>" min="0"><br><br>
                <button class="product-cart-button" onclick="updateQuantity(<%=product.getProductId()%>)">Update</button><br><br>
                Remaining stock : <%=product.getStock()%><br><br>


                Seller : <%= product.getCompanyId().getName() %>
            </div>
            <div class="product-cart-price"><strong><%= total %>&euro;</strong>

            <br><br><br><br><img src="${pageContext.request.contextPath}/images/bin.png"/>
            </div>
        </div>
        <%
            }
        %>
    </div>
    <div class="right-cart">
        <div class="right-cart-title"><strong>Summary</strong></div><br><br>
        Subtotal : <%= grandTotal %> &euro;
        <br>
        <form action="checkoutPage" method="post">
            <input class="right-cart-checkout" type='submit' name="Checkout" value="Checkout"/>
        </form>
    </div>

    <%
            }
        }else {
            response.sendRedirect("loginPage");
        }
    %>
</div>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br>

<jsp:include page="../templates/footer.jsp" />

</body>
</html>
