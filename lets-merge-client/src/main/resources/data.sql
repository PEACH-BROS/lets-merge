insert into user values (1, 'dummy@gmail.com', '엘리', 'profile.png', 'ADMIN');
insert into mission values (10, '2021-08-01T00:00:00', '체스 미션', '2020-08-01T00:00:00'); --dueDateTime / startDateTime
insert into mission values (20, '2021-08-01T00:00:00', '블랙잭 미션', '2020-08-01T00:00:00'); --dueDateTime / startDateTime
insert into mission values (30, '2020-09-01T00:00:00', '체스 미션', '2020-08-01T00:00:00'); --dueDateTime / startDateTime
insert into assign_info (id, user_id, mission_id, assign_status, assign_date_time, update_date_time) values (100, 1, 10, 'ASSIGN', '2020-08-01T00:00:00', '2020-08-01T00:00:00');
