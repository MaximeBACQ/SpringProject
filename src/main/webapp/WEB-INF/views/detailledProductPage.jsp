<%@ page import="com.jeeSpring.Model.ProductEntity" %>

<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="com.jeeSpring.Controller.ProductController" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    long productId = 0L;
// Vérification si le paramètre existe
    if (request.getParameter("productId") != null && !request.getParameter("productId").isEmpty()) {
        // Conversion du productId en entier si nécessaire
        productId = Long.parseLong(request.getParameter("productId"));

        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(application);
        ProductController productController = ctx.getBean(ProductController.class);

        ProductEntity productSelected = productController.getProductById(productId);
    }else{%>
<title>Product not specified</title>
<h1> You tried to access this url without precising an id, come back by clicking on a product's image to make this page
    work </h1>
<%
    }

%>

<head>
    <%  ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(application);
        ProductController productController = ctx.getBean(ProductController.class); %>

    <title><% productController.getProductById(productId);%></title>
</head>
<body>

</body>
</html>