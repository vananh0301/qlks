package model.dao;

import model.bean.Booking;
import model.data.connection;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
	private connection conn;

	public BookingDAO() throws SQLException, ClassNotFoundException {
		conn = new connection();
	}

	// Thêm đặt phòng mới
	public Booking addBooking(Booking booking) throws SQLException {
		String sql = "insert into booking (username, room_id, ngaydat, ngaytra, total_price) values (?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, booking.getUsername());
		ps.setInt(2, booking.getRoomid());
		ps.setTimestamp(3, booking.getNgaydat());
		ps.setTimestamp(4, booking.getNgaytra());
		ps.setBigDecimal(5, new BigDecimal("500000"));
		int rowsInserted = ps.executeUpdate();
		return booking;
	}

	// Lấy danh sách đặt phòng
	public List<Booking> getAllBookings() {
		List<Booking> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM booking";
			ResultSet rs = conn.executeQuery(sql);
			while (rs.next()) {
				Booking booking = new Booking();
				booking.setId(rs.getInt("id"));
				booking.setRoomid(rs.getInt("room_id"));
				booking.setUsername(rs.getString("username"));
				booking.setNgaydat(rs.getTimestamp("ngaydat"));
				booking.setNgaytra(rs.getTimestamp("ngaytra"));
				booking.setNgaycheckin(rs.getTimestamp("ngayvaothuc"));
				booking.setNgaycheckout(rs.getTimestamp("ngayrathuc"));
				booking.setStatus(rs.getString("status"));
				booking.setTongtien(rs.getBigDecimal("total_price"));
				list.add(booking);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// Kiểm tra phòng trống
	public boolean isRoomAvailable(int roomId, Timestamp ngayDat, Timestamp ngayTra) {
		try {
			String sql = "SELECT COUNT(*) FROM booking " + "WHERE room_id = ? " + "AND (ngaydat < ? AND ngaytra > ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, roomId);
			ps.setTimestamp(2, ngayTra);
			ps.setTimestamp(3, ngayDat);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) == 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean cancel(int id) {
		try {
			String sql = "update booking set status = 'cancelled' where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int rowsAffected = ps.executeUpdate();

			return rowsAffected > 0;
		} catch (Exception e) {
			System.out.print(e);
		}
		return false;

	}

	public boolean checkin(int id) {
		try {
			LocalDateTime now = LocalDateTime.now();
			String formattedDateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

			String selectSql = "SELECT ngaydat, ngaytra FROM booking WHERE id = ?";
			PreparedStatement selectPs = conn.prepareStatement(selectSql);
			selectPs.setInt(1, id);
			ResultSet rs = selectPs.executeQuery();

			if (rs.next()) {
				LocalDateTime ngayvao = rs.getTimestamp("ngaydat").toLocalDateTime();
				LocalDateTime ngayra = rs.getTimestamp("ngaytra").toLocalDateTime();

				// Kiểm tra thời gian hiện tại có nằm trong khoảng ngayvao và ngayra không
				if (now.isBefore(ngayvao) || now.isAfter(ngayra)) {
					System.out.println("Thời gian check-in không hợp lệ! Phải nằm trong khoảng thời gian đặt phòng.");
					return false;
				}
			}
			String sql = "update booking set status = 'checked_in', ngayvaothuc = ? where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, formattedDateTime);
			ps.setInt(2, id);
			int rowsAffected = ps.executeUpdate();

			return rowsAffected > 0;
		} catch (Exception e) {
			System.out.print(e);
		}
		return false;

	}

	public boolean checkout(int id) {
		try {
			LocalDateTime now = LocalDateTime.now();
			String formattedDateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

			String query = "SELECT ngaytra, total_price, status FROM booking WHERE id = ?";
			PreparedStatement ps1 = conn.prepareStatement(query);
			ps1.setInt(1, id);

			ResultSet rs = ps1.executeQuery();

			if (rs.next()) {
				Timestamp ngayTraPhong = rs.getTimestamp("ngaytra");
				BigDecimal basePrice = rs.getBigDecimal("total_price");
				String status = rs.getString("status");
				BigDecimal finePerDay = new BigDecimal("1000000");
				
				// nếu chưa checkin thì không được checkout
				if (!status.equals("checked_in")) {
					return false;
				}
				LocalDate ngayTraPhongDate = ngayTraPhong.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate nowDate = LocalDate.now();

				// Tính số ngày bị muộn
				long daysLate = ChronoUnit.DAYS.between(ngayTraPhongDate, nowDate);
				BigDecimal totalPrice;

				if (daysLate > 0) {
					// Nếu bị muộn, cộng thêm tiền phạt
					totalPrice = basePrice.add(finePerDay.multiply(new BigDecimal(daysLate)));
				} else {
					totalPrice = basePrice;
				}

				String sql = "update booking set status = 'checked_out', ngayrathuc = ?, total_price = ? where id = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, formattedDateTime);
				ps.setBigDecimal(2, totalPrice);
				ps.setInt(3, id);
				int rowsAffected = ps.executeUpdate();

				return rowsAffected > 0;
			}
		} catch (Exception e) {
			System.out.print(e);
		}
		return false;
	}
	public List<Booking> getSearch(String keyword, String searchBy) {
		List<Booking> list = new ArrayList<>();
		try {
			Statement sm = conn.createStatement();
			String sql = "select * from booking where ";
			switch (searchBy) {
            case "id":
                sql += "id = '" + keyword + "'";
                break;
            case "ten":
                sql += "username = '" + keyword +"'";
                break;
            case "phong":
                sql += "room_id = '" + keyword +"'";
                break;
            default:
                throw new IllegalArgumentException("Tiêu chí tìm kiếm không hợp lệ.");
        }
			ResultSet rs = sm.executeQuery(sql);
			
			while (rs.next()) {
				Booking booking = new Booking();
				booking.setId(rs.getInt("id"));
				booking.setUsername(rs.getString("username"));
				booking.setRoomid(rs.getInt("room_id"));
				booking.setNgaydat(rs.getTimestamp("ngaydat"));
				booking.setNgaytra(rs.getTimestamp("ngaytra"));
				booking.setNgaycheckin(rs.getTimestamp("ngayvaothuc"));
				booking.setNgaycheckout(rs.getTimestamp("ngayrathuc"));
				booking.setStatus(rs.getString("status"));
				booking.setTongtien(rs.getBigDecimal("total_price"));
				list.add(booking);
			}

		} catch (Exception e) {
			System.out.print(e);
		}
		return list;
	}
}
