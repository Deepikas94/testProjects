package com.deepika.testProjects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class BookTaxi {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int taxiId;
	private long distance;
	private long revenue;
	private String pickPoint;
	private String dropPoint;
	private int endTime;
	private int startTime;
	
	public BookTaxi() {}
	
	public BookTaxi(int taxiId, long distance, long revenue, String pickPoint, String dropPoint, int endTime, int startTime) {
		super();

	
		this.taxiId = taxiId;
		this.distance = distance;
		this.revenue = revenue;
		this.pickPoint = pickPoint;
		this.dropPoint = dropPoint;
		this.endTime = endTime;
		this.startTime=startTime;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTaxiId() {
		return taxiId;
	}
	public void setTaxiId(int taxiId) {
		this.taxiId = taxiId;
	}
	public long getDistance() {
		return distance;
	}
	public void setDistance(long distance) {
		this.distance = distance;
	}
	public long getRevenue() {
		return revenue;
	}
	public void setRevenue(long revenue) {
		this.revenue = revenue;
	}
	public String getDropPoint() {
		return dropPoint;
	}
	public void setDropPoin(String dropPoint) {
		this.dropPoint = dropPoint;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public String getPickPoint() {
		return pickPoint;
	}

	public void setPickPoint(String pickPoint) {
		this.pickPoint = pickPoint;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	
	
	
	
	
}
