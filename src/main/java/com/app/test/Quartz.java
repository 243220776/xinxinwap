package com.app.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Quartz {
	
	ScheduledExecutorService scheduledExecutorService;
	
//    @Scheduled(fixedRate = 5000) // 每隔5秒
//	@Scheduled(cron = "0 0/1 * * * ?") // 每分钟执行一次
//	@Scheduled(cron = "0 0 0/3 * * ?") // 每3个小时执行一次
	@Scheduled(cron = "0 1 0 * * 1") // 每周一0点1分
    public void time(){
    	test();
    }
	
	public String request(){
		String str = "哎呀哎呀！我又来了，我是java定时器";
		return str;
	}
	public void test(){
		System.err.println(request()+" "+new Date());
	}
	
	@SuppressWarnings("all")
	private static long getTimeMillis(String time) {  
	    try {  
	        DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");  
	        DateFormat dayFormat = new SimpleDateFormat("yy-MM-dd");  
	        Date curDate = dateFormat.parse(dayFormat.format(new Date()) + " " + time);  
	        return curDate.getTime();  
	    } catch (ParseException e) {
	        e.printStackTrace();  
	    }  
	    return 0;  
	}  

}
