CREATE TABLE users (
  id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
  name varchar(100) NOT NULL UNIQUE KEY,
  email varchar(100) NOT NULL,
  password varchar(100) NOT NULL
);

-- CREATE TABLE statuses (
--   id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
--   user_id int NOT NULL,
--   plan_id int NOT NULL,
--   status int NOT NULL
-- );
-- 
-- CREATE TABLE plans (
--   id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
--   name varchar(100) NOT NULL,
--   price int NOT NULL
-- );
-- 
CREATE TABLE parties (
  id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
  user_id int NOT NULL,
  place_id int NOT NULL,
  name varchar(100) NOT NULL,
  date Date NOT NULL,
  m_price int NOT NULL,
  f_price int NOT NULL,
  -- 本当はcustomizesの方にあるべき？
  hash varchar(128) NOT NULL UNIQUE KEY
);

ALTER TABLE parties ADD INDEX user_id(user_id);
ALTER TABLE parties ADD INDEX place_id(place_id);

CREATE TABLE places (
  id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
  name varchar(100) NOT NULL,
  address varchar(100) NOT NULL,
  phone varchar(100) NOT NULL,
  url varchar(100) NOT NULL
);

-- CREATE TABLE customizes (
--   id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
--   party_id int NOT NULL,
--   template_id int NOT NULL,
--   photo_url_1 varchar(800),
--   photo_url_2 varchar(800),
--   photo_url_3 varchar(800),
--   photo_url_4 varchar(800),
--   photo_url_5 varchar(800),
--   message_1 varchar(800),
--   message_2 varchar(800),
--   message_3 varchar(800),
--   message_4 varchar(800),
--   message_5 varchar(800)
-- );
-- 
-- CREATE TABLE templates (
--   id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
--   name varchar(100) NOT NULL,
--   file_url varchar(100) NOT NULL
-- );

CREATE TABLE participants (
  id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
  party_id int NOT NULL,
  first_name varchar(100) NOT NULL,
  last_name varchar(100) NOT NULL,
  email varchar(320) NOT NULL,
  gender tinyint NOT NULL,
  side bit(1) NOT NULL,
  attendance bit(1) NOT NULL,
  message varchar(800),
  postal_code varchar(100),
  address varchar(100),
  phone varchar(100),
  hash varchar(32) NOT NULL UNIQUE KEY
);

ALTER TABLE participants ADD INDEX party_id(party_id);

CREATE TABLE bounces (
  id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
  email varchar(320) NOT NULL,
  bounce_type varchar(100) NOT NULL,
  bounce_sub_type varchar(100) NOT NULL,
  created_at DATETIME(3) NOT NULL
);

ALTER TABLE bounces ADD INDEX email(email);

-- ====================
-- データ投入
-- ====================

INSERT INTO users (
  name,
  email,
  password
) VALUES (
  'norieri', 'norihito20@gmail.com', '$2a$10$LtAu/zx522X2i7vYHgPpo.QG5WY9zLSEwS0XGwJ5rR7Sen3hOPCMi'
);

INSERT INTO places (
  name,
  address,
  phone,
  url
) VALUES (
  'TRUNK BY SHOTO GALLERY', '〒150-0046 東京都渋谷区松濤1丁目5−4', '03-5784-1060
', 'https://trunk-shoto.com/'
);

INSERT INTO parties (
  user_id,
  place_id,
  name,
  date,
  m_price,
  f_price,
  hash
) VALUES (
  1, 1, 'Nori & Eri\'s Wedding', '2018-04-22', 8000, 6000, '9BKviZmm'
);

-- INSERT INTO participants (
--   party_id,
--   first_name,
--   last_name,
--   email,
--   gender,
--   side,
--   attendance,
--   message,
--   postal_code,
--   address,
--   phone
-- ) VALUES (
--   1, '一樹', '朏島', 'k.haijima@gmail.com', 1, 1, 1, 'おめでとー', NULL, NULL, NULL
-- ), (
--   1, '優作', '徳永', 'yusaku.tokunaga@gmail.com', 1, 1, 1, NULL, NULL, NULL, NULL  
-- ), (
--   1, 'じゅん','', '', 0, 0, 1, NULL, NULL, NULL, NULL  
-- );

-- INSERT INTO bounces (
--   id,
--   email,
--   bounce_type,
--   bounce_sub_type,
--   created_at
-- ) VALUES (
--   1, 'ishibe.tatsuya@gmail.com', 'Pendding', 'Pendding', '2017-03-12 00:00:00'
-- ),(
--   2, 'ishibe.tatsuya@gmail.com', 'Pendding', 'Pendding', '2017-03-13 00:00:00'
-- );

COMMIT;
