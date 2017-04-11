package com.wangtao.blog.web.admin;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName:BloggerController
 * @author: KarlWang
 * @Description: TODO(博主控制层)
 * @date:2017年4月10日 下午4:26:31
 * @see com.wangtao.blog.web.admin.BloggerController
 */
@Controller
@RequestMapping("/blogger")
public class BloggerController {

	private final static Logger logger = Logger.getLogger(BloggerController.class);

	public static void main(String[] args) {
		System.out.println("start!");
		logger.error("test log4j mell 222");
		System.out.println("end!");
	}
}
