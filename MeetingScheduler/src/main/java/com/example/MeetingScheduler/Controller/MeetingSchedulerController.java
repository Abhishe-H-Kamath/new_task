package com.example.MeetingScheduler.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MeetingScheduler.dto.TimeInterval;
import com.example.MeetingScheduler.service.MeetingSchedulerService;

@RestController
public class MeetingSchedulerController {
	
	@Autowired
	private MeetingSchedulerService schedulerService;
	
	@PostMapping(value = "/findAvailableTime")
	public ResponseEntity<List<TimeInterval>> findAvailableTime() {
		List<TimeInterval> interval = new ArrayList<>();
		try {
			interval = schedulerService.findAvailableTime();

			return ResponseEntity.ok(interval);
		} catch (Exception e) {
			e.printStackTrace();
			return (ResponseEntity<List<TimeInterval>>) ResponseEntity.notFound();
		}

	}

}
