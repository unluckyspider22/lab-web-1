

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Leader Page</title>
    </head>
    <body>
    <c:if test="${sessionScope.USER.roleName ne 'Leader'}"><jsp:forward page="login.jsp"/></c:if>
    <h1>Hello World!</h1>
</body>
</html>
