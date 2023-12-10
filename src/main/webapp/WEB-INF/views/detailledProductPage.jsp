<%@ page import="com.jeeSpring.Controller.ProductController" %>
<%@ page import="com.jeeSpring.Model.ProductEntity" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.context.ApplicationContext" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    long productId = 0L;
// Vérification si le paramètre existe
    if (request.getParameter("productId") != null && !request.getParameter("productId").isEmpty()) {
        // Conversion du productId en entier si nécessaire
        productId = Long.parseLong(request.getParameter("productId"));
    }
%>

<head>
    <title>Product Page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cart.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link href="https://fonts.cdnfonts.com/css/trade-gothic-lt-std" rel="stylesheet">
</head>
<body>
<%
    if(request.getParameter("productId") != null && !request.getParameter("productId").isEmpty()){

        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(application);
        ProductController productController = ctx.getBean(ProductController.class);

        if(productController.getProductById(productId)==null){
%><h1> You tried looking for a product that does not exist</h1><%
}else{%>


<br><br><br>
<div class="en-vedette">
    <div class="vedette-produit">
        <div class="vedette-texte">
            <div class="vedette-titre">
                <h1><% ProductEntity productToPrint = productController.getProductById(productId);%></h1>
                <strong><%= productToPrint.getLabel() %></strong> <br><br>
            </div>
            <%= productToPrint.getDescription() %><br><br>
            Stock : <%if(productToPrint.getStock()==0){%>
            Out of stock
            <%
            }else{
            %>
            <%=productToPrint.getStock()%>
            <%}%><br><br>
            <br><br>
            &agrave; partir de <br>
            <div class="vedette-prix"><strong><%= productToPrint.getPrice() %> &euro;</strong></div><br><br><br><br>

            <form action="CartServlet" method="post">
                <input type="hidden" name="productId" value="<%=productToPrint.getProductId()%>" />
                <input type="submit" value="Ajouter au panier" />
            </form>
        </div>

        <a href="detailledProductPage.jsp?productId=<%=productToPrint.getProductId()%>"><img src="<%=productToPrint.getProductImage()%>" alt="Product Image Missing"></a>
    </div>
</div>
<br><br><br>


<%
        }
    }
%>
</body>
</html>