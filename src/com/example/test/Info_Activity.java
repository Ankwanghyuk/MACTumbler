package com.example.test;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

public class Info_Activity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);
		ActionBar bar = getSupportActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
		bar.setTitle("Á¤º¸");
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#034EA2")));
	}

public boolean onOptionsItemSelected(android.view.MenuItem item) {
	switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
	}
	return super.onOptionsItemSelected(item);
};


}
