/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.49 : Database - uedu
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`uedu` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `uedu`;

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `cid` INT(11) NOT NULL AUTO_INCREMENT COMMENT '课程编号',
  `courseName` VARCHAR(64) DEFAULT NULL COMMENT '课程名称',
  `descs` VARCHAR(255) DEFAULT NULL COMMENT '课程简介',
  `courseType` INT(11) DEFAULT NULL COMMENT '课程类型',
  `courseImage` VARCHAR(255) DEFAULT NULL COMMENT '课程图片地址',
  `courseVideo` VARCHAR(255) DEFAULT NULL COMMENT '课程视频地址',
  `coursePrice` DECIMAL(10,2) DEFAULT NULL COMMENT '价格',
  `status` INT(11) DEFAULT NULL COMMENT '状态',
  `createTime` DATETIME DEFAULT NULL COMMENT '上传时间',
  PRIMARY KEY (`cid`)
) ENGINE=INNODB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='课程表';

/*Data for the table `course` */

INSERT  INTO `course`(`cid`,`courseName`,`descs`,`courseType`,`courseImage`,`courseVideo`,`coursePrice`,`status`,`createTime`) VALUES (3,'java基础','这个课程非常牛逼',1,'87baf5a9-064a-4d94-a327-a92113816e85java基础.jpg','914fa97f-62c3-45bf-a3f9-dc90be233c1djava课程.mp4','2999.00',1,'2021-03-08 20:47:56'),(4,'JavaWeb','这个课程比较重要',1,'d42fa81b-2bf0-4773-9fa6-59261e6c0f5fjavaWeb.jpg','006.mp4','1999.00',1,'2021-03-01 09:52:14'),(5,'html5课程','这个web课程必学',3,'b061a555-9cd9-47d2-809a-6cfb35c96a6dhtml5.jpg','aa8c06bc-e6e7-447b-a52f-a8aaf0e8d84fjava课程.mp4','299.00',1,'2021-03-08 22:38:03'),(6,'vue','作者是一位华人加油',3,'b72cad3f-2401-41ef-b4d9-beb0b77f894bvue.jpg','aa9e3b9a-775a-41ef-bf42-a95a2c9d0499java课程.mp4','899.00',1,'2021-03-01 10:03:07'),(7,'mysql课程','必会的数据库技术',2,'788eb39d-4aae-431b-a8f0-1a9b828cc2afmysql.jpg','abbe910d-7bf8-4d0c-8b4f-b9421936f899java课程.mp4','1299.00',1,'2021-03-08 22:12:07'),(8,'oracle课程 ','高并发必备',2,'201c985e-bbfa-43ad-a8d9-013bd528a4a2oracle.jpg','66ef1942-949d-4af5-ae76-ff9488075928java课程.mp4','599.00',1,'2021-03-01 10:10:28'),(9,'Spring课程','社会很单纯',1,'64a01cf6-0519-4f44-87a5-24d6f1c93bf7Spring课程.jpg','126622f9-9983-4cd1-8b31-fc5aefa674fadx.mp4','875.00',1,'2021-03-08 20:56:19'),(11,'SpringBoot课程','复杂的是人',1,'e727e4ea-7691-4e98-92e3-1f2b898f43adSpringBoot课程.jpg','478a5f37-2b75-4b8c-a062-175bf85f5e34dx.mp4','666.00',1,'2021-03-08 21:26:20'),(12,'SpringCloud课程','你要爱就来',1,'75c22510-6003-40f0-a6a7-2d4089649eb2SpringCloud课程.jpg','138cd330-6365-4fae-9f39-c761366b7bb8dx.mp4','488.00',1,'2021-03-08 21:42:11'),(13,'SpringMVC课程','不爱莫张狂',1,'c78f531d-b642-4f87-a954-ad9bb3d9cf27SpringMVC.jpg','c35ac64d-7f6a-4bfa-b859-1cc30af57c4ddx.mp4','699.00',1,'2021-03-08 21:42:58'),(14,'全栈开发课程','万丈高楼平地起',1,'a791b679-5c91-40fa-9e02-77d1b379cb0b项目实战课程.jpg','301a4eff-c95f-4e83-b4cc-e4580093e460dx.mp4','488.00',1,'2021-03-08 21:46:24'),(15,'SSM框架课程','修行只能靠自己',1,'553c9144-2886-45ac-9b7a-eeddbf6147e6SSM框架课程.jpg','9bf48328-5361-4729-814d-f386e64ed14adx.mp4','388.00',1,'2021-03-08 21:45:35'),(16,'sqlServer课程','零点制作',2,'e1f5350a-4d6b-4c47-ba14-6daba776ca31sqlServer.jpg','','1299.00',1,'2021-03-08 22:16:50'),(17,'数据库案例','案例演示我只服老杜',2,'f55049e6-e6ba-4d4b-b85f-a855a12906e3数据库案例.jpg','','1899.00',1,'2021-03-08 22:17:28'),(18,'数据库实践教程','抬头望望天',2,'fe947ade-bfdb-4a66-85c2-519db1451539数据库实践教程.jpg','','1566.00',1,'2021-03-08 22:18:13'),(19,'数据库原理','月亮在笑',2,'27a319b1-da41-4dd6-84c8-5ef88b40df83数据库原理.jpg','','2999.00',1,'2021-03-08 22:18:46'),(21,'数据库项目实战','让你从0-1',2,'272b3f71-0877-4418-8eb8-c8f2c238ca86数据库项目实战.jpg','','3999.00',1,'2021-03-08 22:19:41'),(22,'sqlServer实用教程','很多开发小技巧',2,'e27d1a7d-f8e5-4d45-9a57-af9066358f2esqlServer实用教程.jpg','009.mp4','4999.00',1,'2021-03-08 22:20:38'),(23,'jQuery课程','有担当',3,'37ff28c8-3411-4c16-91af-5a01f7a8d08bjQuery课程.jpg','','388.00',1,'2021-03-08 22:38:43'),(24,'D3课程','责任在身',3,'c637b75e-2e07-426d-9ced-0e5ec628038aD3.jpg','','499.00',1,'2021-03-08 22:39:10'),(25,'node课程','坚持奋斗',3,'d66e1aad-377b-44c2-8ac6-6c625a5e8f99node课程.jpg','','599.00',1,'2021-03-08 22:39:39'),(26,'js课程','本阶段重点',3,'2e40e20b-ad47-489c-bc96-e9d5cda04acdjs课程.jpg','','699.00',1,'2021-03-08 22:40:07'),(27,'bootStrap课程','一套页面多个终端',3,'89c5917a-880f-4edc-ba8b-eedb234820c7bootStrap课程.jpg','','799.00',1,'2021-03-08 22:40:41'),(28,'css课程','布局排版样式选择',3,'36a21371-9ee5-4ac5-b334-ebb90b2991ebcss课程.jpg','','999.00',1,'2021-03-08 22:41:12');

