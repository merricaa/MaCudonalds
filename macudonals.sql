-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 28, 2022 at 07:36 PM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `macudonals`
--

-- --------------------------------------------------------

--
-- Table structure for table `food`
--

CREATE TABLE `food` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `price` int(11) NOT NULL,
  `stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `food`
--

INSERT INTO `food` (`id`, `name`, `type`, `price`, `stock`) VALUES
(1, 'Pizza', 'Main dish', 100000, 90),
(3, 'Salad', 'Appetizer', 15000, 35),
(4, 'Sprite', 'Drink', 5000, 985),
(5, 'Ice Cream', 'Desert', 7000, 185),
(6, 'Rice', 'Side dish', 5000, 970),
(7, 'Spaghetti', 'Main dish', 75000, 25),
(9, 'Fried Chicken', 'Main dish', 10000, 90);

-- --------------------------------------------------------

--
-- Table structure for table `transaction_detail`
--

CREATE TABLE `transaction_detail` (
  `transaction_id` int(11) NOT NULL,
  `food_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `total_price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaction_detail`
--

INSERT INTO `transaction_detail` (`transaction_id`, `food_id`, `quantity`, `total_price`) VALUES
(1, 3, 10, 15000),
(1, 5, 15, 7000),
(2, 3, 3, 15000),
(2, 5, 6, 7000),
(2, 4, 3, 5000),
(2, 6, 5, 5000),
(3, 4, 13, 5000),
(4, 1, 10, 1000000),
(4, 3, 10, 150000),
(4, 4, 5, 25000),
(5, 2, 10, 100000),
(5, 5, 15, 105000),
(5, 6, 5, 25000),
(6, 4, 10, 50000),
(6, 3, 5, 75000),
(6, 6, 25, 125000);

-- --------------------------------------------------------

--
-- Table structure for table `transaction_header`
--

CREATE TABLE `transaction_header` (
  `transaction_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaction_header`
--

INSERT INTO `transaction_header` (`transaction_id`, `user_id`, `date`) VALUES
(1, 2, '2022-05-28 15:56:45'),
(2, 2, '2022-05-28 16:02:38'),
(3, 2, '2022-05-28 16:05:59'),
(4, 2, '2022-05-28 16:23:15'),
(5, 2, '2022-05-28 16:33:13'),
(6, 3, '2022-05-28 16:49:49');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `nationality` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `email`, `phone_number`, `address`, `gender`, `role`, `nationality`) VALUES
(1, 'Administrator', 'password', 'administrator@email.com', '1234567890', 'Address street', 'Male', 'administrator', 'Indonesian'),
(2, 'User', 'password', 'user@email.com', '1234567890', 'Address street', 'Female', 'user', 'Indonesian'),
(3, 'jungkook', 'password', 'jungkook@email.com', '1234567890', 'Address street', 'Male', 'USER', 'Korea');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `food`
--
ALTER TABLE `food`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transaction_header`
--
ALTER TABLE `transaction_header`
  ADD PRIMARY KEY (`transaction_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `food`
--
ALTER TABLE `food`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `transaction_header`
--
ALTER TABLE `transaction_header`
  MODIFY `transaction_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
