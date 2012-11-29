package com.vinacredit.activity.Account;

import android.graphics.Bitmap;

public class ListItem {
	public Bitmap icon;
	public String title;
	public String subtitle = "";
	public Bitmap iconArrow;
	public ListItem (){
		super();
	}
	
	public ListItem(Bitmap icon, String title, String subtitle, Bitmap iconArrow){
		this.icon 		= icon;
		this.title 		= title;
		this.subtitle	= subtitle;
		this.iconArrow  = iconArrow;
	}
	
	public Bitmap getIcon() {
		return icon;
	}
	public void setIcon(Bitmap icon) {
		this.icon = icon;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public Bitmap getIconArrow() {
		return iconArrow;
	}
	public void setIconArrow(Bitmap iconArrow) {
		this.iconArrow = iconArrow;
	}
	
	
}
