package inputtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputStreamTest {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String read = "";
		
		while(!read.equals("q")){
			System.out.print("输入BufferedReader数据：");
			read = br.readLine();
			System.out.println("输入了："+read);
			
		}
		
		System.out.println("输入结束");
	}

}
