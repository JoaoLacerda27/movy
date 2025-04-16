-- Tabela USERS
CREATE TABLE users (
                       id uuid NOT NULL,
                       created_at timestamp DEFAULT NOW(),
                       name varchar(120) NOT NULL,
                       email varchar(120) NOT NULL,
                       password varchar(120) NOT NULL,
                       phone varchar(20),
                       role varchar(120) NOT NULL,
                       CONSTRAINT pk_users PRIMARY KEY (id),
                       CONSTRAINT uc_users_email UNIQUE (email)
);

-- Tabela RECIPIENTS
CREATE TABLE recipients (
                            id uuid NOT NULL,
                            name varchar(100) NOT NULL,
                            address text NOT NULL,
                            created_at timestamp DEFAULT NOW(),
                            CONSTRAINT pk_recipients PRIMARY KEY (id)
);

-- Tabela DELIVERIES
CREATE TABLE deliveries (
                            id uuid NOT NULL,
                            tracking_code varchar(255) NOT NULL,
                            created_at timestamp DEFAULT NOW(),
                            status varchar(255) NOT NULL,
                            recipient_id uuid NOT NULL,
                            sender_id uuid NOT NULL,
                            CONSTRAINT pk_deliveries PRIMARY KEY (id),
                            CONSTRAINT uc_delivery_tracking_code UNIQUE (tracking_code),
                            CONSTRAINT fk_deliveries_recipient_id FOREIGN KEY (recipient_id) REFERENCES recipients(id),
                            CONSTRAINT fk_deliveries_sender_id FOREIGN KEY (sender_id) REFERENCES users(id)
);

-- Tabela DELIVERY_EVENTS
CREATE TABLE delivery_events (
                                 id uuid NOT NULL,
                                 delivery_id uuid NOT NULL,
                                 event_type varchar(255) NOT NULL,
                                 description text,
                                 location varchar(100),
                                 timestamp timestamp DEFAULT NOW(),
                                 CONSTRAINT pk_delivery_events PRIMARY KEY (id),
                                 CONSTRAINT fk_delivery_events_delivery_id FOREIGN KEY (delivery_id) REFERENCES deliveries(id)
);

-- Tabela NOTIFICATIONS
CREATE TABLE notifications (
                               id uuid NOT NULL,
                               delivery_id uuid NOT NULL,
                               message text NOT NULL,
                               read boolean DEFAULT FALSE NOT NULL,
                               created_at timestamp DEFAULT NOW(),
                               CONSTRAINT pk_notifications PRIMARY KEY (id),
                               CONSTRAINT fk_notifications_delivery_id FOREIGN KEY (delivery_id) REFERENCES deliveries(id)
);
