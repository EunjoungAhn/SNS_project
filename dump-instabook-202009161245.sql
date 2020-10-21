-- MySQL dump 10.13  Distrib 5.5.62, for Win64 (AMD64)
--
-- Host: localhost    Database: instabook
-- ------------------------------------------------------
-- Server version	5.5.62

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
-- Table structure for table `post_insta`
--

DROP TABLE IF EXISTS `post_insta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post_insta` (
  `id_post` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` varchar(50) NOT NULL,
  `date_post` varchar(8) NOT NULL,
  `hash_post` varchar(30) NOT NULL,
  `img_post` varchar(50) NOT NULL,
  `like_post` int(5) NOT NULL,
  PRIMARY KEY (`id_post`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `post_insta_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user_insta` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_insta`
--

LOCK TABLES `post_insta` WRITE;
/*!40000 ALTER TABLE `post_insta` DISABLE KEYS */;
INSERT INTO `post_insta` VALUES (1,'a','20.09.15','a','D:\\yangkj\\workspace\\Instabook\\src\\images\\1.jpg',0),(2,'a','20.09.16','a','D:\\yangkj\\workspace\\Instabook\\src\\images\\2.jpg',1),(3,'b','20.09.17','b','D:\\yangkj\\workspace\\Instabook\\src\\images\\3.jpg',2),(4,'b','20.09.18','b','D:\\yangkj\\workspace\\Instabook\\src\\images\\4.jpg',3),(5,'c','20.09.19','c','D:\\yangkj\\workspace\\Instabook\\src\\images\\5.jpg',4),(6,'c','20.09.20','c','D:\\yangkj\\workspace\\Instabook\\src\\images\\6.jpg',5),(7,'d','20.09.21','d','D:\\yangkj\\workspace\\Instabook\\src\\images\\7.jpg',6),(8,'d','20.09.22','d','D:\\yangkj\\workspace\\Instabook\\src\\images\\8.jpg',7),(9,'e','20.09.23','e','D:\\yangkj\\workspace\\Instabook\\src\\images\\9.jpg',8),(10,'e','20.09.24','e','D:\\yangkj\\workspace\\Instabook\\src\\images\\10.jpg',9),(11,'f','20.09.25','f','D:\\yangkj\\workspace\\Instabook\\src\\images\\11.jpg',10),(12,'f','20.09.26','f','D:\\yangkj\\workspace\\Instabook\\src\\images\\12.jpg',11),(13,'g','20.09.27','g','D:\\yangkj\\workspace\\Instabook\\src\\images\\13.jpg',12),(14,'g','20.09.28','g','D:\\yangkj\\workspace\\Instabook\\src\\images\\14.jpg',13),(15,'h','20.09.29','h','D:\\yangkj\\workspace\\Instabook\\src\\images\\15.jpg',14),(16,'h','20.09.30','h','D:\\yangkj\\workspace\\Instabook\\src\\images\\16.jpg',15),(17,'i','20.09.15','i','D:\\yangkj\\workspace\\Instabook\\src\\images\\17.jpg',16),(18,'i','20.09.16','i','D:\\yangkj\\workspace\\Instabook\\src\\images\\18.jpg',17),(19,'j','20.09.17','j','D:\\yangkj\\workspace\\Instabook\\src\\images\\19.jpg',18),(20,'j','20.09.18','j','D:\\yangkj\\workspace\\Instabook\\src\\images\\20.jpg',19),(21,'k','20.09.19','k','D:\\yangkj\\workspace\\Instabook\\src\\images\\21.jpg',20),(22,'k','20.09.20','k','D:\\yangkj\\workspace\\Instabook\\src\\images\\22.jpg',21),(23,'l','20.09.21','l','D:\\yangkj\\workspace\\Instabook\\src\\images\\23.jpg',22),(24,'l','20.09.22','l','D:\\yangkj\\workspace\\Instabook\\src\\images\\24.jpg',23),(25,'m','20.09.23','n','D:\\yangkj\\workspace\\Instabook\\src\\images\\1.jpg',26),(26,'m','20.09.24','n','D:\\yangkj\\workspace\\Instabook\\src\\images\\2.jpg',27),(27,'n','20.09.25','o','D:\\yangkj\\workspace\\Instabook\\src\\images\\3.jpg',28),(28,'n','20.09.26','o','D:\\yangkj\\workspace\\Instabook\\src\\images\\4.jpg',29),(29,'o','20.09.27','p','D:\\yangkj\\workspace\\Instabook\\src\\images\\5.jpg',30),(30,'o','20.09.28','p','D:\\yangkj\\workspace\\Instabook\\src\\images\\6.jpg',31),(31,'p','20.09.29','q','D:\\yangkj\\workspace\\Instabook\\src\\images\\7.jpg',32),(32,'p','20.09.30','q','D:\\yangkj\\workspace\\Instabook\\src\\images\\8.jpg',33),(33,'q','20.09.15','r','D:\\yangkj\\workspace\\Instabook\\src\\images\\9.jpg',34),(34,'q','20.09.16','r','D:\\yangkj\\workspace\\Instabook\\src\\images\\10.jpg',35),(35,'r','20.09.17','s','D:\\yangkj\\workspace\\Instabook\\src\\images\\11.jpg',36),(36,'r','20.09.18','s','D:\\yangkj\\workspace\\Instabook\\src\\images\\12.jpg',37),(37,'s','20.09.19','t','D:\\yangkj\\workspace\\Instabook\\src\\images\\13.jpg',38),(38,'s','20.09.20','t','D:\\yangkj\\workspace\\Instabook\\src\\images\\14.jpg',39),(39,'t','20.09.21','u','D:\\yangkj\\workspace\\Instabook\\src\\images\\15.jpg',40),(40,'t','20.09.22','u','D:\\yangkj\\workspace\\Instabook\\src\\images\\16.jpg',41),(41,'u','20.09.23','v','D:\\yangkj\\workspace\\Instabook\\src\\images\\17.jpg',42),(42,'u','20.09.24','v','D:\\yangkj\\workspace\\Instabook\\src\\images\\18.jpg',43),(43,'v','20.09.25','w','D:\\yangkj\\workspace\\Instabook\\src\\images\\19.jpg',44),(44,'v','20.09.26','w','D:\\yangkj\\workspace\\Instabook\\src\\images\\20.jpg',45),(45,'w','20.09.27','x','D:\\yangkj\\workspace\\Instabook\\src\\images\\21.jpg',46),(46,'w','20.09.28','x','D:\\yangkj\\workspace\\Instabook\\src\\images\\22.jpg',47),(48,'admin','20.09.30','admin','D:\\yangkj\\workspace\\Instabook\\src\\images\\2.jpg',49),(49,'admin','20.09.15','admin','D:\\yangkj\\workspace\\Instabook\\src\\images\\3.jpg',50),(50,'admin','20.09.16','admin','D:\\yangkj\\workspace\\Instabook\\src\\images\\4.jpg',51),(51,'admin','20.09.17','admin','D:\\yangkj\\workspace\\Instabook\\src\\images\\5.jpg',52),(52,'admin','20.09.18','admin','D:\\yangkj\\workspace\\Instabook\\src\\images\\6.jpg',53),(53,'admin','20.09.19','admin','D:\\yangkj\\workspace\\Instabook\\src\\images\\7.jpg',54),(54,'admin','20.09.20','admin','D:\\yangkj\\workspace\\Instabook\\src\\images\\8.jpg',55),(55,'admin','20.09.21','admin','D:\\yangkj\\workspace\\Instabook\\src\\images\\9.jpg',56),(56,'admin','20.09.22','admin','D:\\yangkj\\workspace\\Instabook\\src\\images\\10.jpg',57),(57,'admin','20.09.16','admin','D:\\yangkj\\workspace\\Instabook\\src\\images\\11.jpg',58),(58,'admin','20.09.24','admin','D:\\yangkj\\workspace\\Instabook\\src\\images\\12.jpg',59),(59,'admin','20.09.25','admin','D:\\yangkj\\workspace\\Instabook\\src\\images\\13.jpg',60),(60,'admin','20.09.26','admin','D:\\yangkj\\workspace\\Instabook\\src\\images\\14.jpg',61),(61,'admin','20.09.27','admin','D:\\yangkj\\workspace\\Instabook\\src\\images\\15.jpg',62),(66,'new123','20.09.16','newpost1','D:\\yangkj\\workspace\\Instabook\\src\\images\\1.jpg',0);
/*!40000 ALTER TABLE `post_insta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_insta`
--

DROP TABLE IF EXISTS `user_insta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_insta` (
  `id_user` varchar(50) NOT NULL,
  `pw_user` varchar(30) NOT NULL,
  `nickname_user` varchar(30) NOT NULL,
  `img_user` varchar(100) DEFAULT NULL,
  `gender_user` char(1) DEFAULT NULL,
  `birth_user` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_insta`
--

LOCK TABLES `user_insta` WRITE;
/*!40000 ALTER TABLE `user_insta` DISABLE KEYS */;
INSERT INTO `user_insta` VALUES ('a','123','aaa','D:\\yangkj\\workspace\\Instabook\\src\\images\\1.jpg','f','123'),('admin','1234','adminNewName','D:\\yangkj\\workspace\\Instabook\\src\\images\\24.jpg','f','2020.09.15'),('b','123','bbb','D:\\yangkj\\workspace\\Instabook\\src\\images\\2.jpg','f','1234'),('c','123','ccc','D:\\yangkj\\workspace\\Instabook\\src\\images\\3.jpg','m','2345'),('d','123','ddd','D:\\yangkj\\workspace\\Instabook\\src\\images\\4.jpg','f','3456'),('e','123','eee','D:\\yangkj\\workspace\\Instabook\\src\\images\\5.jpg','m','4567'),('f','123','fff','D:\\yangkj\\workspace\\Instabook\\src\\images\\6.jpg','f','5678'),('g','123','ggg','D:\\yangkj\\workspace\\Instabook\\src\\images\\7.jpg','m','6789'),('h','123','hhh','D:\\yangkj\\workspace\\Instabook\\src\\images\\8.jpg','f','7900'),('i','123','iii','D:\\yangkj\\workspace\\Instabook\\src\\images\\9.jpg','m','9011'),('j','123','jjj','D:\\yangkj\\workspace\\Instabook\\src\\images\\10.jpg','f','10122'),('k','123','kkk','D:\\yangkj\\workspace\\Instabook\\src\\images\\11.jpg','m','11233'),('l','123','lll','D:\\yangkj\\workspace\\Instabook\\src\\images\\12.jpg','f','12344'),('m','123','mmm','D:\\yangkj\\workspace\\Instabook\\src\\images\\13.jpg','m','13455'),('n','123','nnn','D:\\yangkj\\workspace\\Instabook\\src\\images\\14.jpg','f','14566'),('new123','123','new123',NULL,'f','123456'),('o','123','ooo','D:\\yangkj\\workspace\\Instabook\\src\\images\\15.jpg','m','15677'),('p','123','ppp','D:\\yangkj\\workspace\\Instabook\\src\\images\\16.jpg','f','16788'),('q','123','qqq','D:\\yangkj\\workspace\\Instabook\\src\\images\\17.jpg','m','17899'),('r','123','rrr','D:\\yangkj\\workspace\\Instabook\\src\\images\\18.jpg','f','19010'),('s','123','sss','D:\\yangkj\\workspace\\Instabook\\src\\images\\19.jpg','m','20121'),('t','123','ttt','D:\\yangkj\\workspace\\Instabook\\src\\images\\20.jpg','f','21232'),('u','123','uuu','D:\\yangkj\\workspace\\Instabook\\src\\images\\21.jpg','m','22343'),('v','123','vvv','D:\\yangkj\\workspace\\Instabook\\src\\images\\22.jpg','f','23454'),('w','123','www','D:\\yangkj\\workspace\\Instabook\\src\\images\\23.jpg','m','24565');
/*!40000 ALTER TABLE `user_insta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'instabook'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-16 12:45:48
