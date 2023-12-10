<%@ page import="com.jeeSpring.Model.ProductEntity" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="java.util.*" %>
<%@ page import="com.jeeSpring.Controller.ProductController" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Produit</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cart.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link href="https://fonts.cdnfonts.com/css/trade-gothic-lt-std" rel="stylesheet">
</head>
<body>



<jsp:include page="header.jsp" />
<br><br><br><br>

<div class="search-title"><strong>Results :</strong></div><br><br>

    <%

        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(application);
        ProductController productController = ctx.getBean(ProductController.class);

        String labels = request.getParameter("products");
        ArrayList<ProductEntity> productList = new ArrayList<>(productController.searchProductsByLabelAndDescription(labels));

         for(ProductEntity p : productList){
         %>

<div class="en-vedette">
    <div class="vedette-produit">
        <div class="vedette-texte">
            <div class="vedette-titre">
                <strong><%=p.getLabel()%></strong> <br><br>
            </div>
            <%=p.getDescription()%><br><br>
            Stock : <%if(p.getStock()==0){%>
            Out of stock
            <%
            }else{
            %>
            <%=p.getStock()%>
            <%}%><br><br>
            <br><br>
            &agrave; partir de <br>
            <div class="vedette-prix"><strong><%=p.getPrice()%> &euro;</strong></div><br><br><br><br>

            <form action="CartServlet" method="post">
                <input type="hidden" name="productId" value="<%=p.getProductId()%>" />
                <input type="submit" value="Ajouter au panier" />
            </form>
        </div>

        <a href="detailledProductPage?productId=<%=p.getProductId()%>"><img src="<%=p.getProductImage()%>" alt="Product Image Missing"></a>
    </div>
</div>
<br><br><br>
<%
    }
%>

<jsp:include page="../templates/footer.jsp" />

</body>
</html>
