/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-09-14 07:13:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mood_praise_rel
-- ----------------------------
DROP TABLE IF EXISTS `user_mood_praise_rel`;
CREATE TABLE `user_mood_praise_rel` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `mood_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_mood_praise_rel_user_id_index` (`user_id`) USING BTREE,
  KEY `user_mood_praise_rel_mood_id_index` (`mood_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
