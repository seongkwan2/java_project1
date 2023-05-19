package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import common.DBService;

public class searchService extends DBService {

	int min = 0;			//기본설정
	int max = 1000000000;
	String order = "asc";
	BuyService buyS = new BuyService();

	//모든 상품 보기
	public void Allsearch() {
		Scanner scan = new Scanner(System.in);
		String sql = "select * from shop where s_price BETWEEN ? AND ?"
				+ "order by s_price "+order+", s_brand asc";

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1,min);
			ps.setInt(2,max);
			rs = ps.executeQuery();

			System.out.println("모든 상품 보기");
			System.out.println("==============================");
			System.out.println("브랜드\t상품명\t가격\t남은수량\t");
			System.out.println("==============================");
			while(rs.next()) {

				System.out.print(rs.getString("s_brand")+"\t");
				System.out.print(rs.getString("s_name")+"\t");
				System.out.print(rs.getInt("s_price")+"\t");
				System.out.println(rs.getInt("s_total"));
				System.out.println();
			}
			System.out.println("==============================");
			
			serviceDTO target = buyS.buyChoice();	//구매하는 기능에 어떤 물품을 사는지 전달
			if(target == null) {					//target에 값이 없으면 다시 취소하고
				return;								//처음부터
			}
			
			System.out.println("1.구매하기\n2.장바구니에 넣기\n3.취소");
			int choi = scan.nextInt();
			
			
			switch(choi) {
			case 1:
				 
				buyS.buyItem(target);	//String으로 저장된 값으로 buyItem()메서드 돌림
				break;

			case 2:
				buyS.wishadd(target);
				
				break;

			case 3:
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}





	//품절제외 상품 보기
	public void AllsearchIf() {
		Scanner scan = new Scanner(System.in);
		String sql = "select * from shop where s_price BETWEEN ? AND ? and s_total > 0"
				+ "order by s_price "+order+", s_brand asc";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1,min);
			ps.setInt(2,max);
			rs = ps.executeQuery();

			System.out.println("모든 상품 보기");
			System.out.println("==============================");
			System.out.println("브랜드\t상품명\t가격\t남은수량\t");
			System.out.println("==============================");
			while(rs.next()) {

				System.out.print(rs.getString("s_brand")+"\t");
				System.out.print(rs.getString("s_name")+"\t");
				System.out.print(rs.getInt("s_price")+"\t");
				System.out.println(rs.getInt("s_total"));
				System.out.println();
			}
			System.out.println("==============================");
			
			serviceDTO target = buyS.buyChoice();	//구매하는 기능에 어떤 물품을 사는지 전달
			if(target == null) {					//target에 값이 없으면 다시 취소하고
				return;								//처음부터
			}
			
			System.out.println("1.구매하기\n2.장바구니에 넣기\n3.취소");
			int choi = scan.nextInt();
			
			
			switch(choi) {
			case 1:
				 
				buyS.buyItem(target);	//String으로 저장된 값으로 buyItem()메서드 돌림
				break;

			case 2:
				buyS.wishadd(target);
				
				break;

			case 3:
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}





	//원하는 상품 검색
	public void search() {
		Scanner scan = new Scanner(System.in);
		String sql = 
				"SELECT * FROM shop WHERE s_name LIKE ? OR s_brand LIKE ?"
						+ "AND s_price BETWEEN ? AND ?";
		String what;
		System.out.println("상품을 입력해주세요\n>>>");
		what = scan.next();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, "%" + what + "%");
			ps.setString(2, "%" + what + "%");
			ps.setInt(3,min);
			ps.setInt(4,max);
			rs = ps.executeQuery();

			System.out.println("입력 하신 \""+what+"\" 가 들어간 상품 리스트 입니다.");
			System.out.println("==============================");
			System.out.println("브랜드\t상품명\t가격\t남은수량\t");
			System.out.println("==============================");

			while(rs.next()) {

				System.out.print(rs.getString("s_brand")+"\t");
				System.out.print(rs.getString("s_name")+"\t");
				System.out.print(rs.getInt("s_price")+"\t");
				System.out.println(rs.getInt("s_total"));
				System.out.println();
			}
			serviceDTO target = buyS.buyChoice();	//구매하는 기능에 어떤 물품을 사는지 전달
			if(target == null) {					//target에 값이 없으면 다시 취소하고
				return;								//처음부터
			}
			
			System.out.println("1.구매하기\n2.장바구니에 넣기\n3.취소");
			int choi = scan.nextInt();
			
			
			switch(choi) {
			case 1:
				 
				buyS.buyItem(target);	//String으로 저장된 값으로 buyItem()메서드 돌림
				break;

			case 2:
				buyS.wishadd(target);
				
				break;

			case 3:
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//필터
	public void filter() {
		Scanner scan = new Scanner(System.in);

		int choice2 = 0;
		System.out.println("1.가격 설정하기\n2.순서 설정하기\n>>>");
		choice2 = scan.nextInt();

		switch(choice2) {
		case 1://가격설정
			System.out.println("원하는 가격 범위 설정");
			System.out.println("최소 금액을 입력 해주세요\n>>>");
			min = scan.nextInt();
			System.out.println("최대 금액을 입력 해주세요\n>>>");
			max = scan.nextInt();
			System.out.println("입력하신 값으로 설정 되었습니다.");
			break;
		case 2://가격순 정렬
			int choice = 0;
			System.out.println("1.낮은 가격 순으로 보기\n2.높은 가격 순으로 보기\n3.랭킹순으로 보기>>>");
			choice = scan.nextInt();
			switch(choice) {
			case 1:
				System.out.println("");
				order = "asc";
				System.out.println("입력하신 값으로 설정 되었습니다.");
				break;

			case 2:
				order = "desc";
				System.out.println("입력하신 값으로 설정 되었습니다.");
				break;
				
			}
			break;

			default:
				System.out.println("보기중에 선택해주세요");
			break;

		}
		
		
		
		
		
	}

}





































