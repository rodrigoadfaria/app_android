package com.vinacredit.activity.Sale;

import java.util.List;

import com.vinacredit.activity.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
		
		
		// inflating list view layout if null
		if(convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.sale_plus_item, null);
		}
		if(mListDataItem.size() > 0) {
			DataItem entry = mListDataItem.get(pos);
			if(entry != null){
				// set Image Item
				ImageView imgItem = (ImageView)convertView.findViewById(R.id.imgItem);
				if(entry.getImgItem() == null)
					imgItem.setImageResource(R.drawable.chomsao);
				else 
					imgItem.setImageBitmap(entry.getImgItem());
				
				// set Name Item
				TextView txtItem = (TextView)convertView.findViewById(R.id.txtItem);
				txtItem.setText(entry.getStrItem());
				
				// set Quantity Item
				TextView txtQtyItem = (TextView)convertView.findViewById(R.id.txtQtyItem);
				txtQtyItem.setText(entry.getQuantityItem());
				
				// set Price Item
				TextView txtSubtitle = (TextView)convertView.findViewById(R.id.txtSubtitle);
				txtSubtitle.setText(entry.getPriceItem());
				
//				Button btnPlus	= (Button)convertView.findViewById(R.id.btnPlus);
//				Button btnMinus = (Button)convertView.findViewById(R.id.btnMinus);
			}
		}
		
		
		return convertView;
	}

}