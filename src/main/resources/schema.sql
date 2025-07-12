DROP TABLE IF EXISTS article;
DROP TABLE IF EXISTS law;

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