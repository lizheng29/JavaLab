package num2Voice;

import java.util.HashMap;


/*
【整数数字转读音】
string num2Voice(int num) { … }
例：输入：12345  输出：一万二千三百四十五*/

public class num2Voice {
	
	
	public static void num2Voice(int num){
		

		String numStr = String.valueOf(num);
		char[] numArray = numStr.toCharArray();
		int flag = 0;
		for (int i = 0; i <numStr.length(); i++) {

			int index = numStr.length()-i;
			if(numArray[i]=='0'){
				if(index==5&&flag!=1){
					System.out.print("万");
				}else if(index==9){
					System.out.print("亿");
					flag=1;
				}else{
					continue;
				}
				
			}else{
			System.out.print(transfer(numArray[i]));
			if(index%4==1){
				if(index/4==0){
					System.out.print(" ");
				}else if(index/4==1){
					System.out.print("万");
				}else if(index/4==2){
					System.out.print("亿");
					flag=1;
				}
				
			}else if (index%4==2){
				System.out.print("十");
			}else if (index%4==3){
				System.out.print("百");
			}else if (index%4==0){
				System.out.print("千");
			}
			}
		}
	}
	
	public static char transfer(char num){
		
		switch (num){
		case '1': return '一';
		case '2': return '二';
		case '3': return '三';
		case '4': return '四';
		case '5': return '五';
		case '6': return '六';
		case '7': return '七';
		case '8': return '八';
		case '9': return '九';
		
		default : return ' ';
		}

	}
	
	public static void main(String[] args) {
		int num = 12345;
		num2Voice(num);
	}

}
