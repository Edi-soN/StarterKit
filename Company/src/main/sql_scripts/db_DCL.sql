-- Zaloz widok, w ktorym bedzie widoczne aktualne przypisanie pracownika do projektu/ow
-- IMIE  NAZWISKO  PROJEKT

USE company;

CREATE VIEW emp2proj AS
SELECT e.name AS Employee_name, e.surname AS Employee_surname, p.name AS Project_name
FROM employee e JOIN employee_engagement ee ON e.id = ee.id_employee JOIN project p ON p.id=ee.id_project
WHERE ee.start_date < DATE('2016-06-01') AND (ee.end_date > DATE('2016-06-01') OR ee.end_date IS NULL)
;


-- test
SELECT *
FROM emp2proj
;


-- -------------------------------------------------------------------------------------------------------------------------------------------------
-- Naucz sie tworzyc nowych uzytkownikow w bazie danych oraz przyznawac (GRANT) oraz odbierac (REVOKE) uprawnienia.
-- Zaloz kilku uzytkownikow. Ogranicz ich uprawnienia w nastepujacy sposob:
-- a. uzytownik user_ro moze tylko odczytywac dane z wszystkich tabel
-- b. uzytownik user_rw_projekt ma pelny dostep do tabeli z projektem (tzn. moze wstawiac i usuwac  dane), do innych tabel w projekcie dostepu nie ma

CREATE USER 'user_ro'@'localhost' IDENTIFIED BY 'ro';
CREATE USER 'user_rw_projekt'@'localhost' IDENTIFIED BY 'rw';

SELECT * FROM mysql.user;

DROP USER 'user_ro'@'localhost';
DROP USER 'user_rw_projekt'@'localhost';

GRANT SELECT ON company.* TO 'user_ro'@'localhost';
GRANT ALL ON company.employe_engagement TO 'user_rw_projekt'@'localhost';

REVOKE SELECT ON company.* FROM 'user_ro'@'localhost';
REVOKE ALL ON company.employe_engagement FROM 'user_rw_projekt'@'localhost';