package model.dao;

import model.bean.Booking;
import model.bean.Room;
import model.bean.User;
import model.data.connection;

import java.math.BigDecimal;
import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
	private connection conn;

	public RoomDAO() throws SQLException, ClassNotFoundException {
		conn = new connection();
	}

	// Lấy danh sách tất cả các phòng
	public List<Room> getAllRoom() {
		List<Room> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM room";
			ResultSet rs = conn.executeQuery(sql);
			while (rs.next()) {
				list.add(new Room(
						rs.getInt("IDPB"),
						rs.getString("Tenphong"),
						rs.getBigDecimal("price"),
						rs.getString("status")
				));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// Lấy chi tiết phòng dựa vào ID
	public Room getRoomDetail(int id) {
		Room room = null;
		try {
			String sql = "SELECT * FROM room WHERE IDPB = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				room = new Room();
				room.setId(rs.getInt("IDPB"));
				room.setName(rs.getString("Tenphong"));
				room.setPrice(rs.getBigDecimal("price"));
				room.setStatus(rs.getString("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return room;
	}

	//Thêm phòng
	public Room addRoom(Room room) throws SQLException {
		int id = room.getId();
		String name = room.getName();
		BigDecimal price = room.getPrice();
		String status = room.getStatus();
		String sql = MessageFormat.format("INSERT INTO room(id, tenphong, price, status) VALUES (''{0}'',''{1}'',''{2}'',''{3}'',''{4}'')",id, name, price, status );
		boolean add = conn.execute(sql);
		return room;
	}


	//cap nhat phong
	public int updateRoom(Room room) throws SQLException {
		int id = room.getId();
		String name = room.getName();
		BigDecimal price = room.getPrice();
		String status = room.getStatus();
		String sql = MessageFormat.format("UPDATE room SET tenphong=''{0}'',price=''{1}'',status=''{2}'' WHERE username=''{4}''",name, price, status, id );
		return conn.executeUpdate(sql);
	}

	// Xóa người dùng
	public int deleteRoom(int id) throws SQLException {
		String sql = MessageFormat.format("delete from room where id = ''{0}''", id);
		return conn.executeUpdate(sql);
	}
	public boolean isRoomAvailable(int idp, Timestamp ngaydatTimestamp, Timestamp ngaytraTimestamp) {
		try {
			String sql = "SELECT COUNT(*) FROM booking " + "WHERE room_id = ? "
					+ "AND (status = 'checked_in' or status = 'booked' or status = 'maintenance') "
					+ "AND (ngaydat < ? AND ngaytra > ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idp);
			ps.setTimestamp(2, ngaytraTimestamp);
			ps.setTimestamp(3, ngaydatTimestamp);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1); // Lấy giá trị COUNT(*)
				return count == 0; // Nếu không có bản ghi nào trùng, phòng trống
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean addBooking(Booking booking) {
		try {
			String roomPriceQuery = "SELECT price FROM room WHERE IDPB = ?";
			PreparedStatement roomPriceStmt = conn.prepareStatement(roomPriceQuery);
			roomPriceStmt.setInt(1, booking.getRoomid());
			ResultSet rs = roomPriceStmt.executeQuery();

			if (!rs.next()) {
				return false;
			}

			BigDecimal pricePerDay = rs.getBigDecimal("price");

			// Tính số ngày ở
			long milliseconds = booking.getNgaytra().getTime() - booking.getNgaydat().getTime();
			long days = milliseconds / (1000 * 60 * 60 * 24); // Chuyển đổi từ mili giây sang ngày
			if (days < 1)
				days = 1; // Đảm bảo ít nhất 1 ngày

			BigDecimal totalPrice = pricePerDay.multiply(BigDecimal.valueOf(days));

			String sql = "insert into booking (username, room_id, ngaydat, ngaytra, total_price) values (?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, booking.getUsername());
			ps.setInt(2, booking.getRoomid());
			ps.setTimestamp(3, booking.getNgaydat());
			ps.setTimestamp(4, booking.getNgaytra());
			ps.setBigDecimal(5, totalPrice);
			int rowsInserted = ps.executeUpdate();
			return rowsInserted > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public List<Booking> getLichsu(String username) {
		List<Booking> list = new ArrayList<>();
		try {
			Statement sm = conn.createStatement();
			String sql = "select * from booking where username = '" + username + "'";
			ResultSet rs = sm.executeQuery(sql);
			while (rs.next()) {
				Booking booking = new Booking();
				booking.setId(rs.getInt("id"));
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
