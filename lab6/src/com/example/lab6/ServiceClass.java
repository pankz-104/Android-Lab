package com.example.lab6;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.view.ViewDebug.FlagToString;
import android.widget.Toast;

public class ServiceClass extends Service {
	boolean running = false;
	MyThread thread;

	public void onCreate() {
		super.onCreate();
		Toast.makeText(getBaseContext(), "Service Created", Toast.LENGTH_LONG)
				.show();
		running = true;
		thread = new MyThread();
		thread.start();
	}

	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		Toast.makeText(getBaseContext(), "Service started", Toast.LENGTH_LONG)
				.show();
		Bundle b = intent.getBundleExtra("data");
		running = b.getBoolean("stop");
		if (!thread.isAlive()) {
			thread = new MyThread();
			thread.start();
		}
		return Service.START_NOT_STICKY;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void onDestroy() {
		running = false;
		Toast.makeText(getBaseContext(), "Service stoped", Toast.LENGTH_LONG)
				.show();
		super.onDestroy();
	}

	Handler hand = new Handler() {
		public void handleMessage(Message m) {
			NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			NotificationCompat.Builder builder = new NotificationCompat.Builder(
					getBaseContext());
			builder.setContentTitle("From Service");
			builder.setContentText("Hai " + m.what);
			builder.setSmallIcon(R.drawable.ic_launcher);
			builder.setContentIntent(PendingIntent.getActivity(
					getBaseContext(), 1, new Intent(getBaseContext(),
							MainActivity.class), 1));
			Notification nof = builder.build();
			manager.notify(100, nof);
		}
	};

	class MyThread extends Thread {
		public void run() {
			int i = 0;
			while (running) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				hand.sendEmptyMessage(i++);
			}
		}
	}
}