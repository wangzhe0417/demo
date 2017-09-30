-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(50) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) NOT NULL,
  `user_passwd` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `unique_name` (`user_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;