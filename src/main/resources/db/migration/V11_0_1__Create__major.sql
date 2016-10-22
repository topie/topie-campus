DROP TABLE IF EXISTS `t_faculty`;
CREATE TABLE `t_faculty` (
  `xdm` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '系代码',
  `xmc` varchar(255) DEFAULT NULL,
  `ssxydm` varchar(255) DEFAULT NULL,
  `xfzr` varchar(255) DEFAULT NULL,
  `lxfs` varchar(255) DEFAULT NULL,
  `xclsj` varchar(255) DEFAULT NULL,
  `xkyjyjfx` varchar(255) DEFAULT NULL,
  `xjj` varchar(255) DEFAULT NULL,
  `kyjjxcg` varchar(255) DEFAULT NULL,
  `bz` varchar(255) DEFAULT NULL,
  `ssxsxydm` varchar(255) DEFAULT NULL,
  `xywmc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`xdm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_faculty
-- ----------------------------
INSERT INTO `t_faculty` VALUES ('030407', '党委学生工作部（处）', '0304', null, null, null, null, null, null, null, '0304', null);
INSERT INTO `t_faculty` VALUES ('030409', '团委', '0304', null, null, null, null, null, null, null, '0304', null);
INSERT INTO `t_faculty` VALUES ('030417', '教务处', '0304', null, null, null, null, null, null, null, '0304', null);
INSERT INTO `t_faculty` VALUES ('030441', '公共基础课教学部', '0304', null, null, null, null, null, null, null, '0304', null);
INSERT INTO `t_faculty` VALUES ('030445', '体育教学部', '0304', null, null, null, null, null, null, null, '0304', null);
INSERT INTO `t_faculty` VALUES ('030448', '工程与艺术系', '0304', null, null, null, null, null, null, null, '0304', null);
INSERT INTO `t_faculty` VALUES ('030451', '信息与控制工程系', '0304', null, null, null, null, null, null, null, '0304', null);
INSERT INTO `t_faculty` VALUES ('030453', '生物医药系', '0304', null, null, null, null, null, null, null, '0304', null);
INSERT INTO `t_faculty` VALUES ('030455', '经济管理系', '0304', null, null, null, null, null, null, null, '0304', null);
INSERT INTO `t_faculty` VALUES ('030457', '工程艺术系', '0304', null, null, null, null, null, null, null, '0304', null);
INSERT INTO `t_faculty` VALUES ('030460', '食品科学系', '0304', null, null, null, null, null, null, null, null, null);
INSERT INTO `t_faculty` VALUES ('030463', '工程管理系', '0304', null, null, null, null, null, null, null, '0304', null);
INSERT INTO `t_faculty` VALUES ('030474', '生物化工实践教学中心', '0304', null, null, null, null, null, null, null, '0304', null);
INSERT INTO `t_faculty` VALUES ('030485', '培训中心', '0304', null, null, null, null, null, null, null, '0304', null);

-- ----------------------------
-- Table structure for t_major
-- ----------------------------
DROP TABLE IF EXISTS `t_major`;
CREATE TABLE `t_major` (
  `zydm` varchar(4) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '专业代码',
  `zymc` varchar(120) DEFAULT NULL COMMENT '专业名称',
  `zylb` varchar(20) DEFAULT NULL COMMENT '专业类别',
  `xz` int(11) DEFAULT NULL COMMENT '学制',
  `xw` varchar(20) DEFAULT NULL COMMENT '学位',
  `zypymb` text,
  `zypyyq` text,
  `zykc` text,
  `tskc` text,
  `zyywmc` varchar(100) DEFAULT NULL,
  `ssxydm` varchar(4) DEFAULT NULL,
  `yxj` varchar(6) DEFAULT NULL,
  `ssxdm` varchar(6) DEFAULT NULL,
  `zyjc` varchar(30) DEFAULT NULL,
  `jzydm` varchar(8) DEFAULT NULL,
  `cc` varchar(20) DEFAULT NULL,
  `xklb` varchar(30) DEFAULT NULL,
  `bz` varchar(30) DEFAULT NULL,
  `zszydm` varchar(10) DEFAULT NULL,
  `tjzymc` varchar(40) DEFAULT NULL,
  `sfwy` varchar(2) DEFAULT NULL,
  `sfsf` varchar(2) DEFAULT NULL,
  `sfwxw` varchar(2) DEFAULT NULL,
  `kxlb` varchar(20) DEFAULT NULL,
  `bks` varchar(4) DEFAULT NULL,
  `ssxsxydm` varchar(10) DEFAULT NULL,
  `sfxs` varchar(2) DEFAULT NULL,
  `xkjxpt` varchar(30) DEFAULT NULL,
  `bkflmc` varchar(10) DEFAULT NULL,
  `ywxwlb` varchar(50) DEFAULT NULL,
  `gbxh` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`zydm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_major
-- ----------------------------
INSERT INTO `t_major` VALUES ('04A1', '过程装备与控制工程', '机械类', '4', '工学学士', null, null, null, null, 'Process Equipment and Control', '0304', '1', null, null, '080304', '本科', '工学', '080206', null, '过程装备与控制工程', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04B1', '自动化', '自动化类', '4', '工学学士', null, null, null, null, 'Automation', '0304', '1', '030451', null, '080602', '本科', '工学', '080801', null, '自动化', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04C1', '生物医学工程', '生物医学工程类', '4', '工学学士', null, null, null, null, 'Biomedical Engineering', '0304', '1', '030453', null, '080607', '本科', '工学', '082601', null, '生物医学工程', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04D0', '建筑环境与设备工程(专科起点)', '土建类', '2', '工学学士', null, null, null, null, 'Building Environment and Equipment Engineering(junior college as Starting Point)', '0304', '1', '030451', null, '080704H', '专接本', '工学', '12目录取消', null, '建筑环境与设备工程', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04D1', '建筑环境与设备工程', '土建类', '4', '工学学士', null, null, null, null, 'Building Environment and Equipment Engineering', '0304', '1', '030451', null, '080704H', '本科', '工学', '12目录取消', null, '建筑环境与设备工程', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04D2', '建筑环境与能源应用工程', '土木类', '4', '工学学士', null, null, null, null, null, '0304', '1', '030451', null, '081002', '本科', '工学', '081002', null, '建筑环境与能源应用工程', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04D4', '建筑环境与能源应用工程(专科起点)', '土木类', '2', '工学学士', null, null, null, null, null, '0304', '1', '030451', null, '081002', '专接本', '工学', '081002', null, '建筑环境与能源应用工程', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04E1', '生物工程', '生物工程类', '4', '工学学士', null, null, null, null, 'Bioengineering', '0304', '1', '030453', null, '081801', '本科', '工学', '083001', null, '生物工程', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04F1', '制药工程', '化工与制药类', '4', '工学学士', null, null, null, null, 'Pharmaceutical Engineering', '0304', '1', '030453', null, '081102', '本科', '工学', '081302', null, '制药工程', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04G1', '化学工程与工艺', '化工与制药类', '4', '工学学士', null, null, null, null, 'Chemical Engineering and Technology', '0304', '1', null, null, '081101H', '本科', '工学', '081301', null, '化学工程与工艺', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04H1', '材料科学与工程(生物材料)', '材料科学类', '4', '工学学士', null, null, null, null, 'Material Science and Engineering (Biomaterials)', '0304', '1', '030463', null, '080205Y', '本科', '工学', '080401', null, '材料科学与工程', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04H2', '材料科学与工程(质量工程)', '材料科学类', '4', '工学学士', null, null, null, null, 'Material Science and Engineering (Quality Engineering)', '0304', '1', '030463', null, '080205Y', '本科', '工学', '080401', null, '材料科学与工程', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04I0', '人力资源管理(专科起点)', '工商管理类', '2', '管理学学士', null, null, null, null, 'Human Resource Management(junior college as Starting Point)', '0304', '1', '030455', null, '110205H', '专接本', '管理学', '120206', null, '人力资源管理', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04I1', '人力资源管理', '工商管理类', '4', '管理学学士', null, null, null, null, 'Human Resource Management', '0304', '1', '030455', null, '110205H', '本科', '管理学', '120206', null, '人力资源管理', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04J1', '工业设计', '机械类', '4', '工学学士', null, null, null, null, 'Industrial Design', '0304', '1', '030457', null, '080303', '本科', '工学', '080205', null, '工业设计', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04K1', '包装工程(包装设计)', '轻工类', '4', '工学学士', null, null, null, null, 'Packaging Engineering(Packing Design)', '0304', '1', '030457', null, '081403', '本科', '工学', '081702', null, '包装工程', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04K2', '包装工程', '轻工类', '4', '工学学士', null, null, null, null, 'Packaging Engineering', '0304', '1', '030457', null, '081403', '本科', '工学', '081702', null, '包装工程', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04L1', '工程管理', '管理科学与工程类', '4', '管理学学士', null, null, null, null, 'Project management', '0304', '1', '030463', null, '110104', '本科', '管理学', '120103', null, '工程管理', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04L2', '工程管理(装饰工程管理)', '管理科学与工程类', '4', '管理学学士', null, null, null, null, 'Project management (project management decorative)', '0304', '1', '030463', null, '110104', '本科', '管理学', '120103', null, '工程管理', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04L3', '工程管理(工程项目管理)', '管理科学与工程类', '4', '管理学学士', null, null, null, null, 'Project Management (Project Management)', '0304', '1', '030463', null, '110104', '本科', '管理学', '120103', null, '工程管理', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04L4', '工程管理(投资与造价管理)', '管理科学与工程类', '4', '管理学学士', null, null, null, null, 'Project management (investment and cost management)', '0304', '1', '030463', null, '110104', '本科', '管理学', '120103', null, '工程管理', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04M1', '建筑电气与智能化', '土木类', '4', '工学学士', null, null, null, null, 'Electrical and intelligent building', '0304', '1', '030451', null, '080712', '本科', '工学', '081004', null, '建筑电气与智能化', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04N1', '食品质量与安全', '食品科学与工程类', '4', '工学学士', null, null, null, null, 'Food Quality and Safety', '0304', '1', '030122', null, '081407W', '本科', '工学', '082702', null, '食品质量与安全', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04O1', '食品科学与工程', '食品科学与工程类', '4', '工学学士', null, null, null, null, 'Food Science and Engineering', '0304', '1', '030122', null, '081401H', '本科', '工学', '082701', null, '食品科学与工程', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04O2', '食品科学与工程(食品营养)', '食品科学与工程类', '4', '工学学士', null, null, null, null, 'Food Science and Engineering', '0304', '1', '030122', null, '081401H', '本科', '工学', '082701', null, '食品科学与工程', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04P9', '食品科学与工程类', null, '4', null, null, null, null, null, 'Food Science and Engineering', '0304', '1', '030122', null, null, '本科', null, null, null, null, null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04S1', '工商管理', '工商管理类', '4', '管理学学士', null, null, null, null, 'Business Administration', '0304', '1', '030455', null, '110201H', '本科', '管理学', '120201K', null, '工商管理', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04T0', '会计学(专科起点)', '工商管理类', '2', '管理学学士', null, null, null, null, 'Accounting (junior college as Starting Point)', '0304', '1', '030455', null, '110203H', '专接本', '管理学', '120203K', null, '会计学', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04T1', '会计学', '工商管理类', '4', '管理学学士', null, null, null, null, 'Accounting', '0304', '1', '030455', null, '110203H', '本科', '管理学', '120203K', null, '会计学', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04a1', '供热通风与空调工程技术', '工学', '3', null, null, null, null, null, 'Heating Ventilation and Air Conditioning Engineering Technology', '0304', '1', null, null, '560402', '高职', '高职工科', null, null, '供热通风与空调工程技术', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04b1', '物业设施管理', '工学', '3', null, null, null, null, null, 'Property and Facility Management', '0304', '1', '030451', null, '560703', '高职', '高职工科', null, null, '物业设施管理', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04c1', '医疗器械制造与维护', '工学', '3', null, null, null, null, null, 'Medical Apparatus Manufacture and Maintenance', '0304', '1', null, null, '580112', '高职', null, null, null, '医疗器械制造与维护', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04d1', '计算机控制技术', '工学', '3', null, null, null, null, null, 'Computer Controlling Technology', '0304', '1', '030451', null, '580205', '高职', '高职工科', null, null, '计算机控制技术', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04e1', '计算机应用技术', '工学', '3', null, null, null, null, null, 'Computer Applications Technology', '0304', '1', '030451', null, '590101', '高职', '高职工科', null, null, '计算机应用技术', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04f1', '药物制剂技术', '工学', '3', null, null, null, null, null, 'Pharmaceutical Technology', '0304', '1', '030453', null, '530305', '高职', '高职工科', null, null, '药物制剂技术', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04g1', '会计', '管理学', '3', null, null, null, null, null, 'Accounting', '0304', '1', '030455', null, '620203', '高职', '高职文科', null, null, '会计', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04h1', '视觉传达艺术设计', '艺术', '3', null, null, null, null, null, 'Visual Communications Art Design', '0304', '1', '030457', null, '670103', '高职', '高职艺术', null, null, '视觉传达艺术设计', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);
INSERT INTO `t_major` VALUES ('04m1', '楼宇智能化工程技术', null, '3', null, null, null, null, null, 'Intelligent building engineering', '0304', '1', '030451', null, '560404', '高职', '高职工科', null, null, '楼宇智能化工程技术', null, null, null, '123456789BCDEFGHIJ', null, '0304', '0', null, null, null, null);

DROP TABLE IF EXISTS `t_college`;
CREATE TABLE `t_college` (
  `xydm` varchar(255) NOT NULL,
  `xymc` varchar(255) DEFAULT NULL,
  `xyjc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`xydm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_college
-- ----------------------------
INSERT INTO `t_college` VALUES ('0304', '生物化学工程学院', '生化学院');

DROP TABLE IF EXISTS `t_user_faculty`;
CREATE TABLE `t_user_faculty` (
  `user_id` int(11) NOT NULL,
  `faculty` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`,`faculty`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;