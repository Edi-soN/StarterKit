USE forum;

-- Zadanie 1: Policz wszystkich aktywnych uzytkownikow forum torepublic
SELECT COUNT(*)
FROM users
Where num_posts > 0
;

-- Zadanie 2: Znajdz najbardziej aktywnego uzytkownika forum torrepublic w roku 2015, 2014 i wczesniej. Sam zdefiniuj kryterium "aktywnosci".
SELECT username, num_posts
FROM users
ORDER BY num_posts DESC
LIMIT 1
;

-- Zadanie 3: Znajdz pieciu uzytkownikow, ktorych suma dlugosci wszystkich komentarzy jest najwieksza.
SELECT u.id, u.username, SUM(CHAR_LENGTH(a.answer)) AS Total_comments_length
FROM users u JOIN voting v ON u.id=v.user_id JOIN answers a ON a.id=v.answer_id
GROUP BY u.username
ORDER BY Total_comments_length DESC
LIMIT 5
;
-- check 
SELECT answer
FROM answers a JOIN VOTING v ON a.id=v.answer_id
WHERE v.user_id = 416
;

-- Zadanie 4: Znajdz uzytkownika, ktory nigdy nie napisal zadnego komentarza.
SELECT u.username, u.id, v.user_id 
FROM users u LEFT JOIN voting v ON u.id=v.user_id
WHERE v.user_id IS NULL
;

-- Zadanie 5: Znajdz uzytkownikow, ktorzy oferowali rzeczy lub uslugi niezgodne z prawem lub karalne. :)
SELECT u.username, u.id, e.sellerid
FROM escrows e JOIN users u ON u.id=e.sellerid
WHERE UPPER(subject) LIKE '%HASZ%' OR UPPER(subject) LIKE '%DOWOD%'
;

-- Zadanie 6: Pogrupuj uzytkownikow po skrzynkach pocztowych, z ktorych korzystaja
SELECT COUNT(u.username) AS Number_of_users, SUBSTRING_INDEX(u.email, '@', -1) AS domain
FROM users u
GROUP BY domain 
ORDER BY Number_of_users DESC
;