-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Feb 28, 2018 at 05:15 PM
-- Server version: 5.7.19
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `where_to`
--

DELIMITER $$
--
-- Procedures
--
DROP PROCEDURE IF EXISTS `GenJavaModel`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `GenJavaModel` (IN `pTableName` VARCHAR(255))  BEGIN
DECLARE vClassName varchar(255);
declare vClassGetSet mediumtext;
declare vClassPrivate mediumtext;
declare v_codeChunk_pri_var varchar(1024);
declare v_codeChunk_pub_get varchar(1024);
declare v_codeChunk_pub_set varchar(1024);
 
DECLARE v_finished INTEGER DEFAULT 0;
DEClARE code_cursor CURSOR FOR
    SELECT pri_var,pub_get, pub_set FROM temp1; 
 
DECLARE CONTINUE HANDLER 
        FOR NOT FOUND SET v_finished = 1;
 
set vClassGetSet ='';
/* Make class name*/
    SELECT (CASE WHEN col1 = col2 THEN col1 ELSE concat(col1,col2)  END) into vClassName
    FROM(
    SELECT CONCAT(UCASE(MID(ColumnName1,1,1)),LCASE(MID(ColumnName1,2))) as col1,
    CONCAT(UCASE(MID(ColumnName2,1,1)),LCASE(MID(ColumnName2,2))) as col2
    FROM
    (SELECT SUBSTRING_INDEX(pTableName, '_', -1) as ColumnName2,
        SUBSTRING_INDEX(pTableName, '_', 1) as ColumnName1) A) B;
 
    /*store all properties into temp table*/
    CREATE TEMPORARY TABLE IF NOT EXISTS  temp1 ENGINE=MyISAM  
    as (
    select
    concat('\tprivate ', ColumnType,' _', FieldName,';') pri_var,
    concat( 'public ', ColumnType , ' get' , FieldName,'(){\r\n\t\t return _', FieldName,';\r\n\t}') pub_get,
    concat( 'public void ', ' set' , FieldName,'( ',ColumnType,' value){\r\n\t\t _', FieldName,' = value;\r\n\t}') pub_set
    FROM(
    SELECT (CASE WHEN col1 = col2 THEN col1 ELSE concat(col1,col2)  END) AS FieldName, 
    case DATA_TYPE 
            when 'bigint' then 'long'
            when 'binary' then 'byte[]'
            when 'bit' then 'bool'
            when 'char' then 'String'
            when 'date' then 'Date'
            when 'datetime' then 'DateTime'
            when 'datetime2' then 'DateTime'
            when 'decimal' then 'decimal'
            when 'float' then 'float'
            when 'image' then 'byte[]'
            when 'int' then 'int'
            when 'money' then 'decimal'
            when 'nchar' then 'String'
            when 'ntext' then 'String'
            when 'numeric' then 'decimal'
            when 'nvarchar' then 'String'
            when 'real' then 'double'
            when 'smalldatetime' then 'Date'
            when 'smallint' then 'short'
            when 'mediumint' then 'int'
            when 'smallmoney' then 'decimal'
            when 'text' then 'String'
            when 'time' then 'Date'
            when 'timestamp' then 'Date'
            when 'tinyint' then 'byte'
            when 'uniqueidentifier' then 'String'
            when 'varbinary' then 'byte[]'
            when 'varchar' then 'String'
            when 'year' THEN 'int'
            else 'UNKNOWN_' + DATA_TYPE
        end ColumnType
    FROM(
    select CONCAT(UCASE(MID(ColumnName1,1,1)),LCASE(MID(ColumnName1,2))) as col1,
    CONCAT(UCASE(MID(ColumnName2,1,1)),LCASE(MID(ColumnName2,2))) as col2, DATA_TYPE
    from
    (SELECT SUBSTRING_INDEX(COLUMN_NAME, '_', -1) as ColumnName2,
    SUBSTRING_INDEX(COLUMN_NAME, '_', 1) as ColumnName1,
    DATA_TYPE, COLUMN_TYPE FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_name = pTableName) A) B)C);
 
    set vClassGetSet = '';
    set vClassPrivate = '';
    /* concat all properties*/
    OPEN code_cursor;
  
            get_code: LOOP
          
                FETCH code_cursor INTO v_codeChunk_pri_var, v_codeChunk_pub_get,  v_codeChunk_pub_set;
          
                IF v_finished = 1 THEN
                    LEAVE get_code;
                END IF;
                 
                -- build code
                select  CONCAT('\t',vClassPrivate,'\r\n', v_codeChunk_pri_var) into vClassPrivate;
                select  CONCAT('\t',vClassGetSet,'\r\n\t', v_codeChunk_pub_get,'\r\n\t', v_codeChunk_pub_set) into  vClassGetSet ;
          
            END LOOP get_code;
      
        CLOSE code_cursor;
 
