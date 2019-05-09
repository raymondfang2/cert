CREATE TABLE CERT_EXAM_RESULT (
  ID         BIGINT(20) NOT NULL AUTO_INCREMENT,
  DATA_SOURCE VARCHAR(50),
  CREATE_DATE    DATE,
  UPDATE_DATE    DATE,
  CANDIDATE_EMAIL     VARCHAR(250),
  CANDIDATE_FIRSTNAME VARCHAR(50),
  CANDIDATE_LASTNAME  VARCHAR(50),
  CANDIDATE_COMPANY   VARCHAR(100),
  SITE_REGION   VARCHAR(50),
  SITE_COUNTRY  VARCHAR(50),
  EXAM_CODE VARCHAR(100),
  EXAM_TITLE VARCHAR(250),
  EXAM_DATE  DATE,
  SCORE BIGINT(20),
  GRADE VARCHAR(50),

  PRIMARY KEY (ID)
)
  ENGINE = innodb
  DEFAULT CHARSET = utf8;

CREATE UNIQUE INDEX UNIQUE_EXAM_ENTRY ON CERT_EXAM_RESULT (
  DATA_SOURCE,
  CANDIDATE_EMAIL,
  EXAM_CODE,
  EXAM_DATE
);

#deprecated
CREATE TABLE EXAM_CODE_MAP (
  ID         INT(20) NOT NULL AUTO_INCREMENT,
  DATA_SOURCE VARCHAR(50),
  EXAM_CODE VARCHAR(100),
  PIVOTAL_CODE VARCHAR(100),
  CREATE_DATE    DATE,
  UPDATE_DATE    DATE,

  PRIMARY KEY (ID)
)
  ENGINE = innodb
  DEFAULT CHARSET = utf8;

CREATE UNIQUE INDEX UNIQUE_EXAM_CODE_MAP ON EXAM_CODE_MAP (
  DATA_SOURCE,
  EXAM_CODE,
  PIVOTAL_CODE
);

#deprecated
CREATE TABLE FEED (
 ID         BIGINT(20) NOT NULL AUTO_INCREMENT,
 FEED_SOURCE VARCHAR(50),
 FEED_START DATETIME,
 FEED_END DATETIME,
 FEED_STATUS VARCHAR(50),

PRIMARY KEY (ID)
);

/*STAGE TABLE MAKE EVERY FIELDS VARCHAR, CONVERTION DONE IN MERGE STORE PROC*/
/*SEQUENCE IS 1. DELETE STAGE, 2. UPLOAD TO STAGE, 3. MERGE TO MAIN*/
DROP TABLE CERT_EXAM_STAGE;

CREATE TABLE CERT_EXAM (
	ID         BIGINT(20) NOT NULL AUTO_INCREMENT,
    EXAM_CENTER	VARCHAR(50),
    FIRST_NAME 	VARCHAR(50),
	LAST_NAME	VARCHAR(50),
	IE_CANDIDATE_ID	VARCHAR(50),
	CANDIDATE_EMAIL	VARCHAR(250),
	COMPANY_NAME	VARCHAR(250),
	TITLE	VARCHAR(50),
	FULL_NAME	VARCHAR(250),
	HOME_ADDRESS_1	VARCHAR(250),
	HOME_ADDRESS_2	VARCHAR(250),
	CITY	VARCHAR(50),
	STATE_PROVINCE	VARCHAR(50),
	ZIP	VARCHAR(50),
	COUNTRY_CODE	VARCHAR(50),
	PHONE	VARCHAR(50),
	SPONSOR_NAME	VARCHAR(50),
	EXAM_NAME	VARCHAR(250),
	EXAM_LMS_ID	VARCHAR(50),
	EXAM_CODE		VARCHAR(50),
	EXAM_DURATION VARCHAR(50),
	TRANSACTION_DATE	VARCHAR(50),
	ADMIN_DATE	VARCHAR(50),
	EXAM_START_DATE_TIME VARCHAR(50),
	EXAM_COMP_DATE_TIME VARCHAR(50),
	DELIVERY_COUNTRY VARCHAR(50),
	EXAM_STATUS	VARCHAR(50),
	ATTEMP_NO	VARCHAR(50),
	EXAM_SCORE VARCHAR(50),
	EXAM_PERCENTAGE VARCHAR(50),
	EXAM_RESULT VARCHAR(50),
	CONFIRMATION_CODE  VARCHAR(50),
	PROMO_CODE  VARCHAR(50),
	PAYMENT_METHOD VARCHAR(50),
	TAXES VARCHAR(50),
	EXAM_FEE VARCHAR(50),
	PAYMENT_AMOUNT VARCHAR(50),
	PO_BILLING_TYPE VARCHAR(50),
	ECREDIT_CODE VARCHAR(50),
	RESERVATION_GUID VARCHAR(250),
	AGREE_CHECK_DATE_TIME VARCHAR(50),

    PRIMARY KEY (ID)
);

