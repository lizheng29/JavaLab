package myTestProject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {
	
	
	public static void Test(){
		//获得10天前的date
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)-10);
		Date date = calendar.getTime();
		System.out.println(date.toLocaleString());
		//格式化date为string
		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String dateStr = sdf.format(date);
		dateStr+=" 00:00:00";
		System.out.println(dateStr);
		
	}

	public static void main(String[] args) {
		Test();
	}
}
