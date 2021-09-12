CREATE TABLE t_user (
  id varchar(40) NOT NULL,
  name varchar(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE t_item (
  id varchar(40) NOT NULL,
  name varchar(255) NOT NULL ,
  price varchar(20) NOT NULL ,
  status int NOT NULL ,
  stockNum int NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE t_order (
  id varchar(40) NOT NULL,
  user_id varchar(40) NOT NULL,
  status int NOT NULL ,
  price varchar(50) ,
  create_time bigint,
  update_time bigint,
  PRIMARY KEY (id)
);

CREATE TABLE t_order_item (
  id varchar(40) NOT NULL,
  order_id varchar(40) ,
  item_id varchar(40),
  price varchar(20),
  num int,
  PRIMARY KEY (id)
);