package common;

public class mypageService extends DBService {
	
	public void myPView(String id, String s1, int i) {
		String sql =null;
		if(i == 1) {
			sql = "select " + s1 + " from shop where s_id = '"+ id +"'";
		}else {
			sql = "select " + s1 + ", s_num from service where s_id1 = '"+ id +"'";
		}
		
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getString(s1).equals("0")) {
					continue;
				}
				if(i != 1) {
					System.out.print(rs.getInt("s_num")+".");
				}
				System.out.println(rs.getString(s1));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int myPDelete(String id, String s1, int i) {
		String sql = null;
		int r1 = 0;
		if(i == 0) {
			sql = "delete from shop where s_name = '"+ s1 +"' AND s_id = '"+ id +"'";
		}else {
			sql = "delete from service where s_num = "+ i +" AND s_id1 = '"+ id +"'";
		}
		
		try {
			ps = con.prepareStatement(sql);
			r1 = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r1;
	}
	
}
