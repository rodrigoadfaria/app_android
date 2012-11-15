package com.vinacredit.activity.Sale;

import android.widget.ImageView;

public class DataItem {
	
	private ImageView		imgItem;
	private String 			strItem;
	private String			quantityItem;
	private String			priceItem;
	
	public ImageView getImgItem() {
		return imgItem;
	}
	public void setImgItem(ImageView imgItem) {
		this.imgItem = imgItem;
	}
	public String getStrItem() {
		return strItem;
	}
	public void setStrItem(String strItem) {
		this.strItem = strItem;
	}
	public String getQuantityItem() {
		return quantityItem;
	}
	public void setQuantityItem(String quantityItem) {
		this.quantityItem = quantityItem;
	}
	public String getPriceItem() {
		return priceItem;
	}
	public void setPriceItem(String priceItem) {
		this.priceItem = priceItem;
	}
	
	public DataItem(){}
	
	public DataItem(ImageView imgItem, String strItem, String quantityItem, String priceItem){
		this.imgItem 		= imgItem;
		this.strItem	 	= strItem;
		this.quantityItem 	= quantityItem;
		this.priceItem		= priceItem;
	}

}
