/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50549
Source Host           : localhost:3306
Source Database       : cook

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2018-04-24 23:09:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `aid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `aname` varchar(20) DEFAULT NULL COMMENT '管理员账号',
  `apassword` varchar(20) DEFAULT NULL COMMENT '密码',
  `aemail` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `atelphone` varchar(20) DEFAULT NULL COMMENT '电话',
  `birthday` date DEFAULT NULL COMMENT '生日',
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'huang', '123456', '1135586231@qq.com', '17705901111', '1997-07-23');
INSERT INTO `admin` VALUES ('2', 'lai', '123456', '15824963@qq.com', '184595672', '1995-05-12');
INSERT INTO `admin` VALUES ('3', 'hahaha', '123123', 'dsfsfs@qq.com', '15852459221', '1998-04-14');
INSERT INTO `admin` VALUES ('9', 'jing', '123456', '113558623@qq.com', '14725825825', '2014-01-01');
INSERT INTO `admin` VALUES ('12', 'aaaaa', '123456', '1234567@qq.com', '12233454555', '2002-01-23');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `cid` bigint(20) NOT NULL AUTO_INCREMENT,
  `cname` varchar(20) DEFAULT NULL,
  `cpic` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '家常菜', 'http://s2.cdn.xiachufang.com/0745f4bc8ac111e6b87c0242ac110003_1239w_1239h.jpg?imageView2/2/w/660/interlace/1/q/90');
INSERT INTO `category` VALUES ('2', '快手菜', 'http://s2.cdn.xiachufang.com/9e23a6a088d611e6a9a10242ac110002_640w_640h.jpg?imageView2/2/w/660/interlace/1/q/90');
INSERT INTO `category` VALUES ('3', '下饭菜', 'http://s1.cdn.xiachufang.com/7ab19900896411e6b87c0242ac110003_1239w_828h.jpg@2o_50sh_1pr_1l_215w_138h_1c_1e_90q_1wh');
INSERT INTO `category` VALUES ('4', '早餐', 'http://s2.cdn.xiachufang.com/f672d1048af811e6b87c0242ac110003_1616w_1081h.jpg?http://s2.cdn.xiachufang.com/e05d14ea12ae11e7947d0242ac110002_900w_600h.jpg?imageView2/2/w/660/interlace/1/q/90');
INSERT INTO `category` VALUES ('5', '减肥', 'http://s2.cdn.xiachufang.com/2fe5945c8a6e11e6a9a10242ac110002_2048w_2048h.jpg?imageView2/2/w/660/interlace/1/q/90');
INSERT INTO `category` VALUES ('6', '汤羹', 'http://s1.cdn.xiachufang.com/2e58856088be11e6b87c0242ac110003_1200w_798h.jpg@2o_50sh_1pr_1l_215w_136h_1c_1e_90q_1wh');
INSERT INTO `category` VALUES ('7', '烘焙', 'http://s2.cdn.xiachufang.com/bb22f46c89fe11e6a9a10242ac110002_529w_705h.jpg?imageView2/2/w/660/interlace/1/q/90');
INSERT INTO `category` VALUES ('8', '英雄', 'http://s2.cdn.xiachufang.com/60742eec895311e6b87c0242ac110003_2000w_1501h.jpg?imageView2/1/w/215/h/138/interlace/1/q/90');
INSERT INTO `category` VALUES ('9', '健康餐', 'http://s2.cdn.xiachufang.com/7166657aac4a11e6bc9d0242ac110002_960w_722h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90');
INSERT INTO `category` VALUES ('10', '蝴蝶酥', 'http://s2.cdn.xiachufang.com/60742eec895311e6b87c0242ac110003_2000w_1501h.jpg?imageView2/1/w/215/h/138/interlace/1/q/90');
INSERT INTO `category` VALUES ('11', '午餐', 'http://s1.cdn.xiachufang.com/d7519912873e11e6b87c0242ac110003_3872w_2592h.jpg@2o_50sh_1pr_1l_215w_136h_1c_1e_90q_1wh');

-- ----------------------------
-- Table structure for evaluate
-- ----------------------------
DROP TABLE IF EXISTS `evaluate`;
CREATE TABLE `evaluate` (
  `evaluate_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '评价编号',
  `evaluate_content` varchar(100) DEFAULT NULL COMMENT '评价内容',
  `evalute_date` date DEFAULT NULL COMMENT '评价时间',
  `user_id` bigint(20) DEFAULT NULL COMMENT '评价人',
  `mid` bigint(20) DEFAULT NULL COMMENT '评价菜谱',
  `score` float DEFAULT NULL COMMENT '评分',
  PRIMARY KEY (`evaluate_id`),
  KEY `FK_evaluate` (`mid`),
  KEY `FK_uid` (`user_id`),
  CONSTRAINT `FK_evaluate` FOREIGN KEY (`mid`) REFERENCES `menu` (`mid`),
  CONSTRAINT `FK_uid` FOREIGN KEY (`user_id`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of evaluate
-- ----------------------------
INSERT INTO `evaluate` VALUES ('1', '好吃 怎么做到', '2017-11-15', '1', '1', '4.5');
INSERT INTO `evaluate` VALUES ('2', '哈哈哈', '2017-11-03', '1', '1', '4.8');

-- ----------------------------
-- Table structure for material
-- ----------------------------
DROP TABLE IF EXISTS `material`;
CREATE TABLE `material` (
  `material_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '食材id',
  `material_name` varchar(20) DEFAULT NULL COMMENT '食材名称',
  `material_weight` varchar(20) DEFAULT NULL COMMENT '食材分量',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '对应菜谱',
  PRIMARY KEY (`material_id`),
  KEY `FK_material` (`menu_id`),
  CONSTRAINT `FK_material` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=3037 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of material
-- ----------------------------
INSERT INTO `material` VALUES ('1001', '鸡蛋', '2个', '1');
INSERT INTO `material` VALUES ('1002', '西红柿', '1个', '1');
INSERT INTO `material` VALUES ('1003', '油盐糖', '少许', '1');
INSERT INTO `material` VALUES ('3001', '鸡翅（翅中最佳）', '8个', '3');
INSERT INTO `material` VALUES ('3002', '可乐', '易拉罐一罐', '3');
INSERT INTO `material` VALUES ('3004', '料酒', '3勺', '3');
INSERT INTO `material` VALUES ('3005', '老抽', '2勺', '3');
INSERT INTO `material` VALUES ('3006', '生抽', '2勺', '3');
INSERT INTO `material` VALUES ('3007', '生姜片（可有可无，有则更好）', '5片', '3');
INSERT INTO `material` VALUES ('3008', '桂皮（可有可无，有则更好）', '半只或一只', '3');
INSERT INTO `material` VALUES ('3009', '猪小排', '500克', '4');
INSERT INTO `material` VALUES ('3010', '料酒', '1汤勺（1汤勺为15毫升容量的勺）', '4');
INSERT INTO `material` VALUES ('3011', '盐', '3克', '4');
INSERT INTO `material` VALUES ('3012', '酱油', '2汤勺（15毫克每汤勺）', '4');
INSERT INTO `material` VALUES ('3013', '米醋', '3汤勺（15毫克每汤勺）', '4');
INSERT INTO `material` VALUES ('3014', '白糖', '4汤勺（15毫克每汤勺）', '4');
INSERT INTO `material` VALUES ('3015', '姜片', null, '4');
INSERT INTO `material` VALUES ('3016', '对虾', '8只', '5');
INSERT INTO `material` VALUES ('3017', '姜', null, '5');
INSERT INTO `material` VALUES ('3018', '蒜', null, '5');
INSERT INTO `material` VALUES ('3019', '香葱', null, '5');
INSERT INTO `material` VALUES ('3020', '白糖', null, '5');
INSERT INTO `material` VALUES ('3021', '番茄酱（或西红柿）', null, '5');
INSERT INTO `material` VALUES ('3022', '生抽', null, '5');
INSERT INTO `material` VALUES ('3023', '料酒', null, '5');
INSERT INTO `material` VALUES ('3024', '盐', null, '5');
INSERT INTO `material` VALUES ('3025', '豆腐', '1块', '6');
INSERT INTO `material` VALUES ('3026', '青葱', '1条', '6');
INSERT INTO `material` VALUES ('3027', '蒜蓉', '2瓣', '6');
INSERT INTO `material` VALUES ('3028', '肉碎（牛肉碎最佳）', '为豆腐重量的1/10', '6');
INSERT INTO `material` VALUES ('3029', '郫县豆瓣酱', '1大匙', '6');
INSERT INTO `material` VALUES ('3030', '酱油（生抽）', '少许', '6');
INSERT INTO `material` VALUES ('3031', '糖', '少许', '6');
INSERT INTO `material` VALUES ('3032', '醋', '几滴（最后放）', '6');
INSERT INTO `material` VALUES ('3033', '花椒面', '0.5-1g', '6');
INSERT INTO `material` VALUES ('3034', '麻油', '少许', '6');
INSERT INTO `material` VALUES ('3035', '花雕酒（米酒）', '少许', '6');
INSERT INTO `material` VALUES ('3036', '淀粉水', '勾芡用', '6');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `mid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜谱id',
  `mname` varchar(50) NOT NULL COMMENT '菜名',
  `mpic` varchar(150) DEFAULT NULL COMMENT '菜图',
  `mdesc` text COMMENT '菜描述',
  `userid` bigint(20) NOT NULL DEFAULT '1' COMMENT '用户id',
  `cid` bigint(20) NOT NULL DEFAULT '5' COMMENT '菜谱分类',
  `mdate` date DEFAULT '2017-11-12' COMMENT '创建菜谱时间',
  PRIMARY KEY (`mid`),
  KEY `cid` (`cid`),
  KEY `userid` (`userid`),
  CONSTRAINT `cid` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`),
  CONSTRAINT `userid` FOREIGN KEY (`userid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '西红柿厚蛋烧', 'imageView2/1/w/215/h/136/interlace/1/q/90', '╮(╯▽╰)╭ 今天把西红柿炒鸡蛋换成懒人版的西红柿厚蛋烧！厚蛋烧做成番茄炒蛋的亲请注意： \r\n①西红柿和鸡蛋的比例是1:2，两枚鸡蛋的话用一个小小西红柿就够了，西红柿多了鸡蛋饼粘不住不好成型！\r\n②西红柿切片再横切成丝竖切成丁，去不去皮无所谓，重要的是要细碎，切大块很容易变成番茄炒蛋啊亲！西红柿碎丁越小成功率越高哦～\r\n③蛋液量多锅子不够大的情况分次摊嘛，薄点可以摊匀实，太厚会失败滴=_=，虽然叫做厚蛋烧o(╯□╰)o。\r\n摊的时候注意要小火慢煎～', '1', '1', '2018-04-01');
INSERT INTO `menu` VALUES ('3', '可乐鸡翅', 'http://s1.cdn.xiachufang.com/ed35e338873811e6b87c0242ac110003_450w_600h.jpg@2o_50sh_1pr_1l_215w_136h_1c_1e_90q_1wh', ' 鸡翅（翅中最佳）、可乐、盐、料酒、老抽、生抽、生姜片（可有可无，有则更好）、桂皮（可有可无，有则更好）', '1', '1', '2017-11-04');
INSERT INTO `menu` VALUES ('4', '懒人版糖醋排骨', 'http://s2.cdn.xiachufang.com/4daad8ea877a11e6a9a10242ac110002_469w_701h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '国庆在家宴客最大小通吃的菜 \r\n话说，小辞在家宴客也有了一些经验，总有那么几道菜是不分年龄与各人口味，仍能大小通吃的，【糖醋排骨】绝对是当之无愧的最受欢迎菜式没有之一哦。 ', '1', '1', '2017-11-05');
INSERT INTO `menu` VALUES ('5', '油焖大虾', 'http://s2.cdn.xiachufang.com/b67d1692873611e6a9a10242ac110002_533w_800h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '对虾、姜、蒜、香葱、白糖、番茄酱（或西红柿）、生抽、料酒、盐\n        ', '1', '1', '2017-11-06');
INSERT INTO `menu` VALUES ('6', '麻婆豆腐', 'http://s1.cdn.xiachufang.com/1f54ebe686fa11e6a9a10242ac110002_690w_459h.jpg@2o_50sh_1pr_1l_215w_136h_1c_1e_90q_1wh', '经典传统川菜，川菜代表菜之一。\r\n本菜谱采用适合家庭烹煮方式制作，简单易学。想在家里做出餐馆里的卖相和味道吗?快快准备材料吧!', '1', '1', '2017-11-07');
INSERT INTO `menu` VALUES ('7', '红烧排骨', 'http://s2.cdn.xiachufang.com/9dc3e8a0873711e6b87c0242ac110003_680w_453h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '排骨、葱、姜、蒜、盐、生抽、老抽、料酒、白糖、鸡精\n        ', '1', '1', '2017-11-07');
INSERT INTO `menu` VALUES ('8', '糖醋里脊', 'http://s1.cdn.xiachufang.com/ca83159e872f11e6a9a10242ac110002_690w_458h.jpg@2o_50sh_1pr_1l_215w_136h_1c_1e_90q_1wh', '猪里脊、白糖、番茄酱、鸡蛋、姜、生粉、白胡椒粉、盐、白芝麻\n        ', '1', '1', '2017-11-08');
INSERT INTO `menu` VALUES ('9', '吃着碗里望着锅里的红烧肉', 'http://s2.cdn.xiachufang.com/e9206c9087de11e6b87c0242ac110003_800w_600h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '五花肉、八角、花椒、香叶、干辣椒、葱、姜、生抽、老抽、冰糖、盐、蜂蜜\n        ', '1', '1', '2017-11-08');
INSERT INTO `menu` VALUES ('10', '鱼香肉丝', 'http://img.studyhuang.cn/414ee162-8f68-4afb-8a0c-ac0ddf75504a.jpg', '猪腿肉、泡椒、姜、蒜、葱、水发木耳、胡萝卜、白糖、香醋(保宁醋/镇江香醋)、老抽、生抽、盐、清汤、淀粉', '1', '1', '2018-04-20');
INSERT INTO `menu` VALUES ('11', '番茄炒蛋', 'http://s1.cdn.xiachufang.com/101403a8873911e6a9a10242ac110002_600w_450h.jpg@2o_50sh_1pr_1l_215w_136h_1c_1e_90q_1wh', '番茄、鸡蛋、盐、糖\n        ', '1', '1', '2017-11-09');
INSERT INTO `menu` VALUES ('12', '蒸水蛋', 'http://s2.cdn.xiachufang.com/cf575a7a876211e6a9a10242ac110002_700w_525h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '鸡蛋、盐、酱油、油、温水、葱花\n        ', '1', '1', '2017-11-09');
INSERT INTO `menu` VALUES ('13', '酸辣土豆丝', 'http://s2.cdn.xiachufang.com/4455a6a086f611e6b87c0242ac110003_500w_750h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '大土豆、青辣椒、小红椒、大蒜、葱姜、油、花椒、醋、盐、辣椒油\n        ', '1', '1', '2017-11-09');
INSERT INTO `menu` VALUES ('14', '大盘鸡', 'http://s2.cdn.xiachufang.com/5ac84326873b11e6b87c0242ac110003_3872w_2592h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '鸡、土豆、洋葱、青椒、干红辣椒、花椒、八角、豆瓣酱、姜、蒜、酱油（老抽也行啊~、盐、料酒、啤酒、味精\n        ', '1', '1', '2017-11-09');
INSERT INTO `menu` VALUES ('15', '干锅菜花', 'http://s1.cdn.xiachufang.com/11c7c4de873711e6b87c0242ac110003_690w_461h.jpg@2o_50sh_1pr_1l_215w_136h_1c_1e_90q_1wh', '菜花、五花肉、姜、蒜、辣椒、盐、酱油\n        ', '1', '1', '2017-11-10');
INSERT INTO `menu` VALUES ('16', '蜜汁鸡翅', 'http://s2.cdn.xiachufang.com/f6bb3d5e885511e6b87c0242ac110003_638w_640h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '鸡翅、蒜、姜、料酒、生抽、蜂蜜\n        ', '1', '1', '2017-11-10');
INSERT INTO `menu` VALUES ('17', '盐煎鸡翅', 'http://s2.cdn.xiachufang.com/52b2cba8877b11e6a9a10242ac110002_3566w_2592h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '鸡中翅、盐、花生油、黑椒粉或孜然粉或辣椒粉（非必要）\n        ', '1', '1', '2017-11-10');
INSERT INTO `menu` VALUES ('18', '青椒素炒杏鲍菇', 'http://s2.cdn.xiachufang.com/e63580aa87bc11e6b87c0242ac110003_2592w_1936h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '青椒、杏鲍菇、大蒜瓣、生抽、老抽、盐、\n        ', '1', '1', '2017-11-11');
INSERT INTO `menu` VALUES ('19', '秘制红烧鸡爪', 'http://s2.cdn.xiachufang.com/bb44c5aa873411e6a9a10242ac110002_426w_640h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '鸡爪、花椒、八角、桂皮、香叶、冰糖10g、料酒、老抽、生抽、十三香、姜\n        ', '1', '1', '2017-11-11');
INSERT INTO `menu` VALUES ('20', '香煎培根金针菇卷', 'http://s2.cdn.xiachufang.com/142625de872a11e6a9a10242ac110002_600w_800h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '培根、金针菇、黑胡椒碎、熟白芝麻\n        ', '1', '1', '2017-11-11');
INSERT INTO `menu` VALUES ('21', '红烧排骨', 'http://s2.cdn.xiachufang.com/91fabc6886ee11e6b87c0242ac110003_441w_308h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '排骨、清水、冰糖、油、香葱、姜片、桂皮、八角、细盐、生抽、花雕酒\n        ', '1', '1', '2017-11-11');
INSERT INTO `menu` VALUES ('22', '香辣口水鸡', 'http://s1.cdn.xiachufang.com/d54269bc87ca11e6b87c0242ac110003_1000w_853h.jpg@2o_50sh_1pr_1l_215w_136h_1c_1e_90q_1wh', '#主料：、鸡腿、香葱、姜片、花椒、#调味料：、香葱、姜、蒜、香菜、小米椒、花椒油、辣椒油、芝麻香油、鲜酱油、香醋、料酒、白糖、盐、#装饰：、熟白芝麻、熟花生米、（调味料仅供参考）、\n        ', '1', '1', '2017-11-12');
INSERT INTO `menu` VALUES ('26', '比必胜客好吃太多的【海陆至尊披萨】', 'http://s1.cdn.xiachufang.com/97d6b2d488b211e6a9a10242ac110002_800w_693h.jpg@2o_50sh_1pr_1l_215w_136h_1c_1e_90q_1wh', '高筋面粉【面饼】、低筋面粉【面饼】、水【面饼】、橄榄油【面饼】、细砂糖【面饼】、耐高糖酵母【面饼】、盐【面饼】、番茄【红酱】、洋葱【红酱】、大蒜【红酱】、月桂叶【红酱】、黑胡椒粉【红酱】、盐【红酱】、糖【红酱】、肉糜【红酱】、熟虾仁、青椒丝、红椒丝、口蘑片、玉米粒、豌豆粒、肉片【种类按自己喜好】、马苏里拉芝士碎、番茄酱【红酱】\n        ', '1', '2', '2017-11-12');
INSERT INTO `menu` VALUES ('27', '比必胜客好吃太多的【海陆至尊披萨】', 'http://s1.cdn.xiachufang.com/97d6b2d488b211e6a9a10242ac110002_800w_693h.jpg@2o_50sh_1pr_1l_215w_136h_1c_1e_90q_1wh', '高筋面粉【面饼】、低筋面粉【面饼】、水【面饼】、橄榄油【面饼】、细砂糖【面饼】、耐高糖酵母【面饼】、盐【面饼】、番茄【红酱】、洋葱【红酱】、大蒜【红酱】、月桂叶【红酱】、黑胡椒粉【红酱】、盐【红酱】、糖【红酱】、肉糜【红酱】、熟虾仁、青椒丝、红椒丝、口蘑片、玉米粒、豌豆粒、肉片【种类按自己喜好】、马苏里拉芝士碎、番茄酱【红酱】\n        ', '1', '2', '2017-11-12');
INSERT INTO `menu` VALUES ('28', '蒜蓉蒸金针菇', 'http://s2.cdn.xiachufang.com/e9faf88ced9711e6bc9d0242ac110002_1440w_1080h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', ' 金针菇、蒜、色拉油、盐、鲜味生抽、麻油、葱花、糖\n        ', '1', '2', '2017-11-12');
INSERT INTO `menu` VALUES ('29', '鱼香肉丝', 'http://s2.cdn.xiachufang.com/1a974eba874911e6a9a10242ac110002_600w_450h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', ' 猪腿肉、泡椒、姜、蒜、葱、水发木耳、胡萝卜、白糖、香醋(保宁醋/镇江香醋)、老抽、生抽、盐、清汤、淀粉\n        ', '1', '2', '2017-11-12');
INSERT INTO `menu` VALUES ('30', '番茄炒蛋', 'http://s1.cdn.xiachufang.com/101403a8873911e6a9a10242ac110002_600w_450h.jpg@2o_50sh_1pr_1l_215w_136h_1c_1e_90q_1wh', '番茄、鸡蛋、盐、糖\n        ', '1', '2', '2017-11-12');
INSERT INTO `menu` VALUES ('31', '蒸水蛋', 'http://s2.cdn.xiachufang.com/cf575a7a876211e6a9a10242ac110002_700w_525h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', ' 鸡蛋、盐、酱油、油、温水、葱花\n        ', '1', '2', '2017-11-12');
INSERT INTO `menu` VALUES ('32', '家常蛋炒饭', 'http://s2.cdn.xiachufang.com/5a84d4ee8f9f11e6a9a10242ac110002_620w_413h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', ' 米饭（冷饭）、鸡蛋、午餐肉、青豆仁、胡萝卜粒、玉米粒、盐、料酒、鸡粉\n        ', '1', '2', '2017-11-12');
INSERT INTO `menu` VALUES ('33', '蜜汁鸡翅', 'http://s2.cdn.xiachufang.com/f6bb3d5e885511e6b87c0242ac110003_638w_640h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '鸡翅、蒜、姜、料酒、生抽、蜂蜜\n        ', '1', '2', '2017-11-12');
INSERT INTO `menu` VALUES ('34', '番茄鸡肉浓情焗饭【两人份】', 'http://s1.cdn.xiachufang.com/691355a488a511e6b87c0242ac110003_800w_533h.jpg@2o_50sh_1pr_1l_215w_136h_1c_1e_90q_1wh', '鸡胸肉、胡萝卜、土豆、番茄、青椒、红椒、黑胡椒粉、生粉、番茄酱、盐、糖、酱油、洋葱、马苏里拉芝士碎\n        ', '1', '2', '2017-11-12');
INSERT INTO `menu` VALUES ('35', '油焖大虾', 'http://s2.cdn.xiachufang.com/878625ba882911e6a9a10242ac110002_1200w_900h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '虾、葱、番茄酱、白糖、料酒、食盐、耗油、蒜末、植物油、香菜\n        ', '1', '2', '2017-11-12');
INSERT INTO `menu` VALUES ('36', '香煎培根金针菇卷', 'http://s2.cdn.xiachufang.com/142625de872a11e6a9a10242ac110002_600w_800h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '培根、金针菇、黑胡椒碎、熟白芝麻\n        ', '1', '2', '2017-11-12');
INSERT INTO `menu` VALUES ('37', '番茄龙利鱼', 'http://s2.cdn.xiachufang.com/e5913e2e89eb11e6a9a10242ac110002_800w_533h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '龙利鱼、番茄、番茄酱、小葱、橄榄油、盐、玉米淀粉、现磨黑胡椒、糖、姜丝\n        ', '1', '2', '2017-11-12');
INSERT INTO `menu` VALUES ('38', '韩式辣炒年糕（or网红芝士套路之芝心年糕）', 'http://s2.cdn.xiachufang.com/be4beae8881611e6a9a10242ac110002_500w_334h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '年糕条、包菜、胡萝卜、洋葱、韩国辣酱、韩国大酱、糖、韩国泡菜、芝麻\n        ', '1', '2', '2017-11-12');
INSERT INTO `menu` VALUES ('39', '入口即化的无添加蛋黄溶豆', 'http://s1.cdn.xiachufang.com/9ac04cc28f5511e6b87c0242ac110003_2080w_1560h.jpg@2o_50sh_1pr_1l_215w_136h_1c_1e_90q_1wh', '蛋黄、婴儿奶粉、柠檬汁（没有就不放）、糖粉（可不放，也可以用白砂糖替代）\n        ', '1', '2', '2017-11-12');
INSERT INTO `menu` VALUES ('40', '巧克力豆曲奇(Chocolate Chip Cookies)', 'http://s2.cdn.xiachufang.com/a968eda688ac11e6a9a10242ac110002_597w_800h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '黄油、白糖、红糖、香草精、鸡蛋(或淡奶油)、耐烤巧克力豆(纯可可脂70%以上的巧克力)、低筋面粉、法芙娜可可粉、小苏打、盐、装饰用耐烤巧克力豆\n        ', '1', '2', '2017-11-12');
INSERT INTO `menu` VALUES ('41', '西红柿鸡蛋疙瘩汤', 'http://s2.cdn.xiachufang.com/e80eb34e887311e6b87c0242ac110003_1728w_1152h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '\n            西红柿、鸡蛋、面粉、葱末、香菜末、蕃茄酱、糖、盐、香油\n        ', '1', '2', '2017-11-12');
INSERT INTO `menu` VALUES ('42', '凉拌手撕鸡丝（香辣版&麻油版）', 'http://s1.cdn.xiachufang.com/d64290a2882f11e6a9a10242ac110002_1600w_1066h.jpg@2o_50sh_1pr_1l_215w_136h_1c_1e_90q_1wh', '\n            ＃主料＃、鸡胸肉、黑木耳、胡萝卜、＃煮鸡胸肉料＃、葱白、姜、料酒、＃调味汁＃、蒜头、醋、鲜酱油或生抽、白糖、麻油、油泼辣子、＃点缀＃、白芝麻、香菜碎、小葱\n        ', '1', '2', '2017-11-12');
INSERT INTO `menu` VALUES ('43', '宫保鸡丁', 'http://img.studyhuang.cn/1da00a2c-18db-4a52-b60b-dfa04ad649e1.jpg', '鸡肉、黄瓜、青椒、花生、料酒、葱、姜、蒜、郫县豆瓣酱、干辣椒、花椒、醋、糖、盐、水淀粉', '1', '2', '2018-04-01');
INSERT INTO `menu` VALUES ('44', '葱爆面—就是汪涵在天天向上里面做的那个啦', 'http://s2.cdn.xiachufang.com/70150112886511e6a9a10242ac110002_800w_533h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '\n            挂面、小香葱（没有就用大葱凑合吧）、油、鲜酱油、白糖\n        ', '1', '2', '2017-11-12');
INSERT INTO `menu` VALUES ('45', '老丁的私房菜－蚂蚁上树', 'http://s2.cdn.xiachufang.com/69dcc0d8889111e6a9a10242ac110002_1763w_1672h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '\n            粉丝、肉末、四川郫县豆瓣酱、葱姜蒜，盐，香葱\n        ', '1', '2', '2017-11-12');
INSERT INTO `menu` VALUES ('46', '蒜蓉粉丝娃娃菜', 'http://s2.cdn.xiachufang.com/bc3658e8873411e6a9a10242ac110002_480w_678h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '\n            娃娃菜、粉丝、大蒜、剁椒、生抽、蒸鱼豉油\n        ', '1', '2', '2017-11-12');
INSERT INTO `menu` VALUES ('47', '麻婆豆腐', 'http://s1.cdn.xiachufang.com/1f54ebe686fa11e6a9a10242ac110002_690w_459h.jpg@2o_50sh_1pr_1l_215w_136h_1c_1e_90q_1wh', '\n            豆腐、郫县豆瓣、肉沫、花椒粒、辣椒粉、花椒末、胡椒末、水淀粉、小葱\n        ', '1', '3', '2017-11-12');
INSERT INTO `menu` VALUES ('48', '吃着碗里望着锅里的红烧肉', 'http://s2.cdn.xiachufang.com/e9206c9087de11e6b87c0242ac110003_800w_600h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '\n            五花肉、八角、花椒、香叶、干辣椒、葱、姜、生抽、老抽、冰糖、盐、蜂蜜\n        ', '1', '3', '2017-11-12');
INSERT INTO `menu` VALUES ('49', '肉沫豆角下饭菜', 'http://s1.cdn.xiachufang.com/746b5b3e87d011e6a9a10242ac110002_3420w_2592h.jpg@2o_50sh_1pr_1l_215w_136h_1c_1e_90q_1wh', '\n            豆角、猪肉碎、小米椒、大葱 大蒜、生抽、糖、老抽、淀粉、料酒、盐、植物油\n        ', '1', '3', '2017-11-12');
INSERT INTO `menu` VALUES ('50', '阿婆手撕包菜2', 'http://s2.cdn.xiachufang.com/287de89a873511e6a9a10242ac110002_450w_678h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '\n            圆白菜、干红椒、五花肉、大蒜、生姜、盐、蚝油、生抽、花椒油\n        ', '1', '3', '2017-11-12');
INSERT INTO `menu` VALUES ('51', '干锅菜花', 'http://s1.cdn.xiachufang.com/11c7c4de873711e6b87c0242ac110003_690w_461h.jpg@2o_50sh_1pr_1l_215w_136h_1c_1e_90q_1wh', '\n            菜花、五花肉、姜、蒜、辣椒、盐、酱油\n        ', '1', '3', '2017-11-12');
INSERT INTO `menu` VALUES ('52', '蜜汁鸡翅', 'http://s2.cdn.xiachufang.com/f6bb3d5e885511e6b87c0242ac110003_638w_640h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '\n            鸡翅、蒜、姜、料酒、生抽、蜂蜜\n        ', '1', '3', '2017-11-12');
INSERT INTO `menu` VALUES ('53', '啤酒鸭', 'http://s2.cdn.xiachufang.com/9ba6c0de873111e6a9a10242ac110002_400w_600h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '\n            鸭腿、啤酒、葱、姜、蒜、干辣椒、八角、盐、糖、老抽、生抽、食用油\n        ', '1', '3', '2017-11-12');
INSERT INTO `menu` VALUES ('54', '酸汤肥牛', 'http://s2.cdn.xiachufang.com/b7e3a470879411e6a9a10242ac110002_469w_701h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '\n            肥牛片、金针菇、青尖椒、红尖椒、海南黄灯笼辣椒酱、蒜、姜片、白醋、料酒、盐、糖、高汤\n        ', '1', '3', '2017-11-12');
INSERT INTO `menu` VALUES ('55', '鱼香肉丝2', 'http://s2.cdn.xiachufang.com/b89670e8873311e6a9a10242ac110002_690w_459h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '\n            里脊肉、青红辣椒、红萝卜、干木耳、郫县豆瓣酱、蒜瓣、姜蓉、泡椒、葱白、盐、糖、香醋、酱油、料酒、油、淀粉、清水\n        ', '1', '3', '2017-11-12');
INSERT INTO `menu` VALUES ('56', '菠萝咕噜肉', 'http://s2.cdn.xiachufang.com/e47fe6be872c11e6a9a10242ac110002_500w_658h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '\n            猪肉、菠萝、鸡蛋、小番茄、青椒、番茄酱、白醋、盐、生粉、糖、料酒\n        ', '1', '3', '2017-11-12');
INSERT INTO `menu` VALUES ('57', '茄子豆角', 'http://s1.cdn.xiachufang.com/e5c4d336873611e6a9a10242ac110002_690w_460h.jpg@2o_50sh_1pr_1l_215w_136h_1c_1e_90q_1wh', '\n            茄子、豆角、干辣椒、大蒜、蚝油、生抽、油\n        ', '1', '3', '2017-11-12');
INSERT INTO `menu` VALUES ('58', '超下饭的肉末茄子', 'http://s2.cdn.xiachufang.com/fa70642a88ae11e6b87c0242ac110003_4928w_3264h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '\n            中等身形的茄子、猪肉末、姜末、葱末、蒜末、生抽、料酒、白砂糖、盐、淀粉、豆瓣酱\n        ', '1', '3', '2017-11-12');
INSERT INTO `menu` VALUES ('59', '谁都可以做出好吃的红烧肉', 'http://s2.cdn.xiachufang.com/fa8ebd0688ce11e6b87c0242ac110003_800w_530h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '\n            #主料、五花肉、#配料、八角、香叶、桂皮、大葱、姜片、#调料、酱油、老抽、冰糖\n        ', '1', '3', '2017-11-12');
INSERT INTO `menu` VALUES ('60', '剁椒金针菇', 'http://s2.cdn.xiachufang.com/1d3d6a14873511e6b87c0242ac110003_600w_400h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '\n            金针菇、剁椒、蒸鱼豉油、小葱、食用油\n        ', '1', '3', '2017-11-12');
INSERT INTO `menu` VALUES ('61', '家常菜 蚝油生菜', 'http://s2.cdn.xiachufang.com/c5f6208c88ee11e6b87c0242ac110003_1000w_663h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '\n            生菜、蚝油、白糖、生粉、清水\n        ', '1', '3', '2017-11-12');
INSERT INTO `menu` VALUES ('62', '干锅手撕包菜', 'http://s2.cdn.xiachufang.com/0132a67a889011e6b87c0242ac110003_1600w_1061h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '\n            包菜（牛心菜）、五花肉、干辣椒、蒜、姜、盐、鸡精、生抽、食用油\n        ', '1', '3', '2017-11-12');
INSERT INTO `menu` VALUES ('63', '销魂烤茄子，百吃不厌！海氏烤箱定制', 'http://s1.cdn.xiachufang.com/ade7ac7a89e011e6b87c0242ac110003_640w_640h.jpg@2o_50sh_1pr_1l_215w_136h_1c_1e_90q_1wh', '\n            茄子、辣椒、蒜末、盐、油、酱油、孜然粉、葱花\n        ', '1', '3', '2017-11-12');
INSERT INTO `menu` VALUES ('64', '一个话唠包教包会的香浓红烩牛腩', 'http://s1.cdn.xiachufang.com/50abf3e8884d11e6a9a10242ac110002_1254w_1672h.jpg@2o_50sh_1pr_1l_215w_136h_1c_1e_90q_1wh', '\n            #主料、牛腩、黄皮洋葱、胡萝卜、土豆、西红柿、#调味料、干辣椒、葱、姜、蒜、香叶、冰糖、食用油、生抽、红烧酱油（或老抽）、番茄酱、盐、料酒（或红酒）\n        ', '1', '3', '2017-11-12');
INSERT INTO `menu` VALUES ('65', '老丁的私房菜－蚂蚁上树', 'http://s2.cdn.xiachufang.com/69dcc0d8889111e6a9a10242ac110002_1763w_1672h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '\n            粉丝、肉末、四川郫县豆瓣酱、葱姜蒜，盐，香葱\n        ', '1', '3', '2017-11-12');
INSERT INTO `menu` VALUES ('66', '蒜蓉粉丝娃娃菜', 'http://s2.cdn.xiachufang.com/bc3658e8873411e6a9a10242ac110002_480w_678h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '\n            娃娃菜、粉丝、大蒜、剁椒、生抽、蒸鱼豉油\n        ', '1', '3', '2017-11-12');
INSERT INTO `menu` VALUES ('67', '韩式辣炒年糕（or网红芝士套路之芝心年糕）', 'http://s2.cdn.xiachufang.com/be4beae8881611e6a9a10242ac110002_500w_334h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90', '\n            年糕条、包菜、胡萝卜、洋葱、韩国辣酱、韩国大酱、糖、韩国泡菜、芝麻\n        ', '1', '5', '2017-11-12');

-- ----------------------------
-- Table structure for step
-- ----------------------------
DROP TABLE IF EXISTS `step`;
CREATE TABLE `step` (
  `sid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '步骤主键',
  `step_id` int(11) DEFAULT NULL COMMENT '第几步',
  `spic` varchar(200) DEFAULT NULL COMMENT '步骤图片',
  `sdesc` text COMMENT '步骤描述',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '对应菜谱',
  PRIMARY KEY (`sid`),
  KEY `menu_id` (`menu_id`),
  CONSTRAINT `menu_id` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of step
-- ----------------------------
INSERT INTO `step` VALUES ('1', '1', null, '西红柿去皮，切丁，越细越好，打入2枚鸡蛋，加适量盐，把蛋液和西红柿混合搅匀', '1');
INSERT INTO `step` VALUES ('2', '2', null, '热锅少油小火，将蛋液均匀的铺一层在锅底', '1');
INSERT INTO `step` VALUES ('3', '3', null, '从一头将蛋皮卷起，切块装盘。可以根据个人喜好淋点番茄酱撒点黑胡椒神马的～', '1');
INSERT INTO `step` VALUES ('4', '1', null, '在生鸡翅上划刀，用一勺老抽，两勺料酒腌至少两小时 【时间紧可省略腌制，但要划刀】', '3');
INSERT INTO `step` VALUES ('7', '2', null, '锅中放水和葱姜片，鸡翅下锅煮开，捞出，用冷水冲洗掉浮沫，沥干', '3');
INSERT INTO `step` VALUES ('8', '3', 'http://s2.cdn.xiachufang.com/3dcf0288908111e6a9a10242ac110002_1619w_1080h.jpg?imageView2/2/w/300/interlace/1/q/90', '小火，锅中放一点底油，将鸡翅煎成金黄', '3');
INSERT INTO `step` VALUES ('9', '4', 'http://s2.cdn.xiachufang.com/3cb6acca908111e6b87c0242ac110003_1619w_1080h.jpg?imageView2/2/w/300/interlace/1/q/90', '一听可乐、一大勺料酒、一勺老抽，两勺生抽，加一点盐。加半支桂皮会更有风味。\r\n炖至汤汁还剩一碗水的量时，再尝尝咸淡，如需加盐再加少量盐，这样不容易出差错。', '3');
INSERT INTO `step` VALUES ('10', '5', 'http://s1.cdn.xiachufang.com/3b58421c908111e6a9a10242ac110002_1619w_1080h.jpg@2o_50sh_1pr_1l_300w_90q_1wh', '炖至汤汁收浓，浓度看图，基本所有汁都裹在鸡翅上了，只剩约三勺汤汁了，出锅', '3');
INSERT INTO `step` VALUES ('11', '1', 'http://s2.cdn.xiachufang.com/96cf5bfa8cba11e6a9a10242ac110002_469w_354h.jpg?imageView2/2/w/300/interlace/1/q/90', '猪小排冼净，晾干水份备用', '4');
INSERT INTO `step` VALUES ('12', '2', null, '锅内倒少量油，烧热之后，爆香姜片', '4');
INSERT INTO `step` VALUES ('13', '3', null, '放入排骨，一直煸炒到排骨变色后，表面金黄微焦', '4');
INSERT INTO `step` VALUES ('14', '4', null, '此时就可以放入黄金比例中的调料了，顺序是：先放一汤勺料酒，接着两汤勺酱油，三汤勺米醋最后四汤勺白糖，炒匀', '4');
INSERT INTO `step` VALUES ('15', '5', null, '再倒入能没过排骨的开水，调中小火焖20分钟', '4');
INSERT INTO `step` VALUES ('16', '6', null, '20分钟后调入盐，开大火收汁，收到汁浓色亮时，撒入芝麻点缀即可出锅（锅里剩下的姜片丢掉不用，最后大火收汁时注意多翻动锅内的排骨，避免烧焦哟！）', '4');
INSERT INTO `step` VALUES ('17', '1', 'http://s2.cdn.xiachufang.com/50f5ed8c8cb611e6a9a10242ac110002_527w_326h.jpg?imageView2/2/w/300/interlace/1/q/90', '新鲜对虾，剪去须、爪、嘴，以整齐美观，入口方便', '5');
INSERT INTO `step` VALUES ('18', '2', 'http://s2.cdn.xiachufang.com/5111bf308cb611e6b87c0242ac110003_503w_334h.jpg?imageView2/2/w/300/interlace/1/q/90', '从背部剪开，至尾端，挑去黑线。剪口不要正中，偏一点为宜，以免破坏黑线', '5');
INSERT INTO `step` VALUES ('19', '3', 'http://s1.cdn.xiachufang.com/513815908cb611e6b87c0242ac110003_534w_343h.jpg@2o_50sh_1pr_1l_300w_90q_1wh', '清洗干净，控净水分备用。姜蒜切细（约1:3），香葱切段', '5');
INSERT INTO `step` VALUES ('20', '4', 'http://s2.cdn.xiachufang.com/5165346c8cb611e6a9a10242ac110002_513w_326h.jpg?imageView2/2/w/300/interlace/1/q/90', '锅中多倒油，烧至约6、7成热，投入大虾，迅速翻面，变红起酥即捞出', '5');
INSERT INTO `step` VALUES ('21', '5', 'http://s1.cdn.xiachufang.com/51888ac08cb611e6a9a10242ac110002_532w_333h.jpg@2o_50sh_1pr_1l_300w_90q_1wh', '锅中留少许油，下姜蒜炒香，中火，下番茄酱（约3小匙），或去皮切碎的西红柿（只要约3小匙的量），炒出红油', '5');
INSERT INTO `step` VALUES ('22', '6', 'http://s2.cdn.xiachufang.com/51b2871c8cb611e6b87c0242ac110003_493w_332h.jpg?imageView2/2/w/300/interlace/1/q/90', '投入大虾，沿锅边烹入少许料酒、生抽（不能是深色酱油），洒入约3小匙白糖，以炒勺轻压几下虾脑，挤出红汁，大火炒匀，倒入约小半碗温水，大火烧开，小火盖闷', '5');
INSERT INTO `step` VALUES ('23', '7', 'http://s2.cdn.xiachufang.com/51dc608c8cb611e6b87c0242ac110003_517w_338h.jpg?imageView2/2/w/300/interlace/1/q/90', '约1、2分钟后，开盖，投入葱段，加盐调味，大火收汁，约20来秒，见汤汁收紧、油亮、颜色变深时，即可出锅', '5');
INSERT INTO `step` VALUES ('24', '1', null, '原料切配：\r\n豆腐切正方形块状，大小看个人喜好。\r\n青葱切葱花，蒜头切成末。肉碎不用腌制，但一定要完全化冻。正宗麻婆豆腐是放牛肉碎。家庭制作可以改为“猪肉碎”也没有问题。郫县豆瓣酱看个人喜好放，我的建议可放多一点点。可以提出问道，也可以让油炒到更红。豆瓣酱也要斩几刀，确保里面的豆瓣不会那么大颗粒影响味道发挥。', '6');
INSERT INTO `step` VALUES ('25', '2', null, '煮豆腐：\r\n为什么要煮豆腐？\r\n1.豆腐里水分含量高煮后会流失一些口感更好。\r\n2.水煮豆腐可以使豆腐更加Q弹好吃。\r\n3.水煮豆腐一定要放一些盐，稍微多一点，一块豆腐煮的时候可放2g左右盐一起煮，这样盐的渗透压会更快逼出豆腐里的水分，而且会让豆腐有一点点的咸味，记得这个程序一定要做，想做出好的麻婆豆腐这是关键步骤。\r\n', '6');
INSERT INTO `step` VALUES ('26', '3', null, '炒：\r\n另外取一口锅，放底油（可以多一点点）油温热后先炒肉碎。此时注意油温不要太高，慢慢炒，炒到肉碎开始微黄，有点发干时加入豆瓣酱炒出红油。出红油后下蒜末炒出香味就可以加水或汤，差不多一块豆腐一小碗汤，煮开汤后加入米酒或花雕酒一小勺，生抽一点点（只是提个味道，因郫县豆瓣已经很咸了，所以别放太多），糖一点点，煮开后试试味道，此时不能味道正合适，要稍微淡一点点，不然烧出来水分挥发后就会很咸。味道调好后加入煮好的豆腐，直接捞出来放进汤汁里，稍微烧一个2-3分钟，待汤汁变少后加入花椒面（自己掌握），我用的是自己炒的花椒碾碎的自制花椒面，所以很香很麻，放的不多提出味道就好了。（花椒面不要早放，会黑，会使整锅豆腐变黑掉）\r\n最后用水淀粉勾一个薄芡，再淋入麻油和几滴醋，就完成了炒的部分~（几滴醋也是关键哦，不放和放的会有差别哦，但几滴而已哦，最好不要吃出酸味，只是提味，这就是传说中的”锅边醋“）', '6');
INSERT INTO `step` VALUES ('27', '4', 'http://s1.cdn.xiachufang.com/5dd48ec8e9b311e6bc9d0242ac110002_2160w_1620h.jpg@2o_50sh_1pr_1l_300w_90q_1wh', '点缀：\r\n可点缀葱花，香菜，芝麻等等，随个人喜好。', '6');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT,
  `uname` varchar(20) DEFAULT NULL,
  `upassword` varchar(20) DEFAULT '123456',
  `uemail` varchar(30) DEFAULT NULL,
  `ubirthday` date DEFAULT NULL,
  `utel` varchar(20) DEFAULT NULL,
  `ustate` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '10001', '123456', '1135586231@qq.com', '2017-10-08', '12585248745', null);
INSERT INTO `user` VALUES ('9', '13850420360', 'whwhwh12', '155567@sina.com', '2018-02-27', '13850420360', null);
INSERT INTO `user` VALUES ('10', '1771147558', '123445', '13456@sina.com', '2018-02-27', '13961969033', null);
INSERT INTO `user` VALUES ('11', '147852369996', '122323', '122334@sina.com', '2018-02-27', '13960969233', null);
INSERT INTO `user` VALUES ('16', '13850420368', '123456', '22356665@sina.com', null, '111222222', '1');
INSERT INTO `user` VALUES ('18', '13850420360', '123456', '155567@sina.com', '2018-03-25', '13850420360', '1');
INSERT INTO `user` VALUES ('21', '15396196903', '123456', '12eee11114@sina.com', null, '14147147147', '1');
INSERT INTO `user` VALUES ('24', '13850420368', '123456', '22356665@sina.com', null, '12132344343', '1');
INSERT INTO `user` VALUES ('28', '17705902649', '1234567', '11233333@qq.com', '2018-04-08', '17705902649', '1');
INSERT INTO `user` VALUES ('29', '17705902649', '1234567', '11233333@qq.com', '2018-04-08', '17705902649', '1');
SET FOREIGN_KEY_CHECKS=1;
