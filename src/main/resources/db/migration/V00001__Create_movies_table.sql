CREATE TABLE `streaming_service`.`film` (
                                              `id` INT NOT NULL auto_increment,
                                              `film_id` INT,
                                              `film_name` VARCHAR(255) NULL,
                                              `year` INT NULL,
                                              `rating` FLOAT NULL,
                                              `genres` VARCHAR(1000) NULL,
#                                               `genres` JSON NULL,
                                              `description` VARCHAR(10000) NULL,
                                              PRIMARY KEY (`id`));
CREATE TABLE `streaming_service`.`genress` (
                                               `genres_id` INT NOT NULL,
                                               `genres_str` VARCHAR(100) NULL,
                                               PRIMARY KEY (`genres_id`));
INSERT INTO `streaming_service`.`genress` (`genres_id`, `genres_str`) VALUES ('1', 'триллер');
INSERT INTO `streaming_service`.`genress` (`genres_id`, `genres_str`) VALUES ('2', 'драма');
INSERT INTO `streaming_service`.`genress` (`genres_id`, `genres_str`) VALUES ('3', 'криминал');
INSERT INTO `streaming_service`.`genress` (`genres_id`, `genres_str`) VALUES ('4', 'мелодрама');
INSERT INTO `streaming_service`.`genress` (`genres_id`, `genres_str`) VALUES ('5', 'детектив');
INSERT INTO `streaming_service`.`genress` (`genres_id`, `genres_str`) VALUES ('6', 'фантастика');
INSERT INTO `streaming_service`.`genress` (`genres_id`, `genres_str`) VALUES ('7', 'приключения');
INSERT INTO `streaming_service`.`genress` (`genres_id`, `genres_str`) VALUES ('8', 'биография');
INSERT INTO `streaming_service`.`genress` (`genres_id`, `genres_str`) VALUES ('9', 'фильм-нуар');
INSERT INTO `streaming_service`.`genress` (`genres_id`, `genres_str`) VALUES ('10', 'вестерн');
INSERT INTO `streaming_service`.`genress` (`genres_id`, `genres_str`) VALUES ('11', 'боевик');
INSERT INTO `streaming_service`.`genress` (`genres_id`, `genres_str`) VALUES ('12', 'фэнтези');
INSERT INTO `streaming_service`.`genress` (`genres_id`, `genres_str`) VALUES ('13', 'комедия');
INSERT INTO `streaming_service`.`genress` (`genres_id`, `genres_str`) VALUES ('14', 'военный');
INSERT INTO `streaming_service`.`genress` (`genres_id`, `genres_str`) VALUES ('15', 'история');
INSERT INTO `streaming_service`.`genress` (`genres_id`, `genres_str`) VALUES ('16', 'музыка');
INSERT INTO `streaming_service`.`genress` (`genres_id`, `genres_str`) VALUES ('17', 'ужасы');
INSERT INTO `streaming_service`.`genress` (`genres_id`, `genres_str`) VALUES ('18', 'мультфильм');
INSERT INTO `streaming_service`.`genress` (`genres_id`, `genres_str`) VALUES ('19', 'семейный');
INSERT INTO `streaming_service`.`genress` (`genres_id`, `genres_str`) VALUES ('20', 'мюзикл');
INSERT INTO `streaming_service`.`genress` (`genres_id`, `genres_str`) VALUES ('21', 'спорт');
INSERT INTO `streaming_service`.`genress` (`genres_id`, `genres_str`) VALUES ('22', 'документальный');
INSERT INTO `streaming_service`.`genress` (`genres_id`, `genres_str`) VALUES ('23', 'короткометражка');
INSERT INTO `streaming_service`.`genress` (`genres_id`, `genres_str`) VALUES ('24', 'аниме');
INSERT INTO `streaming_service`.`genress` (`genres_id`, `genres_str`) VALUES ('26', 'новости');
INSERT INTO `streaming_service`.`genress` (`genres_id`, `genres_str`) VALUES ('27', 'концерт');
INSERT INTO `streaming_service`.`genress` (`genres_id`, `genres_str`) VALUES ('28', 'для взрослых');
INSERT INTO `streaming_service`.`genress` (`genres_id`, `genres_str`) VALUES ('29', 'церемония');
INSERT INTO `streaming_service`.`genress` (`genres_id`, `genres_str`) VALUES ('30', 'реальное ТВ');
INSERT INTO `streaming_service`.`genress` (`genres_id`, `genres_str`) VALUES ('31', 'игра');
INSERT INTO `streaming_service`.`genress` (`genres_id`, `genres_str`) VALUES ('32', 'ток-шоу');
INSERT INTO `streaming_service`.`genress` (`genres_id`, `genres_str`) VALUES ('33', 'детский');
