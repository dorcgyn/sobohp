create database if not exists soboHp;

create table if not exists soboHp.user_t (
	id int(11)  NOT NULL AUTO_INCREMENT,
	username varchar(50),
	password varchar(50),
	name varchar(50),
	email varchar(50),
	comments varchar(200),
	PRIMARY KEY (id)
)CHARACTER SET = utf8;

create table if not exists soboHp.transaction_t (
	id int(11) NOT NULL AUTO_INCREMENT,
	lender_id int(11),
	borrow_id int(11),
	generator_id int(11),
	type varchar(20),
	tran_date date,
	created_when datetime,
	location varchar(50),
	amount numeric(15,2),
	comment varchar(200),
	status varchar(10),
	rid int(11),
	PRIMARY KEY (id),
	FOREIGN KEY (lender_id)
      REFERENCES user_t(id),
    FOREIGN KEY (borrow_id)
      REFERENCES user_t(id),
	FOREIGN KEY (generator_id)
      REFERENCES user_t(id)
)CHARACTER SET = utf8;


-- insert user table data
insert into soboHp.user_t (username, name, email, comments) 
	values ('SiYuan Xu', '徐思远', 'ning.yang2@hp.com', '..');

insert into soboHp.user_t (username, name, email, comments) 
	values ('Elvis Zhou', 'Elvis', 'ning.yang2@hp.com', '');

insert into soboHp.user_t (username, name, email, comments) 
	values ('Chris Tan', 'Chris', 'ning.yang2@hp.com', '');

insert into soboHp.user_t (username, name, email, comments) 
	values ('Yu Zhao', '赵宇', 'ning.yang2@hp.com', '..');

insert into soboHp.user_t (username, name, email, comments) 
	values ('ShiRui Peng', '彭ShiRui', 'ning.yang2@hp.com', '..');

insert into soboHp.user_t (username, name, email, comments) 
	values ('Yue Lu', '卢玥', 'ning.yang2@hp.com', '..');

insert into soboHp.user_t (username, name, email, comments) 
	values ('Coco Song', '宋文玲', 'ning.yang2@hp.com', '..');


insert into soboHp.user_t (username, name, email, comments) 
	values ('Lit', 'Lit', 'ning.yang2@hp.com', '..');

insert into soboHp.user_t (username, name, email, comments) 
	values ('Henry', 'Henry', 'ning.yang2@hp.com', '..');

insert into soboHp.user_t (username, name, email, comments) 
	values ('Ning Yang', '杨宁', 'ning.yang2@hp.com', 'admin');

insert into soboHp.user_t (username, name, email, comments) 
	values ('Roger T', 'Roger', 'ning.yang2@hp.com', 'admin');

-- drop two tables
drop table soboHp.transaction_t;
drop table soboHp.user_t;