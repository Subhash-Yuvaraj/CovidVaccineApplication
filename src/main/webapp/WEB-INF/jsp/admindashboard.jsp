<!-- admin-dashboard.jsp -->

<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="/css/adminDashboard.css">
    <script type="text/javascript" src="/js/adminDashboard.js"></script>
</head>
<body>
    <div class="container">
        <h1>Welcome, Admin</h1>
        <center><table border="0.5px">
            <tr><td><a href="/addCentre" class="link" onclick="handleAddCentre()">Add Centre</a></td></tr>
            <tr><td><a href="/viewCentres" class="link" onclick="handleViewCentre()">View Centre</a></td></tr>
        </table></center>
        <div class="content">
            <!-- Place your content here -->
        </div>
    </div>
</body>
</html>
