<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    </head>
    <body>
        <div id="login-box" class="col-md-12">
            <form id="login-form" action="MainController" method="POST">
                <h3 class="text-center text-info">Sign In</h3>
                <div class="form-group">
                    <label for="email" class="text-info">Username:</label><br>
                    <input type="email" name="txtEmail" id="email" class="form-control" required="true">          
                    <font color="red">${requestScope.ERROR}</font>
                </div>
                <div class="form-group">
                    <label for="password" class="text-info">Password:</label><br>
                    <input type="password" name="txtPassword" id="password" class="form-control" required="true">
                </div>
                <div class="g-recaptcha" data-sitekey="6LcVI9waAAAAAH7rL61_IZhpKZCBYMErcaGa8csv"></div>
                <div class="form-group">
                    <button type="submit" name="action" class="btn btn-info btn-md" value="Login">Login</button>

                    <c:url var="loginLink" value="https://accounts.google.com/o/oauth2/auth">   
                        <c:param name="scope" value="email profile"></c:param>
                        <c:param name="redirect_uri" value="http://localhost:8080/ResouceSharing_L.P0016/GoogleLogin"></c:param> 
                        <c:param name="response_type" value="code"></c:param>
                        <c:param name="client_id" value="166960664084-gn79cicdiv2e18bh7q83905nqon4o3a5.apps.googleusercontent.com"></c:param>
                        <c:param name="approval_prompt" value="force"></c:param>
                    </c:url>
                    <a href="${loginLink}"><font color="green">Or Login With Google</font></a> 
                </div>
                <a href="register.jsp"><font color="green">Register</font></a> 
            </form>
        </div>
    </body>
</html>
