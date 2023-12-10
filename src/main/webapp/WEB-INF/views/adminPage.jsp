<%@ page import="com.jeeSpring.Model.User" %>
<%@ page import="com.jeeSpring.Model.ProductEntity" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>
    <head>
        <meta charset="UTF-8">
        <title>Admin interface</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cart.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
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

        <div class="admin-user">
        <h2>Enter the e-mail of the user you would like to delete</h2>
        <br>
        <form action="AdminServlet" method="post">
            <label for="UserToDelete">
                <input type="text" placeholder="User's e-mail" id="UserToDelete" name="email">
            </label>
            <input type="submit" value="Delete User">
        </form>
            <br>

            <%
                String finalMsgDelete = (String) session.getAttribute("finalMsgDelete");
                if (finalMsgDelete != null) {
            %>
            <p><%= finalMsgDelete %></p>
            <%
                    session.removeAttribute("finalMsgDelete");
                }
            %>


            <br><br>
    <h2>Enter the ID of the user you would like to select</h2>

    <br>
    <form action="AdminServlet" method="post">
        <label for="UserToSelect">
            <input type="number" placeholder="User's id" id="UserToSelect" name="idForSelection">
        </label>
        <input type="submit" value="Select User">
    </form><br>

            <%
                String userSelect = (String) session.getAttribute("finalMsgSelectUser");
                if(userSelect != null){
            %>
            <p><%= userSelect %></p>
            <% session.removeAttribute("finalMsgSelectUser"); %>
            <%
                }
            %>


    <form action="AdminServlet" method="post">
        <label for="UserToPromote">
            <input type="number" placeholder="Promote user by id" id="UserToPromote" name="idToPromote">
        </label>
        <input type="submit" value="Promote">
    </form><br>



            <%
                String userModMsg = (String) session.getAttribute("finalMsgModerator");
                if (userModMsg != null) {
            %>
            <p><%= userModMsg %></p>
            <% session.removeAttribute("finalMsgModerator"); %>
            <%
                }
            %>


<br><br>
    <h3>Obviously only works for moderators</h3><br>
    <form action="AdminServlet" method="post">
        <label for="AddUserToCompany">
            <input type="number" placeholder="User's id" id="AddUserToCompany" name="userForCompany">
        </label>
        <input type="number" placeholder="Company's id" id="CompanyId" name="CompanyId">
        <input type="submit" value="Add to company" name="submitCompany">
    </form><br>


            <%
                String userCpMsg = (String) session.getAttribute("finalMsgCompany");
                if (userCpMsg != null) {
            %>
            <p><%= userCpMsg %></p>
            <% session.removeAttribute("finalMsgCompany"); %>
            <%
                }
            %>

    <form action="AdminServlet" method="post">
        <label for="UserToMakeSeller">
            <input type="number" placeholder="User's id" id="UserToMakeSeller" name="userToMakeSeller">
        </label>
        <input type="submit" value="Make Seller">
    </form><br>

            <%
                String userSellerMsg = (String) session.getAttribute("finalMsgSeller");
                if (userSellerMsg != null) {
            %>
            <p><%= userSellerMsg %></p>
            <% session.removeAttribute("finalMsgSeller"); %>
            <%
                }
            %>


    <form action="AdminServlet" method="post">
        <label for="UserToRemoveSeller">
            <input type="number" placeholder="User's id" id="UserToRemoveSeller" name="userToRemoveSeller">
        </label>
        <input type="submit" value="Remove Seller">
    </form><br>

            <%
                String userRemoveSellerMsg = (String) session.getAttribute("finalMsgRemoveSeller");
                if (userRemoveSellerMsg != null) {
            %>
            <p><%= userRemoveSellerMsg %></p>
            <% session.removeAttribute("finalMsgRemoveSeller"); %>
            <%
                }
            %>


        </div>
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

        <%
            String searchProductsMsg = (String) session.getAttribute("finalMsgSearchProducts");
            if (searchProductsMsg != null) {
        %>
        <p><%= searchProductsMsg %></p>
        <% session.removeAttribute("finalMsgSearchProducts"); %>


        <%
            List<ProductEntity> productList = (List<ProductEntity>) session.getAttribute("ProductList");
            if (productList != null && !productList.isEmpty()) {
        %>
        <h3>Search Results:</h3>
        <table>
            <tr>
                <th>ID</th>
                <th>Label</th>
                <th>Description</th>
                <th>Price</th>
            </tr>
            <%
                for (ProductEntity product : productList) {
            %>
            <tr>
                <td><%= product.getProductId() %></td>
                <td><%= product.getLabel() %></td>
                <td><%= product.getDescription() %></td>
                <td><%= product.getPrice() %></td>
            </tr>
            <%
                }
            %>
        </table>
        <%
            session.removeAttribute("ProductList");
        } else if (productList != null) {
        %><p>No products found.</p><%
            }
        }
    %>


    <form action="AdminServlet" method="post">
        <label for="ProductToDelete">
            <input type="number" placeholder="Delete product by id" id="ProductToDelete" name="pToDelete">
        </label>
        <input type="submit" value="Delete Product">
    </form><br>

        <% String finalMsgDeleteP = (String) session.getAttribute("finalMsgDeleteP");
            if (finalMsgDeleteP != null) {
        %>
        <p><%= finalMsgDeleteP %></p>
        <% session.removeAttribute("finalMsgDeleteP");
        }
        %>

    </div>
    <%}
    }else{
    %>

    </div>
    <h2>You're not connected, please connect as an admin to see that page.</h2>
    <a href="index.jsp"> Back to index </a><br><br><br><br><br><br><br><br>
            <%}%>
    <br><br><br><br>

    </div>

    </body>

    <jsp:include page="../templates/footer.jsp" />
    </html>
