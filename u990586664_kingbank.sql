-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1:3306
-- Üretim Zamanı: 14 Oca 2022, 16:26:32
-- Sunucu sürümü: 10.5.12-MariaDB-cll-lve
-- PHP Sürümü: 7.2.34

--
-- Veritabanı: `u990586664_kingbank`
--
CREATE DATABASE IF NOT EXISTS `u990586664_kingbank` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `u990586664_kingbank`;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `actions`
--

DROP TABLE IF EXISTS `actions`;
CREATE TABLE IF NOT EXISTS `actions` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `lastAction` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `lastWrong` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Tablo döküm verisi `actions`
--

INSERT INTO `actions` (`Id`, `email`, `lastAction`, `lastWrong`) VALUES
(40, 'admin@gmail.com', 'Fri Jan 14 18:52:16 GMT+03:00 2022', NULL);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `cards`
--

DROP TABLE IF EXISTS `cards`;
CREATE TABLE IF NOT EXISTS `cards` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `card_number` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `discard_date` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cvc` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `creation_time` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `limits` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Tablo döküm verisi `cards`
--

INSERT INTO `cards` (`Id`, `email`, `name`, `card_number`, `discard_date`, `cvc`, `creation_time`, `limits`) VALUES
(46, 'admin@gmail.com', 'Admin Admin', '1757 6399 7455 3888', '6/27', '392', '1/22', '2000');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `money`
--

DROP TABLE IF EXISTS `money`;
CREATE TABLE IF NOT EXISTS `money` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `iban` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `SendMoney` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `commentt` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Date` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Tablo döküm verisi `money`
--

INSERT INTO `money` (`Id`, `email`, `iban`, `SendMoney`, `commentt`, `Date`) VALUES
(71, 'admin@gmail.com', 'IBN1565214862', '2000', 'money', 'Fri Jan 14 18:44:59 GMT+03:00 2022'),
(72, 'admin@gmail.com', 'IBN1565214862', '500', 'kingbank', 'Fri Jan 14 18:46:20 GMT+03:00 2022');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `money2`
--

DROP TABLE IF EXISTS `money2`;
CREATE TABLE IF NOT EXISTS `money2` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `iban` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `GetMoney` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `commentt` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Date` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Tablo döküm verisi `money2`
--

INSERT INTO `money2` (`Id`, `email`, `iban`, `GetMoney`, `commentt`, `Date`) VALUES
(69, 'admin@gmail.com', 'IBN1565214862', '2000', 'money', 'Fri Jan 14 18:44:59 GMT+03:00 2022'),
(70, 'admin@gmail.com', 'IBN1565214862', '500', 'kingbank', 'Fri Jan 14 18:46:20 GMT+03:00 2022');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `support`
--

DROP TABLE IF EXISTS `support`;
CREATE TABLE IF NOT EXISTS `support` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `message` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Tablo döküm verisi `support`
--

INSERT INTO `support` (`Id`, `email`, `message`) VALUES
(11, 'admin@gmail.com', 'Hello'),
(12, 'admin@gmail.com', 'hi');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Iban` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `resetCode` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `balance` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Tablo döküm verisi `user`
--

INSERT INTO `user` (`Id`, `email`, `password`, `Iban`, `resetCode`, `balance`) VALUES
(1, 'admin@gmail.com', '12345', 'IBN10339166910', '4224', '11611148');
COMMIT;

