package com.thunsaker.javapin.android.demo;


import java.util.List;

import com.thunsaker.javapin.Pinterest;
import com.thunsaker.javapin.classes.Pin;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PopularActivity extends Activity {
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popular_dump);
        
        List<Pin> PopularPins;
		try {
			PopularPins = Pinterest.GetPopularPins();
        
	        if(PopularPins != null && PopularPins.size() > 0) {
		        //ListView myList = (ListView) findViewById(android.R.id.list);
		        RelativeLayout myLayout = (RelativeLayout) findViewById(R.id.layPopularInner);
		        //setListAdapter((ListAdapter) new ArrayAdapter<>(this, R.layout.photo_list_item, textViewResourceId));
		        
		        Integer counter = 10;
		        Integer x = 0;
		    	Integer lastTextViewId = 0;
		        
		        for (Pin pin : PopularPins) {
		        	//LayoutParams p = new LayoutParams(50, 50, x, y, 1, 0, 0);
		        	RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
		        	if(lastTextViewId != 0)
		        		p.addRule(RelativeLayout.BELOW, lastTextViewId);
		    		else
		    			p.addRule(RelativeLayout.BELOW, R.id.lblTitlePopular);
					ImageView myImage = new ImageView(getApplicationContext());
					Bitmap profilePicture = Util.FetchExternalImage(pin.getPinInfo().getUser().getImageUrlLarge().toString());
					myImage.setImageBitmap(profilePicture);
					myImage.setLayoutParams(p);
					myLayout.addView(myImage);
					
					TextView myText = new TextView(getApplicationContext());
					myText.setText(pin.getPinInfo().getUser().getFullName());
		        	RelativeLayout.LayoutParams pText = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
		        	pText.addRule(RelativeLayout.BELOW, myImage.getId());
					myText.setLayoutParams(pText);
					myText.setTextColor(Color.GREEN);
					myLayout.addView(myText);
					
					lastTextViewId = myText.getId();
					
					if(counter-- == 0)
						break;
					else
						x += 60;
				}
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
