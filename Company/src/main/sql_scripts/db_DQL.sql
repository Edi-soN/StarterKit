USE company;

-- a znajdz pracownikow starszych niz X lat
SELECT * , YEAR(CURDATE()) - YEAR(date_of_birth) as age
FROM employee
WHERE YEAR(CURDATE()) - YEAR(date_of_birth) > 30 
ORDER BY AGE 
;

-- a.1 znajdz pracownikow w których nazwisku wystepuje fraza 'ski'
SELECT * 
FROM employee
WHERE surname LIKE '%ski%'
; 

-- a.2 znajdz pracownikow których nazwisko nie zaczyna sie od 'ab' (wielkosc liter bez  znaczenia)
SELECT *
FROM employee
WHERE surname NOT LIKE 'ab%' -- case insensitive, unless using a binary comparison
;

-- a.3 znajdz pracownikow których nazwisko jest dluzsze niz N znaków
SELECT *
FROM employee
WHERE CHAR_LENGTH(surname) > 9 -- zamiast length inna funckja ktora radzi sobie z unicode
;

-- a.4 znajdz pracownikow w których nazwisku na drugim miejscu wystepuje duza litera F
SELECT *
FROM employee
WHERE BINARY surname LIKE '_F%'
;

-- b znajdz pracownikow w dziale X
SELECT e.name, e.surname, e.pesel, d.name
FROM employee e JOIN department d ON e.id_department=d.id
WHERE d.name LIKE 'Department1' 
;

-- c znajdz projekty w których zatrudniony byl pracownik z peselem X
SELECT e.name, e.surname, e.pesel, p.name 
FROM employee_engagement ee JOIN employee e ON ee.id_employee = e.id JOIN project p ON ee.id_project = p.id
WHERE e.pesel LIKE '00000000001'
;

-- d policz w ilu projektach aktualnie zatrudniony jest pracownik X
SELECT e.name, e.surname, e.pesel, COUNT(DISTINCT ee.id_project) AS Number_of_projects
FROM employee e JOIN employee_engagement ee ON e.id = ee.id_employee
WHERE e.pesel LIKE '00000000001' AND (ee.end_date IS NULL OR ee.end_date > DATE('2016-06-01'))
;

-- e policz w ilu projektach w zeszlym roku zatrudniony byl pracownik X na stanowisku PL
SELECT e.name, e.surname, e.pesel, r.name, COUNT(DISTINCT ee.id_project) AS Number_of_projects
FROM (employee e JOIN employee_engagement ee ON e.id = ee.id_employee) JOIN role r ON ee.id_role = r.id 
WHERE e.pesel LIKE '00000000001' AND r.name LIKE 'DEV' AND YEAR(ee.start_date) < YEAR(CURDATE())
AND (YEAR(ee.end_date) >= YEAR(CURDATE() - INTERVAL 1 YEAR) OR ee.end_date IS NULL)
;

-- f wyszukaj zewnetrzne projekty
SELECT DISTINCT p.name, p.project_type
FROM project p
WHERE p.project_type LIKE 'outer'
;

-- g znajdz pracowników z dzialu X którzy pelnili w jakims projekcie przynajmniej 2 funkcje (w tzym samym projekcie)
SELECT e.name, e.surname, e.pesel, d.name, COUNT(DISTINCT ee.id_role) AS Number_of_roles
FROM employee_engagement ee JOIN employee e ON ee.id_employee = e.id JOIN department d ON e.id_department = d.id
WHERE d.name LIKE 'Department1' 
GROUP BY ee.id_employee, ee.id_project
HAVING COUNT(DISTINCT ee.id_role) > 1
;

-- h znajdz pracowników którzy pelnili w jakims projekcie funkcje TCD i DEV
SELECT e.name, e.surname, e.pesel, p.name, GROUP_CONCAT(r.name SEPARATOR ' ') as roles
FROM ((employee e JOIN employee_engagement ee ON e.id = ee.id_employee) JOIN project p ON ee.id_project=p.id) JOIN role r ON ee.id_role=r.id
GROUP BY ee.id_employee, ee.id_project
HAVING roles IN ('TCD DEV', 'DEV TCD')
;

-- i wyszukaj projekt, funkcje pracownika w którym pracownik mial w nim najwyzsza dniówke (ze  wszystkich projektów w systemie)
SELECT p.name, r.name, MAX(ee.salary) AS Highest_salary, e.name, e.surname, e.pesel
FROM employee e JOIN employee_engagement ee ON e.id=ee.id_employee JOIN project p ON ee.id_project=p.id JOIN role r ON ee.id_role=r.id
GROUP BY p.name, r.name, e.name, e.surname, e.pesel
ORDER BY  Highest_salary DESC
Limit 1
;

-- i wyszukaj najlepiej zarabiajacego aktualnie pracownika (chodzi o sumaryczna wartosc dniówek danego pracownika ze wszystkich projektów, do których jest aktualnie przypisany)
SELECT e.name, e.surname, e.pesel, SUM(ee.salary) AS Highest_total_salary
FROM employee e JOIN employee_engagement ee ON e.id=ee.id_employee
WHERE ee.end_date IS NULL OR ee.end_date > DATE('2017-06-01')
GROUP BY e.id
ORDER BY Highest_total_salary DESC
LIMIT 1
; -- having musi byc zredukowane do bool'a

-- j znajdz pracownikow, ktorzy pracowali w projekcie X pomiedzy data Y i Z
SELECT e.name, e.surname, e.pesel, p.name, ee.start_date, ee.end_date
FROM employee e JOIN employee_engagement ee ON e.id=ee.id_employee JOIN project p ON p.id=ee.id_project
WHERE p.name LIKE 'Project1' AND ee.start_date < DATE('2016-01-01') AND (ee.end_date >= DATE('2015-01-01') OR ee.end_date IS NULL)
;

-- k znajdz pracownikow nie przypisanych do zadnego z dzialów
SELECT name, surname, pesel
FROM employee
WHERE id_department IS NULL
;

-- l znajdz pracownikow zarabaiajacych aktualnie w jednym z projektow (dziennie) wiecej niz X
SELECT e.name, e.surname, e.pesel, p.name, ee.salary
FROM employee e JOIN employee_engagement ee ON e.id=ee.id_employee JOIN project p ON p.id=ee.id_project
WHERE ee.salary > 4000.00 AND ee.start_date < DATE('2016-06-01') AND (ee.end_date > DATE('2016-06-01') OR ee.end_date IS NULL)
;

-- m znajdz pracownikow zarabaiajacych aktualnie (dziennie) wiecej niz X
SELECT e.name, e.surname, e.pesel, SUM(ee.salary) AS Total_daily_salary
FROM employee e JOIN employee_engagement ee ON e.id=ee.id_employee
WHERE ee.start_date < DATE('2016-06-01') AND (ee.end_date > DATE('2016-06-01') OR ee.end_date IS NULL)
GROUP BY e.id
HAVING Total_daily_salary > 5000.00
;


