<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Tạo Mới Phòng</title>
</head>
<body>
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
    <div class="form-group">
      <label for="status">Trạng Thái</label>
      <input type="text" class="form-control" id="status" name="status" required>
    </div>
    <button type="submit" class="btn btn-primary">Thêm Mới</button>
    <a href="${pageContext.request.contextPath}/StaffController/index?action=roomindex" class="btn btn-secondary">Trở Về Trang Chủ</a>
  </form>
</div>
</body>
</html>
