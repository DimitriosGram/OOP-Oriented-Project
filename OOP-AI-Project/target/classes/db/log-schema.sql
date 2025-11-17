# logapp schema

CREATE DATABASE IF NOT EXISTS logapp;
USE logapp;


CREATE TABLE IF NOT EXISTS log_file (
id INT PRIMARY KEY auto_increment,
filename VARCHAR(45) NOT NULL,
uploaded_at DATETIME NOT NULL,
detected_source_type VARCHAR(20),
status VARCHAR(20) NOT NULL DEFAULT 'UPLOADED'
);

CREATE TABLE IF NOT EXISTS log_entry (
id INT PRIMARY KEY AUTO_INCREMENT,
log_file_id INT NOT NULL,
line_number INT NOT NULL,
raw_text TEXT,
CONSTRAINT `fk_log_entry_log_file` 
	FOREIGN KEY (log_file_id)
	REFERENCES log_file (id)
    ON UPDATE CASCADE,
INDEX `fk_log_entry_log_file_idx` (log_file_id)
);

CREATE TABLE IF NOT EXISTS parsed_log (
id INT PRIMARY KEY AUTO_INCREMENT,
log_file_id INT NOT NULL,
source_type VARCHAR(20) NOT NULL,
parsed_at DATETIME NOT NULL,
total_entries INT,
CONSTRAINT `fk_parsed_log_log_file` 
	FOREIGN KEY (log_file_id)
	REFERENCES log_file (id)
    ON UPDATE CASCADE,
INDEX `fk_parsed_log_log_file_idx` (log_file_id)
);

CREATE TABLE IF NOT EXISTS parsed_log_entry (
id INT PRIMARY KEY AUTO_INCREMENT,
parsed_log_id INT NOT NULL,
line_number INT NOT NULL,
timestamp DATETIME,
level VARCHAR(25),
message VARCHAR(200),
raw_text TEXT,
CONSTRAINT `fk_parsed_log_entry_parsed_log`
	FOREIGN KEY (parsed_log_id)
	REFERENCES parsed_log (id)
    ON UPDATE CASCADE,
INDEX `fk_parsed_log_entry_parsed_log_idx` (parsed_log_id)
);

