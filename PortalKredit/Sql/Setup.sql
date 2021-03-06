-- <ScriptOptions statementTerminator=";" />
---------------------
-- Schema DTUGRP16 --
---------------------
SET CURRENT SQLID = 'DTUGRP16';

-----------
-- Place --
-----------
CREATE TABLE DTUGRP16.Place (
    postal VARCHAR(10) NOT NULL,
    city VARCHAR(45) NOT NULL,
    country VARCHAR(45) NOT NULL,
    PRIMARY KEY (postal, country)
  );
  
------------
-- Branch --
------------
CREATE TABLE DTUGRP16. Branch (
	regNo VARCHAR(6) NOT NULL,
	bankName VARCHAR(20) NOT NULL,
	postal VARCHAR(10) NOT NULL,
	country VARCHAR(45) NOT NULL,
	street VARCHAR(45) NOT NULL,
	phone VARCHAR(20),
	PRIMARY KEY (regNo),
	FOREIGN KEY (postal, country) REFERENCES DTUGRP16. Place (postal, country)
);

------------
-- Banker --
------------
CREATE TABLE DTUGRP16.Banker (
    bankerID CHAR(7) NOT NULL,
    password VARCHAR(60) NOT NULL,
    firstName VARCHAR(45) NOT NULL,
    lastName VARCHAR(45) NOT NULL,
    regNo VARCHAR(6) NOT NULL,
    email VARCHAR(45) NOT NULL,
    mobile VARCHAR(20),
    PRIMARY KEY (bankerID)
 ,
 	CONSTRAINT fk_banker_branch FOREIGN KEY (regNo) REFERENCES DTUGRP16. Branch (regNo)
  );

------------
-- Client --
------------
CREATE TABLE DTUGRP16.Client (
    clientID CHAR(9) NOT NULL,
    password VARCHAR(60) NOT NULL,
    cpr CHAR(10) NOT NULL,
    first_name VARCHAR(45) NOT NULL,
    last_name VARCHAR(45) NOT NULL,
    email VARCHAR(45),
    mobile VARCHAR(20),
    street VARCHAR(45),
    bankerID CHAR(7) NOT NULL,
    postal VARCHAR(10) NOT NULL,
    country VARCHAR(45),
    
    PRIMARY KEY (clientID),
    FOREIGN KEY (postal, country) REFERENCES DTUGRP16. Place (postal, country),
	FOREIGN KEY (bankerID) REFERENCES DTUGRP16. Banker (bankerID)
  );

------------------
-- Account Type --
------------------
CREATE TABLE DTUGRP16.AccountType (
	accountType VARCHAR(45) NOT NULL,
	interestRate DECIMAL(5,4) NOT NULL,
	PRIMARY KEY(accountType)
);
------------
-- Currency --
------------
CREATE TABLE DTUGRP16.Currency (
    currency CHAR(3) NOT NULL,
    exchangeRate DECIMAL(9,4) NOT NULL,
    PRIMARY KEY (currency)
  );

-------------
-- Account --
-------------
CREATE TABLE DTUGRP16.Account (
    accountNumber CHAR(10) NOT NULL,
    regNo VARCHAR(6) NOT NULL,
    accountName VARCHAR(25),
    accountType VARCHAR(45) NOT NULL,
    clientID CHAR(9) NOT NULL,
    balance DECIMAL(12,2),
    currency CHAR(3),
    interest DECIMAL(20,4),
    PRIMARY KEY (accountNumber, regNo),
    FOREIGN KEY (clientID) REFERENCES DTUGRP16. Client (clientID),
    FOREIGN KEY (accountType) REFERENCES DTUGRP16. AccountType (accountType),
    FOREIGN KEY (currency) REFERENCES DTUGRP16. Currency (currency)
  );

-----------------
-- Transaction --
-----------------
CREATE TABLE "DTUGRP16"."TRANSACTION" (
    transactionID VARCHAR(60) NOT NULL,
    accountNumber CHAR(10) NOT NULL,
    regNo VARCHAR(6) NOT NULL,
    recieveAccount CHAR(10) NOT NULL,
    recieveRegNo VARCHAR(6) NOT NULL,
    dateOfTransaction TIMESTAMP NOT NULL,
    amount DECIMAL(10,2) NOT NULL,		-- Changed from value since value appears to be a reserved keyword
    currency CHAR(3) NOT NULL,
    note VARCHAR(3000),
    balance DECIMAL(12,2)NOT NULL,
    PRIMARY KEY (transactionID, accountNumber, regNo, recieveAccount, recieveRegNo, dateOfTransaction) --Using both as PK so multiple accounts can use same transaction ID
 ,
    FOREIGN KEY (currency) REFERENCES DTUGRP16. Currency (currency),
    FOREIGN KEY (accountNumber, regNo) REFERENCES DTUGRP16. Account (accountNumber, regNo),
    FOREIGN KEY (recieveAccount, recieveRegNo) REFERENCES DTUGRP16. Account (accountNumber, regNo)
  );
-----------
-- TRANSACTIONSOLD --
-----------
CREATE TABLE "DTUGRP16"."TRANSACTIONOLD" (
    transactionID VARCHAR(60) NOT NULL,
    accountNumber CHAR(10) NOT NULL,
    regNo VARCHAR(6) NOT NULL,
    recieveAccount CHAR(10) NOT NULL,
    recieveRegNo VARCHAR(6) NOT NULL,
    dateOfTransaction TIMESTAMP NOT NULL,
    amount DECIMAL(10,2) NOT NULL,		-- Changed from value since value appears to be a reserved keyword
    currency CHAR(3) NOT NULL,
    note VARCHAR(3000),
    balance DECIMAL(12,2)NOT NULL,
    PRIMARY KEY (transactionID, accountNumber, regNo, recieveAccount, recieveRegNo, dateOfTransaction) --Using both as PK so multiple accounts can use same transaction ID
 ,
    FOREIGN KEY (currency) REFERENCES DTUGRP16. Currency (currency),
    FOREIGN KEY (accountNumber, regNo) REFERENCES DTUGRP16. Account (accountNumber, regNo),
    FOREIGN KEY (recieveAccount, recieveRegNo) REFERENCES DTUGRP16. Account (accountNumber, regNo)
  );  
-----------
-- Admin --
-----------
--CREATE TABLE DTUGRP16.Admin (
	--adminID VARCHAR(25) NOT NULL,
--	password VARCHAR(60) NOT NULL,
	--PRIMARY KEY(adminID)
	--);