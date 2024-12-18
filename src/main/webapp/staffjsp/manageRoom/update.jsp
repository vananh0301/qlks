<%@ page import="model.bean.Room" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cập nhật Phòng</title>
</head>
<body>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<div class="container mt-5">
    <%
        Room prevData = (Room) request.getAttribute("room");
    %>
    <h2>Cập nhật Phòng</h2>
    <form action="${pageContext.request.contextPath}/StaffController/update?action=roomupdate" method="post">
        <div class="form-group">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" value="<%= prevData.getId() %>" required readonly>
        </div>
        <div class="form-group">
            <label for="name">Tên Phòng</label>
            <input type="text" class="form-control" id="name" name="name" value="<%= prevData.getName() %>" required>
        </div>
        <div class="form-group">
            <label for="price">Giá Tiền</label>
            <input type="text" class="form-control" id="price" name="price" value="<%= prevData.getPrice() %>" required>
        </div>
        <div class="form-group">
            <label for="status">Trạng Thái</label>
            <input type="text" class="form-control" id="status" name="status" value="<%= prevData.getStatus() %>" required>
        </div>
        <button type="submit" class="btn btn-primary">Cập nhật</button>
        <a href="${pageContext.request.contextPath}/StaffController/index?action=roomindex" class="btn btn-secondary">Trở về trang chủ</a>
    </form>
</div>
</body>
</html>
