package com.example.lab4;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener, Runnable {
	int i = 0;
	TextView lblcounter;
	Button btnstart, btnstop;
	Thread thread;
	boolean running = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnstart = (Button) findViewById(R.id.btn_start);
		btnstop = (Button) findViewById(R.id.btn_stop);
		btnstart.setOnClickListener(this);
		btnstop.setOnClickListener(this);
		lblcounter = (TextView) findViewById(R.id.lbl_counter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.equals(btnstart)) {
			running = true;
			thread = new Thread(this);
			thread.start();
		} else if (v.equals(btnstop)) {
			// thread.interrupt();
			running = false;
		}
	}

	Handler hand = new Handler() {
		public void handleMessage(Message m) {
			lblcounter.setText("" + m.what);
		}
	};

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// int i=0;
		while (i < 100 && running) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			hand.sendEmptyMessage(i);
			i++;
			// lblcounter.setText(""+i);
		}
	}
}