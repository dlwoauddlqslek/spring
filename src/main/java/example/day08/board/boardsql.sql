drop database if exists Board;
create database Board;
use Board;

drop table if exists board;
create table board(            # 회원정보
   bno int auto_increment,
	content longtext,
	bpw varchar(15),
    primary key(bno),
	title longtext,
    date datetime default now(),
    view int default 5
);

insert into board(title,content,bpw) values("밥먹기","아아아","1111");
insert into board(title,content,bpw) values("아무거나","가가가","2222");
insert into board(title,content,bpw) values("더조은","나나나","3333");

select * from board;