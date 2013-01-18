package com.vinacredit.activity.Sale;

import java.util.List;

import com.vinacredit.activity.R;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class Adapter extends BaseAdapter {
	private Context mContext;
	private List<DataItem> mListDataItem;
	
	public Adapter(Context context, List<DataItem> list) {
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
			convertView = inflater.inflate(R.layout.sale_add_item, null);
		}
		if(mListDataItem.size() == 1 || pos == 0) {
			final DataItem entry = mListDataItem.get(0);
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.sale_add_item, null);
			if(entry != null){
				// set Image Item
				ImageButton imgItem = (ImageButton)convertView.findViewById(R.id.imgItem);
				if(entry.getImgItem() == null)
					imgItem.setImageResource(R.drawable.camera);
				else
					imgItem.setImageBitmap(entry.getImgItem());
				
				// set Name Item
				EditText edtItem = (EditText)convertView.findViewById(R.id.edtItem);
				edtItem.setText(entry.getStrItem());				
				
				edtItem.addTextChangedListener(new TextWatcher() {
					
					@Override
					public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
							int arg3) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void afterTextChanged(Editable arg0) {
						// TODO Auto-generated method stub
						entry.setStrItem(arg0.toString());
					}
				});
				
				// set Price Item
				EditText txtSubtitle = (EditText)convertView.findViewById(R.id.txtSubtitle);
				txtSubtitle.setText(entry.getPriceItem());
				

				
				Button btnCheck = (Button)convertView.findViewById(R.id.btnCheck);
				Button btnDel	= (Button)convertView.findViewById(R.id.btnDel);
				
				btnCheck.setVisibility(View.VISIBLE);
				btnDel.setVisibility(View.GONE);
			}
		} else if(mListDataItem.size() != 1 || pos > 0){
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.sale_item, null);
			final DataItem entry1 = mListDataItem.get(pos);
			if(entry1 != null){
				// set Image Item
				ImageButton imgItem = (ImageButton)convertView.findViewById(R.id.imgItem);
				if(entry1.getImgItem() == null)
					imgItem.setImageResource(R.drawable.camera);
				else
					imgItem.setImageBitmap(entry1.getImgItem());
				
				// set Name Item
				EditText edtItem = (EditText)convertView.findViewById(R.id.edtItem);
				edtItem.setText(entry1.getStrItem());

				edtItem.addTextChangedListener(new TextWatcher() {
					
					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void beforeTextChanged(CharSequence s, int start, int count,
							int after) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub
						entry1.setStrItem(s.toString());
					}
				});
				
				
				// set Price Item
				EditText txtSubtitle = (EditText)convertView.findViewById(R.id.txtSubtitle);
				txtSubtitle.setText(entry1.getPriceItem());
				
				
				Button btnCheck = (Button)convertView.findViewById(R.id.btnCheck);
				Button btnDel	= (Button)convertView.findViewById(R.id.btnDel);
				
				btnCheck.setVisibility(View.GONE);
				btnDel.setVisibility(View.VISIBLE);

			}
		}
		
		
		return convertView;
	}

}