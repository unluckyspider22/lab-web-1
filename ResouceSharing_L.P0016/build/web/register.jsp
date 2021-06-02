<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <div class="container">
            <div class="row pt-5">
                <aside class="col-sm-4">
                </aside>
                <aside class="col-sm-4">
                    <div class="card">
                        <article class="card-body">
                            <a href="login.jsp" class="float-right btn btn-outline-primary">Login</a>
                            <h4 class="card-title mb-4 mt-1">Sign Up</h4>
                            <form action="MainController" method="POST">
                                <div class="form-group">
                                    <label>Your name</label>
                                    <input name="txtName" class="form-control" placeholder="Name" type="text" required="true">
                                </div> <!-- form-group// -->

                                <div class="form-group">
                                    <label>Your email</label>
                                    <input name="txtEmail" class="form-control" placeholder="Email" type="email" required="true">
                                    <font color="red">${requestScope.REGISTER_ERROR.emailError}</font>
                                </div> <!-- form-group// -->
                                <div class="form-group">
                                    <label>Password</label>
                                    <input name="txtPassword" id="txtPassword" class="form-control" placeholder="******" type="password" required="true" minlength="8" maxlength="48">
                                </div> <!-- form-group// --> 

                                <div class="form-group">
                                    <label>Confirm password</label>
                                    <input name="txtConfirmPassword" id="txtConfirmPassword" class="form-control" placeholder="******" type="password" required="true">
                                </div> <!-- form-group// --> 
                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary btn-block" name="action" value="Register" onclick="return ValidatePassword()"> Register </button>
                                </div> <!-- form-group// -->                               
                            </form>
                        </article>
                    </div> <!-- card.// -->
                </aside> <!-- col.// -->

                <aside class="col-sm-4">
                </aside>
            </div>
        </div>
        <script type="text/javascript">
            function ValidatePassword() {
                var password = document.getElementById("txtPassword").value;
                var confirmPassword = document.getElementById("txtConfirmPassword").value;
                if (password != confirmPassword) {
                    alert("Passwords do not match!");
                    return false;
                }
                return true;
            }
        </script>
    </body>
</html>
