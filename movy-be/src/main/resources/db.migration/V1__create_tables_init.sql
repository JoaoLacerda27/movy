CREATE TABLE users (
                       id UUID PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       phone VARCHAR(20),
                       created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE deliveries (
                            id UUID PRIMARY KEY,
                            sender_id UUID NOT NULL,
                            recipient_name VARCHAR(100) NOT NULL,
                            recipient_address TEXT NOT NULL,
                            tracking_code VARCHAR(50) UNIQUE NOT NULL,
                            status VARCHAR(20) NOT NULL,
                            created_at TIMESTAMP DEFAULT NOW(),
                            FOREIGN KEY (sender_id) REFERENCES users(id)
);

CREATE TABLE delivery_events (
                                 id UUID PRIMARY KEY,
                                 delivery_id UUID NOT NULL,
                                 event_type VARCHAR(50) NOT NULL,
                                 description TEXT,
                                 location VARCHAR(100),
                                 timestamp TIMESTAMP DEFAULT NOW(),
                                 FOREIGN KEY (delivery_id) REFERENCES deliveries(id)
);

CREATE TABLE notifications (
                               id UUID PRIMARY KEY,
                               delivery_id UUID NOT NULL,
                               message TEXT NOT NULL,
                               read BOOLEAN DEFAULT FALSE,
                               created_at TIMESTAMP DEFAULT NOW(),
                               FOREIGN KEY (delivery_id) REFERENCES deliveries(id)
);
