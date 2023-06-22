<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="/css/userlogin.css"> <!-- CSS file link -->
    <script src="/js/userlogin.js"></script> <!-- JS file link -->
</head>
<body>
    <h1>Login</h1>
    
    <form action="userlogin" method="post">
        <label for="email">Email:</label>
        <input type="email" name="email" id="email" required>
        
        <label for="password">Password:</label>
        <input type="password" name="password" id="password" required>
        
        <span class="error">${errorMsg}</span> 
        
        <input type="submit" value="Login">
    </form>
</body>
</html>
