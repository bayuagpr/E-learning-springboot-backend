﻿--
-- Script was generated by Devart dbForge Studio 2019 for MySQL, Version 8.1.45.0
-- Product home page: http://www.devart.com/dbforge/mysql/studio
-- Script date 10/15/2019 11:57:14 AM
-- Server version: 5.5.5-10.1.28-MariaDB
-- Client version: 4.1
--

-- 
-- Disable foreign keys
-- 
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;

-- 
-- Set SQL mode
-- 
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 
-- Set character set the client will use to send SQL statements to the server
--
SET NAMES 'utf8';

-- 
-- Dumping data for table fakultas
--
INSERT INTO fakultas VALUES
('FAK1123', 'Sekolah Vokasi', NULL);

-- 
-- Dumping data for table role
--
INSERT INTO role VALUES
(1, 'Mahasiswa', NULL),
(2, 'Dosen', NULL);

-- 
-- Dumping data for table mata_kuliah
--
INSERT INTO mata_kuliah VALUES
('VR09911', 'BISNIS PLANNING', NULL, 'Pilihan', 'Gasal'),
('VR0996661', 'BISNIS LANANG', 'terbaik sepanjang masa', 'Pilihan', 'Gasal'),
('VR412', 'MATEMATIKA TEKNIK', NULL, 'Wajib', 'Genap');

-- 
-- Dumping data for table jurusan
--
INSERT INTO jurusan VALUES
('JUR1233', 'Teknik Listrik', 'FAK1123');

-- 
-- Restore previous SQL mode
-- 
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;

-- 
-- Enable foreign keys
-- 
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;