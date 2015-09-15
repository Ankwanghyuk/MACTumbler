package com.example.test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.preference.RingtonePreference;
import android.preference.SwitchPreference;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

public class setup_Activity extends PreferenceActivity implements OnTimeSetListener {

	public static final String ALARM_ALERT_ACTION = "TESTALARM";
	protected static final String TAG = "setup";
	static final int TIME_DIALOG_ID = 0;
	// 알람 메니저
	private AlarmManager mManager;
	// 설정 일시
	private GregorianCalendar mCalendar;
	private NotificationManager mNotification;
	
	private Context context;
	private PendingIntent mAlarmSender;
	int current_hour;
	int current_minute;
	long hour;
	long real_hour;
	long minute;
	long real_minute;
	long interval_set_time;
	final Calendar c = Calendar.getInstance();
	String p_hour;
	String p_minute;
	String set_interval_time;
	
	
	GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+09:00"));
	GregorianCalendar gregorianCalendar_1 = new GregorianCalendar(TimeZone.getTimeZone("GMT+09:00"));
	GregorianCalendar gregorianCalendar_2 = new GregorianCalendar(TimeZone.getTimeZone("GMT+09:00"));
	GregorianCalendar gregorianCalendar_3 = new GregorianCalendar(TimeZone.getTimeZone("GMT+09:00"));
	GregorianCalendar gregorianCalendar_4 = new GregorianCalendar(TimeZone.getTimeZone("GMT+09:00"));
	GregorianCalendar gregorianCalendar_5 = new GregorianCalendar(TimeZone.getTimeZone("GMT+09:00"));
	GregorianCalendar gregorianCalendar_6 = new GregorianCalendar(TimeZone.getTimeZone("GMT+09:00"));
	GregorianCalendar gregorianCalendar_7 = new GregorianCalendar(TimeZone.getTimeZone("GMT+09:00"));
	GregorianCalendar gregorianCalendar_8 = new GregorianCalendar(TimeZone.getTimeZone("GMT+09:00"));
	GregorianCalendar currentCalendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+09:00"));

	int currentYY = currentCalendar.get(Calendar.YEAR);
	int currentMM = currentCalendar.get(Calendar.MONTH);
	int currentDD = currentCalendar.get(Calendar.DAY_OF_MONTH);
	int currentHH = currentCalendar.get(Calendar.HOUR);
	int currentMMM = currentCalendar.get(Calendar.MINUTE);
	
	
	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		LinearLayout root = (LinearLayout) findViewById(android.R.id.list).getParent().getParent().getParent();
		Toolbar bar = (Toolbar) LayoutInflater.from(this).inflate(
		R.layout.setting_toolbar, root, false);
		root.addView(bar, 0); // insert at top
		bar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		addPreferencesFromResource(R.layout.setup_page);
		setOnPreferenceChange(findPreference("vibrater"));
		setOnPreferenceChange(findPreference("autoUpdate_ringtone"));
		setOnPreferenceChange(findPreference("alrarm_method"));
		setOnPreferenceChange(findPreference("alarm_term"));
		setOnPreferenceChange(findPreference("alarm_time"));
		
		Log.d("TIME",currentHH+":"+currentMMM);
		
		// preference에 저장된 알람설정 시간을 갖고 옴
		final SharedPreferences mPref = getSharedPreferences("setTime",MODE_PRIVATE);
		final String p_day = mPref.getString("alarm_day", Integer.toString(currentDD));
		String ampm = mPref.getString("alarm_ampm", "");
		p_hour = mPref.getString("alarm_hour", "");
		p_minute = mPref.getString("alarm_minute", "");
		set_interval_time = mPref.getString("alarm_interval_time", "7200000");
				
