--liquibase formatted sql
--changeset LukaszRogowski:1
CREATE TABLE geolocation (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
device_id BIGINT NOT NULL,
latitude FLOAT NULL,
longitude FLOAT NULL,
created DATETIME NULL
);