DROP TABLE IF EXISTS CoinName;
CREATE TABLE CoinName (
                      id INT AUTO_INCREMENT  PRIMARY KEY,
                      en VARCHAR(20) NOT NULL UNIQUE ,
                      ch VARCHAR(50) NOT NULL,
                      createDate timestamp default current_timestamp,
                      updateDate timestamp default current_timestamp
);