-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 23, 2018 at 07:31 AM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `nsbm`
--

-- --------------------------------------------------------

--
-- Table structure for table `degree`
--

CREATE TABLE `degree` (
  `degree_code` varchar(10) NOT NULL,
  `degree_title` varchar(50) NOT NULL,
  `faculty` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `degree`
--

INSERT INTO `degree` (`degree_code`, `degree_title`, `faculty`) VALUES
('SOCD001', 'Computer Science', 'Computing'),
('SOCD002', 'Bachelor of Information Technology', 'Computing');

-- --------------------------------------------------------

--
-- Table structure for table `faculty`
--

CREATE TABLE `faculty` (
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `faculty`
--

INSERT INTO `faculty` (`name`) VALUES
('Business'),
('Computing'),
('Engineering');

-- --------------------------------------------------------

--
-- Table structure for table `follow`
--

CREATE TABLE `follow` (
  `register_no` varchar(10) NOT NULL,
  `module_code` varchar(10) NOT NULL,
  `payment` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `instruct`
--

CREATE TABLE `instruct` (
  `instructor_no` varchar(10) NOT NULL,
  `module_code` varchar(10) NOT NULL,
  `venue` varchar(20) NOT NULL,
  `day` varchar(15) NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `instructor`
--

CREATE TABLE `instructor` (
  `instructor_no` varchar(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `nic_no` varchar(15) NOT NULL,
  `contact_no` varchar(10) NOT NULL,
  `email` varchar(100) NOT NULL,
  `starting_date` date NOT NULL,
  `faculty` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `lecturer`
--

CREATE TABLE `lecturer` (
  `lecturer_no` varchar(10) NOT NULL,
  `name` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `nic_no` varchar(15) NOT NULL,
  `contact_no` varchar(10) NOT NULL,
  `email` varchar(100) NOT NULL,
  `starting_date` date NOT NULL,
  `faculty` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `module`
--

CREATE TABLE `module` (
  `module_code` varchar(10) NOT NULL,
  `module_title` varchar(100) NOT NULL,
  `year` int(1) NOT NULL,
  `semester` int(1) NOT NULL,
  `credit_value` int(1) NOT NULL,
  `fee` int(11) NOT NULL,
  `type` varchar(20) NOT NULL,
  `faculty` varchar(100) NOT NULL,
  `degree` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `module`
--

INSERT INTO `module` (`module_code`, `module_title`, `year`, `semester`, `credit_value`, `fee`, `type`, `faculty`, `degree`) VALUES
('SOCCSM001', 'DSA 1', 1, 1, 3, 3000, 'Compulsory', 'Computing', 'Computer Science'),
('SOCCSM002', 'Programming 1', 1, 1, 3, 3000, 'Compulsory', 'Computing', 'Computer Science'),
('SOCCSM003', 'Database 1', 1, 1, 3, 3000, 'Compulsory', 'Computing', 'Computer Science'),
('SOCCSM004', 'Computer Systems', 1, 1, 2, 3500, 'Optional', 'Computing', 'Computer Science'),
('SOCCSM005', 'Laboratory 1', 1, 1, 2, 2500, 'Optional', 'Computing', 'Computer Science'),
('SOCCSM006', 'Mathematical Methods 1', 1, 1, 2, 2500, 'Optional', 'Computing', 'Computer Science');

-- --------------------------------------------------------

--
-- Table structure for table `results`
--

CREATE TABLE `results` (
  `register_no` varchar(10) NOT NULL,
  `module_code` varchar(10) NOT NULL,
  `grade` varchar(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `register_no` varchar(15) NOT NULL,
  `name` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `nic_no` varchar(15) NOT NULL,
  `contact_no` varchar(15) NOT NULL,
  `email` varchar(100) NOT NULL,
  `dob` date DEFAULT NULL,
  `gender` varchar(10) NOT NULL,
  `admission_date` date DEFAULT NULL,
  `faculty` varchar(100) NOT NULL,
  `degree` varchar(100) NOT NULL,
  `intake` varchar(15) DEFAULT NULL,
  `subject1` varchar(50) DEFAULT NULL,
  `subject1_result` varchar(2) DEFAULT NULL,
  `subject2` varchar(50) DEFAULT NULL,
  `subject2_result` varchar(2) DEFAULT NULL,
  `subject3` varchar(50) DEFAULT NULL,
  `subject3_result` varchar(2) DEFAULT NULL,
  `english_result` varchar(2) DEFAULT NULL,
  `district_rank` varchar(10) DEFAULT NULL,
  `island_rank` varchar(10) DEFAULT NULL,
  `previous_qualification_type` varchar(200) DEFAULT NULL,
  `institute` varchar(100) DEFAULT NULL,
  `year_of_completion` varchar(4) DEFAULT NULL,
  `undergraduate_flag` varchar(5) NOT NULL,
  `postgraduate_flag` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`register_no`, `name`, `address`, `nic_no`, `contact_no`, `email`, `dob`, `gender`, `admission_date`, `faculty`, `degree`, `intake`, `subject1`, `subject1_result`, `subject2`, `subject2_result`, `subject3`, `subject3_result`, `english_result`, `district_rank`, `island_rank`, `previous_qualification_type`, `institute`, `year_of_completion`, `undergraduate_flag`, `postgraduate_flag`) VALUES
('SOCCS2015001', 'Senith Perera', 'Mount Lavinia', '962603157V', '0777828357', 'smperera@gmail.com', '1996-09-16', 'Male', '2015-02-03', 'Computing', 'Computer Science', 'February', 'Maths', 'B', 'Physics', 'B', 'Chemistry', 'B', 'B', '12', '34', NULL, NULL, NULL, 'Yes', 'No'),
('SOCCS2018001', 'Surangi Silva', 'Nugegoda', '986667654V', '0718288226', 'srsilva@gmail.com', '1998-06-14', 'Female', '2018-07-04', 'Computing', 'Computer Science', 'July', 'Literature', 'C', 'Geography', 'S', 'IT', 'S', 'S', '1648', '245365', NULL, NULL, NULL, 'Yes', 'No');

-- --------------------------------------------------------

--
-- Table structure for table `teach`
--

CREATE TABLE `teach` (
  `lecturer_no` varchar(10) NOT NULL,
  `module_code` varchar(10) NOT NULL,
  `day` varchar(15) NOT NULL,
  `venue` varchar(20) NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`) VALUES
('admin', '123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `degree`
--
ALTER TABLE `degree`
  ADD PRIMARY KEY (`degree_code`),
  ADD UNIQUE KEY `degree_code` (`degree_code`),
  ADD UNIQUE KEY `degree_title` (`degree_title`);

--
-- Indexes for table `faculty`
--
ALTER TABLE `faculty`
  ADD PRIMARY KEY (`name`);

--
-- Indexes for table `follow`
--
ALTER TABLE `follow`
  ADD PRIMARY KEY (`register_no`,`module_code`),
  ADD KEY `follow_fk_2` (`module_code`);

--
-- Indexes for table `instruct`
--
ALTER TABLE `instruct`
  ADD PRIMARY KEY (`instructor_no`,`module_code`),
  ADD KEY `instruct_fk_2` (`module_code`);

--
-- Indexes for table `instructor`
--
ALTER TABLE `instructor`
  ADD PRIMARY KEY (`instructor_no`),
  ADD UNIQUE KEY `instructor_no` (`instructor_no`);

--
-- Indexes for table `lecturer`
--
ALTER TABLE `lecturer`
  ADD PRIMARY KEY (`lecturer_no`),
  ADD UNIQUE KEY `lecturer_no` (`lecturer_no`);

--
-- Indexes for table `module`
--
ALTER TABLE `module`
  ADD PRIMARY KEY (`module_code`),
  ADD UNIQUE KEY `module_title` (`module_title`),
  ADD KEY `module_fk_1` (`degree`);

--
-- Indexes for table `results`
--
ALTER TABLE `results`
  ADD PRIMARY KEY (`register_no`,`module_code`),
  ADD KEY `results_fk_2` (`module_code`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`register_no`),
  ADD UNIQUE KEY `index_no` (`register_no`),
  ADD UNIQUE KEY `nic_no` (`nic_no`);

--
-- Indexes for table `teach`
--
ALTER TABLE `teach`
  ADD PRIMARY KEY (`lecturer_no`,`module_code`),
  ADD KEY `teach_fk_2` (`module_code`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `follow`
--
ALTER TABLE `follow`
  ADD CONSTRAINT `follow_fk_1` FOREIGN KEY (`register_no`) REFERENCES `student` (`register_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `follow_fk_2` FOREIGN KEY (`module_code`) REFERENCES `module` (`module_code`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `instruct`
--
ALTER TABLE `instruct`
  ADD CONSTRAINT `instruct_fk_1` FOREIGN KEY (`instructor_no`) REFERENCES `instructor` (`instructor_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `instruct_fk_2` FOREIGN KEY (`module_code`) REFERENCES `module` (`module_code`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `module`
--
ALTER TABLE `module`
  ADD CONSTRAINT `module_fk_1` FOREIGN KEY (`degree`) REFERENCES `degree` (`degree_title`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `results`
--
ALTER TABLE `results`
  ADD CONSTRAINT `results_fk_1` FOREIGN KEY (`register_no`) REFERENCES `student` (`register_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `results_fk_2` FOREIGN KEY (`module_code`) REFERENCES `module` (`module_code`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `teach`
--
ALTER TABLE `teach`
  ADD CONSTRAINT `teach_fk_1` FOREIGN KEY (`lecturer_no`) REFERENCES `lecturer` (`lecturer_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `teach_fk_2` FOREIGN KEY (`module_code`) REFERENCES `module` (`module_code`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
