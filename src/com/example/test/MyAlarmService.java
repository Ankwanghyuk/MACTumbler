package com.example.test;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

public class MyAlarmService extends Service {

	private NotificationManager mManager;
	/// 알람 볼륨 설정
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		
		super.onCreate();
	}

	@SuppressWarnings({ "static-access", "deprecation" })
	@Override
	public int onStartCommand(Intent intent,int flags , int startId) {
		
		handleStart(intent, startId);
		
		Log.d("onStartCommand","startID  :  "+Integer.toString(startId));
		
		return START_NOT_STICKY;

	}

	
	void handleStart (Intent intent, int startId) {
		if(startId<=8 && startId>=0) {
			Log.d("handleStart","startID  :  "+Integer.toString(startId));
			
			PushWakeLock.acquireCpuWakeLock(this);    /// 화면을 슬립모드 -> 꺠우는 함수
			mManager = (NotificationManager) this.getApplicationContext().getSystemService(
							this.getApplicationContext().NOTIFICATION_SERVICE);
			Intent intent1 = new Intent(this.getApplicationContext(),Page_Activity.class);    /// 상태창에 나타난 화면 클릭시 실행할 Activity
			
			//알람이 울리면 상태바에 표시하는 부분
			Notification notification = new Notification(R.drawable.full_water,"Drink Water!!", System.currentTimeMillis());
			
			intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
			notification.sound = Uri.parse("android.resource://com.example.test/"+R.raw.sound);

			PendingIntent pendingNotificationIntent = PendingIntent.getActivity(
			this.getApplicationContext(), 0, intent1,PendingIntent.FLAG_UPDATE_CURRENT);
			notification.flags |= Notification.FLAG_AUTO_CANCEL;
			
			//// 상태바를 내렸을때 보여줄 타이틀, 내용, 선택시 실행될 intent객체 생성
			notification.setLatestEventInfo(this.getApplicationContext(), "Drink Time", "Drink Water!!", pendingNotificationIntent);
			
			mManager.notify(0, notification);
			// WakeLock 해제.
			PushWakeLock.releaseCpuLock();
			}
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
