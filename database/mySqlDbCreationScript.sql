CREATE SCHEMA IF NOT EXISTS `java-real-practice-insurance`
    DEFAULT CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE `java-real-practice-insurance`;

CREATE TABLE IF NOT EXISTS `classifiers` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(200) NOT NULL,
  `description` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;

CREATE UNIQUE INDEX `ix_classifiers_title` ON `classifiers` (`title`);

CREATE TABLE IF NOT EXISTS `classifier_values` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `classifier_id` BIGINT NOT NULL,
  `ic` VARCHAR(200) NOT NULL,
  `description` VARCHAR(500) NOT NULL,
  PRIMARY KEY (id)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;

ALTER TABLE `classifier_values`
ADD FOREIGN KEY (`classifier_id`) REFERENCES `classifiers`(`id`);

CREATE UNIQUE INDEX `ix_classifier_values_ic`
ON `classifier_values` (`ic`);



