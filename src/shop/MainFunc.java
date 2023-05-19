package shop;

import java.util.ArrayList;
import java.util.Scanner;

import common.MainService;

public class MainFunc implements MainPage {
	Scanner sc = new Scanner(System.in);
	shopDTO sdto = null;
	MainService ms = new MainService();
	
	@Override
	public String login() {
		String id,pwd;
		System.out.print("아이디 : ");
		id = sc.next();
		if(idCheck(id) == 1) {
			System.out.print("비밀번호 : ");
			pwd = sc.next();
			if(pwdCheck(id, pwd) == 1) {
				System.out.println("로그인 성공");
				return id;
			}else {
				System.out.println("비밀번호가 틀렸습니다");
			}
			
		}else {
			System.out.println("아이디가 존재하지 않습니다.");
		}
		return null;
	}
	public int pwdCheck(String s1, String s2) {
		if(s2.equals(ms.PWDCheck(s1))){
			return 1;
		}
		return 0;
	}
	
	
	public int idCheck(String s1) {
		ArrayList<String> s1dto = new ArrayList<>();
		s1dto = ms.IDCheck();
		int i = 0;
		for(i = 0; i < s1dto.size(); i++) {
			if(s1.equals(s1dto.get(i))) {
				return 1;
			}
		}
		return 0;
	}
	
	@Override
	public void signup() {
		String s1;
		sdto = new shopDTO();
		System.out.println("====회원가입====");
		while(true) {
			System.out.print("아이디 : ");
			s1 = sc.next();
			if(idCheck(s1) == 1) {
			System.out.println("아이디가 존재합니다.");
			continue;
			}else {
				break;
			}
		}
		sdto.setUId(s1);
		System.out.print("비밀번호 : ");
		s1 = sc.next();
		sdto.setPwd(s1);
		System.out.print("이메일 : ");
		s1 = sc.next();
		sdto.setEaddr(s1);
		System.out.print("전화번호 : ");
		s1 = sc.next();
		sdto.setPhone(s1);
		System.out.print("이름 : ");
		s1 = sc.next();
		sdto.setUName(s1);	
		if(ms.UserAdd(sdto) != 0) {
			System.out.println("회원가입 되었습니다.");
		}
		
	}

	@Override
	public void freeLogin() {
		
	}
	
}
