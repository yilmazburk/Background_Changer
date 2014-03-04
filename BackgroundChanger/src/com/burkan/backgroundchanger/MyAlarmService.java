package com.burkan.backgroundchanger;

import java.io.IOException;
import java.util.Calendar;

import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.Toast;

public class MyAlarmService extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		Bitmap bmp = null;
		
		if(hour < 6 || hour>19)
			bmp = BitmapFactory.decodeResource(context.getResources(),R.drawable.bg_night_1440x900);
		else if(hour < 21 && hour > 16)
			bmp = BitmapFactory.decodeResource(context.getResources(),R.drawable.bg_afternoon_1920_1200);
		else
			bmp = BitmapFactory.decodeResource(context.getResources(),R.drawable.bg_morning_1280x800);
		BitmapDrawable bitmapDrawable = new BitmapDrawable(bmp);
	    WallpaperManager m=WallpaperManager.getInstance(context);
	    try {
	    	bmp = Bitmap.createBitmap(bmp, (int)(bmp.getWidth()/5)*2, 0, bmp.getWidth()/2, bmp.getHeight());
	        m.setBitmap(bmp);
	        Toast.makeText(context, "Wallpaper Updated", Toast.LENGTH_LONG).show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	

}
