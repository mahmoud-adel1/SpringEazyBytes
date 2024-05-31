CREATE DATABASE eazyschool;

use eazyschool;

CREATE TABLE IF NOT EXISTS `contact_msg` (
    `contact_id` int AUTO_INCREMENT  PRIMARY KEY,
    `name` varchar(100) NOT NULL,
    `mobile_num` varchar(10) NOT NULL,
    `email` varchar(100) NOT NULL,
    `subject` varchar(100) NOT NULL,
    `message` varchar(500) NOT NULL,
    `status` varchar(10) NOT NULL,
    `created_at` TIMESTAMP NOT NULL,
    `created_by` varchar(50) NOT NULL,
    `updated_at` TIMESTAMP DEFAULT NULL,
    `updated_by` varchar(50) DEFAULT NULL
    );

set sql_require_primary_key = off;

CREATE TABLE IF NOT EXISTS `holidays` (
    `day` varchar(20) NOT NULL,
    `reason` varchar(100) NOT NULL,
    `type` varchar(20) NOT NULL,
    `created_at` TIMESTAMP NOT NULL,
    `created_by` varchar(20) NOT NULL,
    `updated_at` TIMESTAMP DEFAULt NULL,
    `updated_by` varchar(20) DEFAULT NULL
    );

CREATE TABLE IF NOT EXISTS `roles` (
    `role_id` INT NOT NULL AUTO_INCREMENT,
    `role_name` VARCHAR(50) NOT NULL ,
    `created_at` TIMESTAMP NOT NULL ,
    `created_by` VARCHAR(50) NOT NULL ,
    `updated_at` TIMESTAMP DEFAULT NULL ,
    `updated_by` VARCHAR(50) DEFAULT NULL,
    PRIMARY KEY (`role_id`)
);

CREATE TABLE IF NOT EXISTS `address` (
    `address_id` INT NOT NULL AUTO_INCREMENT ,
    `address1` VARCHAR(50) NOT NULL ,
    `address2` VARCHAR(50) DEFAULT NULL ,
    `city` VARCHAR(50) NOT NULL ,
    `state` VARCHAR(50) NOT NULL ,
    `zip_code` INT NOT NULL ,
    `created_at` TIMESTAMP NOT NULL ,
    `created_by` VARCHAR(50) NOT NULL ,
    `updated_at` TIMESTAMP DEFAULT NULL ,
    `updated_by` VARCHAR(50) DEFAULT NULL ,
    PRIMARY KEY (`address_id`)
);

CREATE TABLE IF NOT EXISTS `person` (
    `Person_id` INT NOT NULL AUTO_INCREMENT ,
    `name` VARCHAR(100) NOT NULL ,
    `mobile_number` VARCHAR(20) NOT NULL ,
    `email` VARCHAR(50) NOT NULL ,
    `password` VARCHAR(200) NOT NULL ,
    `role_id` INT NOT NULL ,
    `address_id` INT DEFAULT NULL ,
    `created_at` TIMESTAMP NOT NULL ,
    `created_by` VARCHAR(50) NOT NULL ,
    `updated_at` TIMESTAMP DEFAULT NULL ,
    `updated_by` VARCHAR(50) DEFAULT NULL ,
    PRIMARY KEY (`Person_id`) ,
    FOREIGN KEY (`role_id`) REFERENCES role (`role_id`) ,
    FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`)
);

CREATE TABLE IF NOT EXISTS `class` (
    `class_id` INT NOT NULL AUTO_INCREMENT ,
    `name` VARCHAR(50) NOT NULL ,
    `created_at` TIMESTAMP NOT NULL ,
    `created_by` VARCHAR(50) NOT NULL ,
    `updated_at` TIMESTAMP DEFAULT NULL ,
    `updated_by` VARCHAR(50) DEFAULT NULL ,
    PRIMARY KEY (`class_id`)
);

ALTER TABLE `person`
ADD COLUMN `class_id` INT DEFAULT NULL AFTER `address_id`,
ADD CONSTRAINT `FK_CLASS_CLASS_ID` FOREIGN KEY (`class_id`) REFERENCES `class`(`class_id`);

