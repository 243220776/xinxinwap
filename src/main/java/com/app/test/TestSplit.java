package com.app.test;

public class TestSplit {
	public static void main(String[] args) {
		getInt();
		getReplace();
	}
	
	public static void getInt(){
		String str = "14,15,16";
		String[] num = str.split("[,]");
		
		for (int i = 0; i < num.length; i++) {
			String s = num[i];
			Integer a = Integer.valueOf(s);
			
			System.err.println(a);
		}
		
	}
	
	public static void getReplace(){
		String string = "aassdd";
		String val = string.replace("ss", "++");
		System.err.println(val);
	}

}
