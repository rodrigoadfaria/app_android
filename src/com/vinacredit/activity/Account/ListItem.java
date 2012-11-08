package com.vinacredit.activity.Account;

public class ListItem {
	public int icon;
	public String title;
	public String subtitle;
	public int iconArrow;
	public ListItem (){
		super();
	}
	
	public ListItem (int icon, String title, String subtitle, int iconArrow){
		super();
		this.icon 		= icon;
		this.title 		= title;
		this.subtitle 	= subtitle;
		this.iconArrow	= iconArrow;
	}
}
