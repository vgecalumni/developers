-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 28, 2019 at 08:52 PM
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
-- Database: `chatapp`
--

-- --------------------------------------------------------

--
-- Table structure for table `chats`
--

CREATE TABLE `chats` (
  `id` int(11) NOT NULL,
  `topic` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `chats`
--

INSERT INTO `chats` (`id`, `topic`, `created_at`) VALUES
(11, '15', '2017-08-13 04:33:24'),
(12, '14', '2017-08-13 04:42:42'),
(13, '54', '2017-08-14 01:21:04'),
(14, '91', '2017-08-14 01:24:51'),
(15, '111', '2017-08-22 14:23:17'),
(16, '115', '2017-08-22 14:29:52'),
(17, 'test', '2017-08-22 15:26:17'),
(20, 'spectra', '2017-08-22 15:55:38'),
(21, 'yuhu', '2017-08-22 17:15:21'),
(22, '121', '2019-06-25 05:03:26'),
(23, 'bestie', '2019-06-26 18:13:14'),
(24, '1210', '2019-06-26 18:28:33'),
(25, '1512', '2019-06-26 18:30:51'),
(26, '151', '2019-06-26 19:49:29'),
(27, 'test group', '2019-06-28 18:17:25'),
(28, '1213', '2019-06-28 18:17:41'),
(29, '1217', '2019-06-28 18:48:15'),
(30, '1218', '2019-06-28 18:48:22');

-- --------------------------------------------------------

--
-- Table structure for table `chats_details`
--

CREATE TABLE `chats_details` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `chat_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `chats_messages`
--

CREATE TABLE `chats_messages` (
  `id` int(11) NOT NULL,
  `chat_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `content` text,
  `is_image` tinyint(1) NOT NULL DEFAULT '0',
  `is_read` enum('0','1') NOT NULL DEFAULT '0',
  `is_doc` enum('0','1') DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `chats_messages`
--

INSERT INTO `chats_messages` (`id`, `chat_id`, `user_id`, `content`, `is_image`, `is_read`, `is_doc`, `created_at`) VALUES
(1, 12, 1, 'Laporan_v101.pdf', 0, '0', '1', '2017-08-30 04:20:30'),
(2, 12, 1, 'hayy', 0, '0', NULL, '2017-09-04 07:57:43'),
(3, 22, 12, 'hii', 0, '0', NULL, '2019-06-25 05:03:35'),
(4, 22, 12, 'ava6.jpg', 1, '0', '0', '2019-06-25 05:04:13'),
(5, 22, 12, 'what language you use to build this app', 0, '0', NULL, '2019-06-25 05:17:58'),
(6, 20, 12, 'hi', 0, '0', NULL, '2019-06-26 18:19:32'),
(7, 25, 15, 'hiee', 0, '0', NULL, '2019-06-26 18:30:56'),
(8, 25, 15, 'you know me..', 0, '0', NULL, '2019-06-26 18:31:09'),
(9, 25, 15, 'sci-fi-artistic-wallpapers-30848-430884.jpg', 1, '0', '0', '2019-06-26 18:31:32'),
(10, 25, 12, 'hi', 0, '0', NULL, '2019-06-26 18:33:08'),
(11, 28, 12, 'hie', 0, '0', NULL, '2019-06-28 18:17:46'),
(12, 28, 12, 'send me pic plzz', 0, '0', NULL, '2019-06-28 18:18:00'),
(13, 28, 12, 'Chrysanthemum.jpg', 1, '0', '0', '2019-06-28 18:21:34'),
(14, 30, 12, 'hie', 0, '0', NULL, '2019-06-28 18:48:29'),
(15, 30, 12, 'Tulips.jpg', 1, '0', '0', '2019-06-28 18:50:17'),
(16, 30, 12, 'hhie', 0, '0', NULL, '2019-06-28 18:51:06');

-- --------------------------------------------------------

--
-- Table structure for table `dashboard`
--

CREATE TABLE `dashboard` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `messages` text NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dashboard`
--

INSERT INTO `dashboard` (`id`, `user_id`, `messages`, `created_at`) VALUES
(1, 1, 'Hello World', '2017-09-04 07:20:54'),
(2, 1, 'pengumuman a', '2017-09-04 07:42:29'),
(3, 1, 'i am here', '2017-09-04 07:42:35'),
(4, 6, 'gardening here', '2017-09-04 07:43:15'),
(5, 6, 'haloo', '2017-09-04 07:44:00'),
(6, 1, 'rapat jam 3 di lantai 3', '2017-09-04 07:49:23');

-- --------------------------------------------------------

--
-- Table structure for table `groups_chats`
--

CREATE TABLE `groups_chats` (
  `id` int(11) NOT NULL,
  `chat_id` int(11) NOT NULL,
  `created_by` int(11) NOT NULL,
  `total_member` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `groups_chats`
--

INSERT INTO `groups_chats` (`id`, `chat_id`, `created_by`, `total_member`, `created_at`) VALUES
(2, 20, 11, 3, '2017-08-22 15:55:38'),
(3, 21, 10, 1, '2017-08-22 17:15:21'),
(4, 23, 12, 1, '2019-06-26 18:13:14'),
(5, 27, 12, 1, '2019-06-28 18:17:25');

-- --------------------------------------------------------

--
-- Table structure for table `groups_members`
--

CREATE TABLE `groups_members` (
  `id` int(11) NOT NULL,
  `chat_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `groups_members`
--

INSERT INTO `groups_members` (`id`, `chat_id`, `user_id`, `created_at`) VALUES
(10, 20, 1, '2017-08-22 23:57:33'),
(11, 23, 12, '2019-06-26 18:13:14'),
(12, 20, 12, '2019-06-26 18:19:27'),
(13, 27, 12, '2019-06-28 18:17:25');

-- --------------------------------------------------------

--
-- Table structure for table `uri_segments`
--

CREATE TABLE `uri_segments` (
  `id` int(11) NOT NULL,
  `first` int(11) NOT NULL,
  `second` int(11) NOT NULL,
  `chat_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `uri_segments`
--

INSERT INTO `uri_segments` (`id`, `first`, `second`, `chat_id`, `created_at`) VALUES
(8, 1, 5, 11, '2017-08-13 04:33:24'),
(9, 1, 4, 12, '2017-08-13 04:42:42'),
(10, 5, 4, 13, '2017-08-14 01:21:04'),
(11, 9, 1, 14, '2017-08-14 01:24:51'),
(12, 11, 1, 15, '2017-08-22 14:23:17'),
(13, 11, 5, 16, '2017-08-22 14:29:52'),
(14, 12, 1, 22, '2019-06-25 05:03:26'),
(15, 12, 10, 24, '2019-06-26 18:28:33'),
(16, 15, 12, 25, '2019-06-26 18:30:51'),
(17, 15, 1, 26, '2019-06-26 19:49:29'),
(18, 12, 13, 28, '2019-06-28 18:17:41'),
(19, 12, 17, 29, '2019-06-28 18:48:15'),
(20, 12, 18, 30, '2019-06-28 18:48:23');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` text NOT NULL,
  `password` text NOT NULL,
  `role` enum('0','1') NOT NULL DEFAULT '1' COMMENT '0 = admin; 1 = common user',
  `email` text NOT NULL,
  `first_name` text NOT NULL,
  `last_name` text NOT NULL,
  `division` varchar(100) NOT NULL,
  `avatar` text,
  `is_logged_in` tinyint(1) NOT NULL,
  `is_activated` enum('0','1') DEFAULT NULL,
  `last_login` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `role`, `email`, `first_name`, `last_name`, `division`, `avatar`, `is_logged_in`, `is_activated`, `last_login`) VALUES
(0, 'admin', 'admin', '0', 'admin@mail.com', 'admin', 'admin', 'admin', 'logo1.png', 0, '1', '2019-06-28 18:48:41'),
(12, 'drashtiMashru', 'drashti', '1', 'drashti@gmail.com', 'Drashti', 'Mashru', 'H', 'eromanga_sensei-sagiri_izumi-anime-(17819).jpg', 0, '1', '2019-06-28 18:51:22'),
(13, 'test user', 'test', '1', 'test@gmail.com', 'test', 'Mashru', 'H', 'default.jpeg', 0, '1', '2019-06-26 18:30:26'),
(14, 'test user', 'test', '1', 'test@gmail.com', 'test', 'Mashru', 'H', 'default.jpeg', 0, '1', '2019-06-26 18:30:19'),
(15, 'vaa', 'vaa', '1', 'vaa@gmail.com', 'vaa', 'vaa', 'vaa', 'default.jpeg', 1, '1', '2019-06-25 18:30:00'),
(16, 'xyz', 'xyz', '1', 'xyz@xyz.xyz', 'xyz', 'xyz', 'xyz', 'default.jpeg', 0, '1', '2019-06-26 18:30:33'),
(17, 'abc', 'abc', '1', 'abc', 'abc', 'abc', 'abc', 'default.jpeg', 0, NULL, '2019-06-26 18:34:21'),
(18, 'a', 'a', '1', 'a@a.a', 'a', 'a', 'a', 'default.jpeg', 1, '1', '2019-06-27 18:30:00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chats`
--
ALTER TABLE `chats`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `chats_details`
--
ALTER TABLE `chats_details`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `chats_messages`
--
ALTER TABLE `chats_messages`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `dashboard`
--
ALTER TABLE `dashboard`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `groups_chats`
--
ALTER TABLE `groups_chats`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `groups_members`
--
ALTER TABLE `groups_members`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `uri_segments`
--
ALTER TABLE `uri_segments`
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
-- AUTO_INCREMENT for table `chats`
--
ALTER TABLE `chats`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `chats_details`
--
ALTER TABLE `chats_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `chats_messages`
--
ALTER TABLE `chats_messages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `dashboard`
--
ALTER TABLE `dashboard`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `groups_chats`
--
ALTER TABLE `groups_chats`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `groups_members`
--
ALTER TABLE `groups_members`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `uri_segments`
--
ALTER TABLE `uri_segments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
