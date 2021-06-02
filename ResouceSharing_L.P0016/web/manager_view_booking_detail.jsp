
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Booking Detail Page</title>
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
            a {
                margin-left: 15
            }
            .buttonA {
                height: 30px;
                width: 80px;
                background-color: #4CAF50;
                border: none;
                color: white;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                cursor: pointer;
            }
            .buttonR {
                height: 30px;
                width: 80px;
                background-color: #f44336;
                border: none;
                color: white;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                cursor: pointer;
            }
            textarea {
                width: 20%;
                height: 50%;
            }
        </style>
    </head>
    <body>
        <h1>Booking Detail</h1>
        <a href="manager_view_booking.jsp">Back to home page</a>
        <br>
        <br>
        <table>
            <tr>
                <th>Resource Name</th>
                <th>Request User</th>
                <th>Booking Time</th>
                <th>Return Time</th>
                <th>Status</th>
                <th>Request Time</th>
                <th>Quantity</th>  

            </tr>
            <tr>                 
                <td>${sessionScope.BOOKINGDETAIL.resourceName}</td>
                <td>${sessionScope.BOOKINGDETAIL.email}</td>
                <td><fmt:formatDate value="${sessionScope.BOOKINGDETAIL.bookingTimestamp}" pattern="MM/dd/yyyy HH:mm"/></td>
                <td><fmt:formatDate value="${sessionScope.BOOKINGDETAIL.returnTimestamp}" pattern="MM/dd/yyyy HH:mm"/></td> 
                <td>${sessionScope.BOOKINGDETAIL.bookingStatusName}</td> 
                <td><fmt:formatDate value="${sessionScope.BOOKINGDETAIL.insTimestamp}" pattern="MM/dd/yyyy HH:mm"/></td>
                <td>${sessionScope.BOOKINGDETAIL.quantity}</td>
                <td>                 
                </td>
            </tr>

        </table>
        <h3><font color="red">${requestScope.ERROR}</font></h3>
        <br>
        <br>
        <c:if test="${sessionScope.BOOKINGDETAIL.bookingStatusId eq 1}">
            <form action="MainController" method="POST">
                <input type="radio" name="rdConfirm" value="2" required="true">
                <label for="age1">Approve</label><br>
                <input type="radio" name="rdConfirm" value="3" required="true">
                <label for="age1">Reject</label><br>
                <br>
                <textarea  name="responseMessage" form="usrform" placeholder="Enter message here..." style="resize: none" required="true"></textarea>
                <br>
                <br>
                <input type="submit" name="action" value="Confirm Update">
            </form>
        </c:if>
    </body>
</html>
