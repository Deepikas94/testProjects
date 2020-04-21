package com.deepika.testProjects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class User {

	@Id
	private int userId;
	private String pickup;
	private String drop;
	private long charges;
	@OneToOne
	private BookTaxi taxi;
	public User() {}
	public User(int userId, String pickup, String drop, long charges, BookTaxi taxi) {
		super();
		this.userId = userId;
		this.pickup = pickup;
		this.drop = drop;
		this.charges = charges;
		this.taxi = taxi;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPickup() {
		return pickup;
	}

	public void setPickup(String pickup) {
		this.pickup = pickup;
	}

	public String getDrop() {
		return drop;
	}

	public void setDrop(String drop) {
		this.drop = drop;
	}

	public long getCharges() {
		return charges;
	}

	public void setCharges(long charges) {
		this.charges = charges;
	}
	public BookTaxi getTaxi() {
		return taxi;
	}
	public void setTaxi(BookTaxi taxi) {
		this.taxi = taxi;
	}
	
	
}
