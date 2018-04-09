package com.bupt.qrj.unifyum.uti.digest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class m5test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String a="123456";
		MD5Encoder md=new MD5Encoder();
		MD5 md5=new MD5();
		System.out.println("加密:"+md.encode(a));
		System.out.println("解密:"+md5.crypt(a));
		
		MD5Util md5u=new MD5Util();
		String s = new String("12345678");
		System.out.println("原始：" + s);
		System.out.println("MD5后：" + md5u.string2MD5(s));
		System.out.println("加密的：" +  md5u.convertMD5(s));
		System.out.println("解密的：" +  md5u.convertMD5( md5u.convertMD5(s)));
	}
}
/**
 * 采用MD5加密解密
 * @author tfq
 * @datetime 2011-10-13
 */
class MD5Util {

	/***
	* MD5加码 生成32位md5码
	*/
	public static String string2MD5(String inStr){
		MessageDigest md5 = null;
		try{
			md5 = MessageDigest.getInstance("MD5");
		}catch (Exception e){
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++){
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
	/**
	* 加密解密算法 执行一次加密，两次解密
	*/ 
	public static String convertMD5(String inStr){
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++){
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;
	}	
}
