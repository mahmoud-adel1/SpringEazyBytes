INSERT INTO `holidays` (`day`,`reason`,`type`,`created_at`, `created_by`)
VALUES (' Jan 1 ','New Year''s Day','FESTIVAL',CURDATE(),'DBA');

INSERT INTO `holidays` (`day`,`reason`,`type`,`created_at`, `created_by`)
VALUES (' Oct 31 ','Halloween','FESTIVAL',CURDATE(),'DBA');

INSERT INTO `holidays` (`day`,`reason`,`type`,`created_at`, `created_by`)
VALUES (' Nov 24 ','Thanksgiving Day','FESTIVAL',CURDATE(),'DBA');

INSERT INTO `holidays` (`day`,`reason`,`type`,`created_at`, `created_by`)
VALUES (' Dec 25 ','Christmas','FESTIVAL',CURDATE(),'DBA');

INSERT INTO `holidays` (`day`,`reason`,`type`,`created_at`, `created_by`)
VALUES (' Jan 17 ','Martin Luther King Day','FEDERAL',CURDATE(),'DBA');

INSERT INTO `holidays` (`day`,`reason`,`type`,`created_at`, `created_by`)
VALUES (' July 4 ','Independence Day','FEDERAL',CURDATE(),'DBA');

INSERT INTO `holidays` (`day`,`reason`,`type`,`created_at`, `created_by`)
VALUES (' Sep 5 ','Labor Day','FEDERAL',CURDATE(),'DBA');

INSERT INTO `holidays` (`day`,`reason`,`type`,`created_at`, `created_by`)
VALUES (' Nov 11 ','Veterans Day','FEDERAL',CURDATE(),'DBA');

INSERT INTO `role` (role_name, created_at, created_by)
VALUES ('ADMIN',CURDATE(),'DBA');

INSERT INTO `role` (role_name, created_at, created_by)
VALUES ('STUDENT',CURDATE(),'DBA');

DELETE FROM eazyschool.person
WHERE email = 'admin@eazyschool.com';

INSERT INTO `person` (name, mobile_number, email, password, role_id, created_at, created_by)
VALUES ('Admin','1028614035','admin@eazyschool.com','$2a$10$ARXoejYxvmjVknAEj1Ip3uOzKfkQpWEb0MBbm0LAA15xXCBs52aYa',1,CURDATE(),'DBA');

