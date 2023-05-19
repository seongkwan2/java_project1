package service;

import java.util.ArrayList;
import java.util.Scanner;

import common.DBController;
import common.DBService;

public class BuyService extends DBService {



	//고르는 매서드
	public serviceDTO buyChoice() {
		DBController dbcon = new DBController();
		ArrayList<serviceDTO> sdto = dbcon.controller();
		serviceDTO sdto1 = null;
		Scanner scan = new Scanner(System.in);
		int i = 0;
		while (true) {
			System.out.println("리스트에서 구매하실 상품명을 입력해주세요\n(나가기 → ' 취소 ' 입력)\n>>>");
			String item = scan.nextLine().trim().toLowerCase(); // 입력받은 값을 공백 제거
			if (item.equals("취소"))
				return null;	//취소시 메서드 종료

			//진행하면 for문 실행
			for (i = 0; i < sdto.size(); i++) {
				if (sdto.get(i).getSName().toLowerCase().replaceAll("\\s", "").equals(item.replaceAll("\\s", ""))) {
					System.out.println("선택하신 상품은 " + sdto.get(i).getSName() + "입니다.");
					sdto1 = new serviceDTO();
					sdto1.setSId("aa123");
					sdto1.setSName(sdto.get(i).getSName());
					sdto1.setSBrand(sdto.get(i).getSBrand());
					sdto1.setSPrice(sdto.get(i).getSPrice());
					sdto1.setSTotal(sdto.get(i).getSTotal());
					return sdto1; // 객체를 ArrayList에 추가

				}
			}
			if (i == sdto.size()) {
				System.out.println("상품명을 정확히 입력해주세요.\n");
			}
		}


	}

	//구입하는 매서드
	public void buyItem(serviceDTO target) {

		DBController dbcon = new DBController();
		ArrayList<serviceDTO> sdto = dbcon.controller();
		Scanner scan = new Scanner(System.in);
		int i = 0;
		for (i = 0; i < sdto.size(); i++) {					//리스트를 가져오기 위한 for문
			if (sdto.get(i).getSName().equals(target.getSName())) {	//해당 상품명이 몇번째 index에있는지 확인
				if (sdto.get(i).getSTotal() != null) {
					if(sdto.get(i).getSTotal() != 0 ) {
					System.out.println("남은 개수: " + sdto.get(i).getSTotal());
					System.out.println("몇 개를 구입하시겠습니까?\n>>>");
					int su = scan.nextInt();
					scan.nextLine(); // 줄 바꿈 문자 소비
					int su1 = sdto.get(i).getSTotal();
					if (su1 < su) {
						System.out.println("상품 개수가 부족합니다. 다시 입력해주세요");
						return;
					} else {
						su1 -= su;
						int r1 = dbcon.remaining(target.getSName(), su1);	//해당물품의 남은수량 확인
						if (r1 != 0) {
							System.out.println("구매되었습니다.");
							System.out.println("남은 개수: " + su1);
							return;
						}
					}
					}else{
						System.out.println("해당 상품은 품절되었습니다.");
					}
				} else {
					System.out.println("해당 상품은 품절되었습니다.");
				}
			}
		}
	}


	//장바구니에 넣기
	public void wishadd(serviceDTO sdto1) {
		DBController dbcon = new DBController();
		ArrayList<serviceDTO> sdto = dbcon.controller();
		int r1 = 0;
		try {
			
			String sql = "insert into wishlist(w_id, w_brand, w_name, w_price, w_total) "
					+ "values(?,?,?,?,?)";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, sdto1.getSId());
			ps.setString(2, sdto1.getSBrand());
			ps.setString(3, sdto1.getSName());
			ps.setInt(4, sdto1.getSPrice());
			ps.setInt(5, sdto1.getSTotal());
			r1 = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		if(r1 != 0) {
			System.out.println("장바구니에 추가 되었습니다.");
		}
	}




//장바구니 확인
public void wishlist() {

	String sql = "select * from wishlist";
	try {
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		System.out.println("내가 찜한 상품 보기");
		System.out.println("==================================");
		System.out.println("브랜드\t     상품명\t     가격");
		System.out.println("==================================");
		
		//if()문으로 내 아이디가 들어있는 값만 출력
		while(rs.next()) {

			System.out.print(rs.getString("w_brand")+"\t     ");
			System.out.print(rs.getString("w_name")+"\t     ");
			System.out.print(rs.getInt("w_price")+"");
			System.out.println();
		}
	}catch(Exception e) {
		e.printStackTrace();
	}

}

public void wishlistdel(serviceDTO target) {

	String sql = "delete from wishlist where w_name = '" + target.getSName() + "'" ;
	try {
		ps = con.prepareStatement(sql);
		ps.executeUpdate();

	}catch(Exception e) {
		e.printStackTrace();
	}
	System.out.println("구매 완료");

}


}














