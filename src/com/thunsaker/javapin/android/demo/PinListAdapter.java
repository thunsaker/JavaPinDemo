package com.thunsaker.javapin.android.demo;

import java.util.List;

import com.thunsaker.javapin.classes.Pin;
import com.thunsaker.javapin.android.demo.PopularListActivity.ViewHolder;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PinListAdapter extends BaseAdapter {
	
	private LayoutInflater mInflater;
	private List<Pin> data;

	public PinListAdapter(Context context, List<Pin> pins) {
		mInflater = LayoutInflater.from(context);
		this.data = pins;
	}
	
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		if(convertView == null) {
			convertView = mInflater.inflate(R.layout.photo_list_item, null);
			
			holder = new ViewHolder();
			holder.text = (TextView) convertView.findViewById(R.id.lblItem);
			holder.image = (ImageView) convertView.findViewById(R.id.imgItem);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.text.setText(data.get(position).getPinInfo().getSourceUrl());
		String imageUrl = data.get(position).getImage().getMobileUrl().toString();
		if(imageUrl != null && imageUrl != ""){
			Bitmap pinImage = Util.FetchExternalImage(imageUrl);
			if(pinImage != null) {
				holder.image.setImageBitmap(pinImage);
			}
		}

		return convertView;
	}

}
