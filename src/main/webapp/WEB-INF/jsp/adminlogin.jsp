<!DOCTYPE html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <title>Admin Login</title>
    <link rel="stylesheet" type="text/css" href="/css/admin-login.css">
    <script type="text/javascript" src="/js/admin-login.js"></script>
</head>
<body>
    <div class="container">
        <h1>Admin Login</h1>
        <form id="login-form" method="post" action="/adminlogin">
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" name="email" id="email" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" name="password" id="password" required>
            </div>
            <div class="form-group">
                <button type="submit">Login</button>
            </div>
            
                <p class="error">${error}</p>
               
              <div class="bottom">
        <a href="/adminforgotPassword" class="fp">Forgot Password</a></div>
            
        </form>
        <div id="error-message" class="error"></div>
    </div>
</body>
</html>