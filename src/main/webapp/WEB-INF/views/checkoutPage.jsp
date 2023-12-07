<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Checkout</title>
    <link rel="stylesheet" href="css/payment.css">
</head>
<body>
<% if(request.getParameter("Checkout")!=null){%>
    <form action="CheckoutServlet" method="post" class="checkout-form">
        <input type="text" placeholder="Card Owner" class="input-field"><br/>
        <input type="number" name="CardNumber" placeholder="Card Number" class="input-field"><br/>
        expiration mm
        <select name="month" id="month" class="select-field">
            <option value="month" selected disabled>month</option>
            <option value="01">01</option>
            <option value="02">02</option>
            <option value="03">03</option>
            <option value="04">04</option>
            <option value="05">05</option>
            <option value="06">06</option>
            <option value="07">07</option>
            <option value="08">08</option>
            <option value="09">09</option>
            <option value="10">10</option>
            <option value="11">11</option>
            <option value="12">12</option>
        </select>
        expiration yy
        <select name="year" id="year" class="select-field">
            <option value="year" selected disabled>year</option>
                <option value="23">2023</option>
                <option value="24">2024</option>
                <option value="25">2025</option>
                <option value="26">2026</option>
                <option value="27">2027</option>
                <option value="28">2028</option>
                <option value="29">2029</option>
                <option value="30">2030</option>
        </select><br/>
        <input type="number" placeholder="CVV" name ="cvv" class="input-field cvv-field"><br/>
        <input type="submit" value="Validate Informations" name="Payment"  class="submit-button">
    </form>
<%}else{
    %>
    <h2 class="error-message">You are not coming from a cart, please log in and use your cart button to proceed to checkout.</h2>
    <a href="/" >Home</a>
<%}%>
</body>
</html>
