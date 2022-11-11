-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 11, 2022 at 05:49 PM
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
-- Database: `andrewtatefanclub`
--

-- --------------------------------------------------------

--
-- Table structure for table `authors`
--

CREATE TABLE `authors` (
  `authorId` int(10) NOT NULL,
  `fName` varchar(20) NOT NULL,
  `lName` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `authors`
--

INSERT INTO `authors` (`authorId`, `fName`, `lName`) VALUES
(300, 'Nigel', 'Chuan'),
(301, 'Bob', 'Doe'),
(302, 'John', 'Smith'),
(303, 'Sam', 'Lee'),
(304, 'Alex', 'Ho'),
(305, 'Zac', 'Stark');

-- --------------------------------------------------------

--
-- Table structure for table `bids`
--

CREATE TABLE `bids` (
  `paperId` int(10) NOT NULL,
  `reviewerId` int(10) NOT NULL,
  `bidInfo` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bids`
--

INSERT INTO `bids` (`paperId`, `reviewerId`, `bidInfo`) VALUES
(200, 30000, 'No'),
(200, 30001, 'No'),
(200, 30002, 'Yes');

-- --------------------------------------------------------

--
-- Table structure for table `papers`
--

CREATE TABLE `papers` (
  `paperId` int(10) NOT NULL,
  `title` varchar(100) NOT NULL,
  `content` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `papers`
--

INSERT INTO `papers` (`paperId`, `title`, `content`) VALUES
(200, 'The Story Of My Life', 'Wearing a colorful Wig.'),
(201, 'How To Make Money', 'Try begging.'),
(202, 'What\'s The Point In All This?', 'I don\'t know man.'),
(203, 'Going Back Downtown', 'But there\'s nothing there for me.'),
(204, 'How to code for dummies', 'How about go fuck yourself.'),
(205, 'Surviving 101', 'Don\'t drink your own pee.'),
(206, 'Maplesktsot', 'kdnfsafnasf'),
(220, 'New Story', 'New Story'),
(221, 'lorem', 'lorem'),
(222, 'ipsum', 'ipsum'),
(223, 'HI', 'HI'),
(225, 'HI', 'HI'),
(226, 'HI', 'HI'),
(227, 'Yea', 'Yea'),
(229, 'LOL1', 'LOL1'),
(230, 'LOL2', 'LOL2'),
(231, 'kek', 'kek'),
(232, 'hehe', 'hehe'),
(233, 'hehehe', 'hehehe'),
(234, 'OCCK', 'COOCK'),
(235, 'KEKEKE', 'KEKEKE'),
(236, 'pleaseeee', 'Pleaseeee'),
(237, 'PLS', 'PLS'),
(238, 'lolzz', 'lolzz'),
(239, 'kakakakak', 'kakakkaka'),
(240, 'kajajajaj', 'jajjaja'),
(241, 'Jerone', 'Jerone');

-- --------------------------------------------------------

--
-- Table structure for table `paper_authors`
--

CREATE TABLE `paper_authors` (
  `paperId` int(100) NOT NULL,
  `submittedId` int(100) NOT NULL,
  `authorId` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `paper_authors`
--

INSERT INTO `paper_authors` (`paperId`, `submittedId`, `authorId`) VALUES
(200, 20001, 20001),
(200, 20001, 20002),
(200, 20001, 20003),
(201, 20003, 20003),
(241, 20001, 20002);

-- --------------------------------------------------------

--
-- Table structure for table `reviewers`
--

CREATE TABLE `reviewers` (
  `reviewId` int(100) NOT NULL,
  `workload` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `reviewers`
--

INSERT INTO `reviewers` (`reviewId`, `workload`) VALUES
(30000, '6'),
(30001, '2');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userId` int(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `name` varchar(10000) NOT NULL,
  `email` varchar(30) NOT NULL,
  `accountType` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userId`, `username`, `password`, `name`, `email`, `accountType`) VALUES
(10000, 'a', 'a', 'Nigel', 'a@mail.com', 'Admin'),
(10001, 'c', 'c', 'Jerone', 'ewrqwrqa@mail.com', 'Admin'),
(20000, 'Nigel', 'Chuan', 'Bryan', 'b@mail.com', 'Author'),
(20001, 'b', 'b', 'Patrick', 'wdw@mail.com', 'Author'),
(20002, 'k', 'k', 'Ching', 'Ching', 'Author'),
(20003, 'd', 'd', 'd', 'd', 'Author'),
(30000, 'Jerone', 'Tan', 'George', 'c@mail.com', 'Reviewer'),
(30001, '4', '4', 'Monkey', '4', 'Reviewer'),
(30002, 'f', 'f', 'ff', 'f', 'Reviewer'),
(40000, 'Bobby', 'Lee', 'Bob', 'd@mail.com', 'Conference Chair'),
(40001, 'qwer', 'qwer1', 'Peter', 'qwer', 'Conference Chair');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `authors`
--
ALTER TABLE `authors`
  ADD PRIMARY KEY (`authorId`);

--
-- Indexes for table `bids`
--
ALTER TABLE `bids`
  ADD UNIQUE KEY `FK1` (`paperId`,`reviewerId`) USING BTREE,
  ADD KEY `FK4` (`paperId`),
  ADD KEY `FK5` (`reviewerId`);

--
-- Indexes for table `papers`
--
ALTER TABLE `papers`
  ADD PRIMARY KEY (`paperId`);

--
-- Indexes for table `paper_authors`
--
ALTER TABLE `paper_authors`
  ADD UNIQUE KEY `FK1` (`paperId`,`submittedId`,`authorId`) USING BTREE,
  ADD KEY `FK2` (`authorId`),
  ADD KEY `FK3` (`submittedId`);

--
-- Indexes for table `reviewers`
--
ALTER TABLE `reviewers`
  ADD PRIMARY KEY (`reviewId`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `authors`
--
ALTER TABLE `authors`
  MODIFY `authorId` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=306;

--
-- AUTO_INCREMENT for table `papers`
--
ALTER TABLE `papers`
  MODIFY `paperId` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=242;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userId` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40004;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `paper_authors`
--
ALTER TABLE `paper_authors`
  ADD CONSTRAINT `FK1` FOREIGN KEY (`paperId`) REFERENCES `papers` (`paperId`),
  ADD CONSTRAINT `FK2` FOREIGN KEY (`authorId`) REFERENCES `users` (`userId`),
  ADD CONSTRAINT `FK3` FOREIGN KEY (`submittedId`) REFERENCES `users` (`userId`);

--
-- Constraints for table `reviewers`
--
ALTER TABLE `reviewers`
  ADD CONSTRAINT `FK_reviewers` FOREIGN KEY (`reviewId`) REFERENCES `users` (`userId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
