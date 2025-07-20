DROP TABLE IF EXISTS article;
DROP TABLE IF EXISTS law;
DROP TABLE IF EXISTS users;

CREATE TABLE law (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    version VARCHAR(10),
    created_date DATE
);

CREATE TABLE article (
    id SERIAL PRIMARY KEY,
    article_number VARCHAR(255) NOT NULL,
    content TEXT,
    law_id INTEGER NOT NULL,
    CONSTRAINT fk_law FOREIGN KEY (law_id) REFERENCES law(id) ON DELETE CASCADE
);

CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  username VARCHAR(100) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(50) NOT NULL
);
