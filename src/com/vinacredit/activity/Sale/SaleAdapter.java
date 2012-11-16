package com.vinacredit.activity.Sale;

import java.util.ArrayList;
import java.util.List;

import com.vinacredit.activity.Account.Account_Activity;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class SaleAdapter extends ArrayAdapter<DataItem>{

	private Context context = null;
	private ArrayList<DataItem> dataItem = null;
	private LayoutInflater inflater = null;
	
	public SaleAdapter(Context context, int textViewResourceId,
			List<DataItem> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		
		this.context = context;
		dataItem = (ArrayList<DataItem>)objects;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return super.getView(position, convertView, parent);    
	}

}
