package com.wang.blog.common;

import org.junit.Test;

import com.wangtao.blog.common.util.StringUtils;

public class StringUtilsTest {
	
	@Test
	public void testIsEmpty() {
		StringUtils.isBlank(null); //= true
		StringUtils.isBlank(""); //= true
		StringUtils.isBlank(" "); //= true
		StringUtils.isBlank("\t \n \f \r"); //= true
		System.out.println(StringUtils.isBlank(null));
		System.out.println(StringUtils.isBlank(""));
		System.out.println(StringUtils.isBlank(" "));
		System.out.println(StringUtils.isBlank("\t \n \f \r"));
		System.out.println(StringUtils.isBlank("\b"));
	}
	
	@Test
	public void testIsNotEmpty() {
		StringUtils.isNotBlank(null);// = false
		StringUtils.isNotBlank("");// = false
		StringUtils.isNotBlank(" ");// = false
		StringUtils.isNotBlank("\t \n \f \r");// = false
		StringUtils.isNotBlank("\b");// = true
		StringUtils.isNotBlank("bob");// = true
		System.out.println(StringUtils.isNotBlank(null));
		System.out.println(StringUtils.isNotBlank(""));
		System.out.println(StringUtils.isNotBlank(" "));
		System.out.println(StringUtils.isNotBlank("\t \n \f \r"));
		System.out.println(StringUtils.isNotBlank("\b"));
		System.out.println(StringUtils.isNotBlank("bob"));
	}
	
}
