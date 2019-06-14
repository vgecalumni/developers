-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 14, 2019 at 07:31 PM
-- Server version: 10.1.39-MariaDB
-- PHP Version: 7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `vaachat`
--

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `sender_name` varchar(33) NOT NULL,
  `reciver_name` varchar(33) NOT NULL,
  `message_text` text NOT NULL,
  `datetime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `message`
--

INSERT INTO `message` (`id`, `sender_name`, `reciver_name`, `message_text`, `datetime`) VALUES
(1, 'Drashti', 'admin', 'hellow', '2019-06-12 04:01:14'),
(2, 'Drashti', 'drashti', 'hey drashtiiiiii', '2019-06-12 04:06:16'),
(3, 'Drashti', 'drashti', 'drashti hows you??\r\n', '2019-06-13 06:56:42'),
(4, 'user1', 'Drashti', 'gbvjnbnb vncnbvmn', '2019-06-13 08:16:59'),
(5, 'user1', 'user1', 'userrrrrrrrrrrrrrrrrrrrrr1', '2019-06-13 08:19:04'),
(6, 'drashti', 'dhyey', 'hi bro...', '2019-06-13 09:43:17'),
(7, 'dhyey', 'drashti', 'hi', '2019-06-13 09:56:55'),
(8, 'dhyey', 'drashti', 'how are you didi???', '2019-06-13 09:57:30'),
(9, 'dhyey', 'drashti', 'kyare aave 6  ghare??', '2019-06-13 09:57:48'),
(10, 'dhyey', 'admin', 'hey admin', '2019-06-13 10:28:54'),
(11, 'user2', 'Drashti', 'hey hieee\r\n', '2019-06-13 10:43:03'),
(12, 'user2', 'dhyey', 'hellow bro..see you soon\r\n', '2019-06-13 10:43:24'),
(13, 'user3', 'user1', 'hello user1\r\n', '2019-06-13 22:01:32'),
(14, 'user3', 'user2', 'hello user2', '2019-06-13 22:01:47'),
(15, 'user3', 'user3', 'hello user3\r\n', '2019-06-13 22:01:01'),
(16, 'user3', 'user1', 'meet me soon at college', '2019-06-13 10:54:31'),
(17, 'vaa user', 'Drashti', 'hello how are you??\r\n', '2019-06-14 08:01:17'),
(18, 'vaa user', 'user1', 'please change your name so that we can identify you...\r\n', '2019-06-14 08:01:45'),
(19, 'vaa user', 'user1', 'who are you??', '2019-06-14 08:34:02');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `user_name` varchar(33) NOT NULL,
  `password` varchar(33) NOT NULL,
  `role` varchar(25) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `name` varchar(80) DEFAULT NULL,
  `email` varchar(80) DEFAULT NULL,
  `mobile` bigint(10) DEFAULT NULL,
  `profile_pic` varchar(800) DEFAULT NULL,
  `last_logged` datetime DEFAULT NULL,
  `role_id` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `user_name`, `password`, `role`, `status`, `name`, `email`, `mobile`, `profile_pic`, `last_logged`, `role_id`) VALUES
(1, 'Drashti', 'drashti', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(6, 'admin', 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(7, 'user1', 'user1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(8, 'dhyey', 'dhyey', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(9, 'user2', 'user2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(10, 'user3', 'user3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(11, 'vaa user', 'vaavaa', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(12, 'vaa', 'vaa', 'core Member', 0, 'vaa leader', 'vaa@gmail.com', 9909909909, 'sci-fi-artistic-wallpapers-30848-430884.jpg', '0000-00-00 00:00:00', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `message`
--
ALTER TABLE `message`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
