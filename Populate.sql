-- <ScriptOptions statementTerminator=";" />
---------------------
-- Schema DTUGRP16 --
---------------------
SET CURRENT SQLID = 'DTUGRP16';

-----------
-- Users --
-----------
INSERT INTO DTUGRP16.USER (PASSWORD, TYPE)
  VALUES ('DTU16', 'B');

INSERT INTO DTUGRP16.USER (PASSWORD, TYPE)
  VALUES ('DTU17', 'B');

;

INSERT INTO DTUGRP16.USER (PASSWORD, TYPE)
  VALUES ('DTU19', 'B');

INSERT INTO DTUGRP16.USER (PASSWORD, TYPE)
  VALUES ('DTU16', 'C');

INSERT INTO DTUGRP16.USER (PASSWORD, TYPE)
  VALUES ('DTU17', 'C');

INSERT INTO DTUGRP16.USER (PASSWORD, TYPE)
  VALUES ('DTU18', 'C');

INSERT INTO DTUGRP16.USER (PASSWORD, TYPE)
  VALUES ('DTU19', 'C');

-----------
-- Places --
-----------
INSERT INTO DTUGRP16.PLACE (POSTAL, CITY, COUNTRY)
  VALUES (2800, 'Lyngby', 'DENMARK');

-------------
-- Bankers --
-------------
INSERT INTO DTUGRP16.BANKER (BANKERID, BANKER_NAME, POSTAL)
  VALUES (1, 'Simon', 2800);

INSERT INTO DTUGRP16.BANKER (BANKERID, BANKER_NAME, POSTAL)
  VALUES (2, 'Emilie', 2800);

INSERT INTO DTUGRP16.BANKER (BANKERID, BANKER_NAME, POSTAL)
  VALUES (3, 'Mathias', 2800);

INSERT INTO DTUGRP16.BANKER (BANKERID, BANKER_NAME, POSTAL)
  VALUES (4, 'Alexander', 2800);

-------------
-- Clients --
-------------
INSERT INTO DTUGRP16.CLIENT (CLIENTID, CPR, FIRST_NAME, LAST_NAME,
  BANKER, POSTAL)
  VALUES (5, 5555555555, 'Simon', 'Heimdal', 1, 2800);

INSERT INTO DTUGRP16.CLIENT (CLIENTID, CPR, FIRST_NAME, LAST_NAME,
  BANKER, POSTAL)
  VALUES (6, 6666666666, 'Emilie', 'Dahl', 2, 2800);

INSERT INTO DTUGRP16.CLIENT (CLIENTID, CPR, FIRST_NAME, LAST_NAME,
  BANKER, POSTAL)
  VALUES (7, 7777777777, 'Mathias', 'Assmussen', 3, 2800);

INSERT INTO DTUGRP16.CLIENT (CLIENTID, CPR, FIRST_NAME, LAST_NAME,
  BANKER, EMAIL, MOBILE, POSTAL, STREET, STREET_NO)
  VALUES (8, 8888888888, 'Alexander', 'Armstrong', 4, 's154302@student.dtu.dk', '30868112', 2800, 'Kollegiebakken', 9);
  
-----------
-- RegNo --
-----------
INSERT INTO DTUGRP16.REGNO (REG_NO, BANK_NAME)
  VALUES (4571, 'Lollands Bank');
  
--------------
-- Accounts --
--------------
INSERT INTO DTUGRP16.ACCOUNT (ACCOUNT_NUMBER, REG_NO, CLIENTID, BALANCE, INTEREST_RATE)
  VALUES (0123456789, 4571, 8, 10000, 1.05);

------------
-- Valuta --
------------
INSERT INTO DTUGRP16.VALUTA (VALUTA, CURRENCY)
  VALUES ('DKK', 7.5);



