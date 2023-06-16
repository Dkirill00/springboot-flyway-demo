CREATE TABLE `streaming_service`.`film` (
                                              `id` INT NOT NULL auto_increment,
                                              `film_id` INT,
                                              `film_name` VARCHAR(255) NULL,
                                              `year` INT NULL,
                                              `rating` FLOAT NULL,
                                              `description` VARCHAR(10000) NULL,
                                              PRIMARY KEY (`id`));

