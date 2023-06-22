<!DOCTYPE html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <title>Add Slot</title>
    <link rel="stylesheet" type="text/css" href="/css/addSlot.css">
    <script src="/js/addSlot.js"></script>
</head>
<body>
    <h1>Add Slot</h1>
    <form action="/saveSlot" method="post">
        <input type="hidden" name="cId" value="${cId}">
        <label for="date">Date:</label>
        <input type="date" name="date" id="date" min="<%= java.time.LocalDate.now().plusDays(1) %>" required>
        <button type="submit">Add Slot</button>
    </form>
</body>
</html>
