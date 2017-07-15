package com.wangtao.blog.web.verifyCode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wangtao.blog.common.util.VerifyCodeUtil;

/**
 * @ClassName:VerifyCodeController
 * @author: KarlWang
 * @Description: TODO(验证码生成控制类) 
 * @date:2017年5月19日 上午11:50:23
 * @see com.wangtao.blog.web.verifyCode.VerifyCodeController
 */
@Controller
@RequestMapping(value = "/verifyCode")
public class VerifyCodeController {

	// 日志操作对象
	private final Logger logger = Logger.getLogger(VerifyCodeController.class);
	
	/**
	 * codeSequence
	 */
	private char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	
    
	
	/**
	 * @Title: generate 
	 * @Description: TODO(生成验证码) 
	 * @param @param request
	 * @param @param response 设定文件 
	 * @return void 
	 * @throws
	 */
	@RequestMapping("/generate")
	public void generate(HttpServletRequest request, HttpServletResponse response) {
		
		int width = 100;		// 定义图片的width
		int height = 40;		// 定义图片的height
		int codeCount = 4;		// 定义图片上显示验证码的个数
		int xx = 15;			// (width-2) / (codeCount + 1);
		int fontHeight = height - 2;
		int codeY = height - codeCount;
		
		// 定义图像buffer
	    BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    Graphics gd = buffImg.getGraphics();
	    // 创建一个随机数生成器类
	    Random random = new Random();
	    // 将图像填充为白色
	    gd.setColor(Color.WHITE);
	    gd.fillRect(0, 0, width, height);

	    // 创建字体，字体的大小应该根据图片的高度来定。
	    Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
	    // 设置字体。
	    gd.setFont(font);

	    // 画边框。
	    // gd.setColor(Color.GRAY);
	    Color c = new Color(222, 222, 222);
	    gd.setColor(c);
	    gd.drawRect(0, 0, width - 1, height - 1);

	    // 随机产生40条干扰线，使图象中的认证码不易被其它程序探测到。
	    gd.setColor(Color.BLACK);
	    for (int i = 0; i < 40; i++) {
	    	int x = random.nextInt(width);
	    	int y = random.nextInt(height);
	    	int xl = random.nextInt(12);
	    	int yl = random.nextInt(12);
	    	gd.drawLine(x, y, x + xl, y + yl);
	    }

	    // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
	    StringBuffer randomCode = new StringBuffer();
	    int red = 0, green = 0, blue = 0;

	    // 随机产生codeCount数字的验证码。
	    for (int i = 0; i < codeCount; i++) {
	    	// 得到随机产生的验证码数字。
	    	String code = String.valueOf(codeSequence[random.nextInt(36)]);
	    	// 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
	    	red = random.nextInt(255);
	    	green = random.nextInt(255);
	    	blue = random.nextInt(255);

	    	// 用随机产生的颜色将验证码绘制到图像中。
	    	gd.setColor(new Color(red, green, blue));
	    	gd.drawString(code, (i + 1) * xx, codeY);

	    	// 将产生的四个随机数组合在一起。
	    	randomCode.append(code);
	    }
	    
	    // 将四位数字的验证码保存到Session中。
		request.getSession().setAttribute("validateCode", randomCode.toString());
		logger.info("生成验证码为：validateCode[" + randomCode.toString() + "]");

	    // 禁止图像缓存。
	    response.setHeader("Pragma", "no-cache");
	    response.setHeader("Cache-Control", "no-cache");
	    response.setDateHeader("Expires", 0);

	    response.setContentType("image/jpeg");

	    // 将图像输出到Servlet输出流中。
	    ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
		    ImageIO.write(buffImg, "jpeg", sos);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				if(null != sos) {
					sos.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
	
	//使用方法
	@RequestMapping("/create")
	public void create(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        VerifyCodeUtil verifyCode = new VerifyCodeUtil(100,30,5,10);
        request.getSession().setAttribute("verifyCode", verifyCode.getCode());
        verifyCode.write(response.getOutputStream());	
	}	
}
