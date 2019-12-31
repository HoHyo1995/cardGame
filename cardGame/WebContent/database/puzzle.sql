-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.4.8-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- puzzle 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `puzzle` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `puzzle`;

-- 테이블 puzzle.member 구조 내보내기
CREATE TABLE IF NOT EXISTS `member` (
  `member_id` varchar(500) NOT NULL,
  `member_pw` varchar(500) NOT NULL,
  `member_level` enum('Y','N') NOT NULL DEFAULT 'N',
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 puzzle.member:~3 rows (대략적) 내보내기
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` (`member_id`, `member_pw`, `member_level`) VALUES
	('1', '1', 'Y'),
	('kim', 'q1', 'Y'),
	('park', 'dqwd1', 'Y'),
	('shin', 'q2', 'Y');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;

-- 테이블 puzzle.puzzle_report 구조 내보내기
CREATE TABLE IF NOT EXISTS `puzzle_report` (
  `report_id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` varchar(500) DEFAULT NULL,
  `report_date` datetime DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `timer` int(11) DEFAULT NULL,
  PRIMARY KEY (`report_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- 테이블 데이터 puzzle.puzzle_report:~23 rows (대략적) 내보내기
/*!40000 ALTER TABLE `puzzle_report` DISABLE KEYS */;
INSERT INTO `puzzle_report` (`report_id`, `member_id`, `report_date`, `count`, `timer`) VALUES
	(1, '1', '2019-10-18 10:01:06', 15, 36),
	(2, '1', '2019-10-16 16:41:45', 8, 19),
	(3, '1', '2019-10-18 10:02:27', 16, 36),
	(5, '1', '2019-10-18 15:26:24', 10, 9),
	(6, '1', '2019-10-21 09:59:33', 28, 24),
	(7, '1', '2019-10-22 09:42:05', 60, 95),
	(8, '1', '2019-10-22 14:15:48', 60, 15),
	(9, '1', '2019-10-22 14:15:50', 100, 15),
	(10, '1', '2019-10-22 14:15:52', 138, 15),
	(11, '1', '2019-10-22 14:15:53', 178, 15),
	(12, '1', '2019-10-22 14:15:55', 216, 15),
	(13, '1', '2019-10-22 15:35:22', 58, 79),
	(14, '1', '2019-10-22 15:50:24', 56, 51),
	(15, '1', '2019-11-11 17:53:10', 437, 1057),
	(16, 'park', '2019-11-11 17:53:16', 437, 1057),
	(17, 'park', '2019-11-11 17:53:28', 537, 2015),
	(18, 'park', '2019-11-11 17:53:34', 567, 2315),
	(19, 'shin', '2019-11-11 17:53:46', 753, 3901),
	(20, 'shin', '2019-11-11 17:53:51', 553, 3901),
	(21, 'shin', '2019-11-11 17:53:55', 553, 3001),
	(22, 'kim', '2019-11-11 17:54:09', 324, 1576),
	(23, 'kim', '2019-11-11 17:54:17', 343, 1633),
	(24, 'kim', '2019-11-11 17:54:26', 243, 1219),
	(25, '1', '2019-12-30 13:57:40', 172, 127);
/*!40000 ALTER TABLE `puzzle_report` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
