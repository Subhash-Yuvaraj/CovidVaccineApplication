<!DOCTYPE html>
<html>
<head>
    <title>Signup</title>
    <link rel="stylesheet" type="text/css" href="/css/signup.css"> 
    <script src="/js/signup.js"></script> 
</head>
<body>
    <h1>Signup</h1>
    
    <form action="signup" method="post">
        <label for="email">Email:</label>
        <input type="email" name="email" id="email" required>
        <span class="error">${errorMsg}</span> <!-- Display error message, if any -->
        
        <label for="password">Password:</label>
        <input type="password" name="password" id="password" required>
        
        <label for="name">Name:</label>
        <input type="text" name="name" id="name" required>
        
        <label for="dob">Date of Birth:</label>
        <input type="date" name="dob" id="dob" required>
        
        <label for="address1">Address Line 1:</label>
        <input type="text" name="address1" id="address1" required>
        
        <label for="address2">Address Line 2:</label>
        <input type="text" name="address2" id="address2" required>
        
        <label for="pin">PIN:</label>
        <input type="text" name="pin" id="pin" required>
        
        <input type="submit" value="Sign up">
    </form>
</body>
</html>
