package com.thunsaker.javapin.android.demo;


import java.util.List;

import com.thunsaker.javapin.Pinterest;
import com.thunsaker.javapin.classes.Pin;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class PopularListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popular);
		
        List<Pin> PopularPins;
		try {
			PopularPins = Pinterest.GetPopularPins();
			
			if(PopularPins != null && PopularPins.size() > 0) {
				ListView list = (ListView) findViewById(R.id.lstPopularList);
				PinListAdapter adapter = new PinListAdapter(this, PopularPins);
				list.setAdapter(adapter);
			}
		
			//setListAdapter(new ArrayAdapter<Pin>(this, R.layout.photo_list_item, PopularPins));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static class ViewHolder {
	    TextView text;
	    ImageView image;
	}

}