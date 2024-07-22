   drop database if exists springweb;
   create database springweb;
   use springweb;

   drop tables if exists member;
   create table member(
      no bigint auto_increment ,            -- 회원번호
          id varchar(30) not null unique ,            -- 회원 아이디
          pw varchar(30) not null ,            -- 회원 비밀번호
          name varchar(20) not null ,            -- 회원 이름
          email varchar(50) ,               -- 회원 이메일
          phone varchar(15) not null unique,         -- 회원 핸드폰 번호
          constraint member_no_pk primary key(no )       -- 회원 번호 pk
   );
   select * from member;
   
   insert into member(id,pw,name,email,phone) values('qwerasdf','12345','기성','asdf','010-1234-5678');
   #binary 대문자 소문자 구별 가능
   select * from member where binary(id)='qwerasdf' 
   