<%@page import="model.bean.Room"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
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
    table caption {
        font-size: 1.5em;
        margin-bottom: 10px;
    }
    th, td {
        padding: 10px;
        text-align: left;
    }
    th {
        background-color: #f2f2f2;
    }
    tr:nth-child(odd) {
        background-color: #f9f9f9; /* Màu cho dòng lẻ */
    }
    tr:nth-child(even) {
        background-color: #e0e0e0; /* Màu cho dòng chẵn */
    }
    tr {
        border-bottom: 1px solid #ddd; /* Đường kẻ dưới mỗi dòng */
    }
    td a {
        text-decoration: none;
        color: #007BFF;
    }
    td a:hover {
        color: #0056b3;
    }
    /* In đậm cột đầu tiên (ID) */
    td:first-child, th:first-child {
        font-weight: bold;
    }
    </style>
</head>


<body>
<%
	String currentUser = (String) session.getAttribute("currentUser");
	List<Room> danhSachPhong = (List<Room>) request.getAttribute("danhSachPhong");
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
	
	<table>
		<caption>Danh sách phòng của khách sạn</caption>
		<thead>
			<tr>
				<th>ID</th>
				<th>Tên phòng</th>
				<th>Giá phòng</th>
				<th>Trạng thái</th>
				<th>Xem phòng</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (Room phong : danhSachPhong) {
			%>
			<tr>
				<td><%=phong.getId() %></td>
				<td><%=phong.getName() %></td>
				<td><%=phong.getPrice() %></td>
				<td><%=phong.getStatus() %></td>
				<td>
					<!-- <a href="${pageContext.request.contextPath}/CustomerController?action=phongDetail&id=<%=phong.getId()%>"><i class="fas fa-eye"></i></a>  -->
					<% if (phong.getStatus().equals("occupied") || phong.getStatus().equals("maintenance")) { %>
                		<a href="#" onclick="alert('Phòng hiện không thể xem và đặt!'); return false;">
                    		<i class="fas fa-eye" style="color: grey; cursor: not-allowed;"></i>
                		</a>
            		<% } else { %>
                		<!-- Phòng khả dụng: cho phép xem chi tiết -->
                		<a href="${pageContext.request.contextPath}/CustomerController?action=phongDetail&id=<%=phong.getId()%>">
                    		<i class="fas fa-eye"></i>
                		</a>
            		<% } %>
				</td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
</body>
</html>