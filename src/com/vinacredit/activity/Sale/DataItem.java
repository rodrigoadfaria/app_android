package com.vinacredit.activity.Sale;

import android.graphics.Bitmap;

public class DataItem {
	
	private Bitmap			imgItem;
	private String 			strItem;
	private String			quantityItem;
	private String			priceItem;
	private int				qtyItem = 1;
	
	
	public int getQtyItem() {
		return qtyItem;
	}
	public void setQtyItem(int qtyItem) {
		this.qtyItem = qtyItem;
	}
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
	
	public DataItem(Bitmap imgItem, String strItem, String quantityItem, String priceItem){
		this.imgItem 		= imgItem;
		this.strItem	 	= strItem;
		this.quantityItem 	= quantityItem;
		this.priceItem		= priceItem;
	}

}
