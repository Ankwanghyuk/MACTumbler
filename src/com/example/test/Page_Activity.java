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
		startActivity(new Intent(this, Splash.class)); // splash ����
		setContentView(R.layout.activity_main);

		final ActionBar actionBar = getSupportActionBar();
		getSupportActionBar().setStackedBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#3DB7CC"))); // �� �޴� ���� ����
		actionBar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#034EA2"))); // �ֻ�� ����
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true); // �� ���� ǥ��
		actionBar.setDisplayShowHomeEnabled(false);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN); // ���¹� �����(Ǯȭ��)
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
																			// ������
																			// ��
																			// ����
																			// ����
			actionBar.addTab(actionBar.newTab().setIcon(R.drawable.info_white)
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
		if (mSectionsPagerAdapter.getCount() == 4) { // tab ������ �� ���� ���� (������
														// ��Ϲ��� �κ�)
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
					.setMessage("���α׷��� ���� �Ͻðڽ��ϱ�? (���� �����˴ϴ�.)")
					.setCancelable(false)
					.setPositiveButton("Ȯ��",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									android.os.Process
											.killProcess(android.os.Process
													.myPid()); // ��&���μ��� ����
								}
							})
					.setNegativeButton("���",
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
			tab.setIcon(getResources().getDrawable(R.drawable.info_black)); // ������
																			// �̵���
																			// �̹���
																			// ��ü
		else if (tab.getPosition() == TWO_TAB)
			tab.setIcon(getResources().getDrawable(R.drawable.info_black)); //
		else if (tab.getPosition() == THIRD_TAB)
			tab.setIcon(getResources().getDrawable(R.drawable.info_black)); //
		else if (tab.getPosition() == SETUP_TAB)
			tab.setIcon(getResources().getDrawable(R.drawable.setup)); //

		// tab.setIcon(getResources().getDrawable(R.drawable.info_black)); //
		// �׼ǹ�
		// tab.getIcon().setAlpha(255);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		if (tab.getPosition() == MAIN_TAB)
			tab.setIcon(getResources().getDrawable(R.drawable.info_white)); // �׼ǹ�
		else if (tab.getPosition() == TWO_TAB)
			tab.setIcon(getResources().getDrawable(R.drawable.info_white)); // �׼ǹ�
		else if (tab.getPosition() == THIRD_TAB)
			tab.setIcon(getResources().getDrawable(R.drawable.info_white)); // �׼ǹ�
		else if (tab.getPosition() == SETUP_TAB)
			tab.setIcon(getResources().getDrawable(R.drawable.setup)); // �׼ǹ�
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
		public int getCount() { // / �� �޴� ����
			// Show 3 total pages.
			return 4;
		}

		@Override
		public CharSequence getPageTitle(int position) { // // �� �̸� ����
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
			// �� �κ��� Ư�� Ű�� �������� ���� �ȴ�.
			// ���� �ڷ� ��ư�� �������� �� �ൿ�� �����ϰ� �ʹٸ�
			if (KeyCode == KeyEvent.KEYCODE_BACK) {
				AlertDialog.Builder alert_confirm = new AlertDialog.Builder(
						Page_Activity.this);
				alert_confirm
						.setMessage("���α׷��� ���� �Ͻðڽ��ϱ�? (���� �����˴ϴ�.)")
						.setCancelable(false)
						.setPositiveButton("Ȯ��",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										android.os.Process
												.killProcess(android.os.Process
														.myPid()); // ��&���μ��� ����
									}
								})
						.setNegativeButton("���",
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
				// ���⼭ ���ϰ��� �߿��ѵ�; ���ϰ��� true �̳� false �̳Ŀ� ���� �ൿ�� �޶�����.
				// true �ϰ�� back ��ư�� �⺻������ ���Ḧ �����ϰ� �ȴ�.
				// ������ false �� ��� back ��ư�� �⺻������ ���� �ʴ´�.
				// back ��ư�� �������� ������� �ʰ� �ϰ� �ʹٸ� ���⼭ false �� �����ϸ� �ȴ�.
				// back ��ư�� �⺻������ ������ ���ø����̼��� ������ ����� ���⶧����
				// ���� �����ϴ� ����� �����ؾ��Ѵ�.
			}
		}
		return super.onKeyDown(KeyCode, event);
	}
}
