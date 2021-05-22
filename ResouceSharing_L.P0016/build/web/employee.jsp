<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Page</title>
    </head>
    <body>
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
        <p></p>
        <!--Search form-->
        <form action="MainController" class="search-form">
            <div class="form-group">
                <span class="icon icon-search"></span>
                <input type="text" class="form-control" placeholder="Type a keyword" name="txtSearch" value="${sessionScope.pattern}">
                <c:if test="${requestScope.LIST_CATE != null}">
                    <c:if test="${requestScope.LIST_CATE.size > 0}">
                        <select name="cbCategory" id="cars">
                            <c:forEach var="category" items="${requestScope.LIST_CATE}">
                                <option value=${category.categoryId}>${category.categoryName}</option>
                            </c:forEach>
                        </select>
                    </c:if>
                </c:if>


            </div>
            <div class="form-group" >
                <button type="submit" class="btn btn-primary" style="margin-top: 1rem" name="action" value="Search">Search</button>
            </div>
        </form>
    </body>
</html>
