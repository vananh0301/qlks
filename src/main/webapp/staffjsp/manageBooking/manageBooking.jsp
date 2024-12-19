<%@page import="model.bean.Booking"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
	rel="stylesheet">
<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f4f4f9;
	margin: 0 200px;
}

.statistics {
	display: flex;
	justify-content: space-around;
	gap: 20px;
	margin-top: 20px;
}

.stat-box {
	flex: 1;
	padding: 20px;
	background-color: #fff;
	border-radius: 8px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	text-align: center;
}

.stat-box h2 {
	font-size: 2em;
	margin: 0;
	color: #007bff;
}

.stat-box p {
	margin: 5px 0;
	color: #555;
}

table {
	width: 100%; /* Đặt chiều rộng của bảng bằng 100% */
	border-collapse: collapse; /* Loại bỏ khoảng cách giữa các đường kẻ */
	margin: 20px 0; /* Khoảng cách giữa bảng và các phần tử xung quanh */
}

th, td {
	padding: 10px; /* Thêm khoảng cách bên trong ô */
	text-align: left; /* Căn trái nội dung trong ô */
	border: 1px solid #ddd; /* Thêm đường viền cho mỗi ô */
}

th {
	background-color: #f2f2f2; /* Màu nền cho tiêu đề bảng */
}

tr:nth-child(odd) {
	background-color: #f9f9f9; /* Màu nền cho các dòng lẻ */
}

tr:nth-child(even) {
	background-color: #e0e0e0; /* Màu nền cho các dòng chẵn */
}
.btn-circle {
    text-decoration: none;
    color: white;
    font-weight: bold;
    display: inline-flex;
    justify-content: center;
    align-items: center;
    border-radius: 50%;
    width: 28px;
    height: 28px;
    font-size: 14px;
    margin: 0 5px;
    transition: background-color 0.3s ease;
}

.btn-circle[title="Checkin"] {
    background-color: #28a745; /* Xanh lá */
}

.btn-circle[title="Checkout"] {
    background-color: #007bff; /* Xanh dương */
}

.btn-circle[title="Cancelled"] {
    background-color: #dc3545; /* Đỏ */
}

.btn-circle:hover {
    opacity: 0.8;
}
.action-buttons {
    display: flex; /* Sử dụng Flexbox để căn chỉnh nút trên 1 hàng */
    gap: 10px; /* Khoảng cách giữa các nút */
    justify-content: center; /* Căn giữa các nút trong ô */
    
}
/* Bố cục cho hai nút đầu trang */
.top-buttons {
    display: flex; /* Sử dụng Flexbox để căn chỉnh nút nằm ngang */
    justify-content: flex-end;
    align-items: center; /* Căn giữa theo chiều dọc */
    padding: 20px 0; /* Thêm khoảng cách bên trong */
}

/* Thiết kế nút */
.top-buttons .btn-custom {
    padding: 8px 16px; /* Khoảng cách bên trong nút */
    font-size: 14px; /* Kích thước chữ */
    color: white; /* Màu chữ */
    background-color: #005cbe; /* Màu nền */
    text-decoration: none; /* Loại bỏ gạch chân */
    border: none; /* Loại bỏ viền */
    border-radius: 5px; /* Bo góc nút */
    transition: background-color 0.3s ease; /* Hiệu ứng hover */
}

.top-buttons .btn-custom:hover {
    background-color: #004a9f; /* Màu nền khi hover */
}

/* Đảm bảo nút luôn căn giữa khi thu nhỏ màn hình */
.top-buttons {
    flex-wrap: wrap; /* Cho phép xuống hàng nếu không đủ chỗ */
}
</style>
</head>
<body>

	<%
    String message = (String) request.getAttribute("message");
    if (message != null) {
	%>
    <script>
        alert("<%= message %>"); // Hiển thị thông báo bằng alert
    </script>
	<%
    }
	%>
	<%
	String currentAdmin = (String) session.getAttribute("currentAdmin");
	List<Booking> danhSachBooking = (List<Booking>) request.getAttribute("lichSuBooking");
	if (currentAdmin == null) {
        // Nếu chưa đăng nhập, chuyển hướng đến trang đăng nhập
        response.sendRedirect("login_admin.jsp");
        return;
    }
	%>
<div class="top-buttons">
    <a href="${pageContext.request.contextPath}/StaffController/booking?action=find" class="btn btn-create btn-custom" style="margin-right: 10px">Tìm kiếm</a>
    <a href="${pageContext.request.contextPath}/staffjsp/dashboard.jsp" class="btn btn-back btn-custom">Trở về trang chủ</a>
  </div>
		<table>
			<caption style="text-transform: uppercase; font-size: 20px; font-weight: bold; margin-bottom: 20px">Danh sách đặt phòng của khách sạn</caption>
			<thead>
				<tr>
					<th>ID</th>
					<th>Tên phòng</th>
					<th>Username</th>
					<th>Ngày đặt</th>
					<th>Ngày trả</th>
					<th>Ngày checkin</th>
					<th>Ngày checkout</th>
					<th>Trạng thái</th>
					<th>Tổng tiền</th>
					<th>Thao tác</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (Booking booking : danhSachBooking) {
				%>
				<tr>
					<td><%=booking.getId()%></td>
					<td><%=booking.getRoomid()%></td>
					<td><%=booking.getUsername()%></td>
					<td><%=booking.getNgaydat()%></td>
					<td><%=booking.getNgaytra()%></td>
					<td><%=booking.getNgaycheckin()%></td>
					<td><%=booking.getNgaycheckout()%></td>
					<td><%=booking.getStatus()%></td>
					<td><%=booking.getTongtien()%></td>
					<td style="display: flex; border-bottom: none; justify-content: center;">
    					<!-- Nút Checkin -->
    					<a href="${pageContext.request.contextPath}/StaffController/booking?action=checkin&id=<%=booking.getId()%>" class="btn-circle" title="Checkin" 
       						<% if (!"booked".equals(booking.getStatus())) { %> 
           							style="pointer-events: none; opacity: 0.5;" 
      						<% } %>
       						onclick="return confirm('Bạn có chắc chắn muốn checkin phòng này không?');">I
       					</a>

    					<!-- Nút Checkout -->
    					<a href="${pageContext.request.contextPath}/StaffController/booking?action=checkout&id=<%=booking.getId()%>" class="btn-circle" title="Checkout" 
       						<% if (!"checked_in".equals(booking.getStatus())) { %> 
           						style="pointer-events: none; opacity: 0.5;" 
       						<% } %>
       						onclick="return confirm('Bạn có chắc chắn muốn checkout phòng này không?');">O
       					</a> 

    					<!-- Nút Cancelled -->
					    <a href="${pageContext.request.contextPath}/StaffController/booking?action=cancelled&id=<%=booking.getId()%>" class="btn-circle" title="Cancelled" 
					       <% if (!"booked".equals(booking.getStatus())) { %> 
					           style="pointer-events: none; opacity: 0.5;" 
					       <% } %>
					       onclick="return confirm('Bạn có chắc chắn muốn hủy đặt phòng này không?');">X
					    </a>
					</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>

</body>
</html>