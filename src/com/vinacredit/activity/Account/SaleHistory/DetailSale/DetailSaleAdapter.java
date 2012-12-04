package com.vinacredit.activity.Account.SaleHistory.DetailSale;

import java.util.List;

import com.vinacredit.Resource.Library;
import com.vinacredit.activity.R;
import com.vinacredit.activity.Sale.DataItem;

import con.vinacredit.DTO.Bill;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailSaleAdapter extends BaseAdapter {
	private Context mContext;
	private List<Bill> mListDataItem;
	
	public DetailSaleAdapter(Context context, List<Bill> list) {
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
		Bill entry = mListDataItem.get(pos);
		
		// inflating list view layout if null
		if(convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.detail_sale_item, null);
		}
		
				// set Image Item
				ImageView imgItem = (ImageView)convertView.findViewById(R.id.imgCard);
				imgItem.setBackgroundResource(R.drawable.charge_money);
				
				// set Bill Item
				TextView txtItem = (TextView)convertView.findViewById(R.id.txtBill);
				txtItem.setText("Bill " + ++pos);
				
				// set Time Item
				TextView txtTime = (TextView)convertView.findViewById(R.id.txtTime);
				txtTime.setText(entry.getTimeSale());
				
				// set Price Item
				TextView txtQtyItem = (TextView)convertView.findViewById(R.id.txtPriceBill);
				txtQtyItem.setText(Library.addDotNumber(entry.getSumItem()));

		return convertView;
	}

}