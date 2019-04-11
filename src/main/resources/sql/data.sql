/*
Navicat MySQL Data Transfer

Source Server         : swz
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : ssb_test

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-10-22 19:46:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `person`
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(20) NOT NULL COMMENT '名字',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_person_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COMMENT='人员表';

-- ----------------------------
-- Records of personDO
-- ----------------------------
INSERT INTO `person` VALUES ('18', 'swz', '18', 'swz', null, null);
INSERT INTO `person` VALUES ('19', 'swz', '19', '北京', '2018-10-17 17:27:53', '2018-10-17 17:27:53');
INSERT INTO `person` VALUES ('20', 'swz', '18', '北京', '2018-10-17 17:27:53', '2018-10-17 17:27:53');
INSERT INTO `person` VALUES ('21', 'swz', '23', 'xuzhou', '2018-10-17 17:27:53', '2018-10-17 17:27:53');
INSERT INTO `person` VALUES ('22', 'swz', '18', 'xuzhou', '2018-10-17 17:27:53', '2018-10-17 17:27:53');
INSERT INTO `person` VALUES ('23', 'swz', '23', 'xuzhou', '2018-10-17 17:27:53', '2018-10-17 17:27:53');
INSERT INTO `person` VALUES ('24', 'sws', '16', 'xuzhou', '2018-10-17 17:27:53', '2018-10-17 17:27:53');
INSERT INTO `person` VALUES ('25', 'swz', '18', 'xuzhou', '2018-10-17 17:27:53', '2018-10-17 17:27:53');
INSERT INTO `person` VALUES ('27', '测试', '10', 'address', '2018-10-16 15:44:26', '2018-10-16 15:44:26');
INSERT INTO `person` VALUES ('28', '测试', '10', 'address', '2018-10-16 15:49:46', '2018-10-16 15:49:46');
INSERT INTO `person` VALUES ('29', '测试', '10', 'address', '2018-10-16 15:50:40', '2018-10-16 15:50:40');
INSERT INTO `person` VALUES ('30', '测试', '10', 'address', '2018-10-16 15:51:31', '2018-10-16 15:51:31');
INSERT INTO `person` VALUES ('31', '测试', '10', 'address', '2018-10-16 15:54:37', '2018-10-16 15:54:37');
INSERT INTO `person` VALUES ('32', '测试', '10', 'address', '2018-10-16 16:00:51', '2018-10-16 16:00:51');
INSERT INTO `person` VALUES ('33', '测试', '10', 'address', '2018-10-16 17:17:44', '2018-10-16 17:17:44');
INSERT INTO `person` VALUES ('34', '测试', '10', 'address', '2018-10-16 17:18:43', '2018-10-16 17:18:43');
INSERT INTO `person` VALUES ('35', '测试', '10', 'address', '2018-10-16 17:20:04', '2018-10-16 17:20:04');
INSERT INTO `person` VALUES ('36', '测试', '10', 'address', '2018-10-16 17:23:57', '2018-10-16 17:23:57');
INSERT INTO `person` VALUES ('37', '测试', '20', 'address', '2018-10-16 17:29:25', '2018-10-16 17:29:25');
INSERT INTO `person` VALUES ('38', '测试', '20', 'address', '2018-10-16 17:32:14', '2018-10-16 17:32:14');
INSERT INTO `person` VALUES ('39', '测试', '20', 'address', '2018-10-16 17:33:58', '2018-10-16 17:33:58');
INSERT INTO `person` VALUES ('40', '测试', '20', 'address', '2018-10-16 17:35:38', '2018-10-16 17:35:38');
INSERT INTO `person` VALUES ('41', '测试', '20', 'address', '2018-10-17 11:01:13', '2018-10-17 11:01:13');
INSERT INTO `person` VALUES ('42', '测试', '10', 'address', '2018-10-17 11:04:25', '2018-10-17 11:04:25');
INSERT INTO `person` VALUES ('43', '测试', '10', 'address', '2018-10-17 11:08:58', '2018-10-17 11:08:58');

-- ----------------------------
-- Table structure for `t_task`
-- ----------------------------
DROP TABLE IF EXISTS `t_task`;
CREATE TABLE `t_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '物理主键',
  `jobname` varchar(100) NOT NULL COMMENT '任务名称',
  `method` varchar(100) NOT NULL COMMENT '任务执行方法',
  `cron` varchar(100) NOT NULL COMMENT 'cron表达式',
  `status` char(1) NOT NULL COMMENT '状态 0不可用 1可用',
  `jobdesc` varchar(300) DEFAULT NULL COMMENT '任务描述',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_task_jobname` (`jobname`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_task
-- ----------------------------
INSERT INTO `t_task` VALUES ('4', 'jobDetail', 'Hello', '0/5 * * * * ?', '1', 'jobDetail任务1', '2018-10-22 11:05:00', '2018-10-22 11:05:00');
INSERT INTO `t_task` VALUES ('5', 'jobDetail2', 'Hello2', '0/10 * * * * ?', '1', 'jobDetail任务2', '2018-10-22 11:05:17', '2018-10-22 11:05:17');