		Log.d("ALARM",ampm+p_hour+"시 "+p_minute+"분");
		if(Integer.parseInt(p_hour)>12) {
			int a =Integer.parseInt(p_hour)-12;
			findPreference("alarm_time").setSummary(ampm+a+"시 "+p_minute+"분");
			
		} else {
		findPreference("alarm_time").setSummary(ampm+p_hour+"시 "+p_minute+"분");
		}
		SwitchPreference switch_pref = (SwitchPreference)findPreference("autoUpdate");
		switch_pref.setOnPreferenceChangeListener(new SwitchPreference.OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				boolean checked = ((SwitchPreference)findPreference("autoUpdate")).isChecked();
				p_hour = mPref.getString("alarm_hour", "");
				p_minute = mPref.getString("alarm_minute", "");
				set_interval_time = mPref.getString("alarm_interval_time", "7200000");
				
				if (checked==false) {   //// switch preference --> ON 했을때
					Toast.makeText(setup_Activity.this,	"TURN ON", Toast.LENGTH_SHORT).show();
					//여기서 부터 데이터 불러옴 & preference값으로 알람 재설정
					for(int i=0;i<8;i++) {
						cancelAlarm(i);
					}
					int day = Integer.parseInt(p_day);
					int hour = Integer.parseInt(p_hour);
					int minute = Integer.parseInt(p_minute);
					long set_interval_long_time = Long.parseLong(set_interval_time);
					
					gregorianCalendar.set(currentYY, currentMM, currentDD, hour,minute, 00);
					if(gregorianCalendar.getTimeInMillis() < currentCalendar.getTimeInMillis()) {
						gregorianCalendar.set(currentYY, currentMM, currentDD, hour, minute, 00);
					} else {
						gregorianCalendar.set(currentYY, currentMM, currentDD, hour, minute, 00);
					}
					long interval_time = gregorianCalendar.getTimeInMillis();
					setAlarm(0, interval_time);
					setAlarm(1, interval_time+interval_set_time);
					setAlarm(2, interval_time+(interval_set_time*2));
					setAlarm(3, interval_time+(interval_set_time*3));
					setAlarm(4, interval_time+(interval_set_time*4));
					setAlarm(5, interval_time+(interval_set_time*5));
					setAlarm(6, interval_time+(interval_set_time*6));
					setAlarm(7, interval_time+(interval_set_time*7));
					
					if(currentCalendar.getTimeInMillis()>interval_time) {
						cancelAlarm(0);
						gregorianCalendar_1.set(currentYY, currentMM, currentDD+1, hour, minute, 00);
						long interval0 = gregorianCalendar_1.getTimeInMillis();
						setAlarm(0, interval0);
					}
					if(currentCalendar.getTimeInMillis()>interval_time+interval_set_time) {
						cancelAlarm(1);
						gregorianCalendar_2.set(currentYY, currentMM, currentDD+1, hour, minute, 00);
						long interval1 = gregorianCalendar_2.getTimeInMillis();
						setAlarm(1, interval1+interval_set_time);
					}
					if(currentCalendar.getTimeInMillis()>interval_time+(interval_set_time*2)) {
						cancelAlarm(2);
						gregorianCalendar_3.set(currentYY, currentMM, currentDD+1, hour, minute, 00);
						long interval2 = gregorianCalendar_3.getTimeInMillis();
						setAlarm(2, interval2+(interval_set_time*2));
					}
					if(currentCalendar.getTimeInMillis()>interval_time+(interval_set_time*3)) {
						cancelAlarm(3);
						gregorianCalendar_4.set(currentYY, currentMM, currentDD+1, hour, minute, 00);
						long interval3 = gregorianCalendar_4.getTimeInMillis();
						setAlarm(3, interval3+(interval_set_time*3));
					}
					if(currentCalendar.getTimeInMillis()>interval_time+(interval_set_time*4)) {
						cancelAlarm(4);
						gregorianCalendar_5.set(currentYY, currentMM, currentDD+1, hour, minute, 00);
						long interval4 = gregorianCalendar_5.getTimeInMillis();
						setAlarm(4, interval4+(interval_set_time*4));
					}
					if(currentCalendar.getTimeInMillis()>interval_time+(interval_set_time*5)) {
						cancelAlarm(5);
						gregorianCalendar_6.set(currentYY, currentMM, currentDD+1, hour, minute, 00);
						long interval5 = gregorianCalendar_6.getTimeInMillis();
						setAlarm(5, interval5+(interval_set_time*5));
					}
					if(currentCalendar.getTimeInMillis()>interval_time+(interval_set_time*6)) {
						cancelAlarm(6);
						gregorianCalendar_7.set(currentYY, currentMM, currentDD+1, hour, minute, 00);
						long interval6 = gregorianCalendar_7.getTimeInMillis();
						setAlarm(6, interval6+(interval_set_time*6));
					}
					if(currentCalendar.getTimeInMillis()>interval_time+(interval_set_time*7)) {
						cancelAlarm(7);
						gregorianCalendar_8.set(currentYY, currentMM, currentDD+1, hour, minute, 00);
						long interval7 = gregorianCalendar_8.getTimeInMillis();
						setAlarm(7, interval7+(interval_set_time*7));
					}
					Log.d("switchpreference","reset alarm!"+hour+":"+minute);	
		        } else if(checked==true) {		//// switch preference --> OFF 했을때
		        	Toast.makeText(setup_Activity.this,	"TURN OFF", Toast.LENGTH_SHORT).show(); 
		        	for(int i=0;i<8;i++) {
						cancelAlarm(i);     // id=0 인 알람 삭제
					}            
		        	Editor ed = mPref.edit();   // preference 값 삭제
		        	ed.remove("alarm_time");
		        	ed.commit();
		        }
				return true;		
			}
		});
		
		Preference btnDateFilter = (Preference) findPreference("alarm_time");
		btnDateFilter.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
					@TargetApi(Build.VERSION_CODES.HONEYCOMB)
					@Override
					public boolean onPreferenceClick(Preference preference) {
						showDateDialog();	
						return false;
					}
		});

		// 통지 매니저를 취득
		mNotification = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		// 알람 매니저를 취득
		mManager = (AlarmManager) this.getApplicationContext().getSystemService(Context.ALARM_SERVICE);
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
			Preference preference) {
		//Toast.makeText(setup_Activity.this,	"preference:" + preference.getKey(), Toast.LENGTH_SHORT).show();
		return super.onPreferenceTreeClick(preferenceScreen, preference);
	}

	private void showDateDialog() {
		// Use the current date as the default date in the picker

		//current_hour = c.get(Calendar.HOUR);
		//current_minute = c.get(Calendar.MINUTE);
		current_hour = currentCalendar.get(Calendar.HOUR);
		current_minute = currentCalendar.get(Calendar.MINUTE);
		new TimePickerDialog(this, this, current_hour, current_minute, false).show();
	}

	private Preference.OnPreferenceChangeListener onPreferenceChangeListener = new Preference.OnPreferenceChangeListener() {
		@SuppressWarnings("deprecation")
		@Override
		public boolean onPreferenceChange(Preference preference, Object newValue) {
			String stringValue = newValue.toString();
			SharedPreferences prefs = getSharedPreferences("setTime",MODE_PRIVATE);
			Editor ed = prefs.edit();
			
			if (preference instanceof EditTextPreference) {
				preference.setSummary(stringValue);
			}
			if (preference instanceof ListPreference) {
				ListPreference listPreference = (ListPreference) preference;
				int index = listPreference.findIndexOfValue(stringValue);
				preference.setSummary(index >= 0 ? listPreference.getEntries()[index]: null);
				Log.d(TAG, stringValue);
				
				
				/// 알람 방식 설정
				if (stringValue.equals("진동")) {
					findPreference("autoUpdate_ringtone").setEnabled(false);
					findPreference("vibrater").setEnabled(true);
				} else if (stringValue.equals("소리")) {
					findPreference("vibrater").setEnabled(false);
					findPreference("autoUpdate_ringtone").setEnabled(true);
				} else if (stringValue.equals("진+소")) {
					findPreference("vibrater").setEnabled(true);
					findPreference("autoUpdate_ringtone").setEnabled(true);
				}
				
				//// 알람 간격
				if(stringValue.equals("11")) {
					interval_set_time = 1 * 60 * 60 * 1000 + 1 * 30 * 60 * 1000;
					ed.putString("alarm_interval_time", String.valueOf(interval_set_time));
					
				} else if (stringValue.equals("22")) {
					interval_set_time = 2 * 60 * 60 * 1000;
					//interval_set_time = 60 * 1000;
					ed.putString("alarm_interval_time", String.valueOf(interval_set_time));
					
				} else if(stringValue.equals("33")) {
					interval_set_time = 2 * 60 * 60 * 1000 + 1 * 30 * 60 * 1000;
					ed.putString("alarm_interval_time", String.valueOf(interval_set_time));
				}
				ed.commit();
			}
			if (preference instanceof RingtonePreference) {
				if (TextUtils.isEmpty(stringValue)) {
					preference.setSummary("무음");
				} else {
					Ringtone ringtone = RingtoneManager.getRingtone(
							preference.getContext(), Uri.parse(stringValue));
					if (ringtone == null) {
						preference.setSummary(null);
					} else {
						String name = ringtone.getTitle(preference.getContext());
						preference.setSummary(name);
					}
				}
			}
			return true;
		}
	};

	private void setOnPreferenceChange(Preference mPreference) {
		mPreference.setOnPreferenceChangeListener(onPreferenceChangeListener);
		onPreferenceChangeListener.onPreferenceChange(mPreference,
				PreferenceManager.getDefaultSharedPreferences(
						mPreference.getContext()).getString(
						mPreference.getKey(), ""));
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute1) {
		
		gregorianCalendar.set(currentYY, currentMM, currentDD, hourOfDay,minute1, 00);
		SharedPreferences prefs = getSharedPreferences("setTime",MODE_PRIVATE);
		Editor ed = prefs.edit();
		
		// 설정시간이 현재시간 보다 작을경우 그다음 날 알림
		if (gregorianCalendar.getTimeInMillis() < currentCalendar.getTimeInMillis()) {
			gregorianCalendar.set(currentYY, currentMM, currentDD, hourOfDay, minute1, 00);
			ed.putString("alarm_day", Integer.toString(currentDD)); 
			/*
			 * gregorianCalendar.set(currentYY, currentMM, currentDD+1, hourOfDay, minute1, 00);
			ed.putString("alarm_day", Integer.toString(currentDD+1));   
			 * 
			 */
			Log.i("TAG", gregorianCalendar.getTimeInMillis() + ":");
		} else {   /// 설정시간 >= 현재시간 그날 설정시간에 울림
			gregorianCalendar.set(currentYY, currentMM, currentDD, hourOfDay, minute1, 00);
			ed.putString("alarm_day", Integer.toString(currentDD));
			Log.i("TAG", gregorianCalendar.getTimeInMillis() + ":");
		}
		
		//// 프리퍼런스에 저장
		Log.i("dasd", "hour " + hourOfDay + " minute " + minute1);
		
		
		///AM PM 설정
		if(hourOfDay >12) {
		int summary_hour = hourOfDay - 12;
		findPreference("alarm_time").setSummary("PM" + summary_hour + "시 " + minute1 + "분");
		
		ed.putString("alarm_ampm","PM");
		ed.putString("alarm_hour",Integer.toString(hourOfDay));
		ed.putString("alarm_minute",Integer.toString(minute1));
		
		} else {
			findPreference("alarm_time").setSummary("AM" + hourOfDay + "시 " + minute1 + "분");
			ed.putString("alarm_ampm","AM");
			ed.putString("alarm_hour",Integer.toString(hourOfDay));
			ed.putString("alarm_minute",Integer.toString(minute1));
		}
		
		/// 몇초후에 실행될지 설정
		long interval = gregorianCalendar.getTimeInMillis();
		
		
		setAlarm(0, interval);
		setAlarm(1, interval+interval_set_time);
		setAlarm(2, interval+(interval_set_time*2));
		setAlarm(3, interval+(interval_set_time*3));
		setAlarm(4, interval+(interval_set_time*4));
		setAlarm(5, interval+(interval_set_time*5));
		setAlarm(6, interval+(interval_set_time*6));
		setAlarm(7, interval+(interval_set_time*7));
		
		if(currentCalendar.getTimeInMillis()>interval) {
			cancelAlarm(0);
			gregorianCalendar_1.set(currentYY, currentMM, currentDD+1, hourOfDay, minute1, 00);
			long interval0 = gregorianCalendar_1.getTimeInMillis();
			setAlarm(0, interval0);
		}
		if(currentCalendar.getTimeInMillis()>interval+interval_set_time) {
			cancelAlarm(1);
			gregorianCalendar_1.set(currentYY, currentMM, currentDD+1, hourOfDay, minute1, 00);
			long interval1 = gregorianCalendar_1.getTimeInMillis();
			setAlarm(1, interval1+interval_set_time);
		}
		if(currentCalendar.getTimeInMillis()>interval+(interval_set_time*2)) {
			cancelAlarm(2);
			gregorianCalendar_1.set(currentYY, currentMM, currentDD+1, hourOfDay, minute1, 00);
			long interval2 = gregorianCalendar_1.getTimeInMillis();
			setAlarm(2, interval2+(interval_set_time*2));
		}
		if(currentCalendar.getTimeInMillis()>interval+(interval_set_time*3)) {
			cancelAlarm(3);
			gregorianCalendar_1.set(currentYY, currentMM, currentDD+1, hourOfDay, minute1, 00);
			long interval3 = gregorianCalendar_1.getTimeInMillis();
			setAlarm(3, interval3+(interval_set_time*3));
		}
		if(currentCalendar.getTimeInMillis()>interval+(interval_set_time*4)) {
			cancelAlarm(4);
			gregorianCalendar_1.set(currentYY, currentMM, currentDD+1, hourOfDay, minute1, 00);
			long interval4 = gregorianCalendar_1.getTimeInMillis();
			setAlarm(4, interval4+(interval_set_time*4));
		}
		if(currentCalendar.getTimeInMillis()>interval+(interval_set_time*5)) {
			cancelAlarm(5);
			gregorianCalendar_1.set(currentYY, currentMM, currentDD+1, hourOfDay, minute1, 00);
			long interval5 = gregorianCalendar_1.getTimeInMillis();
			setAlarm(5, interval5+(interval_set_time*5));
		}
		if(currentCalendar.getTimeInMillis()>interval+(interval_set_time*6)) {
			cancelAlarm(6);
			gregorianCalendar_1.set(currentYY, currentMM, currentDD+1, hourOfDay, minute1, 00);
			long interval6 = gregorianCalendar_1.getTimeInMillis();
			setAlarm(6, interval6+(interval_set_time*6));
		}
		if(currentCalendar.getTimeInMillis()>interval+(interval_set_time*7)) {
			cancelAlarm(7);
			gregorianCalendar_1.set(currentYY, currentMM, currentDD+1, hourOfDay, minute1, 00);
			long interval7 = gregorianCalendar_1.getTimeInMillis();
			setAlarm(7, interval7+(interval_set_time*7));
		}
		
		///프리퍼런스의 저장을 끝 마침
		ed.commit();
		Toast.makeText(this, "알람시작" + interval + "초 후에 실행", 4000).show();
	}

	// /////// 알람 ///////////
	// 알람의 설정
	// 알람의 설정
	public void setAlarm(int id, long interval) {
		long every_hour = 24 * 60 * 60 * 1000; // 24시간 마다 
		
		Bundle bundle = new Bundle();
		//Intent intent = new Intent(ALARM_ALERT_ACTION);
		Intent intent = new Intent(setup_Activity.this, AlarmReceiver.class);
		bundle.putInt("ID", id);
		intent.putExtras(bundle);

		PendingIntent sender = PendingIntent.getBroadcast(getApplicationContext(), id, intent, 0);
		AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		//alarm.set(AlarmManager.RTC_WAKEUP, interval, sender); //알람 한번만 울릴시
		alarm.setRepeating(AlarmManager.RTC_WAKEUP, interval, every_hour, sender);  // 반복해서 울리게 할때
		
		Log.d("Alarm_set","id : "+id+"interval : "+interval);
	}

	public void cancelAlarm(int id) {

		Bundle bundle = new Bundle();
		Intent intent = new Intent(setup_Activity.this, AlarmReceiver.class);
		bundle.putInt("ID", id);
		intent.putExtras(bundle);

		PendingIntent sender = PendingIntent.getBroadcast(getApplicationContext(), id, intent,PendingIntent.FLAG_CANCEL_CURRENT);
		AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		alarm.cancel(sender);
		Log.d("Alarm_cancel","id : "+id);
	}

}
