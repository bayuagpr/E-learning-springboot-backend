﻿--
-- Script was generated by Devart dbForge Studio 2019 for MySQL, Version 8.1.45.0
-- Product home page: http://www.devart.com/dbforge/mysql/studio
-- Script date 10/14/2019 12:13:03 AM
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
-- Set default database
--
USE elearningdev;

--
-- Drop table `enrollment`
--
DROP TABLE IF EXISTS enrollment;

--
-- Drop table `hasil`
--
DROP TABLE IF EXISTS hasil;

--
-- Drop table `mahasiswa`
--
DROP TABLE IF EXISTS mahasiswa;

--
-- Drop table `jurusan`
--
DROP TABLE IF EXISTS jurusan;

--
-- Drop table `fakultas`
--
DROP TABLE IF EXISTS fakultas;

--
-- Drop table `materi`
--
DROP TABLE IF EXISTS materi;

--
-- Drop table `soal`
--
DROP TABLE IF EXISTS soal;

--
-- Drop table `kelas`
--
DROP TABLE IF EXISTS kelas;

--
-- Drop table `mata_kuliah`
--
DROP TABLE IF EXISTS mata_kuliah;

--
-- Drop table `dosen`
--
DROP TABLE IF EXISTS dosen;

--
-- Drop table `user`
--
DROP TABLE IF EXISTS user;

--
-- Drop table `role`
--
DROP TABLE IF EXISTS role;

--
-- Set default database
--
USE elearningdev;

