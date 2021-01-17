package com.example.lab2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	TextView lblFile;
	EditText txtContent;
	Button btnCreate, btnSave, btnOpen;
	int FILE_CHOOSE_REQUEST = 1;
	String filepath;
	String filename;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lblFile = (TextView) findViewById(R.id.lbl_file);

		txtContent = (EditText) findViewById(R.id.txt_content);

		btnCreate = (Button) findViewById(R.id.btn_create);
		btnCreate.setOnClickListener(this);

		btnSave = (Button) findViewById(R.id.btn_save);
		btnSave.setOnClickListener(this);

		btnOpen = (Button) findViewById(R.id.btn_open);
		btnOpen.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			filepath = data.getData().getPath();
			filename = filepath.substring(filepath.lastIndexOf("/") + 1);
			filepath = filepath.substring(0, filepath.lastIndexOf("/"));

			readFromFile(filepath, filename);
			lblFile.setText(filepath + "/" + filename);
		} else {
			Toast.makeText(this, "Wrong Choice of File", Toast.LENGTH_LONG)
					.show();
		}

	}

	public void writeToFile(String path, String filename) {
		try {
			FileOutputStream fileout = new FileOutputStream(new File(path + "/"
					+ filename));
			OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
			outputWriter.write(txtContent.getText().toString());
			outputWriter.close();
			// display file saved message
			Toast.makeText(getBaseContext(), "File Saved successfully!",
					Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Toast.makeText(getBaseContext(), e.getLocalizedMessage(),
					Toast.LENGTH_SHORT).show();
		}
	}

	public void readFromFile(String path, String filename) {
		try {
			FileInputStream fileIn = new FileInputStream(path + "/" + filename);
			InputStreamReader inputReader = new InputStreamReader(fileIn);
			BufferedReader br = new BufferedReader(inputReader);
			String data = br.readLine();
			while (data != null) {
				txtContent.append(data);
				data = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			Toast.makeText(getBaseContext(), e.getLocalizedMessage(),
					Toast.LENGTH_SHORT).show();
		}
	}

	public void onShowCreateDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final View dialogView = getLayoutInflater().inflate(
				R.layout.dialog_layout, null);
		builder.setView(dialogView);
		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				EditText txtFilename = (EditText) dialogView
						.findViewById(R.id.txt_filename);
				filepath = Environment.getExternalStorageDirectory()
						.getAbsolutePath();
				filename = txtFilename.getText().toString();
				File f = new File(filepath + "/" + filename);
				try {
					f.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Toast.makeText(getBaseContext(),
							"" + e.getLocalizedMessage(), Toast.LENGTH_LONG)
							.show();
				}
				lblFile.setText(filepath + "/" + filename);
			}
		});
		builder.setNegativeButton("Cancel", null);
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.equals(btnOpen)) {
			Intent it = new Intent(Intent.ACTION_GET_CONTENT);
			// it.setType("*.*");
			it.setType("file/*");
			startActivityForResult(it, 0);
		} else if (v.equals(btnCreate)) {
			onShowCreateDialog();
		} else if (v.equals(btnSave)) {
			writeToFile(filepath, filename);
		}
	}

}