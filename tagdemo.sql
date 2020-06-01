/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : tagdemo

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 22/05/2020 20:20:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
CREATE DATABASE `tagdemo`;
use `tagdemo`;
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hierarchy` int(11) NULL DEFAULT NULL COMMENT '层级关系',
  `parentId` int(11) NULL DEFAULT NULL COMMENT '上级id',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `type` int(11) NULL DEFAULT NULL COMMENT '是否互斥 0-不互斥 1-互斥',
  `del` int(11) NULL DEFAULT 0 COMMENT '是否删除 0-正常 1-删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES (1, 0, NULL, '英语', 0, 0);
INSERT INTO `tag` VALUES (2, 1, 1, '考试', 0, 0);
INSERT INTO `tag` VALUES (3, 1, 1, '内容', 0, 0);
INSERT INTO `tag` VALUES (4, 2, 2, '出国考试', 1, 0);
INSERT INTO `tag` VALUES (5, 2, 2, '国内统考', 1, 0);
INSERT INTO `tag` VALUES (6, 2, 3, '电视节目', 1, 0);
INSERT INTO `tag` VALUES (7, 3, 4, '雅思', 1, 0);
INSERT INTO `tag` VALUES (8, 3, 4, '托福', 1, 0);
INSERT INTO `tag` VALUES (9, 3, 4, 'GRE', 1, 0);
INSERT INTO `tag` VALUES (10, 3, 5, '大学四级 ', 1, 0);
INSERT INTO `tag` VALUES (11, 3, 5, '大学六级 ', 1, 0);
INSERT INTO `tag` VALUES (12, 3, 6, '老友记', 1, 0);
INSERT INTO `tag` VALUES (13, 3, 6, '生活大爆炸', 1, 0);

SET FOREIGN_KEY_CHECKS = 1;
