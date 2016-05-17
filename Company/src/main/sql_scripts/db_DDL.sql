-- DataBase: Company

CREATE DATABASE company;

USE company;

DROP TABLE IF EXISTS department;

-- Table: Department

CREATE TABLE department (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL UNIQUE,
  PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS employee;

-- Table: Employee

CREATE TABLE employee (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  name varchar(20) NOT NULL,
  surname varchar(30) NOT NULL,
  pesel char(11) UNIQUE NOT NULL,
  date_of_birth date NOT NULL,
  id_department int(10) unsigned NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_employee_department 
        FOREIGN KEY (id_department) 
        REFERENCES   department(id)
) DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS project;

-- Table: Project

CREATE TABLE project(
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  project_type enum('inner', 'outer') NOT NULL,
  id_manager int(10) unsigned NOT NULL, 
  PRIMARY KEY (id),
  CONSTRAINT fk_project_employee 
        FOREIGN KEY (id_manager) 
        REFERENCES   employee(id)
) DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS role;

-- Table: Role

CREATE TABLE role(
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  name varchar(10) NOT NULL UNIQUE,
  PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS employee_engagement;

-- Table: Employee engagement

CREATE TABLE employee_engagement(
   id int(10) unsigned NOT NULL AUTO_INCREMENT,
   id_employee int(10) unsigned NOT NULL,
   id_project int(10) unsigned NOT NULL,  
   id_role int(10) unsigned NOT NULL,
   start_date date NOT NULL, 
   end_date date NULL,
   salary numeric(15,2) DEFAULT NULL,    
   PRIMARY KEY (id),
   CONSTRAINT fk_employee_engagement_employee 
        FOREIGN KEY (id_employee) 
        REFERENCES   employee(id),
   CONSTRAINT fk_employee_engagement_project 
        FOREIGN KEY (id_project) 
        REFERENCES   project(id),
   CONSTRAINT fk_employee_engagement_role
        FOREIGN KEY (id_role) 
        REFERENCES   role(id)
) DEFAULT CHARSET=utf8;






