#deprecated
insert into EXAM_CODE_MAP ( EXAM_CODE,PIVOTAL_CODE) values ('CoreSpringV3.0','CoreSpring Developer');
insert into EXAM_CODE_MAP ( EXAM_CODE,PIVOTAL_CODE) values ('CoreSpringV3.2','CoreSpring Developer');
insert into EXAM_CODE_MAP ( EXAM_CODE,PIVOTAL_CODE) values ('CoreSpringV4.0','CoreSpring Developer');
insert into EXAM_CODE_MAP ( EXAM_CODE,PIVOTAL_CODE) values ('CoreSpringV4.1','CoreSpring Developer');
insert into EXAM_CODE_MAP ( EXAM_CODE,PIVOTAL_CODE) values ('CoreSpringV4.2','CoreSpring Developer');
insert into EXAM_CODE_MAP ( EXAM_CODE,PIVOTAL_CODE) values ('DAWCFV1.0.b','PCF Developer');
insert into EXAM_CODE_MAP ( EXAM_CODE,PIVOTAL_CODE) values ('EnterpriseSpring4.0','Enterprise Spring');
insert into EXAM_CODE_MAP ( EXAM_CODE,PIVOTAL_CODE) values ('GemFire_Admin_V7.0.2','GemFire Administration');
insert into EXAM_CODE_MAP ( EXAM_CODE,PIVOTAL_CODE) values ('GemFire_Admin_V8.1.0','GemFire Administration');
insert into EXAM_CODE_MAP ( EXAM_CODE,PIVOTAL_CODE) values ('GemFire_Developer_V8','GemFire Developer');
insert into EXAM_CODE_MAP ( EXAM_CODE,PIVOTAL_CODE) values ('HAWQ_V1.2.1b','Pivotal HDB Architecture');
insert into EXAM_CODE_MAP ( EXAM_CODE,PIVOTAL_CODE) values ('PCF Immersion','PCF Immersion');
insert into EXAM_CODE_MAP ( EXAM_CODE,PIVOTAL_CODE) values ('Redis_ICM_V1.0.0','Redis ICM');
insert into EXAM_CODE_MAP ( EXAM_CODE,PIVOTAL_CODE) values ('RichWeb3.0','Spring Web ');
insert into EXAM_CODE_MAP ( EXAM_CODE,PIVOTAL_CODE) values ('SFC2.5','CoreSpring Developer');
insert into EXAM_CODE_MAP ( EXAM_CODE,PIVOTAL_CODE) values ('SpringEIwSCertV1.0','Enterprise Integration with Spring');
insert into EXAM_CODE_MAP ( EXAM_CODE,PIVOTAL_CODE) values ('SpringWeb','Spring Web ');
insert into EXAM_CODE_MAP ( EXAM_CODE,PIVOTAL_CODE) values ('SpringWebDevCertV1','Spring Web ');
insert into EXAM_CODE_MAP ( EXAM_CODE,PIVOTAL_CODE) values ('SpringWebV4.0','Spring Web ');
insert into EXAM_CODE_MAP ( EXAM_CODE,PIVOTAL_CODE) values ('SpringWebV4.2','Spring Web ');

update EXAM_CODE_MAP set  create_date=now()

update EXAM_CODE_MAP set  data_source='Pearson VUE'
#end of deprecated

#the following is for role control only
INSERT INTO USER_ROLE (EMAIL, USER_ROLE) VALUES ('rfang@pivotal.io','ADMIN');
INSERT INTO USER_ROLE (EMAIL, USER_ROLE) VALUES ('raymond.fanglimin@icloud.com','USER');
INSERT INTO USER_ROLE (EMAIL, USER_ROLE) VALUES ('sechan@pivotal.io','ADMIN');
INSERT INTO USER_ROLE (EMAIL, USER_ROLE) VALUES ('cecurtis@pivotal.io','ADMIN');
INSERT INTO USER_ROLE (EMAIL, USER_ROLE) VALUES ('jstenflo@pivotal.io','ADMIN');
INSERT INTO USER_ROLE (EMAIL, USER_ROLE) VALUES ('lachua@pivotal.io','USER');
INSERT INTO USER_ROLE (EMAIL, USER_ROLE) VALUES ('pmcfadden@pivotal.io','USER');
INSERT INTO USER_ROLE (EMAIL, USER_ROLE) VALUES ('tgern@pivotal.io','USER');
INSERT INTO USER_ROLE (EMAIL, USER_ROLE) VALUES ('mbarinek@pivotal.io','USER');
INSERT INTO USER_ROLE (EMAIL, USER_ROLE) VALUES ('jfunk@pivotal.io','USER');
INSERT INTO USER_ROLE (EMAIL, USER_ROLE) VALUES ('msecrist@pivotal.io','USER');
INSERT INTO USER_ROLE (EMAIL, USER_ROLE) VALUES ('dham@pivotal.io','USER');


