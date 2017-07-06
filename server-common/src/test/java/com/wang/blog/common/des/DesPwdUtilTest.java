package com.wang.blog.common.des;

import java.io.IOException;

import org.junit.Test;

import com.wangtao.blog.common.util.des.DesPwdUtil;

/**
 * @ClassName:DesPwdUtilTest
 * @author: KarlWang
 * @Description: TODO(DES对称加密测试) 
 * @date:2017年4月13日 下午8:13:29
 * @see com.wang.blog.common.des.DesPwdUtilTest
 */
public class DesPwdUtilTest {
    
   // @Test
    public void desPwdEncode(){
    	String data = "773995514";
        String key = "011111100001";
        try {
			System.err.println(DesPwdUtil.encrypt(data, key));
			System.err.println(DesPwdUtil.decrypt("FjWd9ibT7eLP2eCtkNAVXw==", key));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @Test
    public void desPwdDecode(){
    	String data = "773995514";
        String key = "011111100001";
        try {
        	System.err.println(DesPwdUtil.encrypt(data, key));
			System.err.println(DesPwdUtil.decrypt("cWvioEyE5RSstqU", key));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    // @Test
    public void desPwd() {
    	String key = "011111100001";
    	String data = "773995514@qq.com";
    	try {
			System.err.println(DesPwdUtil.encrypt(data, key));
			System.err.println(DesPwdUtil.decrypt(DesPwdUtil.encrypt(data, key), key));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
