package model.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Booking {
	private int id;
	private String username;
	private int roomid;
	private Timestamp ngaydat;
	private Timestamp ngaytra;
	private Timestamp ngaycheckin;
	private Timestamp ngaycheckout;
	private String status;
	private BigDecimal tongtien;

	public Booking() {	}

	public Booking(String username, int roomid, Timestamp ngaydat, Timestamp ngaytra, String status) {
		this.username = username;
		this.roomid = roomid;
		this.ngaydat = ngaydat;
		this.ngaytra = ngaytra;
		this.ngaycheckin = null;
		this.ngaycheckout = null;
		this.status = status;
		this.tongtien = BigDecimal.ZERO;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Timestamp getNgaydat() {
		return ngaydat;
	}
	public void setNgaydat(Timestamp ngaydat) {
		this.ngaydat = ngaydat;
	}
	public int getRoomid() {
		return roomid;
	}
	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}
	public Timestamp getNgaytra() {
		return ngaytra;
	}
	public void setNgaytra(Timestamp ngaytra) {
		this.ngaytra = ngaytra;
	}
	public Timestamp getNgaycheckin() {
		return ngaycheckin;
	}
	public void setNgaycheckin(Timestamp ngaycheckin) {
		this.ngaycheckin = ngaycheckin;
	}
	public Timestamp getNgaycheckout() {
		return ngaycheckout;
	}
	public void setNgaycheckout(Timestamp ngaycheckout) {
		this.ngaycheckout = ngaycheckout;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getTongtien() {
		return tongtien;
	}
	public void setTongtien(BigDecimal tongtien) {
		this.tongtien = tongtien;
	}

}