#for PSI
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('AF','AFGHANISTAN','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('AL','ALBANIA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('DZ','ALGERIA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('AS','AMERICAN SAMOA','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('AD','ANDORRA','EMEA');

INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('AO','ANGOLA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('AI','ANGUILLA','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('AQ','ANTARCTICA','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('AG','ANTIGUA AND BARBUDA','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('AR','ARGENTINA','AMERICA');

INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('AM','ARMENIA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('AW','ARUBA','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('AU','AUSTRALIA','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('AT','AUSTRIA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('AZ','AZERBAIJAN','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('BS','BAHAMAS','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('BH','BAHRAIN','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('BD','BANGLADESH','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('BB','BARBADOS','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('BY','BELARUS','EMEA');

INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('BE','BELGIUM','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('BZ','BELIZE','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('BJ','BENIN','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('BM','BERMUDA','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('BT','BHUTAN','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('BO','BOLIVIA','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('BA','BOSNIA AND HERZEGOVINA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('BW','BOTSWANA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('BV','BOUVET ISLAND','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('BR','BRAZIL','AMERICA');

INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('IO','BRITISH INDIAN OCEAN TERRITORY','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('BN','BRUNEI DARUSSALAM','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('BG','BULGARIA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('BF','BURKINA FASO','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('BI','BURUNDI','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('KH','CAMBODIA','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('CM','CAMEROON','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('CA','CANADA','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('CV','CAPE VERDE','EMEA');

INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('KY','CAYMAN ISLANDS','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('CF','CENTRAL EMEAN REPUBLIC','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('TD','CHAD','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('CL','CHILE','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('CN','CHINA','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('CX','CHRISTMAS ISLAND','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('CC','COCOS (KEELING) ISLANDS','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('CO','COLOMBIA','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('KM','COMOROS','EMEA');

INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('CG','CONGO','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('CD','CONGO, THE DEMOCRATIC REPUBLIC OF','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('CK','COOK ISLANDS','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('CR','COSTA RICA','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('CI','CÔTE D\'IVOIRE','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('HR','CROATIA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('CU','CUBA','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('CY','CYPRUS','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('CZ','CZECH REPUBLIC','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('DK','DENMARK','EMEA');

INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('DJ','DJIBOUTI','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('DM','DOMINICA','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('DO','DOMINICAN REPUBLIC','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('EC','ECUADOR','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('EG','EGYPT','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('EH','WESTERN SAHARA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('SV','EL SALVADOR','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('GQ','EQUATORIAL GUINEA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('ER','ERITREA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('EE','ESTONIA','EMEA');

INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('ET','ETHIOPIA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('FK','FALKLAND ISLANDS (MALVINAS)','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('FO','FAROE ISLANDS','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('FJ','FIJI','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('FI','FINLAND','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('FR','FRANCE','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('GF','FRENCH GUIANA','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('PF','FRENCH POLYNESIA','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('TF','FRENCH SOUTHERN TERRITORIES','APAC');

INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('GA','GABON','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('GM','GAMBIA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('GE','GEORGIA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('DE','GERMANY','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('GH','GHANA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('GI','GIBRALTAR','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('GR','GREECE','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('GL','GREENLAND','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('GD','GRENADA','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('GP','GUADELOUPE','AMERICA');

INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('GU','GUAM','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('GT','GUATEMALA','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('GN','GUINEA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('GW','GUINEA-BISSAU','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('GY','GUYANA','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('HT','HAITI','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('HM','HEARD ISLAND AND MCDONALD ISLANDS','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('HN','HONDURAS','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('HK','HONG KONG','APAC');

INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('HU','HUNGARY','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('IS','ICELAND','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('IN','INDIA','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('ID','INDONESIA','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('IR','IRAN, ISLAMIC REPUBLIC OF','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('IQ','IRAQ','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('IE','IRELAND','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('IL','ISRAEL','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('IT','ITALY','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('JM','JAMAICA','AMERICA');

INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('JP','JAPAN','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('JO','JORDAN','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('KZ','KAZAKHSTAN','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('KE','KENYA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('KI','KIRIBATI','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('KP','KOREA, DEMOCRATIC PEOPLE\'S REPUBLIC OF','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('KR','KOREA, REPUBLIC OF','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('KW','KUWAIT','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('KG','KYRGYZSTAN','APAC');

INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('LA','LAO PEOPLE\'S DEMOCRATIC REPUBLIC','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('LV','LATVIA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('LB','LEBANON','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('LS','LESOTHO','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('LR','LIBERIA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('LY','LIBYAN ARAB JAMAHIRIYA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('LI','LIECHTENSTEIN','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('LT','LITHUANIA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('LU','LUXEMBOURG','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('MO','MACAO','EMEA');

INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('MK','MACEDONIA, THE FORMER YUGOSLAV REPUBLIC OF','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('MG','MADAGASCAR','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('MW','MALAWI','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('MY','MALAYSIA','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('MV','MALDIVES','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('ML','MALI','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('MT','MALTA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('MH','MARSHALL ISLANDS','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('MQ','MARTINIQUE','AMERICA');

INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('MR','MAURITANIA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('MU','MAURITIUS','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('YT','MAYOTTE','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('MX','MEXICO','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('FM','MICRONESIA, FEDERATED STATES OF','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('MD','MOLDOVA, REPUBLIC OF','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('MC','MONACO','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('MN','MONGOLIA','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('MS','MONTSERRAT','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('MA','MOROCCO','EMEA');

INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('MZ','MOZAMBIQUE','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('MM','MYANMAR','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('NA','NAMIBIA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('NR','NAURU','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('NP','NEPAL','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('NL','NETHERLANDS','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('AN','NETHERLANDS ANTILLES','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('NC','NEW CALEDONIA','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('NZ','NEW ZEALAND','APAC');

INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('NI','NICARAGUA','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('NE','NIGER','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('NG','NIGERIA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('NU','NIUE','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('NF','NORFOLK ISLAND','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('MP','NORTHERN MARIANA ISLANDS','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('NO','NORWAY','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('OM','OMAN','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('PK','PAKISTAN','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('PW','PALAU','APAC');

INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('PS','PALESTINIAN TERRITORY, OCCUPIED','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('PA','PANAMA','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('PG','PAPUA NEW GUINEA','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('PY','PARAGUAY','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('PE','PERU','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('PH','PHILIPPINES','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('PN','PITCAIRN','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('PL','POLAND','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('PT','PORTUGAL','EMEA');

INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('PR','PUERTO RICO','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('QA','QATAR','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('RE','RÉUNION','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('RO','ROMANIA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('RU','RUSSIAN FEDERATION','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('RW','RWANDA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('SH','SAINT HELENA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('KN','SAINT KITTS AND NEVIS','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('LC','SAINT LUCIA','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('PM','SAINT PIERRE AND MIQUELON','AMERICA');

INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('VC','SAINT VINCENT AND THE GRENADINES','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('WS','SAMOA','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('SM','SAN MARINO','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('ST','SAO TOME AND PRINCIPE','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('SA','SAUDI ARABIA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('SN','SENEGAL','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('CS','SERBIA AND MONTENEGRO','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('SC','SEYCHELLES','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('SL','SIERRA LEONE','EMEA');

INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('SG','SINGAPORE','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('SK','SLOVAKIA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('SI','SLOVENIA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('SB','SOLOMON ISLANDS','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('SO','SOMALIA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('ZA','SOUTH EMEA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('GS','SOUTH GEORGIA AND SOUTH SANDWICH ISLANDS','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('ES','SPAIN','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('LK','SRI LANKA','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('SD','SUDAN','EMEA');

INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('SR','SURINAME','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('SJ','SVALBARD AND JAN MAYEN','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('SZ','SWAZILAND','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('SE','SWEDEN','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('CH','SWITZERLAND','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('SY','SYRIAN ARAB REPUBLIC','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('TW','TAIWAN, CHINA','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('TJ','TAJIKISTAN','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('TZ','TANZANIA, UNITED REPUBLIC OF','EMEA');

INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('TH','THAILAND','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('TL','TIMOR-LESTE','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('TG','TOGO','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('TK','TOKELAU','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('TO','TONGA','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('TT','TRINIDAD AND TOBAGO','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('TN','TUNISIA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('TR','TURKEY','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('TM','TURKMENISTAN','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('TC','TURKS AND CAICOS ISLANDS','AMERICA');

INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('TV','TUVALU','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('UG','UGANDA','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('UA','UKRAINE','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('AE','UNITED ARAB EMIRATES','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('GB','UNITED KINGDOM','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('US','UNITED STATES','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('UM','UNITED STATES MINOR OUTLYING ISLANDS','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('UY','URUGUAY','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('UZ','UZBEKISTAN','APAC');

INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('VE','VENEZUELA','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('VU','VANUATU','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('VN','VIET NAM','AMERICA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('VG','BRITISH VIRGIN ISLANDS','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('VI','U.S. VIRGIN ISLANDS','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('WF','WALLIS AND FUTUNA','APAC');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('YE','YEMEN','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('ZW','ZIMBABWE','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('RS','Serbia','EMEA');
INSERT INTO COUNTRY_REGION_MAP  (ABBREVIATION,COUNTRY,REGION) VALUES ('JE','Jersey','EMEA');

