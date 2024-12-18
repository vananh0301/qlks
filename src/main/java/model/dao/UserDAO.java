package model.dao;

import model.bean.User;
import model.data.connection;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;

public class UserDAO {
    private connection conn;

    public UserDAO() throws SQLException, ClassNotFoundException {
        conn = new connection();
    }

    // Lấy danh sách tất cả người dùng
    public ArrayList<User> getAllUsers() {
        ArrayList<User> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM user";
            ResultSet rs = conn.executeQuery(sql);
            System.out.println("co2");
            while (rs.next()) {
            	System.out.println("co");
                list.add(new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("hoten"),
                        rs.getString("sdt")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //lay thong tin nguoi dung theo username
    public User getUserDetail(String username) {
        try {
        	System.out.println(username);
            String sql = "SELECT * FROM user WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	System.out.println(username);
                return new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("hoten"),
                        rs.getString("sdt")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Đăng nhập người dùng
    public boolean login(String username, String password) {
        try {
            String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Thêm người dùng mới
    public User addUser(User user) throws SQLException {
        String username = user.getUsername();
        String password = user.getPassword();
        String role = user.getRole();
        String hoten = user.getHoten();
        String sdt = user.getSdt();
        String sql = MessageFormat.format("INSERT INTO user(username, password, role, hoten, sdt) VALUES (''{0}'',''{1}'',''{2}'',''{3}'',''{4}'')",username, password, role, hoten, sdt );
        boolean add = conn.execute(sql);
        return user;
    }

    //cap nhat nguoi dung
    public int updateUser(User user) throws SQLException {
        String username = user.getUsername();
        String password = user.getPassword();
        String role = user.getRole();
        String hoten = user.getHoten();
        String sdt = user.getSdt();
        String sql = MessageFormat.format("UPDATE user SET password=''{0}'',role=''{1}'',hoten=''{2}'',sdt=''{3}'' WHERE username=''{4}''",password, role, hoten, sdt, username );
        return conn.executeUpdate(sql);
    }

    // Xóa người dùng
    public int deleteUser(String username) throws SQLException {
        String sql = MessageFormat.format("delete from user where username = ''{0}''", username);
        return conn.executeUpdate(sql);
    }

    // Đăng ký người dùng
    public boolean register (User ngdung){
        try {
            String sql = "insert into user values (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ngdung.getUsername());
            ps.setString(2, ngdung.getPassword());
            ps.setString(3, ngdung.getRole());
            ps.setString(4, ngdung.getHoten());
            ps.setString(5, ngdung.getSdt());

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            System.out.print(e);
        }
        return false;
    }
    public boolean update(User user, Boolean updatePass) {
		try {
			String sql;
		    if (updatePass) {
		        sql = "update user set hoten = ?, sdt = ?, password = ? where username = ?";
		    } else {
		        sql = "update user set hoten = ?, sdt = ? where username = ?";
		    }
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getHoten());
			ps.setString(2, user.getSdt());
			if (updatePass) {
	            ps.setString(3, user.getPassword());
	            ps.setString(4,  user.getUsername());
	        }
			else {
				ps.setString(3,  user.getUsername());
			}
			int rowsInserted = ps.executeUpdate();
			return rowsInserted > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}

