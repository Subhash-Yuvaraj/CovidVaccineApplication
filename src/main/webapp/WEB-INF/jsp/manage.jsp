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
        
        <button type="submit" class="submit-button">Update</button>
        <c:if test="${not empty error}">
                <div id="error">${error}</div>
        </c:if>
        <a href="/addSlot?cId=${center.cId}" class="add-slot-button">Manage</a>
    </form>
    <div id="error-message" class="error"></div>
</body>
</html>
