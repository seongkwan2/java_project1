package shop;

import java.util.Scanner;

import service.MainPage2;
import service.searchService;
import service.serviceFunc;

public class MainShop {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		MainPage2 sf = new serviceFunc();			//상품등록 ,검색 구매 기능
		MainPage mp = new MainFunc();				//회원가입,로그인기능

		int num = 0;
		String logcheck = null;

		while(true) {
			try {
			System.out.println("1.회원가입");
			System.out.println("2.로그인");
			System.out.print(">>>");
			num = sc.nextInt();
			}catch(Exception e) {
				e.printStackTrace();
				sc.nextLine();
				continue;
			}

			switch(num) {
			case 1 : mp.signup();
			break;
			case 2 : logcheck = mp.login();
			if(logcheck != null) {
				while(logcheck != null) {
					System.out.println("1.상품등록 2.상품검색 3.마이페이지 4.고객센터 5.로그아웃");
					num = sc.nextInt();
					switch(num) {
					case 1 :
						sf.add(logcheck);
						break;
					case 2 :
						sf.search();
						break;
					case 3 : sf.myPage(logcheck);
					break;
					case 4 :
						sf.service(logcheck);
						break;
					case 5 :
						logcheck = null;
					}
				}
			}
			break;
			}
		}
	}
}
