-- ------------------
-- Schema DTUGRP16 --
---------------------
SET CURRENT SQLID = 'DTUGRP16';

----------
-- User --
----------
CREATE TABLE DTUGRP16.User (
    userID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    password VARCHAR(45) NOT NULL,
    type CHAR(1) NOT NULL,
    PRIMARY KEY (userID)
    ,
    CONSTRAINT bool CHECK (type in ('B', 'C'))
  );

-----------
-- Place --
-----------
CREATE TABLE DTUGRP16.Place (
    postal INT NOT NULL,
    city VARCHAR(45) NOT NULL,
    country VARCHAR(45) NOT NULL,
    PRIMARY KEY (postal)
  );

------------
-- Banker --
------------
CREATE TABLE DTUGRP16.Banker (
    bankerID INT NOT NULL,
    banker_name VARCHAR(45) NOT NULL,
    postal INT NOT NULL,
    PRIMARY KEY (bankerID)
 ,
    CONSTRAINT fk_userID FOREIGN KEY (bankerID) REFERENCES DTUGRP16. User (userID),
    CONSTRAINT fk_postal FOREIGN KEY (postal) REFERENCES DTUGRP16. Place (postal)
  );

CREATE INDEX fk_postal_idx ON DTUGRP16.Banker (postal ASC);

------------
-- Client --
------------
CREATE TABLE DTUGRP16.Client (
    clientID INT NOT NULL,
    cpr INT NOT NULL,
    first_name VARCHAR(45) NOT NULL,
    last_name VARCHAR(45) NOT NULL,
    banker INT NOT NULL,
    email VARCHAR(45),
    mobile VARCHAR(45),
    postal INT NOT NULL,
    street VARCHAR(45),
    street_no VARCHAR(45),
    PRIMARY KEY (clientID)
 ,
    CONSTRAINT fk_userID FOREIGN KEY (clientID) REFERENCES DTUGRP16. User (userID),
    CONSTRAINT fk_banker FOREIGN KEY (banker) REFERENCES DTUGRP16. Banker (bankerID),
    CONSTRAINT fk_postal FOREIGN KEY (postal) REFERENCES DTUGRP16. Place (postal)
  );

CREATE INDEX fk_banker_idx ON DTUGRP16.Client (banker ASC);

CREATE INDEX fk_city_idx ON DTUGRP16.Client (postal ASC);

-----------
-- RegNo --
-----------
CREATE TABLE DTUGRP16.RegNo (
    reg_no INT NOT NULL,
    bank_name VARCHAR(45) NOT NULL,
    PRIMARY KEY (reg_no)
  );

-------------
-- Account --
-------------
CREATE TABLE DTUGRP16.Account (
    account_number INT NOT NULL,
    reg_no INT NOT NULL,
    clientID INT NOT NULL,
    balance DECIMAL(5,2),
    interest_rate DECIMAL(5,2),
    PRIMARY KEY (account_number, clientID, reg_no)
 ,
    CONSTRAINT unq_acc UNIQUE(account_number),
    CONSTRAINT fk_reg_no FOREIGN KEY (reg_no) REFERENCES DTUGRP16. RegNo (reg_no),
    CONSTRAINT fk_clientID FOREIGN KEY (clientID) REFERENCES DTUGRP16. Client (clientID)
  );

CREATE INDEX fk_clientID_acc_idx ON DTUGRP16.Account (clientID ASC);

------------
-- Valuta --
------------
CREATE TABLE DTUGRP16.Valuta (
    valuta VARCHAR(45) NOT NULL,
    currency DECIMAL(5,2),
    PRIMARY KEY (valuta)
  );

-----------------
-- Transaction --
-----------------
CREATE TABLE DTUGRP16.Transaction (
    transactionID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    account_number INT NOT NULL,
    clientID INT NOT NULL,
    date_of_transaction DATE,
    recieve_account VARCHAR(45) NOT NULL,
    value DECIMAL(5,2) NOT NULL,
    valuta VARCHAR(45) NOT NULL,
    note CLOB,
    PRIMARY KEY (transactionID, account_number)
 ,
    CONSTRAINT fk_account_number FOREIGN KEY (account_number) REFERENCES DTUGRP16. Account (account_number),
    CONSTRAINT fk_clientID FOREIGN KEY (clientID) REFERENCES DTUGRP16. Client (clientID),
    CONSTRAINT fk_valuta FOREIGN KEY (valuta) REFERENCES DTUGRP16. Valuta (valuta)
  );

CREATE INDEX fk_clientID_trans_idx ON DTUGRP16.Transaction (clientID ASC);