-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 18, 2019 at 08:25 PM
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
-- Database: `vgecalumni`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_chat`
--

CREATE TABLE `admin_chat` (
  `id` int(11) NOT NULL,
  `sender_name` varchar(33) NOT NULL,
  `reciver_name` varchar(33) NOT NULL,
  `message_text` text NOT NULL,
  `datetime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin_chat`
--

INSERT INTO `admin_chat` (`id`, `sender_name`, `reciver_name`, `message_text`, `datetime`) VALUES
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
(19, 'vaa user', 'user1', 'who are you??', '2019-06-14 08:34:02'),
(20, 'Drashti', 'admin', 'dxcdxv', '2019-06-14 19:01:28'),
(21, 'xyz', 'admin', 'admin???\r\n', '2019-06-14 19:01:09'),
(22, 'xyz', 'user1', 'vd,mbn', '2019-06-14 19:01:29'),
(23, 'vaa user', 'xyz', 'hello hows you?', '2019-06-14 20:01:52'),
(24, 'vaa user', 'vaa', 'HII m also vaa member', '2019-06-14 20:01:16'),
(25, 'vaa', 'vaa', 'hiiiii', '2019-06-14 20:01:53'),
(26, 'xyz', 'vaa', 'vaa', '2019-06-14 20:01:22'),
(27, 'xyz', 'vaa user', 'hii', '2019-06-14 20:01:37'),
(28, 'vaa user', 'vaa', 'hi', '2019-06-14 20:01:53'),
(29, 'vaa user', 'drashti', 'hii d', '2019-06-14 20:01:53'),
(30, 'Drashti', 'vaa user', 'hii who are you?', '2019-06-14 08:39:42'),
(31, 'Drashti', 'vaa user', 'hiiiiiiiiiiiiii', '2019-06-14 20:01:02'),
(32, 'user', 'Drashti', 'hi', '2019-06-14 20:01:04'),
(33, 'user', 'vaa', 'hi', '2019-06-14 20:01:15'),
(34, 'user', 'vaa user', 'hi user', '2019-06-14 20:01:29'),
(35, 'user', 'xyz', 'xyz', '2019-06-14 20:01:38'),
(36, 'user', 'vaa user', 'who are you?', '2019-06-14 08:45:54'),
(37, 'xyz', 'user', 'who are you??', '2019-06-14 08:46:45'),
(38, 'xyz', 'user', 'how you know me?', '2019-06-14 08:46:59'),
(39, 'vaa', 'user', 'hii', '2019-06-14 08:47:30'),
(40, 'Drashti', 'user', '.....', '2019-06-14 08:47:59'),
(41, 'vaa user', 'user', '....', '2019-06-14 08:48:25'),
(42, 'shivani', 'Drashti', 'hii drashtiiii', '2019-06-18 18:01:09'),
(43, 'shruti', 'drashti', 'hie hws you??', '2019-06-18 18:01:17'),
(44, 'shruti', 'drashti', 'you know me??', '2019-06-18 06:57:36'),
(45, 'Drashti', 'vaa user', 'hie', '2019-06-18 07:01:57'),
(46, 'Drashti', 'shruti', 'hi', '2019-06-18 07:56:53'),
(47, 'shivani', 'Drashti', 'hi', '2019-06-18 07:57:30'),
(48, 'Drashti', 'shivani', 'hi', '2019-06-18 07:57:50'),
(49, 'vgec', 'shivani', 'hi shivani', '2019-06-18 20:01:46'),
(50, 'vgec', 'drashti', 'hii', '2019-06-18 20:01:57'),
(51, 'shivani', 'vgec', 'hey hi', '2019-06-18 08:22:41');

-- --------------------------------------------------------

--
-- Table structure for table `admin_users`
--

CREATE TABLE `admin_users` (
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
-- Dumping data for table `admin_users`
--

INSERT INTO `admin_users` (`id`, `user_name`, `password`, `role`, `status`, `name`, `email`, `mobile`, `profile_pic`, `last_logged`, `role_id`) VALUES
(11, 'vaa user', 'vaavaa', NULL, NULL, NULL, NULL, NULL, NULL, '0000-00-00 00:00:00', NULL),
(12, 'vaa', 'vaa', 'core Member', 0, 'vaa leader', 'vaa@gmail.com', 9909909909, 'sci-fi-artistic-wallpapers-30848-430884.jpg', '0000-00-00 00:00:00', 0),
(13, 'xyz', 'xyz', 'Volunteer', 0, 'xyzz', 'xyz@xyz.xyz', 9909989989, 'Tulips.jpg', '0000-00-00 00:00:00', 0),
(14, 'Drashti', 'drashti', NULL, NULL, NULL, NULL, NULL, NULL, '0000-00-00 00:00:00', NULL),
(15, 'user', 'user', NULL, NULL, NULL, NULL, NULL, NULL, '0000-00-00 00:00:00', NULL),
(16, 'shivani', 'shivani', NULL, NULL, NULL, NULL, NULL, NULL, '0000-00-00 00:00:00', NULL),
(17, 'shruti', '1234', NULL, NULL, NULL, NULL, NULL, NULL, '0000-00-00 00:00:00', NULL),
(18, 'vgec', 'vgec', NULL, NULL, NULL, NULL, NULL, NULL, '0000-00-00 00:00:00', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin_chat`
--
ALTER TABLE `admin_chat`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `admin_users`
--
ALTER TABLE `admin_users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin_chat`
--
ALTER TABLE `admin_chat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT for table `admin_users`
--
ALTER TABLE `admin_users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
