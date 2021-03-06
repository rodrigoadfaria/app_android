package com.vinacredit.activity.Sale;

import java.util.List;

import com.vinacredit.activity.R;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

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
		if(mListDataItem.size() == pos + 1) {
			final DataItem entry = mListDataItem.get(pos);
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
				final EditText txtSubtitle = (EditText)convertView.findViewById(R.id.txtSubtitle);
				txtSubtitle.setText(entry.getPriceItem());
				
				txtSubtitle.setOnTouchListener(new OnTouchListener() {
					
					@Override
					public boolean onTouch(View arg0, MotionEvent arg1) {
						// TODO Auto-generated method stub
						return true;
					}
				});
				
			}
		} else {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.sale_item, null);
			final DataItem entry1 = mListDataItem.get(pos);
			if(entry1 != null){
				// set Image Item
				ImageButton imgItem1 = (ImageButton)convertView.findViewById(R.id.imgItem);
				if(entry1.getImgItem() == null)
					imgItem1.setImageResource(R.drawable.camera);
				else
					imgItem1.setImageBitmap(entry1.getImgItem());
				
				// set Name Item
				EditText edtItem1 = (EditText)convertView.findViewById(R.id.edtItem);
				edtItem1.setText(entry1.getStrItem());

				edtItem1.addTextChangedListener(new TextWatcher() {
					
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
				TextView txtSubtitle1 = (TextView)convertView.findViewById(R.id.txtSubtitle);
				txtSubtitle1.setText(entry1.getPriceItem());


			}
		}
		
		
		return convertView;
	}

}