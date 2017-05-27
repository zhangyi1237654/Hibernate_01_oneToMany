CREATE TABLE grade(
gid int PRIMARY KEY,
gname varchar(20) not null,
gdesc varchar(50)
);

CREATE TABLE student(
sid int PRIMARY KEY,
sname varchar(20),
sex varchar(2),
gid int
);
alter table student add constraint fk_student_gid foreign key (gid)
references grade(gid);