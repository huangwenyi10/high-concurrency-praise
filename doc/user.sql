/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-09-14 07:13:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `account` varchar(20) DEFAULT NULL COMMENT '账户',
  PRIMARY KEY (`id`),
  KEY `user_name_index` (`name`) USING BTREE,
  KEY `user_account_index` (`account`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
