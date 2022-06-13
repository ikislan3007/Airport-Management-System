-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: airport
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `aircraft`
--

DROP TABLE IF EXISTS `aircraft`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aircraft` (
  `id` bigint NOT NULL,
  `capacity` int NOT NULL,
  `last_service_date` datetime(6) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `registration_number` varchar(6) DEFAULT NULL,
  `release_date` datetime(6) DEFAULT NULL,
  `airline_id` bigint DEFAULT NULL,
  `crew_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_oo8devcdr4dgv4wow2q4dwhn9` (`registration_number`),
  KEY `FKjlf3erek6x7moh3fya657mukb` (`airline_id`),
  KEY `FKe6n3p95nonx1btgbydlv1ockb` (`crew_id`),
  CONSTRAINT `FKe6n3p95nonx1btgbydlv1ockb` FOREIGN KEY (`crew_id`) REFERENCES `crew` (`id`),
  CONSTRAINT `FKjlf3erek6x7moh3fya657mukb` FOREIGN KEY (`airline_id`) REFERENCES `airline` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aircraft`
--

LOCK TABLES `aircraft` WRITE;
/*!40000 ALTER TABLE `aircraft` DISABLE KEYS */;
INSERT INTO `aircraft` VALUES (10,120,'2020-07-15 10:00:00.000000','Airbus A220.','N753IK','2016-07-15 10:00:00.000000',4,15),(29,120,'2020-07-15 10:00:00.000000','Airbus A220.','N753SK','2016-07-15 10:00:00.000000',4,15),(31,120,'2020-07-15 10:00:00.000000','Airbus A220.','N75TSK','2016-07-15 10:00:00.000000',21,47),(34,120,'2020-07-15 10:00:00.000000','Airbus A220.','N75TSP','2016-07-15 10:00:00.000000',23,48);
/*!40000 ALTER TABLE `aircraft` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airline`
--

DROP TABLE IF EXISTS `airline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `airline` (
  `id` bigint NOT NULL,
  `iban` varchar(255) DEFAULT NULL,
  `insurance_сompany` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `web_site` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_e8ddci9cyv26p143tumal79j2` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airline`
--

LOCK TABLES `airline` WRITE;
/*!40000 ALTER TABLE `airline` DISABLE KEYS */;
INSERT INTO `airline` VALUES (4,'GB33BUKB2020155555533','Allianz','Ryanair','https://www.ryanair.com'),(21,'GB33BUKB2020155555533','Allianz','Turkish Airlines','https://www.turkishairlines.com/'),(23,'Us33BUKB2020155555533','Allianz','American Airlines','https://www.aa.com/homePage.do?locale=en_US/');
/*!40000 ALTER TABLE `airline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airport`
--

DROP TABLE IF EXISTS `airport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `airport` (
  `id` bigint NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `code` varchar(4) DEFAULT NULL,
  `disabled` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_kpeoje7ewxy99k1wt4cmttjgd` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airport`
--

LOCK TABLES `airport` WRITE;
/*!40000 ALTER TABLE `airport` DISABLE KEYS */;
INSERT INTO `airport` VALUES (18,' Boryspil\', Kyiv Oblast, Украина','IEV',_binary '\0','Boryspil International Airport','+380877644503'),(19,' Żwirki i Wigury','WAW',_binary '\0','Warsaw International Airport','+480877644503'),(20,'булевард „Христофор Колумб“ 1','SOF',_binary '\0','Sofia Airport','+359877644503');
/*!40000 ALTER TABLE `airport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airport_airline`
--

DROP TABLE IF EXISTS `airport_airline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `airport_airline` (
  `airport_id` bigint NOT NULL,
  `airline_id` bigint NOT NULL,
  PRIMARY KEY (`airport_id`,`airline_id`),
  KEY `FK3efpk9jivqk1kt65owqd4o6ys` (`airline_id`),
  CONSTRAINT `FK3efpk9jivqk1kt65owqd4o6ys` FOREIGN KEY (`airline_id`) REFERENCES `airline` (`id`),
  CONSTRAINT `FKmp5m4xp6wygj5e1xrhn8n9ud` FOREIGN KEY (`airport_id`) REFERENCES `airport` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airport_airline`
--

LOCK TABLES `airport_airline` WRITE;
/*!40000 ALTER TABLE `airport_airline` DISABLE KEYS */;
INSERT INTO `airport_airline` VALUES (18,4),(19,4),(20,4),(18,21),(19,21),(20,21),(18,23),(19,23);
/*!40000 ALTER TABLE `airport_airline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `crew`
--

DROP TABLE IF EXISTS `crew`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `crew` (
  `id` bigint NOT NULL,
  `unique_crew_identifier_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_tfwm7tynl6x4mrslerg1hmn7x` (`unique_crew_identifier_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crew`
--

LOCK TABLES `crew` WRITE;
/*!40000 ALTER TABLE `crew` DISABLE KEYS */;
INSERT INTO `crew` VALUES (15,'c12567'),(47,'c14567'),(48,'c14997');
/*!40000 ALTER TABLE `crew` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `crew_member`
--

DROP TABLE IF EXISTS `crew_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `crew_member` (
  `id` bigint NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `hiring_date` datetime(6) DEFAULT NULL,
  `job_title` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) NOT NULL,
  `salary` double NOT NULL,
  `unique_identifier_number` varchar(255) DEFAULT NULL,
  `airline_id` bigint DEFAULT NULL,
  `crew_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_g2kiv5fychaxvaag3p2cvlpgu` (`unique_identifier_number`),
  KEY `FK4p9cc8bkhgx0ujrbjr7ex3dp8` (`airline_id`),
  KEY `FKesrnjlu0s2tt9reqrkobvjgha` (`crew_id`),
  CONSTRAINT `FK4p9cc8bkhgx0ujrbjr7ex3dp8` FOREIGN KEY (`airline_id`) REFERENCES `airline` (`id`),
  CONSTRAINT `FKesrnjlu0s2tt9reqrkobvjgha` FOREIGN KEY (`crew_id`) REFERENCES `crew` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crew_member`
--

LOCK TABLES `crew_member` WRITE;
/*!40000 ALTER TABLE `crew_member` DISABLE KEYS */;
INSERT INTO `crew_member` VALUES (12,'irinaKislan@fmail.com','Irina','2022-08-08 09:00:00.000000','Board attendant','Kisaln','+359877644503',3000,'9907301111',4,15),(40,'andoninn@fmail.com','Maxim','2022-08-08 09:00:00.000000','Pilot','Andonin','+359877644503',3000,'9907302111',21,47),(42,'andonina@fmail.com','Irina','2022-08-08 09:00:00.000000','Board-Attendant','Andonina','+359877644503',3000,'9907302211',21,47),(44,'petrova@fmail.com','Alex','2022-08-08 09:00:00.000000','Board-Attendant','Petrova','+359877644503',3000,'9907302221',23,48),(45,'petrova@fmail.com','Inna','2022-08-08 09:00:00.000000','Pilot','Petrova','+359877644503',5000,'9907302222',23,48);
/*!40000 ALTER TABLE `crew_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight`
--

DROP TABLE IF EXISTS `flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flight` (
  `id` bigint NOT NULL,
  `arr_time` datetime(6) DEFAULT NULL,
  `dep_time` datetime(6) DEFAULT NULL,
  `flight_number` varchar(255) DEFAULT NULL,
  `aircraft_id` bigint DEFAULT NULL,
  `airline_id` bigint DEFAULT NULL,
  `dept_airport_id` bigint DEFAULT NULL,
  `dest_airport_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_aucisqx7arn3fi6eyjmsvqdb3` (`flight_number`),
  KEY `FKmofq89ullrd4qk1hllnyf8pn5` (`aircraft_id`),
  KEY `FK37wfh52g7g91rllg104gfq3yv` (`airline_id`),
  KEY `FK67tuuub5vnvrnm4pju9p7kp5q` (`dept_airport_id`),
  KEY `FKl14vudryha9kqbxkh3c9wols0` (`dest_airport_id`),
  CONSTRAINT `FK37wfh52g7g91rllg104gfq3yv` FOREIGN KEY (`airline_id`) REFERENCES `airline` (`id`),
  CONSTRAINT `FK67tuuub5vnvrnm4pju9p7kp5q` FOREIGN KEY (`dept_airport_id`) REFERENCES `airport` (`id`),
  CONSTRAINT `FKl14vudryha9kqbxkh3c9wols0` FOREIGN KEY (`dest_airport_id`) REFERENCES `airport` (`id`),
  CONSTRAINT `FKmofq89ullrd4qk1hllnyf8pn5` FOREIGN KEY (`aircraft_id`) REFERENCES `aircraft` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight`
--

LOCK TABLES `flight` WRITE;
/*!40000 ALTER TABLE `flight` DISABLE KEYS */;
INSERT INTO `flight` VALUES (24,'2022-07-30 12:00:00.000000','2022-07-30 10:00:00.000000','S12234',10,4,18,19),(26,'2022-07-30 12:00:00.000000','2022-07-30 10:00:00.000000','S15234',34,23,18,19),(28,'2022-07-30 12:00:00.000000','2022-07-30 10:00:00.000000','S15937',31,21,18,19);
/*!40000 ALTER TABLE `flight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (49);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passenger`
--

DROP TABLE IF EXISTS `passenger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `passenger` (
  `id` bigint NOT NULL,
  `birth_date` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) NOT NULL,
  `unique_identifier_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_898wg8ggh5bu1n8qxybuxx562` (`unique_identifier_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passenger`
--

LOCK TABLES `passenger` WRITE;
/*!40000 ALTER TABLE `passenger` DISABLE KEYS */;
INSERT INTO `passenger` VALUES (11,'2001-06-08 03:00:00.000000','irinakislan@gmail.com','Irina','Kislan','+359877644503','9907301111'),(36,'2001-06-08 03:00:00.000000','annaIvanova@gmail.com','Anna','Ivanova','+359877644502','9907302111'),(37,'2001-06-08 03:00:00.000000','petrPetrov@gmail.com','Petr','Petrova','+359877644503','9907302211'),(38,'2001-06-08 03:00:00.000000','mariqPetrich@gmail.com','Mariq','Petrich','+357877644293','9907302221');
/*!40000 ALTER TABLE `passenger` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passenger_flight`
--

DROP TABLE IF EXISTS `passenger_flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `passenger_flight` (
  `passenger_id` bigint NOT NULL,
  `flight_id` bigint NOT NULL,
  PRIMARY KEY (`passenger_id`,`flight_id`),
  KEY `FKftd1902u8wfmg8x8guyb7oatg` (`flight_id`),
  CONSTRAINT `FKftd1902u8wfmg8x8guyb7oatg` FOREIGN KEY (`flight_id`) REFERENCES `flight` (`id`),
  CONSTRAINT `FKg1971fdu5o55kc1nwvkpg3gov` FOREIGN KEY (`passenger_id`) REFERENCES `passenger` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passenger_flight`
--

LOCK TABLES `passenger_flight` WRITE;
/*!40000 ALTER TABLE `passenger_flight` DISABLE KEYS */;
INSERT INTO `passenger_flight` VALUES (11,24),(37,24),(11,26),(36,26),(36,28),(37,28);
/*!40000 ALTER TABLE `passenger_flight` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-14  0:09:12
