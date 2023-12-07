<%@ page import="com.jeeSpring.Model.User" %>
<%@ page import="com.jeeSpring.Model.ProductEntity" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>
    <head>
        <meta charset="UTF-8">
        <title>Admin interface</title>
        <link rel="stylesheet" type="text/css" href="css/cart.css">
        <link rel="stylesheet" href="css/header.css">
        <link rel="stylesheet" href="css/footer.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/admin.css">
        <link href="https://fonts.cdnfonts.com/css/trade-gothic-lt-std" rel="stylesheet">
        <%
            User deleted = null;
            User selected = null;
        %>
        <c:if test="${sessionScope.selected != null}">
            <%  selected = (User) session.getAttribute("selected");
                session.removeAttribute("selected");
            %>
        </c:if>

    </head>
    <body>
    <jsp:include page="header.jsp"/>
    <br><br><br>
    <%
        // Récupération de la variable de session
        User adminUser = (User) session.getAttribute("connectedUser");

        // Vérification si la variable de session est définie
        if( adminUser != null){
            if (adminUser.getIsAdmin()) {
    %>
    <div class="admin-main">
    <h1>Admin Interface, users/moderators actions</h1>
    <br><br><br>
        <% String msgDelete = (String)request.getAttribute("finalMsgDelete");
                if(msgDelete!=null){
                %>
                <%=msgDelete%>
    <%}%>

        <div class="admin-user">
        <h2>Enter the e-mail of the user you would like to delete</h2>
        <br>
        <form action="AdminServlet" method="post">
            <label for="UserToDelete">
                <input type="text" placeholder="User's e-mail" id="UserToDelete" name="email">
            </label>
            <input type="submit" value="Delete User">
        </form>
<br><br>
    <h2>Enter the ID of the user you would like to select</h2>

    <% String userSelect = (String) request.getAttribute("finalMsgSelectUser");
       if(userSelect!=null){
    %>
    <%=userSelect%>
    <%
    }
    %>
    <br>
    <form action="AdminServlet" method="post">
        <label for="UserToSelect">
            <input type="number" placeholder="User's id" id="UserToSelect" name="idForSelection">
        </label>
        <input type="submit" value="Select User">
    </form>
    <%
        String userModMsg = (String) request.getAttribute("finalMsgModerator");
        if(userModMsg != null)
        {%>
    <p><%= userModMsg %></p>
    <%}
    %>
    <form action="AdminServlet" method="post">
        <label for="UserToPromote">
            <input type="number" placeholder="Promote user by id" id="UserToPromote" name="idToPromote">
        </label>
        <input type="submit" value="Promote">
    </form>

    <% String userCpMsg = (String) request.getAttribute("finalMsgCompany");
        if (userCpMsg != null) {
    %>
    <p><%= userCpMsg %></p>
    <%}else{
    }%>
<br><br>
    <h3>Obviously only works for moderators</h3><br>
    <form action="AdminServlet" method="post">
        <label for="AddUserToCompany">
            <input type="number" placeholder="User's id" id="AddUserToCompany" name="userForCompany">
        </label>
        <input type="number" placeholder="Company's id" id="CompanyId" name="CompanyId">
        <input type="submit" value="Add to company" name="submitCompany">
    </form>

    <% String userSellerMsg = (String) request.getAttribute("finalMsgSeller");
        if (userSellerMsg != null) {
    %>
    <p><%= userSellerMsg %></p>
    <%}else{
    }%>
    <form action="AdminServlet" method="post">
        <label for="UserToMakeSeller">
            <input type="number" placeholder="User's id" id="UserToMakeSeller" name="userToMakeSeller">
        </label>
        <input type="submit" value="Make Seller">
    </form>
    <% String userIdMessageSeller = (String) request.getAttribute("finalMsgSeller");
        if (userIdMessageSeller != null) {
    %>
    <p><%= userIdMessageSeller %></p>
    <%}else {
    }%>

    <form action="AdminServlet" method="post">
        <label for="UserToRemoveSeller">
            <input type="number" placeholder="User's id" id="UserToRemoveSeller" name="userToRemoveSeller">
        </label>
        <input type="submit" value="Remove Seller">
    </form></div>
    <br><br><br><br>

    <div class="admin-product">
    <h1>Products actions</h1><br>
    <h2> To add a product, you must head to the moderator page where sellers can sell products.</h2><br>

    <form action="AdminServlet" method="post">
        <label for="ProductToSearch">
            <input type="text" placeholder="List Products by Name" id="ProductToSearch" name="pToSearch">
        </label>
        <input type="submit" value="Search Products">
    </form>
    <% String userIdMessageSearchProducts = (String) request.getAttribute("finalMsgSearchProducts");
        if (userIdMessageSearchProducts != null) {
    %>
    <p><%= userIdMessageSearchProducts %></p>
    <%}else{
    }
        if(request.getAttribute("ProductList")!=null){
        StringBuilder url = new StringBuilder("productPage?products=");
        for(ProductEntity p : (List<ProductEntity>) request.getAttribute("ProductList")) {
            url.append(p.getLabel());
        }%>
    <h3>Your product list: <a href="<%=url%>">link</a></h3><%
    }else{

    }%>

    <form action="AdminServlet" method="post">
        <label for="ProductToDelete">
            <input type="number" placeholder="Delete product by id" id="ProductToDelete" name="pToDelete">
        </label>
        <input type="submit" value="Delete Product">
    </form></div>
    <%
        }else{
    %>
    <h2>You're not an admin, you cannot access that page.</h2>
    <a href="index.jsp"> Back to index </a>
    <%}
    }else{
            %>
    <h2>You're not connected, please connect as an admin to see that page.</h2>
    <a href="index.jsp"> Back to index </a>
            <%}%>
    <br><br><br><br>

    </div>

    </body>
    <%@ include file="../html/footer.html" %>
    </html>
