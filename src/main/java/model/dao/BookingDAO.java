package model.dao;

import model.bean.Booking;
import model.data.connection;

import java.math.BigDecimal;
import java.sql.*;
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
                booking.setUsername(rs.getString("username"));
                booking.setRoomid(rs.getInt("room_id"));
                booking.setNgaydat(rs.getTimestamp("ngaydat"));
                booking.setNgaytra(rs.getTimestamp("ngaytra"));
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
            String sql = "SELECT COUNT(*) FROM booking "
                    + "WHERE room_id = ? "
                    + "AND (ngaydat < ? AND ngaytra > ?)";
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
}
