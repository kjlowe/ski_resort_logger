-- phpMyAdmin SQL Dump
-- version 4.0.10.7
-- http://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Generation Time: Jan 16, 2016 at 07:15 AM
-- Server version: 5.5.32-cll-lve
-- PHP Version: 5.4.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `diuus_lifts`
--
CREATE DATABASE IF NOT EXISTS `diuus_lifts` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `diuus_lifts`;

-- --------------------------------------------------------

--
-- Table structure for table `log`
--

CREATE TABLE IF NOT EXISTS `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `logtext` varchar(200) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=722 ;

-- --------------------------------------------------------

--
-- Table structure for table `snowreport`
--

CREATE TABLE IF NOT EXISTS `snowreport` (
  `tomupdated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Base` smallint(6) NOT NULL,
  `Last12Hours` smallint(6) NOT NULL,
  `Last24Hours` smallint(6) NOT NULL,
  `Last48Hours` smallint(6) NOT NULL,
  `Last7Days` smallint(6) NOT NULL,
  `CumulativeSnow` mediumint(9) NOT NULL,
  `Visibility` varchar(100) NOT NULL,
  `FreezingLevel` varchar(100) NOT NULL,
  `CurrentConditionsValley` varchar(100) NOT NULL,
  `CurrentConditionsMid` varchar(100) NOT NULL,
  `CurrentConditionsAlpine` varchar(100) NOT NULL,
  `SurfaceConditionPrimary` varchar(100) NOT NULL,
  `SurfaceConditionSecondary` varchar(100) NOT NULL,
  `Snowmaking` varchar(100) NOT NULL,
  `TotalAcresOpen` smallint(6) NOT NULL,
  `WhistlerPark` tinyint(1) NOT NULL,
  `BlackcombPark` tinyint(1) NOT NULL,
  `RunsGroomedWhistler` smallint(6) NOT NULL,
  `RunsGroomedBlackcomb` smallint(6) NOT NULL,
  `RunsOpenWhistler` smallint(6) NOT NULL,
  `RunsOpenBlackcomb` smallint(6) NOT NULL,
  `LiftsOpenWhistler` smallint(6) NOT NULL,
  `LiftsOpenBlackcomb` smallint(6) NOT NULL,
  `AcresGroomedWhistler` smallint(6) NOT NULL,
  `AcresGroomedBlackcomb` smallint(6) NOT NULL,
  PRIMARY KEY (`tomupdated`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `stations`
--

CREATE TABLE IF NOT EXISTS `stations` (
  `StationID` smallint(6) NOT NULL,
  `MaxTemp` float NOT NULL,
  `AvgTemp` float NOT NULL,
  `MinTemp` float NOT NULL,
  `MaxWind` float NOT NULL,
  `AvgWind` float NOT NULL,
  `WindDir` float NOT NULL,
  `RH` float NOT NULL,
  `BP` float NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Timestamp` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `StationID` (`StationID`,`Timestamp`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10131 ;

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE IF NOT EXISTS `status` (
  `name` varchar(100) NOT NULL,
  `date` date NOT NULL,
  `opened` time DEFAULT NULL,
  `closed` time DEFAULT NULL,
  `standby` time DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13214 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
