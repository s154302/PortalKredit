-- <ScriptOptions statementTerminator=";" />
---------------------
-- Schema DTUGRP16 --
---------------------
SET CURRENT SQLID = 'DTUGRP16';

-----------
-- Places --
-----------
INSERT INTO DTUGRP16.PLACE (POSTAL, CITY, COUNTRY)
  VALUES (2800, 'Lyngby', 'DENMARK');
------------
-- Branch --
------------
INSERT INTO DTUGRP16.BRANCH (regNo, bankName, postal, country, street, phone)
  VALUES (6220, 'Lollands Bank', 2800, 'DENMARK', 'Algade', NULL);

-------------
-- Bankers --
-------------
INSERT INTO DTUGRP16.BANKER (BANKERID, PASSWORD, FIRSTNAME, LASTNAME, REGNO, EMAIL, MOBILE)
  VALUES ('000001B', 'Heimdal2', 'Simon', 'Heimdal', 6220, 'heimsebassen', NULL);


-------------
-- Clients --
-------------
INSERT INTO DTUGRP16.CLIENT (CLIENTID, PASSWORD, CPR, FIRST_NAME, LAST_NAME,
  EMAIL, MOBILE, STREET, BANKERID, POSTAL, COUNTRY)
  VALUES ('00000001C', 'Armstrong7', '2807942021', 'Alexander', 'Armstrong', NULL, NULL, NULL, '000001B', 2800, NULL);
  
------------------
-- Account Type --
------------------
INSERT INTO DTUGRP16.ACCOUNTTYPE (ACCOUNTTYPE, INTERESTRATE)
  VALUES ('Checkings', 2);

--------------
-- Currency --
--------------
INSERT INTO DTUGRP16.CURRENCY (CURRENCY, EXCHANGERATE)
  VALUES ('DKK', 7);

--------------
-- Accounts --
--------------
INSERT INTO DTUGRP16.ACCOUNT (ACCOUNTNUMBER, REGNO, ACCOUNTTYPE, CLIENTID, BALANCE, CURRENCY)
  VALUES (0123456789, 6220, 'Checkings', '00000001C', 20000, 'DKK');

-----------
-- Admin --
-----------
INSERT INTO DTUGRP16.ADMIN (ADMINID, PASSWORD)
  VALUES ('admin', 'admin');


