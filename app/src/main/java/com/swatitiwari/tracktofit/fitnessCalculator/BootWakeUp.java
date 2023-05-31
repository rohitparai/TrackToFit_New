package com.swatitiwari.tracktofit.fitnessCalculator;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.legacy.content.WakefulBroadcastReceiver;


public class BootWakeUp extends WakefulBroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.e("13", "BootWakeUp onReceive: '" );
		Intent startServiceIntent = new Intent(context, push_notification.class);
		startWakefulService(context, startServiceIntent);
	}
}
