CREATE TABLE `merchant` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(64) COLLATE utf8_bin NOT NULL,
    `logo_url` VARCHAR(256) COLLATE utf8_bin NOT NULL,
    `business_licence_url` VARCHAR(256) COLLATE utf8_bin NOT NULL,
    `phone` VARCHAR(64) COLLATE utf8_bin NOT NULL,
    `address` VARCHAR(256) COLLATE utf8_bin NOT NULL,
    `is_audit` BOOLEAN not null,
    PRIMARY KEY(`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 17 DEFAULT CHARSET = utf8;