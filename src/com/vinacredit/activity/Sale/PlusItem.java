package com.vinacredit.activity.Sale;

import android.widget.ImageView;

public class PlusItem {

	private ImageView	imgItem;
	private String 		strItem;
	private String		priceItem;
	private String		quantityItem;
	private int			ipx;
	
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
	public String getQuantityItem() {
		return quantityItem;
	}
	public void setQuantityItem(String quantityItem) {
		this.quantityItem = quantityItem;
	}
	public int getIpx() {
		return ipx;
	}
	public void setIpx(int ipx) {
		this.ipx = ipx;
	}
	
	public PlusItem(){}
	
	public PlusItem(ImageView imgItem, String strItem, String priceItem, String quantityItem, int ipx){
		this.imgItem		= imgItem;
		this.strItem		= strItem;
		this.priceItem		= priceItem;
		this.quantityItem	= quantityItem;
		this.ipx			= ipx;
	}
	
}
