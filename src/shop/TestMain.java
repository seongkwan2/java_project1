package shop;

import java.util.Scanner;

import service.MainPage2;
import service.serviceFunc;

public class TestMain {
	public static void main(String[] args) {
		MainPage2 sf = new serviceFunc();
		int num;
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("1.상품등록 2.고객센터");
			num = sc.nextInt();
			switch(num) {
			case 1 :
//				sf.add();
				break;
			case 2 :
				//sf.service();
				break;
			}
		}
		
		
		
	}
}
