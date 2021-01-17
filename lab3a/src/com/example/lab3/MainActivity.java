package com.example.lab3;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	EditText txtDate, txtContent;
	Button btnAddNote;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txtDate = (EditText) findViewById(R.id.txt_date);
		txtContent = (EditText) findViewById(R.id.txt_Content);

		btnAddNote = (Button) findViewById(R.id.btn_add_note);
		btnAddNote.setOnClickListener(this);
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
		if (v.equals(btnAddNote)) {
			String sdate = txtDate.getText().toString();
			String scontent = txtContent.getText().toString();
			ContentValues values = new ContentValues();
			values.put("note_date", sdate);
			values.put("content", scontent);

			getContentResolver().insert(
					Uri.parse("content://com.example.notesprovider/notes"),
					values);
			Toast.makeText(getBaseContext(), "Data Inserted Successfully",
					Toast.LENGTH_LONG).show();
		}
	}

}
