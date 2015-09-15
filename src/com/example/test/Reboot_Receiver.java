package com.example.test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class Reboot_Receiver extends BroadcastReceiver {
	long every_hour = 24 * 60 * 60 * 1000;
	GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+09:00"));
	GregorianCalendar currentCalendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+09:00"));
	
	int currentYY = currentCalendar.get(Calendar.YEAR);
	int currentMM = currentCalendar.get(Calendar.MONTH);
	int currentDD = currentCalendar.get(Calendar.DAY_OF_MONTH);
	int currentHH = currentCalendar.get(Calendar.HOUR);
	int currentMMM = currentCalendar.get(Calendar.MINUTE);
	
	GregorianCalendar gregorianCalendar_1 = new GregorianCalendar(TimeZone.getTimeZone("GMT+09:00"));
	GregorianCalendar gregorianCalendar_2 = new GregorianCalendar(TimeZone.getTimeZone("GMT+09:00"));
	GregorianCalendar gregorianCalendar_3 = new GregorianCalendar(TimeZone.getTimeZone("GMT+09:00"));
	GregorianCalendar gregorianCalendar_4 = new GregorianCalendar(TimeZone.getTimeZone("GMT+09:00"));
	GregorianCalendar gregorianCalendar_5 = new GregorianCalendar(TimeZone.getTimeZone("GMT+09:00"));
	GregorianCalendar gregorianCalendar_6 = new GregorianCalendar(TimeZone.getTimeZone("GMT+09:00"));
	GregorianCalendar gregorianCalendar_7 = new GregorianCalendar(TimeZone.getTimeZone("GMT+09:00"));
	GregorianCalendar gregorianCalendar_8 = new GregorianCalendar(TimeZone.getTimeZone("GMT+09:00"));
	setup_Activity setup;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		SharedPreferences mPref = context.getSharedPreferences("setTime",Context.MODE_PRIVATE);
		String p_hour = mPref.getString("alarm_hour", "");
		String p_minute = mPref.getString("alarm_minute", "");
		String set_interval_time = mPref.getString("alarm_interval_time", "7200000");
		setup_Activity setup;
		int hour = Integer.parseInt(p_hour);
		int minute = Integer.parseInt(p_minute);
		long set_interval_long_time = Long.parseLong(set_interval_time);
		long interval_time = gregorianCalendar.getTimeInMillis();
		
		//// 재부팅시 
		if(intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
			
			if(gregorianCalendar.getTimeInMillis() < currentCalendar.getTimeInMillis()) {
				gregorianCalendar.set(currentYY, currentMM, currentDD+1, hour, minute, 00);
				interval_time = gregorianCalendar.getTimeInMillis();
			} else {
				gregorianCalendar.set(currentYY, currentMM, currentDD, hour, minute, 00);
				interval_time = gregorianCalendar.getTimeInMillis();
			}
				
			setAlarm(context, 0, interval_time);
			setAlarm(context, 1, interval_time+set_interval_long_time);
			setAlarm(context, 2, interval_time+(set_interval_long_time*2));
			setAlarm(context, 3, interval_time+(set_interval_long_time*3));
			setAlarm(context, 4, interval_time+(set_interval_long_time*4));
			setAlarm(context, 5, interval_time+(set_interval_long_time*5));
			setAlarm(context, 6, interval_time+(set_interval_long_time*6));
			setAlarm(context, 7, interval_time+(set_interval_long_time*7));
			
			if(currentCalendar.getTimeInMillis()>interval_time) {
				cancelAlarm(context, 0);
				gregorianCalendar_1.set(currentYY, currentMM, currentDD+1, hour, minute, 00);
				long interval0 = gregorianCalendar_1.getTimeInMillis();
				setAlarm(context, 0, interval0);
			}
			if(currentCalendar.getTimeInMillis()>interval_time+set_interval_long_time) {
				cancelAlarm(context, 1);
				gregorianCalendar_2.set(currentYY, currentMM, currentDD+1, hour, minute, 00);
				long interval1 = gregorianCalendar_2.getTimeInMillis();
				setAlarm(context, 1, interval1+set_interval_long_time);
			}
			if(currentCalendar.getTimeInMillis()>interval_time+(set_interval_long_time*2)) {
				cancelAlarm(context, 2);
				gregorianCalendar_3.set(currentYY, currentMM, currentDD+1, hour, minute, 00);
				long interval2 = gregorianCalendar_3.getTimeInMillis();
				setAlarm(context, 2, interval2+(set_interval_long_time*2));
			}
			if(currentCalendar.getTimeInMillis()>interval_time+(set_interval_long_time*3)) {
				cancelAlarm(context, 3);
				gregorianCalendar_4.set(currentYY, currentMM, currentDD+1, hour, minute, 00);
				long interval3 = gregorianCalendar_4.getTimeInMillis();
				setAlarm(context, 3, interval3+(set_interval_long_time*3));
			}
			if(currentCalendar.getTimeInMillis()>interval_time+(set_interval_long_time*4)) {
				cancelAlarm(context, 4);
				gregorianCalendar_5.set(currentYY, currentMM, currentDD+1, hour, minute, 00);
				long interval4 = gregorianCalendar_5.getTimeInMillis();
				setAlarm(context, 4, interval4+(set_interval_long_time*4));
			}
			if(currentCalendar.getTimeInMillis()>interval_time+(set_interval_long_time*5)) {
				cancelAlarm(context, 5);
				gregorianCalendar_6.set(currentYY, currentMM, currentDD+1, hour, minute, 00);
				long interval5 = gregorianCalendar_6.getTimeInMillis();
				setAlarm(context, 5, interval5+(set_interval_long_time*5));
			}
			if(currentCalendar.getTimeInMillis()>interval_time+(set_interval_long_time*6)) {
				cancelAlarm(context, 6);
				gregorianCalendar_7.set(currentYY, currentMM, currentDD+1, hour, minute, 00);
				long interval6 = gregorianCalendar_7.getTimeInMillis();
				setAlarm(context, 6, interval6+(set_interval_long_time*6));
			}
			if(currentCalendar.getTimeInMillis()>interval_time+(set_interval_long_time*7)) {
				cancelAlarm(context, 7);
				gregorianCalendar_8.set(currentYY, currentMM, currentDD+1, hour, minute, 00);
				long interval7 = gregorianCalendar_8.getTimeInMillis();
				setAlarm(context, 7, interval7+(set_interval_long_time*7));
			}
			
			Log.d("BOOT_COMPLETED","Set Alarm");
			Toast.makeText(context,	"TURN ON", Toast.LENGTH_LONG).show();
			Toast.makeText(context,	hour+":"+minute+" interval="+set_interval_long_time, Toast.LENGTH_LONG).show();
		}	
	}
	
	public void setAlarm(Context context, int id, long interval) {
		long every_hour = 24 * 60 * 60 * 1000; // 24시간 마다 
		
		Bundle bundle = new Bundle();
		//Intent intent = new Intent(ALARM_ALERT_ACTION);
		Intent intent = new Intent(context, AlarmReceiver.class);
		bundle.putInt("ID", id);
		intent.putExtras(bundle);

		PendingIntent sender = PendingIntent.getBroadcast(context, id, intent, 0);
		AlarmManager alarm = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		//alarm.set(AlarmManager.RTC_WAKEUP, interval, sender); //알람 한번만 울릴시
		alarm.setRepeating(AlarmManager.RTC_WAKEUP, interval, every_hour, sender);  // 반복해서 울리게 할때
		Log.d("Alarm_set","id : "+ id +"interval : " + interval);
		Toast.makeText(context,	"id : "+ id +"interval : " + interval, Toast.LENGTH_SHORT).show();
	}
	
	public void cancelAlarm(Context context, int id) {

		Bundle bundle = new Bundle();
		Intent intent = new Intent(context, AlarmReceiver.class);
		bundle.putInt("ID", id);
		intent.putExtras(bundle);

		PendingIntent sender = PendingIntent.getBroadcast(context, id, intent,PendingIntent.FLAG_CANCEL_CURRENT);
		AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		alarm.cancel(sender);
		Log.d("Alarm_cancel","id : "+id);
	}

}