package com.app.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class TestThread {
	public static void main(String[] args) {
//		threadOne();
//		threadTwo();
//		ceil();
	}
	
	public static void threadOne(){
		new Thread(){
			public void run() {
				while(true){
					 try {
						System.out.println("线程输出");
						Thread.sleep(2*1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	public static void threadTwo(){
		new Thread(new Runnable() {
			public void run() {
				 System.out.println("嘿嘿线程定时器");
				 
				 while(true){
					 Calendar time = Calendar.getInstance();
					 String format = "yyyy-MM-dd HH:mm:ss";
					 SimpleDateFormat dateFormat = new SimpleDateFormat(format);
					 
			         System.out.println("看我每秒执行一次"+ dateFormat.format(time.getTime()));
			         
			         try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 }
			}
		}).start();
	}
	
	public static void ceil(){
		
		Float i = (float) 100;
		System.err.println("要提现："+i);
		Float j= (float) (i*0.06);//收取百分之六手续费
		Float b=(float)(Math.round(j*100)/100.0);
		
		System.err.println("手续费是："+b);
		
		Float v= i-b;
		Float c=(float)(Math.round(v*100)/100.0);
		
		System.err.println("实际到账："+c);
	}
	
}
