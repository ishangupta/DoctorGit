package com.ons.doctor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Window;

public class SplashActivity extends Activity{
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);
       MyDatabase myDatabase=new MyDatabase(SplashActivity.this);
        final int welcomeScreenDisplay = 6000;
		Thread welcomeThread = new Thread() {

			int wait = 0;

			@Override
			public void run() {
				try {
					while (wait < welcomeScreenDisplay) {
						sleep(1000);
						wait += 3000;
					}
				} catch (Exception e) {
					System.out.println("EXc=" + e);
				} finally {
			        	Intent i=new Intent(SplashActivity.this,MainActivity.class);
						startActivity(i);
						finish();
				}
			}
		};
		welcomeThread.start();
    }

}
