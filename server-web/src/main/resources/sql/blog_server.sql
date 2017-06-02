/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50529
Source Host           : localhost:3306
Source Database       : blog_server

Target Server Type    : MYSQL
Target Server Version : 50529
File Encoding         : 65001

Date: 2017-05-15 22:41:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for b_test
-- ----------------------------
DROP TABLE IF EXISTS `b_test`;
CREATE TABLE `b_test` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `test_name` varchar(32) DEFAULT NULL COMMENT '测试名',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(32) DEFAULT NULL COMMENT '修改人',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `active` char(1) DEFAULT NULL COMMENT '是否可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17518 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='博客测试表';

-- ----------------------------
-- Records of b_test
-- ----------------------------
INSERT INTO `b_test` VALUES ('17509', 'junit init test', 'admin', '2017-03-19 23:52:02', null, null, '\0');
INSERT INTO `b_test` VALUES ('17510', 'junit init test', 'admin', '2017-03-20 20:21:48', null, null, '\0');
INSERT INTO `b_test` VALUES ('17511', 'junit init test王涛', 'admin', '2017-03-22 11:58:46', null, null, '\0');
INSERT INTO `b_test` VALUES ('17512', 'junit init test', 'admin', '2017-03-22 13:25:24', null, null, '\0');
INSERT INTO `b_test` VALUES ('17513', 'junit init test', 'admin', '2017-03-23 11:29:31', null, null, '\0');
INSERT INTO `b_test` VALUES ('17514', 'junit init test', 'admin', '2017-03-23 11:44:13', null, null, '\0');
INSERT INTO `b_test` VALUES ('17515', 'junit init test', 'admin', '2017-03-25 02:20:04', null, null, '\0');
INSERT INTO `b_test` VALUES ('17516', 'junit init test', 'admin', '2017-03-25 02:27:57', null, null, '\0');
INSERT INTO `b_test` VALUES ('17517', 'junit init test', 'admin', '2017-03-29 20:55:26', null, null, '\0');

-- ----------------------------
-- Table structure for seckill
-- ----------------------------
DROP TABLE IF EXISTS `seckill`;
CREATE TABLE `seckill` (
  `seckill_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '��Ʒ���ID',
  `name` varchar(120) NOT NULL COMMENT '��Ʒ����',
  `number` int(11) NOT NULL COMMENT '�������',
  `start_time` datetime NOT NULL COMMENT '��ɱ��ʼʱ��',
  `end_time` datetime NOT NULL COMMENT '��ɱ����ʱ��',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
  PRIMARY KEY (`seckill_id`),
  KEY `idx_start_time` (`start_time`),
  KEY `idx_end_time` (`end_time`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8 COMMENT='��ɱ����';

-- ----------------------------
-- Records of seckill
-- ----------------------------
INSERT INTO `seckill` VALUES ('1000', '1000元秒杀iphone6', '99', '2016-01-01 00:00:00', '2017-04-09 00:00:00', '2017-03-24 16:15:49');
INSERT INTO `seckill` VALUES ('1001', '800元秒杀ipad', '200', '2016-01-01 00:00:00', '2016-01-02 00:00:00', '2017-03-24 16:15:49');
INSERT INTO `seckill` VALUES ('1002', '6600元秒杀mac book pro', '300', '2016-01-01 00:00:00', '2016-01-02 00:00:00', '2017-03-24 16:15:49');
INSERT INTO `seckill` VALUES ('1003', '7000元秒杀iMac', '400', '2016-01-01 00:00:00', '2016-01-02 00:00:00', '2017-03-24 16:15:49');

-- ----------------------------
-- Table structure for success_killed
-- ----------------------------
DROP TABLE IF EXISTS `success_killed`;
CREATE TABLE `success_killed` (
  `seckill_id` bigint(20) NOT NULL COMMENT '��ɱ��ƷID',
  `user_phone` bigint(20) NOT NULL COMMENT '�û��ֻ���',
  `state` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '״̬��ʶ:-1:��Ч 0:�ɹ� 1:�Ѹ��� 2:�ѷ���',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '����ʱ��',
  PRIMARY KEY (`seckill_id`,`user_phone`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��ɱ�ɹ���ϸ��';

-- ----------------------------
-- Records of success_killed
-- ----------------------------
INSERT INTO `success_killed` VALUES ('1000', '13476191876', '0', '2017-03-30 19:28:27');
INSERT INTO `success_killed` VALUES ('1000', '13476191877', '0', '2017-03-25 02:23:20');

-- ----------------------------
-- Table structure for t_auth_blogger
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_blogger`;
CREATE TABLE `t_auth_blogger` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `type` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '博主类型',
  `nick_name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '昵称',
  `email` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '电子邮箱',
  `sign` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '个性签名',
  `profile` text COLLATE utf8_bin COMMENT '个人简介',
  `head_img_url` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '头像存储地址',
  `last_login` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `active` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '用户状态',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建用户',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`),
  UNIQUE KEY `indx_user_name` (`user_name`),
  UNIQUE KEY `indx_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='博主表';

-- ----------------------------
-- Records of t_auth_blogger
-- ----------------------------
INSERT INTO `t_auth_blogger` VALUES ('1', 'admin', 'MTIzNDU2', 'admin', '大无畏', '773995514@qq.com', null, 0x48656C6C6F20776F726C64, null, null, 'Y', '2017-05-08 09:53:27', 'admin', null, null);
