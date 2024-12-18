<%@page import="model.bean.User"%>
<%@page import="model.bean.Room"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
       body {
    margin: 0;
    font-family: Arial, sans-serif;
    height: 100vh;
    display: flex;
    flex-direction: column;
}

.wrapper {
    display: flex;
    flex-direction: column;
    flex: 1; /* Tự động chiếm không gian còn lại */
}

.container {
    background: #ffffff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
    width: 400px;
    margin: auto; /* Đưa form vào giữa theo chiều dọc và ngang */
}
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
        h2 {
            text-align: center;
            color: #333;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            margin-bottom: 5px;
            color: #555;
        }
        input[type="text"], input[type="datetime-local"] {
            margin-bottom: 15px;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            width: 100%;
            box-sizing: border-box;
        }
        button {
            padding: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #0056b3;
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
    	
    //Integer idp = (Integer) request.getAttribute("idPhong");
    Room phong = (Room) request.getAttribute("phongDetail");
    User user = (User) request.getAttribute("user");
    //if (idp == null){
    	//idp = 0;
    //}
        String currentUser = (String) session.getAttribute("currentUser");
        if (currentUser == null) {
            // Nếu chưa đăng nhập, chuyển hướng đến trang đăng nhập
            response.sendRedirect("${pageContext.request.contextPath}/customerjsp/login.jsp");
            return;
        }
    %>
<div class="wrapper">
<header>
     <h1><a href="${pageContext.request.contextPath}/CustomerController?action=listPhong" style="color: white; text-decoration: none;">HotelBooking</a></h1>
    <div class="nav-links">
   		<% if (currentUser != null) { %>
            <p style="margin: 0">Xin chào, <%= currentUser %>!</p>
            <a href="${pageContext.request.contextPath}/CustomerController?action=logout"">Đăng xuất</a>
        <% } else { %>
            <a href="${pageContext.request.contextPath}/customerjsp/login.jsp">Đăng nhập</a>
        	<a href="${pageContext.request.contextPath}/customerjsp/register.jsp"">Đăng ký</a>
        <% } %>
        
    </div>
</header>
		<nav class="sub-menu">
        	<a href="${pageContext.request.contextPath}/CustomerController?action=listPhong" style="border-right: 1px solid white;">Trang chủ</a>
            <a href="${pageContext.request.contextPath}/CustomerController?action=lichsu" style="border-right: 1px solid white;">Xem lịch sử booking</a>
            <a href="${pageContext.request.contextPath}/CustomerController?action=updateProfile">Cập nhật hồ sơ</a>
        </nav>
<div class="container">
    <h2>Đặt phòng khách sạn</h2>
    <form action="${pageContext.request.contextPath}/CustomerController" method="post">
        <h3>Thông tin người đặt phòng</h3>
        <label for="username">Tên người đặt:</label>
        <input type="text" id="username" name="username" value="<%= currentUser %>" readonly><br>
        <label for="sdt">Số điện thoại:</label>
        <input type="text" id="sdt" name="sdt" value="<%= user.getSdt() %>" readonly><br>
		
        <h3>Thông tin đặt phòng</h3>
        
        	<input type="hidden" id="idp" name="idp" value="<%= phong.getId() %>">

        <label for="ten">Tên phòng:</label>
        <input type="text" id="ten" name="ten" value="<%= phong.getName() %>"><br>
        <label for="gia">Giá phòng:</label>
        <input type="text" id="gia" name="gia" value="<%= phong.getPrice()%>"><br>
        <label for="ngaydat">Ngày nhận phòng:</label>
        <input type="datetime-local" id="ngaydat" name="ngaydat" required><br>

        <label for="ngaytra">Ngày trả phòng:</label>
        <input type="datetime-local" id="ngaytra" name="ngaytra" required><br>
		<input type="hidden" name="action" value="addBooking">
        <button type="submit">Đặt phòng</button>
    </form>
        <% 
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
    %>
        <p class="error-message"><%= errorMessage %></p>
    <% } %>
</div>
</div>
</body>
</html>