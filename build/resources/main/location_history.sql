CREATE TABLE `location_history` (
	`id` INT(10) NOT NULL AUTO_INCREMENT COMMENT 'Id of the row.',
	`userID` VARCHAR(100) NOT NULL COMMENT 'User Id.',
	`timestamp` TIMESTAMP NOT NULL COMMENT 'Timestamp of the action',
	`longitude` DOUBLE COMMENT 'Longitude of at given timestamp.',
    `latitude` DOUBLE COMMENT 'Latitude of at given timestamp.',
    `info` JSON COMMENT 'Additional information at timestamp.',
    `domain` VARCHAR(250) COMMENT 'Domain of the user.',
	PRIMARY KEY (`id`)
);
