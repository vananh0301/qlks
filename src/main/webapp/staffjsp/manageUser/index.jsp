<%@ page import="model.bean.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Quan ly User</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .table-custom th, .table-custom td {
      vertical-align: middle;
      padding: 15px;
    }
    .table-custom tbody tr:hover {
      background-color: #f1f1f1;
    }
    .table-custom thead {
      background-color: #343a40;
      color: white;
    }
    .btn-custom {
      font-size: 14px;
      padding: 8px 15px;
    }
    .btn-create {
      background-color: #28a745;
      color: white;
    }
    .btn-back {
      background-color: #6c757d;
      color: white;
    }
    .btn-delete {
      background-color: #dc3545;
      color: white;
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
<div class="container mt-5">
  <h2 class="text-center mb-4">Danh sach User</h2>
  <table class="table table-bordered table-striped table-custom">
    <thead>
    <tr>
      <th>Username</th>
      <th>Password</th>
      <th>Role</th>
      <th>Họ tên</th>
      <th>Số điện thoại</th>
      <th></th>
    </tr>
    </thead>
    <tbody>
    <%
      List<User> userList = (List<User>) request.getAttribute("user");
      if (userList != null) {
        for (User user : userList) {
    %>
    <tr>
      <td><%= user.getUsername() %></td>
      <td><%= user.getPassword() %></td>
      <td><%= user.getRole() %></td>
      <td><%= user.getHoten() %></td>
      <td><%= user.getSdt() %></td>
      <td>
        <form action="${pageContext.request.contextPath}/StaffController/detail" method="get" class="d-inline">
          <input type="hidden" name="username" value="<%= user.getUsername() %>"/>
          <input type="hidden" name="action" value="userdetail">
          <button type="submit" class="btn btn-primary btn-custom">Chi tiết</button>
        </form>
        <form action="${pageContext.request.contextPath}/StaffController/update" method="get" class="d-inline">
          <input type="hidden" name="username" value="<%= user.getUsername() %>"/>
          <input type="hidden" name="action" value="userupdate">
          <button type="submit" class="btn btn-warning btn-custom">Cập nhật</button>
        </form>
      </td>
    </tr>
    <%
      }
    } else {
    %>
    <tr>
      <td colspan="5" class="text-center">No users found.</td>
    </tr>
    <%
      }
    %>
    </tbody>
  </table>
  <div class="mt-4">
    <a href="${pageContext.request.contextPath}/StaffController/create?action=usercreate" class="btn btn-create btn-custom">Tạo User mới</a>
    <a href="${pageContext.request.contextPath}/StaffController/delete?action=userdelete" class="btn btn-delete btn-custom">Xóa User</a>
        <a href="${pageContext.request.contextPath}/staffjsp/dashboard.jsp" class="btn btn-back btn-custom">Trở về trang chủ</a>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
