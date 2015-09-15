package com.example.test;

import java.util.Locale;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

public class Page_Activity extends ActionBarActivity implements
		ActionBar.TabListener {

	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;

	private static final int MAIN_TAB = 0;
	private static final int TWO_TAB = 1;
	private static final int THIRD_TAB = 2;
	private static final int SETUP_TAB = 3;
	private static final String TAG = "Page";

	private Fragment frag_1;
	Fragment fragment = frag_1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		startActivity(new Intent(this, Splash.class)); // splash 설정
		setContentView(R.layout.activity_main);

		final ActionBar actionBar = getSupportActionBar();
		getSupportActionBar().setStackedBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#3DB7CC"))); // 탭 메뉴 색상 변경
		actionBar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#034EA2"))); // 최상단 색깔
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true); // 앱 제목 표시
		actionBar.setDisplayShowHomeEnabled(false);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN); // 상태바 숨기기(풀화면)
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount() - 1; i++) { // tab
																			// 아이콘
																			// 및
																			// 제목
																			// 설정
			actionBar.addTab(actionBar.newTab().setIcon(R.drawable.info_white)
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
		if (mSectionsPagerAdapter.getCount() == 4) { // tab 아이콘 및 제목 설정 (마지막
														// 톱니바퀴 부분)
			actionBar.addTab(actionBar.newTab().setIcon(R.drawable.setup)
					.setText(mSectionsPagerAdapter.getPageTitle(4))
					.setTabListener(this));
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.action_settings2) {
			AlertDialog.Builder alert_confirm = new AlertDialog.Builder(
					Page_Activity.this);
			alert_confirm
					.setMessage("프로그램을 종료 하시겠습니까? (페어링도 해제됩니다.)")
					.setCancelable(false)
					.setPositiveButton("확인",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									android.os.Process
											.killProcess(android.os.Process
													.myPid()); // 앱&프로세스 종료
								}
							})
					.setNegativeButton("취소",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// 'No'
									return;
								}
							});
			AlertDialog alert = alert_confirm.create();
			alert.show();
		}
		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
		if (tab.getPosition() == MAIN_TAB)
			tab.setIcon(getResources().getDrawable(R.drawable.info_black)); // 탭으로
																			// 이동시
																			// 이미지
																			// 교체
		else if (tab.getPosition() == TWO_TAB)
			tab.setIcon(getResources().getDrawable(R.drawable.info_black)); //
		else if (tab.getPosition() == THIRD_TAB)
			tab.setIcon(getResources().getDrawable(R.drawable.info_black)); //
		else if (tab.getPosition() == SETUP_TAB)
			tab.setIcon(getResources().getDrawable(R.drawable.setup)); //

		// tab.setIcon(getResources().getDrawable(R.drawable.info_black)); //
		// 액션바
		// tab.getIcon().setAlpha(255);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		if (tab.getPosition() == MAIN_TAB)
			tab.setIcon(getResources().getDrawable(R.drawable.info_white)); // 액션바
		else if (tab.getPosition() == TWO_TAB)
			tab.setIcon(getResources().getDrawable(R.drawable.info_white)); // 액션바
		else if (tab.getPosition() == THIRD_TAB)
			tab.setIcon(getResources().getDrawable(R.drawable.info_white)); // 액션바
		else if (tab.getPosition() == SETUP_TAB)
			tab.setIcon(getResources().getDrawable(R.drawable.setup)); // 액션바
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {

			switch (position) {
			case 0:
				return new Fragment_Menu1();
			case 1:
				return new Fragment_Menu2();
			case 2:
				return new Fragment_Menu3();
			case 3:
				return new Fragment_Menu4();
			}

			return null;

			// return PlaceholderFragment.newInstance(position + 1);
		}

		@Override
		public int getCount() { // / 탭 메뉴 갯수
			// Show 3 total pages.
			return 4;
		}

		@Override
		public CharSequence getPageTitle(int position) { // // 탭 이름 설정
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			case 3:
				return getString(R.string.title_section4).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		private static final String ARG_SECTION_NUMBER = "section_number";

		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main_1,
					container, false);
			return rootView;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		String a = Integer.toString(requestCode);
		String b = Integer.toString(resultCode);

		int request = requestCode & 0xffff;

		for (Fragment fragment : getSupportFragmentManager().getFragments()) {
			fragment.onActivityResult(request, resultCode, data);
		}

	}

	public boolean onKeyDown(int KeyCode, KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			// 이 부분은 특정 키를 눌렀을때 실행 된다.
			// 만약 뒤로 버튼을 눌럿을때 할 행동을 지정하고 싶다면
			if (KeyCode == KeyEvent.KEYCODE_BACK) {
				AlertDialog.Builder alert_confirm = new AlertDialog.Builder(
						Page_Activity.this);
				alert_confirm
						.setMessage("프로그램을 종료 하시겠습니까? (페어링도 해제됩니다.)")
						.setCancelable(false)
						.setPositiveButton("확인",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										android.os.Process
												.killProcess(android.os.Process
														.myPid()); // 앱&프로세스 종료
									}
								})
						.setNegativeButton("취소",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// 'No'
										return;
									}
								});
				AlertDialog alert = alert_confirm.create();
				alert.show();
				return false;
				// 여기서 리턴값이 중요한데; 리턴값이 true 이냐 false 이냐에 따라 행동이 달라진다.
				// true 일경우 back 버튼의 기본동작인 종료를 실행하게 된다.
				// 하지만 false 일 경우 back 버튼의 기본동작을 하지 않는다.
				// back 버튼을 눌렀을때 종료되지 않게 하고 싶다면 여기서 false 를 리턴하면 된다.
				// back 버튼의 기본동작을 막으면 어플리케이션을 종료할 방법이 없기때문에
				// 따로 종료하는 방법을 마련해야한다.
			}
		}
		return super.onKeyDown(KeyCode, event);
	}
}