drop table temp1;
/*make class*/
select concat('public class ',vClassName,'\r\n{',vClassPrivate,'\r\n', vClassGetSet,'\r\n}');
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `amitie`
--

DROP TABLE IF EXISTS `amitie`;
CREATE TABLE IF NOT EXISTS `amitie` (
  `idAmitie` int(8) NOT NULL AUTO_INCREMENT,
  `idUtilisateur1` int(8) NOT NULL,
  `idUtilisateur2` int(8) NOT NULL,
  PRIMARY KEY (`idAmitie`)
) ENGINE=MyISAM AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `amitie`
--

INSERT INTO `amitie` (`idAmitie`, `idUtilisateur1`, `idUtilisateur2`) VALUES
(21, 12, 18),
(26, 8, 12),
(20, 12, 10),
(28, 8, 5),
(23, 1, 17),
(24, 8, 17),
(29, 8, 4),
(30, 8, 15);

-- --------------------------------------------------------

--
-- Table structure for table `archive`
--

DROP TABLE IF EXISTS `archive`;
CREATE TABLE IF NOT EXISTS `archive` (
  `ref` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `ref_etab` varchar(255) NOT NULL,
  `date_debut` varchar(255) NOT NULL,
  `date_fin` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `theme` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ref`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `destination`
--

DROP TABLE IF EXISTS `destination`;
CREATE TABLE IF NOT EXISTS `destination` (
  `ref` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `adresse` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  `rating` float NOT NULL,
  `id_voyage` int(11) NOT NULL,
  PRIMARY KEY (`ref`),
  KEY `id` (`id_voyage`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `destination`
--

INSERT INTO `destination` (`ref`, `nom`, `adresse`, `type`, `rating`, `id_voyage`) VALUES
(1, 'Berliner Philharmonie', 'Mitte, 10785 Berlin, Germany', 'premise', 0, 21),
(2, 'Radisson Blu Hotel, Berlin', 'Karl-Liebknecht-Str. 3, 10178 Berlin, Germany', 'bar', 4.4, 21),
(3, 'Hotel Hackescher Markt', 'Große Präsidentenstraße 8, 10178 Berlin, Germany', 'lodging', 4.3, 21),
(4, 'Citadines Trafalgar Square London', '18-21 Northumberland Ave, London WC2N 5EA, UK', 'real_estate_agency', 4.1, 28),
(5, 'Corinthia Hotel London', 'Whitehall Pl, Westminster, London SW1A 2BD, UK', 'lodging', 4.6, 28),
(6, 'Every Hotel Piccadilly', 'Coventry St, London W1D 6BZ, UK', 'bar', 4.4, 28),
(7, 'Citystay', 'Rosenstraße 16, 10178 Berlin, Germany', 'lodging', 4.2, 22),
(8, 'Hôtel De Nice', '42 Bis Rue de Rivoli, 75004 Paris, France', 'lodging', 4.2, 17);

-- --------------------------------------------------------

--
-- Table structure for table `enseigne`
--

DROP TABLE IF EXISTS `enseigne`;
CREATE TABLE IF NOT EXISTS `enseigne` (
  `idEnseigne` int(11) NOT NULL AUTO_INCREMENT,
  `idUtilisateur` int(11) NOT NULL,
  `type` varchar(50) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `description` varchar(100) NOT NULL,
  `adresse` varchar(50) NOT NULL,
  `note` float DEFAULT NULL,
  `specialite` varchar(50) NOT NULL,
  `numTel` varchar(50) NOT NULL,
  `signalEnseigne` int(11) DEFAULT '0',
  `photo1` varchar(100) DEFAULT NULL,
  `photo2` varchar(100) DEFAULT NULL,
  `photo3` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idEnseigne`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `enseigne`
--

INSERT INTO `enseigne` (`idEnseigne`, `idUtilisateur`, `type`, `nom`, `description`, `adresse`, `note`, `specialite`, `numTel`, `signalEnseigne`, `photo1`, `photo2`, `photo3`) VALUES
(33, 1, 'Restaurant', 'chanabo', 'zazaza	zaza', 'zoeizeoizoie', 2.5, 'popopopo', '88858', 2, 'file:/C:/Users/Mohamed%20El%20Hammi/Desktop/2.jpg', 'file:/C:/Users/Mohamed%20El%20Hammi/Desktop/Capture.PNG', 'file:/C:/Users/Mohamed%20El%20Hammi/Desktop/1.jpg'),
(35, 5, 'Restaurant', 'Le Parrain', 'cafe', 'ariana', 0, '9ahwa', '3', 0, 'file:/C:/Users/ghaie/Desktop/Page%20de%20garde%20groupe%205.jpg', 'file:/C:/Users/ghaie/Desktop/Page%20de%20garde%20groupe%205.jpg', 'file:/C:/Users/ghaie/Desktop/Page%20de%20garde%20groupe%205.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `evenement`
--

DROP TABLE IF EXISTS `evenement`;
CREATE TABLE IF NOT EXISTS `evenement` (
  `ref` int(15) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `ref_etab` varchar(255) NOT NULL,
  `date_debut` varchar(255) NOT NULL,
  `date_fin` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `theme` varchar(255) DEFAULT NULL,
  `nbr_place` int(11) DEFAULT NULL,
  `prix` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  PRIMARY KEY (`ref`)
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `evenement`
--

INSERT INTO `evenement` (`ref`, `nom`, `ref_etab`, `date_debut`, `date_fin`, `description`, `image`, `theme`, `nbr_place`, `prix`, `idUser`) VALUES
(23, 'wassim', 'Essouassi, Tunisia', '2018-03-02', '2018-03-10', 'fless', 'file:\\C:\\Users\\ghaie\\Desktop\\3A\\S2\\PI-Dev\\PI-Dev\\src\\gui\\ressources\\jamie_jones.jpg', 'Musique', 10, 80, 0),
(13, 'aze', 'aze', '2018-02-07', '2018-02-23', 'aze', 'file:\\C:\\Users\\ghaie\\Desktop\\3A\\S2\\PI-Dev\\PI-Dev\\src\\gui\\ressources\\jamie_jones.jpg', NULL, NULL, 0, 0),
(11, 'qsd', 'zae', '2018-02-23', '2018-02-24', 'aze', 'file:\\C:\\Users\\ghaie\\Desktop\\3A\\S2\\PI-Dev\\PI-Dev\\src\\gui\\ressources\\jamie_jones.jpg', 'Musique', 5, 5, 0),
(20, 'aaa', 'az', '2018-03-07', '2018-03-09', 'aze', 'file:\\C:\\Users\\ghaie\\Desktop\\3A\\S2\\PI-Dev\\PI-Dev\\src\\gui\\ressources\\jamie_jones.jpg', 'Culture', 123, 0, 0),
(22, 'oooo', 'aaaa', '2018-02-27', '2018-02-28', 'aa', 'file:\\C:\\Users\\ghaie\\Desktop\\3A\\S2\\PI-Dev\\PI-Dev\\src\\gui\\ressources\\jamie_jones.jpg', 'Enfance', 11, 12, 0),
(27, 'Fairground', 'Ecovillage, Tunisia', '2018-03-02', '2018-03-04', 'barsha fless w after kita', 'file:/C:/Users/ghaie/Pictures/Photo_identité.jpg', 'Musique', 300, 70, 8),
(26, 'aa', 'Sousse, Tunisia', '2018-03-02', '2018-03-10', 'aa', 'file:/C:/Users/ghaie/Desktop/neon_deer_large.jpg', 'Musique', 123, 123, 8);

-- --------------------------------------------------------

--
-- Table structure for table `noteenseigne`
--

DROP TABLE IF EXISTS `noteenseigne`;
CREATE TABLE IF NOT EXISTS `noteenseigne` (
  `idNote` int(11) NOT NULL AUTO_INCREMENT,
  `idEnseigne` int(11) NOT NULL,
  `idUtilisateur` int(11) NOT NULL,
  `noteUtilisateur` int(11) DEFAULT NULL,
  PRIMARY KEY (`idNote`),
  KEY `noteenseigne_ibfk_1` (`idEnseigne`),
  KEY `noteenseigne_ibfk_2` (`idUtilisateur`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `noteenseigne`
--

INSERT INTO `noteenseigne` (`idNote`, `idEnseigne`, `idUtilisateur`, `noteUtilisateur`) VALUES
(12, 33, 2, 4),
(13, 33, 8, 1);

-- --------------------------------------------------------

--
-- Table structure for table `reservationenseigne`
--

DROP TABLE IF EXISTS `reservationenseigne`;
CREATE TABLE IF NOT EXISTS `reservationenseigne` (
  `ref_res` int(11) NOT NULL AUTO_INCREMENT,
  `date_reservation` date NOT NULL,
  `nb_place` int(11) NOT NULL,
  `type` varchar(20) NOT NULL,
  `prix` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `idBonplan` int(11) NOT NULL,
  PRIMARY KEY (`ref_res`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reservationenseigne`
--

INSERT INTO `reservationenseigne` (`ref_res`, `date_reservation`, `nb_place`, `type`, `prix`, `idUser`, `idBonplan`) VALUES
(15, '2018-02-06', 55, 'evenement', 0, 0, 0),
(33, '2018-03-02', 55, 'evenement', 0, 0, 0),
(42, '2018-03-02', 44, 'hey', 44, 0, 0),
(43, '2018-03-02', 10, 'Evénement', 800, 8, 23),
(44, '2018-03-02', 5, 'Evénement', 400, 8, 23),
(45, '2018-03-02', 6, 'Evénement', 480, 8, 23),
(46, '2018-03-02', 7, 'Evénement', 560, 8, 23),
(47, '2018-03-02', 6, 'Evénement', 480, 8, 23),
(48, '2018-03-02', 2, 'Evénement', 160, 8, 23),
(49, '2018-03-02', 6, 'Evénement', 0, 8, 23),
(50, '2018-02-26', 10, 'Voyage', 120, 8, 22),
(51, '2018-02-22', 3, 'Voyage', 3000, 8, 17);

-- --------------------------------------------------------

--
-- Table structure for table `signalenseigne`
--

DROP TABLE IF EXISTS `signalenseigne`;
CREATE TABLE IF NOT EXISTS `signalenseigne` (
  `idSignal` int(11) NOT NULL AUTO_INCREMENT,
  `idEnseigne` int(11) NOT NULL,
  `idUtilisateur` int(11) NOT NULL,
  `nbrSignal` int(11) DEFAULT NULL,
  PRIMARY KEY (`idSignal`),
  KEY `signalenseigne_ibfk_1` (`idEnseigne`),
  KEY `signalenseigne_ibfk_2` (`idUtilisateur`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `signalenseigne`
--

INSERT INTO `signalenseigne` (`idSignal`, `idEnseigne`, `idUtilisateur`, `nbrSignal`) VALUES
(17, 33, 1, 1),
(18, 33, 8, 1);

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(32) NOT NULL,
  `email` varchar(64) NOT NULL,
  `motdp` varchar(32) NOT NULL,
  `nom` varchar(32) NOT NULL,
  `prenom` varchar(32) NOT NULL,
  `photo` varchar(256) NOT NULL,
  `telephone` varchar(8) NOT NULL,
  `ville` varchar(32) NOT NULL,
  `bio` text NOT NULL,
  `nbAbo` int(8) NOT NULL,
  `nbAmi` int(8) NOT NULL,
  `bloque` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `role`, `email`, `motdp`, `nom`, `prenom`, `photo`, `telephone`, `ville`, `bio`, `nbAbo`, `nbAmi`, `bloque`) VALUES
(1, 'Admin', 'admin', '21232f297a57a5a743894a0e4a801fc3', 'Ben Moussa', 'Ghaieth', 'urlPhoto', '93969702', 'Tunis', '', 0, 1, 0),
(4, 'Utilisateur', 'adam@beyer.com', '1d7c2923c1684726dc23d2901c4d8157', 'Beyer', 'Adam', 'C:\\Users\\ghaie\\Desktop\\3A\\S2\\PI-Dev\\PI-Dev\\src\\gui\\ressources\\adam_beyer.jpg', '22448394', 'Le Kef', '', 0, 1, 0),
(5, 'Propriétaire', 'amelie', '5b4ac833a90cb6fe84ca5672deb5fb0e', 'Lens', 'Amélie', 'C:\\Users\\ghaie\\Desktop\\3A\\S2\\PI-Dev\\PI-Dev\\src\\gui\\ressources\\amelie_lens.jpg', '23664133', 'Ariana', 'ACID!', 0, 1, 0),
(6, 'Agence de voyage', 'ben', '7fe4771c008a22eb763df47d19e2c6aa', 'Klock', 'Ben', 'C:\\Users\\ghaie\\Desktop\\3A\\S2\\PI-Dev\\PI-Dev\\src\\gui\\ressources\\ben_klock.jpg', '93050183', 'Nabeul', '', 0, 0, 0),
(8, 'Utilisateur', 'carl', 'a0df931e7a7f9b608c165504bde9b620', 'Cox', 'Carl', 'C:\\Users\\ghaie\\Desktop\\3A\\S2\\PI-Dev\\PI-Dev\\src\\gui\\ressources\\carl_cox.jpg', '95200475', 'Sousse', 'Oh yes! Oh yes!', 0, 5, 0),
(9, 'Utilisateur', 'charlotte@dewitte.com', 'a6008231fa64ff879ecb286a657b6a99', 'DeWitte', 'Charlotte', 'C:\\Users\\ghaie\\Desktop\\3A\\S2\\PI-Dev\\PI-Dev\\src\\gui\\ressources\\charlotte_dewitte.jpg', '51680332', 'Ariana', '', 0, 0, 0),
(10, 'Utilisateur', 'jamie@jones.com', '1c138fd52ddd771388a5b4c410a9603a', 'Jones', 'Jamie', 'C:\\Users\\ghaie\\Desktop\\3A\\S2\\PI-Dev\\PI-Dev\\src\\gui\\ressources\\jamie_jones.jpg', '28008317', 'Gabès', '', 0, 1, 0),
(11, 'Utilisateur', 'jeff@mills.fr', '166ee015c0e0934a8781e0c86a197c6e', 'Mills', 'Jeff', 'C:\\Users\\ghaie\\Desktop\\3A\\S2\\PI-Dev\\PI-Dev\\src\\gui\\ressources\\jeff_mills.jpg', '90548223', '	\r\nSfax', '', 0, 0, 0),
(12, 'Agence de voyage', 'laurent@garnier.com', '34a321664be49e31c2368f6f42798a98', 'Garnier', 'Laurent', 'C:\\Users\\ghaie\\Desktop\\3A\\S2\\PI-Dev\\PI-Dev\\src\\gui\\ressources\\laurent_garnier.jpg', '22744937', 'Gabès', '', 0, 3, 0),
(13, 'Utilisateur', 'loco@dice.tn', '4c193eb3ec2ce5f02b29eba38621bea1', 'Dice', 'Loco', 'C:\\Users\\ghaie\\Desktop\\3A\\S2\\PI-Dev\\PI-Dev\\src\\gui\\ressources\\loco_dice.jpg', '92448575', 'Le Kef', '', 0, 0, 0),
(14, 'Propriétaire', 'maceo@plex.com', '0b531a5846ac9461296ce74ece76d45d', 'Plex', 'Maceo', 'C:\\Users\\ghaie\\Desktop\\3A\\S2\\PI-Dev\\PI-Dev\\src\\gui\\ressources\\maceo_plex.jpg', '59318537', 'Sidi Bouzid', '', 0, 0, 0),
(15, 'Propriétaire', 'marcel@dettmann.fr', '24dde05168c24253ce9fec0fddd1e48d', 'Dettmann', 'Marcel', 'C:\\Users\\ghaie\\Desktop\\3A\\S2\\PI-Dev\\PI-Dev\\src\\gui\\ressources\\marcel_dettmann.jpg', '52847142', 'Tataouine', '', 0, 1, 0),
(16, 'Agence de voyage', 'marco@carola.tn', 'f5888d0bb58d611107e11f7cbc41c97a', 'Carola', 'Marco', 'C:\\Users\\ghaie\\Desktop\\3A\\S2\\PI-Dev\\PI-Dev\\src\\gui\\ressources\\marco_carola.jpg', '94671257', 'La Manouba', '', 0, 0, 0),
(17, 'Utilisateur', 'nina@kraviz.com', 'f2ceea1536ac1b8fed1a167a9c8bf04d', 'Kraviz', 'Nina', 'C:\\Users\\ghaie\\Desktop\\3A\\S2\\PI-Dev\\PI-Dev\\src\\gui\\ressources\\nina_kraviz.jpg', '28593756', 'Gabès', '', 0, 2, 0),
(18, 'Agence de voyage', 'ricardo@villalobos.com', '6720720054e9d24fbf6c20a831ff287e', 'Villalobos', 'Ricardo', 'C:\\Users\\ghaie\\Desktop\\3A\\S2\\PI-Dev\\PI-Dev\\src\\gui\\ressources\\ricardo_villalobos.jpg', '93562781', 'Ben Arous', '', 0, 1, 1),
(19, 'Propriétaire', 'richie@hawtin.com', 'e15806424fcd348214138bc43b2bb9e1', 'Hawtin', 'Richie', 'C:\\Users\\ghaie\\Desktop\\3A\\S2\\PI-Dev\\PI-Dev\\src\\gui\\ressources\\richie_hawtin.jpg', '91472997', 'Kasserine', '', 0, 0, 0),
(20, 'Utilisateur', 'sven@vath.tn', 'b732b209dbc45a50f95af8825f739fc5', 'Vath', 'Sven', 'C:\\Users\\ghaie\\Desktop\\3A\\S2\\PI-Dev\\PI-Dev\\src\\gui\\ressources\\sven_vath.jpg', '52891663', 'Sfax', '', 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `voyage`
--

DROP TABLE IF EXISTS `voyage`;
CREATE TABLE IF NOT EXISTS `voyage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_debut` varchar(50) NOT NULL,
  `date_fin` varchar(50) NOT NULL,
  `prix` float NOT NULL,
  `destination` varchar(20) NOT NULL,
  `description` varchar(500) NOT NULL,
  `image` varchar(200) NOT NULL,
  `nb_places` int(20) NOT NULL,
  `nb_dispo` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `voyage`
--

INSERT INTO `voyage` (`id`, `date_debut`, `date_fin`, `prix`, `destination`, `description`, `image`, `nb_places`, `nb_dispo`, `idUser`) VALUES
(17, '2018-02-22', '2018-02-28', 500, 'Paris', 'Mohamed', 'CLINTON.jpg', 12, 8, 0),
(21, '2018-02-26', '2018-03-02', 12555600, 'Berlin', 'kksksks', '1024px-KFC_logo.svg.png', 25324567, 25324567, 0),
(22, '2018-02-26', '2018-02-28', 12, 'Berlin', 'kqjqjqhq', '1024px-KFC_logo.svg.png', 25, 23, 0),
(23, '2018-03-01', '2018-03-11', 185, 'madrid', 'xcxjcwlxwkjc', '3.jpg', 52, 51, 0),
(24, '2018-02-27', '2018-02-28', 123, 'New York', 'ksksks', 'el marhouma.jpg', 166, 166, 0),
(25, '2018-02-26', '2018-03-02', 12555600, 'Berlin', 'kksksks', '1024px-KFC_logo.svg.png', 25324567, 25324567, 0),
(27, '2018-02-27', '2018-02-28', 3, 'Tokyo', 'er', 'Page de garde groupe 5.jpg', 3, 3, 0),
(28, '2018-02-28', '2018-03-04', 6, 'London', 'qfqsdqs', 'Page de garde groupe 5.jpg', 5, 5, 6),
(29, '2018-02-28', '2018-03-04', 6, 'London', 'qfqsdqs', 'Page de garde groupe 5.jpg', 5, 5, 6),
(30, '2018-02-26', '2018-02-28', 12, 'Berlin', 'kqjqjqhq', '1024px-KFC_logo.svg.png', 25, 25, 0),
(31, '2018-02-22', '2018-02-28', 500, 'Paris', 'Mohamed', 'CLINTON.jpg', 12, 12, 0);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `destination`
--
ALTER TABLE `destination`
  ADD CONSTRAINT `destination_ibfk_1` FOREIGN KEY (`id_voyage`) REFERENCES `voyage` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `noteenseigne`
--
ALTER TABLE `noteenseigne`
  ADD CONSTRAINT `noteenseigne_ibfk_1` FOREIGN KEY (`idEnseigne`) REFERENCES `enseigne` (`idEnseigne`) ON DELETE CASCADE;

--
-- Constraints for table `signalenseigne`
--
ALTER TABLE `signalenseigne`
  ADD CONSTRAINT `signalenseigne_ibfk_1` FOREIGN KEY (`idEnseigne`) REFERENCES `enseigne` (`idEnseigne`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
