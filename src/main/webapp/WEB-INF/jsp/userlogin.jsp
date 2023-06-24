<!DOCTYPE html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="/css/userlogin.css"> 
    <script src="/js/userlogin.js"></script> 
</head>
<body>
    <h1>Login</h1>
    
    <form action="userlogin" method="post">
        <label for="email">Email:</label>
        <input type="email" name="email" id="email" required>
        
        <label for="password">Password:</label>
        <input type="password" name="password" id="password" required>
        
        <span class="error">${error}</span> 
        
        <input type="submit" value="Login">
        <div class="bottom">
        <a href="/signup" class="newuser">New user? Click here</a>
        <a href="/userforgotPassword" class="fp">Forgot Password</a></div>
    </form>
    <style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f2f2f2;
        margin: 0;
        padding: 0;
    }

    h1 {
        text-align: center;
        color: #333;
    }

    form {
        max-width: 300px;
        margin: 20px auto;
        padding: 20px;
        background-color: #fff;
        border: 1px solid #ccc;
        border-radius: 5px;
    }

    label {
        display: block;
        margin-bottom: 10px;
        color: #333;
    }

    input[type="email"],
    input[type="password"] {
        width: 100%;
        padding: 10px;
        margin-bottom: 20px;
        border: 1px solid #ccc;
        border-radius: 3px;
        box-sizing: border-box;
    }

    .error {
        color: red;
        font-size: 14px;
        margin-bottom: 10px;
    }

    input[type="submit"] {
        display: block;
        width: 100%;
        padding: 10px;
        background-color: #4CAF50;
        color: #fff;
        border: none;
        border-radius: 3px;
        cursor: pointer;
    }
	.newuser{
	text-decoration:none;
	padding-top:1rem;
	}
	
	.fp{
	text-decoration:none;
	}
	
	.bottom{
	 display:flex;
	 text-decoration:none;
	 flex-direction:column;
	 gap: 1rem;
	}

    input[type="submit"]:hover {
        background-color: #45a049;
    }
</style>

    
</body>
</html>
