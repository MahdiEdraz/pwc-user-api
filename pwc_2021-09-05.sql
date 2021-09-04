# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.13)
# Database: pwc
# Generation Time: 2021-09-04 21:48:44 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table department
# ------------------------------------------------------------

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `department_name` varchar(200) NOT NULL DEFAULT '',
  `employee_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `dep_to_user` (`employee_id`),
  CONSTRAINT `dep_to_user` FOREIGN KEY (`employee_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;

INSERT INTO `department` (`id`, `department_name`, `employee_id`)
VALUES
	(6,'IT',2),
	(9,'HR',2);

/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table department_lookup
# ------------------------------------------------------------

DROP TABLE IF EXISTS `department_lookup`;

CREATE TABLE `department_lookup` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `department` varchar(200) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `department_lookup` WRITE;
/*!40000 ALTER TABLE `department_lookup` DISABLE KEYS */;

INSERT INTO `department_lookup` (`id`, `department`)
VALUES
	(1,'IT'),
	(2,'HR'),
	(3,'Accounting');

/*!40000 ALTER TABLE `department_lookup` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table project
# ------------------------------------------------------------

DROP TABLE IF EXISTS `project`;

CREATE TABLE `project` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `project_name` varchar(200) NOT NULL DEFAULT '',
  `assigned_userId` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `project_to_user` (`assigned_userId`),
  CONSTRAINT `project_to_user` FOREIGN KEY (`assigned_userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;

INSERT INTO `project` (`id`, `project_name`, `assigned_userId`)
VALUES
	(5,'Dev',3);

/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table project_lookup
# ------------------------------------------------------------

DROP TABLE IF EXISTS `project_lookup`;

CREATE TABLE `project_lookup` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `project` varchar(200) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `project_lookup` WRITE;
/*!40000 ALTER TABLE `project_lookup` DISABLE KEYS */;

INSERT INTO `project_lookup` (`id`, `project`)
VALUES
	(1,'Payment'),
	(2,'Blockchain'),
	(3,'Hiring');

/*!40000 ALTER TABLE `project_lookup` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(200) NOT NULL DEFAULT '',
  `last_name` varchar(200) NOT NULL DEFAULT '',
  `password` varchar(500) NOT NULL DEFAULT '',
  `user_type` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `first_name`, `last_name`, `password`, `user_type`)
VALUES
	(2,'ahmad','rr','frfr','Manager'),
	(3,'mahdi','ss','123','manager'),
	(4,'cool','ss','123','manager'),
	(5,'cool','ss','123','manager'),
	(6,'dse','gtgtgt','gtgtgt','Manager');

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
