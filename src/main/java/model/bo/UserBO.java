package model.bo;

import model.bean.User;
import model.dao.UserDAO;

import java.sql.SQLException;
import java.util.List;

public class UserBO {
	public UserDAO userDAO;

	public UserBO() throws SQLException, ClassNotFoundException {
		userDAO = new UserDAO();
	}

	public boolean login(String username, String password) {
		return userDAO.login(username, password);
	}

	public boolean register(User user) {
		return userDAO.register(user);
	}

	// Lấy danh sách tất cả người dùng
	public List<User> getAllUsers() {
		System.out.println("cc");
		return userDAO.getAllUsers();
	}

	// Lấy chi tiết thông tin người dùng
	public User getUserDetail(String username) {
		return userDAO.getUserDetail(username);
	}

	//Them nguoi dung
	public User addUser (User user) throws SQLException {
		return userDAO.addUser(user);
	}
	//cap nhat nguoi dung
	public int updateUser(User user) throws SQLException {
		return userDAO.updateUser(user);
	}

	//Xoa nguoi dung
	public int deleteUser(String username) throws SQLException {
		return userDAO.deleteUser(username);
	}
	public boolean update(User user, boolean updatePass) {
	    return userDAO.update(user, updatePass);
	}

}
