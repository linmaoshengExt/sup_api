package web.utils;

import java.text.ParseException;

import java.util.List;

import web.model.SupGoods;

public class PriceUtils {
	
	public static List<SupGoods>  getPrice(List<SupGoods> list){
		for (SupGoods supGoods : list) {
			supGoods.setGoodsPrices(supGoods.getGoodsPrices()/100);
		}
		return list;
	}
	
	public static void main(String[] args) throws ParseException {
	String a ="61E416E7-F8A9-4210-9639-50A5B5E1FBC1";
	
	String aa= "61E416E7-F8A9-4210-9639-50A5B5E1FBC1";
		System.err.println(a.toLowerCase());
		System.err.println(aa.toUpperCase());
		
	}
	
	

}
