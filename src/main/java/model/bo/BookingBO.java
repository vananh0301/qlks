package model.bo;

import model.bean.Booking;
import model.dao.BookingDAO;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class BookingBO {
    private BookingDAO bookingDAO;

    public BookingBO() throws SQLException, ClassNotFoundException {
        bookingDAO = new BookingDAO();
    }

    // Thêm đặt phòng mới
    public Booking addBooking(Booking booking) throws SQLException {
        return bookingDAO.addBooking(booking);
    }

    // Kiểm tra phòng có trống trong thời gian cụ thể không
    public boolean isRoomAvailable(int roomId, Timestamp ngayDat, Timestamp ngayTra) {
        return bookingDAO.isRoomAvailable(roomId, ngayDat, ngayTra);
    }

    // Lấy danh sách tất cả đặt phòng
    public List<Booking> getAllBookings() {
        return bookingDAO.getAllBookings();
    }
}