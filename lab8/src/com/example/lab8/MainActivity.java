package com.example.lab8;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {
	EditText txtNumber;
	Button btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven,
			btnEight, btnNine, btnZero, btnCall, btnSave, btnDel, btnStar,
			btnHash;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txtNumber = (EditText) findViewById(R.id.txt_display);

		btnOne = (Button) findViewById(R.id.btn_one);
		btnOne.setOnClickListener(this);

		btnTwo = (Button) findViewById(R.id.btn_two);
		btnTwo.setOnClickListener(this);

		btnThree = (Button) findViewById(R.id.btn_three);
		btnThree.setOnClickListener(this);

		btnFour = (Button) findViewById(R.id.btn_four);
		btnFour.setOnClickListener(this);

		btnFive = (Button) findViewById(R.id.btn_five);
		btnFive.setOnClickListener(this);

		btnSix = (Button) findViewById(R.id.btn_six);
		btnSix.setOnClickListener(this);

		btnSeven = (Button) findViewById(R.id.btn_seven);
		btnSeven.setOnClickListener(this);

		btnEight = (Button) findViewById(R.id.btn_eight);
		btnEight.setOnClickListener(this);

		btnNine = (Button) findViewById(R.id.btn_nine);
		btnNine.setOnClickListener(this);

		btnZero = (Button) findViewById(R.id.btn_zero);
		btnZero.setOnClickListener(this);

		btnSave = (Button) findViewById(R.id.btn_save);
		btnSave.setOnClickListener(this);

		btnCall = (Button) findViewById(R.id.btn_call);
		btnCall.setOnClickListener(this);

		btnStar = (Button) findViewById(R.id.btn_star);
		btnStar.setOnClickListener(this);

		btnHash = (Button) findViewById(R.id.btn_ash);
		btnHash.setOnClickListener(this);

		btnDel = (Button) findViewById(R.id.btn_del);
		btnDel.setOnClickListener(this);

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
		if (v.equals(btnOne)) {
			txtNumber.append("1");
		} else if (v.equals(btnTwo)) {
			txtNumber.append("2");
		} else if (v.equals(btnThree)) {
			txtNumber.append("3");
		} else if (v.equals(btnFour)) {
			txtNumber.append("4");
		} else if (v.equals(btnFive)) {
			txtNumber.append("5");
		} else if (v.equals(btnSix)) {
			txtNumber.append("6");
		} else if (v.equals(btnSeven)) {
			txtNumber.append("7");
		} else if (v.equals(btnEight)) {
			txtNumber.append("8");
		} else if (v.equals(btnNine)) {
			txtNumber.append("9");
		} else if (v.equals(btnZero)) {
			txtNumber.append("0");
		} else if (v.equals(btnStar)) {
			txtNumber.append("*");
		} else if (v.equals(btnHash)) {
			txtNumber.append("#");
		} else if (v.equals(btnDel)) {
			String num = txtNumber.getText().toString();
			if (num.length() > 0) {
				num = num.substring(0, num.length() - 1);
			}
			txtNumber.setText(num);
		} else if (v.equals(btnCall)) {
			String num = txtNumber.getText().toString();
			Intent it = new Intent(Intent.ACTION_CALL);
			it.setData(Uri.parse("tel:" + num));
			startActivity(it);
		} else if (v.equals(btnSave)) {
			String num = txtNumber.getText().toString();
			Intent intent = new Intent(Intent.ACTION_INSERT,
					ContactsContract.Contacts.CONTENT_URI);
			intent.putExtra(ContactsContract.Intents.Insert.PHONE, num);
			startActivity(intent);
		}
	}

}