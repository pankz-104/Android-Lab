package com.example.lab1;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends Activity implements OnClickListener {
	EditText txtempid;
	Button btnsearch;
	TextView txtdisplay;

	public void onCreate(Bundle b) {
		super.onCreate(b);
		setContentView(R.layout.search);
		txtempid = (EditText) findViewById(R.id.txt_empid);
		btnsearch = (Button) findViewById(R.id.txt_search);
		txtdisplay = (TextView) findViewById(R.id.txt_display);
		btnsearch.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Button clicked", 15000).show();
		if (v.equals(btnsearch)) {
			String eid = txtempid.getText().toString();
			MyDatabase dat = new MyDatabase(this, MyDatabase.DATABASE_NAME,
					null, 1);
			SQLiteDatabase database = dat.getReadableDatabase();
			String[] columns = new String[] { "id", "name", "age", "address" };
			String where = "id=?";
			String[] value = new String[] { eid.trim() };
			Cursor cu = database.query(MyDatabase.EMPLOYEE_TABLE, columns,
					where, value, null, null, null);
			txtdisplay.setText("");
			if (cu.moveToNext()) {
				String id = cu.getString(0);
				String name = cu.getString(1);
				String age = cu.getString(2);
				String address = cu.getString(3);
				txtdisplay.append(id + " " + name + " " + age + " " + address
						+ "\n");
			} else {
				Toast.makeText(this, "No Id Exist", 15000).show();
			}
		}
	}
}