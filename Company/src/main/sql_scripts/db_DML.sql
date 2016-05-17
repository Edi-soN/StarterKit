USE company;

INSERT INTO department (name) VALUES ('Department1'), ('Department2'), ('Department3'), ('Department4'), ('Department5'), ('Department6'), ('Department7'), ('Department8'), ('Department9');

INSERT INTO employee (name, surname, pesel, date_of_birth, id_department) VALUES ('Jan', 'Kowalski', '00000000001', date('1987-07-03'), 1), 
	('Jan', 'Nowak', '00000000002', date('1988-07-03'), 2), ('Mateusz', 'Kowalski', '00000000003', date('1989-07-03'), 3), 
	('Adam', 'Kowalski', '00000000004', date('1990-07-03'), 4), ('Adam', 'Nowak', '00000000005', date('1986-07-03'), 5),
	('Marcin', 'Kowalski', '00000000006', date('1985-07-03'), 6), ('Marcin', 'Nowak', '00000000007', date('1984-07-03'), 7), 
	('Marek', 'Kowalski', '00000000008', date('1983-07-03'), 8),('Jan', 'Dygala', '00000000010', date('1981-07-03'), 1), 
	('Mateusz', 'Nowak', '00000000011', date('1980-07-03'), 2), ('Jan', 'Gawron', '00000000012', date('1979-07-03'), 3), 
	('Jan', 'Kowalski', '00000000013', date('1978-07-03'), 4), ('Jan', 'Sroka', '00000000014', date('1977-07-03'), 5), 
	('Artur', 'Abwalski', '00000000015', date('1976-07-03'), 6),('Jan', 'Gmoch', '00000000016', date('1975-07-03'), 7), 
	('Jan', 'abwalski', '00000000017', date('1974-07-03'), 8),('Jan', 'KFwalski', '00000000019', date('1988-07-03'), 1), 
	('Jan', 'Janowski', '00000000020', date('1988-07-03'), 2), ('Andrzej', 'KFwalski', '00000000021', date('1990-07-03'), 3), 
	('Jan', 'Para', '00000000022', date('1986-07-03'), 4), ('Mateusz', 'Janewicz', '00000000023', date('1985-07-03'), 5), 
	('Jan', 'Kawka', '00000000024', date('1984-07-03'), 6), ('Mateusz', 'Jakubowski', '00000000025', date('1983-07-03'), 7), 
	('Jan', 'Woda', '00000000026', date('1982-07-03'), 8), ('Jan', 'Janeczko', '00000000028', date('1980-07-03'), 1), 
	('Adam', 'Sandler', '00000000029', date('1987-07-03'), 1), ('Jan', 'Rej', '00000000030', date('1987-07-03'), 1), 
	('Piotr', 'Kowalski', '00000000031', date('1991-07-03'), 1);

INSERT INTO employee (name, surname, pesel, date_of_birth) VALUES ('Marek', 'Nowak', '00000000009', date('1982-07-03')), 
('Jan', 'Ferenc', '00000000018', date('1987-07-03')), ('Adam', 'Biegly', '00000000027', date('1981-07-03'));
	
	
INSERT INTO project (name, project_type, id_manager) VALUES ('Project1', 'inner', 1), ('Project2', 'outer', 2), ('Project3', 'inner', 3), 
	('Project4', 'outer', 4), ('Project5', 'inner', 5), ('Project6', 'outer', 6), ('Project7', 'inner', 7), ('Project8', 'outer', 8);

INSERT INTO role (name) VALUES ('DEV'), ('PL'), ('TCD'), ('FCD');

INSERT INTO employee_engagement (id_employee, id_project, id_role, start_date, end_date, salary) VALUES 
	(1,1,1,date('2015-01-01'),date('2016-01-01'),5000.00), (2,1,1,date('2016-01-01'),date('2016-06-06'),4000.00), 
	(3,1,1,date('2015-01-01'),date('2016-01-01'),3000.00), (4,2,1,date('2016-01-01'),date('2016-06-06'),4500.00), 
	(5,3,1,date('2016-01-01'),date('2016-03-06'),3500.00), (6,4,1,date('2016-01-01'),date('2016-03-06'),5000.00),
	(7,5,1,date('2016-01-01'),date('2016-06-06'),4000.00), (8,6,1,date('2016-01-01'),date('2016-06-06'),3000.00), 
	(9,7,1,date('2016-01-01'),date('2016-06-06'),2500.00), (10,8,1,date('2016-01-01'),date('2016-06-06'),2000.00), 
	(11,1,1,date('2016-01-01'),date('2016-06-06'),3400.00), (12,1,1,date('2016-01-01'),date('2016-06-06'),4200.00), 
	(13,1,1,date('2016-01-01'),date('2016-06-06'),5600.00), (1,1,3,date('2016-01-01'),date('2016-06-06'),5000.00), 
	(2,6,1,date('2016-01-01'),date('2016-06-06'),3900.00), (3,5,1,date('2015-02-01'),date('2016-06-06'),4800.00);
	
INSERT INTO employee_engagement (id_employee, id_project, id_role, start_date, salary) VALUES (1,4,2,date('2016-01-01'),2600.00), 
										  (15,1,1,date('2016-01-01'),2200.00),
										  (16,1,1,date('2016-01-01'),5000.00), 
										  (17,1,1,date('2016-01-01'),3500.00);
	