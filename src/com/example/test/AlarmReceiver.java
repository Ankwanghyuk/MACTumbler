package com.example.test;

import java.util.GregorianCalendar;
import java.util.TimeZone;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
	String action;
	long[] vibratePattern = {0,100,300,100};
	public static final String ACTION_RESTART_PERSISTENTSERVICE = "ACTION.Restart.PersistentService";
	
	setup_Activity setup;

	@SuppressLint("ShowToast")
	@Override
	public void onReceive(Context context, Intent intent) {
		
		action = intent.getAction();
		Log.d("Log", "action : " + action);
		int id = intent.getIntExtra("ID", -1);
		Log.d("AlarmReceiver", "ID : " + id);
		Toast.makeText(context, "Alarm Receive!! 알람 번호 = " + id, 4000).show();
		Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		vibrator.vibrate(vibratePattern,-1);

		Intent service1 = new Intent(context, MyAlarmService.class);
		context.startService(service1);
		
		//////재부팅시 알람이 재설정 될수 있게하는 함수
		/*if (intent.getAction().equals(ACTION_RESTART_PERSISTENTSERVICE)) {
			Intent i = new Intent(context, MyAlarmService.class);
			// Intent i = new Intent(this, PersistentService.class);
			context.startService(i);
		}*/
	}
}