<!DOCTYPE html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <title>Vaccination History</title>
    <link rel="stylesheet" type="text/css" href="/css/viewHistory.css"> <!-- CSS file link -->
</head>
<body>
    <h1>Vaccination History</h1>
    
    <%-- Iterate over the user's bookings and display the details --%>
    <table>
        <tr>
            <th>Booking ID</th>
            <th>Slot Date</th>
            <th>Centre Address</th>
        </tr>
        <c:forEach items="${bookings}" var="booking">
            <tr>
                <td>${booking.bId}</td>
                <td>${booking.slot.date}</td>
                <td>${booking.slot.centre.addressLine1}, ${booking.slot.centre.addressLine2}</td>
            </tr>
        </c:forEach>
    </table>
    
    <a href="/userdashboard">Go Back to Dashboard</a>
</body>
</html>
