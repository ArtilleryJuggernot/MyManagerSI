-- MariaDB dump 10.19-11.2.2-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: mmsi
-- ------------------------------------------------------
-- Server version	11.2.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Sequence structure for `hibernate_sequence`
--

CREATE DATABASE mmsi;
USE mmsi;

DROP SEQUENCE IF EXISTS `hibernate_sequence`;
CREATE SEQUENCE `hibernate_sequence` start with 1 minvalue 1 maxvalue 9223372036854775806 increment by 1 cache 1000 nocycle ENGINE=InnoDB;
SELECT SETVAL(`hibernate_sequence`, 1, 0);

--
-- Sequence structure for `next_val`
--

DROP SEQUENCE IF EXISTS `next_val`;
CREATE SEQUENCE `next_val` start with 1 minvalue 1 maxvalue 9223372036854775806 increment by 1 cache 1000 nocycle ENGINE=InnoDB;
SELECT SETVAL(`next_val`, 1, 0);

--
-- Table structure for table `catmat`
--

DROP TABLE IF EXISTS `catmat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catmat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Libelle` varchar(255) DEFAULT NULL,
  `entreprise_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_catégorie_entreprise` (`entreprise_id`),
  CONSTRAINT `fk_catégorie_entreprise` FOREIGN KEY (`entreprise_id`) REFERENCES `entreprise` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catmat`
--

LOCK TABLES `catmat` WRITE;
/*!40000 ALTER TABLE `catmat` DISABLE KEYS */;
INSERT INTO `catmat` VALUES
(1,'Ordinateur',16),
(2,'Imprimante',16),
(3,'Téléphone',16),
(13,'Patate',16);
/*!40000 ALTER TABLE `catmat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entreprise`
--

DROP TABLE IF EXISTS `entreprise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entreprise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) DEFAULT NULL,
  `DateCreation` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entreprise`
--

LOCK TABLES `entreprise` WRITE;
/*!40000 ALTER TABLE `entreprise` DISABLE KEYS */;
INSERT INTO `entreprise` VALUES
(16,'test','2024-01-20');
/*!40000 ALTER TABLE `entreprise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logs`
--

DROP TABLE IF EXISTS `logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logs` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Date` date DEFAULT NULL,
  `Utilisateur_ID` int(11) DEFAULT NULL,
  `Action` varchar(255) DEFAULT NULL,
  `Contenu` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `Utilisateur_ID` (`Utilisateur_ID`),
  CONSTRAINT `logs_ibfk_1` FOREIGN KEY (`Utilisateur_ID`) REFERENCES `utilisateur` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logs`
--

LOCK TABLES `logs` WRITE;
/*!40000 ALTER TABLE `logs` DISABLE KEYS */;
INSERT INTO `logs` VALUES
(1,'2024-01-31',1,'LOG SUCCESS','Utilisateur authentifié avec succès : test'),
(2,'2024-01-31',1,'LOG SUCCESS','Utilisateur authentifié avec succès : test'),
(3,'2024-01-31',1,'AJOUT MATERIEL','Nouveau matériel : Patate pour l\'entreprise test'),
(4,'2024-01-31',1,'LOG SUCCESS','Utilisateur authentifié avec succès : test'),
(5,'2024-01-31',1,'AJOUT STOCK','Nouveau stock :patate de paris pour l\'entreprise test sur le site Site Principal'),
(6,'2024-01-31',1,'LOG SUCCESS','Utilisateur authentifié avec succès : test'),
(7,'2024-01-31',1,'LOG SUCCESS','Utilisateur authentifié avec succès : test'),
(8,'2024-01-31',1,'LOG SUCCESS','Utilisateur authentifié avec succès : test'),
(9,'2024-01-31',1,'LOG SUCCESS','Utilisateur authentifié avec succès : test'),
(10,'2024-01-31',1,'LOG SUCCESS','Utilisateur authentifié avec succès : test'),
(11,'2024-01-31',1,'LOG SUCCESS','Utilisateur authentifié avec succès : test');
/*!40000 ALTER TABLE `logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matériel`
--

DROP TABLE IF EXISTS `matériel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `matériel` (
  `id` int(11) NOT NULL,
  `Libelle` varchar(255) DEFAULT NULL,
  `ID_categorie` int(11) DEFAULT NULL,
  `site_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ID_categorie` (`ID_categorie`),
  KEY `fk_site` (`site_id`),
  CONSTRAINT `fk_site` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`),
  CONSTRAINT `matériel_ibfk_1` FOREIGN KEY (`ID_categorie`) REFERENCES `catmat` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matériel`
--

LOCK TABLES `matériel` WRITE;
/*!40000 ALTER TABLE `matériel` DISABLE KEYS */;
INSERT INTO `matériel` VALUES
(1,'Ordinateur de Léo',1,15),
(3,'mint',2,15),
(4,'iPhone de Quentin',3,15),
(5,'test',1,15),
(6,'patate de paris',13,15);
/*!40000 ALTER TABLE `matériel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site`
--

DROP TABLE IF EXISTS `site`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `site` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Libelle` varchar(255) DEFAULT NULL,
  `ID_entreprise` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ID_entreprise` (`ID_entreprise`),
  CONSTRAINT `site_ibfk_1` FOREIGN KEY (`ID_entreprise`) REFERENCES `entreprise` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site`
--

LOCK TABLES `site` WRITE;
/*!40000 ALTER TABLE `site` DISABLE KEYS */;
INSERT INTO `site` VALUES
(15,'Site Principal',16),
(16,'Test site',16),
(18,'Lyon',16),
(20,'Marseile',16);
/*!40000 ALTER TABLE `site` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statut`
--

DROP TABLE IF EXISTS `statut`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `statut` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Libelle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statut`
--

LOCK TABLES `statut` WRITE;
/*!40000 ALTER TABLE `statut` DISABLE KEYS */;
INSERT INTO `statut` VALUES
(1,'Opérationnel'),
(2,'En panne'),
(3,'En maintenance');
/*!40000 ALTER TABLE `statut` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock` (
  `ID_matériel` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `ID_Entreprise` int(11) DEFAULT NULL,
  `Statut_id` int(11) DEFAULT NULL,
  `Site_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_matériel`),
  KEY `ID_Entreprise` (`ID_Entreprise`),
  KEY `Statut_id` (`Statut_id`),
  KEY `Site_id` (`Site_id`),
  CONSTRAINT `stock_ibfk_1` FOREIGN KEY (`ID_Entreprise`) REFERENCES `entreprise` (`ID`),
  CONSTRAINT `stock_ibfk_2` FOREIGN KEY (`Statut_id`) REFERENCES `statut` (`ID`),
  CONSTRAINT `stock_ibfk_3` FOREIGN KEY (`Site_id`) REFERENCES `site` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES
(1,'test description',16,1,15),
(3,'erreur panne',16,2,15),
(4,'Maj IOS',16,3,15),
(5,'test',16,1,15),
(6,'problème d\'UML',16,2,15);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `typecompte`
--

DROP TABLE IF EXISTS `typecompte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `typecompte` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Libelle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `typecompte`
--

LOCK TABLES `typecompte` WRITE;
/*!40000 ALTER TABLE `typecompte` DISABLE KEYS */;
INSERT INTO `typecompte` VALUES
(1,'Administrateur'),
(2,'Manager'),
(3,'Salarié');
/*!40000 ALTER TABLE `typecompte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utilisateur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT NULL,
  `last_co` timestamp NULL DEFAULT NULL,
  `entreprise_id` int(11) DEFAULT NULL,
  `Site_id` int(11) DEFAULT NULL,
  `Type_compteID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `entreprise_id` (`entreprise_id`),
  KEY `Site_id` (`Site_id`),
  KEY `Type_compteID` (`Type_compteID`),
  CONSTRAINT `utilisateur_ibfk_1` FOREIGN KEY (`entreprise_id`) REFERENCES `entreprise` (`ID`),
  CONSTRAINT `utilisateur_ibfk_2` FOREIGN KEY (`Site_id`) REFERENCES `site` (`ID`),
  CONSTRAINT `utilisateur_ibfk_3` FOREIGN KEY (`Type_compteID`) REFERENCES `typecompte` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilisateur`
--

LOCK TABLES `utilisateur` WRITE;
/*!40000 ALTER TABLE `utilisateur` DISABLE KEYS */;
INSERT INTO `utilisateur` VALUES
(1,'Fondateur du système','test','9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08','2024-01-19 23:00:00','2024-01-21 23:00:00',16,15,1),
(5,'Mint','mint@mint.fr','1f30014ac280f540d342a9d6d3e06aa9547d7bbfda4413cc677ff1b3dd186972',NULL,NULL,16,18,2);
/*!40000 ALTER TABLE `utilisateur` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-19 20:58:35


CREATE USER 'mmsi'@'localhost' IDENTIFIED BY 'azerty123!!!';
GRANT SELECT, INSERT, UPDATE, DELETE ON mmsi.* TO 'mmsi'@'localhost';
FLUSH PRIVILEGES;

