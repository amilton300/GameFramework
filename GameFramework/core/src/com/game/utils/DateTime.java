package com.game.utils;

import java.util.Calendar;


public class DateTime{
	
	public String getTimeString(){  
		int hour = Calendar.getInstance().getTime().getHours();
		int minutes = Calendar.getInstance().getTime().getMinutes();
		int seconds = Calendar.getInstance().getTime().getSeconds();
		
		return hour + ":" + minutes + ":" + seconds;
	}
	
	public int getHour(){
		return Calendar.getInstance().getTime().getHours();
	}
	
	public static DateTime getInstance(){
		if(instance == null)
			instance = new DateTime();
		return instance;
	}
	
	private DateTime(){
	}
	
	private static DateTime instance;
}
