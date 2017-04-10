package com.wangtao.blog.common.util.md5;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import com.wangtao.blog.common.util.base64.BASE64Decoder;
import com.wangtao.blog.common.util.base64.BASE64Encoder;

/**
 * @ClassName:CryptoUtil
 * @author: KarlWang
 * @Description: TODO(系统加密方式工具类) 
 * @date:2017年4月10日 下午2:41:54
 * @see com.wangtao.blog.common.util.md5.CryptoUtil
 */
public class CryptoUtil {

	private static final Logger LOGGER = Logger.getLogger(CryptoUtil.class);

	public static String base64Encode(byte bytes[]) {
		if (bytes == null){
			return "";
		} else {
			return (new BASE64Encoder()).encode(bytes);
		}
	}

	/**
	 * @Title: digestByMD5 
	 * @Description: TODO(MD5加密并返回字符串) 
	 * @param @param text
	 * @param @return 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	public static String digestByMD5(String text) {
		if (null == text){
			return null;
		}
		byte digest[] = new byte[0];
		try {
			digest = md5(text.getBytes());
			return base64Encode(digest);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e);
			throw new RuntimeException(e);
		}
	}

	private static byte[] md5(byte input[]) throws NoSuchAlgorithmException {
		MessageDigest alg = MessageDigest.getInstance("MD5");
		alg.update(input);
		byte digest[] = alg.digest();
		return digest;
	}

	/**
	 * @Title: digestByEncode 
	 * @Description: TODO(Base64加密) 
	 * @param @param text
	 * @param @return 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	public static String digestByEncode(String text) {
		if (text == null){
			return null;
		}
		try {
			return base64Encode(text.getBytes());
		} catch (Exception e) {
			LOGGER.error(e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Title: digestByDecode 
	 * @Description: TODO(Base64解密返回字符串) 
	 * @param @param text
	 * @param @return 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	public static String digestByDecode(String text) {

		byte[] bytes = base64Decode(text);
		try {
			if(null == bytes) {
				return null;
			}
			return new String(bytes, "UTF-8");
		} catch (Exception e) {
			LOGGER.error(e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @Title: base64Decode 
	 * @Description: TODO(Base64解密返回字符数组) 
	 * @param @param pwd
	 * @param @return 设定文件 
	 * @return byte[] 返回类型 
	 * @throws
	 */
	public static byte[] base64Decode(String pwd) {
		if (null != pwd && !"".equals(pwd)) {
			try {
				return new BASE64Decoder().decodeBuffer(pwd);
			} catch (IOException e) {
				LOGGER.error(e);
				throw new RuntimeException(e);
			}
		}
		return null;
	}
	
	/*public static void main(String[] args) {
		String test = "测试100";
		System.out.println("被加密字符：" + test);
		// md5加密
		String md5Str = digestByMD5(test);
		System.out.println("md5加密：" + md5Str);
		
		// Base64加密
		String encode = digestByEncode(test);
		System.out.println("Base64加密：" + encode);
		
		// Base64解密
		String decode = digestByDecode(encode);
		System.out.println("Base64解密：" + decode);
	}*/

}
