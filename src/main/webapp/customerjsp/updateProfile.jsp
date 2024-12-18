<%@page import="model.bean.User"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>Update Profile</title>
<style type="text/css">
header {
	background-color: #005cbe;
	color: white;
	padding: 10px 50px;
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin: 0; /* Xóa margin */
	width: 100vw; /* Chiều rộng bằng toàn bộ màn hình */
	box-sizing: border-box; /* Bao gồm padding vào kích thước */
	position: relative; /* Giữ header không bị lệch */
}

header h1 {
	margin: 0;
	font-size: 1.9em;
	color: white;
}

header .nav-links {
	display: flex;
	align-items: center;
	gap: 15px;
}

header .nav-links a {
	color: white;
	text-decoration: none;
	font-size: 1em;
	padding: 8px 12px;
	border: 1px solid white;
	border-radius: 5px;
	transition: background-color 0.3s, color 0.3s;
}

header .nav-links a:hover {
	background-color: white;
	color: #007BFF;
}

/* Menu ngang dưới header */
.sub-menu {
	background-color: #004a99;
	display: flex;
	padding: 10px 50px;
	margin: 0;
	justify-content: center;
	width: 100vw; /* Chiều rộng bằng toàn bộ màn hình */
	box-sizing: border-box; /* Bao gồm padding vào kích thước */
	position: relative; /* Giữ header không bị lệch */
	margin-bottom: 30px;
}

.sub-menu a {
	color: white;
	text-decoration: none;
	font-size: 1em;
	padding: 8px 15px;
	transition: background-color 0.3s, color 0.3s;
	text-transform: uppercase;
}

.sub-menu a:hover {
	background-color: white;
	color: #004a99;
	border-radius: 5px;
}

body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f9;
	margin: 0;
	s
}

.profile-form {
	background-color: #ffffff;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	max-width: 500px;
	margin: auto;
}

.profile-form h2 {
	text-align: center;
	margin-bottom: 20px;
	color: #005cbe;
}

.profile-form .form-section {
	margin-bottom: 20px;
}

.profile-form .form-section h3 {
	border-bottom: 2px solid #005cbe;
	margin-bottom: 10px;
	padding-bottom: 5px;
	color: #333333;
}

.profile-form label {
	display: block;
	margin-bottom: 5px;
	color: #333333;
}

.profile-form input {
	width: 100%;
	padding: 8px;
	margin-bottom: 10px;
	border: 1px solid #cccccc;
	border-radius: 4px;
	box-sizing: border-box;
}

.profile-form input:focus {
	border-color: #005cbe;
	outline: none;
}

.profile-form .form-actions {
	text-align: center;
}

.profile-form .btn-save {
	background-color: #005cbe;
	color: white;
	border: none;
	padding: 10px 20px;
	border-radius: 5px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.profile-form .btn-save:hover {
	background-color: #004a99;
}

.profile-form .btn-reset {
	background-color: #cccccc;
	color: #333333;
	border: none;
	padding: 10px 20px;
	border-radius: 5px;
	cursor: pointer;
	margin-left: 10px;
}

.error-message {
	color: red;
	margin-top: 10px;
	text-align: center;
}
</style>
</head>
<body>
	<%
	String message = (String) request.getAttribute("message");
	if (message != null) {
	%>
	<script>
        alert("<%=message%>");
	</script>
	<%
	}
	%>
	<%
	User user = (User) request.getAttribute("user");
	String currentUser = (String) session.getAttribute("currentUser");
	if (currentUser == null) {
		// Nếu chưa đăng nhập, chuyển hướng đến trang đăng nhập
		response.sendRedirect("${pageContext.request.contextPath}/customerjsp/login.jsp");
		return;
	}
	%>
	<header>
		<h1>
			<a
				href="${pageContext.request.contextPath}/CustomerController?action=listPhong"
				style="color: white; text-decoration: none;">HotelBooking</a>
		</h1>
		<div class="nav-links">
			<%
			if (currentUser != null) {
			%>
			<p style="margin: 0">
				Xin chào,
				<%=currentUser%>!
			</p>
			<a
				href="${pageContext.request.contextPath}/CustomerController?action=logout">Đăng
				xuất</a>
			<%
			} else {
			%>
			<a href="${pageContext.request.contextPath}/customerjsp/login.jsp">Đăng
				nhập</a> <a
				href="${pageContext.request.contextPath}/customerjsp/register.jsp">Đăng
				ký</a>
			<%
			}
			%>
		</div>

	</header>
	<nav class="sub-menu">
		<a
			href="${pageContext.request.contextPath}/CustomerController?action=listPhong"
			style="border-right: 1px solid white;">Trang chủ</a> <a
			href="${pageContext.request.contextPath}/CustomerController?action=lichsu"
			style="border-right: 1px solid white;">Xem lịch sử booking</a> <a
			href="${pageContext.request.contextPath}/CustomerController?action=updateProfile">Cập
			nhật hồ sơ</a>
	</nav>
	<div class="profile-form">
		<h2>Cập nhật hồ sơ</h2>
		<form action="${pageContext.request.contextPath}/CustomerController"
			method="post">
			<!-- Hidden field for identifying action -->
			<input type="hidden" name="action" value="updateProfile">

			<!-- Thông tin cá nhân -->
			<div class="form-section">
				<h3>Thông tin cá nhân</h3>
				<label for="name">Họ và tên:</label> <input type="text" id="name"
					name="name" value="<%=user.getHoten()%>" required> <label
					for="phone">Số điện thoại:</label> <input type="tel" id="phone"
					name="phone" value="<%=user.getSdt()%>" required>
			</div>

			<!-- Cập nhật mật khẩu -->
			<div class="form-section">
				<h3>Đổi mật khẩu</h3>

				<label for="newPassword">Mật khẩu mới:</label> <input
					type="password" id="newPassword" name="newPassword"> <label
					for="confirmPassword">Xác nhận mật khẩu mới:</label> <input
					type="password" id="confirmPassword" name="confirmPassword">
			</div>

			<!-- Nút lưu thay đổi -->
			<div class="form-actions">
				<button type="submit" class="btn-save">Lưu thay đổi</button>
				<button type="reset" class="btn-reset">Đặt lại</button>
			</div>
			<%
			String errorMessage = (String) request.getAttribute("error");
			if (errorMessage != null) {
			%>
			<p class="error-message"><%=errorMessage%></p>
			<%
			}
			%>
		</form>
	</div>
</body>
</html>
