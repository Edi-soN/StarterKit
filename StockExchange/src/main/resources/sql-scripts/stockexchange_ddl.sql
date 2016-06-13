-- DataBase: StockMarket



CREATE DATABASE stockexchange;

USE stockexchange;


-- Table: StockShare

DROP TABLE IF EXISTS stockshareentity;

CREATE TABLE stockshareentity (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  sharename varchar(50) NOT NULL,
  sharedate date NOT NULL,
  shareprice numeric(15,2) NOT NULL,   
  PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;


-- Table: currency wallet

DROP TABLE IF EXISTS currencywalletentity;

CREATE TABLE currencywalletentity (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  currencyname enum('PLN', 'EUR') NOT NULL,
  currencyamount numeric(15,2) NOT NULL,  
  PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;


-- Table: share wallet

DROP TABLE IF EXISTS sharewalletentity;

CREATE TABLE sharewalletentity(
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  sharename varchar(50) NOT NULL,
  sharequantity int(10) NOT NULL,
  PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;


-- Table: offer

DROP TABLE IF EXISTS offerentity;

CREATE TABLE offerentity(
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  sharename varchar(50) NOT NULL,
  sharequantity int(10) NOT NULL,
  shareprice numeric(15,2) NOT NULL,  
  offertype enum('BUY', 'SELL') NOT NULL,
  PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;


INSERT INTO currencywalletentity (currencyname, currencyamount) VALUES ('PLN', 10000.00), ('EUR', 1250.00);
