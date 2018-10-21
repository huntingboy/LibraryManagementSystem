show databases;
use librarymanagementsystem;
show tables;

-- 读者表
-- 从txt导入数据
load data local infile '/home/nomad/Desktop/testin_reader.txt'
into table Reader;
-- 导出数据到txt(存在权限错误，修改了还是错误，导出到/tmp也出错)
select *  
into outfile '//home/nomad/Desktop/testout.txt'  
lines terminated by '/r/n'
from Reader;
show warnings;
describe Lend;

select *
from Reader;
select Lend.*, Rname from Lend, Reader where Lend.Rno = Reader.Rno and Lend.Rno = 9;

delete
from Reader;
drop table Lend;
truncate Reader;

-- 图书表
describe Book;
-- 从txt导入数据
load data local infile '/home/nomad/Desktop/testin_book.txt'
into table Book;
show warnings;

select *
from Book;

-- 借阅信息表
describe Lend;
-- 从txt导入数据
load data local infile '/home/nomad/Desktop/testin_lend.txt'
into table Lend;

select *
from Lend;

insert 
into Lend
values(10, 1, '2016-11-11', '2016-12-12', -1);

update Lend 
set Quantity = Quantity + 1 
where Bno = 2 and Rno = 2;

select Lend.*, Rname
from Lend, Reader
where Lend.Rno = Reader.Rno and
	Lend.Rno = 2;

-- 登录用户信息表
describe MUser;
insert
into MUser(Uname, Ukey)
values('user1', '001', 0);

select *
from MUser;

truncate MUser;
