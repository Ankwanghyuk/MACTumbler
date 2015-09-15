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
	/// �˶� ���� ����
	
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
			
			PushWakeLock.acquireCpuWakeLock(this);    /// ȭ���� ������� -> �ƿ�� �Լ�
			mManager = (NotificationManager) this.getApplicationContext().getSystemService(
							this.getApplicationContext().NOTIFICATION_SERVICE);
			Intent intent1 = new Intent(this.getApplicationContext(),Page_Activity.class);    /// ����â�� ��Ÿ�� ȭ�� Ŭ���� ������ Activity
			
			//�˶��� �︮�� ���¹ٿ� ǥ���ϴ� �κ�
			Notification notification = new Notification(R.drawable.full_water,"Drink Water!!", System.currentTimeMillis());
			
			intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
			notification.sound = Uri.parse("android.resource://com.example.test/"+R.raw.sound);

			PendingIntent pendingNotificationIntent = PendingIntent.getActivity(
			this.getApplicationContext(), 0, intent1,PendingIntent.FLAG_UPDATE_CURRENT);
			notification.flags |= Notification.FLAG_AUTO_CANCEL;
			
			//// ���¹ٸ� �������� ������ Ÿ��Ʋ, ����, ���ý� ����� intent��ü ����
			notification.setLatestEventInfo(this.getApplicationContext(), "Drink Time", "Drink Water!!", pendingNotificationIntent);
			
			mManager.notify(0, notification);
			// WakeLock ����.
			PushWakeLock.releaseCpuLock();
			}
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
