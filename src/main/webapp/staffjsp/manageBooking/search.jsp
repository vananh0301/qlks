<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.form-container {
	width: 400px;
	margin: 0 auto;
	padding: 20px;
	border: 1px solid #ccc;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
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
<div class="form-container">
	<h2 align="center">Tìm kiếm</h2>
    <form action="${pageContext.request.contextPath}/StaffController/booking" method="get">
        <label for="keyword">Nhập giá trị tìm kiếm:</label>
        <input type="text" name="keyword" id="keyword" required>
        <br><br>
        
        <label>Chọn tiêu chí tìm kiếm:</label><br>
        <input type="radio" name="searchBy" value="id" checked> ID<br>
        <input type="radio" name="searchBy" value="ten"> Username<br>
        <input type="radio" name="searchBy" value="phong"> Số phòng<br>
        <br>
        
        <input type="hidden" name="action" value="search"> <!-- Để phân biệt hành động -->
        <p style="text-align: center;">
        	<input type="submit" value="Tìm kiếm" style="background-color: #4CAF50; color: white; padding: 8px 16px; border: none; border-radius: 4px; cursor: pointer;">
        	<input type="reset" value="Reset" style="background-color: #f44336; color: white; padding: 8px 16px; border: none; border-radius: 4px; cursor: pointer;">
        </p>
    </form>
</div>
</body>
</html>