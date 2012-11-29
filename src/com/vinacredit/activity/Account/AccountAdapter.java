package com.vinacredit.activity.Account;

import java.util.List;

import com.vinacredit.activity.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AccountAdapter extends BaseAdapter {
	private Context mContext;
	private List<ListItem> mListDataItem;
	
	public AccountAdapter(Context context, List<ListItem> list) {
		mContext = context;
		mListDataItem = list;	
		
	}

	@Override
	public int getCount() {
		return mListDataItem.size();		
	}

	@Override
	public Object getItem(int pos) {
		return mListDataItem.get(pos);		
	}

	@Override
	public long getItemId(int pos) {
		return pos;
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
		// get selected entry
		ListItem entry = mListDataItem.get(pos);
		
		// inflating list view layout if null
		if(convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.listview_row_account, null);
		}
				// set Image Item
				ImageView imgItem = (ImageView)convertView.findViewById(R.id.imgList);
				imgItem.setImageBitmap(entry.getIcon());
				
				// set Name Item
				TextView txtItem = (TextView)convertView.findViewById(R.id.txtTitle);
				txtItem.setText(entry.getTitle());
				
				// set Quantity Item
				TextView txtQtyItem = (TextView)convertView.findViewById(R.id.txtSubtitle);
				txtQtyItem.setText(entry.getSubtitle());				
		return convertView;
	}

}