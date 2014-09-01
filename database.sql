-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 01 Wrz 2014, 21:34
-- Server version: 5.6.17-log
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `firma_magazyn`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `firmy`
--

CREATE TABLE IF NOT EXISTS `firmy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `adres` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=76 ;

--
-- Zrzut danych tabeli `firmy`
--

INSERT INTO `firmy` (`id`, `nazwa`, `adres`) VALUES
(61, 'But-met', 'ul. Chopina BIałystok'),
(62, 'Monter', 'ul. Biała Warszawa'),
(63, 'Wszystko dla domu', 'ul. Bochaterów 22/11'),
(72, 'Obi', 'Gdańsk ul. Polna 12'),
(73, 'Castorama', 'Gdańsk ul. Polna 19a'),
(74, 'IKEA', 'Gdańsk ul. Polna 1a'),
(75, 'Rem-Bud', 'Wrocław ul. Dobra 21');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `kategorie`
--

CREATE TABLE IF NOT EXISTS `kategorie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=72 ;

--
-- Zrzut danych tabeli `kategorie`
--

INSERT INTO `kategorie` (`id`, `name`) VALUES
(66, 'Toaleta'),
(67, 'Sypialnia'),
(69, 'Kuchnia'),
(70, 'Ogród'),
(71, 'Salon');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `klienci`
--

CREATE TABLE IF NOT EXISTS `klienci` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `imie` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `nazwisko` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `adres` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=5 ;

--
-- Zrzut danych tabeli `klienci`
--

INSERT INTO `klienci` (`id`, `imie`, `nazwisko`, `adres`) VALUES
(2, 'Łukasz', 'Ogan', 'Adres'),
(4, 'Jan', 'Kowalski', 'Toruń ul. Ogrodowa 2');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `produkty`
--

CREATE TABLE IF NOT EXISTS `produkty` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_kategoria` int(11) NOT NULL,
  `id_firmy` int(11) NOT NULL,
  `nazwa` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_kategoria_idx` (`id_kategoria`),
  KEY `id_firmy` (`id_firmy`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=43 ;

--
-- Zrzut danych tabeli `produkty`
--

INSERT INTO `produkty` (`id`, `id_kategoria`, `id_firmy`, `nazwa`) VALUES
(31, 66, 61, 'Lustro'),
(34, 71, 63, 'Łóżko'),
(37, 70, 73, 'Lampka'),
(40, 70, 73, 'Płot'),
(42, 69, 63, 'Szklanki');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zamowienia`
--

CREATE TABLE IF NOT EXISTS `zamowienia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data` datetime DEFAULT NULL,
  `id_klienta` int(11) DEFAULT NULL,
  `id_produktu` int(11) DEFAULT NULL,
  `realizacja` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `id_produktu_idx` (`id_produktu`),
  KEY `id_klienta_idx` (`id_klienta`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=6 ;

--
-- Zrzut danych tabeli `zamowienia`
--

INSERT INTO `zamowienia` (`id`, `data`, `id_klienta`, `id_produktu`, `realizacja`) VALUES
(1, '2014-09-09 00:00:00', 2, 31, 1),
(3, '2014-09-18 10:26:26', 4, 31, 1),
(4, '2014-09-09 07:35:38', 4, 31, 0),
(5, '2014-09-05 13:22:28', 2, 31, 0);

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `produkty`
--
ALTER TABLE `produkty`
  ADD CONSTRAINT `id_firmy` FOREIGN KEY (`id_firmy`) REFERENCES `firmy` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `id_kategoria` FOREIGN KEY (`id_kategoria`) REFERENCES `kategorie` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ograniczenia dla tabeli `zamowienia`
--
ALTER TABLE `zamowienia`
  ADD CONSTRAINT `id_klienta` FOREIGN KEY (`id_klienta`) REFERENCES `klienci` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `id_produktu` FOREIGN KEY (`id_produktu`) REFERENCES `produkty` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
