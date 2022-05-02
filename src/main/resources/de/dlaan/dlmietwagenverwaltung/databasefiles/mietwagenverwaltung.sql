-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 17, 2021 at 08:22 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mietwagenverwaltung`
--

-- --------------------------------------------------------

--
-- Table structure for table `pkw`
--

CREATE TABLE `pkw` (
  `_id` int(11) NOT NULL,
  `type` text NOT NULL,
  `propulsion` text NOT NULL,
  `is_available` tinyint(1) NOT NULL,
  `day_price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pkw`
--

INSERT INTO `pkw` (`_id`, `type`, `propulsion`, `is_available`, `day_price`) VALUES
(1, 'Mercedes G350', 'Elektric', 0, 100),
(2, 'Mercedes A4', 'Elektric', 0, 200),
(3, 'Mercedes AMG E280', 'Elektric', 1, 300),
(5, 'Mercedes B-Class', 'Elektric', 1, 789.45),
(6, 'Mercedes A81', 'Elektric', 0, 900),
(7, 'Toyota Aygo', 'Hybride', 1, 100.5),
(8, 'Toyota Yaris', 'Hybride', 1, 200.6),
(9, 'Toyota RAF', 'Hybride', 0, 300.75),
(10, 'Toyota Hilux', 'Hybride', 1, 400.9),
(11, 'VW Golf', 'Stromer', 1, 150.78),
(12, 'VW Passat', 'Diesel', 1, 251.45),
(13, 'VW Polo', 'Diesel', 1, 50.45),
(14, 'VW ID3', 'Stromer', 1, 800.78),
(15, 'VW Golf +', 'Diesel', 1, 412.45),
(16, 'Toyota Aygo', 'Hybride', 1, 100.5),
(17, 'Toyota Yaris', 'Hybride', 1, 200.6),
(18, 'Toyota RAF', 'Hybride', 1, 300.75),
(19, 'Toyota Hilux', 'Hybride', 1, 400.9),
(20, 'VW Golf', 'Diesel', 1, 150.78),
(21, 'VW Passat', 'Diesel', 1, 251.45),
(22, 'VW Polo', 'Diesel', 1, 50.45),
(23, 'VW ID3', 'Stromer', 1, 800.78),
(24, 'VW Golf +', 'Diesel', 1, 412.45),
(25, 'Tesla CyberTruck', 'Stromer', 0, -100),
(26, 'Tesla Model 3', 'Stromer', 1, 1200),
(27, 'Tesla Model S', 'Stromer', 1, 2500),
(28, 'McLaren', 'Hybride', 1, 10000),
(29, 'Lotus', 'Brenner', 0, 8500),
(30, 'Ferrari', 'Hybride', 1, 25000),
(31, 'Bentley', 'Hybride', 1, 50000),
(32, 'McLaren', 'Hybride', 1, 10000),
(33, 'Lotus', 'Brenner', 1, 8500),
(34, 'Ferrari Testarosa', 'Hybride', 1, 25000),
(35, 'Bentley', 'Hybride', 1, 51000),
(36, 'Trabant Turbo', 'Elektric', 1, 5),
(37, 'Smart very Smart', 'Stromer', 1, 3000),
(38, 'Toyota Hilux', 'Diesel', 1, 1599),
(39, 'DAF 66', 'Stromer', 0, 55.55),
(40, 'Mercedes G350', 'Elektric', 0, 100),
(41, 'Mercedes A4', 'Elektric', 0, 200),
(42, 'Mercedes AMG E280', 'Elektric', 1, 300),
(43, 'Mercedes A36', 'Elektric', 0, 600),
(44, 'Mercedes B-Class', 'Elektric', 1, 789.45),
(45, 'Mercedes A81', 'Elektric', 0, 900),
(46, 'Toyota Aygo', 'Hybride', 1, 100.5),
(47, 'Toyota Yaris', 'Hybride', 1, 200.6),
(48, 'Toyota RAF', 'Hybride', 0, 300.75),
(49, 'Toyota Hilux', 'Hybride', 1, 400.9),
(50, 'VW Golf', 'Stromer', 1, 150.78),
(51, 'VW Passat', 'Diesel', 1, 251.45),
(52, 'VW Polo', 'Diesel', 1, 50.45),
(53, 'VW ID3', 'Stromer', 1, 800.78),
(54, 'VW Golf +', 'Diesel', 1, 412.45),
(55, 'Toyota Aygo', 'Hybride', 1, 100.5),
(56, 'Toyota Yaris', 'Hybride', 1, 200.6),
(57, 'Toyota RAF', 'Hybride', 1, 300.75),
(58, 'Toyota Hilux', 'Hybride', 1, 400.9),
(59, 'VW Golf', 'Diesel', 1, 150.78),
(60, 'VW Passat', 'Diesel', 1, 251.45),
(61, 'VW Polo', 'Diesel', 1, 50.45),
(62, 'VW ID3', 'Stromer', 1, 800.78),
(63, 'VW Golf +', 'Diesel', 1, 412.45),
(64, 'Tesla CyberTruck', 'Stromer', 0, -100),
(65, 'Tesla Model 3', 'Stromer', 1, 1200),
(66, 'Tesla Model S', 'Stromer', 1, 2500),
(67, 'McLaren', 'Hybride', 1, 10000),
(68, 'Lotus', 'Brenner', 0, 8500),
(69, 'Ferrari', 'Hybride', 1, 25000),
(70, 'Bentley', 'Hybride', 1, 50000),
(71, 'McLaren', 'Hybride', 1, 10000),
(72, 'Lotus', 'Brenner', 1, 8500),
(73, 'Ferrari Testarosa', 'Hybride', 1, 25000),
(74, 'Bentley', 'Hybride', 1, 51000),
(75, 'Trabant Turbo', 'Elektric', 1, 5),
(76, 'Smart very Smart', 'Stromer', 1, 3000),
(77, 'Toyota Hilux', 'Diesel', 1, 1599),
(78, 'DAF 66', 'Stromer', 0, 55.55),
(79, 'Toyota Aygo', 'Hybride', 1, 100.5),
(80, 'Toyota Yaris', 'Hybride', 1, 200.6),
(81, 'Toyota RAF', 'Hybride', 1, 300.75),
(82, 'Toyota Hilux', 'Hybride', 1, 400.9),
(83, 'VW Golf', 'Diesel', 1, 150.78),
(84, 'VW Passat', 'Diesel', 1, 251.45),
(85, 'VW Polo', 'Diesel', 1, 50.45),
(86, 'VW ID3', 'Stromer', 1, 800.78),
(87, 'VW Golf +', 'Diesel', 1, 412.45),
(88, 'Tesla CyberTruck', 'Stromer', 0, -100),
(89, 'Tesla Model 3', 'Stromer', 1, 1200),
(90, 'Tesla Model S', 'Stromer', 1, 2500),
(91, 'McLaren', 'Hybride', 1, 10000),
(92, 'Lotus', 'Brenner', 1, 8500),
(93, 'Ferrari', 'Hybride', 1, 25000),
(94, 'Bentley', 'Hybride', 1, 50000),
(95, 'Buli', 'Stromer', 1, 3.79),
(96, 'Very bIg Car', 'Hybride', 1, 100);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `pkw`
--
ALTER TABLE `pkw`
  ADD PRIMARY KEY (`_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pkw`
--
ALTER TABLE `pkw`
  MODIFY `_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=97;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
