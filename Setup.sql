-- <ScriptOptions statementTerminator=";" />
---------------------
-- Schema DTUGRP16 --
---------------------
SET CURRENT SQLID = 'DTUGRP16';

-----------
-- Place --
-----------
CREATE TABLE DTUGRP16.Place (
    postal INT NOT NULL,
    city VARCHAR(45) NOT NULL,
    country VARCHAR(45) NOT NULL,
    PRIMARY KEY (postal, country)
  );
  
------------
-- Branch --
------------
CREATE TABLE DTUGRP16. Branch (
	regNo INT NOT NULL,
	bankName VARCHAR(20) NOT NULL,
	postal INT NOT NULL,
	country VARCHAR(45) NOT NULL,
	street VARCHAR(45) NOT NULL,
	phone CHAR(11),
	PRIMARY KEY (regNo),
	FOREIGN KEY (postal, country) REFERENCES DTUGRP16. Place (postal, country)
);

------------
-- Banker --
------------
CREATE TABLE DTUGRP16.Banker (
    bankerID CHAR(7) NOT NULL,
    password VARCHAR(20) NOT NULL,
    firstName VARCHAR(45) NOT NULL,
    lastName VARCHAR(45) NOT NULL,
    regNo INT NOT NULL,
    email VARCHAR(45) NOT NULL,
    mobile CHAR(11),
    PRIMARY KEY (bankerID)
 ,
 	CONSTRAINT fk_banker_branch FOREIGN KEY (regNo) REFERENCES DTUGRP16. Branch (regNo)
  );

------------
-- Client --
------------
CREATE TABLE DTUGRP16.Client (
    clientID CHAR(9) NOT NULL,
    password VARCHAR(20) NOT NULL,
    cpr CHAR(10) NOT NULL,
    first_name VARCHAR(45) NOT NULL,
    last_name VARCHAR(45) NOT NULL,
    email VARCHAR(45),
    mobile CHAR(11),
    street VARCHAR(45),
    bankerID CHAR(7) NOT NULL,
    postal INT NOT NULL,
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
	interestRate DECIMAL(5,2),
	PRIMARY KEY(accountType)
);

-------------
-- Account --
-------------
CREATE TABLE DTUGRP16.Account (
    accountNumber CHAR(10) NOT NULL,
    regNo INT NOT NULL,
    accountType VARCHAR(45) NOT NULL,
    clientID CHAR(9) NOT NULL,
    balance DECIMAL(10,2),
    currency VARCHAR(10),
    PRIMARY KEY (accountNumber, regNo),
    FOREIGN KEY (clientID) REFERENCES DTUGRP16. Client (clientID),
    FOREIGN KEY (accountType) REFERENCES DTUGRP16. AccountType (accountType),
    FOREIGN KEY (currency) REFERENCES DTUGRP16. Currency (currency)
  );

------------
-- Currency --
------------
CREATE TABLE DTUGRP16.Currency (
    currency VARCHAR(10) NOT NULL,
    exchangeRate DECIMAL(5,2) NOT NULL,
    PRIMARY KEY (currency)
  );

-----------------
-- Transaction --
-----------------
CREATE TABLE DTUGRP16.Transaction (
    transactionID INT NOT NULL,
    accountNumber CHAR(10) NOT NULL,
    regNo INT NOT NULL,
    recieveAccount CHAR(10) NOT NULL,
    recieveRegNo INT NOT NULL,
    dateOfTransaction DATE,
    amount DECIMAL(5,2) NOT NULL,		-- Changed from value since value appears to be a reserved keyword
    currency VARCHAR(10) NOT NULL,
    note CLOB,
    PRIMARY KEY (transactionID, accountNumber) --Using both as PK so multiple accounts can use same transaction ID
 ,
    CONSTRAINT fk_transaction_currency FOREIGN KEY (currency) REFERENCES DTUGRP16. Currency (currency),
    CONSTRAINT fk_transaction_account1 FOREIGN KEY (accountNumber, regNo) REFERENCES DTUGRP16. Account (accountNumber, regNo),
    CONSTRAINT fk_transaction_account2 FOREIGN KEY (recieveAccount, recieveRegNo) REFERENCES DTUGRP16. Account (accountNumber, regNo)
  );
  
-----------
-- Admin --
-----------
CREATE TABLE DTUGRP16.Admin (
	adminID VARCHAR(25) NOT NULL,
	password VARCHAR(25) NOT NULL,
	PRIMARY KEY(adminID)
	);