package com.appkefu.androidstudiodemo.adapter;

import java.util.List;

import com.appkefu.androidstudiodemo.R;
import com.appkefu.androidstudiodemo.entity.ApiEntity;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ApiAdapter extends BaseAdapter {

	private Context context;
	private List<ApiEntity> list;
	private LayoutInflater inflater;
	
	public ApiAdapter(Context context, List<ApiEntity> list) {
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public void remove(ApiEntity entity) {
		list.remove(entity);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.api_item_layout, null);
			holder = new ViewHolder();
			holder.api_function = (TextView) convertView.findViewById(R.id.api_function);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		final ApiEntity entity = list.get(position);

		holder.api_function.setTextColor(Color.BLACK);
		holder.api_function.setText(entity.getId() +"."+ entity.getApiName());
		
		return convertView;
	}

	static class ViewHolder {
		public TextView api_function;
	}
}













