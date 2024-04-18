insert into board(title, content, author, createdDate, modifiedDate)
values ('test1', '테스트1번 입니다.', 'testuser', sysdate, sysdate);
insert into board(title, content, author, createdDate, modifiedDate)
values ('test2', '테스트2번 입니다.', 'testuser', sysdate, sysdate);

insert into todo(content, completed, category)
values ('테스트1번 입니다.', false, 1);
insert into todo(content, completed, category)
values ('테스트2번 입니다.', false, 1);

insert into category(name)
values ('업무');
insert into category(name)
values ('개인');
