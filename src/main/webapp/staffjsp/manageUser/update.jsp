<%@ page import="model.bean.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cập nhật User</title>
</head>
<body>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<div class="container mt-5">
    <%
        User prevData = (User) request.getAttribute("user");
    %>
    <h2>Cập nhật User</h2>
    <form action="${pageContext.request.contextPath}/StaffController/update?action=userupdate" method="post">
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" id="username" name="username" value="<%= prevData.getUsername() %>" required readonly>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="text" class="form-control" id="password" name="password" value="<%= prevData.getPassword() %>" required>
        </div>
        <div class="form-group">
            <label for="role">Role</label>
            <input type="text" class="form-control" id="role" name="role" value="<%= prevData.getRole() %>" required>
        </div>
        <div class="form-group">
            <label for="hoten">Họ tên</label>
            <input type="text" class="form-control" id="hoten" name="hoten" value="<%= prevData.getHoten() %>" required>
        </div>
        <div class="form-group">
            <label for="sdt">Số điện thoại</label>
            <input type="text" class="form-control" id="sdt" name="sdt" value="<%= prevData.getSdt() %>" required>
        </div>
        <button type="submit" class="btn btn-primary">Cập nhật</button>
        <a href="${pageContext.request.contextPath}/StaffController/index?action=userindex" class="btn btn-secondary">Trở về trang chủ</a>
    </form>
</div>
</body>
</html>
