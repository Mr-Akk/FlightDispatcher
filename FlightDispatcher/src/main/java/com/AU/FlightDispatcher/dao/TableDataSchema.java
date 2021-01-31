package com.AU.FlightDispatcher.dao;

import java.sql.Date;
import java.sql.Timestamp;

public class TableDataSchema {
	
	private int flightNo;
	private Date flightZdate;
	private int legNo;
	private String origin;
	private String destination;
	private int releaseNumber;
	private Timestamp releaseTime ;
		
	public TableDataSchema() {
		super();
	}

	public TableDataSchema(int flightNo, Date flightZdate, int legNo, String origin, String destination,
			int releaseNumber, Timestamp releaseTime) {
		super();
		this.flightNo = flightNo;
		this.flightZdate = flightZdate;
		this.legNo = legNo;
		this.origin = origin;
		this.destination = destination;
		this.releaseNumber = releaseNumber;
		this.releaseTime = releaseTime;
	}

	public int getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(int flightNo) {
		this.flightNo = flightNo;
	}

	public Date getFlightZdate() {
		return flightZdate;
	}

	public void setFlightZdate(Date flightZdate) {
		this.flightZdate = flightZdate;
	}

	public int getLegNo() {
		return legNo;
	}

	public void setLegNo(int legNo) {
		this.legNo = legNo;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getReleaseNumber() {
		return releaseNumber;
	}

	public void setReleaseNumber(int releaseNumber) {
		this.releaseNumber = releaseNumber;
	}

	public Timestamp getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Timestamp releaseTime) {
		this.releaseTime = releaseTime;
	}

	@Override
	public String toString() {
		return "TableDataSchema [flightNo=" + flightNo + ", flightZdate=" + flightZdate + ", legNo=" + legNo
				+ ", origin=" + origin + ", destination=" + destination + ", releaseNumber=" + releaseNumber
				+ ", releaseTime=" + releaseTime + "]";
	}	
}
