package myTestProject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Test {

	public static void Test() {
		// 获得10天前的date====================================================================================
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 10);
		Date date = calendar.getTime();
		System.out.println(date.toLocaleString());
		// 格式化date为string
		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String dateStr = sdf.format(date);
		dateStr += " 00:00:00";
		System.out.println(dateStr);

		//=====================================&运算以及HashMAPkey定位算法==============================================
		System.out.println("BinaryString:" + Integer.toBinaryString(20));
		System.out.println("BinaryString:" + Integer.toBinaryString(16));
		System.out.println("BinaryString:" + Integer.toBinaryString(15));
		System.out.println("BinaryString:" + Integer.toBinaryString(20 & (16 - 1)) + "   " + (20 & (16 - 1)));

		
		//======================================lambda表达式=====================================================
		
		// 1. 不需要参数,返回值为 5  
		//System.out.println(() -> 5); 
		  
		// 2. 接收一个参数(数字类型),返回其2倍的值  
		Consumer<Integer> consumer1 = x -> System.out.println("x*2="+x*2); 
		consumer1.accept(6);
		  
		// 3. 接受2个参数(数字),并返回他们的差值
		//int x_y = (x, y) -> x – y  ;
		//Consumer consumer2 = (x, y) -> System.out.println(x -> 2 * x);
		
		// 4. 接收2个int型整数,返回他们的和  
		//int x_y=(int x, int y) -> x + y  ;
		//System.out.println("x+y="+x_y);
		// 5. 接受一个 string 对象,并在控制台打印,不返回任何值(看起来像是返回void)  
		String s ="hahaha";
		Consumer<String>  consumer = (String s3) -> System.out.println(s3+="_suffix");
		consumer.accept(s);
		
		String[] num = { "1", "2", "3", "4", "5", "6" };
		List<String> nums = Arrays.asList(num);
		// 以前的循环方式
		for (String n : nums) {
			System.out.print(n + "; ");
		}

		System.out.println("\n");
		// 使用 lambda 表达式以及函数操作(functional operation)
		nums.forEach((player) -> System.out.print(player + "; "));

		System.out.println("\n");
		// 在 Java 8 中使用双冒号操作符(double colon operator)
		nums.forEach(System.out::print);
		
		System.out.println("\n");
		//返回123456各+1之后大于等于5的数
		List<Integer> plusOne = nums.stream().map(e->new Integer(e)).map(e->e+=1).filter(e-> e>=5).collect(Collectors.toList());
		System.out.println("123456各+1之后大于等于5的数"+plusOne);
	}

	public static void main(String[] args) {
		Test();
	}
}
