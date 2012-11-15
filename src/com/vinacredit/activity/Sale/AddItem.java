package com.vinacredit.activity.Sale;

import android.widget.ImageView;

public class AddItem {
	
	private ImageView	imgItem;
	private String 		strItem;
	private String		priceItem;
	private int			iax;
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
	public String getPriceItem() {
		return priceItem;
	}
	public void setPriceItem(String priceItem) {
		this.priceItem = priceItem;
	}
	public int getIax() {
		return iax;
	}
	public void setIax(int iax) {
		this.iax = iax;
	}
	
	public AddItem(){}
	
	public AddItem(ImageView imgItem, String strItem, String priceItem, int iax){
		this.imgItem	= imgItem;
		this.strItem	= strItem;
		this.priceItem	= priceItem;
		this.iax		= iax;
	}

}
