package com.example.lab3b;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	EditText txtSearch;
	Button btnSearch;
	TextView lblMessage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txtSearch = (EditText) findViewById(R.id.txt_search);
		lblMessage = (TextView) findViewById(R.id.lbl_message);

		btnSearch = (Button) findViewById(R.id.btn_search);
		btnSearch.setOnClickListener(this);

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
		if (v.equals(btnSearch)) {
			String searchDate = txtSearch.getText().toString();
			String where = "note_date=?";
			Cursor cursor = getContentResolver().query(
					Uri.parse("content://com.example.notesprovider/notes"),
					new String[] { "note_date", "content" }, where,
					new String[] { searchDate }, null);
			if (cursor != null && cursor.moveToNext()) {

				String ndate = cursor.getString(0);
				String content = cursor.getString(1);

				lblMessage.setText(ndate + " " + content + "\n");
			} else {
				Toast.makeText(getBaseContext(), "No Data Available",
						Toast.LENGTH_LONG).show();
			}
		}
	}

}