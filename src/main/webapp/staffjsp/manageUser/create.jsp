<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Tạo mới User</title>
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
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<div class="container mt-5">
  <h2>Tạo mới User</h2>
  <form action="${pageContext.request.contextPath}/StaffController/create?action=usercreate" method="post">
    <div class="form-group">
      <label for="username">Username</label>
      <input type="text" class="form-control" id="username" name="username" required>
    </div>
    <div class="form-group">
      <label for="password">Password</label>
      <input type="password" class="form-control" id="password" name="password" required>
    </div>
    <!-- <div class="form-group">
      <label for="role">Role</label>
      <input type="text" class="form-control" id="role" name="role" required>
    </div> -->
    <div class="form-group">
    	<label for="role">Role</label>
    	<select class="form-control" id="role" name="role" required>
        	<option value="customer">Customer</option>
        	<option value="staff">Staff</option>
    	</select>
	</div>
    <div class="form-group">
      <label for="hoten">Họ tên</label>
      <input type="text" class="form-control" id="hoten" name="hoten" required>
    </div>
    <div class="form-group">
      <label for="sdt">Số điện thoại</label>
      <input type="text" class="form-control" id="sdt" name="sdt" required>
    </div>
    <button type="submit" class="btn btn-primary">Thêm mới</button>
    <a href="${pageContext.request.contextPath}/StaffController/index" class="btn btn-secondary">Trở về trang chủ</a>
  </form>
</div>
</body>
</html>
