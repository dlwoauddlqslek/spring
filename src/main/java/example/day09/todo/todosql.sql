drop database if exists TodoList;
create database TodoList;
use TodoList;

drop table if exists member;
create table member(            # 회원정보
   memberCode int auto_increment,
    content longtext,
    color int default 0,
    primary key(memberCode)
);

insert into member(content) values("밥먹기");
insert into member(content) values("공부");
insert into member(content) values("운동");
select * from member;