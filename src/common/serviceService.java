package common;

import java.sql.SQLException;

public class serviceService extends DBService {
	public void serviceView(String s1) {
		String sql = "select "+s1+" , s_date from service";
		int c1 = 0;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getString(s1) == null) {
					continue;
				}
				if(rs.getString(s1).equals("0")) {
					continue;
				}
				System.out.print(rs.getString(s1)+"\t");
				System.out.println(rs.getDate("s_date"));
				c1++;
			}
			if(c1 == 0) {
				System.out.println("등록된 글이 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int serviceAdd(String s1, int num, String id) {
		String sql = "insert into service VALUES(?, ?, service_SEQ.nextval, '"+ id + "',sysdate)";
		int r1 = 0;
		try {
			ps = con.prepareStatement(sql);
			if(num == 0) {
				 ps.setString(1, s1);
				 ps.setString(2, "0");
			}else {
				ps.setString(1, "0");
				ps.setString(2, s1);
			}
			r1 = ps.executeUpdate();
		} catch (Exception e) {
		}
		return r1;
		
	}
}
