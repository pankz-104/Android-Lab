package com.example.lab1;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	EditText txtid, txtname, txtage, txtaddress;
	Button btnsubmit, btnsearch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txtid = (EditText) findViewById(R.id.txt_id);
		txtname = (EditText) findViewById(R.id.txt_name);
		txtage = (EditText) findViewById(R.id.txt_age);
		txtaddress = (EditText) findViewById(R.id.txt_address);
		btnsubmit = (Button) findViewById(R.id.btn_submit);
		btnsubmit.setOnClickListener(this);
		btnsearch = (Button) findViewById(R.id.btn_search);
		btnsearch.setOnClickListener(this);
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
		Toast.makeText(this, "buttonclicked", 15000).show();
		if (v.equals(btnsubmit)) {
			String sid = txtid.getText().toString();
			String sname = txtname.getText().toString();
			String sage = txtage.getText().toString();
			String saddress = txtaddress.getText().toString();
			MyDatabase dat = new MyDatabase(this, MyDatabase.DATABASE_NAME,
					null, 1);
			SQLiteDatabase database = dat.getWritableDatabase();
			ContentValues cv = new ContentValues();
			cv.put("id", sid);
			cv.put("name", sname);
			cv.put("age", sage);
			cv.put("address", saddress);
			database.insert("Employee", null, cv);
			database.close();
			Toast.makeText(this, "Data Inserted successfully", 15000).show();
		} else if (v.equals(btnsearch)) {
			Intent it = new Intent(this, SearchActivity.class);
			startActivity(it);
		}
	}
}
