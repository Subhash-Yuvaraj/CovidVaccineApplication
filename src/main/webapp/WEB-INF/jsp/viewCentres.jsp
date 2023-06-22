<!DOCTYPE html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <title>Centers</title>
    <link rel="stylesheet" type="text/css" href="/css/viewCentres.css">
    
</head>
<body>
    <h1>Centers</h1>
    <table>
        <tr>
            <th>Center ID</th>
            <th>Address Line 1</th>
            <th>Address Line 2</th>
            <th>Pin</th>
            <th>State</th>
            <th>Working From</th>
            <th>Working To</th>
            <th>Action</th>
        </tr>
        <c:forEach var="center" items="${centers}">
            <tr>
                <td>${center.cId}</td>
                <td>${center.addressLine1}</td>
                <td>${center.addressLine2}</td>
                <td>${center.pin}</td>
                <td>${center.state}</td>
                <td>${center.workingFrom}</td>
                <td>${center.workingTo}</td>
                <td>
                    <a href="/manage?cId=${center.cId}" class="add-slot-button">Manage</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
