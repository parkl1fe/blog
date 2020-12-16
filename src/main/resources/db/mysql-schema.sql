DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS article_stats;
DROP TABLE IF EXISTS article;
DROP TABLE IF EXISTS user;

CREATE TABLE user (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `avatar` varchar(255),
  `username` varchar(255) UNIQUE NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL
);

CREATE TABLE `article` (
                           `id` bigint PRIMARY KEY AUTO_INCREMENT,
                           `user_id` bigint NOT NULL,
                           `created` timestamp NOT NULL,
                           `title` varchar(255) NOT NULL,
                           `description` text NOT NULL,
                           `content` text NOT NULL
);

CREATE TABLE `article_stats` (
                                 `id` bigint PRIMARY KEY AUTO_INCREMENT,
                                 `article_id` bigint UNIQUE NOT NULL,
                                 `views` int,
                                 `score` int
);

CREATE TABLE `comment` (
                           `id` bigint PRIMARY KEY AUTO_INCREMENT,
                           `article_id` bigint NOT NULL,
                           `user_id` bigint NOT NULL,
                           `created` timestamp NOT NULL,
                           `content` text NOT NULL
);

ALTER TABLE `article` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
ALTER TABLE `article_stats` ADD FOREIGN KEY (`article_id`) REFERENCES `article` (`id`);
ALTER TABLE `comment` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
ALTER TABLE `comment` ADD FOREIGN KEY (`article_id`) REFERENCES `article` (`id`);
