<!DOCTYPE html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
    <title>Manage Center</title>
    <link rel="stylesheet" type="text/css" href="/css/manageCenter.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="/js/manageCenter.js"></script>
</head>
<body>
	<nav class="navbar-container">
        <div class="logo-container">
            <a href="index.html">Hi! ${name}</a>
        </div>

        <div class="bars">
            <div class="bar"></div>
            <div class="bar"></div>
            <div class="bar"></div>
        </div>

        <ul class="nav-items">
            <li class="nav-link"><a href="/admindashboard">Home</a></li>
            <li class="nav-link"><a href="/addCentre">Add center</a></li>
            <li class="nav-link"><a href="/viewCentres">View centers</a></li>
            <li class="nav-link"><a href="/adminlogout">Logout</a></li>
        </ul>
    </nav>
    <div style='margin-top:100px' class="container">
    <h1>Manage Center</h1>
    <form action="/manage/${center.cId}/update" method="post">
        
        <label for="addressLine1">Address Line 1:</label>
        <input type="text" id="addressLine1" name="addressLine1" value="${center.addressLine1}" required>
        
        <label for="addressLine2">Address Line 2:</label>
        <input type="text" id="addressLine2" name="addressLine2" value="${center.addressLine2}" required>
        
        <label for="pin">Pin:</label>
        <input type="number" id="pin" name="pin" value="${center.pin}" required>
        
        <label for="state">State:</label>
        <div class="toggle-switch">
            <input type="checkbox" id="state" name="state" ${center.state ? 'checked' : ''}>
            <label for="state" class="toggle-switch-label"></label>
        </div>
        
        <label for="workingFrom">Working From:</label>
        <input type="text" id="workingFrom" name="workingFrom" value="${center.workingFrom}" required>
        
        <label for="workingTo">Working To:</label>
        <input type="text" id="workingTo" name="workingTo" value="${center.workingTo}" required>
        <center>
        <button type="submit" class="submit-button">Update</button>
        
                <p id="error">${error}</p>
        
        
        <a href="/addSlot?cId=${center.cId}" class="submit-button" style="text-decoration:none;">Add slot</a></center>
    </form>
    <div id="error-message" class="error"></div>
    </div>
</body>
</html>

<style>
	@import url('https://fonts.googleapis.com/css2?family=Kanit:wght@600;700&family=Poppins:wght@500&display=swap');

* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Poppins', sans-serif
}


a {
    text-decoration: none;
}

.navbar-container {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0 2rem;
    width: 100%;
    height: 70px;
    background: #176B87;
    color: #DAFFFB;
    position: fixed;
    top: 0;
}

.navbar-container .logo-container a {
    font-size: 1.5rem;
    font-weight: 500;
    font-family: 'Kanit', sans-serif;
    text-transform: uppercase;
    cursor: pointer;
    color: #DAFFFB;
}

.navbar-container .logo-container a:hover {
    opacity: 0.8;
}

.navbar-container .nav-items {
    display: flex;
    align-items: center;
    gap: 3rem;
    list-style: none;
}

.navbar-container .nav-items .nav-link a {
    color: #DAFFFB;
    padding: 10px;
    text-transform: uppercase;
    transition: 0.2s;
    -webkit-transition: 0.2s;
    -moz-transition: 0.2s;
    -ms-transition: 0.2s;
    -o-transition: 0.2s;
}

.navbar-container .nav-items .nav-link:after {
    content: '';
    display: block;
    border-top: 2px solid #DAFFFB;
    transform: scaleX(0);
    -webkit-transform: scaleX(0);
    -moz-transform: scaleX(0);
    -ms-transform: scaleX(0);
    -o-transform: scaleX(0);
    transition: transform 250ms ease-in-out;
    -webkit-transition: transform 250ms ease-in-out;
    -moz-transition: transform 250ms ease-in-out;
    -ms-transition: transform 250ms ease-in-out;
    -o-transition: transform 250ms ease-in-out;
}

.navbar-container .nav-items .nav-link:hover:after {
    transform: scaleX(0.8);
    -webkit-transform: scaleX(0.8);
    -moz-transform: scaleX(0.8);
    -ms-transform: scaleX(0.8);
    -o-transform: scaleX(0.8);
}

.navbar-container .nav-items .login-register {
    display: flex;
    flex-direction: row;
    gap: 1rem;
}

.navbar-container .nav-items .login-register .button {
    color: #DAFFFB;
    border: 2px solid #DAFFFB;
    padding: 5px 20px;
    text-transform: uppercase;
    border-radius: 3px;
    -webkit-border-radius: 3px;
    -moz-border-radius: 3px;
    -ms-border-radius: 3px;
    -o-border-radius: 3px;
    transition: all ease-in 0.3s;
    -webkit-transition: all ease-in 0.3s;
    -moz-transition: all ease-in 0.3s;
    -ms-transition: all ease-in 0.3s;
    -o-transition: all ease-in 0.3s;
}

.navbar-container .nav-items .login-register .button:hover {
    background: #DAFFFB;
    color: #176B87;
}



.main {
    min-height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    color: #DAFFFB;
    font-size: 3rem;
    text-align: center;
    padding: 1rem;
}



@media(max-width: 1000px) {
    .navbar-container {
        padding: 0 1rem;
    }

    .navbar-container .nav-items {
        gap: 0.1rem;
    }

    .navbar-container .nav-items .nav-link a,
    .navbar-container .nav-items .login-register .button {
        font-size: 0.9rem;
    }
}

@media(max-width: 700px) {
    .navbar-container .nav-items {
        flex-direction: column;
        position: fixed;
        top: 70px;
        right: -100%;
        width: 100vw;
        height: 100vh;
        padding: 1rem 0;
        z-index: 99;
        background: #2590B5;
        transition: 0.2s ease-in;
        -webkit-transition: 0.2s ease-in;
        -moz-transition: 0.2s ease-in;
        -ms-transition: 0.2s ease-in;
        -o-transition: 0.2s ease-in;
    }

    .bars {
        width: 22px;
        height: auto;
        cursor: pointer;
    }

    .bars .bar {
        width: 100%;
        height: 2px;
        background: #DAFFFB;
        margin: 5px;
    }

    .navbar-container .nav-items {
        gap: 3rem;
    }

    .navbar-container .nav-items .login-register {
        width: 50%;
        text-align: center;
        flex-direction: column;
        gap: 2rem;
    }

    .navbar-container .nav-items .login-register .button {
        padding: 1rem 0;
    }

    .main {
        font-size: 2rem;
    }
}

.navbar-container .nav-items.active {
    right: 0;
}
</style>
