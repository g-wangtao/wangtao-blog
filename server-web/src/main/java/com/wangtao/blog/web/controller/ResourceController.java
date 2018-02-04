package com.wangtao.blog.web.controller;

import com.wangtao.blog.web.base.AbstractBaseController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName:BloggerController
 * @author: KarlWang
 * @Description: TODO(博主控制层)
 * @date:2017年4月10日 下午4:26:31
 * @see com.wangtao.blog.web.controller.ResourceController
 */
@Controller
@RequestMapping("/resource")
public class ResourceController extends AbstractBaseController{

	private final static Logger logger = Logger.getLogger(ResourceController.class);

	static String s;

	public static void main(String[] args){
		System.out.println(s.replace("",""));
	}
}
