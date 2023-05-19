package common;

import java.sql.SQLException;
import java.util.ArrayList;

import shop.shopDTO;

public class MainService extends DBService{
	
	public ArrayList<String> IDCheck() {
		String sql = "select u_id from userinfo";
		ArrayList<String> s1 = new ArrayList<>();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				s1.add(rs.getString("u_id"));
			}
		} catch (Exception e) {
			
		}
		return s1;
	}
	
	public String PWDCheck(String s1) {
		String sql = "select pwd from userinfo where u_id = '" + s1 + "'";
		String s2 = null;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			s2 = rs.getNString("pwd");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s2;
	}
	
	public int UserAdd(shopDTO sdto) {
		String sql = "insert into userinfo VALUES( ?, ?, ?, ?,?)";
		int r1 = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, sdto.getUId());
			ps.setString(2, sdto.getPwd());
			ps.setString(3, sdto.getEaddr());
			ps.setString(4, sdto.getPhone());
			ps.setString(5, sdto.getUName());
			r1 = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r1;
	}
	
}
