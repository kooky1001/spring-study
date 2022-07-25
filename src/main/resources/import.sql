insert into user (user_id, name, password, email) values ('test', 'testname', 'test', 'test@test.com');
insert into user (user_id, name, password, email) values ('pest', 'pestname', 'pest', 'pest@test.com');

insert into board (content, title, user_id) values ('테스트 내용', 'test 유저가 입력한 게시판', 1);
insert into board (content, title, user_id) values ('테스트 내용2으로 길게 된다면 어떠할지 테스트', '짧은 제목', 2);
insert into board (content, title, user_id) values ('테스트 내용3', '긴 제목', 2);