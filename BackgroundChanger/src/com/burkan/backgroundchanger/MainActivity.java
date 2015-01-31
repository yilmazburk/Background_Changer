package com.burkan.backgroundchanger;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	public Button set,exit;
	//a��klama asdasdasd
	private PendingIntent pendingIntent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		set = (Button) findViewById(R.id.change);
		exit = (Button) findViewById(R.id.cancel);
		set.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(MainActivity.this, MyAlarmService.class);
				pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent, 0);
	            AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
	            Calendar calendar = Calendar.getInstance();
	            calendar.setTimeInMillis(System.currentTimeMillis());
	            calendar.add(Calendar.SECOND, 2);
	            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),1000*60*60,pendingIntent);
				Toast.makeText(MainActivity.this, "Start Background Changer", Toast.LENGTH_LONG).show();
			}
		});
		
		exit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
				alarmManager.cancel(pendingIntent);
				// Tell the user about what we did.
				Toast.makeText(MainActivity.this, "Cancel!", Toast.LENGTH_LONG).show();

			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
