-- MYSQL5.5版本前一张表只能有一个字段是timestamp
CREATE TABLE seckill(
  `seckill_id` BIGINT NOT NUll AUTO_INCREMENT COMMENT '商品库存ID',
  `name` VARCHAR(120) NOT NULL COMMENT '商品名称',
  `number` int NOT NULL COMMENT '库存数量',
  `start_time` DATETIME  NOT NULL COMMENT '秒杀开始时间',
  `end_time`   DATETIME   NOT NULL COMMENT '秒杀结束时间',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (seckill_id),
  key idx_start_time(start_time),
  key idx_end_time(end_time),
  key idx_create_time(create_time)
)ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

-- 初始化数据
INSERT into seckill(name,number,start_time,end_time)
VALUES
  ('1000元秒杀iphone6',100,'2016-01-01 00:00:00','2016-01-02 00:00:00'),
  ('800元秒杀ipad',200,'2016-01-01 00:00:00','2016-01-02 00:00:00'),
  ('6600元秒杀mac book pro',300,'2016-01-01 00:00:00','2016-01-02 00:00:00'),
  ('7000元秒杀iMac',400,'2016-01-01 00:00:00','2016-01-02 00:00:00');

-- 秒杀成功明细表
-- 用户登录认证相关信息(简化为手机号)
CREATE TABLE success_killed(
  `seckill_id` BIGINT NOT NULL COMMENT '秒杀商品ID',
  `user_phone` BIGINT NOT NULL COMMENT '用户手机号',
  `state` TINYINT NOT NULL DEFAULT -1 COMMENT '状态标识:-1:无效 0:成功 1:已付款 2:已发货',
  `create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
  PRIMARY KEY(seckill_id,user_phone),/*联合主键*/
  KEY idx_create_time(create_time)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';

CREATE TABLE `b_test` (
 `id` bigint(20) not null auto_increment  comment'主键',
`test_name` varchar(32)  default null comment'测试名',
`create_user` varchar(32) default null comment'创建人',
`create_date` datetime default null comment'创建时间',
`modify_user` varchar(32) default null comment'修改人',
`modify_date` datetime default null comment'修改时间',
`active` char(1) default null comment'是否可用',
PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17509 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT'博客测试表';

CREATE TABLE `t_mbe_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ACCOUNT` varchar(32) DEFAULT NULL COMMENT '账号',
  `PHONE` varchar(32) DEFAULT NULL COMMENT '手机号码',
  `PASSWORD` varchar(32) DEFAULT NULL COMMENT '密码',
  `NEW_USER_TYPE` int(11) DEFAULT NULL COMMENT '新用户类型',
  `USER_TYPE` varchar(64) DEFAULT NULL COMMENT '用户类型：inner-内部会员、third-第三方账号',
  `teamCode` varchar(128) DEFAULT NULL COMMENT '所属配送团队编码',
  `HEAD_IMG_URL` varchar(256) DEFAULT NULL COMMENT '头像存储目录',
  `NICK_NAME` varchar(64) DEFAULT NULL COMMENT '昵称',
  `EMAIL` varchar(32) DEFAULT NULL COMMENT '电子邮箱',
  `ACTIVE` char(1) DEFAULT NULL COMMENT '是否有效',
  `DESCRIPTION` varchar(256) DEFAULT NULL COMMENT '说明',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `CREATE_USER` varchar(64) DEFAULT NULL COMMENT '推荐人手机号',
  `MODIFY_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `MODIFY_USER` varchar(64) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `indx_phone` (`PHONE`,`ACCOUNT`)
) ENGINE=InnoDB AUTO_INCREMENT=17509 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;