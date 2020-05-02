-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Apr 07, 2020 at 06:54 PM
-- Server version: 10.3.12-MariaDB
-- PHP Version: 7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `librarysurvey`
--

-- --------------------------------------------------------

--
-- Table structure for table `answers`
--

DROP TABLE IF EXISTS `answers`;
CREATE TABLE IF NOT EXISTS `answers` (
  `ID` int(50) NOT NULL AUTO_INCREMENT,
  `QID` int(50) NOT NULL,
  `Answers` text NOT NULL,
  `Date` date NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `answers`
--

INSERT INTO `answers` (`ID`, `QID`, `Answers`, `Date`) VALUES
(1, 1, '[[true,false],[false,true,false,false,false],[false,true,false,false,false],[false,false,false,true,false,false,false,false],[true,false,false,false],[false,false,false,false,true],[true,false,false,false],[false,false,true,false,false]]', '2020-03-27'),
(2, 1, '[[true,false],[false,false,true,false,false],[false,true,false,false,false],[false,false,false,false,true,false,false,false],[false,false,false,true],[false,true,false,false,false],[false,true,false,false],[false,true,false,false,false]]', '2020-03-27'),
(3, 1, '[[true,false],[false,true,false,false,false],[false,true,false,false,false],[false,true,false,false,false,false,false,false],[false,true,false,false],[false,false,true,false,false],[true,false,false,false],[false,true,false,false,false]]', '2020-03-27'),
(4, 1, '[[false,true],[false,true,false,false,false],[true,false,false,false,false],[true,false,false,false,false,false,false,false],[true,false,false,false],[false,false,true,false,false],[false,true,false,false],[false,true,false,false,false]]', '2020-03-27'),
(5, 1, '[[true,false],[false,true,false,false,false],[false,true,false,false,false],[false,false,true,false,false,false,false,false],[false,false,true,false],[false,false,true,false,false],[false,false,true,false],[false,false,true,false,false]]', '2020-03-27'),
(6, 1, '[[false,true],[false,true,false,false,false],[false,false,true,false,false],[false,false,false,false,true,false,false,false],[false,false,true,false],[false,false,true,false,false],[true,false,false,false],[false,false,true,false,false]]', '2020-03-27'),
(7, 1, '[[false,true],[true,false,false,false,false],[true,false,false,false,false],[false,false,true,false,false,false,false,false],[false,false,true,false],[false,false,true,false,false],[false,false,true,false],[false,false,false,true,false]]', '2020-03-28'),
(8, 1, '[[true,false],[false,true,false,false,false],[false,false,false,true,false],[false,false,false,false,true,false,false,false],[false,false,false,true],[false,true,false,false,false],[false,true,false,false],[false,false,false,true,false]]', '2020-03-28'),
(9, 1, '[[true,false],[false,true,false,false,false],[false,false,true,false,false],[true,false,false,false,false,false,false,false],[false,true,false,false],[false,true,false,false,false],[false,false,true,false],[false,false,true,false,false]]', '2020-03-28'),
(10, 1, '[[true,false],[true,false,false,false,false],[false,false,true,false,false],[false,false,true,false,false,false,false,false],[false,false,true,false],[false,true,false,false,false],[false,false,true,false],[false,false,true,false,false]]', '2020-03-28'),
(11, 1, '[[true,false],[false,false,true,false,false],[false,true,false,false,false],[true,false,false,false,false,false,false,false],[false,true,false,false],[false,false,true,false,false],[false,false,false,true],[false,false,true,false,false]]', '2020-03-28'),
(12, 1, '[[true,false],[false,true,false,false,false],[false,false,false,true,false],[false,false,true,false,false,false,false,false],[false,true,false,false],[false,true,false,false,false],[false,false,true,false],[false,true,false,false,false]]', '2020-03-28'),
(13, 1, '[[true,false],[false,true,false,false,false],[false,false,false,true,false],[false,true,false,false,false,false,false,false],[false,true,false,false],[false,true,false,false,false],[false,true,false,false],[false,true,false,false,false]]', '2020-04-06'),
(14, 1, '[[true,false],[false,true,false,false,false],[false,true,false,false,false],[false,false,true,false,false,false,false,false],[false,true,false,false],[false,true,false,false,false],[false,true,false,false],[false,false,false,true,false]]', '2020-04-07');

-- --------------------------------------------------------

--
-- Table structure for table `participants`
--

DROP TABLE IF EXISTS `participants`;
CREATE TABLE IF NOT EXISTS `participants` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `participants`
--

INSERT INTO `participants` (`id`, `name`, `email`) VALUES
(1, 'vihanga', 'liyanage.vihanga99@gmail.com'),
(2, 'isuri yasara', 'isuri.yasara0513@gmail.com'),
(3, 'test123', 'test123@gmail.com'),
(4, 'test12345', 'vihanga@gmail.com'),
(5, '4e64uye4', 'fkfjhgkhj'),
(6, 'vihanga', 'vihanga@gmail.com'),
(7, 'vihanga', 'vihanga@gmail.com'),
(8, 'sahan', 'liyanage.sahan99@gmail.com'),
(9, 'test', 'test123@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
CREATE TABLE IF NOT EXISTS `questions` (
  `QID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `question` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `ping` int(50) NOT NULL,
  PRIMARY KEY (`QID`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`QID`, `name`, `question`, `ping`) VALUES
(1, 'questions', '[\r\n  {\r\n    \"question\": \"Have you a borrowed books earlier?\",\r\n    \"answers\": [\r\n      \"Yes\",\r\n      \"No\"\r\n    ],\r\n    \"type\": \"Radio\"\r\n  },\r\n  {\r\n    \"question\": \"What is your age category?\",\r\n    \"answers\": [\r\n      \"Under 18\",\r\n      \"18 - 25\",\r\n      \"25 - 35\",\r\n      \"35 - 45\",\r\n      \"Above 45\"\r\n    ],\r\n    \"type\": \"Radio\"\r\n  },\r\n  {\r\n    \"question\": \"What is your preferred language for the books that you read?\",\r\n    \"answers\": [\r\n      \"Sinhala\",\r\n      \"English\",\r\n      \"French\",\r\n      \"German\",\r\n      \"Other\"\r\n    ],\r\n    \"type\": \"Radio\"\r\n  },\r\n  {\r\n    \"question\": \"Which book genre do you borrow more often?\",\r\n    \"answers\": [\r\n      \"Educational\",\r\n      \"Classic\",\r\n      \"Action and Adventure\",\r\n      \"Romance\",\r\n      \"Biography/Autobiography\",\r\n      \"Comic and Graphic Novel\",\r\n      \"Short Story\",\r\n      \"Humor\"\r\n    ],\r\n    \"type\": \"Radio\"\r\n  },\r\n  {\r\n    \"question\": \"For how long do you borrow books?\",\r\n    \"answers\": [\r\n      \"For Few days\",\r\n      \"For 1 week\",\r\n      \"For 2 weeks\",\r\n      \"More than any of the above\"\r\n    ],\r\n    \"type\": \"Radio\"\r\n  },\r\n  {\r\n    \"question\": \"How much would you rate the library service?\",\r\n    \"answers\": [\r\n      \"1 star service\",\r\n      \"2 star service\",\r\n      \"3 star service\",\r\n      \"4 star service\",\r\n      \"5 star service\"\r\n    ],\r\n    \"type\": \"Radio\"\r\n  },\r\n  {\r\n    \"question\": \"What was the reason for your visit to the library?\",\r\n    \"answers\": [\r\n      \"Borrow Books\",\r\n      \"To use the Computer Facilities\",\r\n      \"Study Purposes\",\r\n      \"Other\"\r\n    ],\r\n    \"type\": \"Radio\"\r\n  },\r\n  {\r\n    \"question\": \"Do you think that there is enough books in the library?\",\r\n    \"answers\": [\r\n      \"1 star\",\r\n      \"2 star\",\r\n      \"3 star\",\r\n      \"4 star\",\r\n      \"5 star\"\r\n    ],\r\n    \"type\": \"Radio\"\r\n  }\r\n]', 15);

-- --------------------------------------------------------

--
-- Table structure for table `tblogin`
--

DROP TABLE IF EXISTS `tblogin`;
CREATE TABLE IF NOT EXISTS `tblogin` (
  `ID` int(50) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblogin`
--

INSERT INTO `tblogin` (`ID`, `username`, `password`, `type`) VALUES
(1, 'admin', '1234', 'admin'),
(2, 'vihanga', '4321', 'admin'),
(3, 'vindya', '1234', 'admin'),
(7, 'test4', '1234', 'admin');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
