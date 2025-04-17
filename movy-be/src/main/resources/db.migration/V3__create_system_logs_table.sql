CREATE TABLE system_logs (
                             id UUID PRIMARY KEY,
                             action VARCHAR(255),
                             class_name VARCHAR(255),
                             method_name VARCHAR(255),
                             username VARCHAR(255),
                             message TEXT,
                             timestamp TIMESTAMP WITHOUT TIME ZONE,
                             payload TEXT
);