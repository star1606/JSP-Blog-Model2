package test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class Sha256Test {

	private final static String mSalt = "코스";
    
    public static String encodeSha256(String source) {
        String result = "";
        
        byte[] a = source.getBytes();
        byte[] salt = mSalt.getBytes();
        byte[] bytes = new byte[a.length + salt.length];
        
        System.arraycopy(a, 0, bytes, 0, a.length);
        System.arraycopy(salt, 0, bytes, a.length, salt.length);
        
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(bytes);
            
            byte[] byteData = md.digest();
            
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xFF) + 256, 16).substring(1));
            }
            
            result = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        return result;
    }



	
	
	

@Test
	public void encSha256() {

		String plain = "1234";
		String result = "";
		
		byte[] bytePlain = plain.getBytes();   //바이트 단위로 쪼개져있는 글자!
		byte[] byteSalt = mSalt.getBytes();
		
		
		
		
//		System.out.println(plain);
//		
//		System.out.println(bytePlain);
//		System.out.println(byteSalt);
//		
//		for (byte b : bytePlain) {
//			System.out.print(b+ "  ");
//			
//		}
//		System.out.println();
//		
//		for (byte b : byteSalt) {
//			System.out.print(b+ "  ");
//		
//		}
//		
//		System.out.println();
//		
		byte[] bytePlainAndSalt =
				new byte[bytePlain.length+byteSalt.length];
//		
//		
//		for(int i =0; i<bytePlain.length+byteSalt.length; i++) {
//			if(bytePlain > byteplain.length) {
//				for (int j = 0; j < bytePlainAndSalt.length; j++) {
//					
//				}
//			}
//				
//		}
//		for( byte[] i= j;  bytesalt<bytePlain.length+byteSalt.length;) {
//			
//		}
//		
		
		
		System.arraycopy
		
		(
				bytePlain, 
				0, 
				bytePlainAndSalt,
				0,
				bytePlain.length
		);
		
		System.arraycopy
		(	
				byteSalt,
				0,
				bytePlainAndSalt,
				bytePlain.length,
				byteSalt.length
		);
		
		//깊은복사
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(bytePlainAndSalt);
			
			byte[] byteData = md.digest();
		
			StringBuffer sb = new StringBuffer();  // 동기화 지원 critical section : 임계구역 --- StringBuilder: 동기화 지원x
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toHexString(byteData[i] & 0xFF)+256).substring(1);
			}
			
			System.out.println();
			result = sb.toString();
			System.out.println(result);
			
		} catch (Exception e) {
			
		}
		
		
		
	}
		

	
}
