package com.example.MeetingScheduler.service;

import java.util.List;

import com.example.MeetingScheduler.dto.TimeInterval;

public interface MeetingSchedulerService {

	public List<TimeInterval> findAvailableTime();

}
