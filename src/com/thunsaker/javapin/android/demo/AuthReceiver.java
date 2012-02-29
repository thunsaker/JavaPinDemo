package com.thunsaker.javapin.android.demo;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AuthReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context authContext, Intent authIntent) {
		String rawAuthUri = authIntent.getDataString();
		String authCode = rawAuthUri.substring(rawAuthUri.indexOf("code") + 4, rawAuthUri.length() - 1);
		
		// Set/Get the Application Access Token in shared prefs
		
		// Redirect the user back to the main Activity Screen
	}
}
