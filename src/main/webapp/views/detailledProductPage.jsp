<%@ page import="com.jeeSpring.Repository.ProductRepository" %>
<%@ page import="com.jeeSpring.Model.ProductEntity" %><%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 27/11/2023
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    int productId = 0;
// Vérification si le paramètre existe
    if (request.getParameter("productId") != null && !request.getParameter("productId").isEmpty()) {
        // Conversion du productId en entier si nécessaire
        productId = Integer.parseInt(request.getParameter("productId"));
        ProductDAO productDAO = new ProductDAO();
        ProductEntity productSelected = productDAO.findProductById(productId);
    }else{%>
<title>Product not specified</title>
<h1> You tried to access this url without precising an id, come back by clicking on a product's image to make this page
    work </h1>
<%
    }

%>

<head>
    <% ProductDAO productDAO = new ProductDAO();%>
    <title><% productDAO.findProductById(productId).getProduct%></title>
</head>
<body>

</body>
</html>