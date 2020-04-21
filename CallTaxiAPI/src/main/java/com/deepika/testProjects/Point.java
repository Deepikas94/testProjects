package com.deepika.testProjects;

public class Point {

	private int time;
	private long distance;
	
	public Point () {}
	public Point(int time, long distance) {
		super();
		this.time = time;
		this.distance = distance;
	}
	
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public long getDistance() {
		return distance;
	}
	public void setDistance(long distance) {
		this.distance = distance;
	}
}