/*The main table */

CREATE TABLE CERT_EXAM (
    EXAM_CENTER	VARCHAR(50),
    FIRST_NAME 	VARCHAR(50),
	LAST_NAME	VARCHAR(50),
	IE_CANDIDATE_ID	VARCHAR(50),
	CANDIDATE_EMAIL	VARCHAR(250),
	COMPANY_NAME	VARCHAR(250),
	TITLE	VARCHAR(50),
	FULL_NAME	VARCHAR(250),
	HOME_ADDRESS_1	VARCHAR(250),
	HOME_ADDRESS_2	VARCHAR(250),
	CITY	VARCHAR(50),
	STATE_PROVINCE	VARCHAR(50),
	ZIP	VARCHAR(50),
	COUNTRY_CODE	VARCHAR(50),
	PHONE	VARCHAR(50),
	SPONSOR_NAME	VARCHAR(50),
	EXAM_NAME	VARCHAR(250),
	EXAM_LMS_ID	VARCHAR(50),
	EXAM_CODE		VARCHAR(50),
	EXAM_DURATION VARCHAR(50),
	TRANSACTION_DATE	DATETIME,
	ADMIN_DATE	DATETIME,
	EXAM_START_DATE_TIME DATETIME,
	EXAM_COMP_DATE_TIME DATETIME,
	DELIVERY_COUNTRY VARCHAR(50),
	EXAM_STATUS	VARCHAR(50),
	ATTEMP_NO	VARCHAR(50),
	EXAM_SCORE VARCHAR(50),
	EXAM_PERCENTAGE VARCHAR(50),
	EXAM_RESULT VARCHAR(50),
	CONFIRMATION_CODE  VARCHAR(50),
	PROMO_CODE  VARCHAR(50),
	PAYMENT_METHOD VARCHAR(50),
	TAXES VARCHAR(50),
	EXAM_FEE VARCHAR(50),
	PAYMENT_AMOUNT VARCHAR(50),
	PO_BILLING_TYPE VARCHAR(50),
	ECREDIT_CODE VARCHAR(50),
	RESERVATION_GUID VARCHAR(250),
	AGREE_CHECK_DATE_TIME DATETIME,

    PRIMARY KEY (RESERVATION_GUID)
);

CREATE TABLE DYNAMIC_TAB (
  TAB_ID         VARCHAR(20),
  TAB_NAME VARCHAR(100),
  DSQL VARCHAR(4000),
  CREATE_DATE    DATE,
  UPDATE_DATE    DATE,

  PRIMARY KEY (TAB_ID)
);

CREATE TABLE USER_ROLE (
  EMAIL         VARCHAR(250),
  USER_ROLE VARCHAR(250),
  CREATE_DATE    DATE,
  UPDATE_DATE    DATE,

  PRIMARY KEY (EMAIL)
);

CREATE TABLE COUNTRY_REGION_MAP (
  ID         INT(20) NOT NULL AUTO_INCREMENT,
  COUNTRY VARCHAR(250),
  ABBREVIATION VARCHAR(50),
  REGION VARCHAR(100),
  CREATE_DATE    DATETIME,
  UPDATE_DATE    DATETIME,

  PRIMARY KEY (ID)
)

#VIEW
CREATE VIEW CERT_EXAM_VW AS
SELECT E.EXAM_CENTER,  E.CANDIDATE_EMAIL, E.FIRST_NAME, E.LAST_NAME, E.COMPANY_NAME,
M.REGION, M.COUNTRY, E.EXAM_CODE, E.EXAM_NAME,
CASE WHEN EXAM_START_DATE_TIME IS NULL THEN TRANSACTION_DATE ELSE EXAM_START_DATE_TIME end EXAM_DATE,
E.EXAM_PERCENTAGE, EXAM_RESULT
FROM CERT_EXAM E,   COUNTRY_REGION_MAP M
WHERE E.COUNTRY_CODE = M.ABBREVIATION;