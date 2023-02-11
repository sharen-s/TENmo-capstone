BEGIN TRANSACTION;

DROP TABLE IF EXISTS tenmo_user, account, transfers;

DROP SEQUENCE IF EXISTS seq_user_id, seq_account_id, seq_transfer_id;


-- Sequence to start user_id values at 1001 instead of 1
CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  START WITH 1001
  NO MAXVALUE;

CREATE TABLE tenmo_user (
	user_id int NOT NULL DEFAULT nextval('seq_user_id'),
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	CONSTRAINT PK_tenmo_user PRIMARY KEY (user_id),
	CONSTRAINT UQ_username UNIQUE (username)
);

-- Sequence to start account_id values at 2001 instead of 1
-- Note: Use similar sequences with unique starting values for additional tables
CREATE SEQUENCE seq_account_id
  INCREMENT BY 1
  START WITH 2001
  NO MAXVALUE;

CREATE TABLE account (
	account_id int NOT NULL DEFAULT nextval('seq_account_id'),
	user_id int NOT NULL,
	balance numeric(13, 2) NOT NULL,
	CONSTRAINT PK_account PRIMARY KEY (account_id),
	CONSTRAINT FK_account_tenmo_user FOREIGN KEY (user_id) REFERENCES tenmo_user (user_id)
);


-- Sequence to start account_id values at 2001 instead of 1
-- Note: Use similar sequences with unique starting values for additional tables
CREATE SEQUENCE seq_transfer_id
  INCREMENT BY 1
  START WITH 3001
  NO MAXVALUE;

CREATE TABLE transfers (
	transfer_id int NOT NULL DEFAULT nextval('seq_transfer_id'),
	sender_id int NOT NULL,
	receiver_id int NOT NULL,
	transfer_amount int NOT NULL,
	CONSTRAINT PK_transfers PRIMARY KEY (transfer_id),
	CONSTRAINT FK_transfers_sender_tenmo_user FOREIGN KEY (sender_id) REFERENCES tenmo_user (user_id),
	CONSTRAINT FK_transfers_receiver_tenmo_user FOREIGN KEY (receiver_id) REFERENCES tenmo_user (user_id)
);

--SELECT * FROM transfers;

--INSERT INTO transfers (sender_id, receiver_id, transfer_amount) VALUES (1001, 1002, 500) RETURNING transfer_id

--SELECT * FROM transfers;

-- SELECT * FROM tenmo_user;

COMMIT;
