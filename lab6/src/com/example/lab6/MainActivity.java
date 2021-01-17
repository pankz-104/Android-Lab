package com.example.lab6;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	Button btnstart, btnstop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnstart = (Button) findViewById(R.id.btn_start);
		btnstart.setOnClickListener(this);
		btnstop = (Button) findViewById(R.id.btn_stop);
		btnstop.setOnClickListener(this);
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
			Intent it = new Intent(this, ServiceClass.class);
			Bundle b = new Bundle();
			b.putBoolean("stop", true);
			it.putExtra("data", b);
			startService(it);
		} else {
			Intent it = new Intent(this, ServiceClass.class);
			stopService(it);
		}
	}
}
