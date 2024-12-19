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
        String currentAdmin = (String) session.getAttribute("currentAdmin");
        if (currentAdmin == null) {
            // Nếu chưa đăng nhập, chuyển hướng đến trang đăng nhập
            response.sendRedirect("login_admin.jsp");
            return;
        }
    %>
    <%
        Room prevData = (Room) request.getAttribute("room");
    	String message = (String) request.getAttribute("updateMessage");
    if (message != null) {
	%>
    <script>
        alert("<%= message %>"); // Hiển thị thông báo bằng alert
    </script>
	<%
    }
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
    	<select class="form-select" id="status" name="status" required>
        	<option value="available" <%= "available".equals(prevData.getStatus()) ? "selected" : "" %>>available</option>
        	<option value="occupied" <%= "occupied".equals(prevData.getStatus()) ? "selected" : "" %>>occupied</option>
        	<option value="maintenance" <%= "maintenance".equals(prevData.getStatus()) ? "selected" : "" %>>maintenance</option>
    	</select>
		</div>
        <!-- <div class="form-group">
            <label for="status">Trạng Thái</label>
            <input type="text" class="form-control" id="status" name="status" value="<%= prevData.getStatus() %>" required>
        </div> -->

        <button type="submit" class="btn btn-primary">Cập nhật</button>
        <a href="${pageContext.request.contextPath}/StaffController/index?action=roomindex" class="btn btn-secondary">Quay lại</a>
    </form>
</div>
</body>
</html>
