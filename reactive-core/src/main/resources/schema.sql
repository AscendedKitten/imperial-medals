DROP TABLE IF EXISTS member;

CREATE TABLE IF NOT EXISTS member (
    id SERIAL PRIMARY KEY,
    uuid UUID,
    medals text[]
);