package com.wangtao.blog.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangtao.blog.common.enums.seckill.SeckillStatEnum;
import com.wangtao.blog.common.exception.seckill.RepeatKillException;
import com.wangtao.blog.common.exception.seckill.SeckillCloseException;
import com.wangtao.blog.core.seckill.ISeckillService;
import com.wangtao.blog.model.dto.ExposerDto;
import com.wangtao.blog.model.dto.SeckillExecution;
import com.wangtao.blog.model.entity.seckill.SeckillEntity;
import com.wangtao.blog.model.vo.SeckillResultVo;

/**
 * Created by codingBoy on 16/11/28.
 */
@Component
@RequestMapping("/seckill") // url:模块/资源/{}/细分
public class SeckillController {
	
	@Autowired
	private ISeckillService seckillService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		// list.jsp+mode=ModelAndView
		// 获取列表页
		List<SeckillEntity> list = seckillService.getSeckillList();
		model.addAttribute("list", list);
		return "list";
	}

	@RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
		if (seckillId == null) {
			return "redirect:/seckill/list";
		}

		SeckillEntity seckill = seckillService.getById(seckillId);
		if (seckill == null) {
			return "forward:/seckill/list";
		}

		model.addAttribute("seckill", seckill);

		return "detail";
	}

	// ajax ,json暴露秒杀接口的方法
	@RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public SeckillResultVo<ExposerDto> exposer(@PathVariable("seckillId")Long seckillId) {
		SeckillResultVo<ExposerDto> result;
		try {
			ExposerDto exposer = seckillService.exportSeckillUrl(seckillId);
			result = new SeckillResultVo<ExposerDto>(true, exposer);
		} catch (Exception e) {
			e.printStackTrace();
			result = new SeckillResultVo<ExposerDto>(false, e.getMessage());
		}

		return result;
	}

	@RequestMapping(value = "/{seckillId}/{md5}/execution", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public SeckillResultVo<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
			@PathVariable("md5") String md5, @CookieValue(value = "killPhone", required = false) Long phone) {
		if (phone == null) {
			return new SeckillResultVo<SeckillExecution>(false, "未注册");
		}
		try {
			SeckillExecution execution = seckillService.executeSeckill(seckillId, phone, md5);
			return new SeckillResultVo<SeckillExecution>(true, execution);
		} catch (RepeatKillException e1) {
			SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.REPEAT_KILL);
			return new SeckillResultVo<SeckillExecution>(true, execution);
		} catch (SeckillCloseException e2) {
			SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.END);
			return new SeckillResultVo<SeckillExecution>(true, execution);
		} catch (Exception e) {
			SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
			return new SeckillResultVo<SeckillExecution>(true, execution);
		}

	}

	// 获取系统时间
	@RequestMapping(value = "/time/now", method = RequestMethod.GET)
	@ResponseBody
	public SeckillResultVo<Long> time() {
		Date now = new Date();
		return new SeckillResultVo<Long>(true, now.getTime());
	}
}
