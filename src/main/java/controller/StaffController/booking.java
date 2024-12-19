package controller.StaffController;

import java.io.IOException;
import java.rmi.ServerException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Booking;
import model.bo.BookingBO;
import model.bo.RoomBO;

@WebServlet("/StaffController/booking")
public class booking extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RoomBO roomBO;
	private BookingBO bookingBO;

	@Override
	public void init() throws ServletException {
		try {
			roomBO = new RoomBO();
			bookingBO = new BookingBO();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServerException, IOException, ServletException {
		String action = request.getParameter("action");
		switch (action) {
		case "manageBooking":
			manageBooking(request, response);
			break;
		case "cancelled":
			int id2 = Integer.parseInt(request.getParameter("id"));
			cancelBooking(request, response, id2);
			break;
		case "checkin":
			int id3 = Integer.parseInt(request.getParameter("id"));
			checkinBooking(request, response, id3);
			break;
		case "checkout":
			int id4 = Integer.parseInt(request.getParameter("id"));
			checkoutBooking(request, response, id4);
			break;
		case "find":
			find(request, response);
			break;
		case "search":
			String keyword = request.getParameter("keyword"); // Từ khóa tìm kiếm
			String searchBy = request.getParameter("searchBy");
			search(request, response, keyword, searchBy);
			break;
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServerException, IOException, ServletException {
		
	}
	private void manageBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("lichSuBooking", bookingBO.getAllBookings());
		request.getRequestDispatcher("/staffjsp/manageBooking/manageBooking.jsp").forward(request, response);
	}
	private void cancelBooking(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
		boolean isSuccess = bookingBO.cancel(id);
		String message;
		if (isSuccess) {
			message = "Hủy đặt phòng thành công!";
		} else {
			message = "Hủy đặt phòng thất bại. Vui lòng thử lại!";
		}
		request.setAttribute("message", message);
		request.setAttribute("lichSuBooking", bookingBO.getAllBookings());
		request.getRequestDispatcher("/staffjsp/manageBooking/manageBooking.jsp").forward(request, response);
		
	}
	private void checkinBooking(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
		boolean isSuccess = bookingBO.checkin(id);
		String message;
		if (isSuccess) {
			message = "Checkin phòng thành công!";
		} else {
			message = "Checkin phòng thất bại. Vui lòng thử lại!";
		}
		request.setAttribute("message", message);
		request.setAttribute("lichSuBooking", bookingBO.getAllBookings());
		request.getRequestDispatcher("/staffjsp/manageBooking/manageBooking.jsp").forward(request, response);
		
	}
	private void checkoutBooking(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
		boolean isSuccess = bookingBO.checkout(id);
		String message;
		if (isSuccess) {
			message = "Checkout phòng thành công!";
		} else {
			message = "Checkout phòng thất bại. Vui lòng thử lại!";
		}
		request.setAttribute("message", message);
		request.setAttribute("lichSuBooking", bookingBO.getAllBookings());
		request.getRequestDispatcher("/staffjsp/manageBooking/manageBooking.jsp").forward(request, response);
		
	}
	private void find(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/staffjsp/manageBooking/search.jsp").forward(request, response);
	}
	private void search(HttpServletRequest request, HttpServletResponse response, String keyword, String searchBy)
			throws ServletException, IOException {
		request.setAttribute("lichSuBooking", bookingBO.search(keyword, searchBy));
		request.getRequestDispatcher("/staffjsp/manageBooking/manageBooking.jsp").forward(request, response);
	}
}
