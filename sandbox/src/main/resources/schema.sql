DROP TABLE IF EXISTS todo;

CREATE TABLE todo (
id NUMBER(10,0) NOT NULL AUTO_INCREMENT,
expId INT NOT NULL,
title VARCHAR2(255) NOT NULL,
description VARCHAR2(255) DEFAULT NULL,
startDate TIMESTAMP DEFAULT NULL,
endDate TIMESTAMP DEFAULT NULL,
isCurrent BOOLEAN,
PRIMARY KEY (id));

DROP SEQUENCE IF EXISTS todo_id_seq;

CREATE SEQUENCE todo_id_seq
  MINVALUE 1
  MAXVALUE 9999999999999999
  START WITH 1
  INCREMENT BY 100
  CACHE 100;