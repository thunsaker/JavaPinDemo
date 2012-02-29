package com.thunsaker.javapin.android.demo;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class JavaPinDemoActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void AuthenticateUser(View view) {
    	Uri authenticationUri = Uri.parse(String.format(getString(R.string.authorizeUrl), getString(R.string.clientId), Uri.encode(getString(R.string.callbackUrl))));
    	Intent launchBrowser = new Intent(Intent.ACTION_VIEW);
    	launchBrowser.setData(authenticationUri);
    	startActivity(launchBrowser);
    }
    
    public void StartPopular(View view) {
    	Intent popularIntent = new Intent(view.getContext(), PopularActivity.class);
    	startActivity(popularIntent);
    }
    
    public void StartPopularList(View view) {
    	Intent popularIntent = new Intent(view.getContext(), PopularListActivity.class);
    	startActivity(popularIntent);
    }
    
    public void GetUserInfo(View view) {
    	EditText txtUsername = (EditText) findViewById(R.id.txtUsername);
    	String username = txtUsername.getText().toString();
    	if(username == "" || username == null || username == "username") {
    		Toast.makeText(this.getApplicationContext(), "Please provide a Username", Toast.LENGTH_SHORT).show();
    		return;
    	} else {
    		Intent userIntent = new Intent(view.getContext(), UserActivity.class);
    		userIntent.putExtra("username", username);
    		startActivity(userIntent);
    	}
    }
}