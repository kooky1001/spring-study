insert into board(title, content, author, createdDate, modifiedDate)
values ('test1', '테스트1번 입니다.', 'testuser', sysdate, sysdate);
insert into board(title, content, author, createdDate, modifiedDate)
values ('test2', '테스트2번 입니다.', 'testuser', sysdate, sysdate);

insert into todo(content, completed, toDate, category)
values ('테스트1번 입니다.', false, '2024-04-15', 'work');
insert into todo(content, completed, toDate, category)
values ('테스트2번 입니다.', false, '2024-04-15', 'work');

insert into category(name, description, active)
values ('work', '업무', true);
insert into category(name, description, active)
values ('personal', '개인', true);
