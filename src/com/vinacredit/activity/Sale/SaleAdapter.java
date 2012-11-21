package com.vinacredit.activity.Sale;


import static com.vinacredit.activity.Sale.Constant.FIRST_COLUMN;
import static com.vinacredit.activity.Sale.Constant.SECOND_COLUMN;
import static com.vinacredit.activity.Sale.Constant.THIRD_COLUMN;
import static com.vinacredit.activity.Sale.Constant.FOURTH_COLUMN;
import static com.vinacredit.activity.Sale.Constant.FIFTH_COLUMN;
import static com.vinacredit.activity.Sale.Constant.SIXTH_COLUMN;

import java.util.ArrayList;
import java.util.HashMap;

import com.vinacredit.activity.R;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class SaleAdapter extends BaseAdapter{

	public ArrayList<HashMap<String,String>> list;
	Activity activity;
	
	public SaleAdapter(Activity activity, ArrayList<HashMap<String,String>> list) {
		super();
		this.activity = activity;
		this.list = list;
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
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private class ViewHolder {
	       ImageView  	imgItem;
	       TextView   	txtItem;
	       TextView   	txtQtyItem;
	       TextView   	txtSubtitle;
	       Button		btnMinus;
	       Button		btnPlus;
	  }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		LayoutInflater inflater =  activity.getLayoutInflater();

		if (convertView == null)
		{
			convertView = inflater.inflate(R.layout.sale_plus_item, null);
			holder = new ViewHolder();
			holder.imgItem 		= (ImageView) convertView.findViewById(R.id.imgItem);
			holder.txtItem 		= (TextView) convertView.findViewById(R.id.txtItem);
			holder.txtQtyItem 	= (TextView) convertView.findViewById(R.id.txtQtyItem);
			holder.txtSubtitle 	= (TextView) convertView.findViewById(R.id.txtSubtitle);
			holder.btnMinus		= (Button) convertView.findViewById(R.id.btnMinus);
			holder.btnPlus		= (Button) convertView.findViewById(R.id.btnPlus);
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		
		HashMap<String, String> map = list.get(position);
		holder.txtItem.setText(map.get(SECOND_COLUMN));
		holder.txtQtyItem.setText(map.get(THIRD_COLUMN));
		holder.txtSubtitle.setText(map.get(FOURTH_COLUMN));
	return convertView;
	}

}
