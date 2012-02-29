package com.thunsaker.javapin.android.demo;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Util {
    public static Bitmap FetchExternalImage(String imageUri) {
    	try {
	    	if (imageUri != "") {
	    		URL url = new URL(imageUri);
	    		URLConnection conn = url.openConnection();
	    		conn.connect();
	    		InputStream is = conn.getInputStream();
	    		BufferedInputStream bis = new BufferedInputStream(is);
	    		Bitmap bmp = BitmapFactory.decodeStream(bis);
	    		
	    		bis.close();
	    		is.close();
	    		
	    		return bmp;
	    	}
    	} catch (IOException e) {
    		
    	}
    	return null;
	}
}
