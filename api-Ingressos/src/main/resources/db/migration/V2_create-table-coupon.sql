CREATE TABLE session (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    code VARCHAR(100) NOT NULL,
    discount INTEGER NOT NULL,
    valid TIMESTAMP NOT NULL,
    session_id UUID,
    FOREIGN KEY (session_id) REFERENCES session(id) ON DELETE CASCADE
    );