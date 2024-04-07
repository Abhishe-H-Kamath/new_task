package com.example.MeetingScheduler.config;

import java.io.FileReader;
import java.util.logging.Logger;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MeetingScheduleReadFile {
	
	static Logger logger = Logger.getLogger("MeetingScheduleReadFile.Class");
	
	public static JSONObject readJsonObject() {

		logger.info("---------------------[ Entering readJsonObject Method ]-----------------------------");
		String fileName  = "D:\\IDES\\Workspace\\MeetingScheduler\\src\\main\\resources\\Data\\Danny boy.json";
		JSONObject data = null;
		try {
			JSONParser parser = new JSONParser();
			data = (JSONObject) parser.parse(new FileReader(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;

	}

}
