package com.example.MeetingScheduler.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MeetingScheduler.config.MeetingScheduleReadFile;
import com.example.MeetingScheduler.constants.StringConstants;
import com.example.MeetingScheduler.dto.Appointment;
import com.example.MeetingScheduler.dto.TimeInterval;
import com.example.MeetingScheduler.dto.TimeSlot;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("MeetingSchedulerService")
public class MeetingSchedulerServiceImpl implements MeetingSchedulerService{
	
	public  List<TimeInterval> findAvailableTime() {
		List<TimeInterval> availableTime = new ArrayList<>();
		try {
			 ObjectMapper mapper = new ObjectMapper();
	         JsonNode jsonNode = mapper.readTree(MeetingScheduleReadFile.readJsonObject().toString());
	         List<Appointment> appointments = new ArrayList<>();
	         List<TimeSlot> timeSlots = new ArrayList<>();
	         
	         // Parse appointments
	            for (JsonNode appointmentNode : jsonNode.get(StringConstants.APPOINTMENTS)) {
	                Appointment appointment = new Appointment();
	                appointment.setId(appointmentNode.get(StringConstants.ID).asText());
	                appointment.setStart(LocalDateTime.parse(appointmentNode.get(StringConstants.START).asText()));
	                appointment.setEnd(LocalDateTime.parse(appointmentNode.get(StringConstants.END).asText()));
	                appointments.add(appointment);
	            }

	            // Parse time slots
	            for (JsonNode timeSlotNode : jsonNode.get(StringConstants.TIMESLOTS)) {
	                TimeSlot timeSlot = new TimeSlot();
	                timeSlot.setId(timeSlotNode.get(StringConstants.ID).asText());
	                timeSlot.setStart(LocalDateTime.parse(timeSlotNode.get(StringConstants.START).asText()));
	                timeSlot.setEnd(LocalDateTime.parse(timeSlotNode.get(StringConstants.END).asText()));
	                timeSlots.add(timeSlot);
	            }

	            // Calculate available time
	            availableTime = calculateAvailableTime(appointments, timeSlots);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return availableTime;
		
	}
	
	private static List<TimeInterval> calculateAvailableTime(List<Appointment> appointments, List<TimeSlot> timeSlots) {
	        List<TimeInterval> availableTime = new ArrayList<>();

	        for (int i = 0; i < timeSlots.size(); i++) {
	            TimeSlot timeSlot = timeSlots.get(i);

	            LocalDateTime currentSlotStart = timeSlot.getStart();
	            LocalDateTime currentSlotEnd = timeSlot.getEnd();

	            for (int j = 0; j < appointments.size(); j++) {
	                Appointment appointment = appointments.get(j);

	                LocalDateTime appointmentStart = appointment.getStart();
	                LocalDateTime appointmentEnd = appointment.getEnd();

	                if (currentSlotStart.isBefore(appointmentStart) && currentSlotEnd.isAfter(appointmentEnd)) {
	                    availableTime.add(new TimeInterval(currentSlotStart, appointmentStart));
	                    currentSlotStart = appointmentEnd;
	                }
	            }

	            // Add remaining available time after last appointment
	            availableTime.add(new TimeInterval(currentSlotStart, currentSlotEnd));
	        }

	        return availableTime;
	    }

}
