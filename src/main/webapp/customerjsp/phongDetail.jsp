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
        font-family: Arial, sans-serif;
        margin: 0 200px;
        padding: 0;
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
    left: -200px; /* Dịch header để mở rộng ra khỏi margin của body */
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
    left: -200px; /* Dịch header để mở rộng ra khỏi margin của body */
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
        .button-container {
            display: flex;
            justify-content: space-between;
            margin-bottom: 20px;
        }
        table {
        width: 100%;
        border-collapse: collapse;
    }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        .room-info {
            margin-top: 20px;
        }
        .room-info label {
            font-weight: bold;
            color: #555;
        }
        .room-info p {
            margin: 5px 0 15px;
        }
        .amenities {
            margin-top: 20px;
        }
        .amenities ul {
            list-style: none;
            padding: 0;
        }
        .amenities ul li {
            margin: 5px 0;
            padding: 5px;
            background: #f2f2f2;
            border-radius: 4px;
        }
        .actions {
            text-align: center;
            margin-top: 20px;
        }
        .actions a {
            text-decoration: none;
            padding: 10px 20px;
            margin: 5px;
            color: #fff;
            background: #4CAF50;
            border-radius: 4px;
        }
        .actions a:hover {
            background: #45a049;
        }
        .actions .back {
            background: #f44336;
        }
        .actions .back:hover {
            background: #d32f2f;
        }
    </style>
</head>
<body>
<%
	String currentUser = (String) session.getAttribute("currentUser");
	Room phong = (Room) request.getAttribute("phongDetail");
	%>
	
<header>
     <h1><a href="${pageContext.request.contextPath}/CustomerController?action=listPhong" style="color: white; text-decoration: none;">HotelBooking</a></h1>
    <div class="nav-links">
   		<% if (currentUser != null) { %>
            <p style="margin: 0">Xin chào, <%= currentUser %>!</p>
            <a href="${pageContext.request.contextPath}/CustomerController?action=logout">Đăng xuất</a>
        <% } else { %>
            <a href="${pageContext.request.contextPath}/customerjsp/login.jsp">Đăng nhập</a>
        	<a href="${pageContext.request.contextPath}/customerjsp/register.jsp">Đăng ký</a>
        <% } %>
        
    </div>
</header>
		<nav class="sub-menu">
        	<a href="${pageContext.request.contextPath}/CustomerController?action=listPhong" style="border-right: 1px solid white;">Trang chủ</a>
            <a href="${pageContext.request.contextPath}/CustomerController?action=lichsu" style="border-right: 1px solid white;">Xem lịch sử booking</a>
            <a href="${pageContext.request.contextPath}/CustomerController?action=updateProfile">Cập nhật hồ sơ</a>
        </nav>
    <div class="container">
        <h1>Thông Tin Phòng</h1>
        <div class="room-info">
            <label>Tên Phòng:</label>
            <p><%= phong.getName() %></p>

            <label>Giá Phòng:</label>
            <p><%= phong.getPrice() %> VND</p>

            <label>Trạng Thái:</label>
            <p><%= phong.getStatus() %></p>
        </div>

        <div class="actions">
            <a href="${pageContext.request.contextPath}/CustomerController?action=datPhong&id=<%=phong.getId()%>"">Đặt Phòng</a>
            <a href="javascript:history.back()" class="back">Quay Lại</a>
        </div>
    </div>
</body>
</html>