<%@ page contentType="text/html; charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Staff Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
        }
        .container {
            margin-top: 50px;
        }
        .card {
            margin-bottom: 20px;
        }
        .nav-link {
            font-size: 18px;
            color: #007bff;
        }
        .nav-link:hover {
            color: #0056b3;
        }
        .card-header {
            background-color: #007bff;
            color: white;
        }
        .logout-btn {
            background-color: #dc3545; /* Red color for logout */
            color: white;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
        }
        .logout-btn:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>
    <%
        String currentAdmin = (String) session.getAttribute("currentAdmin");
        if (currentAdmin == null) {
            // Nếu chưa đăng nhập, chuyển hướng đến trang đăng nhập
            response.sendRedirect("login_admin.jsp");
            return;
        }
    %>
<div class="container">
    <div class="card">
        <div class="card-header text-center">
            <h2>Welcome, Staff!</h2>
        </div>
        <div class="card-body">
            <ul class="list-group">
                <li class="list-group-item">
                    <a href="${pageContext.request.contextPath}/StaffController/index?action=roomindex" class="nav-link">Manage Room</a>
                </li>
                <li class="list-group-item">
                    <a href="${pageContext.request.contextPath}/StaffController/booking?action=manageBooking" class="nav-link">Manage Bookings</a>
                </li>
                <li class="list-group-item">
                    <a href="${pageContext.request.contextPath}/StaffController/index?action=userindex" class="nav-link">Manage Users</a>
                </li>
            </ul>
        </div>
        <div class="card-footer text-center">
            <!-- Logout Button -->
            <form action="${pageContext.request.contextPath}/StaffController/login" method="get">
                <input type="hidden" name="action" value="logout_admin">
                <button type="submit" class="logout-btn">Logout</button>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
