package com.wangtao.blog.test.seckill;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangtao.blog.common.exception.seckill.RepeatKillException;
import com.wangtao.blog.common.exception.seckill.SeckillCloseException;
import com.wangtao.blog.core.seckill.ISeckillService;
import com.wangtao.blog.model.dto.ExposerDto;
import com.wangtao.blog.model.dto.SeckillExecution;
import com.wangtao.blog.model.entity.seckill.SeckillEntity;

/**
 * Created by codingBoy on 16/11/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring的配置文件
@ContextConfiguration({ "classpath:applicationContext.xml" })

public class SeckillServiceTest {

	@Autowired
	private ISeckillService seckillService;
	

	// @Test
	public void getSeckillList() throws Exception {
		List<SeckillEntity> seckills = seckillService.getSeckillList();
		System.out.println(seckills);

	}

	// @Test
	public void getById() throws Exception {

		long seckillId = 1000;
		SeckillEntity seckill = seckillService.getById(seckillId);
		System.out.println(seckill);
	}

	//@Test
	public void exportSeckillUrl() throws Exception {

		long seckillId = 1000;
		ExposerDto exposer = seckillService.exportSeckillUrl(seckillId);
		System.out.println(exposer);

	}

	@Test//完整逻辑代码测试，注意可重复执行
	public void testSeckillLogic() throws Exception {
		long seckillId = 1000;
		ExposerDto exposer = seckillService.exportSeckillUrl(seckillId);
		if (exposer.isExposed()) {

			System.out.println(exposer);

			long userPhone = 13476191876L;
			String md5 = exposer.getMd5();

			try {
				SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);
				System.out.println(seckillExecution);
			} catch (RepeatKillException e) {
				e.printStackTrace();
			} catch (SeckillCloseException e1) {
				e1.printStackTrace();
			}
		} else {
			// 秒杀未开启
			System.out.println(exposer);
		}
	}

	//@Test
	public void executeSeckill() throws Exception {

		long seckillId = 1000;
		long userPhone = 13476191876L;
		String md5 = "bf204e2683e7452aa7db1a50b5713bae";

		SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);

		System.out.println(seckillExecution);

	}

}