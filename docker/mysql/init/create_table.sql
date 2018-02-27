CREATE TABLE users (
  id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
  name varchar(100) NOT NULL,
  email varchar(100) NOT NULL,
  password varchar(100) NOT NULL
);

CREATE TABLE statuses (
  id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
  user_id int NOT NULL,
  plan_id int NOT NULL,
  status int NOT NULL
);

CREATE TABLE plans (
  id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
  name varchar(100) NOT NULL,
  price int NOT NULL
);

CREATE TABLE parties (
  id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
  user_id int NOT NULL,
  place_id int NOT NULL,
  template_id int NOT NULL,
  name varchar(100) NOT NULL,
  date Date NOT NULL,
  m_price int NOT NULL,
  f_price int NOT NULL,
  hash varchar(128) UNIQUE NOT NULL,
  message_1 varchar(800),
  message_2 varchar(800),
  message_3 varchar(800),
  message_4 varchar(800),
  message_5 varchar(800)
);

CREATE TABLE places (
  id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
  name varchar(100) NOT NULL,
  address varchar(100) NOT NULL,
  phone varchar(100) NOT NULL,
  url varchar(100) NOT NULL
);

CREATE TABLE templates (
  id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
  name varchar(100) NOT NULL,
  file_url varchar(100) NOT NULL
);

CREATE TABLE participants (
  id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
  party_id int NOT NULL,
  first_name varchar(100) NOT NULL,
  last_name varchar(100) NOT NULL,
  email varchar(100) NOT NULL,
  gender tinyint NOT NULL,
  side bit(1) NOT NULL,
  attendance bit(1) NOT NULL,
  message varchar(100),
  postal_code varchar(100),
  address varchar(100),
  phone varchar(100)
);


-- ====================
-- データ投入
-- ====================

INSERT INTO users (
  name,
  email,
  password
) VALUES (
  'kimura', 'k.haijima@gmail.com', 'password'
);

INSERT INTO plans (
  name,
  price
) VALUES (
  'free', 0
);

INSERT INTO parties (
  user_id,
  place_id,
  template_id,
  name,
  date,
  m_price,
  f_price,
  hash
) VALUES (
  1, 1, 1, '木村・福田家披露宴二次会', '2018-04-22', 8000, 6000, 'qwertyuiop'
);

INSERT INTO participants (
  party_id,
  first_name,
  last_name,
  email,
  gender,
  side,
  attendance,
  message,
  address,
  phone
) VALUES (
  1, '一樹', '朏島', 'k.haijima@gmail.com', 1, 1, 1, 'おめでとー', NULL, NULL
), (
  1, '優作', '徳永', 'yusaku.tokunaga@gmail.com', 1, 1, 1, NULL, NULL, NULL  
), (
  1, 'じゅん','', '', 0, 0, 1, NULL, NULL, NULL  
);

COMMIT;
