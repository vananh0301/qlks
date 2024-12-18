package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bo.RoomBO;
import model.bo.UserBO;
import model.bean.Booking;
import model.bean.User;

@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RoomBO roomBO;
	private UserBO userBO;

	@Override
	public void init() throws ServletException {
		try {
			userBO = new UserBO();
			roomBO = new RoomBO();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) {
			action = "listPhong";
		}
		switch (action) {
		case "listPhong":
			show(request, response);
			break;
		case "logout":
			logout(request, response);
			break;
		case "phongDetail":
			int id = Integer.parseInt(request.getParameter("id"));
			showDetail(request, response, id);
			break;
		case "datPhong":
			int id1 = Integer.parseInt(request.getParameter("id"));
			datPhong(request, response, id1);
			break;
		case "lichsu":
			showLichSu(request, response);
			break;
		case "updateProfile":
			showupdateProfile(request, response);
			break;
		default:
			show(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {
		case "login":
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			login(request, response, username, password);
			break;
		case "register":
			String username1 = request.getParameter("username");
			String password1 = request.getParameter("password");
			String ten = request.getParameter("ten");
			String sdt = request.getParameter("sdt");
			register(request, response, username1, password1, ten, sdt);
			break;
		case "addBooking":
			String username2 = request.getParameter("username");
			int idp = Integer.parseInt(request.getParameter("idp"));
			String ngaydat = request.getParameter("ngaydat");
			String ngaytra = request.getParameter("ngaytra");
			addBooking(request, response, idp, username2, ngaydat, ngaytra);
			break;
		case "updateProfile":
			String username3 = request.getParameter("name");
			String sdt3 = request.getParameter("phone");
			String newPassword = request.getParameter("newPassword");
			String confirmPassword = request.getParameter("confirmPassword");
			updateProfile(request, response, username3, sdt3, newPassword, confirmPassword);
			break;
		}
	}

	private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("danhSachPhong", roomBO.getAllRooms());

		request.getRequestDispatcher("/customerjsp/xemthongtinPhong.jsp").forward(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response, String username, String password)
			throws ServletException, IOException {
		boolean isSuccess = userBO.login(username, password);
		if (isSuccess) {
			request.getSession().setAttribute("currentUser", username);
			request.setAttribute("danhSachPhong", roomBO.getAllRooms());
			request.getRequestDispatcher("/customerjsp//xemthongtinPhong.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMessage", "Tài khoản hoặc mật khẩu không đúng.");
			request.getRequestDispatcher("/customerjsp/login.jsp").forward(request, response);
		}
	}

	private void register(HttpServletRequest request, HttpServletResponse response, String username, String password,
			String ten, String sdt) throws ServletException, IOException {
		User ngdung = new User();
		ngdung.setUsername(username);
		ngdung.setPassword(password);
		ngdung.setHoten(ten);
		ngdung.setSdt(sdt);
		ngdung.setRole("customer");
		boolean isSuccess = userBO.register(ngdung);
		if (isSuccess) {
			request.getRequestDispatcher("/customerjsp/login.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMessage", "Đăng ký tài khoản không thành công.");
			request.getRequestDispatcher("/customerjsp/register.jsp").forward(request, response);
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		request.setAttribute("danhSachPhong", roomBO.getAllRooms());
		request.getRequestDispatcher("/customerjsp/xemthongtinPhong.jsp").forward(request, response);
	}

	private void showDetail(HttpServletRequest request, HttpServletResponse response, int id)
			throws ServletException, IOException {
		request.setAttribute("phongDetail", roomBO.getRoomDetail(id));
		request.getRequestDispatcher("/customerjsp/phongDetail.jsp").forward(request, response);
	}

	private void datPhong(HttpServletRequest request, HttpServletResponse response, int id)
			throws ServletException, IOException {

		String currentUser = (String) request.getSession().getAttribute("currentUser");
		if (currentUser == null) {
			request.getRequestDispatcher("/customerjsp/login.jsp").forward(request, response);
		} else {
//			request.setAttribute("idPhong", id);
//			request.setAttribute("phongDetail", roomBO.getRoomDetail(id));
			request.setAttribute("phongDetail", roomBO.getRoomDetail(id));
			request.setAttribute("user", userBO.getUserDetail(currentUser));
			request.getRequestDispatcher("/customerjsp/datPhong.jsp").forward(request, response);
		}

	}

	private void addBooking(HttpServletRequest request, HttpServletResponse response, int idp, String username,
			String ngaydat, String ngaytra) throws ServletException, IOException {
		String currentUser = (String) request.getSession().getAttribute("currentUser");

		ngaydat += ":00";
		ngaytra += ":00";
		Timestamp ngaydatTimestamp = Timestamp.valueOf(ngaydat.replace("T", " "));
		Timestamp ngaytraTimestamp = Timestamp.valueOf(ngaytra.replace("T", " "));
		System.out.println(ngaydatTimestamp);
		if (ngaydatTimestamp.after(ngaytraTimestamp)) {
			request.setAttribute("errorMessage", "Ngày đặt phải trước ngày trả!");
//		    request.setAttribute("idPhong", idp);
			request.setAttribute("phongDetail", roomBO.getRoomDetail(idp));
			request.setAttribute("user", userBO.getUserDetail(currentUser));
			request.getRequestDispatcher("/customerjsp/datPhong.jsp").forward(request, response);
			return;
		}
		if (ngaydatTimestamp.before(new Timestamp(System.currentTimeMillis()))) {
			request.setAttribute("errorMessage", "Ngày đặt phải là tương lai!");
//		    request.setAttribute("idPhong", idp);
			request.setAttribute("phongDetail", roomBO.getRoomDetail(idp));
			request.setAttribute("user", userBO.getUserDetail(currentUser));
			request.getRequestDispatcher("/customerjsp/datPhong.jsp").forward(request, response);
			return;
		}
		// Kiểm tra phòng trống
		if (!roomBO.isRoomAvailable(idp, ngaydatTimestamp, ngaytraTimestamp)) {
			request.setAttribute("errorMessage", "Phòng này đã có người đặt trong khoảng thời gian yêu cầu!");
//	        request.setAttribute("idPhong", idp);
			request.setAttribute("phongDetail", roomBO.getRoomDetail(idp));
			request.setAttribute("user", userBO.getUserDetail(currentUser));
			request.getRequestDispatcher("/customerjsp/datPhong.jsp").forward(request, response);
			return;
		}
		Booking booking = new Booking();
		booking.setUsername(username);
		booking.setRoomid(idp);
		booking.setNgaydat(ngaydatTimestamp);
		booking.setNgaytra(ngaytraTimestamp);
		boolean isSuccess = roomBO.addBooking(booking);
		if (isSuccess) {
			request.setAttribute("lichSuBooking", roomBO.getLichsu(username));
			request.getRequestDispatcher("/customerjsp/xemlichsubooking.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMessage", "Đặt phòng thất bại!");
//			request.setAttribute("idPhong", idp);
			request.setAttribute("user", userBO.getUserDetail(currentUser));
			request.setAttribute("phongDetail", roomBO.getRoomDetail(idp));
			request.getRequestDispatcher("/customerjsp/datPhong.jsp").forward(request, response);
		}

	}

	private void showLichSu(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String currentUser = (String) request.getSession().getAttribute("currentUser");
		if (currentUser == null) {
			request.getRequestDispatcher("/customerjsp/login.jsp").forward(request, response);
		} else {
			request.setAttribute("lichSuBooking", roomBO.getLichsu(currentUser));
			request.getRequestDispatcher("/customerjsp/xemlichsubooking.jsp").forward(request, response);
		}

	}

	private void showupdateProfile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String currentUser = (String) request.getSession().getAttribute("currentUser");
		if (currentUser == null) {
			request.getRequestDispatcher("/customerjsp/login.jsp").forward(request, response);
		} else {
			request.setAttribute("user", userBO.getUserDetail(currentUser));
			request.getRequestDispatcher("/customerjsp/updateProfile.jsp").forward(request, response);
		}

	}

	private void updateProfile(HttpServletRequest request, HttpServletResponse response, String name, String sdt,
			String newPassword, String confirmPassword) throws ServletException, IOException {
		String message = "";
		String currentUser = (String) request.getSession().getAttribute("currentUser");
		User user = new User();
		user.setUsername(currentUser);
		user.setHoten(name);
		user.setSdt(sdt);

		boolean updatePassword = false;
		
		if (newPassword!="" && confirmPassword=="") {
			request.setAttribute("error", "Nhập mật khẩu xác nhận!");
			request.setAttribute("user", userBO.getUserDetail(currentUser));
			request.getRequestDispatcher("/customerjsp/updateProfile.jsp").forward(request, response);
			return;
		}
		if (newPassword=="" && confirmPassword!="") {
			request.setAttribute("error", "Chưa nhập mật khẩu mới!");
			request.setAttribute("user", userBO.getUserDetail(currentUser));
			request.getRequestDispatcher("/customerjsp/updateProfile.jsp").forward(request, response);
			return;
		}
		
		if (newPassword!="" && confirmPassword!="") {

			if (newPassword.equals(confirmPassword)) {
				user.setPassword(confirmPassword);
				updatePassword = true;
			} else {
				request.setAttribute("error", "Mật khẩu mới và xác nhận không khớp!");
				request.setAttribute("user", userBO.getUserDetail(currentUser));
				request.getRequestDispatcher("/customerjsp/updateProfile.jsp").forward(request, response);
				return;
			}
		}

		boolean isSuccess = userBO.update(user, updatePassword);

		if (isSuccess) {
			message = "Cập nhật thông tin thành công!";
		} else {
			message = "Cập nhật thông tin thất bại. Vui lòng thử lại!";
		}
		request.setAttribute("message", message);
		request.setAttribute("user", userBO.getUserDetail(currentUser));
		request.getRequestDispatcher("/customerjsp/updateProfile.jsp").forward(request, response);
	}
}
