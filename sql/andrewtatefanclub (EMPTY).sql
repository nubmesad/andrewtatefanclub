-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 17, 2022 at 07:29 AM
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
-- Table structure for table `allocated_papers`
--

CREATE TABLE `allocated_papers` (
  `allocatedID` int(10) NOT NULL,
  `paperId` int(10) NOT NULL,
  `reviewerId` int(10) NOT NULL,
  `reviewStatus` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `authors_rating`
--

CREATE TABLE `authors_rating` (
  `id` int(100) NOT NULL,
  `reviewerId` int(100) NOT NULL,
  `paperId` int(100) NOT NULL,
  `rating` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `bids`
--

CREATE TABLE `bids` (
  `paperId` int(10) NOT NULL,
  `reviewerId` int(10) NOT NULL,
  `bidInfo` varchar(100) NOT NULL,
  `bidStatus` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `email`
--

CREATE TABLE `email` (
  `emailId` int(100) NOT NULL,
  `conferenceChairId` int(100) NOT NULL,
  `authorId` int(100) NOT NULL,
  `message` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `papers`
--

CREATE TABLE `papers` (
  `paperId` int(10) NOT NULL,
  `title` varchar(100) NOT NULL,
  `content` varchar(10000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `paper_authors`
--

CREATE TABLE `paper_authors` (
  `paperId` int(100) NOT NULL,
  `submittedId` int(100) NOT NULL,
  `authorId` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `paper_status`
--

CREATE TABLE `paper_status` (
  `paperId` int(100) NOT NULL,
  `conferenceChairId` int(100) NOT NULL,
  `paperStatus` varchar(100) NOT NULL,
  `notifyStatus` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `reviewers`
--

CREATE TABLE `reviewers` (
  `reviewId` int(100) NOT NULL,
  `workload` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE `reviews` (
  `paperId` int(10) NOT NULL,
  `rating` varchar(100) NOT NULL,
  `review` varchar(10000) NOT NULL,
  `reviewerId` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `reviews_comment`
--

CREATE TABLE `reviews_comment` (
  `columnId` int(10) NOT NULL,
  `paperId` int(100) NOT NULL,
  `userId` int(100) NOT NULL,
  `comment` varchar(10000) NOT NULL,
  `date` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
-- Indexes for dumped tables
--

--
-- Indexes for table `allocated_papers`
--
ALTER TABLE `allocated_papers`
  ADD PRIMARY KEY (`allocatedID`),
  ADD KEY `ap_FK1` (`paperId`),
  ADD KEY `ap_FK2` (`reviewerId`);

--
-- Indexes for table `authors_rating`
--
ALTER TABLE `authors_rating`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bids`
--
ALTER TABLE `bids`
  ADD UNIQUE KEY `FK1` (`paperId`,`reviewerId`) USING BTREE,
  ADD KEY `FK4` (`paperId`),
  ADD KEY `FK5` (`reviewerId`);

--
-- Indexes for table `email`
--
ALTER TABLE `email`
  ADD PRIMARY KEY (`emailId`);

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
-- Indexes for table `paper_status`
--
ALTER TABLE `paper_status`
  ADD UNIQUE KEY `statusFK1` (`paperId`,`conferenceChairId`) USING BTREE,
  ADD KEY `statusFK2` (`conferenceChairId`);

--
-- Indexes for table `reviewers`
--
ALTER TABLE `reviewers`
  ADD PRIMARY KEY (`reviewId`);

--
-- Indexes for table `reviews`
--
ALTER TABLE `reviews`
  ADD UNIQUE KEY `reviews_FK1` (`paperId`) USING BTREE,
  ADD KEY `reviews_FK2` (`reviewerId`);

--
-- Indexes for table `reviews_comment`
--
ALTER TABLE `reviews_comment`
  ADD PRIMARY KEY (`columnId`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `allocated_papers`
--
ALTER TABLE `allocated_papers`
  MODIFY `allocatedID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=122;

--
-- AUTO_INCREMENT for table `authors_rating`
--
ALTER TABLE `authors_rating`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `email`
--
ALTER TABLE `email`
  MODIFY `emailId` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `papers`
--
ALTER TABLE `papers`
  MODIFY `paperId` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=300;

--
-- AUTO_INCREMENT for table `reviews_comment`
--
ALTER TABLE `reviews_comment`
  MODIFY `columnId` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userId` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40026;

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
