package com.wangtao.blog.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

/**
 * @ClassName:VerifyCodeUtil
 * @author: KarlWang
 * @Description: TODO(图片验证码工具类) 
 * @date:2017年5月19日 下午2:16:17
 * @see com.wangtao.blog.common.util.VerifyCodeUtil
 */
public class VerifyCodeUtil {
	
	private final Logger logger = Logger.getLogger(VerifyCodeUtil.class);
	
	// 图片的宽度。
	private int width = 160;
	// 图片的高度。
	private int height = 40;
	// 验证码字符个数
	private int codeCount = 4;
	// 验证码干扰线数
	private int lineCount = 20;
	// 验证码
	private String code = null;
	// 验证码图片Buffer
	private BufferedImage buffImg = null;
	// 随机数生成器
	Random random = new Random();
	 
	
	public VerifyCodeUtil() {
		creatImage();
	}

	public VerifyCodeUtil(int width, int height) {
		this.width = width;
		this.height = height;
		creatImage();
	}

	public VerifyCodeUtil(int width, int height, int codeCount) {
		this.width = width;
		this.height = height;
		this.codeCount = codeCount;
		creatImage();
	}

	public VerifyCodeUtil(int width, int height, int codeCount, int lineCount) {
		this.width = width;
		this.height = height;
		this.codeCount = codeCount;
		this.lineCount = lineCount;
		creatImage();
	}

	/**
	 * @Title: creatImage 
	 * @Description: TODO(生成图片) 
	 * @param  设定文件 
	 * @return void 返回类型 
	 * @throws
	 */
	private void creatImage() {
		int fontWidth = width / codeCount; // 字体的宽度
		int fontHeight = height - 5; // 字体的高度
		int codeY = height - 8;
		// 图像buffer
		buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = buffImg.getGraphics();
		// 设置背景色
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		// 设置字体
		//Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
		// 设置随机字体
		Font font = this.getFont(fontHeight);
		g.setFont(font);
		// 设置干扰线
		for (int i = 0; i < lineCount; i++) {
			int xs = random.nextInt(width);
			int ys = random.nextInt(height);
			int xe = xs + random.nextInt(width);
			int ye = ys + random.nextInt(height);
			g.setColor(this.getRandColor(1, 255));
			g.drawLine(xs, ys, xe, ye);
		}
		// 添加噪点
		float yawpRate = 0.01f;// 噪声率
		int area = (int) (yawpRate * width * height);
		for (int i = 0; i < area; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			buffImg.setRGB(x, y, random.nextInt(255));
		}
		this.code = this.randomStr(codeCount);// 得到随机字符;
		logger.info("生成验证码为：code[" + code + "]");
		for (int i = 0; i < this.codeCount; i++) {
			String strRand = this.code.substring(i, i + 1);
			g.setColor(this.getRandColor(1, 255));
			g.drawString(strRand, i * fontWidth + 3, codeY);
		}
	}

	/**
	 * @Title: randomStr 
	 * @Description: TODO(得到随机字符) 
	 * @param @param n
	 * @param @return 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	private String randomStr(int n) {
		final String verifyCodes = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
		StringBuffer code = new StringBuffer();
		int len = verifyCodes.length() - 1;
		double r;
		for (int i = 0; i < n; i++) {
			r = (Math.random()) * len;
			code.append(verifyCodes.charAt((int) r));
		}
		return code.toString();
	}

	/**
	 * @Title: getRandColor 
	 * @Description: TODO(得到随机颜色) 
	 * @param @param fc
	 * @param @param bc
	 * @param @return 设定文件 
	 * @return Color 返回类型 
	 * @throws
	 */
	private Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	/**
	 * @Title: getFont 
	 * @Description: TODO(产生随机字体) 
	 * @param @param size
	 * @param @return 设定文件 
	 * @return Font 返回类型 
	 * @throws
	 */
	private Font getFont(int size) {
		Random random = new Random();
		Font font[] = new Font[5];
		font[0] = new Font("Ravie", Font.PLAIN, size);
		font[1] = new Font("Antique Olive Compact", Font.PLAIN, size);
		font[2] = new Font("Fixedsys", Font.PLAIN, size);
		font[3] = new Font("Wide Latin", Font.PLAIN, size);
		font[4] = new Font("Gill Sans Ultra Bold", Font.PLAIN, size);
		return font[random.nextInt(5)];
	}

	/**
	 * @Title: write 
	 * @Description: TODO(输出图片) 
	 * @param @param sos
	 * @param @throws IOException 设定文件 
	 * @return void 返回类型 
	 * @throws
	 */
	public void write(OutputStream sos) {
		try {
			ImageIO.write(buffImg, "jpeg", sos);
			sos.close();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	/**
	 * @Title: getBuffImg 
	 * @Description: TODO(获取缓存图片流) 
	 * @param @return 设定文件 
	 * @return BufferedImage 返回类型 
	 * @throws
	 */
	public BufferedImage getBuffImg() {
		return buffImg;
	}
	
	/**
	 * @Title: getCode 
	 * @Description: TODO(获取验证码) 
	 * @param @return 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	public String getCode() {
		return code.toLowerCase();
	}
}