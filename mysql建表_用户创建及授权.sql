show databases;
-- 图书管理系统数据库
use librarymanagementsystem;
show tables;
describe Book;
describe Reader;
describe Lend;
describe MUser;

drop table Book;
drop table Reader;
drop table Lend;
drop table MUser;

drop trigger TestField1_BeforeInsert;
drop trigger TestField1_BeforeUpdate;
drop trigger TestField3_BeforeInsert;
drop trigger TestField3_BeforeUpdate;
drop trigger TestField2_BeforeInsert;
drop trigger TestField2_BeforeUpdate;

-- 解决check约束无效问题
DELIMITER $$      
  
CREATE TRIGGER TestField1_BeforeInsert BEFORE INSERT ON Book
FOR EACH ROW  
BEGIN  
    IF NEW.Quantity < 0 THEN  
        SET NEW.Quantity = 0;  
    END IF;  
END$$ 
DELIMITER $$      
  
CREATE TRIGGER TestField1_BeforeUpdate BEFORE UPDATE ON Book
FOR EACH ROW  
BEGIN  
    IF NEW.Quantity < 0 THEN  
        SET NEW.Quantity = 0;  
    END IF;  
END$$ 
DELIMITER $$      
  
CREATE TRIGGER TestField3_BeforeInsert BEFORE INSERT ON MUser
FOR EACH ROW  
BEGIN  
    IF NEW.Uright not between 0 and 1 THEN  
        SET NEW.Uright = 1;  
    END IF;  
END$$ 
DELIMITER $$      
  
CREATE TRIGGER TestField3_BeforeUpdate BEFORE UPDATE ON MUser
FOR EACH ROW  
BEGIN  
    IF NEW.Uright not between 0 and 1 THEN  
        SET NEW.Uright = 1;  
    END IF; 
END$$ 
DELIMITER $$      

CREATE TRIGGER TestField2_BeforeInsert BEFORE INSERT ON Lend 
FOR EACH ROW  
BEGIN  
    IF NEW.Quantity < 0 THEN  
        SET NEW.Quantity = 0;  
    END IF;  
END$$ 
DELIMITER $$      

CREATE TRIGGER TestField2_BeforeUpdate BEFORE UPDATE ON Lend 
FOR EACH ROW  
BEGIN  
    IF NEW.Quantity < 0 THEN  
        SET NEW.Quantity = 0;  
    END IF;  
END$$ 

-- 图书
create table Book(
	Bno int auto_increment primary key,
    Bname char(15) not null,
    Bauthor char(4),
    Bpublishment char(20),
    Quantity int check(Quantity >= 0)
    );
    
show errors;
    alter table Book modify column Bname char(15);
    alter table Book modify column Bpublishment char(20);
-- 读者
create table Reader(
	Rno int auto_increment primary key,
    Rname char(4) not null,
    Rsex char(2) check(Rsex between '男' and '女')
    );
-- 借阅
create table Lend(
	Bno int not null,
    Rno int not null,
    Ltime date,
    Rtime date,
    Quantity int check(Quantity >= 0),
    primary key(Bno, Rno),
    foreign key(Bno) references Book(Bno),
    foreign key(Rno) references Reader(Rno)
    );
    alter table Lend add column Quantity int;
    alter table Lend add  constraint check(Quantity >= 0);
-- 登录用户
create table MUser(
    Uname  char(10) primary key,
    Ukey char(20) not null,
    Uright int not null check(Uright between 0 and 1);
    );
    alter table MUser add column Uright int not null check(Uright between 0 and 1);
    select *
    from MUser;

-- 创建用户同时赋予增删查改权限
grant insert, delete, select, update
on librarymanagementsystem.*
to test@'%'
with grant option;
grant drop
on librarymanagementsystem.*
to test@'localhost'
with grant option;

show warnings;

use mysql;
describe user;

select *
from user
where User = 'test';

flush privileges;
