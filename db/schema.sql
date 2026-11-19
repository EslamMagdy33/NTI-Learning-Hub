-- Login App - Day 3 - the database and its one table.
-- Run it once in MySQL Workbench: File > Open SQL Script > Execute (the lightning icon).

CREATE DATABASE IF NOT EXISTS nti_learning_hub
  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;    -- utf8mb4: Arabic names work

USE nti_learning_hub;

CREATE TABLE IF NOT EXISTS users (
  id            INT AUTO_INCREMENT PRIMARY KEY,         -- MySQL numbers the rows: 1, 2, 3 ...
  full_name     VARCHAR(100) NOT NULL,
  email         VARCHAR(150) NOT NULL UNIQUE,           -- MySQL refuses the same email twice
  password_hash VARCHAR(255) NOT NULL                   -- a hash, never the password itself
);

-- Check it:
-- SELECT * FROM users;
