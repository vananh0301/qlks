<%@ page import="model.bean.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Xóa User</title>
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
        .btn-delete {
            background-color: #dc3545;
            color: white;
        }
        .btn-back {
            background-color: #6c757d;
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
    <h2 class="text-center mb-4">Danh sách User</h2>
    <form id="deleteForm" action="${pageContext.request.contextPath}/StaffController/delete?action=userdelete" method="POST">
        <table class="table table-bordered table-striped table-custom">
            <thead>
            <tr>
                <th>Username</th>
                <th>Password</th>
                <th>Role</th>
                <th>Họ tên</th>
                <th>Số điện thoại</th>
                <th>Chọn</th>
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
                    <input type="checkbox" name="selectedIds" value="<%= user.getUsername() %>" class="checkbox-item" />
                </td>
            </tr>
            <%
                    }
                }
            %>
            </tbody>
        </table>
        <button type="button" class="btn btn-delete btn-custom" onclick="submitSelected()">Xóa lựa chọn</button>
        <a href="${pageContext.request.contextPath}/StaffController/index?action=userindex" class="btn btn-back btn-custom">Trở về danh sách</a>
    </form>
</div>

<script>
    function submitSelected() {
        // Lấy tất cả các checkbox có class 'checkbox-item'
        const checkboxes = document.querySelectorAll('.checkbox-item');
        const selectedValues = [];

        // Lặp qua các checkbox và lấy giá trị của các checkbox được chọn
        checkboxes.forEach((checkbox) => {
            if (checkbox.checked) {
                selectedValues.push(checkbox.value);
            }
        });

        // Kiểm tra xem có checkbox nào được chọn không
        if (selectedValues.length === 0) {
            alert('Vui lòng chọn ít nhất một User để xóa.');
            return;
        }

        // Tạo một trường ẩn để lưu giá trị đã chọn
        const form = document.getElementById('deleteForm');
        const hiddenInput = document.createElement('input');
        hiddenInput.type = 'hidden';
        hiddenInput.name = 'selectedIds';
        hiddenInput.value = selectedValues.join(',');
        form.appendChild(hiddenInput);
        form.submit();
    }
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
