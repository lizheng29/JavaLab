package inputtest;

import java.util.Scanner;

public class ScannerTest {
	public static void main(String[] args) {

		String read = "";
		Scanner scan = new Scanner(System.in,"UTF-8");
		while (true) {
			System.out.print("输入Scanner数据：");
			if (scan.hasNext()) {
				read = scan.nextLine();
				System.out.println("输入了：" + read);
				if (read.equals("q")) {
					System.out.println("输入了退出符号");
					break;
				}
			}
		}
		scan.close();
		System.out.println("输入结束");
	}

}
