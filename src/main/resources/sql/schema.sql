CREATE TABLE IF NOT EXISTS person (
                                      idperson INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
                                      lastname VARCHAR(45) NOT NULL,
    firstname VARCHAR(45) NOT NULL,
    nickname VARCHAR(45) NOT NULL,
    phone_number VARCHAR(15) NULL,
    address VARCHAR(200) NULL,
    email_address VARCHAR(150) NULL,
    birth_date DATE NULL,
    photo_path VARCHAR(255) NULL);

DELETE FROM person;
DELETE FROM sqlite_sequence WHERE name='person';

INSERT INTO person (
    lastname,
    firstname,
    nickname,
    phone_number,
    address,
    email_address,
    birth_date,
    photo_path
) VALUES
      ('Smith', 'John', 'JS', '1111111111', 'Street 1', 'john1@mail.com', '2000-01-01', 'images/john.jpg'),

      ('Brown', 'Alice', 'AB', '2222222222', 'Street 2', 'alice@mail.com', '1999-02-02', 'images/alice.jpg'),

      ('Taylor', 'Robert', 'RT', '3333333333', 'Street 3', 'robert@mail.com', '1998-03-03', 'images/robert.jpg'),

      ('Johnson', 'Emily', 'EJ', '4444444444', 'Street 4', 'emily@mail.com', '1997-04-04', 'images/emily.jpg'),

      ('Williams', 'Michael', 'MW', '5555555555', 'Street 5', 'michael@mail.com', '1996-05-05', 'images/michael.jpg'),

      ('Jones', 'Sarah', 'SJ', '6666666666', 'Street 6', 'sarah@mail.com', '1995-06-06', 'images/sarah.jpg'),

      ('Garcia', 'David', 'DG', '7777777777', 'Street 7', 'david@mail.com', '1994-07-07', 'images/david.jpg'),

      ('Martinez', 'Laura', 'LM', '8888888888', 'Street 8', 'laura@mail.com', '1993-08-08', 'images/laura.jpg'),

      ('Davis', 'James', 'JD', '9999999999', 'Street 9', 'james@mail.com', '1992-09-09', 'images/james.jpg'),

      ('Lopez', 'Anna', 'AL', '1010101010', 'Street 10', 'anna@mail.com', '1991-10-10', 'images/anna.jpg');

