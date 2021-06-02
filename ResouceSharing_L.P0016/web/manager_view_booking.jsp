<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Booking Page</title>
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
    </style>
    <script>
        let valChk = false;
        function onClickHandler() {
            this.valChk = !this.valChk;
            console.log(this.valChk)
            var fromDate = document.getElementById("fromDate");
            var toDate = document.getElementById("toDate");
            if (!this.valChk) {
                document.getElementById("search_date").style = "display:none;"
                fromDate.required = false;
                toDate.required = false;
            } else {
                document.getElementById("search_date").style = "display:block;"
                fromDate.required = true;
                toDate.required = true;
            }
        }
    </script>
    <body>
        <c:if test="${sessionScope.USER.roleName ne 'Manager'}"><jsp:forward page="login.jsp"/></c:if>
            <h1>Booking Management</h1>

            <!--Search form-->
            <a href="manager.jsp">Back to home page</a>
            <br>
            <br>
            <form action="MainController" class="search-form">
                <div class="form-group">
                    <span class="icon icon-search"></span>
                    <input type="text" class="form-control" placeholder="Type a resource name" name="txtSearch" value="${sessionScope.PATTERN}">
                <c:if test="${sessionScope.LIST_BOOKINGSTATUS != null}">
                    <c:if test="${sessionScope.LIST_BOOKINGSTATUS.size() > 0}">
                        <select name="CBSTATUS">
                            <option>All</option>
                            <c:forEach var="bookingStatus" items="${sessionScope.LIST_BOOKINGSTATUS}">
                                <option value=${bookingStatus.bookingStatusId}>${bookingStatus.name}</option>
                            </c:forEach>
                        </select>
                    </c:if>
                </c:if>
                <!--checkbox-->
                <label class="switch">
                    <input  type="checkbox" onChange="onClickHandler()" name="chkDate">
                    <!--<input  onclick="onClickHandler()">-->                   

                    <span class="slider">Turn on to search by booking date !</span>
                </label>
                <br>
                <br>

                <div id="search_date" style="display:none">
                    <label for="bookingDate">From Date </label>
                    <input id="fromDate" type="datetime-local" name="fromDate"  >
                    <br>
                    <br>
                    <label for="returnDate">To Date</label>
                    <input id="toDate" type="datetime-local" name="toDate" >
                </div>

            </div>      

            <div class="form-group">
                <button type="submit" class="btn btn-primary" style="margin-top: 1rem" name="action" value="SearchBooking">Search</button>

                <h3><font color="blue">${requestScope.RESULTUPDATESUCC}</font></h3>
                <h3><font color="red">${requestScope.RESULTUPDATEFAI}</font></h3>
            </div>
            <div class="col-xl-8 py-5 px-md-5">
                <div class="row pt-md-4">
                    <div>
                        <h3 class="mb-2">${requestScope.SEARCH_MESSAGE}</h3>
                    </div>

                    <c:if test="${requestScope.LIST_BOOKING != null}">
                        <c:if test="${not empty requestScope.LIST_BOOKING}">
                            <c:if test="${requestScope.LIST_BOOKING.size() > 0}">
                                <table>
                                    <tr>
                                        <th>No</th>
                                        <th>Resource Name</th>
                                        <th>Request User</th>
                                        <th>Booking Time</th>
                                        <th>Return Time</th>
                                        <th>Status</th>
                                        <th>Request Time</th>
                                        <th>Quantity</th>  

                                    </tr>
                                    <c:forEach var="booking" items="${requestScope.LIST_BOOKING}" varStatus="counter">
                                        <tr>     
                                            <c:url var="viewDetail" value="ViewBookingDetailController">
                                                <c:param name="bookingId" value="${booking.bookingId}"></c:param>
                                            </c:url>
                                            <td>${counter.count}</td>
                                            <td>${booking.resourceName}</td>
                                            <td>${booking.email}</td>
                                            <td><fmt:formatDate value="${booking.bookingTimestamp}" pattern="MM/dd/yyyy HH:mm"/></td>
                                            <td><fmt:formatDate value="${booking.returnTimestamp}" pattern="MM/dd/yyyy HH:mm"/></td> 
                                            <td>${booking.bookingStatusName}</td> 
                                            <td><fmt:formatDate value="${booking.insTimestamp}" pattern="MM/dd/yyyy HH:mm"/></td>
                                            <td>${booking.quantity}</td>
                                            <td>
                                                <a href="${viewDetail}" >View Detail</a>
                                                <%--<c:if test="${booking.bookingStatusId eq 1}">--%>
                                                <!--<form action="MainController" method="POST">-->
                                                <!--<button class="buttonA" name="action" value="2">Approve</button>-->
                                                <!--<button class="buttonR" name="action" value="3">Reject</button>-->
                                                <!--<textarea  name="requestMessage" form="usrform" placeholder="Enter message here..." style="resize: none" required="true">${sessionScope.REQUESTMESSAGE}</textarea>-->
                                                <!--</form>-->
                                                <%--</c:if>--%>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </c:if>
                        </c:if>
                    </c:if>
                </div>
            </div> 
        </form>
        <!-- END--> 
        <ul>
            <c:if test="${requestScope.NB_PAGE.size() > 1}">

                <c:url var="pageFrist" value="PagingBookingController">
                    <c:param name="page" value="1"/>
                    <c:param name="txtSearch" value="${sessionScope.PATTERN}"/>
                    <c:param name="CBSTATUS" value="${sessionScope.CBSTATUS}"/>
                </c:url>
                <a href="${pageFrist}">&lt;</a>

                <c:forEach var="page" varStatus="counter" items="${requestScope.NB_PAGE}">
                    <c:url var="pageNumber" value="PagingBookingController">
                        <c:param name="page" value="${page}"/>
                        <c:param name="txtSearch" value="${sessionScope.PATTERN}"/>
                        <c:param name="CBSTATUS" value="${sessionScope.CBSTATUS}"/>
                    </c:url>
                    <a href="${pageNumber}">${counter.count}</a>
                </c:forEach>

                <c:url var="pageLast" value="PagingBookingController">
                    <c:param name="page" value="${requestScope.NB_PAGE.size()}"/>
                    <c:param name="txtSearch" value="${sessionScope.PATTERN}"/>
                    <c:param name="CBSTATUS" value="${sessionScope.CBSTATUS}"/>
                </c:url>
                <a href="${pageLast}">&gt;</a>
            </c:if>
        </ul>
    </body>
</html>
