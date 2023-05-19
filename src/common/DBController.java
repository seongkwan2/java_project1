package common;

import java.util.ArrayList;

import service.serviceDTO;

public class DBController extends DBService {


	public ArrayList<serviceDTO> controller() {	//출력하는 메소드

		ArrayList<serviceDTO> sdto = new ArrayList<>();
		serviceDTO sdto1 = null;

		try {
			String sql = "select * from shop"; 
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();


			while(rs.next()) {				//컨트롤러 메서드로 shop에있는 데이터를 가져옴
				sdto1 = new serviceDTO();
				sdto1.setSName(rs.getString("s_name"));
				sdto1.setSTotal(rs.getInt("s_total"));
				sdto1.setSBrand(rs.getString("s_brand"));
				sdto1.setSPrice(rs.getInt("s_price"));
				sdto.add(sdto1);	//가져온 정보들을 seviceDTO에 저장 시킴
			}

		}catch(Exception e) {
			e.printStackTrace();

		}
		return sdto;
	}







	public int remaining(String target, int n1){	//남은수량 체크
		int r1 = 0;
		String sql = "update shop set s_total = " + n1 + " where s_name = '" + target + "'";
		try {									//구입갯수						//상품이름
			
			ps = con.prepareStatement(sql);
			r1 = ps.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();

		}
		return r1;
	}

}



