--
-- Create table `role`
--
CREATE TABLE role (
  id int(11) NOT NULL AUTO_INCREMENT,
  nama enum ('Dosen', 'Mahasiswa') DEFAULT NULL,
  deskripsi varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
AUTO_INCREMENT = 3,
AVG_ROW_LENGTH = 8192,
CHARACTER SET latin1,
COLLATE latin1_swedish_ci;

--
-- Create table `user`
--
CREATE TABLE user (
  id varchar(255) NOT NULL,
  email varchar(50) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  id_role int(11) DEFAULT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
AVG_ROW_LENGTH = 364,
CHARACTER SET latin1,
COLLATE latin1_swedish_ci;

--
-- Create foreign key
--
ALTER TABLE user
ADD CONSTRAINT FK_user_id_role FOREIGN KEY (id_role)
REFERENCES role (id) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Create table `dosen`
--
CREATE TABLE dosen (
  nidn varchar(255) NOT NULL,
  nama varchar(255) DEFAULT NULL,
  `tempat lahir` varchar(255) DEFAULT NULL,
  `tanggal lahir` date DEFAULT NULL,
  alamat varchar(255) DEFAULT NULL,
  PRIMARY KEY (nidn)
)
ENGINE = INNODB,
AVG_ROW_LENGTH = 2730,
CHARACTER SET latin1,
COLLATE latin1_swedish_ci;

--
-- Create foreign key
--
ALTER TABLE dosen
ADD CONSTRAINT FK_dosen_id_user FOREIGN KEY (nidn)
REFERENCES user (id) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Create table `mata_kuliah`
--
CREATE TABLE mata_kuliah (
  id varchar(255) NOT NULL,
  nama varchar(255) DEFAULT NULL,
  deskripsi varchar(255) DEFAULT NULL,
  tipe enum ('Wajib', 'Pilihan') DEFAULT NULL,
  term enum ('Gasal', 'Genap') DEFAULT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
AVG_ROW_LENGTH = 8192,
CHARACTER SET latin1,
COLLATE latin1_swedish_ci;

--
-- Create table `kelas`
--
CREATE TABLE kelas (
  id varchar(255) NOT NULL,
  nama varchar(255) DEFAULT NULL,
  id_matkul varchar(255) DEFAULT NULL,
  id_dosen varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
AVG_ROW_LENGTH = 1489,
CHARACTER SET latin1,
COLLATE latin1_swedish_ci;

--
-- Create foreign key
--
ALTER TABLE kelas
ADD CONSTRAINT FK_kelas_id_dosen FOREIGN KEY (id_dosen)
REFERENCES dosen (nidn) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Create foreign key
--
ALTER TABLE kelas
ADD CONSTRAINT FK_kelas_id_matkul FOREIGN KEY (id_matkul)
REFERENCES mata_kuliah (id) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Create table `soal`
--
CREATE TABLE soal (
  id varchar(255) NOT NULL,
  judul varchar(255) DEFAULT NULL,
  deskripsi varchar(255) DEFAULT NULL,
  attachment varchar(255) DEFAULT NULL,
  tipe enum ('Kuis', 'Tugas') DEFAULT NULL,
  due_date timestamp NULL DEFAULT '0000-00-00 00:00:00',
  creation_date timestamp NULL DEFAULT '0000-00-00 00:00:00',
  id_class varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
AVG_ROW_LENGTH = 1170,
CHARACTER SET latin1,
COLLATE latin1_swedish_ci;

--
-- Create foreign key
--
ALTER TABLE soal
ADD CONSTRAINT FK_soal_id_class FOREIGN KEY (id_class)
REFERENCES kelas (id) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Create table `materi`
--
CREATE TABLE materi (
  id varchar(255) NOT NULL,
  judul varchar(255) DEFAULT NULL,
  deskripsi varchar(255) DEFAULT NULL,
  attachment varchar(255) DEFAULT NULL,
  creation_date timestamp NULL DEFAULT '0000-00-00 00:00:00',
  id_class varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
AVG_ROW_LENGTH = 1489,
CHARACTER SET latin1,
COLLATE latin1_swedish_ci;

--
-- Create foreign key
--
ALTER TABLE materi
ADD CONSTRAINT FK_materi_id_class FOREIGN KEY (id_class)
REFERENCES kelas (id) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Create table `fakultas`
--
CREATE TABLE fakultas (
  id varchar(255) NOT NULL,
  nama varchar(255) DEFAULT NULL,
  deskripsi varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
AVG_ROW_LENGTH = 16384,
CHARACTER SET latin1,
COLLATE latin1_swedish_ci;

--
-- Create table `jurusan`
--
CREATE TABLE jurusan (
  id varchar(255) NOT NULL,
  nama varchar(255) DEFAULT NULL,
  id_fakultas varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
CHARACTER SET latin1,
COLLATE latin1_swedish_ci;

--
-- Create foreign key
--
ALTER TABLE jurusan
ADD CONSTRAINT FK_jurusan_id_fakultas FOREIGN KEY (id_fakultas)
REFERENCES fakultas (id) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Create table `mahasiswa`
--
CREATE TABLE mahasiswa (
  nim varchar(255) NOT NULL,
  nama varchar(255) DEFAULT NULL,
  tempat_lahir varchar(255) DEFAULT NULL,
  tanggal_lahir date DEFAULT NULL,
  alamat varchar(255) DEFAULT NULL,
  id_jurusan varchar(255) DEFAULT NULL,
  PRIMARY KEY (nim)
)
ENGINE = INNODB,
AVG_ROW_LENGTH = 3276,
CHARACTER SET latin1,
COLLATE latin1_swedish_ci;

--
-- Create foreign key
--
ALTER TABLE mahasiswa
ADD CONSTRAINT FK_mahasiswa_id_jurusan FOREIGN KEY (id_jurusan)
REFERENCES jurusan (id) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Create foreign key
--
ALTER TABLE mahasiswa
ADD CONSTRAINT FK_mahasiswa_nim FOREIGN KEY (nim)
REFERENCES user (id) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Create table `hasil`
--
CREATE TABLE hasil (
  id varchar(255) NOT NULL,
  isi_jawaban varchar(255) DEFAULT NULL,
  attachment varchar(255) DEFAULT NULL,
  nilai varchar(255) DEFAULT NULL,
  komentar varchar(255) DEFAULT NULL,
  status enum ('Early', 'Late') DEFAULT NULL,
  last_modified timestamp NULL DEFAULT '0000-00-00 00:00:00',
  ternilai tinyint(1) DEFAULT NULL,
  id_soal varchar(255) DEFAULT NULL,
  id_mahasiswa varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
AVG_ROW_LENGTH = 3276,
CHARACTER SET latin1,
COLLATE latin1_swedish_ci;

--
-- Create foreign key
--
ALTER TABLE hasil
ADD CONSTRAINT FK_hasil_id_mahasiswa FOREIGN KEY (id_mahasiswa)
REFERENCES mahasiswa (nim) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Create foreign key
--
ALTER TABLE hasil
ADD CONSTRAINT FK_hasil_id_soal FOREIGN KEY (id_soal)
REFERENCES soal (id) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Create table `enrollment`
--
CREATE TABLE enrollment (
  id varchar(255) NOT NULL,
  disetujui tinyint(1) DEFAULT 0,
  id_mahasiswa varchar(255) DEFAULT NULL,
  id_class varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
AVG_ROW_LENGTH = 8192,
CHARACTER SET latin1,
COLLATE latin1_swedish_ci;

--
-- Create foreign key
--
ALTER TABLE enrollment
ADD CONSTRAINT FK_enrollment_id_class FOREIGN KEY (id_class)
REFERENCES kelas (id) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Create foreign key
--
ALTER TABLE enrollment
ADD CONSTRAINT FK_enrollment_id_mahasiswa FOREIGN KEY (id_mahasiswa)
REFERENCES mahasiswa (nim) ON DELETE SET NULL ON UPDATE CASCADE;

-- 
-- Restore previous SQL mode
-- 
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;

-- 
-- Enable foreign keys
-- 
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;

DELETE FROM hasil;
DELETE FROM materi;
DELETE FROM soal;