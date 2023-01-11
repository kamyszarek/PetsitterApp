-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 11 Sty 2023, 11:54
-- Wersja serwera: 10.4.20-MariaDB
-- Wersja PHP: 8.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `petsitter`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `address`
--

CREATE TABLE `address` (
  `id` bigint(20) NOT NULL,
  `country` varchar(32) DEFAULT NULL,
  `state` varchar(32) DEFAULT NULL,
  `city` varchar(32) DEFAULT NULL,
  `street` varchar(32) DEFAULT NULL,
  `house_no` varchar(32) DEFAULT NULL,
  `flat_no` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `address`
--

INSERT INTO `address` (`id`, `country`, `state`, `city`, `street`, `house_no`, `flat_no`) VALUES
(1, 'Polska', 'małopolskie', 'Tarnów', 'Kwiatowa', '3', '1'),
(2, 'Polska', 'wielkopolskie', 'Poznań', 'Szkolna', '1', '11'),
(29, 'a', 'a', 'a', 'a', '1', '1'),
(30, 'Polska', 'wielkopolskie', 'Poznań', 'Szkolna', '1', '2'),
(31, 'Polska', 'małopolskie', 'Kraków', 'Miodowa', '12', '3'),
(32, 'Polska', 'małopolskie', 'Tarnów', '3 maja', '3', '22'),
(33, 'Polska', 'małopolskie', 'Kraków', 'Stella', '43', '12'),
(35, 'Polska', 'małopolskie', 'Wietrzychowice', 'Wola Rogowska', '106', '1');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `confirm_new_account`
--

CREATE TABLE `confirm_new_account` (
  `id` bigint(20) NOT NULL,
  `code` int(11) DEFAULT NULL,
  `u_email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `confirm_new_account`
--

INSERT INTO `confirm_new_account` (`id`, `code`, `u_email`) VALUES
(4, 611833, 'aaa'),
(5, 496621, 'ann99@gmail.com'),
(6, 373728, 'arek9907@gmail.com');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(36);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `reservation`
--

CREATE TABLE `reservation` (
  `id` bigint(20) NOT NULL,
  `end_date` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `owner_id` bigint(20) DEFAULT NULL,
  `petsitter_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `reservation`
--

INSERT INTO `reservation` (`id`, `end_date`, `start_date`, `owner_id`, `petsitter_id`) VALUES
(8, '2022-11-23 10:00:00', '2022-11-22 10:00:00', 30, 33);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `reservation1`
--

CREATE TABLE `reservation1` (
  `id` bigint(20) NOT NULL,
  `end_date` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `reservation1`
--

INSERT INTO `reservation1` (`id`, `end_date`, `start_date`) VALUES
(1, '2022-09-20 10:10:04', '2022-09-20 10:10:04'),
(2, '2022-11-13 11:30:00', '2022-09-20 10:46:17');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `roles`
--

CREATE TABLE `roles` (
  `role_id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `roles`
--

INSERT INTO `roles` (`role_id`, `name`) VALUES
(1, 'ADMIN'),
(2, 'PETSITTER'),
(3, 'OWNER');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user`
--

CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `lastname` varchar(32) NOT NULL,
  `login` varchar(32) NOT NULL,
  `password` varchar(256) DEFAULT NULL,
  `email` varchar(32) NOT NULL,
  `address_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `user`
--

INSERT INTO `user` (`user_id`, `enabled`, `name`, `lastname`, `login`, `password`, `email`, `address_id`) VALUES
(1, b'1', 'Arkadiusz', 'Kamysz', 'arek99', '$2a$12$q0tqoIJ.QvJuxxFCY9buOuu0Rh/XHdHjEpbND//VJTbbao3Hq1qHm', 'arek9905@gmail.com', 1),
(2, b'1', 'Jan', 'Kowalski', 'jan99', '$2a$10$U3Z.44FdC4g/E4.yLAn90ukc1SDykeCK7w/98VAfgPCfIrNDHGVTm', 'arek9906@gmail.com', 2),
(29, b'1', 'a', 'a', 'jan00', '$2a$10$CDggt1EfIBE1Nvbnd.dvL.wiMEbNtSJKiP3/ujFuPlnecMMHUCdSy', 'arek99071@gmail.com', 29),
(30, b'1', 'Adam', 'Kowalski', 'adam99', '$2a$10$GIWiKel4W4iysQ3TX1j7EOV9U0M2ooJHmt8kn9TGQstkfnIFYYtAq', 'arek9906@gmail.com', 30),
(31, b'1', 'Mariusz', 'Wiśniewski', 'mar99', '$2a$10$NTK2Xb1bDQy70J.sejCSm.U6ZS3EShM7JP.VXYuUDV23au3BiphQO', 'tekstowy.arek@gmail.com', 31),
(32, b'1', 'Marek', 'Kania', 'marek99', '$2a$10$g8g3H8YiAmFsZMWpKmI9ceXPJxIZRno9zGXMv0m5m.z6jBeDAU4bW', 'aaa', 32),
(33, b'1', 'Anna', 'Smolarek', 'anna99', '$2a$10$JEK8DbG0eZWCTtdkpCXNPO.T5oGiranDVf83caQtLGCIwtDTwGGAO', 'ann99@gmail.com', 33),
(34, b'1', 'Roman', 'Smolarek', 'roman99', '$2a$10$LI1iIjkxDg.5vXz1wNT0d.h2zAJ2N1EFEtP7Of/q5eHj9042gZSqq', 'arek9907@gmail.com', 35);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users_roles`
--

CREATE TABLE `users_roles` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `users_roles`
--

INSERT INTO `users_roles` (`id`, `user_id`, `role_id`) VALUES
(1, 1, 1),
(2, 2, 2),
(26, 29, 2),
(27, 30, 3),
(28, 31, 2),
(29, 32, 2),
(30, 33, 2),
(31, 34, 2);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `confirm_new_account`
--
ALTER TABLE `confirm_new_account`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6n75n30qux463e41kcdgs05lk` (`owner_id`),
  ADD KEY `FKm4oimk0l1757o9pwavorj6ljg` (`petsitter_id`);

--
-- Indeksy dla tabeli `reservation1`
--
ALTER TABLE `reservation1`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`role_id`);

--
-- Indeksy dla tabeli `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD KEY `fk` (`address_id`);

--
-- Indeksy dla tabeli `users_roles`
--
ALTER TABLE `users_roles`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk1` (`user_id`),
  ADD KEY `fk2` (`role_id`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `confirm_new_account`
--
ALTER TABLE `confirm_new_account`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT dla tabeli `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT dla tabeli `reservation1`
--
ALTER TABLE `reservation1`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT dla tabeli `roles`
--
ALTER TABLE `roles`
  MODIFY `role_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT dla tabeli `user`
--
ALTER TABLE `user`
  MODIFY `user_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT dla tabeli `users_roles`
--
ALTER TABLE `users_roles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `FK6n75n30qux463e41kcdgs05lk` FOREIGN KEY (`owner_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `FKm4oimk0l1757o9pwavorj6ljg` FOREIGN KEY (`petsitter_id`) REFERENCES `user` (`user_id`);

--
-- Ograniczenia dla tabeli `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `fk` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`);

--
-- Ograniczenia dla tabeli `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `fk1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `fk2` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
