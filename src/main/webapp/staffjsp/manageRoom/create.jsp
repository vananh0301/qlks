<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Tạo Mới Phòng</title>
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
  <h2>Tạo Mới Phòng</h2>
  <form action="${pageContext.request.contextPath}/StaffController/create?action=roomcreate" method="post">
    <div class="form-group">
      <label for="id">ID</label>
      <input type="text" class="form-control" id="id" name="id" required>
    </div>
    <div class="form-group">
      <label for="name">Tên Phòng</label>
      <input type="text" class="form-control" id="name" name="name" required>
    </div>
    <div class="form-group">
      <label for="price">Giá Tiền</label>
      <input type="text" class="form-control" id="price" name="price" required>
    </div>
    <!--     <div class="form-group">
      <label for="status">Trạng Thái</label>
      <input type="text" class="form-control" id="status" name="status" required>
    </div> -->
	<div class="form-group">
    	<label for="status">Trạng Thái</label>
    	<select class="form-control" id="status" name="status" required>
        	<option value="available">available</option>
        	<option value="occupied">occupied</option>
        	<option value="maintenance">maintenance</option>
    	</select>
	</div>
    <button type="submit" class="btn btn-primary">Thêm Mới</button>
    <a href="${pageContext.request.contextPath}/StaffController/index?action=roomindex" class="btn btn-secondary">Quay lại</a>
  </form>
</div>
</body>
</html>
