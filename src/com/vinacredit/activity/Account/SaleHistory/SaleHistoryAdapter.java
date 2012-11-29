package com.vinacredit.activity.Account.SaleHistory;

import java.util.List;

import com.vinacredit.activity.R;

import con.vinacredit.DTO.SumBill;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SaleHistoryAdapter extends BaseAdapter {
	private Context mContext;
	private List<SumBill> mListDataItem;
	
	public SaleHistoryAdapter(Context context, List<SumBill> list) {
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
		SumBill entry = mListDataItem.get(pos);
		
		// inflating list view layout if null
		if(convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.sales_history_item, null);
		}				
				
				// set Date Item
				TextView txtItem = (TextView)convertView.findViewById(R.id.txtDate);
				txtItem.setText(entry.getDateSale());
				
				// set Price Item
				TextView txtQtyItem = (TextView)convertView.findViewById(R.id.txtPrice);
				txtQtyItem.setText(entry.getSumBill());
				
				// set Image Item
//				ImageView imgItem = (ImageView)convertView.findViewById(R.id.imgStar);
//				imgItem.setImageBitmap(R.drawable.arrow);	
		
		return convertView;
	}

}