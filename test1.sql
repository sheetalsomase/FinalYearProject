# --------------------------------------------------------
# Host:                         127.0.0.1
# Database:                     test1
# Server version:               5.1.73-community
# Server OS:                    Win64
# HeidiSQL version:             5.0.0.3272
# Date/time:                    2019-01-22 21:44:58
# --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
# Dumping database structure for test1
CREATE DATABASE IF NOT EXISTS `test1` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `test1`;


# Dumping structure for table test1.user_details
CREATE TABLE IF NOT EXISTS `user_details` (
  `username` varchar(100) DEFAULT NULL,
  `lastname` varchar(100) DEFAULT NULL,
  `user_email` varchar(100) DEFAULT NULL,
  `user_mobile` varchar(200) DEFAULT NULL,
  `uname` varchar(45) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table test1.user_details: 1 rows
/*!40000 ALTER TABLE `user_details` DISABLE KEYS */;
INSERT INTO `user_details` (`username`, `lastname`, `user_email`, `user_mobile`, `uname`, `password`) VALUES ('Javed', 'Tamboli', 'java@gmail.com', '8380067850', 'Javed', 'Java@123');
/*!40000 ALTER TABLE `user_details` ENABLE KEYS */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
