CREATE DATABASE  IF NOT EXISTS `thinkmall` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `thinkmall`;
-- MySQL dump 10.13  Distrib 5.7.21, for Linux (x86_64)
--
-- Host: localhost    Database: thinkmall
-- ------------------------------------------------------
-- Server version	5.7.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `administrator_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '管理员编号',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` char(32) NOT NULL COMMENT '密码 = md5(真实密码 + 盐)',
  `salt` char(32) NOT NULL COMMENT '盐',
  `gmt_create` datetime(3) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(3) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`administrator_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `category_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '类别编号',
  `name` varchar(20) NOT NULL COMMENT '类别名称',
  `description` varchar(255) DEFAULT NULL COMMENT '类别描述',
  `parent_id` bigint(20) unsigned NOT NULL COMMENT '父类别编号（0代表是根类别）',
  `level` tinyint(2) unsigned NOT NULL COMMENT ' 类别层次（只能为1或2或3）',
  `is_deleted` tinyint(1) unsigned NOT NULL COMMENT '是否删除（0：正常，1：删除）',
  `gmt_create` datetime(3) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(3) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='类别';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `order_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户编号',
  `shipping_address_id` bigint(20) unsigned NOT NULL COMMENT '收货地址编号',
  `user_message` varchar(255) DEFAULT NULL COMMENT '用户留言',
  `status` tinyint(2) unsigned NOT NULL COMMENT ' 订单状态(1:已生成；2：已发货；3:交易成功；4：交易关闭)',
  `gmt_create` datetime(3) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(3) NOT NULL COMMENT '更新时间',
  `gmt_consign` datetime(3) DEFAULT NULL COMMENT '发货时间',
  `gmt_success` datetime(3) DEFAULT NULL COMMENT '交易成功时间',
  `gmt_close` datetime(3) DEFAULT NULL COMMENT '交易关闭时间',
  PRIMARY KEY (`order_id`),
  KEY `fk_user_id_idx` (`user_id`),
  KEY `fk_shipping_address_id_idx` (`shipping_address_id`),
  CONSTRAINT `fk_order_shipping_address_id` FOREIGN KEY (`shipping_address_id`) REFERENCES `shipping_address` (`shipping_address_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_item` (
  `order_item_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '订单项编号',
  `order_id` bigint(20) unsigned NOT NULL COMMENT '订单编号',
  `product_id` bigint(20) unsigned NOT NULL COMMENT '商品编号',
  `count` int(10) unsigned NOT NULL COMMENT '购买数量',
  `gmt_create` datetime(3) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(3) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`order_item_id`),
  KEY `fk_order_item_order_id_idx` (`order_id`),
  KEY `fk_order_item_product_id_idx` (`product_id`),
  CONSTRAINT `fk_order_item_order_id` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_item_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单项';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `product_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '商品编号',
  `category_id` bigint(20) unsigned NOT NULL COMMENT '类别编号',
  `name` varchar(20) NOT NULL COMMENT '商品名称',
  `description` varchar(255) DEFAULT NULL COMMENT '商品描述',
  `price` decimal(12,2) NOT NULL COMMENT '商品单价（单位：元）',
  `count` int(10) unsigned NOT NULL COMMENT '商品数量',
  `image_path` varchar(255) DEFAULT NULL COMMENT '图片路径',
  `status` tinyint(2) unsigned NOT NULL COMMENT '商品状态(1:正常；2:下架；3：删除)',
  `gmt_create` datetime(3) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(3) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`product_id`),
  KEY `fk_category_id_idx` (`category_id`),
  CONSTRAINT `fk_product_category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipping_address`
--

DROP TABLE IF EXISTS `shipping_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shipping_address` (
  `shipping_address_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '收货地址编号',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户编号',
  `receiver_name` varchar(20) NOT NULL COMMENT '收货人姓名',
  `phone_number` char(11) NOT NULL COMMENT '电话号码',
  `detail` varchar(45) NOT NULL COMMENT '详细地址',
  `is_acquiescent` tinyint(1) unsigned NOT NULL COMMENT '是否是默认值(0表示否，1表示是)',
  `is_deleted` tinyint(1) unsigned NOT NULL COMMENT '是否删除（0：正常，1：删除）',
  `gmt_create` datetime(3) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(3) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`shipping_address_id`),
  KEY `fk_user_id_idx` (`user_id`),
  CONSTRAINT `fk_shipping_address_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='收货地址';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipping_address`
--

LOCK TABLES `shipping_address` WRITE;
/*!40000 ALTER TABLE `shipping_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `shipping_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` char(32) NOT NULL COMMENT '密码 = md5(真实密码 + 盐)',
  `salt` char(32) NOT NULL COMMENT '盐',
  `email` varchar(50) NOT NULL COMMENT '邮箱',
  `phone_number` char(11) NOT NULL COMMENT '电话号码',
  `sex` tinyint(2) unsigned NOT NULL COMMENT '性别(0:女，1:男，2:不愿透露)',
  `is_locked` tinyint(1) unsigned NOT NULL COMMENT '是否锁定(1表示是,0表示否)',
  `gmt_create` datetime(3) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(3) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-19 20:39:02