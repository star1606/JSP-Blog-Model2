#### jsp 모델2 블로그 프로젝트

## 오라클 12c 사용자 생성 
```sql

alter session set "_ORACLE_SCRIPT"=true;
CREATE USER cos1 IDENTIFIED BY bitc5600;

GRANT CREATE SESSION TO cos;
GRANT CREATE TABLESPACE TO COS;

GRANT CREATE TABLE TO COS;
GRANT select, insert, delete, update ON cos.table1 TO cos;
alter user cos default tablespace users quota unlimited on users;


```

##테이블
CREATE TABLE users(
	id number primary key,
    username varchar2(100) not null unique,
    password varchar2(100) not null,
    email varchar2(100) not null,
    address varchar2(100) not null,
    userProfile varchar2(200),
    createDate timestamp
) ;

CREATE TABLE board(
	id number primary key,
    userId number,
    title varchar2(100) not null,
    content clob,
    readCount number default 0,
    createDate timestamp,
    foreign key (userId) references users (id)
);

CREATE TABLE reply(
	id number primary key,
    userId number,
    boardId number,
    content varchar2(300) not null,
    createDate timestamp,
    foreign key (userId) references users (id) on delete set null,
    foreign key (boardId) references board (id) on delete cascade
);

```

## 시퀀스
```sql
CREATE SEQUENCE USER_SEQ
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE BOARD_SEQ
    START WITH 1
    INCREMENT BY 1;
    
CREATE SEQUENCE REPLY_SEQ
    START WITH 1
    INCREMENT BY 1;



```
