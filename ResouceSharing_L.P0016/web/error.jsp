<%-- 
    Document   : error
    Created on : May 19, 2021, 11:33:08 PM
    Author     : buido
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
        <style>
             body{
                font-family: 'Pacifico', cursive;
            }
        </style>
    </head>
    <body>
        <h1>Error page</h1>
        <%
            String error = (String) request.getAttribute("ERROR");
        %>
        <font color="red"><%=error%></font>
    </body>
</html>
