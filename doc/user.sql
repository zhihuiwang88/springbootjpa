/*
Navicat MySQL Data Transfer

Source Server         : my
Source Server Version : 50627
Source Host           : 127.0.0.1:3306
Source Database       : zgdb

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2020-09-04 22:21:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `age` int(16) DEFAULT NULL,
  `sex` varchar(16) DEFAULT NULL,
  `createtime` timestamp NULL DEFAULT NULL,
  `updatetime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4;
