-- alter session set "_oracle_script" = true;

-- create user dogeuser identified by doge
-- default TABLESPACE users
-- TEMPORARY TABLESPACE temp;

-- grant connect, resource, dba to dogeuser;

drop sequence goods_pk_seq;
drop sequence cart_pk_seq;
drop sequence purchase_pk_seq;
drop sequence question_pk_seq;
drop sequence answer_pk_seq;
drop sequence manager_pk_seq;
drop sequence member_pk_seq;

drop table goods;
drop table cart;
drop table purchase;
drop table question;
drop table answer;
drop table manager;
drop table member;

create sequence member_pk_seq start with 1 increment by 1 nocache nocycle;
create sequence manager_pk_seq start with 1 increment by 1 nocache nocycle;
create sequence goods_pk_seq start with 1 increment by 1 nocache nocycle;
create sequence cart_pk_seq start with 1 increment by 1 nocache nocycle;
create sequence purchase_pk_seq start with 1 increment by 1 nocache nocycle;
create sequence question_pk_seq start with 1 increment by 1 nocache nocycle;
create sequence answer_pk_seq start with 1 increment by 1 nocache nocycle;

create table member (
    member_id number default member_pk_seq.nextval constraint member_pk primary key,
    id varchar2(16) not null unique,
    pw varchar2(60) not null,
    phone varchar2(11) not null,
    addr varchar2(50) not null,
    reg_date date default sysdate
);

create table manager (
    manager_id number default manager_pk_seq.nextval constraint manager_pk primary key,
    id varchar2(16) not null unique,
    pw varchar2(60) not null,
    reg_date date default sysdate
);

create table goods (
    goods_id number default goods_pk_seq.nextval constraint goods_pk primary key,
    title varchar2(40) not null,
    description varchar2(1000) not null,
    title_img varchar2(50) default 'default.png',
    price number(6) check (price >= 0) not null,
    discount number(3) check (discount between 0 and 100) not null,
    quantity number(4) check (quantity >= 0) not null,
    category varchar2(20) not null,
    reg_date date default sysdate
);

create table cart (
    cart_id number default cart_pk_seq.nextval constraint cart_pk primary key,
    member_id references member (member_id),
    goods_id references goods (goods_id),
    quantity number(4) not null,
    total number default 0 not null,
    reg_date date default sysdate
);

create table purchase (
    purchase_id number default purchase_pk_seq.nextval constraint purchase_pk primary key,
    member_id references member (member_id),
    goods_id references goods (goods_id),
    quantity number(4) not null,
    total number default 0 not null,
    reg_date date default sysdate
);

create table question (
    question_id number default question_pk_seq.nextval constraint question_pk primary key,
    title varchar2(40) not null,
    content varchar2(1000) not null,
    author constraint question_member_fk references member (member_id),
    reg_date date default sysdate
);

create table answer (
    answer_id number default answer_pk_seq.nextval constraint answer_pk primary key,
    question_id number constraint answer_question_fk references question (question_id),
    content varchar2(1000) not null,
    reg_date date default sysdate
);

insert into manager (id, pw) values ('admin', 'admin');

insert into goods(title, description, title_img, price, discount, quantity, category) values ('test','test', 'test.png', 10001, 0,100, 'other');
insert into goods(title, description, price, discount, quantity, category) values ('test1','test1', 10002, 0,100, 'other');
insert into goods(title, description, price, discount, quantity, category) values ('test2','test2', 10003, 0,100, 'other');
insert into goods(title, description, price, discount, quantity, category) values ('test3','test3', 10004, 0,100, 'other');
insert into goods(title, description, price, discount, quantity, category) values ('test4','test4', 10005, 0,100, 'other');
insert into goods(title, description, price, discount, quantity, category) values ('test5','test5', 10006, 0,100, 'other');
insert into goods(title, description, price, discount, quantity, category) values ('test6','test6', 10007, 0,100, 'other');
insert into goods(title, description, price, discount, quantity, category) values ('test7','test7', 10008, 0,100, 'other');

commit;


select * from manager;
select * from member;
select * from goods;