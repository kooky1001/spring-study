insert into user (user_id, name, password, email, create_date, update_date) values ('test', 'testname', 'test', 'test@test.com', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into user (user_id, name, password, email, create_date, update_date) values ('pest', 'pestname', 'pest', 'pest@test.com', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into board (content, title, user_id, create_date, update_date) values ('테스트 내용', 'test 유저가 입력한 게시판', 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into board (content, title, user_id, create_date, update_date) values ('테스트 내용2으로 길게 된다면 어떠할지 테스트', '짧은 제목', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into board (content, title, user_id, create_date, update_date) values ('테스트 내용3', '긴 제목', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


insert into reply (content, board_id, user_id, create_date, update_date) values ('테스트 내용', 1, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into reply (content, board_id, user_id, create_date, update_date) values ('테스트 내용2으로 길게 된다면 어떠할지 테스트', 1, 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into reply (content, board_id, user_id, create_date, update_date) values ('2번 게시판에 길게 된다면 어떠할지 테스트', 2, 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
