package com.example.lab5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class MySmsReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		Object[] objmessages = (Object[]) arg1.getExtras().get("pdus");
		for (int i = 0; i < objmessages.length; i++) {
			SmsMessage m = SmsMessage.createFromPdu((byte[]) objmessages[i]);
			Bundle b1 = new Bundle();
			b1.putString("number", m.getOriginatingAddress());
			b1.putString("content", m.getMessageBody());
			Intent it = new Intent(arg0, MainActivity.class);
			it.putExtra("data", b1);
			it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			arg0.startActivity(it);
			break;
		}
	}
}
