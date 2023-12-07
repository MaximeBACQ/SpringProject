<%@ page import="com.jeeSpring.Model.ProductEntity" %>
<%@ page import="jakarta.persistence.TypedQuery" %>
<%@ page import="com.jeeSpring.Repository.JPAUtil" %>
<%@ page import="java.util.*" %>
<%@ page import="com.jeeSpring.Repository.ProductRepository" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Produit</title>
    <link rel="stylesheet" type="text/css" href="css/cart.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/style.css">
    <link href="https://fonts.cdnfonts.com/css/trade-gothic-lt-std" rel="stylesheet">
</head>
<body>



<%@ include file="header.jsp" %>
<br><br><br><br>

<div class="search-title"><strong>Results :</strong></div><br><br>

    <%
        ProductDAO productDAO = new ProductDAO();
        String labels = request.getParameter("products");
        ArrayList<ProductEntity> productList = new ArrayList<>(productDAO.searchProductsByLabelAndDescription(labels));

         for(ProductEntity p : productList){
         %>




        <div class="en-vedette">
            <div class="vedette-produit">
                <div class="vedette-texte">
                    <div class="vedette-titre">
                        <strong><%=p.getLabel()%></strong> <br><br>
                    </div>
                    <%=p.getDescription()%><br><br>
                    Corps en aulne<br><br>
                    Stock : <%=p.getStock()%> <br><br>
                    <br><br>
                    &agrave; partir de <br>
                    <div class="vedette-prix"><strong><%=p.getPrice()%> &euro;</strong></div><br><br><br><br>

                    <form action="CartServlet" method="post">
                        <input type="hidden" name="productId" value="<%=p.getProductId()%>" />
                        <input type="submit" value="Ajouter au panier" />
                    </form>
                </div>

                <a href=""><img src="<%=p.getProductImage()%>" alt="Product Image Missing"></a>
            </div>
        </div>
        <br><br><br>
        <%
         }
%>

<%--            <%=productList.toString() %>--%>

<%--    TypedQuery<ProductEntity> query = JPAUtil.getEntityManager().createQuery("SELECT u FROM SiteUser u WHERE label LIKE %%")--%>
<%--<%--/*        if (product != null) {&ndash;%&gt;
&lt;%&ndash;    %>&ndash;%&gt;
&lt;%&ndash;    <div class="product-details">&ndash;%&gt;
&lt;%&ndash;        <p>Nom du Produit : <%= product.getLabel() %></p>&ndash;%&gt;
&lt;%&ndash;        <p>Prix : <%= product.getPrice() %> €</p>&ndash;%&gt;
&lt;%&ndash;        <p>Quantité en Stock : <%= product.getStock() %></p>&ndash;%&gt;
&lt;%&ndash;        <p>Compagnie : <%= product.getCompanyId().getName() %></p>&ndash;%&gt;
&lt;%&ndash;    </div>&ndash;%&gt;
&lt;%&ndash;    &lt;%&ndash;dash;%&gt;
&lt;%&ndash;    } else {&ndash;%&gt;
&lt;%&ndash;    %>&ndash;%&gt;
&lt;%&ndash;    <p>Le produit n'est pas disponible.</p>&ndash;%&gt;
&lt;%&ndash;    &lt;%&ndash;dash;%&gt;
&lt;%&ndash;        }&ndash;%&gt;
&lt;%&ndash;    %>&ndash;%&gt;
&lt;%&ndash;</div>&ndash;%&gt;
&lt;%&ndash;<br><br><br><br><br><br><br><br><br><br><br><br><br><br>*/--%>
<%@ include file="../html/footer.html" %>

</body>
</html>