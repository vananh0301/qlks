<%@ page import="model.bean.Room" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Xoá Phòng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">Danh Sách Phòng</h2>
    <form id="deleteForm" action="${pageContext.request.contextPath}/StaffController/delete?action=roomdelete" method="POST">
        <table class="table table-bordered">
            <thead class="thead-light">
            <tr>
                <th>ID</th>
                <th>Tên Phòng</th>
                <th>Giá Tiền</th>
                <th>Trạng Thái</th>
                <th>Chọn</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Room> roomList = (List<Room>) request.getAttribute("room");
                if (roomList != null) {
                    for (Room room : roomList) {
            %>
            <tr>
                <td><%= room.getId() %></td>
                <td><%= room.getName() %></td>
                <td><%= room.getPrice() %></td>
                <td><%= room.getStatus() %></td>
                <td>
                    <input type="checkbox" name="selectedIds" value="<%= room.getId() %>" class="checkbox-item" />
                </td>
            </tr>
            <%
                    }
                }
            %>
            </tbody>
        </table>
        <button type="button" class="btn btn-danger" onclick="submitSelected()">Xoá Lựa Chọn</button>
        <a href="${pageContext.request.contextPath}/StaffController/index?action=roomindex" class="btn btn-secondary">Trở Về Trang Chủ</a>
    </form>
</div>

<script>
    function submitSelected() {
        // Get all checkboxes with class 'checkbox-item'
        const checkboxes = document.querySelectorAll('.checkbox-item');
        const selectedValues = [];

        // Loop through checkboxes and gather checked values
        checkboxes.forEach((checkbox) => {
            if (checkbox.checked) {
                selectedValues.push(checkbox.value);
            }
        });

        // Check if any checkboxes are selected
        if (selectedValues.length === 0) {
            alert('Please select at least one room to delete.');
            return;
        }

        // Create a hidden input field to store selected values
        const form = document.getElementById('deleteForm');
        const hiddenInput = document.createElement('input');
        hiddenInput.type = 'hidden';
        hiddenInput.name = 'selectedIds';
        hiddenInput.value = selectedValues.join(',');
        console.log(hiddenInput.value);
        console.log(selectedValues.join(','));
        // Append the hidden input to the form and submit
        form.appendChild(hiddenInput);
        form.submit();
    }
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
