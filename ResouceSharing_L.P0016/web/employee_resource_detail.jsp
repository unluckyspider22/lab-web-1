
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resource Detail Page</title>
    </head>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 70%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
        textarea {
            width: 20%;
            height: 50%;
        }
    </style>
    <body>
        <c:if test="${sessionScope.USER.roleName ne 'Employee'}"><jsp:forward page="login.jsp"/></c:if>
            <h1>Resource Detail</h1>
            <table>
                <tr>
                    <th>Resource Name</th>
                    <th>Category Name</th>
                    <th>Color</th>
                    <th>Available Quantity</th>
                </tr>
            <c:url var="bookResource" value="MainController">
                <c:param name="action" value="Book"/>
                <c:param name="resourceId" value="${sessionScope.RESOURCE_DETAIL.resourceId}"/>
            </c:url>
            <tr>
                <td>${sessionScope.RESOURCE_DETAIL.resourceName}</td>
                <td>${sessionScope.RESOURCE_DETAIL.categoryName}</td>
                <td>${sessionScope.RESOURCE_DETAIL.color}</td>
                <td>${sessionScope.RESOURCE_DETAIL.availableQuantity}</td>               
            </tr>

        </table>
        <h1>Input to booking</h1>
        <h2><font color="red">${requestScope.FAIL_WARNING}</font></h2>
        <h3><font color="red">${requestScope.MESSAGE}</font></h3>
        <h3><font color="blue">${requestScope.SUCCESS_MESS}</font></h3>
        <h3><font color="red">${requestScope.FAIL_MESS}</font></h3>

        <form action="MainController" id="usrform">
            <label for="quantity">Quantity </label>
            <input type="number" id="quantity" name="txtQuantity" min="1" max="1000" pattern="^[0-9]" required="true" value=${sessionScope.QUANTITY}>
            <br>
            <br>
            <label for="bookingDate">Booking Time </label>
            <input type="datetime-local"  name="bookingDate" min="${sessionScope.NOW}" required="true" value=${sessionScope.BOOKINGDATE}>
            <br>
            <br>
            <label for="returnDate">Return Time </label>
            <input type="datetime-local" name="returnDate" min="${sessionScope.NOW}" required="true" value=${sessionScope.RETURNDATE}>
            <br>
            <br>
            <label for="requestMessage">Request Message </label>
            <br>
            <br>
            <textarea name="requestMessage" form="usrform" placeholder="Enter message here..." style="resize: none" required="true">${sessionScope.REQUESTMESSAGE}</textarea>
            <br>
            <br>
            <input type="submit" name="action" value="Book">
        </form>
        <br>
        <a href="employee.jsp">Back to Home Page</a>
    </body>
</html>
