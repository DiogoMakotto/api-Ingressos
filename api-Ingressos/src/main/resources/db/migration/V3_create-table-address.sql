CREATE TABLE address(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    city VARCHAR (100) NOT NULL,
    uf VARCHAR (100) NOT NULL,
    session_id UUID,
    FOREIGN KEY (session_id) REFERENCES session(id) ON DELETE CASCADE
)