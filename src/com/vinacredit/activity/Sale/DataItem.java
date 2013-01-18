package com.vinacredit.activity.Sale;

import android.graphics.Bitmap;

public class DataItem {
	
	private Bitmap			imgItem;
	private String 			strItem;
	private String			priceItem;
	

	public Bitmap getImgItem() {
		return imgItem;
	}
	public void setImgItem(Bitmap imgItem) {
		this.imgItem = imgItem;
	}
	public String getStrItem() {
		return strItem;
	}
	public void setStrItem(String strItem) {
		this.strItem = strItem;
	}

	public String getPriceItem() {
		return priceItem;
	}
	public void setPriceItem(String priceItem) {
		this.priceItem = priceItem;
	}
	
	public DataItem(){}
	
	public DataItem(Bitmap imgItem, String strItem, String priceItem){
		this.imgItem 		= imgItem;
		this.strItem	 	= strItem;
		this.priceItem		= priceItem;
	}

}
