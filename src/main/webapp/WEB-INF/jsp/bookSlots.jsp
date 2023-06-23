<!DOCTYPE html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
    <title>Book Vaccination Slots</title>
    <link rel="stylesheet" type="text/css" href="/css/bookSlots.css">
    <script src="/js/bookSlots.js"></script>
</head>
<body>
    <h1>Book Vaccination Slots</h1>

    <form id="searchForm" action="/bookSlots" method="GET">
        <div>
            <label for="pinInput">Pin:</label>
            <input type="number" id="pinInput" name="pin" placeholder="Enter pin code">
        </div>
        <div>
            <label for="workingFromInput">Enter timing you are available from:</label>
            <input type="text" id="workingFromInput" name="workingFrom" placeholder="Enter working from time">
        </div>
        <div>
            <button type="submit">Search</button>
        </div>
    </form>

    <table id="slotsTable">
        <thead>
            <tr>
                <th>Date</th>
                <th>Centre</th>
                <th>Working From</th>
                <th>Working To</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="slot" items="${slots}">
                <tr>
                    <td>${slot.date}</td>
                    <td>${slot.centre.addressLine1}, ${slot.centre.addressLine2}, ${slot.centre.pin}</td>
                    <td>${slot.centre.workingFrom}</td>
                    <td>${slot.centre.workingTo}</td>
                    <td>
                        <a href="/bookSlot?sId=${slot.sId}">Book</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
