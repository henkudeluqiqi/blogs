/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : blogs

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 01/12/2019 17:31:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blogs_article
-- ----------------------------
DROP TABLE IF EXISTS `blogs_article`;
CREATE TABLE `blogs_article`  (
  `blogs_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '博客文章的主键',
  `show_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `author_number` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '指向该文章的创建者',
  `blogs_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章标题',
  `blogs_content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章内容',
  `image_lists` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '该文章使用了哪些图片',
  `if_public` int(2) NOT NULL COMMENT '是否公开 1、是 2、否',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `blogs_source` int(11) NOT NULL COMMENT '文章来源',
  `read_size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '阅读的数量',
  `like` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '点赞的数量',
  `retain1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章的状态 1、正常 2、删除',
  `retain2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `retain3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`blogs_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blogs_article
-- ----------------------------
INSERT INTO `blogs_article` VALUES (1, 'http://39.105.41.2:9000/king2-product-image/king2-BRAND-LOGO-SP7FD5F6064FF9907DD1A16D0FE8EB593295.jpg', 'luqiqiya', '测试文章数据1', '测试文章数据1测试文章数据1测试文章数据1测试文章数据1测试文章数据1测试文章数据1测试文章数据1测试文章数据1测试文章数据1测试文章数据1测试文章数据1', 'http://39.105.41.2:9000/king2-product-image/king2-BRAND-LOGO-SP7FD5F6064FF9907DD1A16D0FE8EB593295.jpg', 1, '2019-11-24 19:58:16', '2019-11-24 19:58:19', 1, '10000', '1000', '1', NULL, NULL);
INSERT INTO `blogs_article` VALUES (2, 'http://39.105.41.2:9000/king2-product-image/king2-BRAND-LOGO-SP9A4C10A5461C9553303383BFA4FA593293.jpg', 'ziqing', '测试文章2', '测试文章2测试文章2测试文章2测试文章2测试文章2测试文章2测试文章2测试文章2测试文章2测试文章2', 'http://39.105.41.2:9000/king2-product-image/king2-BRAND-LOGO-SP9A4C10A5461C9553303383BFA4FA593293.jpg', 1, '2019-11-24 19:59:10', '2019-11-24 19:59:12', 1, '3333', '333', '1', NULL, NULL);
INSERT INTO `blogs_article` VALUES (3, 'http://39.105.41.2:9000/king2-product-image/BDDFF87E-A0D0-40A9-A659-3788C2283F8D.gif', 'xiechangyi', '测试文章3', '测试文章3测试文章3测试文章3测试文章3测试文章3测试文章3', 'http://39.105.41.2:9000/king2-product-image/BDDFF87E-A0D0-40A9-A659-3788C2283F8D.gif', 1, '2019-11-24 20:00:13', '2019-11-24 20:00:15', 1, '40000', '444', '1', NULL, NULL);

-- ----------------------------
-- Table structure for blogs_skill
-- ----------------------------
DROP TABLE IF EXISTS `blogs_skill`;
CREATE TABLE `blogs_skill`  (
  `skill_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '掌握技能自增id',
  `skill_key` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '掌握技能的名称',
  PRIMARY KEY (`skill_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blogs_skill
-- ----------------------------
INSERT INTO `blogs_skill` VALUES (1, 'Java');
INSERT INTO `blogs_skill` VALUES (2, 'HTML');

-- ----------------------------
-- Table structure for blogs_skill_value
-- ----------------------------
DROP TABLE IF EXISTS `blogs_skill_value`;
CREATE TABLE `blogs_skill_value`  (
  `skill_value_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '掌握的技能的值',
  `skill_key_id` int(11) NOT NULL COMMENT '指向掌握技能的主键。',
  `skill_value` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '掌握技能的值',
  `skill_desc_content` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '掌握技能描述',
  `user_name` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '这个技能是属于谁的',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `retain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `retain2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`skill_value_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blogs_skill_value
-- ----------------------------
INSERT INTO `blogs_skill_value` VALUES (1, 1, '100', '我对Java很掌握', 'luqiqiya', '2019-12-01 16:52:27', NULL, NULL, NULL);
INSERT INTO `blogs_skill_value` VALUES (2, 2, '47', '我对HTML不怎么掌握', 'luqiqiya', '2019-12-01 17:10:25', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for blogs_source
-- ----------------------------
DROP TABLE IF EXISTS `blogs_source`;
CREATE TABLE `blogs_source`  (
  `blogs_source_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章的来源主键\r\n',
  `souce_content` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`blogs_source_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for blogs_user
-- ----------------------------
DROP TABLE IF EXISTS `blogs_user`;
CREATE TABLE `blogs_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表的自增Id',
  `user_name` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名，唯一不可重复，7~11位',
  `pass_word` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码，7~15',
  `u_email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `u_name` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '称呼',
  `u_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '头像',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间，初始化就是null',
  `state` int(2) NOT NULL COMMENT '状态，1、正常 2、异常',
  `retain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '三个保留字段',
  `retain2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `retain3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blogs_user
-- ----------------------------
INSERT INTO `blogs_user` VALUES (1, 'luqiqiya', '4c9ae6a0d44b17c7822d7741bab4ee77', '2246483156@qq.com', '鹿七七', 'http://39.105.41.2:9000/king2-product-image/kodinger.jpg', '2019-11-21 19:34:47', NULL, 1, '', '', '');
INSERT INTO `blogs_user` VALUES (5, 'xiechangyi', 'fcea920f7412b5da7be0cf42b8c93759', NULL, '鹿七七2', 'http://39.105.41.2:9000/king2-product-image/kodinger.jpg', '2019-11-22 21:09:07', NULL, 1, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for blogs_user_else_info
-- ----------------------------
DROP TABLE IF EXISTS `blogs_user_else_info`;
CREATE TABLE `blogs_user_else_info`  (
  `else_info_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户其他信息表的自增id',
  `intro_content` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户简介的信息',
  `skill_id` int(11) NULL DEFAULT NULL COMMENT '指向掌握技能的id',
  `skill_value` int(3) NULL DEFAULT NULL COMMENT '掌握技能的熟练程度',
  `skill_intro_content` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '掌握节能的描述',
  `user_git_addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户的GitHub地址',
  `look_size` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问的人数',
  `retain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '指向属于哪个用户 ，（用户名）',
  `retain2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`else_info_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
