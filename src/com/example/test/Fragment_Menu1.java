package com.example.test;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_Menu1 extends Fragment implements OnClickListener {

	ImageView bt1, bt2, exitbt;
	ImageView iv, iv1, iv2, iv3, iv4, iv5, iv6, iv7, iv8, Image_btn; // �� �̹���
	TextView tv, cup, temp;
	static int count = 0; // ī��Ʈ
	String readMessage;
	String readMessage2, readMessage3="0", readMessage4;
	ViewPager mViewPager;
	String buffer = "";
	String cup_count ="0";
	String temp_count="";
	String total_count="";
	int real_count=0;
	
	int count_buffer = 0;
	int blue_state=0;
	private BluetoothService btService = null; // ������� ����
	int blue_cup = 0;
	int blue_cupArr[] = new int[2];
	//private TextView txt_Result;
	static TextView txt_Chat;
	private static final String TAG = "Main";
	// Debugging
	//private ListView mConversationView;
	//private static final boolean D = true;

	// Message types sent from the BluetoothChatService Handler
	public static final int MESSAGE_STATE_CHANGE = 1;
	public static final int MESSAGE_READ = 2;
	public static final int MESSAGE_WRITE = 3;
	public static final int MESSAGE_DEVICE_NAME = 4;
	public static final int MESSAGE_TOAST = 5;
	public static final int CONNECT_FAIL = 6;

	// Key names received from the BluetoothChatService Handler
	public static final String DEVICE_NAME = "device_name";
	public static final String TOAST = "toast";
	static final int STATE_LISTEN = 1;
	private static final int REQUEST_CONNECT_DEVICE = 1;
	private static final int REQUEST_ENABLE_BT = 2;
	protected static final int RESULT_OK = 0;
	static ArrayAdapter<String> mConversationArrayAdapter;
	static ArrayAdapter<String> mConversationArrayAdapter2;
	static ArrayAdapter<String> mConversationArrayAdapter3;
	static ArrayAdapter<String> mConversationArrayAdapter4;

	private String a = "";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setLayout();
		View rootView = inflater.inflate(R.layout.main, container, false);
		Image_btn = (ImageView) rootView.findViewById(R.id.Image_btn);
		bt1 = (ImageView) rootView.findViewById(R.id.button1);
		bt2 = (ImageView) rootView.findViewById(R.id.button2);
		exitbt = (ImageView) rootView.findViewById(R.id.exit);

		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		exitbt.setOnClickListener(this);

		Image_btn.setOnClickListener(this);

		mConversationArrayAdapter = new ArrayAdapter<String>(getActivity(),
				R.layout.message);

		//mConversationView = (ListView) rootView.findViewById(R.id.in);

		cup = (TextView) rootView.findViewById(R.id.Text_cup);
		temp = (TextView) rootView.findViewById(R.id.Text_temp);

		//mConversationView.setAdapter(mConversationArrayAdapter);
		
		
		temp.setText(temp_count);
		// xml�� �ҷ��� �̹̺並 iv�� �־� �Ӽ���� �ϱ����� ����
		iv = (ImageView) rootView.findViewById(R.id.percent);
		iv1 = (ImageView) rootView.findViewById(R.id.percent1);
		iv2 = (ImageView) rootView.findViewById(R.id.percent2);
		iv3 = (ImageView) rootView.findViewById(R.id.percent3);
		iv4 = (ImageView) rootView.findViewById(R.id.percent4);
		iv5 = (ImageView) rootView.findViewById(R.id.percent5);
		iv6 = (ImageView) rootView.findViewById(R.id.percent6);
		iv7 = (ImageView) rootView.findViewById(R.id.percent7);
		
		iv.setImageResource(R.drawable.water);
		iv1.setImageResource(R.drawable.water);
		iv2.setImageResource(R.drawable.water);
		iv3.setImageResource(R.drawable.water);
		iv4.setImageResource(R.drawable.water);
		iv5.setImageResource(R.drawable.water);
		iv6.setImageResource(R.drawable.water);
		iv7.setImageResource(R.drawable.water);
		
		
		if (real_count >= 8) {
			iv.setImageResource(R.drawable.full_water);
			iv1.setImageResource(R.drawable.full_water);
			iv2.setImageResource(R.drawable.full_water);
			iv3.setImageResource(R.drawable.full_water);
			iv4.setImageResource(R.drawable.full_water);
			iv5.setImageResource(R.drawable.full_water);
			iv6.setImageResource(R.drawable.full_water);
			iv7.setImageResource(R.drawable.full_water);
			cup.setText(Integer.toString(real_count));
		} else if (real_count == 7) {
			iv.setImageResource(R.drawable.full_water);
			iv1.setImageResource(R.drawable.full_water);
			iv2.setImageResource(R.drawable.full_water);
			iv3.setImageResource(R.drawable.full_water);
			iv4.setImageResource(R.drawable.full_water);
			iv5.setImageResource(R.drawable.full_water);
			iv6.setImageResource(R.drawable.full_water);
			iv7.setImageResource(R.drawable.water);
			cup.setText(Integer.toString(real_count));
		} else if (real_count == 6) {
			iv.setImageResource(R.drawable.full_water);
			iv1.setImageResource(R.drawable.full_water);
			iv2.setImageResource(R.drawable.full_water);
			iv3.setImageResource(R.drawable.full_water);
			iv4.setImageResource(R.drawable.full_water);
			iv5.setImageResource(R.drawable.full_water);
			iv6.setImageResource(R.drawable.water);
			iv7.setImageResource(R.drawable.water);
			cup.setText(Integer.toString(real_count));
		} else if (real_count == 5) {
			iv.setImageResource(R.drawable.full_water);
			iv1.setImageResource(R.drawable.full_water);
			iv2.setImageResource(R.drawable.full_water);
			iv3.setImageResource(R.drawable.full_water);
			iv4.setImageResource(R.drawable.full_water);
			iv5.setImageResource(R.drawable.water);
			iv6.setImageResource(R.drawable.water);
			iv7.setImageResource(R.drawable.water);
			cup.setText(Integer.toString(real_count));
		} else if (real_count == 4) {
			iv.setImageResource(R.drawable.full_water);
			iv1.setImageResource(R.drawable.full_water);
			iv2.setImageResource(R.drawable.full_water);
			iv3.setImageResource(R.drawable.full_water);
			iv4.setImageResource(R.drawable.water);
			iv5.setImageResource(R.drawable.water);
			iv6.setImageResource(R.drawable.water);
			iv7.setImageResource(R.drawable.water);
			cup.setText(Integer.toString(real_count));
		} else if (real_count == 3) {
			iv.setImageResource(R.drawable.full_water);
			iv1.setImageResource(R.drawable.full_water);
			iv2.setImageResource(R.drawable.full_water);
			iv3.setImageResource(R.drawable.water);
			iv4.setImageResource(R.drawable.water);
			iv5.setImageResource(R.drawable.water);
			iv6.setImageResource(R.drawable.water);
			iv7.setImageResource(R.drawable.water);
			cup.setText(Integer.toString(real_count));
		} else if (real_count == 2) {
			iv.setImageResource(R.drawable.full_water);
			iv1.setImageResource(R.drawable.full_water);
			iv2.setImageResource(R.drawable.water);
			iv3.setImageResource(R.drawable.water);
			iv4.setImageResource(R.drawable.water);
			iv5.setImageResource(R.drawable.water);
			iv6.setImageResource(R.drawable.water);
			iv7.setImageResource(R.drawable.water);
			cup.setText(Integer.toString(real_count));
		} else if (real_count == 1) {
			iv.setImageResource(R.drawable.full_water);
			iv1.setImageResource(R.drawable.water);
			iv2.setImageResource(R.drawable.water);
			iv3.setImageResource(R.drawable.water);
			iv4.setImageResource(R.drawable.water);
			iv5.setImageResource(R.drawable.water);
			iv6.setImageResource(R.drawable.water);
			iv7.setImageResource(R.drawable.water);
			cup.setText(Integer.toString(real_count));
		} else if (real_count == 0) {
			iv.setImageResource(R.drawable.water);
			iv1.setImageResource(R.drawable.water);
			iv2.setImageResource(R.drawable.water);
			iv3.setImageResource(R.drawable.water);
			iv4.setImageResource(R.drawable.water);
			iv5.setImageResource(R.drawable.water);
			iv6.setImageResource(R.drawable.water);
			iv7.setImageResource(R.drawable.water);
			cup.setText(Integer.toString(real_count));
		}
		
		if(blue_state==1) {
			Image_btn.setImageResource(R.drawable.bt_pair);
		}
		if(blue_state==0) {
			Image_btn.setImageResource(R.drawable.bt);
		}
		// xml�� �ҷ��� �ؽ�Ʈ�並 tv�� �־� �Ӽ���� �ϱ����� ����
		// tv = (TextView) findViewById(R.id.textView1);

		if (btService == null) {
			btService = new BluetoothService(getActivity(), mHandler); // bluetoothservice.java
			// ���� �Ѿ���� ��
		}
		return rootView;
	}

	public void onActivityCreadred(Bundle savedInstanceState) { // oncreated�������
																// ����

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.Image_btn) { // ���� ��ư
			if (btService.getDeviceState()) { // ��������� ���� ������ ����� ��
				btService.enableBluetooth(); // ������� ON �Ұ���
			} else {
				getActivity().finish();
			}

		}

		if (v.getId() == R.id.exit) {
			AlertDialog.Builder alert_confirm = new AlertDialog.Builder(getActivity());
			alert_confirm.setMessage("���α׷��� ���� �Ͻðڽ��ϱ�? (���� �����˴ϴ�.)")
						 .setCancelable(false)
						 .setPositiveButton("Ȯ��",
			new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog,	int which) {
					android.os.Process.killProcess(android.os.Process.myPid()); // ��&���μ��� ����
				}
			}).setNegativeButton("���", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog,int which) {
									// 'No'
									return;
								}
							});
			AlertDialog alert = alert_confirm.create();
			alert.show();
		}
		if (v.getId() == R.id.button1 && real_count >= 8) {
			iv.setImageResource(R.drawable.full_water);
			iv1.setImageResource(R.drawable.full_water);
			iv2.setImageResource(R.drawable.full_water);
			iv3.setImageResource(R.drawable.full_water);
			iv4.setImageResource(R.drawable.full_water);
			iv5.setImageResource(R.drawable.full_water);
			iv6.setImageResource(R.drawable.full_water);
			iv7.setImageResource(R.drawable.full_water);
			real_count = real_count + 1;
			cup.setText(Integer.toString(real_count));
			count = count +1;
		}
		if (v.getId() == R.id.button1 && real_count == 7) {
			iv.setImageResource(R.drawable.full_water);
			iv1.setImageResource(R.drawable.full_water);
			iv2.setImageResource(R.drawable.full_water);
			iv3.setImageResource(R.drawable.full_water);
			iv4.setImageResource(R.drawable.full_water);
			iv5.setImageResource(R.drawable.full_water);
			iv6.setImageResource(R.drawable.full_water);
			iv7.setImageResource(R.drawable.full_water);
			real_count = real_count + 1;
			cup.setText(Integer.toString(real_count));
			count = count + 1;
		}
		if (v.getId() == R.id.button1 && real_count == 6) {
			iv.setImageResource(R.drawable.full_water);
			iv1.setImageResource(R.drawable.full_water);
			iv2.setImageResource(R.drawable.full_water);
			iv3.setImageResource(R.drawable.full_water);
			iv4.setImageResource(R.drawable.full_water);
			iv5.setImageResource(R.drawable.full_water);
			iv6.setImageResource(R.drawable.full_water);
			iv7.setImageResource(R.drawable.water);
			real_count = real_count + 1;
			cup.setText(Integer.toString(real_count));
			count = count + 1;
		}
		if (v.getId() == R.id.button1 && real_count == 5) {
			iv.setImageResource(R.drawable.full_water);
			iv1.setImageResource(R.drawable.full_water);
			iv2.setImageResource(R.drawable.full_water);
			iv3.setImageResource(R.drawable.full_water);
			iv4.setImageResource(R.drawable.full_water);
			iv5.setImageResource(R.drawable.full_water);
			iv6.setImageResource(R.drawable.water);
			iv7.setImageResource(R.drawable.water);
			real_count = real_count + 1;
			cup.setText(Integer.toString(real_count));
			count = count + 1;
		}
		if (v.getId() == R.id.button1 && real_count == 4) {
			iv.setImageResource(R.drawable.full_water);
			iv1.setImageResource(R.drawable.full_water);
			iv2.setImageResource(R.drawable.full_water);
			iv3.setImageResource(R.drawable.full_water);
			iv4.setImageResource(R.drawable.full_water);
			iv5.setImageResource(R.drawable.water);
			iv6.setImageResource(R.drawable.water);
			iv7.setImageResource(R.drawable.water);
			real_count = real_count + 1;
			cup.setText(Integer.toString(real_count));
			count = count + 1;
		}
		if (v.getId() == R.id.button1 && real_count == 3) {
			iv.setImageResource(R.drawable.full_water);
			iv1.setImageResource(R.drawable.full_water);
			iv2.setImageResource(R.drawable.full_water);
			iv3.setImageResource(R.drawable.full_water);
			iv4.setImageResource(R.drawable.water);
			iv5.setImageResource(R.drawable.water);
			iv6.setImageResource(R.drawable.water);
			iv7.setImageResource(R.drawable.water);
			real_count = real_count + 1;
			cup.setText(Integer.toString(real_count));
			count = count + 1;
		}
		if (v.getId() == R.id.button1 && real_count == 2) {
			iv.setImageResource(R.drawable.full_water);
			iv1.setImageResource(R.drawable.full_water);
			iv2.setImageResource(R.drawable.full_water);
			iv3.setImageResource(R.drawable.water);
			iv4.setImageResource(R.drawable.water);
			iv5.setImageResource(R.drawable.water);
			iv6.setImageResource(R.drawable.water);
			iv7.setImageResource(R.drawable.water);
			real_count = real_count + 1;
			cup.setText(Integer.toString(real_count));
			count = count + 1;
		}
		if (v.getId() == R.id.button1 && real_count == 1) {
			iv.setImageResource(R.drawable.full_water);
			iv1.setImageResource(R.drawable.full_water);
			iv2.setImageResource(R.drawable.water);
			iv3.setImageResource(R.drawable.water);
			iv4.setImageResource(R.drawable.water);
			iv5.setImageResource(R.drawable.water);
			iv6.setImageResource(R.drawable.water);
			iv7.setImageResource(R.drawable.water);
			real_count = real_count + 1;
			cup.setText(Integer.toString(real_count));
			count = count + 1;
		}
		if (v.getId() == R.id.button1 && real_count == 0) {
			iv.setImageResource(R.drawable.full_water);
			iv1.setImageResource(R.drawable.water);
			iv2.setImageResource(R.drawable.water);
			iv3.setImageResource(R.drawable.water);
			iv4.setImageResource(R.drawable.water);
			iv5.setImageResource(R.drawable.water);
			iv6.setImageResource(R.drawable.water);
			iv7.setImageResource(R.drawable.water);
			real_count = real_count + 1;
			cup.setText(Integer.toString(real_count));
			count = count + 1;
		} 
		
		/// -1 ��ư		
		if (v.getId() == R.id.button2 && real_count == 0) {
			iv.setImageResource(R.drawable.water);
			iv1.setImageResource(R.drawable.water);
			iv2.setImageResource(R.drawable.water);
			iv3.setImageResource(R.drawable.water);
			iv4.setImageResource(R.drawable.water);
			iv5.setImageResource(R.drawable.water);
			iv6.setImageResource(R.drawable.water);
			iv7.setImageResource(R.drawable.water);
			cup.setText(Integer.toString(real_count));	
		}
		if (v.getId() == R.id.button2 && real_count == 1) {
			iv.setImageResource(R.drawable.water);
			iv1.setImageResource(R.drawable.water);
			iv2.setImageResource(R.drawable.water);
			iv3.setImageResource(R.drawable.water);
			iv4.setImageResource(R.drawable.water);
			iv5.setImageResource(R.drawable.water);
			iv6.setImageResource(R.drawable.water);
			iv7.setImageResource(R.drawable.water);
			real_count = real_count - 1;
			cup.setText(Integer.toString(real_count));
			count = count - 1;	
		}
		if (v.getId() == R.id.button2 && real_count == 2) {
			iv.setImageResource(R.drawable.full_water);
			iv1.setImageResource(R.drawable.water);
			iv2.setImageResource(R.drawable.water);
			iv3.setImageResource(R.drawable.water);
			iv4.setImageResource(R.drawable.water);
			iv5.setImageResource(R.drawable.water);
			iv6.setImageResource(R.drawable.water);
			iv7.setImageResource(R.drawable.water);
			real_count = real_count - 1;
			cup.setText(Integer.toString(real_count));
			count = count - 1;	
		}
		if (v.getId() == R.id.button2 && real_count == 3) {
			iv.setImageResource(R.drawable.full_water);
			iv1.setImageResource(R.drawable.full_water);
			iv2.setImageResource(R.drawable.water);
			iv3.setImageResource(R.drawable.water);
			iv4.setImageResource(R.drawable.water);
			iv5.setImageResource(R.drawable.water);
			iv6.setImageResource(R.drawable.water);
			iv7.setImageResource(R.drawable.water);
			real_count = real_count - 1;
			cup.setText(Integer.toString(real_count));
			count = count - 1;	
		}
		if (v.getId() == R.id.button2 && real_count == 4) {
			iv.setImageResource(R.drawable.full_water);
			iv1.setImageResource(R.drawable.full_water);
			iv2.setImageResource(R.drawable.full_water);
			iv3.setImageResource(R.drawable.water);
			iv4.setImageResource(R.drawable.water);
			iv5.setImageResource(R.drawable.water);
			iv6.setImageResource(R.drawable.water);
			iv7.setImageResource(R.drawable.water);
			real_count = real_count - 1;
			cup.setText(Integer.toString(real_count));
			count = count - 1;	
		}
		if (v.getId() == R.id.button2 && real_count == 5) {
			iv.setImageResource(R.drawable.full_water);
			iv1.setImageResource(R.drawable.full_water);
			iv2.setImageResource(R.drawable.full_water);
			iv3.setImageResource(R.drawable.full_water);
			iv4.setImageResource(R.drawable.water);
			iv5.setImageResource(R.drawable.water);
			iv6.setImageResource(R.drawable.water);
			iv7.setImageResource(R.drawable.water);
			real_count = real_count - 1;
			cup.setText(Integer.toString(real_count));
			count = count - 1;	
		}
		if (v.getId() == R.id.button2 && real_count == 6) {
			iv.setImageResource(R.drawable.full_water);
			iv1.setImageResource(R.drawable.full_water);
			iv2.setImageResource(R.drawable.full_water);
			iv3.setImageResource(R.drawable.full_water);
			iv4.setImageResource(R.drawable.full_water);
			iv5.setImageResource(R.drawable.water);
			iv6.setImageResource(R.drawable.water);
			iv7.setImageResource(R.drawable.water);
			real_count = real_count - 1;
			cup.setText(Integer.toString(real_count));
			count = count - 1;	
		}
		if (v.getId() == R.id.button2 && real_count == 7) {
			iv.setImageResource(R.drawable.full_water);
			iv1.setImageResource(R.drawable.full_water);
			iv2.setImageResource(R.drawable.full_water);
			iv3.setImageResource(R.drawable.full_water);
			iv4.setImageResource(R.drawable.full_water);
			iv5.setImageResource(R.drawable.full_water);
			iv6.setImageResource(R.drawable.water);
			iv7.setImageResource(R.drawable.water);
			real_count = real_count - 1;
			cup.setText(Integer.toString(real_count));
			count = count - 1;	
		}
		if (v.getId() == R.id.button2 && real_count == 8) {
			iv.setImageResource(R.drawable.full_water);
			iv1.setImageResource(R.drawable.full_water);
			iv2.setImageResource(R.drawable.full_water);
			iv3.setImageResource(R.drawable.full_water);
			iv4.setImageResource(R.drawable.full_water);
			iv5.setImageResource(R.drawable.full_water);
			iv6.setImageResource(R.drawable.full_water);
			iv7.setImageResource(R.drawable.water);
			real_count = real_count - 1;
			cup.setText(Integer.toString(real_count));
			count = count - 1;	
		}
		if (v.getId() == R.id.button2 && real_count >= 9) {
			real_count = real_count - 1;
			cup.setText(Integer.toString(real_count));
			count = count - 1;	
		}
	}

	public void onActivityResult(int request, int resultCode, Intent data) {
		Log.d(TAG, "onActivityResult " + resultCode);
		Log.d(TAG, "request : " + request);

		switch (request) {

		/** �߰��� �κ� ���� **/
		case REQUEST_CONNECT_DEVICE:
			// When DeviceListActivity returns with a device to connect
			if (resultCode == Activity.RESULT_OK) {
				btService.getDeviceInfo(data);

			}
			break;
		/** �߰��� �κ� �� **/
		case REQUEST_ENABLE_BT:
			// When the request to enable Bluetooth returns
			if (resultCode == Activity.RESULT_OK) {
				// Next Step
				btService.scanDevice();
			} else {
				Toast.makeText(getActivity().getApplicationContext(),
						"��������� ����Ҽ������ϴ�", Toast.LENGTH_SHORT).show();
				Log.d(TAG, "Bluetooth is not enabled");
			}
			break;
		}
	}

	private void setLayout(){
		
	}
	@SuppressLint("HandlerLeak")
	private final Handler mHandler = new Handler() {
		
		public void handleMessage(Message msg) {
			
			switch (msg.what) {

			case MESSAGE_READ:
				byte[] readBuf = (byte[]) msg.obj;

				// construct a string from the valid bytes in the buffer
				
				readMessage = new String(readBuf, 0, msg.arg1);

				Log.d(TAG, "arg :::" + msg.arg1);
				if(msg.arg1>=6 && msg.arg1<=10 && readMessage.indexOf("@")>=0 && readMessage.indexOf("#")<readMessage.length()) {
					readMessage3 = readMessage.substring(readMessage.indexOf("@")+1, readMessage.indexOf("#"));
					readMessage4 = readMessage.substring(readMessage.indexOf("#") + 1, readMessage.length());
					
					temp.setText(readMessage4);
					if(Integer.parseInt(cup_count)!=Integer.parseInt(readMessage3)) {
						total_count = readMessage3;
						
						real_count = count + Integer.parseInt(total_count);
						
						if (real_count >= 8) {
							iv.setImageResource(R.drawable.full_water);
							iv1.setImageResource(R.drawable.full_water);
							iv2.setImageResource(R.drawable.full_water);
							iv3.setImageResource(R.drawable.full_water);
							iv4.setImageResource(R.drawable.full_water);
							iv5.setImageResource(R.drawable.full_water);
							iv6.setImageResource(R.drawable.full_water);
							iv7.setImageResource(R.drawable.full_water);
							cup.setText(Integer.toString(real_count));
						} else if (real_count == 7) {
							iv.setImageResource(R.drawable.full_water);
							iv1.setImageResource(R.drawable.full_water);
							iv2.setImageResource(R.drawable.full_water);
							iv3.setImageResource(R.drawable.full_water);
							iv4.setImageResource(R.drawable.full_water);
							iv5.setImageResource(R.drawable.full_water);
							iv6.setImageResource(R.drawable.full_water);
							iv7.setImageResource(R.drawable.water);
							cup.setText(Integer.toString(real_count));
						} else if (real_count == 6) {
							iv.setImageResource(R.drawable.full_water);
							iv1.setImageResource(R.drawable.full_water);
							iv2.setImageResource(R.drawable.full_water);
							iv3.setImageResource(R.drawable.full_water);
							iv4.setImageResource(R.drawable.full_water);
							iv5.setImageResource(R.drawable.full_water);
							iv6.setImageResource(R.drawable.water);
							iv7.setImageResource(R.drawable.water);
							cup.setText(Integer.toString(real_count));
						} else if (real_count == 5) {
							iv.setImageResource(R.drawable.full_water);
							iv1.setImageResource(R.drawable.full_water);
							iv2.setImageResource(R.drawable.full_water);
							iv3.setImageResource(R.drawable.full_water);
							iv4.setImageResource(R.drawable.full_water);
							iv5.setImageResource(R.drawable.water);
							iv6.setImageResource(R.drawable.water);
							iv7.setImageResource(R.drawable.water);
							cup.setText(Integer.toString(real_count));
						} else if (real_count == 4) {
							iv.setImageResource(R.drawable.full_water);
							iv1.setImageResource(R.drawable.full_water);
							iv2.setImageResource(R.drawable.full_water);
							iv3.setImageResource(R.drawable.full_water);
							iv4.setImageResource(R.drawable.water);
							iv5.setImageResource(R.drawable.water);
							iv6.setImageResource(R.drawable.water);
							iv7.setImageResource(R.drawable.water);
							cup.setText(Integer.toString(real_count));
						} else if (real_count == 3) {
							iv.setImageResource(R.drawable.full_water);
							iv1.setImageResource(R.drawable.full_water);
							iv2.setImageResource(R.drawable.full_water);
							iv3.setImageResource(R.drawable.water);
							iv4.setImageResource(R.drawable.water);
							iv5.setImageResource(R.drawable.water);
							iv6.setImageResource(R.drawable.water);
							iv7.setImageResource(R.drawable.water);
							cup.setText(Integer.toString(real_count));
						} else if (real_count == 2) {
							iv.setImageResource(R.drawable.full_water);
							iv1.setImageResource(R.drawable.full_water);
							iv2.setImageResource(R.drawable.water);
							iv3.setImageResource(R.drawable.water);
							iv4.setImageResource(R.drawable.water);
							iv5.setImageResource(R.drawable.water);
							iv6.setImageResource(R.drawable.water);
							iv7.setImageResource(R.drawable.water);
							cup.setText(Integer.toString(real_count));
						} else if (real_count == 1) {
							iv.setImageResource(R.drawable.full_water);
							iv1.setImageResource(R.drawable.water);
							iv2.setImageResource(R.drawable.water);
							iv3.setImageResource(R.drawable.water);
							iv4.setImageResource(R.drawable.water);
							iv5.setImageResource(R.drawable.water);
							iv6.setImageResource(R.drawable.water);
							iv7.setImageResource(R.drawable.water);
							cup.setText(Integer.toString(real_count));
						} else if (real_count == 0) {
							iv.setImageResource(R.drawable.water);
							iv1.setImageResource(R.drawable.water);
							iv2.setImageResource(R.drawable.water);
							iv3.setImageResource(R.drawable.water);
							iv4.setImageResource(R.drawable.water);
							iv5.setImageResource(R.drawable.water);
							iv6.setImageResource(R.drawable.water);
							iv7.setImageResource(R.drawable.water);
							cup.setText(Integer.toString(real_count));
						}
					}
					cup_count=readMessage3;
					temp_count=readMessage4;
				}
				
				blue_state=1;
				//mConversationArrayAdapter.add(readMessage);
				
				break;

			case MESSAGE_DEVICE_NAME:
				// save the connected device's name
				a = msg.getData().getString(DEVICE_NAME);
				Toast.makeText(getActivity().getApplicationContext(),
						"Connected to " + a, Toast.LENGTH_SHORT).show();
				blue_state=1;
				Image_btn.setImageResource(R.drawable.bt_pair);
				break;

			case CONNECT_FAIL:
				// save the connected device's name
				Log.d(TAG, "change red");
				Toast.makeText(getActivity().getApplicationContext(),
						"Not Connect", Toast.LENGTH_SHORT).show();
				Image_btn.setImageResource(R.drawable.bt);
				blue_state=0;
				break;

			}
		}
	};

}
