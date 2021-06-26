CREATE DATABASE BookStore;

USE BookStore;

CREATE TABLE USER_AUTHORS (
	ID BIGINT PRIMARY KEY IDENTITY(1,1),
	USERNAME VARCHAR(50) NOT NULL,
	FIRST_NAME VARCHAR(100) NOT NULL,
	LAST_NAME VARCHAR(100) NOT NULL,
	PASSWORD CHAR(60) NOT NULL
);

-- Password: anJ?FS6w
INSERT INTO USER_AUTHORS VALUES('admin', 'Jose Luis', 'Perez Olvera', '$2a$10$CtseeuORmf4ZzOhk1Wr1G.WoaNVxXtVFryXwumJjNqKVIwVeBkRN6');
INSERT INTO USER_AUTHORS VALUES('author', 'George', 'Lucas', '$2a$10$CtseeuORmf4ZzOhk1Wr1G.WoaNVxXtVFryXwumJjNqKVIwVeBkRN6');
INSERT INTO USER_AUTHORS VALUES('darthvader', 'Darth', 'Vader', '$2a$10$CtseeuORmf4ZzOhk1Wr1G.WoaNVxXtVFryXwumJjNqKVIwVeBkRN6');

CREATE TABLE BOOKS (
	ID BIGINT PRIMARY KEY IDENTITY(1,1),
	TITLE VARCHAR(150) NOT NULL,
	DESCRIPTION VARCHAR(150) NOT NULL,
	AUTHOR BIGINT FOREIGN KEY REFERENCES USER_AUTHORS(ID) NOT NULL,
	COVER_IMAGE VARCHAR(MAX) NOT NULL,
	PRICE DECIMAL(10, 2) NOT NULL,
	PUBLISH_DATE DATETIME NOT NULL
);

INSERT INTO BOOKS VALUES('My First Book', 'This is the first published book by admin', 1, 'https://www.tonica.la/__export/1618261177521/sites/debate/img/2021/04/12/ewok-star-wars-mono-peligro-extincixn_1.jpg_185051854.jpg', 25, GETDATE());
INSERT INTO BOOKS VALUES('Star Wars', 'This is the first edition of the Star Wars history.', 2, 'https://i1.wp.com/hipertextual.com/wp-content/uploads/2020/05/hipertextual-star-wars-celebra-may-the-4th-be-with-you-con-extraordinario-video-que-rinde-homenaje-toda-saga-2020581511.jpg?fit=1920%2C1080&ssl=1', 50, GETDATE());

select * from BOOKS;