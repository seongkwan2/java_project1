package common;

import java.sql.SQLException;

import service.serviceDTO;

public class addService extends DBService {
	public int add(serviceDTO sdto) {
		String sql = "insert into shop VALUES(?, ?, ?, ?, ?, 0)";
		int r1 = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, sdto.getSId());
			ps.setString(2, sdto.getSName());
			ps.setInt(3, sdto.getSPrice());
			ps.setInt(4, sdto.getSTotal());
			ps.setString(5, sdto.getSBrand());
			r1 = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r1;
	}
}
