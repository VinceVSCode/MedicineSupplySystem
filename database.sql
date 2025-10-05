-- v0.1 schema draft
--Auto to kommati egine me thn vohtheia tou chatgpt, me epivlepsi diki mou
-- h empeiria mou me SQL einai kurios me MongoDB kai SQLAlchemy, 
--diladi relational databases pou trexoun sql me morfi python objects kai methods.


CREATE TABLE medicine_category(
  id            BIGINT PRIMARY KEY,
  name          VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE medicine(
  id            BIGINT PRIMARY KEY,
  code          VARCHAR(64)  NOT NULL UNIQUE,
  name          VARCHAR(200) NOT NULL,
  price         DECIMAL(12,2) NOT NULL,
  stock         INT NOT NULL CHECK (stock >= 0),
  category_id   BIGINT NOT NULL REFERENCES medicine_category(id)
);

CREATE TABLE warehouse_movement(
  id            BIGINT PRIMARY KEY,
  type          VARCHAR(32) NOT NULL, 
  medicine_id   BIGINT NOT NULL REFERENCES medicine(id),
  amount        INT NOT NULL CHECK (amount > 0),
  date          DATE NOT NULL
);
