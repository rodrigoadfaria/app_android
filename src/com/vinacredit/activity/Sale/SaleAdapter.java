package com.vinacredit.activity.Sale;

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

public class SaleAdapter extends BaseAdapter {
	private Context mContext;
	private List<DataItem> mListDataItem;
	
	public SaleAdapter(Context context, List<DataItem> list) {
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
		DataItem entry = mListDataItem.get(pos);
		
		// inflating list view layout if null
		if(convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.sale_plus_item, null);
		}
		
		// set avatar
		ImageView ivAvatar = (ImageView)convertView.findViewById(R.id.imgItem);
		ivAvatar.setImageBitmap(entry.getImgItem());
		
		// set name
		TextView tvName = (TextView)convertView.findViewById(R.id.txtItem);
		tvName.setText(entry.getStrItem());
		
		// set phone
		TextView tvPhone = (TextView)convertView.findViewById(R.id.txtQtyItem);
		tvPhone.setText(entry.getQuantityItem());
		
		// set email
		TextView tvEmail = (TextView)convertView.findViewById(R.id.txtSubtitle);
		tvEmail.setText(entry.getPriceItem());
		
		Button btnPlus	= (Button)convertView.findViewById(R.id.btnPlus);
		Button btnMinus = (Button)convertView.findViewById(R.id.btnMinus);
		
		return convertView;
	}

}