package model.bean;

import java.math.BigDecimal;

public class Room {
	private int id;
	private String name;
	private BigDecimal price;
	private String status;

	public Room() {}

    public Room(int idpb, String tenphong, BigDecimal price, String status) {
		this.id = idpb;
		this.name = tenphong;
		this.price = price;
		this.status = status;
    }

    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
