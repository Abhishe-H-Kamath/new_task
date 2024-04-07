package com.example.MeetingScheduler.dto;

import java.time.LocalDateTime;

public class TimeSlot {
	
	 private String id;
     private LocalDateTime start;
     private LocalDateTime end;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LocalDateTime getStart() {
		return start;
	}
	public void setStart(LocalDateTime start) {
		this.start = start;
	}
	public LocalDateTime getEnd() {
		return end;
	}
	public void setEnd(LocalDateTime end) {
		this.end = end;
	}
     
     

}