/*Table structure for table `course_user` */

DROP TABLE IF EXISTS `course_user`;

CREATE TABLE `course_user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `cid` INT(11) DEFAULT NULL,
  `uid` INT(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `course_user` */

INSERT  INTO `course_user`(`id`,`cid`,`uid`) VALUES (10,3,29);

/*Table structure for table `coursedetail` */

DROP TABLE IF EXISTS `coursedetail`;

CREATE TABLE `coursedetail` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) DEFAULT NULL,
  `type` VARCHAR(11) DEFAULT NULL,
  `url` VARCHAR(500) DEFAULT NULL,
  `start_data` VARCHAR(50) DEFAULT NULL,
  `cid` INT(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `coursedetail` */

INSERT  INTO `coursedetail`(`id`,`name`,`type`,`url`,`start_data`,`cid`) VALUES (1,'java由来','第一章','001.mp4','2020.12.10 20：00',3),(2,'基于语法','第一章','002.mp4','2020.12.11 20：00',3),(3,'循环数组','第一章','004.mp4','2020.12.12 20：00',3),(4,'封装','第二章','005.mp4','2020.12.13 20：00',3),(5,'继承','第二章','006.mp4','2020.12.14 20：00',3),(6,'多态','第二章','007.mp4','2020.12.15 20：00',3),(8,'servlet详解','第一章','008.mp4','2020.12.16 20：00',4),(9,'请求request','第一章','009.mp4','2020.12.17 20：00',4),(10,'响应response','第一章','010.mp4','2020.12.18 20：00',4),(14,'jsp入门','第二章','011.mp4','2020.12.19 20：00',4),(15,'jsp原理','第二章','012.mp4','2020.12.20 20：00',4),(16,'jsp动作标签','第二章','013.mp4','2020.12.21 20：00',4),(17,'sqlServer简介','第一章','014.mp4','2020.12.22 20：00',22),(18,'sqlServer基础语法','第一章','015.mp4','2020.12.23 20：00',22),(19,'sqlServer数据类型','第二章','016.mp4','2020.12.24 20：00',22),(20,'sqlServer约束','第二章','017.mp4','2020.12.25 20：00',22),(21,'sqlServer表关系','第二章','018.mp4','2020.12.26 20：00',22),(22,'sqlServer连接查询','第三章','019.mp4','2020.12.27 20：00',22),(23,'sqlServer综合练习','第三章','020.mp4','2020.12.28 20：00',22);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `uid` INT(11) NOT NULL AUTO_INCREMENT COMMENT '学员编号',
  `name` VARCHAR(32) DEFAULT NULL COMMENT '真实姓名',
  `phone` VARCHAR(20) DEFAULT NULL COMMENT '手机号',
  `age` INT(11) DEFAULT NULL COMMENT '年龄',
  `sex` INT(11) DEFAULT NULL COMMENT '性别',#0男 1女
  `username` VARCHAR(32) DEFAULT NULL COMMENT '账号',
  `password` VARCHAR(64) DEFAULT NULL COMMENT '密码',
  `status` INT(11) DEFAULT NULL COMMENT '状态',#1启用 2禁用
  `createtime` DATETIME DEFAULT NULL COMMENT '注册时间',
  `role` INT(11) DEFAULT NULL COMMENT '角色',#1管理员 #2一般用户 #3VIP用户
  `picture` VARCHAR(255) DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`uid`)
) ENGINE=INNODB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `user` */

INSERT  INTO `user`(`uid`,`name`,`phone`,`age`,`sex`,`username`,`password`,`status`,`createtime`,`role`,`picture`) VALUES (1,'张三','18638938271',32,1,'root','123456',1,'2020-12-04 16:30:23',1,NULL),(2,'李44','13721432300',30,1,'admin1','123456',1,'2020-12-21 18:14:23',2,NULL),(4,'王二麻子','13838384384',19,0,'bbb','123321',2,'2021-03-03 16:07:49',3,'null'),(5,'哈哈','199',20,0,'admin1','1234',1,'2020-12-22 09:35:04',3,NULL),(6,'张6','13721432300',20,0,'admin2','123456',1,'2021-02-09 00:23:40',3,NULL),(10,'pgone','13721432300',18,1,'pgone123','123456',1,'2021-02-09 00:23:56',3,NULL),(13,'孙悟空','13721432300',0,0,'sunwukong','123456',1,'2020-10-18 00:00:00',2,NULL),(14,'杜易墨','18839788165',99,1,'dym','123456',1,'2021-02-09 00:22:22',2,NULL),(15,'dasd','13223414524',18,0,'ddd','123456',1,'2021-02-20 10:15:12',3,NULL),(27,'老袁','13721432301',0,0,NULL,'123456',1,'2021-03-08 14:24:29',3,NULL),(28,'老刘','13721432033',0,0,NULL,'123456',1,'2021-03-09 16:35:21',3,NULL),(29,'师春青','13535354356',0,0,NULL,'123456',1,'2021-03-12 12:34:08',3,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
