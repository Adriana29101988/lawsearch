CREATE TABLE IF NOT EXISTS law (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    version VARCHAR(50),
    created_date DATE
);

CREATE TABLE IF NOT EXISTS article (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT,
    law_id INTEGER NOT NULL,
    FOREIGN KEY (law_id) REFERENCES law(id)
);