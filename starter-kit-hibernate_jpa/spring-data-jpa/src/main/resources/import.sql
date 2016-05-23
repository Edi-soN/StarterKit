insert into library (id, name) values (1, 'Library1');
insert into library (id, name) values (2, 'Library2');
insert into library (id, name) values (3, 'Library3');

insert into book (id, title, library_id, version) values (1, 'Pierwsza książka', 1, 1);
insert into book (id, title, library_id, version) values (2, 'Druga książka', 2, 1);
insert into book (id, title, library_id, version) values (3, 'Trzecia książka', 3, 1);

insert into author (id, first_name, last_name) values (7, 'Jan', 'Kowalski');
insert into author (id, first_name, last_name) values (8, 'Zbigniew', 'Nowak');
insert into author (id, first_name, last_name) values (9, 'Janusz', 'Jankowski');

insert into book_author(book_id, author_id) values (1, 7);
insert into book_author(book_id, author_id) values (2, 8);
insert into book_author(book_id, author_id) values (3, 9);