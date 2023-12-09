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
</head>
<body>
<%
    if(request.getParameter("productId") != null && !request.getParameter("productId").isEmpty()){

        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(application);
        ProductController productController = ctx.getBean(ProductController.class);

        if(productController.getProductById(productId)==null){
%><h1> You tried looking for a product that does not exist</h1><%
}else{%>
<h1><% ProductEntity productToPrint = productController.getProductById(productId);%></h1>
<p><span class="info-label">Name :</span> <%= productToPrint.getLabel() %></p>
<p><span class="info-label">Price :</span> <%= productToPrint.getPrice() %></p>
<p><span class="info-label">Stock :</span> <%= productToPrint.getStock() %></p>
<p><span class="info-label">Description :</span> <%= productToPrint.getDescription() %></p>
<p><span class="info-label"></span><img src="<%=productToPrint.getProductImage()%>" alt="Product Image"></p>
<form action="CartServlet" method="post">
    <input type="hidden" name="productId" value="<%=productToPrint.getProductId()%>" />
    <input type="submit" value="Ajouter au panier" />
</form>
<%
        }
    }
%>
</body>
</html>