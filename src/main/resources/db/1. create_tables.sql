SET NAMES utf8 ;

DROP TABLE IF EXISTS `cities`;

 SET character_set_client = utf8mb4 ;
CREATE TABLE `cities` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `companies`;

 SET character_set_client = utf8mb4 ;
CREATE TABLE `companies` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `logo_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `site_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `reviews`;

 SET character_set_client = utf8mb4 ;
CREATE TABLE `reviews` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_rating_total` double NOT NULL,
  `created` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `feedback_comment` varchar(255) DEFAULT NULL,
  `feedback_detailization` int(11) NOT NULL,
  `feedback_on_time` int(11) NOT NULL,
  `feedback_rating_total` double NOT NULL,
  `hr_attitude` int(11) NOT NULL,
  `hr_comment` varchar(255) DEFAULT NULL,
  `hr_ice_brake` int(11) NOT NULL,
  `hr_impression` int(11) NOT NULL,
  `hr_name` varchar(255) DEFAULT NULL,
  `hr_punctuality` int(11) NOT NULL,
  `hr_rating_total` double NOT NULL,
  `start_date` datetime DEFAULT NULL,
  `tech_attitude` int(11) NOT NULL,
  `tech_comment` varchar(255) DEFAULT NULL,
  `tech_ice_brake` int(11) NOT NULL,
  `tech_impression` int(11) NOT NULL,
  `tech_interviewer_name` varchar(255) DEFAULT NULL,
  `tech_questions_quality` int(11) NOT NULL,
  `tech_rating_total` double NOT NULL,
  `updated` datetime DEFAULT NULL,
  `vacancy_name` varchar(255) DEFAULT NULL,
  `city_id` bigint(20) NOT NULL,
  `company_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `city_id_fk` (`city_id`),
  KEY `company_id_fk` (`company_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;