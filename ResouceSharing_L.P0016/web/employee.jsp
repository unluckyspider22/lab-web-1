<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Page</title>
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
        </style>
    </head>
    <body>
        <c:if test="${sessionScope.USER.roleName ne 'Employee'}"><c:redirect url="login.jsp"></c:redirect></c:if>
            <!--Welcome user-->
        <c:if test="${sessionScope.USER != null}">
            <c:if test="${not empty sessionScope.USER.name}">
                <h3>Welcome ${sessionScope.USER.name}!</h3>
            </c:if>
        </c:if>
        <c:url var="Logout" value="MainController">
            <c:param name="action" value="Logout"></c:param>
        </c:url>
        <!--Logout-->
        <a href="${Logout}">Logout</a>
        <br>
        <!--Search form-->
        <form action="MainController" class="search-form">
            <div class="form-group">
                <span class="icon icon-search"></span>
                 <input type="text" class="form-control" placeholder="Type a resource name" name="txtSearch" value="${sessionScope.PATTERN}">
                <c:if test="${sessionScope.LIST_CATE != null}">
                    <c:if test="${sessionScope.LIST_CATE.size() > 0}">
                        <select name="CBCATEGORY">
                            <option>All</option>
                            <c:forEach var="category" items="${sessionScope.LIST_CATE}">
                                <option value=${category.categoryId}>${category.categoryName}</option>
                            </c:forEach>
                        </select>
                    </c:if>
                </c:if>
            </div>      

            <div class="form-group">
                <button type="submit" class="btn btn-primary" style="margin-top: 1rem" name="action" value="Search">Search</button>
            </div>
            <div class="col-xl-8 py-5 px-md-5">
                <div class="row pt-md-4">
                    <div>
                        <h3 class="mb-2">${requestScope.SEARCH_MESSAGE}</h3>
                    </div>

                    <c:if test="${requestScope.LIST_RESOURCE != null}">
                        <c:if test="${not empty requestScope.LIST_RESOURCE}">
                            <c:if test="${requestScope.LIST_RESOURCE.size() > 0}">
                                <table>
                                    <tr>
                                        <th>No</th>
                                        <th>Resource Name</th>
                                        <th>Category Name</th>
                                        <th>Color</th>
                                        <th>Available Quantity</th>
                                        <th>Booking status</th>
                                    </tr>
                                    <c:forEach var="resource" items="${requestScope.LIST_RESOURCE}" varStatus="counter">
                                        <c:url var="resourceDetail" value="MainController">
                                            <c:param name="action" value="ResourceDetail"/>
                                            <c:param name="resourceId" value="${resource.resourceId}"/>
                                        </c:url>
                                        <tr>
                                            <td>${counter.count}</td>
                                            <td>${resource.resourceName}</td>
                                            <td>${resource.categoryName}</td>
                                            <td>${resource.color}</td>
                                            <td>${resource.availableQuantity}</td>
                                            <td>                                            
                                                <a href="${resourceDetail}" >View Detail</a>
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

                <c:url var="pageFrist" value="PagingController">
                    <c:param name="page" value="1"/>
                    <c:param name="txtSearch" value="${sessionScope.PATTERN}"/>
                    <c:param name="CBCATEGORY" value="${sessionScope.CBCATEGORY}"/>
                </c:url>
                <a href="${pageFrist}">&lt;</a>

                <c:forEach var="page" varStatus="counter" items="${requestScope.NB_PAGE}">
                    <c:url var="pageNumber" value="PagingController">
                        <c:param name="page" value="${page}"/>
                        <c:param name="txtSearch" value="${sessionScope.PATTERN}"/>
                        <c:param name="CBCATEGORY" value="${sessionScope.CBCATEGORY}"/>
                    </c:url>
                    <a href="${pageNumber}">${counter.count}</a>
                </c:forEach>

                <c:url var="pageLast" value="PagingController">
                    <c:param name="page" value="${requestScope.NB_PAGE.size()}"/>
                    <c:param name="txtSearch" value="${sessionScope.PATTERN}"/>
                    <c:param name="CBCATEGORY" value="${sessionScope.CBCATEGORY}"/>
                </c:url>
                <a href="${pageLast}">&gt;</a>
            </c:if>
        </ul>
    </body>
</html>
