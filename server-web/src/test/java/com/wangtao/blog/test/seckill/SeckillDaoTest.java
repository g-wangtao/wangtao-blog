package com.wangtao.blog.test.seckill;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangtao.blog.dao.seckill.SeckillDao;
import com.wangtao.blog.model.entity.seckill.SeckillEntity;

/**
 * Created by codingBoy on 16/11/27.
 * 配置spring和junit整合，这样junit在启动时就会加载spring容器
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件
@ContextConfiguration({"classpath:datasource/datasource.xml"})
public class SeckillDaoTest {

    //注入Dao实现类依赖
    @Resource
    private SeckillDao seckillDao;


    @Test
    public void queryById() throws Exception {
        long seckillId=1000;
        SeckillEntity seckill=seckillDao.queryById(seckillId);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    //@Test
    public void queryAll() throws Exception {

        List<SeckillEntity> seckills=seckillDao.queryAll(0,100);
        for (SeckillEntity seckill : seckills)
        {
            System.out.println(seckill);
        }
    }

    //@Test
    public void reduceNumber() throws Exception {

        long seckillId=1000;
        Date date=new Date();
        int updateCount=seckillDao.reduceNumber(seckillId,date);
        System.out.println(updateCount);

    }


}