CREATE TABLE `location_history` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Primary key.',
  `domain` varchar(255) NOT NULL COMMENT 'FK to the company.',
  `user_id` varchar(255) NOT NULL COMMENT 'User Id.',
  `client_timestamp_utc` timestamp NOT NULL COMMENT 'Client timestamp of when location data was generated.',
  `server_timestamp_utc` timestamp NOT NULL COMMENT 'Server timestamp of when location data was received.',
  `longitude` double DEFAULT NULL COMMENT 'Longitude of the location.',
  `latitude` double DEFAULT NULL COMMENT 'Latitude of the location.',
  PRIMARY KEY (`id`),
  KEY `idx_location_history_domain` (`domain`),
  KEY `idx_location_history_domain_user_id` (`domain`,`user_id`),
  CONSTRAINT `fk_location_history_domain` FOREIGN KEY (`domain`) REFERENCES `ids_db`.`company` (`domain`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8