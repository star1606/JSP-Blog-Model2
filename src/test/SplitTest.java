package test;

public class SplitTest {
		
	
	//split 연습
	public static void main(String[] args) {
		
		String a = "안녕-하세-요";
		
		String[] b = a.split("-");
		System.out.println(b);
		
		String 문자열 = "abc-def-ghi-jk";
		String[] 분리 = 문자열.split("-");
		
		//System.out.println(분리[0]);
		//System.out.println(분리[1]);
		//System.out.println(분리[2]);
		//System.out.println(분리[3]);
		//System.out.println(분리[4]);
		
		for (String 문자 : 분리) {
			System.out.println(문자);
		}
		
		System.out.println("--------------------------------------------------");
		String d = "12//34";
		String[] e = d.split("/");
		
		System.out.println(e[0]);
		System.out.println(e[1]);
		System.out.println(e[2]);
		//System.out.println(e[3]);
		
		
		
		
	}
}
