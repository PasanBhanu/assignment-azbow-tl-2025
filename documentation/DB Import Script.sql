-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 27, 2025 at 03:44 AM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `azbow`
--

-- --------------------------------------------------------

--
-- Table structure for table `agent`
--

CREATE TABLE `agent` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `agent`
--

INSERT INTO `agent` (`id`, `name`) VALUES
(1, 'Agent 1'),
(2, 'Agent 2');

-- --------------------------------------------------------

--
-- Table structure for table `lead`
--

CREATE TABLE `lead` (
  `id` int(11) NOT NULL,
  `budget` decimal(38,2) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `follow_up_status` varchar(255) DEFAULT NULL,
  `inquiry_date` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `preferred_property_type` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `agent_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `lead_seq`
--

CREATE TABLE `lead_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `lead_seq`
--

INSERT INTO `lead_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Table structure for table `property`
--

CREATE TABLE `property` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `property`
--

INSERT INTO `property` (`id`, `name`) VALUES
(1, 'Bare Land'),
(2, 'Coconut Plantation'),
(3, 'Single Story House'),
(4, 'Condominium'),
(5, 'Seaside Villa'),
(6, 'Hotel'),
(7, 'Business Venue');

-- --------------------------------------------------------

--
-- Table structure for table `property_reservation`
--

CREATE TABLE `property_reservation` (
  `id` int(11) NOT NULL,
  `contract_signed` bit(1) DEFAULT NULL,
  `expected_closing_date` datetime(6) DEFAULT NULL,
  `financial_status` bit(1) DEFAULT NULL,
  `legal_notes` varchar(255) DEFAULT NULL,
  `loan_amount` decimal(38,2) DEFAULT NULL,
  `payment_plan` varchar(255) DEFAULT NULL,
  `reservation_date` datetime(6) DEFAULT NULL,
  `reservation_fee` decimal(38,2) DEFAULT NULL,
  `lead_id` int(11) DEFAULT NULL,
  `property_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `property_reservation_seq`
--

CREATE TABLE `property_reservation_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `property_reservation_seq`
--

INSERT INTO `property_reservation_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Table structure for table `sale`
--

CREATE TABLE `sale` (
  `id` int(11) NOT NULL,
  `commission_details` varchar(255) DEFAULT NULL,
  `final_sale_price` decimal(38,2) DEFAULT NULL,
  `sale_date` datetime(6) DEFAULT NULL,
  `agent_id` int(11) DEFAULT NULL,
  `lead_id` int(11) DEFAULT NULL,
  `property_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `sale_seq`
--

CREATE TABLE `sale_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sale_seq`
--

INSERT INTO `sale_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `password`, `role`, `username`) VALUES
(1, 'Admin', 'admin', 'admin', 'admin'),
(2, 'Sales Agent', 'sales', 'sales', 'sales');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `agent`
--
ALTER TABLE `agent`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `lead`
--
ALTER TABLE `lead`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKi7o2of1rjjpu017gbnppxm6li` (`agent_id`);

--
-- Indexes for table `property`
--
ALTER TABLE `property`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `property_reservation`
--
ALTER TABLE `property_reservation`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKovulm29fk44gvjro8nin1vfy1` (`lead_id`),
  ADD UNIQUE KEY `UK6m4n2114vmtm6j0f92yxkejar` (`property_id`);

--
-- Indexes for table `sale`
--
ALTER TABLE `sale`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKina9pbyl33s7ssvu5l5lhxlga` (`property_id`),
  ADD KEY `FKgvah8hs1frx0s0wxqjr2vw74u` (`agent_id`),
  ADD KEY `FK14hoeonm4nkkdblg49r52wi2n` (`lead_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `lead`
--
ALTER TABLE `lead`
  ADD CONSTRAINT `FKi7o2of1rjjpu017gbnppxm6li` FOREIGN KEY (`agent_id`) REFERENCES `agent` (`id`);

--
-- Constraints for table `property_reservation`
--
ALTER TABLE `property_reservation`
  ADD CONSTRAINT `FK1d1f3a568nhr20wpro4jwxlw5` FOREIGN KEY (`property_id`) REFERENCES `property` (`id`),
  ADD CONSTRAINT `FK8eu73q8vxkhwtbmla5rlan3j4` FOREIGN KEY (`lead_id`) REFERENCES `lead` (`id`);

--
-- Constraints for table `sale`
--
ALTER TABLE `sale`
  ADD CONSTRAINT `FK14hoeonm4nkkdblg49r52wi2n` FOREIGN KEY (`lead_id`) REFERENCES `lead` (`id`),
  ADD CONSTRAINT `FK828liflqefl8ny5gstfca9s7m` FOREIGN KEY (`property_id`) REFERENCES `property` (`id`),
  ADD CONSTRAINT `FKgvah8hs1frx0s0wxqjr2vw74u` FOREIGN KEY (`agent_id`) REFERENCES `agent` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
