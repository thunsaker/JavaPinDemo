package com.thunsaker.javapin.android.demo;


import com.thunsaker.javapin.Pinterest;
import com.thunsaker.javapin.classes.User;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class UserActivity extends Activity {
	
	public static String currentUser;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userinfo);
		
		Bundle extras = getIntent().getExtras();
		String username = "";
		
		if(extras != null) {
			username = extras.getString("username");
			currentUser = username;
		}
		
		User UserInfo = new User();
		try {
			UserInfo = Pinterest.GetUserInfo(username);
		} catch (Exception e) {
			Toast.makeText(this.getApplicationContext(), "There was a problem fetching the user information", Toast.LENGTH_SHORT).show();
		}
		
		if(UserInfo != null) {
			if(UserInfo.getUsername() != "") {
				TextView usernameText = (TextView) findViewById(R.id.lblUserInfoTitle);
				usernameText.setText(UserInfo.getUsername());
			}
			
			if(UserInfo.getImageUrlLarge() != "") {
				ImageView myImage = (ImageView) findViewById(R.id.imgUserInfoProfilePhoto);
				Bitmap profilePicture = Util.FetchExternalImage(UserInfo.getImageUrlLarge().toString());
				myImage.setImageBitmap(profilePicture);
			}

			Button followingButton = (Button) findViewById(R.id.btnFollow);
			// Show the follow button
			if(UserInfo.getIsFollowing()) {
				followingButton.setText(R.string.following_btn_text);
			}
			else {
				followingButton.setText(R.string.follow_btn_text);
			}
			
			// Set the counts
			if(UserInfo.getStats() != null) {
				if(UserInfo.getStats().get("Followers") != null) {
					TextView FollowersLabel = (TextView) findViewById(R.id.lblFollowers);
					Integer FollowersCount = UserInfo.getStats().get("Followers");
					String FollowersText = getResources().getQuantityString(R.plurals.follower_count, FollowersCount, FollowersCount);
					FollowersLabel.setText(FollowersText);
				}
				if(UserInfo.getStats().get("Likes") != null) {
					TextView LikesLabel = (TextView) findViewById(R.id.lblLikes);
					Integer LikesCount = UserInfo.getStats().get("Likes");
					String LikesText = getResources().getQuantityString(R.plurals.like_count, LikesCount, LikesCount);
					LikesLabel.setText(LikesText);
				}
				if(UserInfo.getStats().get("Pins") != null) {
					TextView PinsLabel = (TextView) findViewById(R.id.lblPins);
					Integer PinsCount = UserInfo.getStats().get("Pins");
					String PinsText = getResources().getQuantityString(R.plurals.pin_count, PinsCount, PinsCount);
					PinsLabel.setText(PinsText);
				}
				if(UserInfo.getStats().get("Following") != null) {
					TextView FollowingLabel = (TextView) findViewById(R.id.lblFollowing);
					Integer FollowingCount = UserInfo.getStats().get("Following");
					String FollowingText = getResources().getQuantityString(R.plurals.following_count, FollowingCount, FollowingCount);
					FollowingLabel.setText(FollowingText);
				}
				if(UserInfo.getStats().get("Boards") != null) {
					TextView BoardsLabel = (TextView) findViewById(R.id.lblBoards);
					Integer BoardsCount = UserInfo.getStats().get("Boards");
					String BoardsText = getResources().getQuantityString(R.plurals.board_count, BoardsCount, BoardsCount);
					BoardsLabel.setText(BoardsText);
				}
			}
		}
	}
	
    public void ViewProfileOnWeb(View view) {
    	Uri userUri = Uri.parse(String.format("http://pinterest.com/%s", currentUser));
    	Intent browserIntent = new Intent(Intent.ACTION_VIEW, userUri);
    	startActivity(browserIntent);
    }
}
