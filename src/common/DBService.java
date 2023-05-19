package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import service.serviceDTO;
import shop.shopDTO;

public class DBService {
	protected Connection con;
	protected PreparedStatement ps;
	protected ResultSet rs;
	ArrayList<String> list = new ArrayList<>();
	shopDTO sdto = new shopDTO();
	int result =0;
	
	public DBService() {
		try {
			con = DBConnection.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> idckeck() {

		String sql = "select u_id from userinfo";
		ArrayList<String> list = new ArrayList<>();
		
		try {	
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()) {
			
			list.add(rs.getString("u_id"));
			
		}

		} catch (Exception e) {
			e.printStackTrace();	
			}
		return list;
	}
		
	public int singup(shopDTO sdto) {
		int result =0;
		String sql = "insert into userinfo(u_id,pwd,eaddr,phone,u_name)values (?,?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, sdto.getUId());
			ps.setString(2, sdto.getPwd());
			ps.setString(3, sdto.getEaddr());
			ps.setString(4, sdto.getPhone());			
			ps.setString(5, sdto.getUName());		
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}return result;
		
	}
	
	public int login(String ckId, String ckpwd) {
		String sql = "select * from userinfo where u_id= ? and pwd= ?";
	
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, ckId);
			ps.setString(2, ckpwd);
			result =ps.executeUpdate();
			
			
		} catch (Exception e) {
		e.printStackTrace();
		}return result;
	
	}
	
	public int freeLog(String fId, String fPwd) {
		String sql = "select * from userinfo where u_id= ? and pwd= ?";
		
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, fId);
			ps.setString(2, fPwd);
			result =ps.executeUpdate();
			
		} catch (Exception e) {
		e.printStackTrace();
		}
		
		return result;
	}
	
	public int freeSign(String id, String pwd) {
		 String sql = "insert into userinfo(u_id,pwd)values (?,?)";
		 try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pwd);
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} return result;
		
		
		
	}
	
	
}
