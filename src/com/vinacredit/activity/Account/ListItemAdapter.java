package com.vinacredit.activity.Account;


import com.vinacredit.activity.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListItemAdapter extends ArrayAdapter<ListItem>{
	Context context;
	int layoutResourceId;
	ListItem data[] = null;
	
	public ListItemAdapter(Context context, int layoutResourceId, ListItem[] data) {
		super(context, layoutResourceId, data);
		// TODO Auto-generated constructor stub
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
//		return super.getView(position, convertView, parent);
		View row = convertView;
		ListItemHolder holder = null;
		if(row == null)
		{
			LayoutInflater inflater = ((Account_Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent,false);
			
			holder = new ListItemHolder();
			holder.imgIcon 		= (ImageView)row.findViewById(R.id.imgList);
			holder.txtTitle 	= (TextView)row.findViewById(R.id.txtTitle);
			holder.txtSubtitle 	= (TextView)row.findViewById(R.id.txtSubtitle);
			
			row.setTag(holder);
		}
		else
		{
			holder = (ListItemHolder)row.getTag();
		}
		
		ListItem list = data[position];
        holder.txtTitle.setText(list.title);
        holder.imgIcon.setImageResource(list.icon);
        holder.txtSubtitle.setText(list.subtitle);
		
		return row;
	}
	static class ListItemHolder
	{
		ImageView imgIcon;
		TextView txtTitle;
		TextView txtSubtitle;
	}
}
