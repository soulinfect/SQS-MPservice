CREATE TABLE spaces (
    id BIGSERIAL PRIMARY KEY,
    external_id VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(50),
    action VARCHAR(50),
    payload_json TEXT,
    deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE groups (
    id BIGSERIAL PRIMARY KEY,
    external_id VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(50),
    action VARCHAR(50),
    payload_json TEXT,
    deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE users (
   id BIGSERIAL PRIMARY KEY,
   external_id VARCHAR(255) NOT NULL UNIQUE,
   name VARCHAR(255) NOT NULL,
   description TEXT,
   status VARCHAR(50),
   action VARCHAR(50),
   payload_json TEXT,
   deleted BOOLEAN DEFAULT FALSE,
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

   email VARCHAR(255) NOT NULL UNIQUE,
   first_name VARCHAR(100),
   last_name VARCHAR(100),
   group_id BIGINT REFERENCES groups(id)
);

CREATE TABLE forms (
   id BIGSERIAL PRIMARY KEY,
   external_id VARCHAR(255) NOT NULL UNIQUE,
   name VARCHAR(255) NOT NULL,
   description TEXT,
   status VARCHAR(50),
   action VARCHAR(50),
   payload_json TEXT,
   deleted BOOLEAN DEFAULT FALSE,
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

   space_id BIGINT REFERENCES spaces(id)
);

CREATE TABLE sites (
   id BIGSERIAL PRIMARY KEY,
   external_id VARCHAR(255) NOT NULL UNIQUE,
   name VARCHAR(255) NOT NULL,
   description TEXT,
   status VARCHAR(50),
   action VARCHAR(50),
   payload_json TEXT,
   deleted BOOLEAN DEFAULT FALSE,
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

   s3_object_key VARCHAR(500)
);

CREATE INDEX idx_users_group_id ON users(group_id);
CREATE INDEX idx_forms_space_id ON forms(space_id);