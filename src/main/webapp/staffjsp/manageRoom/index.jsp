<%@ page import="model.bean.Room" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Quan ly Phong</title>
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
<div class="container mt-5">
  <h2 class="text-center mb-4">Danh sach Phong</h2>
  <table class="table table-bordered table-striped table-custom">
    <thead>
    <tr>
      <th>ID</th>
      <th>Ten Phong</th>
      <th>Gia tien</th>
      <th>Trang thai</th>
      <th></th>
    </tr>
    </thead>
    <tbody>
    <%
      List<Room> roomArrayList = (List<Room>) request.getAttribute("room");
      if (roomArrayList != null) {
        for (Room room : roomArrayList) {
    %>
    <tr>
      <td><%= room.getId() %></td>
      <td><%= room.getName() %></td>
      <td><%= room.getPrice() %></td>
      <td><%= room.getStatus() %></td>
      <td>
        <form action="${pageContext.request.contextPath}/StaffController/detail?action=roomdetail" method="get" class="d-inline">
          <input type="hidden" name="id" value="<%= room.getId() %>"/>
          <button type="submit" class="btn btn-primary btn-custom">Chi tiet</button>
        </form>
        <form action="${pageContext.request.contextPath}/StaffController/update?action=roomupdate" method="get" class="d-inline">
          <input type="hidden" name="id" value="<%= room.getId() %>"/>
          <button type="submit" class="btn btn-warning btn-custom">Cap nhat</button>
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
    <a href="${pageContext.request.contextPath}/StaffController/create?action=roomcreate" class="btn btn-create btn-custom">Tao phong moi</a>
    <a href="../dashboard.jsp" class="btn btn-back btn-custom">Tro ve trang chu</a>
    <a href="${pageContext.request.contextPath}/StaffController/delete?action=roomdelete" class="btn btn-delete btn-custom">Xoa phong</a>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
