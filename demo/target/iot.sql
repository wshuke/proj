-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: iot_web
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cmd_log`
--

DROP TABLE IF EXISTS `cmd_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cmd_log` (
  `cmd_log_id` int(11) NOT NULL AUTO_INCREMENT,
  `cmd_value` int(11) NOT NULL,
  `device_id` int(11) NOT NULL,
  `cmd_date` datetime NOT NULL,
  `is_cmd_exec` tinyint(1) NOT NULL,
  PRIMARY KEY (`cmd_log_id`),
  KEY `FK_Reference_9` (`device_id`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`device_id`) REFERENCES `devices` (`device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cmd_log`
--

LOCK TABLES `cmd_log` WRITE;
/*!40000 ALTER TABLE `cmd_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `cmd_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dev_states`
--

DROP TABLE IF EXISTS `dev_states`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `dev_states` (
  `device_state_id` int(11) NOT NULL AUTO_INCREMENT,
  `device_state_name` varchar(20) NOT NULL,
  PRIMARY KEY (`device_state_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dev_states`
--

LOCK TABLES `dev_states` WRITE;
/*!40000 ALTER TABLE `dev_states` DISABLE KEYS */;
INSERT INTO `dev_states` VALUES (1,'正常运行'),(2,'故障停止'),(3,'正常停止');
/*!40000 ALTER TABLE `dev_states` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dev_types`
--

DROP TABLE IF EXISTS `dev_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `dev_types` (
  `device_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `device_type_name` varchar(30) NOT NULL,
  `device_type_description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`device_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dev_types`
--

LOCK TABLES `dev_types` WRITE;
/*!40000 ALTER TABLE `dev_types` DISABLE KEYS */;
INSERT INTO `dev_types` VALUES (1,'三相变压器',NULL),(2,'光伏逆变器',NULL),(3,'开关设备',NULL);
/*!40000 ALTER TABLE `dev_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `devices`
--

DROP TABLE IF EXISTS `devices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `devices` (
  `device_id` int(11) NOT NULL AUTO_INCREMENT,
  `device_name` varchar(20) NOT NULL,
  `device_description` varchar(128) DEFAULT NULL,
  `device_type_id` int(11) NOT NULL,
  `device_state_id` int(11) NOT NULL,
  `urban_area_id` int(11) NOT NULL,
  `device_position` varchar(20) NOT NULL,
  `user_id` int(11) NOT NULL,
  `registration_date` datetime NOT NULL,
  PRIMARY KEY (`device_id`),
  KEY `FK_dev_area_r` (`urban_area_id`),
  KEY `FK_dev_dev_state_r` (`device_state_id`),
  KEY `FK_dev_dev_type_r` (`device_type_id`),
  KEY `FK_dev_user_r` (`user_id`),
  CONSTRAINT `FK_dev_area_r` FOREIGN KEY (`urban_area_id`) REFERENCES `urban_areas` (`urban_area_id`),
  CONSTRAINT `FK_dev_dev_state_r` FOREIGN KEY (`device_state_id`) REFERENCES `dev_states` (`device_state_id`),
  CONSTRAINT `FK_dev_dev_type_r` FOREIGN KEY (`device_type_id`) REFERENCES `dev_types` (`device_type_id`),
  CONSTRAINT `FK_dev_user_r` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `devices`
--

LOCK TABLES `devices` WRITE;
/*!40000 ALTER TABLE `devices` DISABLE KEYS */;
INSERT INTO `devices` VALUES (1,'NEPCC的变压器','[{\"name\":\"额定电压\",\"val\":380,\"unit\":\"V\"},{\"name\":\"额定电流\",\"val\":20,\"unit\":\"A\"}]',1,2,1,'NEPCC -1F 001',1,'2018-10-26 13:51:36'),(2,'旧的光伏逆变器','[{\"name\":\"额定电压\",\"val\":220,\"unit\":\"V\"},{\"name\":\"额定电流\",\"val\":10,\"unit\":\"A\"}]',2,3,3,'搬到了雨花区',1,'2018-10-26 14:56:15'),(3,'天心区的变压器','[{\"name\":\"额定电压\",\"val\":220,\"unit\":\"V\"},{\"name\":\"额定电流\",\"val\":20,\"unit\":\"A\"}]',1,3,2,'NEPCC 3F 301',2,'2018-10-26 14:58:37'),(4,'岳麓区的变压器','[{\"name\":\"额定电压\",\"val\":220,\"unit\":\"V\"},{\"name\":\"额定电流\",\"val\":20,\"unit\":\"A\"}]',1,3,1,'麓山南路1号',2,'2018-10-26 17:07:08'),(5,'新注册的断路器','[{\"name\":\"额定电压\",\"val\":400,\"unit\":\"V\"},{\"name\":\"额定电流\",\"val\":100,\"unit\":\"A\"}]',3,3,2,'天心区区政府',2,'2018-10-31 23:07:31');
/*!40000 ALTER TABLE `devices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fault_log`
--

DROP TABLE IF EXISTS `fault_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `fault_log` (
  `fault_log_id` int(11) NOT NULL AUTO_INCREMENT,
  `device_id` int(11) NOT NULL,
  `fault_type_id` int(11) NOT NULL,
  `fault_time` datetime NOT NULL,
  `fault_clear` tinyint(1) NOT NULL,
  `fault_clear_time` datetime DEFAULT NULL,
  PRIMARY KEY (`fault_log_id`),
  KEY `FK_fault_dev_r` (`device_id`),
  KEY `FK_fault_fault_type_r` (`fault_type_id`),
  CONSTRAINT `FK_fault_dev_r` FOREIGN KEY (`device_id`) REFERENCES `devices` (`device_id`),
  CONSTRAINT `FK_fault_fault_type_r` FOREIGN KEY (`fault_type_id`) REFERENCES `fault_types` (`fault_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fault_log`
--

LOCK TABLES `fault_log` WRITE;
/*!40000 ALTER TABLE `fault_log` DISABLE KEYS */;
INSERT INTO `fault_log` VALUES (1,1,1,'2018-10-26 15:00:09',1,'2018-10-26 17:00:51'),(2,1,2,'2018-10-26 17:35:12',1,'2018-10-26 18:01:44'),(3,3,1,'2018-10-26 17:55:13',1,'2018-10-26 19:53:46'),(4,1,2,'2018-10-30 11:38:38',0,NULL),(10,1,2,'2018-10-30 11:41:20',0,NULL);
/*!40000 ALTER TABLE `fault_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fault_types`
--

DROP TABLE IF EXISTS `fault_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `fault_types` (
  `fault_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `fault_name` varchar(30) NOT NULL,
  PRIMARY KEY (`fault_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fault_types`
--

LOCK TABLES `fault_types` WRITE;
/*!40000 ALTER TABLE `fault_types` DISABLE KEYS */;
INSERT INTO `fault_types` VALUES (1,'过压'),(2,'过流'),(3,'漏电');
/*!40000 ALTER TABLE `fault_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history_datas`
--

DROP TABLE IF EXISTS `history_datas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `history_datas` (
  `data_id` int(11) NOT NULL AUTO_INCREMENT,
  `data_time` datetime NOT NULL,
  `device_id` int(11) NOT NULL,
  `data_json` varchar(128) NOT NULL,
  PRIMARY KEY (`data_id`),
  KEY `FK_history_data_dev_r` (`device_id`),
  CONSTRAINT `FK_history_data_dev_r` FOREIGN KEY (`device_id`) REFERENCES `devices` (`device_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history_datas`
--

LOCK TABLES `history_datas` WRITE;
/*!40000 ALTER TABLE `history_datas` DISABLE KEYS */;
INSERT INTO `history_datas` VALUES (1,'2018-10-26 14:02:39',1,'[{\"name\":\"电压\",\"val\":220,\"unit\":\"V\"},{\"name\":\"电流\",\"val\":20,\"unit\":\"A\"}]'),(2,'2018-10-26 14:05:05',1,'[{\"name\":\"电压\",\"val\":220,\"unit\":\"V\"},{\"name\":\"电流\",\"val\":20,\"unit\":\"A\"}]'),(3,'2018-10-30 10:04:27',1,'[{\"val\":220,\"unit\":\"V\",\"name\":\"电压\"},{\"val\":20,\"unit\":\"A\",\"name\":\"电流\"}]'),(5,'2018-10-30 10:16:21',1,'[{\"val\":220,\"unit\":\"V\",\"name\":\"电压\"},{\"val\":20,\"unit\":\"A\",\"name\":\"电流\"}]'),(9,'2018-10-30 11:37:09',1,'[{\"val\":230,\"unit\":\"V\",\"name\":\"电压\"},{\"val\":16,\"unit\":\"A\",\"name\":\"电流\"}]'),(10,'2018-10-30 11:38:38',1,'[{\"val\":230,\"unit\":\"V\",\"name\":\"电压\"},{\"val\":16,\"unit\":\"A\",\"name\":\"电流\"}]');
/*!40000 ALTER TABLE `history_datas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `urban_areas`
--

DROP TABLE IF EXISTS `urban_areas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `urban_areas` (
  `urban_area_id` int(11) NOT NULL AUTO_INCREMENT,
  `urban_area_name` varchar(20) NOT NULL,
  PRIMARY KEY (`urban_area_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `urban_areas`
--

LOCK TABLES `urban_areas` WRITE;
/*!40000 ALTER TABLE `urban_areas` DISABLE KEYS */;
INSERT INTO `urban_areas` VALUES (1,'岳麓区'),(2,'天心区'),(3,'雨花区'),(4,'芙蓉区');
/*!40000 ALTER TABLE `urban_areas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_groups`
--

DROP TABLE IF EXISTS `user_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_groups` (
  `user_group_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_group_name` varchar(20) NOT NULL,
  `user_group_description` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`user_group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_groups`
--

LOCK TABLES `user_groups` WRITE;
/*!40000 ALTER TABLE `user_groups` DISABLE KEYS */;
INSERT INTO `user_groups` VALUES (1,'Super User','They manage everything.'),(2,'Manager',NULL),(3,'Visitor',NULL);
/*!40000 ALTER TABLE `user_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `count` varchar(20) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `user_description` varchar(40) DEFAULT NULL,
  `user_group_id` int(11) NOT NULL,
  `privilege_value` int(11) NOT NULL,
  `contact_information` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FK_user_user_group_r` (`user_group_id`),
  CONSTRAINT `FK_user_user_group_r` FOREIGN KEY (`user_group_id`) REFERENCES `user_groups` (`user_group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'super','Super User','123456','我是SuperUser',1,10,'8888888'),(2,'nepcc307','新NEPCC','123456','我修改过信息！',3,3,'123333333'),(3,'nepcc301','JJ','123456','nothing to say',3,3,'1334566454'),(4,'nepcc2018','J5','654321','oh!',3,3,'1335555'),(5,'nepcc300','JJ','12345678','',3,3,'123456');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-06 13:02:14
