<%@ page import="model.bean.Room" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi tiết Phòng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
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
<div class="container mt-5">
    <div class="card">
        <div class="card-header text-center bg-primary text-white">
            <h2>Chi tiết Phòng</h2>
            <h3><%=request.getAttribute("updateMessage") != null? request.getAttribute("updateMessage") :"" %></h3>
        </div>
        <div class="card-body">
            <%
                Room room = (Room) request.getAttribute("room");
            %>
            <table class="table table-bordered">
                <tbody>
                <tr>
                    <th scope="row">ID</th>
                    <td><%= room.getId() %></td>
                </tr>
                <tr>
                    <th scope="row">Tên Phòng</th>
                    <td><%= room.getName() %></td>
                </tr>
                <tr>
                    <th scope="row">Giá Tiền</th>
                    <td><%= room.getPrice() %></td>
                </tr>
                <tr>
                    <th scope="row">Trạng Thái</th>
                    <td><%= room.getStatus() %></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="card-footer text-center">
            <a href="${pageContext.request.contextPath}/StaffController/index?action=roomindex" class="btn btn-secondary">Quay lại</a>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
