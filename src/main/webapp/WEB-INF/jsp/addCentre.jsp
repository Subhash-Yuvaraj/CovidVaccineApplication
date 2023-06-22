<!-- addCentre.jsp -->

<!DOCTYPE html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <title>Add Centre</title>
    <link rel="stylesheet" type="text/css" href="/css/addCentre.css">
    <script type="text/javascript" src="/js/addCentre.js"></script>
</head>
<body>
    <div class="container">
        <h1>Add Centre</h1>
        <form id="addCentreForm" method="post" action="/addCentre">
            <div class="form-group">
                <label for="addressLine1">Address Line 1</label>
                <input type="text" id="addressLine1" name="addressLine1" required>
            </div>
            <div class="form-group">
                <label for="addressLine2">Address Line 2</label>
                <input type="text" id="addressLine2" name="addressLine2" required>
            </div>
            <div class="form-group">
                <label for="pin">PIN</label>
                <input type="number" id="pin" name="pin" required>
            </div>
            <div class="form-group">
                <label for="workingFrom">Working From (hh:mm:ss)</label>
                <input type="text" id="workingFrom" name="workingFrom" required>
            </div>
            <div class="form-group">
                <label for="workingTo">Working To (hh:mm:ss)</label>
                <input type="text" id="workingTo" name="workingTo" required>
            </div>
            <button type="submit" class="submit-button">Submit</button>
            <c:if test="${not empty error}">
                <div id="error">${error}</div>
            </c:if>
        </form>
    </div>
</body>
</html>
