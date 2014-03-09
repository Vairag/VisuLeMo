-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 31, 2014 at 01:41 AM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `visulemo`
--

-- --------------------------------------------------------

--
-- Table structure for table `actions`
--

CREATE TABLE IF NOT EXISTS `actions` (
  `action_id` int(11) NOT NULL AUTO_INCREMENT,
  `action_name` varchar(500) NOT NULL,
  PRIMARY KEY (`action_id`),
  UNIQUE KEY `action_name` (`action_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `actions`
--

INSERT INTO `actions` (`action_id`, `action_name`) VALUES
(1, 'Facebook like'),
(8, 'hosting'),
(0, 'No action'),
(6, 'passing'),
(7, 'permission'),
(3, 'post'),
(5, 'publication'),
(4, 'share'),
(2, 'tweet');

-- --------------------------------------------------------

--
-- Table structure for table `domains`
--

CREATE TABLE IF NOT EXISTS `domains` (
  `domain_id` int(11) NOT NULL AUTO_INCREMENT,
  `domain_name` varchar(100) NOT NULL,
  PRIMARY KEY (`domain_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=87 ;

--
-- Dumping data for table `domains`
--

INSERT INTO `domains` (`domain_id`, `domain_name`) VALUES
(1, 'Sports'),
(2, 'Soccer'),
(3, 'Olympic'),
(4, 'People'),
(5, 'Awards'),
(6, 'Education'),
(7, 'Media'),
(8, 'Books'),
(9, 'Video games'),
(10, 'award'),
(11, 'base/siswimsuitmodels'),
(12, 'tv'),
(13, 'media_common'),
(14, 'base/popstra'),
(15, 'music'),
(16, 'base/moscratch'),
(17, 'film'),
(18, 'base/saturdaynightlive'),
(19, 'book'),
(20, 'base/webvideo'),
(21, 'user/alust/default_domain'),
(22, 'base/daylifetopics'),
(23, 'internet'),
(24, 'broadcast'),
(25, 'base/americancomedy'),
(26, 'fictional_universe'),
(27, 'common'),
(28, 'base/moscowinternationalfilmfestival'),
(29, 'base/socanddatamanagement'),
(30, 'base/testbase123'),
(31, 'base/services'),
(32, 'business'),
(33, 'user/ilear/blog'),
(34, 'user/tahatfield/default_domain'),
(35, 'conferences'),
(36, 'user/tsegaran/random'),
(37, 'organization'),
(38, 'base/tagit'),
(39, 'user/fustbariclation/default_domain'),
(40, 'user/tsegaran/computer'),
(41, 'base/ontologies'),
(42, 'user/coco/science'),
(43, 'base/futurama'),
(44, 'base/sxswfilm'),
(45, 'base/argumentmaps'),
(46, 'user/osprey/default_domain'),
(47, 'base/allthingsnewyork'),
(48, 'user/micahsaul/advertising'),
(49, 'base/ghtech'),
(50, 'base/events'),
(51, 'computer'),
(52, 'base/skosbase'),
(53, 'base/schemastaging'),
(54, 'freebase'),
(55, 'cvg'),
(56, 'user/leop/default_domain'),
(57, 'user/pumpkin/etymology'),
(58, 'base/patronage'),
(59, 'user/robert/x2008_presidential_election'),
(60, 'base/charities'),
(61, 'atom'),
(62, 'base/jewlib'),
(63, 'base/database'),
(64, 'user/alexander/philosophy'),
(65, 'games'),
(66, 'base/bisac'),
(67, 'architecture'),
(68, 'user/trembler2005/default_domain'),
(69, 'base/process'),
(70, 'projects'),
(71, 'base/knowledgemanagement'),
(72, 'location'),
(73, 'base/socialocracy'),
(74, 'government'),
(75, 'base/elbogen'),
(76, 'theater'),
(77, 'radio'),
(78, 'exhibitions'),
(79, 'base/onlineadvertising'),
(80, 'base/bioventurist'),
(81, 'user/spencermountain/default_domain'),
(82, 'user/esamsoe/life'),
(83, 'base/ywhbase'),
(84, 'base/bibkn'),
(85, 'base/skills'),
(86, 'user/sexyprout/default_domain');

-- --------------------------------------------------------

--
-- Table structure for table `frameworks`
--

CREATE TABLE IF NOT EXISTS `frameworks` (
  `framework_id` int(11) NOT NULL AUTO_INCREMENT,
  `framework_name` varchar(100) NOT NULL,
  `framework_link` varchar(500) NOT NULL,
  PRIMARY KEY (`framework_id`),
  UNIQUE KEY `framework_name` (`framework_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `frameworks`
--

INSERT INTO `frameworks` (`framework_id`, `framework_name`, `framework_link`) VALUES
(1, 'PALM', 'http://subprogra.informatik.rwth-aachen.de/~ddugosija/llmian/'),
(2, 'Webtrace', 'http://subprogra.informatik.rwth-aachen.de/~mklein/cake/');

-- --------------------------------------------------------

--
-- Table structure for table `interests`
--

CREATE TABLE IF NOT EXISTS `interests` (
  `interest_id` int(11) NOT NULL AUTO_INCREMENT,
  `interest_name` varchar(100) NOT NULL,
  PRIMARY KEY (`interest_id`),
  UNIQUE KEY `interest` (`interest_name`),
  UNIQUE KEY `interest_2` (`interest_name`),
  UNIQUE KEY `interest_3` (`interest_name`),
  UNIQUE KEY `interest_4` (`interest_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=43 ;

--
-- Dumping data for table `interests`
--

INSERT INTO `interests` (`interest_id`, `interest_name`) VALUES
(42, 'Baba Ramdev'),
(29, 'blended learning'),
(1, 'Computer programming'),
(37, 'computer science'),
(36, 'computer science education'),
(10, 'Computing'),
(32, 'cultural heritage management'),
(12, 'data parallel'),
(11, 'data parallelism'),
(8, 'Dexter'),
(39, 'Dr. Manmohan Singh'),
(40, 'Dropbox'),
(18, 'Education'),
(21, 'Education Organizations'),
(19, 'Education Supplies'),
(34, 'Ethnic Groups'),
(15, 'finite difference time'),
(13, 'finite difference time domain'),
(2, 'Football'),
(6, 'Futurama'),
(41, 'Goethe-Institut'),
(35, 'Health'),
(4, 'How I Met Your Mother'),
(30, 'informal learning'),
(7, 'Iron Sky'),
(38, 'Issuu'),
(33, 'knowledge acquisition'),
(23, 'knowledge management'),
(28, 'learning object'),
(31, 'lifelong learning'),
(5, 'Linkin Park'),
(3, 'Lionel Messi'),
(0, 'No interest'),
(14, 'numerical method'),
(25, 'rwth aachen university'),
(20, 'Schools'),
(27, 'social network'),
(26, 'social software'),
(17, 'Software'),
(22, 'software development'),
(16, 'Teaching & Learning'),
(9, 'Technology & Electronics'),
(24, 'technology enhanced learning');

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE IF NOT EXISTS `items` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_link` varchar(500) NOT NULL,
  `learner_interest_id` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`item_id`),
  UNIQUE KEY `item_link` (`item_link`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=33 ;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`item_id`, `item_link`, `learner_interest_id`) VALUES
(0, 'No items', 31),
(1, 'http://www.facebook.com/ProgrammersCreateLife?fref=ts', 1),
(2, 'http://www.facebook.com/HOLI.GAUDY.AACHEN', 2),
(3, 'http://en.wikipedia.com/wiki/Research', 3),
(4, 'http://en.wikipedia.com/wiki/Social_network', 4),
(5, 'http://en.wikipedia.com/wiki/Iterative_design', 5),
(6, 'http://en.wikipedia.com/wiki/Technical_support', 6),
(7, 'http://en.wikipedia.com/wiki/Educational_software', 7),
(8, 'http://en.wikipedia.com/wiki/Lifelong_learning', 7),
(9, 'http://en.wikipedia.com/wiki/E-learning', 9),
(10, 'http://en.wikipedia.com/wiki/Knowledge_building', 10),
(11, 'http://en.wikipedia.com/wiki/Online_community', 11),
(12, 'http://en.wikipedia.com/wiki/Software_development', 12),
(13, 'http://en.wikipedia.com/wiki/Architecture', 13),
(14, 'http://en.wikipedia.com/wiki/Cost-effectiveness_analysis', 14),
(15, 'http://en.wikipedia.com/wiki/Formative_assessment', 15),
(16, 'http://en.wikipedia.com/wiki/Cambridge_University_Press', 15),
(20, 'http://en.wikipedia.com/wiki/Data_parallelism', 26),
(21, 'http://en.wikipedia.com/wiki/Finite-difference_time-domain_method', 29),
(22, 'http://en.wikipedia.com/wiki/Numerical_analysis', 29),
(23, 'http://www.facebook.com/5720169626', 1),
(24, 'http://www.facebook.com/17780227654', 1),
(25, 'http://www.facebook.com/19891061755', 1),
(26, 'http://www.facebook.com/23104471892', 1),
(27, 'http://www.facebook.com/49218554014', 1),
(28, 'http://www.theguardian.com/technology/computing', 1),
(29, 'http://www.facebook.com/102173226491776', 1),
(30, 'https://twitter.com/LioMessi_', 1),
(31, 'http://books.google.de/books/about/Basic_Computer_Programming.html', 1),
(32, 'https://plus.google.com/+ProgrammingCom', 1);

-- --------------------------------------------------------

--
-- Table structure for table `learners`
--

CREATE TABLE IF NOT EXISTS `learners` (
  `learner_id` int(11) NOT NULL AUTO_INCREMENT,
  `learner_name` varchar(50) NOT NULL,
  `learner_uid` varchar(20) NOT NULL,
  `learner_password` varchar(100) NOT NULL,
  `learner_email` varchar(40) NOT NULL,
  `learner_university` varchar(50) NOT NULL,
  `learner_designation` varchar(50) NOT NULL,
  `nameInPalm` varchar(30) NOT NULL DEFAULT 'Palm name',
  `nameInWebtrace` varchar(30) NOT NULL DEFAULT 'webtracename',
  PRIMARY KEY (`learner_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=112 ;

--
-- Dumping data for table `learners`
--

INSERT INTO `learners` (`learner_id`, `learner_name`, `learner_uid`, `learner_password`, `learner_email`, `learner_university`, `learner_designation`, `nameInPalm`, `nameInWebtrace`) VALUES
(1, 'Test User VisuLeMo', 'test_visulemo', 'test_visulemo', 'gvairagk@gmail.com', 'RWTH Aachen University', 'Master Student', 'ana balevic', 'Vairag Godhani'),
(52, 'ahmad ammari', 'ahmad', 'ahmad', 'ahmad@rwth-aachen.de', 'RWTH Aachen University', 'Supervisor/lecturer', 'ahmad ammari', 'ahmad ammari'),
(53, 'ana balevic', 'ana', 'ana', 'ana@rwth-aachen.de', 'RWTH Aachen University', 'Supervisor/lecturer', 'ana balevic', 'Hendrik Thüs'),
(54, 'Arham Muslim', 'Arham', 'Arham', 'Arham@rwth-aachen.de', 'RWTH Aachen University', 'Supervisor/lecturer', 'Arham Muslim', 'Arham Muslim'),
(57, 'christoph rensing', 'christoph', 'christoph', 'christoph@rwth-aachen.de', 'RWTH Aachen University', 'Supervisor/lecturer', 'christoph rensing', 'christoph rensing'),
(58, 'dan suthers', 'dan', 'dan', 'dan@rwth-aachen.de', 'RWTH Aachen University', 'Supervisor/lecturer', 'dan suthers', 'Hendrik Thüs'),
(66, 'hendrik drachsler', 'hendrik', 'hendrik', 'hendrik@rwth-aachen.de', 'RWTH Aachen University', 'Supervisor/lecturer', 'hendrik drachsler', 'hendrik drachsler'),
(80, 'mohamed amine chatti', 'chatti', 'chatti', 'mohamed@rwth-aachen.de', 'RWTH Aachen University', 'Supervisor/lecturer', 'mohamed amine chatti', 'Stefan Peters'),
(81, 'ralf klamma', 'ralf', 'ralf', 'ralf@rwth-aachen.de', 'RWTH Aachen University', 'Supervisor/lecturer', 'ana balevic', 'Vairag Godhani'),
(89, 'ulrik schroeder', 'ulrik', 'ulrik', 'ulrik@rwth-aachen.de', 'RWTH Aachen University', 'Supervisor/lecturer', 'ulrik schroeder', 'ulrik schroeder'),
(109, 'Krishna', 'krishna', 'IATO""!', 'krishna.prasath.god@gmail.com', '', '', 'ana balevic', 'Vairag Godhani'),
(110, 'Ashutosh Singla', 'ashutosh', 'A123456', 'ashutosh@rwth-aachen.de', 'RWTH Aachen', 'Masters', 'ana balevic', 'Hendrik Thüs'),
(111, 'Rajesh Dobaria', 'rajesh', 'r123456', 'rajesh@gmail.com', 'RWTH Aachen', 'Masters', 'ulrik schroeder', 'Rajesh Dobaria');

-- --------------------------------------------------------

--
-- Table structure for table `learners_interests`
--

CREATE TABLE IF NOT EXISTS `learners_interests` (
  `learner_interest_id` int(11) NOT NULL AUTO_INCREMENT,
  `learner_id` int(11) NOT NULL,
  `interest_id` int(11) NOT NULL,
  `weight` float NOT NULL DEFAULT '1',
  `action_id` int(11) NOT NULL,
  `source_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL DEFAULT '0',
  `framework_id` int(11) NOT NULL,
  `timestamp` datetime NOT NULL,
  PRIMARY KEY (`learner_interest_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=98 ;

--
-- Dumping data for table `learners_interests`
--

INSERT INTO `learners_interests` (`learner_interest_id`, `learner_id`, `interest_id`, `weight`, `action_id`, `source_id`, `item_id`, `framework_id`, `timestamp`) VALUES
(32, 1, 1, 0.9, 5, 6, 32, 1, '2013-10-31 00:00:00'),
(33, 1, 2, 0.5, 1, 1, 29, 2, '2013-10-30 16:00:00'),
(34, 1, 3, 0.6, 2, 2, 30, 2, '2013-10-30 17:00:00'),
(35, 58, 4, 0.8, 1, 1, 0, 2, '2012-06-13 12:42:22'),
(36, 58, 5, 0.3, 1, 1, 0, 2, '2012-06-13 04:46:32'),
(37, 58, 6, 0.7, 1, 1, 0, 2, '2012-06-13 16:46:32'),
(38, 58, 7, 0.5, 1, 1, 0, 2, '2012-06-13 04:46:32'),
(39, 58, 8, 0.2, 1, 1, 0, 2, '2012-06-13 04:46:32'),
(40, 53, 9, 0.679348, 5, 0, 0, 1, '2012-12-15 03:01:00'),
(41, 53, 10, 0.403846, 5, 0, 0, 1, '2012-12-15 03:01:00'),
(42, 53, 11, 0.3, 5, 0, 0, 1, '2012-12-15 03:01:00'),
(43, 53, 12, 1, 5, 0, 0, 1, '2012-12-15 03:01:00'),
(44, 53, 15, 0.75, 5, 0, 0, 1, '2012-12-15 03:01:00'),
(45, 53, 14, 0.6, 5, 0, 0, 1, '2012-12-15 03:01:00'),
(46, 53, 4, 1, 1, 1, 0, 2, '2012-06-13 12:42:22'),
(47, 53, 5, 1, 1, 1, 0, 2, '2012-06-13 04:46:32'),
(48, 53, 6, 0.07, 1, 1, 0, 2, '2012-06-13 16:46:32'),
(49, 53, 7, 0.9, 1, 1, 0, 2, '2012-06-13 04:46:32'),
(50, 53, 8, 0.35, 1, 1, 0, 2, '2012-06-13 04:46:32'),
(51, 53, 13, 1, 5, 0, 0, 1, '2012-12-15 03:01:00'),
(52, 80, 16, 0.746032, 5, 0, 0, 1, '2012-11-27 16:50:27'),
(53, 80, 9, 0.728972, 5, 0, 0, 1, '2012-11-27 16:50:27'),
(54, 80, 17, 0.6875, 5, 0, 0, 1, '2012-11-27 16:50:27'),
(55, 80, 18, 0.571429, 5, 0, 0, 1, '2012-11-27 16:50:27'),
(56, 80, 19, 0.607843, 5, 0, 0, 1, '2012-11-27 16:50:27'),
(57, 80, 20, 0.473441, 5, 0, 0, 1, '2012-11-27 16:50:27'),
(58, 80, 21, 0.473441, 5, 0, 0, 1, '2012-11-27 16:50:27'),
(59, 80, 22, 1, 5, 0, 0, 1, '2012-11-27 16:50:27'),
(60, 80, 23, 1, 5, 0, 0, 1, '2012-11-27 16:50:27'),
(61, 80, 24, 1, 5, 0, 0, 1, '2012-11-27 16:50:27'),
(62, 80, 25, 1, 5, 0, 0, 1, '2012-11-27 16:50:27'),
(63, 80, 26, 1, 5, 0, 0, 1, '2012-11-27 16:50:27'),
(64, 80, 27, 1, 5, 0, 0, 1, '2012-11-27 16:50:27'),
(65, 80, 28, 1, 5, 0, 0, 1, '2012-11-27 16:50:27'),
(66, 80, 29, 1, 5, 0, 0, 1, '2012-11-27 16:50:28'),
(67, 80, 30, 1, 5, 0, 0, 1, '2012-11-27 16:50:28'),
(68, 80, 31, 1, 5, 0, 0, 1, '2012-11-27 16:50:28'),
(69, 80, 32, 1, 5, 0, 0, 1, '2012-11-27 16:50:28'),
(70, 80, 33, 0.9, 3, 0, 0, 1, '2012-11-27 16:50:28'),
(71, 111, 34, 0.558824, 5, 0, 0, 1, '2012-11-28 08:39:31'),
(72, 111, 35, 0.446154, 5, 0, 0, 1, '2012-11-28 08:39:31'),
(73, 111, 18, 0.74026, 5, 0, 0, 1, '2012-11-28 08:39:31'),
(74, 111, 9, 0.562738, 5, 0, 0, 1, '2012-11-28 08:39:31'),
(75, 111, 16, 0.423729, 5, 0, 0, 1, '2012-11-28 08:39:31'),
(76, 111, 17, 0.596154, 5, 0, 0, 1, '2012-11-28 08:39:31'),
(77, 111, 36, 1, 5, 0, 0, 1, '2012-11-28 08:39:31'),
(78, 111, 37, 1, 5, 0, 0, 1, '2012-11-28 08:39:31'),
(79, 111, 1, 1, 5, 0, 0, 1, '2012-11-28 08:39:31'),
(80, 111, 22, 1, 5, 0, 0, 1, '2012-11-28 08:39:31'),
(87, 81, 38, 1, 1, 1, 23, 2, '2013-07-05 12:30:45'),
(88, 81, 39, 1, 1, 1, 24, 2, '2013-07-05 12:30:45'),
(89, 81, 40, 1, 1, 1, 25, 2, '2013-07-05 12:30:45'),
(90, 81, 41, 1, 1, 1, 26, 2, '2013-09-19 01:47:18'),
(91, 81, 42, 1, 1, 1, 27, 2, '2013-09-19 01:47:18'),
(92, 81, 9, 0.679348, 5, 0, 0, 1, '2012-12-15 03:01:00'),
(93, 81, 10, 0.403846, 5, 0, 28, 1, '2012-12-15 03:01:00'),
(94, 81, 11, 1, 5, 0, 20, 1, '2012-12-15 03:01:00'),
(95, 81, 12, 1, 5, 0, 20, 1, '2012-12-15 03:01:00'),
(96, 81, 13, 1, 5, 0, 21, 1, '2012-12-15 03:01:00'),
(97, 81, 14, 1, 5, 0, 0, 1, '2012-12-15 03:01:00');

-- --------------------------------------------------------

--
-- Table structure for table `sources`
--

CREATE TABLE IF NOT EXISTS `sources` (
  `source_id` int(11) NOT NULL AUTO_INCREMENT,
  `source_name` varchar(100) NOT NULL,
  `source_url` varchar(300) NOT NULL DEFAULT '  ',
  PRIMARY KEY (`source_id`),
  UNIQUE KEY `source_name` (`source_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `sources`
--

INSERT INTO `sources` (`source_id`, `source_name`, `source_url`) VALUES
(0, 'No source', 'No source'),
(1, 'Facebook', 'www.facebook.com'),
(2, 'Twitter', 'www.twitter.com'),
(6, 'Google', 'www.google.com');

-- --------------------------------------------------------

--
-- Table structure for table `types`
--

CREATE TABLE IF NOT EXISTS `types` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(100) NOT NULL,
  `domain_id` int(11) NOT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=154 ;

--
-- Dumping data for table `types`
--

INSERT INTO `types` (`type_id`, `type_name`, `domain_id`) VALUES
(1, 'Sport', 1),
(2, 'Athlete', 1),
(3, 'Football player', 2),
(4, 'Olympic discipline', 3),
(5, 'Olympic athletes', 3),
(6, 'person', 4),
(7, 'Professional field', 4),
(8, 'Awards winner', 5),
(9, 'Field Of Study', 6),
(10, 'media genre', 7),
(11, 'Literature Subject', 8),
(12, 'Video game genre', 9),
(13, 'Award-Nominated Work', 10),
(14, 'Topic', 11),
(15, 'TV Program', 12),
(16, 'Netflix Title', 13),
(17, 'Award-Winning Work', 10),
(18, 'Celebrity', 14),
(19, 'Award Winner', 10),
(20, 'sww_base', 14),
(21, 'Lyricist', 15),
(22, 'Musical Artist', 15),
(23, 'Record Producer', 15),
(24, 'SHCE021709', 16),
(25, 'Person or entity appearing in film', 17),
(26, 'TV program guest', 12),
(27, 'SNL musical guest', 18),
(28, 'Award Nominee', 10),
(29, 'Musical Group', 15),
(30, 'Literature Subject', 19),
(31, 'Composer', 15),
(32, 'Topic', 20),
(33, 'Processed with Review Queue', 21),
(34, 'Daylife Topic', 22),
(35, 'Social network user', 23),
(36, 'Broadcast Artist', 24),
(37, 'Quotation Source', 13),
(38, 'TV show', 25),
(39, 'Work of Fiction', 26),
(40, 'Topic', 27),
(41, 'Topic', 28),
(42, 'Film', 17),
(43, 'Adaptation', 13),
(44, 'Adapted Work', 13),
(45, 'concept', 29),
(46, 'Topic', 30),
(47, 'Web hosting services provided', 31),
(48, 'Competitive Space', 32),
(49, 'Internet', 33),
(50, 'Business Development', 34),
(51, 'Industry', 32),
(52, 'Conference subject', 35),
(53, 'Profession', 4),
(54, 'Taxonomy Subject', 36),
(55, 'Organization sector', 37),
(56, 'Concept', 38),
(57, 'Website Category', 23),
(58, 'Discipline', 39),
(59, 'Algorithm Family', 40),
(60, 'Ontology Instance', 41),
(61, 'Concepts/Theories', 42),
(62, 'Topic', 40),
(63, 'Topic', 43),
(64, 'Topic', 44),
(65, 'Thing of expressed value', 45),
(66, 'Occupation', 46),
(67, 'Beauty school field of study', 31),
(68, 'Topic', 47),
(69, 'Advertising Role', 48),
(70, 'Character Occupation', 26),
(71, 'Topic', 49),
(72, 'GH: technologies', 49),
(73, 'Periodical Subject', 19),
(74, 'Quotation Subject', 13),
(75, 'Subject of festival', 50),
(76, 'Consumer product', 32),
(77, 'Broadcast Genre', 24),
(78, 'Software Genre', 51),
(79, 'Vocabulary Equivalent Topic', 52),
(80, 'Media genre', 13),
(81, 'Topic', 52),
(82, 'Contact product', 53),
(83, 'Type/domain equivalent topic', 54),
(84, 'Video Game Genre', 55),
(85, 'schoolanduniversity.com', 56),
(86, 'Film subject', 17),
(87, 'Literary Genre', 13),
(88, 'Internet Video Genre', 20),
(89, 'Award discipline', 10),
(90, 'Word', 57),
(91, 'Patronage sector', 58),
(92, 'Contact category', 37),
(93, 'Service clientele', 31),
(94, 'Campaign issues', 59),
(95, 'Charitable field', 60),
(96, 'Feed Category', 61),
(97, 'Embroidery service type', 31),
(98, 'Jewish Studies Field', 62),
(99, 'Film genre', 17),
(100, 'Database topic', 63),
(101, 'Philosophical Subject', 64),
(102, 'TV Genre', 12),
(103, 'Game genre', 65),
(104, 'BISAC Equivalent Subject', 66),
(105, 'School category', 6),
(106, 'Building function', 67),
(107, 'Organization type', 37),
(108, 'Product category', 32),
(109, 'Literary School Or Movement', 19),
(110, 'Book Edition', 19),
(111, 'Cataloged instance', 13),
(112, 'SSADM', 68),
(113, 'Agile', 68),
(114, 'Process', 69),
(115, 'Topic', 69),
(116, 'Freebase Data Task', 54),
(117, 'Project focus', 70),
(118, 'Topic', 71),
(119, 'College/University', 6),
(120, 'Topic', 62),
(121, 'Organization', 37),
(122, 'Employer', 32),
(123, 'Location', 72),
(124, 'Parent Institution of Judaica Owner', 62),
(125, 'Educational Institution', 6),
(126, 'Topic', 73),
(127, 'Government', 74),
(128, 'Governmental Body', 74),
(129, 'Meeting Focus', 75),
(130, 'Theater Genre', 76),
(131, 'Advertised thing', 48),
(132, 'Radio subject', 77),
(133, 'Exhibition subject', 78),
(134, 'Ad Network Vertical', 79),
(135, 'Technology class', 80),
(136, 'School subject', 81),
(137, 'topic', 82),
(138, 'Topic', 83),
(139, 'Department', 84),
(140, 'Skill', 85),
(141, 'Character Power', 26),
(142, 'Website', 23),
(143, 'Politician', 74),
(144, 'Organization leader', 32),
(145, 'Context name', 53),
(146, 'Software', 51),
(147, 'Web application', 86),
(148, 'Membership organization', 37),
(149, 'Award Presenting Organization', 10),
(150, 'Building Occupant', 67),
(151, 'Nonprofit organization', 37),
(152, 'Author', 19),
(153, 'Organization founder', 37);

-- --------------------------------------------------------

--
-- Table structure for table `types_interests`
--

CREATE TABLE IF NOT EXISTS `types_interests` (
  `types_interests_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_id` int(11) NOT NULL,
  `interest_id` int(11) NOT NULL,
  PRIMARY KEY (`types_interests_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=290 ;

--
-- Dumping data for table `types_interests`
--

INSERT INTO `types_interests` (`types_interests_id`, `type_id`, `interest_id`) VALUES
(1, 1, 2),
(2, 2, 3),
(3, 3, 3),
(4, 4, 2),
(5, 5, 3),
(6, 6, 3),
(7, 7, 1),
(8, 8, 3),
(9, 9, 1),
(10, 10, 1),
(11, 10, 2),
(12, 11, 1),
(13, 11, 2),
(14, 12, 2),
(15, 13, 4),
(16, 14, 4),
(17, 15, 4),
(18, 16, 4),
(19, 17, 4),
(20, 18, 5),
(21, 19, 5),
(22, 20, 5),
(23, 21, 5),
(24, 22, 5),
(25, 23, 5),
(26, 24, 5),
(27, 25, 5),
(28, 26, 5),
(29, 27, 5),
(30, 28, 5),
(31, 29, 5),
(32, 30, 5),
(33, 31, 5),
(34, 32, 5),
(35, 33, 5),
(36, 34, 5),
(37, 35, 5),
(38, 36, 5),
(39, 37, 6),
(40, 38, 6),
(41, 39, 6),
(42, 13, 6),
(43, 40, 6),
(44, 15, 6),
(45, 16, 6),
(46, 17, 6),
(47, 13, 7),
(48, 41, 7),
(49, 42, 7),
(50, 17, 7),
(51, 13, 8),
(52, 39, 8),
(53, 40, 8),
(54, 43, 8),
(55, 15, 8),
(56, 16, 8),
(57, 17, 8),
(58, 44, 8),
(59, 45, 10),
(60, 30, 10),
(61, 46, 10),
(62, 47, 10),
(63, 48, 10),
(64, 49, 10),
(65, 50, 9),
(66, 51, 10),
(67, 52, 9),
(68, 30, 9),
(69, 53, 9),
(70, 40, 11),
(71, 54, 9),
(72, 40, 12),
(73, 55, 9),
(74, 40, 9),
(75, 40, 13),
(76, 56, 9),
(77, 57, 9),
(78, 9, 9),
(79, 58, 9),
(80, 59, 14),
(81, 51, 9),
(82, 30, 14),
(83, 60, 14),
(84, 61, 14),
(85, 54, 14),
(86, 62, 14),
(87, 9, 14),
(88, 63, 6),
(89, 64, 7),
(90, 65, 16),
(91, 30, 16),
(92, 66, 16),
(93, 53, 16),
(94, 67, 16),
(95, 68, 16),
(96, 69, 16),
(97, 56, 16),
(98, 70, 16),
(99, 60, 9),
(100, 71, 9),
(101, 72, 9),
(102, 73, 9),
(103, 74, 17),
(104, 55, 17),
(105, 75, 17),
(106, 76, 17),
(107, 77, 17),
(108, 78, 17),
(109, 79, 17),
(110, 30, 17),
(111, 80, 17),
(112, 54, 17),
(113, 81, 17),
(114, 82, 17),
(115, 48, 17),
(116, 83, 17),
(117, 51, 17),
(118, 74, 18),
(119, 84, 18),
(120, 85, 18),
(121, 86, 18),
(122, 55, 18),
(123, 7, 18),
(124, 87, 18),
(125, 88, 18),
(126, 73, 18),
(127, 89, 18),
(128, 90, 18),
(129, 91, 18),
(130, 92, 18),
(131, 93, 18),
(132, 30, 18),
(133, 80, 18),
(134, 94, 18),
(135, 95, 18),
(136, 32, 18),
(137, 96, 18),
(138, 57, 18),
(139, 97, 18),
(140, 60, 18),
(141, 98, 18),
(142, 56, 18),
(143, 77, 18),
(144, 99, 18),
(145, 100, 18),
(146, 52, 18),
(147, 101, 18),
(148, 54, 18),
(149, 102, 18),
(150, 103, 18),
(151, 104, 18),
(152, 82, 18),
(153, 9, 18),
(154, 83, 18),
(155, 51, 18),
(156, 40, 19),
(157, 30, 20),
(158, 105, 20),
(159, 86, 20),
(160, 106, 20),
(161, 40, 20),
(162, 56, 20),
(163, 107, 20),
(164, 82, 20),
(165, 108, 20),
(166, 51, 20),
(167, 109, 20),
(168, 110, 21),
(169, 111, 21),
(170, 40, 21),
(171, 112, 22),
(172, 113, 22),
(173, 114, 22),
(174, 115, 22),
(175, 116, 22),
(176, 117, 23),
(177, 30, 23),
(178, 53, 23),
(179, 55, 23),
(180, 118, 23),
(181, 60, 24),
(182, 105, 24),
(183, 55, 24),
(184, 40, 24),
(185, 57, 24),
(186, 82, 24),
(187, 119, 25),
(188, 120, 25),
(189, 121, 25),
(190, 122, 25),
(191, 123, 25),
(192, 124, 25),
(193, 125, 25),
(194, 92, 26),
(195, 86, 26),
(196, 126, 26),
(197, 116, 26),
(198, 57, 26),
(199, 78, 26),
(200, 92, 27),
(201, 86, 27),
(202, 126, 27),
(203, 116, 27),
(204, 57, 27),
(205, 78, 27),
(206, 40, 28),
(207, 40, 29),
(208, 40, 30),
(209, 127, 31),
(210, 40, 31),
(211, 128, 31),
(212, 40, 32),
(213, 117, 33),
(214, 30, 33),
(215, 53, 33),
(216, 55, 33),
(217, 118, 33),
(218, 74, 34),
(219, 129, 34),
(220, 30, 34),
(221, 54, 34),
(222, 40, 34),
(223, 130, 34),
(224, 74, 35),
(225, 131, 35),
(226, 60, 35),
(227, 55, 35),
(228, 87, 35),
(229, 88, 35),
(230, 77, 35),
(231, 73, 35),
(232, 79, 35),
(233, 30, 35),
(234, 132, 35),
(235, 80, 35),
(236, 133, 35),
(237, 32, 35),
(238, 95, 35),
(239, 134, 35),
(240, 57, 35),
(241, 104, 35),
(242, 9, 35),
(243, 51, 35),
(244, 7, 9),
(245, 135, 9),
(246, 136, 9),
(247, 137, 36),
(248, 89, 36),
(249, 52, 36),
(250, 80, 36),
(251, 138, 36),
(252, 9, 36),
(253, 139, 36),
(254, 137, 37),
(255, 89, 37),
(256, 52, 37),
(257, 80, 37),
(258, 138, 37),
(259, 9, 37),
(260, 139, 37),
(261, 52, 1),
(262, 80, 1),
(263, 140, 1),
(264, 141, 1),
(265, 52, 33),
(266, 60, 33),
(267, 40, 33),
(268, 142, 38),
(269, 40, 38),
(270, 143, 39),
(271, 40, 39),
(272, 144, 39),
(273, 145, 39),
(274, 6, 39),
(275, 146, 40),
(276, 40, 40),
(277, 147, 40),
(278, 148, 41),
(279, 149, 41),
(280, 40, 41),
(281, 121, 41),
(282, 122, 41),
(283, 150, 41),
(284, 151, 41),
(285, 152, 42),
(286, 40, 42),
(287, 153, 42),
(288, 6, 42),
(289, 40, 14);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
