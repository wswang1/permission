/*
Navicat MySQL Data Transfer

Source Server         : 47.98.146.179
Source Server Version : 50727
Source Host           : 47.98.146.179:3306
Source Database       : shuang

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2019-12-18 11:11:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(64) DEFAULT NULL,
  `path` varchar(64) DEFAULT NULL,
  `component` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `icon` varchar(64) DEFAULT NULL,
  `keepAlive` tinyint(1) DEFAULT NULL,
  `requireAuth` tinyint(1) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  `hidden` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', 'dashboard', 'dashboard', 'dashboard/index', '主页', 'dashboard', null, null, '0', '1', null);
INSERT INTO `menu` VALUES ('2', '/test1', '/test1', null, '测试一', 'form', null, null, '0', '1', null);
INSERT INTO `menu` VALUES ('3', '/test1/test1-1', '/test1/test1-1', 'test1/test1-1', '测试一-1', 'form', null, null, '2', '1', null);
INSERT INTO `menu` VALUES ('4', '/test/test1-2', '/test/test1-2', 'test1/test1-2', '测试一-2', 'form', null, null, '2', '1', null);
INSERT INTO `menu` VALUES ('5', '/test2', '/test2', null, '测试二', 'form', null, null, '0', '1', null);
INSERT INTO `menu` VALUES ('6', '/test2/test1', '/test2/test1', 'test2/test2-1', '测试二-1', 'form', null, null, '5', '1', null);
INSERT INTO `menu` VALUES ('11', null, 'test3', null, '测试三', 'form', null, null, '0', '1', null);
INSERT INTO `menu` VALUES ('12', null, 'test3/test1', null, '测试三-1', 'form', null, null, '11', '1', null);
INSERT INTO `menu` VALUES ('13', '', '/hr', 'test1/test1-2', '人力资源', 'form', null, null, '0', '1', null);
INSERT INTO `menu` VALUES ('14', '', '/hr/hr1', 'test1/test1-2', '人事调动', 'form', null, null, '13', '1', null);
INSERT INTO `menu` VALUES ('15', '', '/hr/hr2', 'test1/test1-2', '考勤管理', 'form', null, null, '13', '1', null);
INSERT INTO `menu` VALUES ('16', null, '/finance', 'test1/test1-2', '财务管理', 'form', null, null, '0', '1', null);
INSERT INTO `menu` VALUES ('17', null, '/finance/finance1', 'test1/test1-2', '工资发放', 'form', null, null, '16', '1', null);
INSERT INTO `menu` VALUES ('18', null, '/finance/finance2', 'test1/test1-2', '投资管理', 'form', null, null, '16', '1', null);
INSERT INTO `menu` VALUES ('99', '/permission', '/permission', 'test1/test1-2', '权限管理', 'form', null, null, '0', '1', null);
INSERT INTO `menu` VALUES ('100', '/permission/user', '/permission/user', 'user/index', '用户管理', 'form', null, null, '99', '1', null);
INSERT INTO `menu` VALUES ('101', '/permission/role', '/permission/role', 'role/index', '角色管理', 'form', null, null, '99', '1', null);
INSERT INTO `menu` VALUES ('102', '/permission/menu', '/permission/menu', 'menu/index', '菜单管理', 'form', null, null, '99', '1', null);

-- ----------------------------
-- Table structure for menu_role
-- ----------------------------
DROP TABLE IF EXISTS `menu_role`;
CREATE TABLE `menu_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1313 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu_role
-- ----------------------------
INSERT INTO `menu_role` VALUES ('1175', '1', '10');
INSERT INTO `menu_role` VALUES ('1176', '3', '10');
INSERT INTO `menu_role` VALUES ('1177', '4', '10');
INSERT INTO `menu_role` VALUES ('1206', '1', '16');
INSERT INTO `menu_role` VALUES ('1219', '1', '17');
INSERT INTO `menu_role` VALUES ('1220', '13', '17');
INSERT INTO `menu_role` VALUES ('1221', '14', '17');
INSERT INTO `menu_role` VALUES ('1222', '15', '17');
INSERT INTO `menu_role` VALUES ('1238', '1', '18');
INSERT INTO `menu_role` VALUES ('1239', '16', '18');
INSERT INTO `menu_role` VALUES ('1240', '17', '18');
INSERT INTO `menu_role` VALUES ('1241', '18', '18');
INSERT INTO `menu_role` VALUES ('1279', '1', '13');
INSERT INTO `menu_role` VALUES ('1280', '2', '13');
INSERT INTO `menu_role` VALUES ('1281', '3', '13');
INSERT INTO `menu_role` VALUES ('1282', '4', '13');
INSERT INTO `menu_role` VALUES ('1283', '5', '13');
INSERT INTO `menu_role` VALUES ('1284', '6', '13');
INSERT INTO `menu_role` VALUES ('1285', '11', '13');
INSERT INTO `menu_role` VALUES ('1286', '12', '13');
INSERT INTO `menu_role` VALUES ('1287', '13', '13');
INSERT INTO `menu_role` VALUES ('1288', '14', '13');
INSERT INTO `menu_role` VALUES ('1289', '15', '13');
INSERT INTO `menu_role` VALUES ('1290', '16', '13');
INSERT INTO `menu_role` VALUES ('1291', '17', '13');
INSERT INTO `menu_role` VALUES ('1292', '18', '13');
INSERT INTO `menu_role` VALUES ('1293', '19', '13');
INSERT INTO `menu_role` VALUES ('1294', '20', '13');
INSERT INTO `menu_role` VALUES ('1295', '99', '13');
INSERT INTO `menu_role` VALUES ('1296', '100', '13');
INSERT INTO `menu_role` VALUES ('1297', '101', '13');
INSERT INTO `menu_role` VALUES ('1298', '102', '13');
INSERT INTO `menu_role` VALUES ('1299', '1', '14');
INSERT INTO `menu_role` VALUES ('1300', '2', '14');
INSERT INTO `menu_role` VALUES ('1301', '3', '14');
INSERT INTO `menu_role` VALUES ('1302', '4', '14');
INSERT INTO `menu_role` VALUES ('1303', '5', '14');
INSERT INTO `menu_role` VALUES ('1304', '6', '14');
INSERT INTO `menu_role` VALUES ('1305', '11', '14');
INSERT INTO `menu_role` VALUES ('1306', '12', '14');
INSERT INTO `menu_role` VALUES ('1307', '1', '15');
INSERT INTO `menu_role` VALUES ('1308', '2', '15');
INSERT INTO `menu_role` VALUES ('1309', '3', '15');
INSERT INTO `menu_role` VALUES ('1310', '4', '15');
INSERT INTO `menu_role` VALUES ('1311', '5', '15');
INSERT INTO `menu_role` VALUES ('1312', '6', '15');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `nameZh` varchar(64) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('13', '超级管理员', '超级管理员，啥都能做！');
INSERT INTO `role` VALUES ('14', '董事长', '公司董事长');
INSERT INTO `role` VALUES ('15', '部门经理', '公司部门经理');
INSERT INTO `role` VALUES ('16', '普通员工', '公司普通员工');
INSERT INTO `role` VALUES ('17', '人事主管', '公司人事');
INSERT INTO `role` VALUES ('18', '财务主管', '公司财务主管');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'USERID',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `phone` char(11) DEFAULT NULL COMMENT '手机号码',
  `telephone` varchar(16) DEFAULT NULL COMMENT '住宅电话',
  `address` varchar(64) DEFAULT NULL COMMENT '联系地址',
  `enabled` tinyint(1) DEFAULT '1',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `userface` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('10', '老二', '', '11111111111', '', '1', '0002', '$2a$10$7Mq4JNYe/osVgRfLeaBoS.UE1ruashUXZU08qx/12T1LE4X4ngGN.', null, '');
INSERT INTO `user` VALUES ('11', '老王', '', '15967893822', '', '1', '0001', '$2a$10$6B27EdMgGkkjyF0vUK1gIeCzQ78Iqv8wucMkdrdxbB/H6H8CewAQ6', null, '');
INSERT INTO `user` VALUES ('12', '老李', '', '15967893822', '', '1', '0003', '$2a$10$O2QbEDoSYkWV78DbhQhGOeMmWuwr8q1orIUawAbH0oggK3xksWNxy', null, '');
INSERT INTO `user` VALUES ('13', '管理员', '', '12333333333', '', '1', 'admin', '$2a$10$IpasEhbt043GVA1RVcadRevPlwQk1PntJ1Tk1B.oRuiHzgPlmV9bi', null, '');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('119', '10', '13');
INSERT INTO `user_role` VALUES ('120', '13', '13');
INSERT INTO `user_role` VALUES ('122', '11', '17');
INSERT INTO `user_role` VALUES ('123', '12', '18');
