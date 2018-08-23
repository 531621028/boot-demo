CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) NOT NULL COMMENT '用户id',
  `nick_name` varchar(255) NOT NULL COMMENT '用户昵称',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `phone` varchar(255) NOT NULL COMMENT '电话',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '用户的状态,1:正常状态,2：用户被锁定',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) NOT NULL COMMENT '角色',
  `description` varchar(255) NOT NULL COMMENT '描述',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '角色的状态,1:正常状态,2：用户被锁定',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
