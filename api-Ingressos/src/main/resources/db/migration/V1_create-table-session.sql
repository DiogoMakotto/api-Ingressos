CREATE EXCEPTION IF NOT EXISTS "pgcrypto";

CREATE TABLE session (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(250) NOT NULL,
    img_url VARCHAR(100) NOT NULL,
    session_url VARCHAR(100) NOT NULL,
    date TIMESTAMP NOT NULL,
    remote BOOLEAN NOT NULL
    );
