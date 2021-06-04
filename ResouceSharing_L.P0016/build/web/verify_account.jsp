<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verify Account Page</title>
    </head>
    <body>
        <div id="login-box" class="col-md-12">
            <form id="login-form" action="MainController" method="POST">
                <h3 class="text-center text-info">You need to activate your account by verify code !</h3>
                <h3 class="text-center text-info">We have sent the verify code to your email. Check your email to get it.</h3>
                <div class="form-group">
                    <label for="verifyCode" class="text-info">Verify Code</label><br>
                    <input type="text" name="txtVerifyCode" class="form-control" required="true" placeholder="Verify Code">
                    <font color="red">${requestScope.VERIFICATION_ERROR}</font>
                </div>
                <div class="form-group">
                    <button type="submit" name="action" class="btn btn-info btn-md" value="CheckVerifyCode">Check Verify code</button>             
                </div>
            </form>
        </div>
    </body>
</html>
