package model.bo;

import model.bean.Booking;
import model.bean.Room;
import model.bean.User;
import model.dao.RoomDAO;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class RoomBO {
	private RoomDAO roomDAO;

	public RoomBO() throws SQLException, ClassNotFoundException {
		roomDAO = new RoomDAO();
	}

	// Lấy danh sách tất cả phòng
	public List<Room> getAllRooms() {
		return roomDAO.getAllRoom();
	}

	// Lấy chi tiết phòng
	public Room getRoomDetail(int roomId) {
		return roomDAO.getRoomDetail(roomId);
	}

	//Them phong
	public Room addRoom (Room room) throws SQLException {
		return roomDAO.addRoom(room);
	}
	//cap nhat nguoi dung
	public int updateRoom(Room room) throws SQLException {
		return roomDAO.updateRoom(room);
	}

	//Xoa nguoi dung
	public int deleteRoom(int id) throws SQLException {
		return roomDAO.deleteRoom(id);
	}
	public boolean isRoomAvailable(int idp, Timestamp ngaydatTimestamp, Timestamp ngaytraTimestamp) {
	    return roomDAO.isRoomAvailable(idp, ngaydatTimestamp, ngaytraTimestamp);
	}
	public boolean addBooking(Booking booking) {
	    return roomDAO.addBooking(booking);
	}
	public List<Booking> getLichsu(String username) {
		return roomDAO.getLichsu(username);
	}
}
