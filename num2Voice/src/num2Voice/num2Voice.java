package num2Voice;

/*
【整数数字转读音】
string num2Voice(int num) { … }
例：输入：12345  输出：一万二千三百四十五*/

public class num2Voice {
	
	public static  void num2Voice(int num){
		String numStr = String.valueOf(num);
		char[] numArray = numStr.toCharArray();
		for (int i = 0; i <numStr.length(); i++) {

			int index = numStr.length()-i;
			if(numArray[i]=='0'){
				if(index==5&&numStr.length()<9){
					System.out.print("万");
				}else if(index==9){
					System.out.print("亿");
				}else{
					continue;
				}
				
			}else{
			System.out.print(numArray[i]);
			if(index%4==1){
				if(index/4==0){
					System.out.print(" ");
				}else if(index/4==1){
					System.out.print("万");
				}else if(index/4==2){
					System.out.print("亿");
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
	
	public static void main(String[] args) {
		int num = 12345678;
		num2Voice(num);
	}

}
