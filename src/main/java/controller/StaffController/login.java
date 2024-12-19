package controller.StaffController;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bo.RoomBO;
import model.bo.UserBO;

@WebServlet("/StaffController/login")
public class login extends HttpServlet{
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
		switch (action) {
		case "logout_admin":
			logout_admin(request, response);
			break;
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {
		case "login_admin":
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			login_admin(request, response, username, password);
			break;
		}
	}
	private void login_admin(HttpServletRequest request, HttpServletResponse response, String username, String password)
			throws ServletException, IOException {
		boolean isSuccess = userBO.login_admin(username, password);
		if (isSuccess) {
			request.getSession().setAttribute("currentAdmin", username);
			request.getRequestDispatcher("/staffjsp/dashboard.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMessage", "Tài khoản hoặc mật khẩu không đúng.");
			request.getRequestDispatcher("/staffjsp/login_admin.jsp").forward(request, response);
		}
	}
	private void logout_admin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		request.getRequestDispatcher("/staffjsp/login_admin.jsp").forward(request, response);
	}
}
