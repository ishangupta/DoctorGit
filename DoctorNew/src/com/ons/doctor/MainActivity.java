package com.ons.doctor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{
	
	TextView tv_findDoctor,tv_about;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		tv_about=(TextView)findViewById(R.id.tv_about);
		tv_about.setOnClickListener(this);
		tv_findDoctor=(TextView)findViewById(R.id.tv_findDoctor);
		tv_findDoctor.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.tv_about:
			break;
		case R.id.tv_findDoctor:
			startActivity(new Intent(MainActivity.this,SearchScreen.class));
			break;
		}
		
	}

	
}
